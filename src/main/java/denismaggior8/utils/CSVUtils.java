package denismaggior8.utils;

import denismaggior8.model.Coordinates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CSVUtils {
    StringBuffer sb = new StringBuffer();

    public CSVUtils(String header) {
        sb
                .append(header)
                .append("\n");
    }

    public void addLine(String collection, LocalDateTime ldt, Coordinates coordinates) {
        sb
                // Type of collection (Subject)
                .append(collection)
                .append(",")
                // Event Start Date
                .append(ldt.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .append(",")
                // Event Start Time (hour)
                .append(ldt.format(DateTimeFormatter.ofPattern("hh:mm")))
                .append(" ")
                // AM or PM
                .append(ldt.format(DateTimeFormatter.ofPattern("a")))
                .append(",")
                // Event End Date
                .append(ldt.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .append(",")
                // Event End Time (hour)
                .append(ldt
                        .plusMinutes(10)
                        .format(DateTimeFormatter.ofPattern("hh:mm")))
                .append(" ")
                // AM or PM
                .append(ldt.format(DateTimeFormatter.ofPattern("a")))
                .append(",")
                // All day event?
                .append((""+coordinates.isAllDayEvent()).toUpperCase())
                .append(",")
                // Description
                .append(collection)
                .append("\n");
    }

    public void toFile(String filePath){
        try {
            Files.write(Paths.get(filePath), sb.toString().getBytes(), StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
