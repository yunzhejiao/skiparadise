package simulator;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import static simulator.utils.MetricsConstant.*;

public class App {

	public static void main(String[] args) {

    	simulator.utils.Weather weather = null;
    	simulator.utils.Season season = null;
    	simulator.utils.Day day = null;

    	Scanner in = new Scanner(System.in);

    	System.out.println(WEATHER_NOTIFICATION_MESSAGE);
    	while (!(weather instanceof simulator.utils.Weather)) {
			try {
				weather = simulator.utils.Weather.valueOf(in.next());

			} catch(IllegalArgumentException exception) {
				System.out.println(WEATHER_NOTIFICATION_MESSAGE);
			}
		}

    	System.out.println(SEASON_NOTIFICATION_MESSAGE);
		while (!(season instanceof simulator.utils.Season)) {
			try {
				season = simulator.utils.Season.valueOf(in.next());
			} catch (IllegalArgumentException exception) {
				System.out.println(SEASON_NOTIFICATION_MESSAGE);
			}
		}

    	System.out.println(DAY_NOTIFICATION_MESSAGE);
		while (!(day instanceof simulator.utils.Day)) {
			try {
				day = simulator.utils.Day.valueOf(in.next());
			} catch (IllegalArgumentException exception) {
				System.out.println(DAY_NOTIFICATION_MESSAGE);
			}
		}

		// if file not exists, generate the csv file.
		String csvFilename = RELATIONSHIPCSV + weather.toString() + "-" + season.toString() + "-" + day.toString();
		if (!Files.exists(Paths.get(csvFilename))) {
			System.out.println(csvFilename + " DOES NOT exist. TO DO: file generation");
			//generate relationship csv.
			new SkierDataSimulator().generateData(season, day, weather, RELATIONSHIPCSV);
		} else {
			System.out.println(csvFilename + " already exist. Skip file generation");
		}
		
		//		// Add neo4j connection		
 //		try (Neo4jConnector driver = new Neo4jConnector("bolt://localhost:7687", "neo4j",		
 //			"tiger")) {		
 //			driver.loadCSV(csvFilename);		
 //		} catch (Exception e) {		
 //			e.printStackTrace();		
 //			System.out.println("=====NEO4J OPERATION EXCEPTION====="+e);		
 //			System.out.println(QUERY_NOTIFICATION_MESSAGE);		
 //		}
    }
}
