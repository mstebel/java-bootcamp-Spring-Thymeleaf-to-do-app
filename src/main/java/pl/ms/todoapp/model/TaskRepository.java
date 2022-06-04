package pl.ms.todoapp.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("SELECT t FROM Task t WHERE t.isComplete = FALSE")
    List<Task> getIncompleteTasks();

    @Query("SELECT t FROM Task t WHERE t.isComplete = TRUE")
    List<Task> getCompleteTasks();
}
