package denismaggior8;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Coordinates {
    double left;
    double top;
    double width;
    double height;
    String label;
    int id;
}
