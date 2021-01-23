package it.sunnyvale.chieriraccoltarifiuti.ics;

import it.sunnyvale.chieriraccoltarifiuti.model.Coordinates;
import lombok.AllArgsConstructor;
import net.fortuna.ical4j.model.property.Uid;
import net.fortuna.ical4j.util.UidGenerator;

import java.time.LocalDateTime;
import java.time.ZoneId;

@AllArgsConstructor
public class EventUidGenerator implements UidGenerator {

    private String collection;
    private LocalDateTime ldt;
    private Coordinates coordinates;

    @Override
    public Uid generateUid() {
        return new Uid(collection+"@"+ldt.atZone(ZoneId.of("Europe/Rome")).toEpochSecond()+"@sunnyvale-it/chieri-calendari-raccolta-rifiuti");
    }
}
