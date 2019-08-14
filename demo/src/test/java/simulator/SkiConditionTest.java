package simulator;

import org.junit.Before;
import org.junit.Test;
import simulator.utils.Day;
import simulator.utils.Season;
import simulator.utils.Weather;

import static org.junit.Assert.*;

public class SkiConditionTest {
  Weather average;
  Season regular;
  Day monday;
  SkiCondition skiCondition1;
  Weather stormy;
  Weather powder;
  Season peak;
  Season late;
  Season early;
  Day friday;
  SkiCondition skiCondition2;
  SkiCondition skiCondition3;
  SkiCondition skiCondition4;
  SkiCondition skiCondition5;
  SkiCondition skiCondition6;
  SkiCondition skiCondition7;


  @Before
  public void setUp() throws Exception {
    average = Weather.AVERAGE;
    regular = Season.REGULAR;
    monday = Day.MONDAY;
    skiCondition1 = new SkiCondition(average, regular, monday);

    stormy = Weather.STORMY;
    friday = Day.FRIDAY;
    skiCondition2 = new SkiCondition(stormy, regular, monday);
    skiCondition3 = new SkiCondition(stormy, regular, friday);

    powder = Weather.POWDER;
    skiCondition4 = new SkiCondition(powder, regular, monday);

    peak = Season.PEAK;
    late = Season.LATE;
    early = Season.EARLY;
    skiCondition5 = new SkiCondition(average, peak, monday);
    skiCondition6 = new SkiCondition(average, late, monday);
    skiCondition7 = new SkiCondition(average, early, monday);

  }

  @Test
  public void getSkierNumber() {
    assertEquals(skiCondition1.getSkierNumber(), 10000);
    assertEquals(skiCondition2.getSkierNumber(), 5000);
    assertEquals(skiCondition3.getSkierNumber(), 7000);
    assertEquals(skiCondition4.getSkierNumber(), 12500);
    assertEquals(skiCondition5.getSkierNumber(), 11000);
    assertEquals(skiCondition6.getSkierNumber(), 8000);
    assertEquals(skiCondition7.getSkierNumber(), 8000);

  }
}