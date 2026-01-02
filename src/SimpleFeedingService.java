import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public final class SimpleFeedingService implements FeedingService {

    // Здесь будем хранить "последнее кормление" по каждому зверю
    // key: "вид:имя"  value: текст лога
    private final Map<String, String> lastFeed = new HashMap<>();

    public void feed(Animal animal, Staff feeder) {
        feed(animal, feeder, "стандартная порция");
    }

    public void feed(Animal animal, Staff feeder, String portion) {
        // 1) Простые проверки
        if (animal == null) {
            throw new IllegalArgumentException("поле 'животное'  не заполнено");
        }
        if (feeder == null) {
            throw new IllegalArgumentException("поле 'FEEDER'  не заполнено");
        }

        // 2) Проверяем роль
        if (!"FEEDER".equals(feeder.getRole())) {
            throw new IllegalArgumentException("Только FEEDER может кормить животных");
        }

        // 3) Определяем "по расписанию" или нет
        LocalDateTime now = LocalDateTime.now();
        boolean okBySchedule = isCloseToSchedule(animal, now.toLocalTime());

        String scheduleText;
        if (okBySchedule) {
            scheduleText = "ОК (по расписанию)";
        } else {
            scheduleText = "ПРЕДУПРЕЖДЕНИЕ (вне графика)";
        }

        // 4) Формируем сообщение
        String message = "[FEED] "
                + animal.name() + " (" + animal.species() + ")"
                + " | вольер=" + animal.enclosure()
                + " | рацион=" + animal.diet()
                + " | размер порции:=" + portion
                + " | " + scheduleText
                + " | кем: " + feeder.getName()
                + " время: " + now;

        // 5) Сохраняем как "последнее кормление" и печатаем
        lastFeed.put(makeKey(animal), message);
        System.out.println(message);
    }

    public String lastFeeding(Animal animal) {
        String key = makeKey(animal);
        if (lastFeed.containsKey(key)) {
            return lastFeed.get(key);
        }
        return "никаких записей о кормлении";
    }

    private String makeKey(Animal animal) {
        return animal.species() + ":" + animal.name();
    }

    // Проверка: текущее время близко к любому времени кормления (+-30 минут)
    private boolean isCloseToSchedule(Animal animal, LocalTime now) {
        int nowMinutes = now.getHour() * 60 + now.getMinute();

        for (LocalTime t : animal.feedingTimes()) {
            int tMinutes = t.getHour() * 60 + t.getMinute();
            int diff = Math.abs(nowMinutes - tMinutes);

            if (diff <= 30) {
                return true;
            }
        }

        return false;
    }
}
