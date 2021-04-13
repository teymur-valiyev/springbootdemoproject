package org.teamlearning.springbootdemo.service;

import org.teamlearning.springbootdemo.domain.dto.AssignTaskDTO;
import org.teamlearning.springbootdemo.domain.dto.TaskDTO;
import org.teamlearning.springbootdemo.domain.dto.TaskResponseDTO;
import org.teamlearning.springbootdemo.domain.dto.TaskUpdateDTO;

import java.util.List;

public interface TaskService {

    TaskResponseDTO getTaskByTaskId(int taskId);

    void addTask(TaskDTO taskDTO);

    void assign(AssignTaskDTO assignTaskDTO);

    void deleteTask(int taskId);

    List<TaskResponseDTO> getTaskByUserId(String userId);

    void unAssign(AssignTaskDTO assignTaskDTO);

    void updateTask(int taskID, TaskUpdateDTO taskUpdateDTO);


}
