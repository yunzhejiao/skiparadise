package simulator;

import org.junit.Before;
import org.junit.Test;
import simulator.utils.Day;
import simulator.utils.Season;
import simulator.utils.Weather;

import java.util.List;

import static org.junit.Assert.*;

public class SkiResortTest {
  private SkiResort skiResort;
  private SkiCondition skiCondition;

  @Before
  public void setUp() throws Exception {
    skiCondition = new SkiCondition(Weather.STORMY, Season.REGULAR, Day.MONDAY);
    skiResort = new SkiResort(skiCondition);

  }

  @Test
  public void generateSkiers() {
    skiResort.generateSkiers();
  }

  @Test
  public void getSkiCondition() {
    skiResort.getSkiCondition();
  }

  @Test
  public void getSkiers() {
    skiResort.getSkiers();
  }
}