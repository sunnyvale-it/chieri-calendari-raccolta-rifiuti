package it.sunnyvale.chieriraccoltarifiuti.utils;

import it.sunnyvale.chieriraccoltarifiuti.ics.EventUidGenerator;
import it.sunnyvale.chieriraccoltarifiuti.model.Coordinates;
import net.fortuna.ical4j.data.CalendarOutputter;
import net.fortuna.ical4j.model.*;
import net.fortuna.ical4j.model.component.VAlarm;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.parameter.XParameter;
import net.fortuna.ical4j.model.property.*;
import net.fortuna.ical4j.util.FixedUidGenerator;
import net.fortuna.ical4j.util.RandomUidGenerator;
import net.fortuna.ical4j.util.UidGenerator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;

public class ICSUtils {

    private net.fortuna.ical4j.model.Calendar calendar = new net.fortuna.ical4j.model.Calendar();
    private TimeZoneRegistry registry = TimeZoneRegistryFactory.getInstance().createRegistry();
    private TimeZone timezone = registry.getTimeZone("Europe/Rome");
    private String zone;
    private int year;

    public ICSUtils(String zone, int year){
        this.zone = zone;
        this.year = year;
        calendar.getProperties().add(new ProdId("-//Sunnyvale S.r.l.//iCal4j 1.0//EN"));
        calendar.getProperties().add(Version.VERSION_2_0);
        calendar.getProperties().add(CalScale.GREGORIAN);
        calendar.getProperties().add(new XProperty("X-WR-CALNAME", "Raccolta rifiuti "+zone+" "+year));
        calendar.getProperties().add(new XProperty("X-WR-CALDESC", "Raccolta rifiuti "+zone+" "+year));

        RefreshInterval refreshInterval = new RefreshInterval();
        refreshInterval.setValue("PT12H");
        calendar.getProperties().add(refreshInterval);
    }

    public void addEvent(String collection, LocalDateTime ldt, Coordinates coordinates){

        net.fortuna.ical4j.model.DateTime dtStart = new DateTime (Date.from(ldt.atZone(ZoneId.of("Europe/Rome")).toInstant()));
        //dtStart.setUtc(true);

        net.fortuna.ical4j.model.DateTime dtEnd = new DateTime (Date.from(ldt.atZone(ZoneId.of("Europe/Rome")).toInstant()));
        //dtEnd.setUtc(true);

        VEvent event = new VEvent(dtStart,dtEnd, collection);

        // Generate a UID for the event..
        EventUidGenerator ug = new EventUidGenerator(collection,ldt,coordinates,year,zone);
        event.getProperties().add(ug.generateUid());

        // Add the event alarm
        VAlarm alarm = new VAlarm();

        Trigger trigger = new Trigger();
        // trigger fires at a specific time
        //trigger.setDateTime(new DateTime(Date.from(ldt.minusHours(12).atZone(ZoneId.of("Europe/Rome")).toInstant())));
        // trigger fires 12H before the start of an event
        trigger.setValue("-PT12H");
        alarm.getProperties().add(trigger);

        alarm.getProperties().add(Action.DISPLAY);

        // alarm repetition
        Repeat repeat = new Repeat();
        repeat.setValue("1");
        alarm.getProperties().add(repeat);

        // alarm descriptions
        Description description = new Description("Raccolta differenziata, prossimo conferimento: "+collection);
        alarm.getProperties().add(description);

        event.getAlarms().add(alarm);

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
