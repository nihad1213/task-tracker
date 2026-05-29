package com.tracker.tasktracker.service;

import com.tracker.tasktracker.entity.Task;
import com.tracker.tasktracker.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task add(String description) {
        return taskRepository.save(new Task(description));
    }

    public List<Task> list() {
        return taskRepository.findAll();
    }

    public String complete(Long id) {
        return taskRepository.findById(id).map(task -> {
            task.setCompleted(true);
            taskRepository.save(task);
            return "Task " + id + " marked as done.";
        }).orElse("Task not found.");
    }

    public String delete(Long id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
            return "Task " + id + " deleted.";
        }
        return "Task not found.";
    }

    public List<Task> fetchOnlyCompletedTasks() {
        return taskRepository.fetchOnlyCompleted();
    }





}
