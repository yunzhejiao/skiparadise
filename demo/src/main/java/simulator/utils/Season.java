package simulator.utils;

public enum Season {
    //the percentage the customer increase or decrease compared to average numbers
    REGULAR(0), EARLY(-.20), LATE(-.20), PEAK(.10);

    private double value;

    //sets the change to the season.
    Season(double value) {
        this.value = value;
    }

    //returns the value of the change.
    public double getValue() {
        return value;
    }
}