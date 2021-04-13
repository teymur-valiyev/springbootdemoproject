package org.teamlearning.springbootdemo.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.teamlearning.springbootdemo.dto.*;
import org.teamlearning.springbootdemo.service.TaskService;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
public class TaskApiController implements TaskApi {

    @Autowired
    private TaskService taskService;

    public ResponseEntity<Void> addTask(@Valid @RequestBody TaskDTO body) {
        taskService.addTask(body);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Void> assign(@Valid @RequestBody AssignTaskDTO body) {
        taskService.assign(body);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Void> deleteTask(@PathVariable("taskId") int taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<TaskResponseDTO> getTaskByTaskId(@PathVariable("taskId") int taskId) {
        TaskResponseDTO taskByTaskId = taskService.getTaskByTaskId(taskId);
        return ResponseEntity.ok(taskByTaskId);
    }

    public ResponseEntity<TaskResponseDTOs> getTaskByUserId(@PathVariable("userId") String userId) {
        List<TaskResponseDTO> taskByUserIds = taskService.getTaskByUserId(userId);
        TaskResponseDTOs taskResponseDTOs = TaskResponseDTOs.builder().taskResponseDTOS(taskByUserIds).build();
        return ResponseEntity.ok(taskResponseDTOs);
    }

    public ResponseEntity<Void> unAssign(@Valid @RequestBody AssignTaskDTO body) {
        taskService.unAssign(body);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Void> updateTask(@PathVariable("taskId") int taskId, @Valid @RequestBody TaskUpdateDTO body) {
        taskService.updateTask(taskId, body);
        return ResponseEntity.ok().build();
    }

}
