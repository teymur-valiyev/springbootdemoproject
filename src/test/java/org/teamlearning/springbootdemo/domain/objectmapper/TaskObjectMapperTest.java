package org.teamlearning.springbootdemo.domain.objectmapper;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.teamlearning.springbootdemo.domain.dto.TaskDTO;
import org.teamlearning.springbootdemo.domain.entities.Task;
import org.teamlearning.springbootdemo.domain.enums.StatusEnum;

public class TaskObjectMapperTest {

    private TaskObjectMapper taskObjectMapper;

    @Before
    public void setup() {
        taskObjectMapper = TaskObjectMapper.INSTANCE;
    }

    @Test
    public void getTaskTest() {
        TaskDTO taskDTO = TaskDTO.builder()
                .title("task1").description("task desc").deadline("10.10.2021")
                .status(StatusEnum.TO_DO).userId("john").build();

        Task task = taskObjectMapper.getTask(taskDTO);
        Assert.assertEquals(task.getTitle(), taskDTO.getTitle());
        Assert.assertEquals(task.getDescription(), taskDTO.getDescription());
        Assert.assertEquals(task.getDeadline(), taskDTO.getDeadline());
//        Assert.assertEquals(task.getStatus(), taskDTO.getStatus());
        Assert.assertEquals(task.getUserId(), taskDTO.getUserId());
    }
}
