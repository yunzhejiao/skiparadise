package simulator;

import org.junit.Before;
import org.junit.Test;

import java.sql.Time;
import java.util.UUID;

import static org.junit.Assert.*;

public class DataEntryTest {
  private UUID skierId;
  private String liftName;
  private Time time;
  private DataEntry de;
  @Before
  public void setUp() throws Exception {
    skierId = UUID.randomUUID();
    liftName = "RED";
    time = Time.valueOf("09:40:00");
    de = new DataEntry(skierId, liftName, time);
  }

  @Test
  public void toStringTest() {
    String s = skierId + "," + liftName + "," + time + "\n";
    assertEquals(s, de.toString());
  }
}