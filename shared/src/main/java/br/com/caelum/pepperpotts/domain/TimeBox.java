package br.com.caelum.pepperpotts.domain;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.StringJoiner;
import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.entity.PlanningPin;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

@PlanningEntity
public class TimeBox {

    public static final Duration DEFAULT_DURATION = Duration.ofMinutes(30);
    private Task task;

    private ZonedDateTime dateTime;

    public TimeBox() {
    }

    public TimeBox(ZonedDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public TimeBox(ZonedDateTime dateTime, Task task) {
        this.dateTime = dateTime;
        this.task = task;
    }

    public static Long howManyTimeBoxes(Task task) {
        Duration duration = task.getDuration();
        return duration.toMinutes() / DEFAULT_DURATION.toMinutes();
    }

    @PlanningVariable(valueRangeProviderRefs = "allTasks", nullable = true)
    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TimeBox.class.getSimpleName() + "[", "]")
            .add("task=" + task)
            .add("dateTime=" + dateTime)
            .toString();
    }

    public ZonedDateTime getDateTime() {
        return dateTime;
    }

    @PlanningPin
    public boolean isFixed() {
        return Optional.ofNullable(task)
            .map(Task::hasDate).orElse(false);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TimeBox timeBox = (TimeBox) o;
        return dateTime.equals(timeBox.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateTime);
    }
}
