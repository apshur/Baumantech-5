interface FeedingService {
    void feed(Animal animal, Staff feeder);
    void feed(Animal animal, Staff feeder, String portion);
    String lastFeeding(Animal animal);
}