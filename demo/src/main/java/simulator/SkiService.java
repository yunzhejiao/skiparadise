package simulator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.*;

import static simulator.utils.MetricsConstant.*;

public class SkiService {
  protected Time recordTime;

  public SkiService() {
  }

  private Map<String, List<String>> generateLiftGraphhelper(String liftGraphCsv) {
    try {
      BufferedReader br = new BufferedReader(new FileReader(liftGraphCsv));
      String header = br.readLine();
      String[] liftnames = header.split(","); // To get length for adjacency matrix
      HashMap<Integer, String> liftIds = new HashMap<>();

      for (int i = 1; i < liftnames.length; i++) {
        liftIds.put(i - 1, liftnames[i]); // Lifts is the key and index is a value.
      }

      Map<String, List<String>> liftgraph = new HashMap<>();

      String line;
      int r = 0, cols = liftnames.length;
      while ((line = br.readLine()) != null) {
        String[] row = line.split(",");

        for (int j = 1; j < cols; j++) {
          if (row[j].equals("1")){
            List<String> adjLifts = liftgraph.getOrDefault(liftIds.get(r), new ArrayList<>());
            adjLifts.add(liftIds.get(j-1));
            liftgraph.put(liftIds.get(r), adjLifts);
          }
        }
        r++;
      }
      br.close();
      return liftgraph;
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      return null;
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }


  public List<Map.Entry<Time, String>> generateRuns(Skier skier) {
        List<Map.Entry<Time, String>> runs = new ArrayList<>();
        for (int i = 0; i < skier.getSkiLevel().getValue(); i++) {
            if (i == 0) {
                recordTime = skier.getStartTime();
            } else {
                recordTime = calculateNextSkiingTime(skier);
                if (recordTime != null) {
                    skier.setPreviousTime(recordTime);
                } else {
                    break;
                }
            }

            String startLift = skier.getCurrentLoc();
            Map<String, List<String>>  liftGraph = generateLiftGraph();
            List<String> connectedLift = liftGraph.get(startLift);
            if (connectedLift != null && connectedLift.size() > 0) {
              String nextStation = connectedLift.get(new Random().nextInt(connectedLift.size()));
              skier.setCurrentLoc(nextStation);
             runs.add(new AbstractMap.SimpleEntry<>(recordTime, startLift));
            }
        }
        return runs;
    }

  public Map<String,List<String>> generateLiftGraph() {
    Map<String,List<String>> wholeGraph = new HashMap<>();
    Map<String,List<String>> whistlerGraph= generateLiftGraphhelper(WHISTLER_PATH);
    Map<String,List<String>> blackcombGraph = generateLiftGraphhelper(BLACKCOMB_PATH);
    wholeGraph.putAll(whistlerGraph);
    wholeGraph.putAll(blackcombGraph);
    return wholeGraph;
  }


  private Time calculateNextSkiingTime(Skier skier) {
        Time startTime = skier.getPreviousTime();
        Random rand = new Random();

        int intervalMinute =  0;
        switch (skier.getSkiLevel()) {
          case ADVANCED:
            intervalMinute = INTERVAL_ADVANCED + new Random().nextInt(INTERVAL_DEVIATION);
            break;
          case INTERMEDIATE:
            intervalMinute = INTERVAL_INTERMEDIATE + new Random().nextInt(INTERVAL_DEVIATION);
            break;
          case BEGINNER:
            intervalMinute = INTERVAL_BEGINNER + new Random().nextInt(INTERVAL_DEVIATION);
            break;
          default:
            break;
        }

      LocalTime localtime = startTime.toLocalTime();
      localtime = localtime.plusMinutes(intervalMinute);
      localtime = localtime.plusSeconds(rand.nextInt(SECONDS));

      //add one more hour for break if skier have lunch.
      if (skier.getHaveLunch() && startTime.getHours() == 12) {
        localtime = localtime.plusHours(1);
      }
      Time nextTime = new Time(localtime.getHour(), localtime.getMinute(), localtime.getSecond());
      if (nextTime.getTime() < Time.valueOf("15:00:00").getTime()) {
          return nextTime;
      } else {
          return null;
      }
    }
}
