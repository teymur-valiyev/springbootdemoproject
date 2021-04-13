
package org.teamlearning.springbootdemo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.teamlearning.springbootdemo.domain.dto.*;

import javax.validation.Valid;

@Validated
@RequestMapping(value = "/v1")
public interface TaskApi {


    @RequestMapping(value = "/task",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<Void> addTask(@Valid @RequestBody TaskDTO body);


    @RequestMapping(value = "/task/assign",
            produces = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<Void> assign(@Valid @RequestBody AssignTaskDTO body);


    @RequestMapping(value = "/task/{taskId}",
            produces = {"application/json"},
            method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteTask(@PathVariable("taskId") int taskId);


    @RequestMapping(value = "/task/{taskId}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<TaskResponseDTO> getTaskByTaskId(@PathVariable("taskId") int taskId);


    @RequestMapping(value = "/task/user/{userId}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<TaskResponseDTOs> getTaskByUserId(@PathVariable("userId") String userId);


    @RequestMapping(value = "/task/unAssign",
            produces = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<Void> unAssign(@Valid @RequestBody AssignTaskDTO body);


    @RequestMapping(value = "/task/{taskId}",
            produces = {"application/json"},
            method = RequestMethod.PUT)
    ResponseEntity<Void> updateTask(@PathVariable("taskId") int taskId, @Valid @RequestBody TaskUpdateDTO body);

}
