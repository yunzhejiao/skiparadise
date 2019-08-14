package simulator.utils;

public class MetricsConstant {
    public static String RELATIONSHIPCSV = "./src/csv/relationship-";
    public static final int SECONDS = 60;
    public static double BlackcombProb = 0.2;
    public static double WhistlerProb = 0.5;
    public static int INTERVAL_BEGINNER = 30;
    public static int INTERVAL_INTERMEDIATE = 23;
    public static int INTERVAL_ADVANCED = 18;
    public static int INTERVAL_DEVIATION = 5;

    public static String WHISTLER_PATH = "./src/LiftConnectivityCSV/WhistlerConnectivity.csv";
    public static String BLACKCOMB_PATH = "./src/LiftConnectivityCSV/BlackcombConnectivity.csv";
    public static String WEATHER_NOTIFICATION_MESSAGE = "Please enter one of the three weather conditions: STORMY, POWDER, or AVERAGE.";
    public static String SEASON_NOTIFICATION_MESSAGE = "Please enter one of the following season conditions: REGULAR, EARLY, LATE, or PEAK." ;
    public static String DAY_NOTIFICATION_MESSAGE = "Please enter one of the following days of the week: MONDAY, TUESDAY, WEDNESDAY, or THURSDAY, " +
            "FRIDAY, SATURDAY, OR SUNDAY.";
    public static String QUERY_NOTIFICATION_MESSAGE = "Please enter the Cypher query:";

    //constants used for determining time.
    public static int hourOfPeakTime = 9;
    public static int minuteOfPeakTime = 31;
    public static int endOfMorningHour = 12;
    public static int beginningOfAfternoonHour = 13;
    public static int endOfDayHour = 14;
    public static int lastMinuteBeforeNextHour = 59;
}
