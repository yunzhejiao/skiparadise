package simulator;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LiftTest {
  private Lift l;

  @Before
  public void setUp() throws Exception {
    l = new Lift("RED");
  }

  @Test
  public void getLiftName() {
    assertEquals("RED", l.getLiftName());
  }
}