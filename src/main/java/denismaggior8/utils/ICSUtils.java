package denismaggior8.utils;

import denismaggior8.model.Coordinates;
import net.fortuna.ical4j.data.CalendarOutputter;
import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.TimeZone;
import net.fortuna.ical4j.model.TimeZoneRegistry;
import net.fortuna.ical4j.model.TimeZoneRegistryFactory;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.CalScale;
import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.model.property.Version;
import net.fortuna.ical4j.util.FixedUidGenerator;
import net.fortuna.ical4j.util.RandomUidGenerator;
import net.fortuna.ical4j.util.UidGenerator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class ICSUtils {

    net.fortuna.ical4j.model.Calendar calendar = new net.fortuna.ical4j.model.Calendar();
    TimeZoneRegistry registry = TimeZoneRegistryFactory.getInstance().createRegistry();
    TimeZone timezone = registry.getTimeZone("Europe/Rome");

    public ICSUtils(){
        calendar.getProperties().add(new ProdId("-//Ben Fortuna//iCal4j 1.0//EN"));
        calendar.getProperties().add(Version.VERSION_2_0);
        calendar.getProperties().add(CalScale.GREGORIAN);

    }

    public void addEvent(String collection, LocalDateTime ldt, Coordinates coordinates){

        net.fortuna.ical4j.model.DateTime dtStart = new DateTime (Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant()));
        //dtStart.setUtc(true);

        net.fortuna.ical4j.model.DateTime dtEnd = new DateTime (Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant()));
        //dtEnd.setUtc(true);

        VEvent event = new VEvent(dtStart,dtEnd, collection);

        // Generate a UID for the event..
        UidGenerator ug = new RandomUidGenerator();
        event.getProperties().add(ug.generateUid());

        calendar.getComponents().add(event);
    }

    public void toFile(String filePath){
        try {
            FileOutputStream fout = new FileOutputStream(filePath);
            CalendarOutputter outputter = new CalendarOutputter();
            outputter.output(calendar, fout);
        }catch(FileNotFoundException ex){
            ex.printStackTrace();
        }catch(IOException ex){
            ex.printStackTrace();
        }

    }
}
