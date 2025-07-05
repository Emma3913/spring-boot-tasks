package com.emma.tasks.services;

import com.emma.tasks.domain.entities.TasksList;

import java.util.List;

public interface TaskListService {
    List<TasksList> listTaskLists();

    TasksList createTaskList(TasksList tasksList);
}
