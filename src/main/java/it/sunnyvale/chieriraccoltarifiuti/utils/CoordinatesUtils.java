package it.sunnyvale.chieriraccoltarifiuti.utils;

import it.sunnyvale.chieriraccoltarifiuti.model.Coordinates;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;

public class CoordinatesUtils {

    public static Coordinates[] readCoordinatesFromCSV(String fileName) {
        Coordinates[] coordinatesArray = new Coordinates[12];
         Path pathToFile = Paths.get(fileName);
         try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
             String line = br.readLine();
             int i = 0;
             while (line != null) {
                 if(line.startsWith("#")){
                     line = br.readLine();
                     continue;
                 }
                 String[] attributes = line.split(",");
                 Coordinates coordinates = createCoordinates(attributes);
                 coordinatesArray[i]=coordinates;
                 i++;
                 line = br.readLine();
             }
         } catch (IOException ioe) {
             ioe.printStackTrace();
         }
         return coordinatesArray;
    }

    public static Coordinates createCoordinates(String[] line) {
        double left = Double.parseDouble(line[0]);
        double top = Double.parseDouble(line[1]);
        double width = Double.parseDouble(line[2]);
        double height  = Double.parseDouble(line[3]);
        String label = line[4];
        int id = Integer.parseInt(line[5]);
        String regexp =  line[6];
        LocalTime collectionTime = LocalTime.of( Integer.parseInt(line[7].split(":")[0]),Integer.parseInt(line[7].split(":")[1]));
        boolean allDayEvent = Boolean.parseBoolean(line[8]);
        int daysOffset = Integer.parseInt(line[9]);
        int hoursOffset = Integer.parseInt(line[10]);
        int minsOffset = Integer.parseInt(line[11]);
        return new Coordinates(left, top, width, height, label, id, regexp, collectionTime, allDayEvent, daysOffset, hoursOffset, minsOffset);
    }
}
