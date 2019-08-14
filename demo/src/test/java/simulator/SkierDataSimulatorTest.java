package simulator;

import org.junit.Before;
import org.junit.Test;
import simulator.utils.Day;
import simulator.utils.Season;
import simulator.utils.Weather;
import static simulator.utils.MetricsConstant.RELATIONSHIPCSV;

public class SkierDataSimulatorTest {
  SkierDataSimulator skierDataSimulator;
  private Season season;
  private Day day;
  private Weather weather;

  @Before
  public void setUp() throws Exception {
    weather = Weather.AVERAGE;
    season = Season.REGULAR;
    day = Day.MONDAY;
    skierDataSimulator = new SkierDataSimulator();
  }

  @Test
  public void generateData() {
    skierDataSimulator.generateData(season, day, weather, RELATIONSHIPCSV+"SkierDataSimulatorTest");
  }
}