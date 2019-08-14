package simulator;

import java.sql.Time;
import java.util.UUID;

public class DataEntry {
  private UUID skierId;
  private String liftName;
  private Time time;

  public DataEntry(UUID skierId, String liftName, Time time) {
    this.skierId = skierId;
    this.liftName = liftName;
    this.time = time;
  }

  @Override
  public String toString() {
    return skierId + "," + liftName + "," + time + "\n";
  }
}
