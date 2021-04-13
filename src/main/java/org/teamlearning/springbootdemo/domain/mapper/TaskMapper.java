package org.teamlearning.springbootdemo.domain.mapper;

import org.apache.ibatis.annotations.*;
import org.teamlearning.springbootdemo.domain.dto.AssignTaskDTO;
import org.teamlearning.springbootdemo.domain.dto.TaskDTO;
import org.teamlearning.springbootdemo.domain.dto.TaskResponseDTO;
import org.teamlearning.springbootdemo.domain.dto.TaskUpdateDTO;
import org.teamlearning.springbootdemo.domain.entities.Task;

import java.util.List;

@Mapper
public interface TaskMapper {

    @Results(value = {
            @Result(property = "taskId", column = "id"),
            @Result(property = "title", column = "title"),
            @Result(property = "description", column = "description"),
            @Result(property = "deadline", column = "deadline"),
    })
    @Select("select * from task  where id=#{oid}")
    TaskResponseDTO getTaskByTaskId(@Param("oid") int id);


    @Insert("INSERT INTO public.task (title, description, deadline, status, \"userId\") " +
            "VALUES (#{title}, #{description},#{deadline},#{status},#{userId})")
    void addTask(TaskDTO taskDTO);

    @Insert("INSERT INTO public.task (title, description, deadline, status, \"userId\") " +
            "VALUES (#{title}, #{description},#{deadline},#{status},#{userId})")
    void addTask2(Task task);

    @Update("UPDATE public.task SET \"userId\"= #{userId} where id=#{taskId}")
    void assign(AssignTaskDTO assignTaskDTO);

    @Delete("DELETE from task where id=#{oid}")
    void deleteTask(@Param("oid") int oid);


    @Results(value = {
            @Result(property = "taskId", column = "id"),
            @Result(property = "title", column = "title"),
            @Result(property = "description", column = "description"),
            @Result(property = "deadline", column = "deadline"),
    })
    @Select("select * from task where \"userId\"=#{userId}")
    List<TaskResponseDTO> getTaskByUserId(String userId);

    @Update("UPDATE public.task SET \"userId\"=null where \"userId\"=#{userId} and id=#{taskId}")
    void unAssign(AssignTaskDTO assignTaskDTO);

    @Update("UPDATE public.task SET title=#{taskUpdateDTO.title}, description=#{taskUpdateDTO.description}, deadline=#{taskUpdateDTO.deadline}, status=#{taskUpdateDTO.status} where id=#{oid}")
    void updateTask(@Param("oid") int oid, TaskUpdateDTO taskUpdateDTO);
}
