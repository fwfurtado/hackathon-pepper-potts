package br.com.caelum.pepperpotts.allocator;

import br.com.caelum.pepperpotts.domain.Task;
import br.com.caelum.pepperpotts.domain.TimeBox;
import java.time.LocalDate;
import java.util.List;

public interface PlanningService {

    List<TimeBox> allocateTasksIn(List<Task> tasks, LocalDate date);
}
