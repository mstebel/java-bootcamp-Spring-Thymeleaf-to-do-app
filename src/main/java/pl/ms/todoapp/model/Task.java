package pl.ms.todoapp.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public
class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate deadline;
    @Enumerated(EnumType.STRING)
    private TaskCategory taskCategory;
    private Boolean isComplete;


    public Task(String name, String description, LocalDate deadline, TaskCategory taskCategory, Boolean isComplete) {
        this.name = name;
        this.description = description;
        this.deadline = deadline;
        this.taskCategory = taskCategory;
        this.isComplete = isComplete;
    }

    public Task(Long id, String name, String description, LocalDate deadline, TaskCategory taskCategory, Boolean isComplete) {
        this(name, description, deadline, taskCategory, isComplete);
        this.id = id;
    }

    public Task() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public TaskCategory getTaskCategory() {
        return taskCategory;
    }

    public void setTaskCategory(TaskCategory taskCategory) {
        this.taskCategory = taskCategory;
    }

    public Boolean getComplete() {
        return isComplete;
    }

    public void setComplete(Boolean complete) {
        isComplete = complete;
    }

    @Override
    public String toString() {
        String status = isComplete ? "wykonane" : "niewykonane";
        return String.format("Zadanie: %s, szczegóły: %s, termin wykonania: %s, kategoria: %s, status: %s", name,
                description, deadline.toString(), taskCategory.getDescription(), status);

    }
}
