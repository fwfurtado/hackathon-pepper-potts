package br.com.caelum.pepperpotts.allocator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import br.com.caelum.pepperpotts.domain.Priority;
import br.com.caelum.pepperpotts.domain.Task;
import br.com.caelum.pepperpotts.domain.TimeBox;
import java.time.Duration;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class TimeBoxTest {



    @ParameterizedTest
    @MethodSource("taskWithTimeBoxesSource")
    void shouldReturnA2TimeBoxesWhenTaskHas1Hour(Task task, Long expectedTimeBoxes) {
        Long timeBoxes = TimeBox.howManyTimeBoxes(task);

        Assertions.assertEquals(expectedTimeBoxes, timeBoxes);
    }

    static Stream<Arguments> taskWithTimeBoxesSource() {
        String title = "Title";
        Priority priority = Priority.HIGH;

        return Stream.of(
          Arguments.arguments(Task.simple(title, priority), 1L),
          Arguments.arguments(Task.simple(title, priority, Duration.ofHours(1)), 2L),
          Arguments.arguments(Task.simple(title, priority, Duration.ofMinutes(90)), 3L),
          Arguments.arguments(Task.simple(title, priority, Duration.ofHours(2)), 4L)
        );
    }

}