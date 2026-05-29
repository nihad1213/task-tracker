package com.tracker.tasktracker.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private boolean completed;

    public Task() {}

    public Task(String description) {
        this.description = description;
        this.completed = false;
    }

    public Long getId() {return id;}

    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description}

    public boolean isCompleted() {return completed;}
    public void setCompleted(boolean completed) {this.completed = completed}

    @Override
    public String toString() {
        return "[" + id + "] " + description + " (" + (completed ? "done" : "pending") + ")";
    }

}
