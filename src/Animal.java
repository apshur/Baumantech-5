import java.time.LocalTime;
import java.util.*;

interface Animal {
    String name();                   // кличка
    String species();                // вид (Lion/Parrot/Snake)
    String enclosure();              // вольер
    String diet();                   // рацион
    List<LocalTime> feedingTimes();  // времена кормления
    String funFact();                // интересный факт (для отчёта)
}
