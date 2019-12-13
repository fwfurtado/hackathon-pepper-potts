package br.com.caelum.pepperpotts.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.drools.ProblemFactCollectionProperty;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;

@PlanningSolution
public class Solution {
    private List<Task> tasks;

    @PlanningEntityCollectionProperty
    private List<TimeBox> timeBoxes = new ArrayList<>();
    private HardSoftScore score;

    public Solution() {
    }

    public Solution(List<Task> tasks, List<TimeBox> timeBoxes) {
        this.tasks = tasks;
        this.timeBoxes = timeBoxes;
    }

    @ValueRangeProvider(id="allTasks")
    @ProblemFactCollectionProperty
    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<TimeBox> getTimeBoxes() {
        return timeBoxes;
    }

    public void setTimeBoxes(List<TimeBox> timeBoxes) {
        this.timeBoxes = timeBoxes;
    }

    @PlanningScore
    public HardSoftScore getScore() {
        return score;
    }

    public void setScore(HardSoftScore score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Solution.class.getSimpleName() + "[", "]")
            .add("tasks=" + tasks)
            .add("timeBoxes=" + timeBoxes)
            .add("score=" + score)
            .toString();
    }
}
