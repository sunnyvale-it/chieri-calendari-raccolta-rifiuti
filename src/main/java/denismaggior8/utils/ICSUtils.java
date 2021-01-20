package denismaggior8.utils;

import net.fortuna.ical4j.data.CalendarOutputter;
import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.TimeZone;
import net.fortuna.ical4j.model.TimeZoneRegistry;
import net.fortuna.ical4j.model.TimeZoneRegistryFactory;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.CalScale;
import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.model.property.Version;
import net.fortuna.ical4j.util.RandomUidGenerator;
import net.fortuna.ical4j.util.UidGenerator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;

public class ICSUtils {

    net.fortuna.ical4j.model.Calendar calendar = new net.fortuna.ical4j.model.Calendar();
    TimeZoneRegistry registry = TimeZoneRegistryFactory.getInstance().createRegistry();
    TimeZone timezone = registry.getTimeZone("Etc/GMT+1");

    public ICSUtils(){
        calendar.getProperties().add(new ProdId("-//Ben Fortuna//iCal4j 1.0//EN"));
        calendar.getProperties().add(Version.VERSION_2_0);
        calendar.getProperties().add(CalScale.GREGORIAN);

    }

    public void addEvent(String collection, LocalDateTime ldt){
        //Creating an event
        java.util.Calendar startCal = java.util.Calendar.getInstance(timezone);
        startCal.set(java.util.Calendar.YEAR, ldt.getYear());
        startCal.set(java.util.Calendar.MONTH, ldt.getMonthValue());
        startCal.set(java.util.Calendar.DAY_OF_MONTH, ldt.getDayOfMonth());
        startCal.set(java.util.Calendar.HOUR_OF_DAY, ldt.getHour());
        startCal.clear(ldt.getMinute());
        startCal.clear(ldt.getSecond());

        java.util.Calendar endCal = java.util.Calendar.getInstance(timezone);
        endCal.set(java.util.Calendar.YEAR, ldt.getYear());
        endCal.set(java.util.Calendar.MONTH, ldt.getMonthValue());
        endCal.set(java.util.Calendar.DAY_OF_MONTH, ldt.getDayOfMonth());
        endCal.set(java.util.Calendar.HOUR_OF_DAY, ldt.getHour());
        endCal.clear(ldt.getMinute());
        endCal.clear(ldt.getSecond());

        net.fortuna.ical4j.model.DateTime dtStart = new DateTime (startCal.getTime());
        //dtStart.setUtc(true);

        net.fortuna.ical4j.model.DateTime dtEnd = new DateTime(endCal.getTime());
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
