package com.emma.tasks.mappers.impl;

import com.emma.tasks.domain.dto.TaskListDto;
import com.emma.tasks.domain.entities.Task;
import com.emma.tasks.domain.entities.TaskStatus;
import com.emma.tasks.domain.entities.TasksList;
import com.emma.tasks.mappers.TaskListMapper;
import com.emma.tasks.mappers.TaskMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TaskListMapperImpl implements TaskListMapper {

    private final TaskMapper taskMapper;

    public TaskListMapperImpl(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    @Override
    public TasksList fromDto(TaskListDto taskListDto) {
        return new TasksList(
                taskListDto.id(),
                taskListDto.title(),
                taskListDto.description(),
                Optional.ofNullable(taskListDto.tasks())
                        .map(tasks -> tasks.stream()
                                .map(taskMapper::fromDto)
                                .toList()
                        ).orElse(null),
                null,
                null
        );
    }

    @Override
    public TaskListDto toDto(TasksList tasksList) {
        return new TaskListDto(
                tasksList.getId(),
                tasksList.getTitle(),
                tasksList.getDescription(),
                Optional.ofNullable(tasksList.getTasks())
                        .map(List::size)
                        .orElse(0),
                calculateTaskListProgress(tasksList.getTasks()),
                Optional.ofNullable(tasksList.getTasks())
                        .map(tasks ->
                                tasks.stream().map(taskMapper::toDto).stream().toList()
                        ).orElse(null)
        );
    }

    private Double calculateTaskListProgress(List<Task> tasks) {
        if (null == tasks) {
            return null;
        }

        long closeTaskCount = tasks.stream().filter(task ->
                TaskStatus.CLOSE == task.getStatus()).count();
        return (double) closeTaskCount / tasks.size();
    }
}
