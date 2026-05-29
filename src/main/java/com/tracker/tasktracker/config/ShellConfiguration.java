package com.tracker.tasktracker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.shell.core.ShellRunner;
import org.springframework.shell.core.command.CommandContext;
import org.springframework.shell.core.command.CommandExecutor;
import org.springframework.shell.core.command.CommandNotFoundException;
import org.springframework.shell.core.command.CommandParser;
import org.springframework.shell.core.command.CommandRegistry;
import org.springframework.shell.core.command.ExitStatus;
import org.springframework.shell.core.command.ParsedInput;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

@Configuration
public class ShellConfiguration {

    @Bean
    @Primary
    public ShellRunner interactiveShellRunner(CommandRegistry commandRegistry, CommandParser commandParser) {
        return args -> {
            CommandExecutor executor = new CommandExecutor(commandRegistry);
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter writer = new PrintWriter(System.out, true);

            while (true) {
                writer.print("shell:> ");
                writer.flush();
                String input;
                try {
                    input = reader.readLine();
                } catch (Exception e) {
                    break;
                }
                if (input == null || input.equalsIgnoreCase("exit") || input.equalsIgnoreCase("quit")) {
                    writer.println("Goodbye!");
                    break;
                }
                if (input.trim().isEmpty()) continue;

                ParsedInput parsedInput;
                try {
                    parsedInput = commandParser.parse(input);
                } catch (Exception e) {
                    writer.println("Error: " + e.getMessage());
                    continue;
                }

                try {
                    CommandContext context = new CommandContext(parsedInput, commandRegistry, writer, null);
                    ExitStatus status = executor.execute(context);
                    if (ExitStatus.OK.code() != status.code()) {
                        writer.println("Error: " + status.description());
                    }
                } catch (CommandNotFoundException e) {
                    writer.println("Command not found: " + e.getCommandName());
                } catch (Exception e) {
                    writer.println("Error: " + e.getMessage());
                }
            }
        };
    }
}
