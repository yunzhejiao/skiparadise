package simulator.utils;

public enum SkiLevel {
    BEGINNER(3), INTERMEDIATE(15), ADVANCED(25);

    private int value;

    SkiLevel(int value) {
        this.value = value;
    }

    //returns the value of the runs for one skier
    public int getValue() {
        return value;
    }

}
