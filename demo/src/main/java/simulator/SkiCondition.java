package simulator;

import simulator.utils.Day;
import simulator.utils.Season;
import simulator.utils.Weather;

public class SkiCondition {
    Weather weather;
    Season season;
    Day day;

    public SkiCondition(Weather weather, Season season, Day day) {
        this.weather = weather;
        this.season = season;
        this.day = day;
    }

    public int getSkierNumber() {
        int averageSkierNumber = day.getValue();
        double weatherChange = weather.getValue();
        double seasonChange = season.getValue();
        Double totalSkigoers = averageSkierNumber *(1 + weatherChange + seasonChange);
        return totalSkigoers.intValue();
    }
}