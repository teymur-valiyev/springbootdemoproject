package org.teamlearning.springbootdemo.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.teamlearning.springbootdemo.domain.dto.AssignTaskDTO;
import org.teamlearning.springbootdemo.domain.dto.TaskDTO;
import org.teamlearning.springbootdemo.domain.dto.TaskResponseDTO;
import org.teamlearning.springbootdemo.domain.dto.TaskUpdateDTO;
import org.teamlearning.springbootdemo.domain.entities.Task;
import org.teamlearning.springbootdemo.domain.mapper.TaskMapper;
import org.teamlearning.springbootdemo.domain.objectmapper.TaskObjectMapper;
import org.teamlearning.springbootdemo.service.TaskService;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskMapper taskMapper;

    @Override
    public TaskResponseDTO getTaskByTaskId(int taskId) {
        // todo: add task mapper
//        TaskResponseDTO hello_world = TaskResponseDTO.builder()
//                .taskId("1")
//                .status(StatusEnum.DONE)
//                .title("hello world")
//                .build();
//
//        try {
        TaskResponseDTO taskResponseDTO = taskMapper.getTaskByTaskId(taskId);
//            hello_world = taskByTaskId;
//        } catch (Exception e) {
//            log.error("find task error", e);
//        }

        return taskResponseDTO;
    }

    @Override
    public void addTask(TaskDTO taskDTO) {
        Task task = TaskObjectMapper.INSTANCE.getTask(taskDTO);
        taskMapper.addTask2(task);
        log.info(String.valueOf(task.getId()));
    }

    @Override
    public void assign(AssignTaskDTO assignTaskDTO) {
        taskMapper.assign(assignTaskDTO);
    }

    @Override
    public void deleteTask(int taskId) {
        taskMapper.deleteTask(taskId);
    }

    @Override
    public List<TaskResponseDTO> getTaskByUserId(String userId) {
        List<TaskResponseDTO> taskByUserIds = taskMapper.getTaskByUserId(userId);
        return taskByUserIds;
    }

    @Override
    public void unAssign(AssignTaskDTO assignTaskDTO) {
        taskMapper.unAssign(assignTaskDTO);
    }

    @Override
    public void updateTask(int taskId, TaskUpdateDTO taskUpdateDTO) {
        taskMapper.updateTask(taskId, taskUpdateDTO);
    }
}
