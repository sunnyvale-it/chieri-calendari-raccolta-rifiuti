package it.sunnyvale.chieriraccoltarifiuti;

import it.sunnyvale.chieriraccoltarifiuti.model.Coordinates;
import it.sunnyvale.chieriraccoltarifiuti.utils.CSVUtils;
import it.sunnyvale.chieriraccoltarifiuti.utils.CoordinatesUtils;
import it.sunnyvale.chieriraccoltarifiuti.utils.ICSUtils;
import org.apache.commons.io.IOUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import java.awt.geom.Rectangle2D;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {


        String zone = args[0];
        String inputPDFFilePath = args[1];
        String inputCoordinatesFilePath = args[2];
        int year = Integer.parseInt(args[3]);
        String outputCSVPath = args[4];
        String outputICSPath = args[5];

        int hoursOffset = 0;

        int daysOffset = 0;

        if(args.length == 7 ){
            hoursOffset = Integer.parseInt(args[6]);
        }

        if(args.length == 8 ){
            daysOffset = Integer.parseInt(args[7]);
        }

        Coordinates[] coordinatesArray = CoordinatesUtils.readCoordinatesFromCSV(inputCoordinatesFilePath);

        CSVUtils csvUtils = new CSVUtils("Subject,Start Date,Start Time,End Date,End Time,All day event,Description,Location");
        ICSUtils icsUtils = new ICSUtils(zone,year);

        try {

            for (Coordinates coordinates : coordinatesArray) {


                FileInputStream inputStream = new FileInputStream(inputPDFFilePath);
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
                    Pattern r = Pattern.compile(coordinates.getRegexp());

                    // Now create matcher object.
                    Matcher m = r.matcher(dateString);


                    if (m.find()) {
                        int day = Integer.parseInt(m.group(1));

                        LocalDateTime ldt = LocalDateTime.from(LocalDate.of(year, coordinates.getId(), day).atStartOfDay())
                                .withHour(coordinates.getCollectionTime().getHour())
                                .withMinute(coordinates.getCollectionTime().getMinute())
                                .plusHours(hoursOffset)
                                .plusDays(daysOffset);

                        String collection = m.group(3);

                        csvUtils.addLine(collection, ldt, coordinates);
                        icsUtils.addEvent(collection, ldt, coordinates);

                    }
                }
                pddDoc.close();

            }
            csvUtils.toFile(outputCSVPath + System.getProperty("file.separator") + zone + "_" + year + ".csv");
            icsUtils.toFile(outputICSPath + System.getProperty("file.separator") + zone + "_" + year + ".ics");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

