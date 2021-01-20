package denismaggior8.model;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalTime;

@Data
@AllArgsConstructor
public class Coordinates {
    double left;
    double top;
    double width;
    double height;
    String label;
    int id;
    String regexp;
    LocalTime collectionTime;
    boolean allDayEvent;
    int daysOffest;
    int hoursOffset;
    int minsOffset;
}
