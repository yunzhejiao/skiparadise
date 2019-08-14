package simulator;

import org.junit.Test;

public class CSVWriterTest {
  CSVWriter csvWriter;

  @Test(expected = NullPointerException.class)
  public void generateCSVTestFail() {
    csvWriter.generateCSV();
  }
}