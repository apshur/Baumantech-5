import java.time.LocalTime;
import java.util.*;

public final class Lion implements Animal {
    private final String name;
    private final String enclosure;

    Lion(String name, String enclosure) {
        this.name = name;
        this.enclosure = enclosure;
    }

    public String name() { return name; }
    public String species() { return "Лев"; }
    public String enclosure() { return enclosure; }
    public String diet() { return "Мясо (говядина/курица), 5-7 кг"; }

    public List<LocalTime> feedingTimes() {
        List<LocalTime> times = new ArrayList<>();
        times.add(LocalTime.of(10, 0));
        times.add(LocalTime.of(16, 0));
        return times;
}

    public String funFact() {
        return "Может спать по 16-20 часов в сутки. Рев слышен на расстоянии ~8 км.";
    }
}
