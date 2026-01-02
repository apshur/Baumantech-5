import java.time.LocalTime;
import java.util.*;

public final class Snake implements Animal {
    private final String name;
    private final String enclosure;

    Snake(String name, String enclosure) {
        this.name = name;
        this.enclosure = enclosure;
    }

    public String name() { return name; }
    public String species() { return "Змея"; }
    public String enclosure() { return enclosure; }
    public String diet() { return "Мыши (1-2 шт.), в зависимости от размера"; }

    public List<LocalTime> feedingTimes() {
        List<LocalTime> times = new ArrayList<>();
        times.add(LocalTime.of(12, 0));
        return times;
    }

    public String funFact() {
        return "Обоняет языком и ощущает тепло в окружающей среде.";
    }
}