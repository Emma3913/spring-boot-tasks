package com.emma.tasks.mappers;

import com.emma.tasks.domain.dto.TaskDto;
import com.emma.tasks.domain.entities.Task;

public interface TaskMapper {
    Task fromDto(TaskDto taskDto);
    TaskDto toDto(Task task);
}
