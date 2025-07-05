package com.emma.tasks.mappers;

import com.emma.tasks.domain.dto.TaskListDto;
import com.emma.tasks.domain.entities.TasksList;

public interface TaskListMapper {
    TasksList fromDto(TaskListDto taskListDto);

    TaskListDto toDto(TasksList taskList);
}
