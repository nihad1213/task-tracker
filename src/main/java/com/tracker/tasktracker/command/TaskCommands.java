package com.tracker.tasktracker.command;

import com.tracker.tasktracker.service.TaskService;
import org.springframework.shell.core.command.annotation.Command;
import org.springframework.shell.core.command.annotation.Option;
import org.springframework.stereotype.Component;

@Component
public class TaskCommands {

    private final TaskService service;

    public TaskCommands(TaskService service) {
        this.service = service;
    }

    @Command(name = "add", description = "Add a new task")
    public String add(@Option(longName = "description", shortName = 'd') String description) {
        return "Added: " + service.add(description);
    }

    @Command(name = "list", description = "List all tasks")
    public String list() {
        var tasks = service.list();
        if (tasks.isEmpty()) return "No tasks yet.";
        StringBuilder sb = new StringBuilder();
        tasks.forEach(t -> sb.append(t).append("\n"));
        return sb.toString();
    }

    @Command(name = "complete", description = "Mark a task as done")
    public String complete(@Option(longName = "id", shortName = 'i') Long id) {
        return service.complete(id);
    }

    @Command(name = "delete", description = "Delete a task")
    public String delete(@Option(longName = "id", shortName = 'i') Long id) {
        return service.delete(id);
    }

    @Command(name = "list-completed", description = "List only completed tasks")
    public String listCompleted() {
        var tasks = service.fetchOnlyCompletedTasks();
        if (tasks.isEmpty()) return "No completed tasks.";
        StringBuilder sb = new StringBuilder();
        tasks.forEach(t -> sb.append(t).append("\n"));
        return sb.toString();
    }
}
