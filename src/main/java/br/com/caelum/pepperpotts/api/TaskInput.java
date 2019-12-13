package br.com.caelum.pepperpotts.api;

import br.com.caelum.pepperpotts.domain.Priority;
import java.time.Duration;
import java.time.ZonedDateTime;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

class TaskInput {

    @NotBlank
    private String title;

    private Duration duration = Duration.ofMinutes(30);

    @FutureOrPresent
    private ZonedDateTime date;

    @FutureOrPresent
    private ZonedDateTime dueDate;

    @NotNull
    private Priority priority;

    @NotNull
    private TaskType type;

    public String getTitle() {
        return title;
    }

    public Duration getDuration() {
        return duration;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public ZonedDateTime getDueDate() {
        return dueDate;
    }

    public Priority getPriority() {
        return priority;
    }

    public TaskType getType() {
        return type;
    }
}
