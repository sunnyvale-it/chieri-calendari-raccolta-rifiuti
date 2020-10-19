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

        Coordinates[] coordinatesArray = new Coordinates[]{
                // gennaio
                new Coordinates(35.49, 170.53, 186.32, 319.44, "gennaio", 1),
                // febbraio
                new Coordinates(171.78, 170.53, 186.32, 319.44, "febbraio", 2),
                // marzo
                new Coordinates(424.47, 170.53, 186.32, 319.44, "marzo", 3),
                // aprile
                new Coordinates(619.23, 170.53, 186.32, 319.44, "aprile", 4),
                // maggio
                new Coordinates(35.38, 500.88, 186.32, 319.44, "maggio", 5),
                // giugno
                new Coordinates(230.06, 500.88, 186.32, 319.44, "giugno", 6),
                // luglio
                new Coordinates(425.7, 500.88, 186.32, 319.44, "luglio", 7),
                // agosto
                new Coordinates(620.07, 500.88, 186.32, 319.44, "agosto", 8),
                // settembre
                new Coordinates(35.49, 827.07, 186.32, 319.44, "settembre", 9),
                // ottobre
                new Coordinates(230.23, 827.07, 186.32, 319.44, "ottobre", 10),
                // novembre
                new Coordinates(425.3, 827.07, 186.32, 319.44, "novembre", 11),
                // dicembre
                new Coordinates(619.7, 827.07, 186.32, 319.44, "dicembre", 12)
        };

        for (Coordinates coordinates : coordinatesArray) {

            try {
                FileInputStream inputStream = new FileInputStream(args[0]);
                byte[] pdfBytes = IOUtils.toByteArray(inputStream);
                PDDocument pddDoc = PDDocument.load(pdfBytes);
                PDFTextStripperByArea stripper = new PDFTextStripperByArea();
                stripper.setSortByPosition(true);
                Rectangle2D.Double rect = new Rectangle2D.Double();
                rect.setRect(coordinates.getLeft(), coordinates.getTop(), coordinates.getWidth(), coordinates.getHeight());
                stripper.addRegion(coordinates.getLabel(), rect);
                stripper.extractRegions(pddDoc.getPage(1));
                String string = stripper.getTextForRegion(coordinates.getLabel());
                //System.out.println(string);
                Stream<String> linesStream = Arrays.stream(string.split("\n"));
                List<String> collections = linesStream.filter(s -> s.matches("(^\\d*) (\\S*) (.*$)")).collect(Collectors.toList());

                for (String dateString : collections) {
                    //System.out.println(dateString);
                    // Create a Pattern object
                    Pattern r = Pattern.compile("(^\\d*) (\\S*) (.*$)");

                    // Now create matcher object.
                    Matcher m = r.matcher(dateString);


                    if (m.find()) {
                        int day = Integer.parseInt(m.group(1));

                        LocalDateTime ldt = LocalDateTime.from(LocalDate.of(2020, coordinates.id, day).atStartOfDay())
                                .minusDays(1)
                                .withHour(21)
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

