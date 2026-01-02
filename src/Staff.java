public final class Staff {
    private final String name;
    private final String role;

    Staff(String name, String role) {
        this.name = name;
        this.role = role;
    }

    public String getName() { return name; }
    public String getRole() { return role; }

    public static Staff feeder(String name) {
        return new Staff(name, "FEEDER");
    }

    public static Staff vet(String name) {
        return new Staff(name, "VET");
    }
}