package simulator;

import simulator.utils.Day;
import simulator.utils.Season;
import simulator.utils.Weather;

public interface IDataSimulator {
    void generateData(Season season, Day day, Weather weather, String csvfilename);
}
