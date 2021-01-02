package denismaggior8;

import org.apache.commons.io.IOUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import java.awt.geom.Rectangle2D;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        String inputPDFPath = args[0];
        int year =  Integer.parseInt(args[1]);

        Coordinates[] coordinatesArray = new Coordinates[]{
                // gennaio
                new Coordinates(37.87, 197.2, 183.21, 297.97, "gennaio", 1),
                // febbraio
                new Coordinates(231.47, 196.63, 183.21, 297.97, "febbraio", 2),
                // marzo
                new Coordinates(426.87, 196.73, 183.21, 297.97, "marzo", 3),
                // aprile
                new Coordinates(620.96, 196.12, 183.21, 297.97, "aprile", 4),
                // maggio
                new Coordinates(36.93, 536.11, 183.21, 297.97, "maggio", 5),
                // giugno
                new Coordinates(232.91, 535.51, 183.21, 297.97, "giugno", 6),
                // luglio
                new Coordinates(424.99, 536.16, 183.21, 297.97, "luglio", 7),
                // agosto
                new Coordinates(622.19, 535.99, 183.21, 297.97, "agosto", 8),
                // settembre
                new Coordinates(37.36, 876.71, 183.21, 297.97, "settembre", 9),
                // ottobre
                new Coordinates(232.17, 875.69, 183.21, 297.97, "ottobre", 10),
                // novembre
                new Coordinates(426.97, 875.39, 183.21, 297.97, "novembre", 11),
                // dicembre
                new Coordinates(621.69, 874.84, 183.21, 297.97, "dicembre", 12)
        };
        System.out.println("Subject,Start Date,Start Time,End Date,End Time,All day event,Description,Location");
        for (Coordinates coordinates : coordinatesArray) {

            try {
                FileInputStream inputStream = new FileInputStream(inputPDFPath);
                byte[] pdfBytes = IOUtils.toByteArray(inputStream);
                PDDocument pddDoc = PDDocument.load(pdfBytes);
                PDFTextStripperByArea stripper = new PDFTextStripperByArea();
                stripper.setSortByPosition(true);
                Rectangle2D.Double rect = new Rectangle2D.Double();
                rect.setRect(coordinates.getLeft(), coordinates.getTop(), coordinates.getWidth(), coordinates.getHeight());
                stripper.addRegion(coordinates.getLabel(), rect);
                stripper.extractRegions(pddDoc.getPage(1));
                String string = stripper.getTextForRegion(coordinates.getLabel());
                for (String dateString : string.split("\n")) {
                    //System.out.println(dateString);
                    // Create a Pattern object
                    Pattern r = Pattern.compile("(^\\d*) (\\S*) (.*$)");

                    // Now create matcher object.
                    Matcher m = r.matcher(dateString);


                    if (m.find()) {
                        int day = Integer.parseInt(m.group(1));

                        LocalDateTime ldt = LocalDateTime.from(LocalDate.of(year, coordinates.id, day).atStartOfDay())
                                .minusDays(1)
                                .withHour(19)
                                .minusMinutes(0)
                                .withSecond(0);

                        String collection = m.group(3);


                        StringBuilder sb = new StringBuilder()
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
                                .append("FALSE")
                                .append(",")
                                // Description
                                .append(collection)
                                ;

                        System.out.println(sb.toString());

                    }
                }
                pddDoc.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}

