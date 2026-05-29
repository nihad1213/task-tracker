package com.tracker.tasktracker.repository;

import com.tracker.tasktracker.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("SELECT * FROM task WHERE completed = 1")
    List<Task> fetchOnlyCompleted();
}
