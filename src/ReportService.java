import java.time.LocalDate;
import java.util.*;

public final class ReportService {
    private final FeedingService feedingService;
    private final MedicalService medicalService;

    // Важно: ReportService зависит от интерфейсов, а не от конкретных классов (DIP)
    ReportService(FeedingService feedingService, MedicalService medicalService) {
        this.feedingService = feedingService;
        this.medicalService = medicalService;
    }

    String buildDailyReport(LocalDate date, List<Animal> animals) {
        // 1) Считаем статистику по видам и по вольерам
        Map<String, Integer> bySpecies = new TreeMap<>();
        Map<String, Integer> byEnclosure = new TreeMap<>();

        for (Animal a : animals) {
            // по видам
            int countSpecies = bySpecies.getOrDefault(a.species(), 0);
            bySpecies.put(a.species(), countSpecies + 1);

            // по вольерам
            int countEnclosure = byEnclosure.getOrDefault(a.enclosure(), 0);
            byEnclosure.put(a.enclosure(), countEnclosure + 1);
        }

        // 2) Расписание кормления
        StringBuilder schedule = new StringBuilder();
        for (Animal a : animals) {
            schedule.append("  - ")
                    .append(a.name()).append(" (").append(a.species()).append(")")
                    .append(" times=").append(a.feedingTimes())
                    .append("\n");
        }

        // 3) Последние действия
        StringBuilder lastActions = new StringBuilder();
        for (Animal a : animals) {
            lastActions.append("  - ").append(a.name()).append(" (").append(a.species()).append(")\n");
            lastActions.append("        вольер        : ").append(a.enclosure()).append("\n");
            lastActions.append("        рацион        : ").append(a.diet()).append("\n");
            lastActions.append("  последнее кормление : ").append(feedingService.lastFeeding(a)).append("\n");
            lastActions.append("последнее обследование: ").append(medicalService.lastRecord(a)).append("\n");
            lastActions.append("      забавный факт   : ").append(a.funFact()).append("\n");
        }

        // 4) Склеиваем всё в один красивый текст
        return """
                ===== ЕЖЕДНЕВНЫЙ ОТЧЕТ ЗООПАРКА (%s) =====
                Общее количество животных: %d
                
                По видам:
                %s
                
                По вольерам:
                %s
                
                Расписание кормления:
                %s
                Последние действия:
                %s
                    =================================
                    """.formatted(
                date,
                animals.size(),
                mapToText(bySpecies),
                mapToText(byEnclosure),
                schedule.length() == 0 ? "  - none\n" : schedule.toString(),
                lastActions.length() == 0 ? "  - none\n" : lastActions.toString()
        );
    }

    private String mapToText(Map<String, Integer> map) {
        if (map.isEmpty()) {
            return "  - none";
        }

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Integer> e : map.entrySet()) {
            sb.append("  - ").append(e.getKey()).append(": ").append(e.getValue()).append("\n");
        }

        return sb.toString().trim();
    }
}