package simulator;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Time;
import java.util.*;

public class CSVWriter {

    private List<Skier> skiers;
    private String csvfilename;

    public CSVWriter(List<Skier> skiers, String filename) {
        this.skiers = skiers;
        this.csvfilename = filename;
        generateCSV();
    }


    public void generateCSV() {
        FileWriter fw = null;
        try {
            fw = new FileWriter(csvfilename);
            for (Skier skier : skiers) {//for each skier

                SkiService skiService = new SkiService();
                List<Map.Entry<Time, String>>  runs = skiService.generateRuns(skier);
                UUID skierId = skier.getSkierId();

                for (Map.Entry<Time, String> entry : runs) {
                    fw.write(new DataEntry(skierId, entry.getValue(),entry.getKey()).toString());
                }
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e + "IOExp");
        }
    }
}