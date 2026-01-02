import java.time.LocalDate;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        // 1) Создаём сервисы
        FeedingService feedingService = new SimpleFeedingService();
        MedicalService medicalService = new SimpleMedicalService();

        // 2) Создаём отчётный сервис (он зависит от интерфейсов)
        ReportService reportService = new ReportService(feedingService, medicalService);

        // 3) Создаём сотрудников
        Staff feeder = Staff.feeder("Иван");
        Staff vet = Staff.vet("Смирнов");

        // 4) Создаём животных (и вольеры)
        List<Animal> animals = new ArrayList<>();
        animals.add(new Lion("Лев", "Большие кошки #1"));
        animals.add(new Parrot("Кеша", "Птичий дом A"));
        animals.add(new Snake("Нагайна", "Терариум 3"));

        // 5) Регламент: осмотр при приёме (для всех)
        for (Animal a : animals) {
            medicalService.intakeExam(a, vet);
        }

        // 6) Кормление
        feedingService.feed(animals.get(0), feeder, "6 кг говядины");
        feedingService.feed(animals.get(1), feeder);

        // 7) Лечение (пример)
        medicalService.treat(animals.get(2), vet, "Обычная дегельминтизация");

        // 8) Отчёт
        System.out.println();
        String report = reportService.buildDailyReport(LocalDate.now(), animals);
        System.out.println(report);
    }
}