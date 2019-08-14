package simulator;

import simulator.utils.BaseArea;
import simulator.utils.SkiLevel;

import java.sql.Time;
import java.util.Map;
import java.util.UUID;

public class Skier {
    private Map<Time, Lift> runs;
    private UUID skierId;
    private SkiLevel skiLevel;
    private Boolean haveLunch;
    private BaseArea baseArea;
    private String currentLoc;
    private Time startTime;
    private Time previousTime;

    public Skier(UUID skierId, SkiLevel skiLevel, Boolean haveLunch, BaseArea baseArea, Time startTime) {
        this.skierId = skierId;
        this.skiLevel = skiLevel;
        this.haveLunch = haveLunch;
        this.baseArea = baseArea;
        this.currentLoc = baseArea.toString();
        this.startTime = startTime;
    }

    public Boolean getHaveLunch() {
        return haveLunch;
    }

    public Time getStartTime() {
        return startTime;
    }

    public Time getPreviousTime() {
        if (previousTime == null) {
            return startTime;
        }
        return previousTime;
    }

    public void setPreviousTime(Time previousTime) {
        this.previousTime = previousTime;
    }

    public UUID getSkierId() {
        return skierId;
    }

    public SkiLevel getSkiLevel() {
        return skiLevel;
    }

    public String getCurrentLoc() {
        return currentLoc;
    }

    public void setCurrentLoc(String currentLoc) {
        this.currentLoc = currentLoc;
    }


}