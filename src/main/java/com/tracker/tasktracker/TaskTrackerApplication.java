package com.tracker.tasktracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.shell.core.command.annotation.EnableCommand;
import com.tracker.tasktracker.command.TaskCommands;

@SpringBootApplication
@EnableCommand(TaskCommands.class)
public class TaskTrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskTrackerApplication.class, args);
    }

}
