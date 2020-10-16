package denismaggior8;

import org.apache.commons.io.IOUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import java.awt.geom.Rectangle2D;
import java.io.FileInputStream;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        Coordinates[] coordinatesArray = new Coordinates[]{
                // gennaio
                new Coordinates(35.49,170.53,186.32,319.44, "gennaio",1),
                // febbraio
                new Coordinates(171.78,170.53,186.32,319.44, "febbraio",2),
                // marzo
                new Coordinates(424.47,170.53,186.32,319.44, "marzo",3),
                // aprile
                new Coordinates(619.23,170.53,186.32,319.44, "aprile",4),
                // maggio
                new Coordinates(35.38,500.88,186.32,319.44, "maggio",5),
                // giugno
                new Coordinates(230.06,500.88,186.32,319.44, "giugno",6),
                // luglio
                new Coordinates(425.7,500.88,186.32,319.44, "luglio",7),
                // agosto
                new Coordinates(620.07,500.88,186.32,319.44, "agosto",8),
                // settembre
                new Coordinates(35.49,827.07,186.32,319.44, "settembre", 9),
                // ottobre
                new Coordinates(230.23,827.07,186.32,319.44, "ottobre", 10),
                // novembre
                new Coordinates(425.3,827.07,186.32,319.44, "novembre",11),
                // dicembre
                new Coordinates(619.7,827.07,186.32,319.44, "dicembre",12)
        };

        for(Coordinates coordinates : coordinatesArray){

            try {
                FileInputStream inputStream = new FileInputStream(args[0]);
                byte[] pdfBytes = IOUtils.toByteArray(inputStream);
                PDDocument pddDoc = PDDocument.load(pdfBytes);
                PDFTextStripperByArea stripper = new PDFTextStripperByArea();
                stripper.setSortByPosition( true );
                Rectangle2D.Double rect = new Rectangle2D.Double();
                rect.setRect(coordinates.getLeft(),coordinates.getTop(),coordinates.getWidth(),coordinates.getHeight());
                stripper.addRegion(coordinates.getLabel(),rect);
                stripper.extractRegions( pddDoc.getPage(1) );
                String string = stripper.getTextForRegion( coordinates.getLabel() );
                System.out.println(string);
                pddDoc.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}

