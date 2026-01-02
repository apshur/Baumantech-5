import java.time.LocalTime;
import java.util.*;

public final class Parrot implements Animal {
    private final String name;
    private final String enclosure;

    Parrot(String name, String enclosure) {
        this.name = name;
        this.enclosure = enclosure;
    }

    public String name() { return name; }
    public String species() { return "Попугай"; }
    public String enclosure() { return enclosure; }
    public String diet() { return "Семена + ломтики фруктов"; }

    public List<LocalTime> feedingTimes() {
        List<LocalTime> times = new ArrayList<>();
        times.add(LocalTime.of(9, 0));
        times.add(LocalTime.of(14, 0));
        return times;
    }

    public String funFact() {
        return "Может имитировать звуки и запоминать короткие фразы.";
    }
}