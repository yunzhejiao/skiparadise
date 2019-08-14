package simulator;

import org.junit.Test;

import java.io.ByteArrayInputStream;

public class AppTest {

  @Test
  public void main() {
    String simulatedUserInput = "STORMY" + System.getProperty("line.separator")
      + "REGULAR" + System.getProperty("line.separator")+ "SUNDAY" + System.getProperty("line.separator");
    ByteArrayInputStream testIn = new ByteArrayInputStream(simulatedUserInput.getBytes());
    System.setIn(testIn);
    App.main(new String[0]);
    System.setIn(System.in);
  }
}