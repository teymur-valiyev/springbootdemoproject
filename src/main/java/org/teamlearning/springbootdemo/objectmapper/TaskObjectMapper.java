package org.teamlearning.springbootdemo.objectmapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.teamlearning.springbootdemo.dto.TaskDTO;
import org.teamlearning.springbootdemo.entities.Task;

@Mapper
public interface TaskObjectMapper {

    TaskObjectMapper INSTANCE = Mappers.getMapper(TaskObjectMapper.class);

    @Mappings({
            @Mapping(target = "id",ignore = true),
            @Mapping(target = "createdAt",ignore = true),
            @Mapping(source = "dto.title", target = "title"),
            @Mapping(source = "dto.description", target = "description"),
            @Mapping(source = "dto.deadline", target = "deadline"),
//            @Mapping(source = "dto.status", target = "status"),
            @Mapping(target = "status", ignore = true),
            @Mapping(source = "dto.userId", target = "userId")
    })
    Task getTask(TaskDTO dto);

}
