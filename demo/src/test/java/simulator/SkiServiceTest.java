package simulator;

import org.junit.Before;
import org.junit.Test;
import simulator.utils.BaseArea;
import simulator.utils.SkiLevel;

import java.sql.Time;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.Assert.*;

public class SkiServiceTest {
  private SkiService ss;
  private UUID skierId;
  private SkiLevel skiLevel;
  private Boolean haveLunch;
  private BaseArea baseArea;
  private String currentLoc;
  private Time startTime;
  private Time previousTime;
  private Skier skier;


  @Before
  public void setUp() throws Exception {
    ss = new SkiService();
    skierId = UUID.randomUUID();
    skiLevel = SkiLevel.ADVANCED;
    haveLunch = true;
    baseArea = BaseArea.WHISTLER_GONDOLA;
    startTime = Time.valueOf("09:40:00");
    skier = new Skier(skierId,  skiLevel,  haveLunch,  baseArea,  startTime);
  }

  @Test
  public void generateRuns() {
    List<Map.Entry<Time, String>> runs = ss.generateRuns( skier);
//    System.out.println("runs len = "+runs.size());
//    for (Map.Entry e : runs) {
//      System.out.println(e.getKey() + " : "+e.getValue());
//    }
  }

  @Test
  public void generateLiftGraph() {
    Map<String,List<String>> graph = ss.generateLiftGraph();
//    for (Map.Entry e : graph.entrySet()) {
//      System.out.println(e.getKey() + " : "+e.getValue());
//    }
  }
}