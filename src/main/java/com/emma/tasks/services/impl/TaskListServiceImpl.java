package com.emma.tasks.services.impl;

import com.emma.tasks.domain.entities.TasksList;
import com.emma.tasks.repositories.TaskListRepository;
import com.emma.tasks.services.TaskListService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskListServiceImpl implements TaskListService {

    private final TaskListRepository taskListRepository;

    public TaskListServiceImpl(TaskListRepository taskListRepository) {
        this.taskListRepository = taskListRepository;
    }

    @Override
    public List<TasksList> listTaskLists() {
        return taskListRepository.findAll();
    }

    @Override
    public TasksList createTaskList(TasksList tasksList) {
        if (null != tasksList.getId()) {
            throw new IllegalArgumentException("Task list already has an ID");
        }

        if (null == tasksList.getTitle() || tasksList.getTitle().isBlank()) {
            throw new IllegalArgumentException("Task title must be present!");
        }

        LocalDateTime now = LocalDateTime.now();
        taskListRepository.save(new TasksList(
                null,
                tasksList.getTitle(),
                tasksList.getDescription(),
                null,
                now,
                now));
        return null;
    }
}
