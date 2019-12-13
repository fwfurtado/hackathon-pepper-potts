package br.com.caelum.pepperpotts.api;

import java.util.List;
import javax.validation.constraints.NotEmpty;

class TasksInputWrapper {

    @NotEmpty
    private List<TaskInput> tasks;

    /**
     * @deprecated frameworks only
     */
    @Deprecated
    private TasksInputWrapper() { }

    public TasksInputWrapper(List<TaskInput> tasks) {
        this.tasks = tasks;
    }

    public List<TaskInput> getTasks() {
        return tasks;
    }
}
