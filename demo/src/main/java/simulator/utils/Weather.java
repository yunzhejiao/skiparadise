package simulator.utils;

public enum Weather {
    //the amount of population increase or decrease based on the weather
    STORMY(-.50), POWDER(.25), AVERAGE(0);

    private double value;

    //setter for the population change
    Weather(double value) {
        this.value = value;
    }

    //returns the change based on the weather
    public double getValue() {
        return value;
    }
}
