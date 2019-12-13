package br.com.caelum.pepperpotts.domain;

import static java.util.Objects.nonNull;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.StringJoiner;

public class Task {

    private String title;
    private Priority priority;
    private Duration duration;
    private ZonedDateTime date;
    private ZonedDateTime dueDate;

    public static Task simple(String title, Priority priority) {
        return simple(title, priority, TimeBox.DEFAULT_DURATION);
    }

    public static Task simple(String title, Priority priority, Duration duration) {
        return new Task(title, priority, duration, null, null);
    }

    public static Task fixed(String title, Priority priority, Duration duration, ZonedDateTime date) {
        return new Task(title, priority, duration, date, null);
    }

    public static Task dueDate(String title, Priority priority, Duration duration, ZonedDateTime dueDate) {
        return new Task(title, priority, duration, null, dueDate);
    }

    public static Task of(String title, Priority priority, Duration duration, ZonedDateTime date, ZonedDateTime dueDate) {
        return new Task(title, priority, duration, date, dueDate);
    }

    private Task() {
        title = "Void task";
        priority = Priority.HIGH;
        duration = TimeBox.DEFAULT_DURATION;
    }

    private Task(String title, Priority priority, Duration duration, ZonedDateTime date, ZonedDateTime dueDate) {
        this.title = title;
        this.priority = priority;
        this.duration = duration;
        this.date = date;
        this.dueDate = dueDate;
    }

    public Long getTotalOfTimeBoxes() {
        return TimeBox.howManyTimeBoxes(this);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Task.class.getSimpleName() + "[", "]")
            .add("title='" + title + "'")
            .add("priority=" + priority)
            .add("duration=" + duration)
            .add("date=" + date)
            .add("dueDate=" + dueDate)
            .toString();
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public String getTitle() {
        return title;
    }

    public Priority getPriority() {
        return priority;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public ZonedDateTime getDueDate() {
        return dueDate;
    }

    public boolean hasDate() {
        return nonNull(date);
    }
    public ZonedDateTime finishDate() {
        return date.plus(duration);
    }
}
