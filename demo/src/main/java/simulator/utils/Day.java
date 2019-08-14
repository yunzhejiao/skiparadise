package simulator.utils;

public enum Day {
    //the values are the amount of people who would appear on an average day.
    MONDAY(10000), TUESDAY(8000), WEDNESDAY(10000), THURSDAY(12000), FRIDAY(14000), SATURDAY(40000), SUNDAY(30000);

    private int value;

    Day(int value) {
        this.value = value;
    }

    //returns the value of the amount of people
    public int getValue() {
        return value;
    }
}