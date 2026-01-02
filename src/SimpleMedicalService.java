import java.time.LocalDateTime;
import java.util.*;

public final class SimpleMedicalService implements MedicalService {

    // Последняя мед. запись по животному
    private final Map<String, String> lastMedical = new HashMap<>();

    public void intakeExam(Animal animal, Staff vet) {
        if (animal == null) {
            throw new IllegalArgumentException("поле 'животное'  не заполнено");
        }
        if (vet == null) {
            throw new IllegalArgumentException("поле 'vet'  не заполнено");
        }

        if (!"VET".equals(vet.getRole())) {
            throw new IllegalArgumentException("Только ветеринар может провести медицинское обследование");
        }

        LocalDateTime now = LocalDateTime.now();

        String message = "[MED] Обследование: "
                + animal.name() + " (" + animal.species() + ")"
                + " | вольер=" + animal.enclosure()
                + " | результат=OK"
                + " | кем: " + vet.getName()
                + " когда: " + now;

        lastMedical.put(makeKey(animal), message);
        System.out.println(message);
    }

    public void treat(Animal animal, Staff vet, String note) {
        if (animal == null) {
            throw new IllegalArgumentException("поле 'животное'  не заполнено");
        }
        if (vet == null) {
            throw new IllegalArgumentException("поле 'vet'  не заполнено");
        }

        if (!"VET".equals(vet.getRole())) {
            throw new IllegalArgumentException("Только ветеринар может лечить");
        }

        LocalDateTime now = LocalDateTime.now();

        String message = "[MED] Лечит: "
                + animal.name() + " (" + animal.species() + ")"
                + " | запись='" + note + "'"
                + " | кем: " + vet.getName()
                + " когда: " + now;

        lastMedical.put(makeKey(animal), message);
        System.out.println(message);
    }

    public String lastRecord(Animal animal) {
        String key = makeKey(animal);
        if (lastMedical.containsKey(key)) {
            return lastMedical.get(key);
        }
        return "никаких медицинских записей";
    }

    private String makeKey(Animal animal) {
        return animal.species() + ":" + animal.name();
    }
}