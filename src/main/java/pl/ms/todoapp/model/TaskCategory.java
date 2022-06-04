package pl.ms.todoapp.model;

enum TaskCategory {
    HOUSEWORK("obowiązek domowy"),
    WORK("praca"),
    STUDIES("nauka"),
    MISCELLANEOUS("inna");

    private final String description;

    TaskCategory(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description;
    }
}
