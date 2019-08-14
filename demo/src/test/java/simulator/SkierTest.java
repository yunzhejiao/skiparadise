package simulator;

import org.junit.Before;
import org.junit.Test;
import simulator.utils.BaseArea;
import simulator.utils.SkiLevel;

import java.sql.Time;
import java.util.Map;
import java.util.UUID;

import static org.junit.Assert.*;

public class SkierTest {
  private Map<Time, Lift> runs;
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
    skierId = UUID.randomUUID();
    skiLevel = SkiLevel.ADVANCED;
    haveLunch = true;
    baseArea = BaseArea.WHISTLER_GONDOLA;
    startTime = new Time(111111111);
    skier = new Skier(skierId,  skiLevel,  haveLunch,  baseArea,  startTime);
  }

  @Test
  public void getStartTime() {
    assertEquals(skier.getStartTime(), startTime);
  }

  @Test
  public void getPreviousTime() {
    assertEquals(skier.getPreviousTime(), startTime);
  }

  @Test
  public void setPreviousTime() {
    Time newPrev = new Time(111111112);
    skier.setPreviousTime(newPrev);
    assertEquals(skier.getPreviousTime(), newPrev);
  }

  @Test
  public void getSkierId() {
    assertEquals(skier.getSkierId(), skierId);
  }

  @Test
  public void getSkiLevel() {
    assertEquals(skier.getSkiLevel(), skiLevel);
  }

  @Test
  public void getCurrentLoc() {
    assertEquals(skier.getCurrentLoc(), baseArea.toString());
  }

  @Test
  public void setCurrentLoc() {
    BaseArea newLoc = BaseArea.CREEKSIDE_GONDOLA;
    skier.setCurrentLoc(newLoc.toString());
    assertEquals(skier.getCurrentLoc(), newLoc.toString());
  }
}