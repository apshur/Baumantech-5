interface MedicalService {
    void intakeExam(Animal animal, Staff vet);          // осмотр при приёме
    void treat(Animal animal, Staff vet, String note);  // лечение/процедура
    String lastRecord(Animal animal);                   // последняя запись
}