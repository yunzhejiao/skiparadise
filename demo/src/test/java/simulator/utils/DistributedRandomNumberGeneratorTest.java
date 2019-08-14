package simulator.utils;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class DistributedRandomNumberGeneratorTest {
  private static Map<Integer, Double> distribution;
  DistributedRandomNumberGenerator drng;
  @Before
  public void setUp() throws Exception {
    distribution = new HashMap<>();
    drng = new DistributedRandomNumberGenerator();
    drng.addNumber(1, 0.2d);
    drng.addNumber(2, 0.3d);
  }

  @Test
  public void getDistributedRandomNumber() {
    drng.addNumber(3, 0.5d);

    int testCount = 1000000;
    for (int i = 0; i < testCount; i++) {
      int random = drng.getDistributedRandomNumber();
      distribution.put(random, (distribution.get(random) == null) ? (1d / testCount) :
        distribution.get(random) + 1d / testCount);
    }

    assertEquals(distribution.get(1), 0.2, 0.01);
    assertEquals(distribution.get(2), 0.3, 0.01);
    assertEquals(distribution.get(3), 0.5, 0.01);
    System.out.println(distribution);
    }
}