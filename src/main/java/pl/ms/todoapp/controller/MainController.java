package pl.ms.todoapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.ms.todoapp.model.Task;
import pl.ms.todoapp.model.TaskRepository;

import java.util.Optional;

@Controller
class MainController {
    private TaskRepository taskRepository;

    public MainController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("tasks", taskRepository.getIncompleteTasks());
        return "home";
    }

    @PostMapping("/{id}")
    public String moveTaskToRecords(@PathVariable Long id) {
        Optional<Task> taskById = taskRepository.findById(id);
        if (taskById.isPresent()) {
            Task task = taskById.get();
            task.setComplete(true);
            taskRepository.save(task);
        }
        return "redirect:/";
    }

    @GetMapping("/records")
    public String printRecords(Model model) {
        model.addAttribute("tasks", taskRepository.getCompleteTasks());
        return "records";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("task", new Task());
        model.addAttribute("mode", "add");
        return "addOrEditForm";
    }

    @PostMapping("/add")
    public String addTask(Task task) {
        task.setComplete(false);
        taskRepository.save(task);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String editForm(Model model, @RequestParam Long id) {
        Optional<Task> byId = taskRepository.findById(id);
        if (byId.isPresent()) {
            model.addAttribute("task", byId.get());
            model.addAttribute("mode", "edit");
            return "addOrEditForm";
        }
        return "redirect:/";
    }

    @PostMapping("/edit")
    public String editTask(Task task) {
        Optional<Task> byId = taskRepository.findById(task.getId());
        if (byId.isPresent()) {
            Task taskDb = byId.get();
            taskDb.setName(task.getName());
            taskDb.setDescription(task.getDescription());
            taskDb.setDeadline(task.getDeadline());
            taskDb.setTaskCategory(task.getTaskCategory());
            taskRepository.save(taskDb);
        }
        return "redirect:/";
    }
}
