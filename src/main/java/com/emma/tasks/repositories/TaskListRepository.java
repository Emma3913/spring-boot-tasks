package com.emma.tasks.repositories;

import com.emma.tasks.domain.entities.TasksList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TaskListRepository extends JpaRepository<TasksList, UUID> {

}
