package com.emma.tasks.controllers;

import com.emma.tasks.domain.dto.TaskListDto;
import com.emma.tasks.domain.entities.TasksList;
import com.emma.tasks.mappers.TaskListMapper;
import com.emma.tasks.services.TaskListService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(path = "api/task-lists")
public class TaskListController {

    private final TaskListService taskListService;
    private final TaskListMapper taskListMapper;

    public TaskListController(TaskListService taskListService, TaskListMapper taskListMapper) {
        this.taskListService = taskListService;
        this.taskListMapper = taskListMapper;
    }

    @GetMapping
    public List<TaskListDto> listTaskLists() {
        return taskListService.listTaskLists()
                .stream()
                .map(taskListMapper::toDto)
                .toList();
    }

    @PostMapping
    public TaskListDto createTaskListDto(@RequestBody TaskListDto taskListDto) {
        TasksList createdTaskList = taskListService.createTaskList(
                taskListMapper.fromDto(taskListDto));
        return taskListMapper.toDto(createdTaskList);
    }

}
