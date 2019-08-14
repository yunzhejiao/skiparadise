package simulator;

import org.junit.Test;
import simulator.utils.Day;
import simulator.utils.Season;
import simulator.utils.Weather;

public class ISkiResortTest {

  @Test
  public void generateSkiers() {
    ISkiResort.generateSkiers(new SkiCondition(Weather.STORMY, Season.REGULAR, Day.MONDAY));
  }
}