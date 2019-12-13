package br.com.caelum.pepperpotts.allocator;

import br.com.caelum.pepperpotts.domain.Solution;
import br.com.caelum.pepperpotts.domain.Task;
import br.com.caelum.pepperpotts.domain.TimeBox;
import java.time.LocalDate;
import java.util.List;
import org.optaplanner.core.api.solver.Solver;
import org.springframework.stereotype.Service;

@Service
class DefaultPlanningService implements PlanningService {

    private final Solver<Solution> solver;
    private final TimeBoxService timeBoxService;

    public DefaultPlanningService(Solver<Solution> solver, TimeBoxService timeBoxService) {
        this.solver = solver;
        this.timeBoxService = timeBoxService;
    }

    @Override
    public List<TimeBox> allocateTasksIn(List<Task> tasks, LocalDate date) {
        List<TimeBox> timeBoxes = timeBoxService.generateBy(date, tasks);


        Solution solution = solver.solve(new Solution(tasks, timeBoxes));

        return solution.getTimeBoxes();
    }
}
