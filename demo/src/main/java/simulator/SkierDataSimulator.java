package simulator;

import simulator.utils.Day;
import simulator.utils.Season;
import simulator.utils.Weather;

import java.util.List;

public class SkierDataSimulator implements IDataSimulator {
    /**
     * generate Data is the entry point of the project, input of Weather weather, Season season, Day day,
     * String liftConnectCSV and output the String outputCSV
     *
     */
    @Override
    public void generateData(Season season, Day day, Weather weather, String csvfilename ) {
        SkiCondition skiCondition = new SkiCondition(weather,
                season, day);

        csvfilename += weather.toString() + "-" +
                season.toString() + "-" +day.toString();

        List<Skier> skiers = simulator.ISkiResort.generateSkiers(skiCondition);

        new CSVWriter(skiers, csvfilename);
    }
}
