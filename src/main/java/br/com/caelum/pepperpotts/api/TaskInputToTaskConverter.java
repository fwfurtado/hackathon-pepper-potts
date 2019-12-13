package br.com.caelum.pepperpotts.api;

import br.com.caelum.pepperpotts.domain.Task;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
class TaskInputToTaskConverter implements Converter<TaskInput, Task> {

    @Override
    public Task convert(TaskInput source) {
        TaskType type = source.getType();

        switch (type) {
            case DUE_DATE:
                return Task.dueDate(source.getTitle(), source.getPriority(), source.getDuration(), source.getDueDate());
            case FIXED:
                return Task.fixed(source.getTitle(), source.getPriority(), source.getDuration(), source.getDate());
            case SIMPLE:
                return Task.simple(source.getTitle(), source.getPriority(), source.getDuration());
            default:
                throw new IllegalStateException("The type of task must be in DUE_DATE, FIXED or SIMPLE");
        }
    }
}
