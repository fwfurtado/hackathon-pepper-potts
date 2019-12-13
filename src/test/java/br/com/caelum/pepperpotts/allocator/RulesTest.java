package br.com.caelum.pepperpotts.allocator;

import br.com.caelum.pepperpotts.domain.Priority;
import br.com.caelum.pepperpotts.domain.Solution;
import br.com.caelum.pepperpotts.domain.Task;
import br.com.caelum.pepperpotts.domain.TimeBox;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.optaplanner.core.api.solver.SolverFactory;
import org.optaplanner.test.impl.score.buildin.hardsoft.HardSoftScoreVerifier;

class RulesTest {
    private SolverFactory<Solution> factory = SolverFactory.createFromXmlResource("allocate-configuration.xml");
    private HardSoftScoreVerifier<Solution> checker = new HardSoftScoreVerifier<>(factory);

    @Test
    void shouldReturnAZeroWeightWhenGivenLessTaskThanTimeBoxes() {
        Task dev1 = Task.simple("Dev 1", Priority.HIGH);
        Task dev2 = Task.simple("Dev 2", Priority.HIGH);
        Task dev3 = Task.simple("Dev 3", Priority.HIGH);
        Task meet1 = Task.simple("Meet 1", Priority.HIGH);
        Task meet2 = Task.simple("Meet 2", Priority.HIGH);

        List<Task> tasks = Arrays.asList(
            dev1,
            dev2,
            dev3,
            meet1,
            meet2
        );

        LocalDate date = LocalDate.of(2019, Month.MAY, 18);

        List<TimeBox> timeBoxes = Arrays.asList(

            new TimeBox(ZonedDateTime.of(date, LocalTime.of(8, 0), ZoneId.systemDefault()), dev1),
            new TimeBox(ZonedDateTime.of(date, LocalTime.of(8, 30), ZoneId.systemDefault()), dev2),
            new TimeBox(ZonedDateTime.of(date, LocalTime.of(9, 0), ZoneId.systemDefault()), dev3),
            new TimeBox(ZonedDateTime.of(date, LocalTime.of(9, 30), ZoneId.systemDefault()), meet1),
            new TimeBox(ZonedDateTime.of(date, LocalTime.of(10, 0), ZoneId.systemDefault()), meet2),
            new TimeBox(ZonedDateTime.of(date, LocalTime.of(10, 30), ZoneId.systemDefault())),
            new TimeBox(ZonedDateTime.of(date, LocalTime.of(11, 0), ZoneId.systemDefault()))

        );

        Solution solution = new Solution(tasks, timeBoxes);

        checker.assertSoftWeight("Cannot allocate more task than your total of time-boxes", 0, solution);

        timeBoxes.get(1).setTask(dev1);

        checker.assertSoftWeight("Cannot allocate more task than your total of time-boxes", -20, solution);
    }

    @Test
    void shouldReturnAZeroWeightWhenGivenTasksOrderedByPriorityValue() {
        Task dev1 = Task.simple("Dev 1", Priority.LOW);
        Task dev2 = Task.simple("Dev 2", Priority.MEDIUM);
        Task dev3 = Task.simple("Dev 3", Priority.HIGH);


        List<Task> tasks = Arrays.asList(
            dev1,
            dev2,
            dev3
        );

        LocalDate date = LocalDate.of(2019, Month.MAY, 18);

        List<TimeBox> timeBoxes = Arrays.asList(

            new TimeBox(ZonedDateTime.of(date, LocalTime.of(8, 0), ZoneId.systemDefault()), dev3),
            new TimeBox(ZonedDateTime.of(date, LocalTime.of(9, 0), ZoneId.systemDefault()), dev2),
            new TimeBox(ZonedDateTime.of(date, LocalTime.of(9, 30), ZoneId.systemDefault()), dev1),
            new TimeBox(ZonedDateTime.of(date, LocalTime.of(11, 0), ZoneId.systemDefault()))

        );

        Solution solution = new Solution(tasks, timeBoxes);

        checker.assertSoftWeight("Cannot allocate task with low priority before tasks with high priority", 0, solution);

        dev1.setPriority(Priority.HIGH);

        checker.assertSoftWeight("Cannot allocate task with low priority before tasks with high priority", -10, solution);
    }

}