package br.com.caelum.pepperpotts.api;

import static java.util.stream.Collectors.toList;

import br.com.caelum.pepperpotts.allocator.PlanningService;
import br.com.caelum.pepperpotts.domain.Task;
import br.com.caelum.pepperpotts.domain.TimeBox;
import java.time.LocalDate;
import java.util.List;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
class AllocatorService {

    private final Converter<TaskInput, Task> converter;
    private final PlanningService planning;

    public AllocatorService(Converter<TaskInput, Task> converter, PlanningService planning) {
        this.converter = converter;
        this.planning = planning;
    }

    List<TimeBox> generateAllocationFor(TasksInputWrapper input, LocalDate date) {

        List<Task> tasks = input.getTasks()
                                .stream()
                                    .map(converter::convert)
                                        .collect(toList());

        return planning.allocateTasksIn(tasks, date);
    }
}
