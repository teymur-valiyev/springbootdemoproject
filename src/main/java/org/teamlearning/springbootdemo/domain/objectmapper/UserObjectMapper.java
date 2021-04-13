package org.teamlearning.springbootdemo.domain.objectmapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.teamlearning.springbootdemo.domain.dto.SignupDTO;
import org.teamlearning.springbootdemo.domain.dto.UserDTO;
import org.teamlearning.springbootdemo.domain.entities.Organizations;
import org.teamlearning.springbootdemo.domain.entities.User;

@Mapper
public interface UserObjectMapper {

    UserObjectMapper INSTANCE = Mappers.getMapper(UserObjectMapper.class);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(source = "dto.name", target = "name"),
            @Mapping(source = "dto.address", target = "address"),
            @Mapping(source = "dto.phone", target = "phone")
    })
    Organizations getOrganizations(SignupDTO dto);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "surname", ignore = true),
            @Mapping(target = "role", ignore = true),
            @Mapping(source = "dto.user", target = "name"),
            @Mapping(source = "dto.email", target = "email"),
            @Mapping(source = "dto.password", target = "psw"),
    })
    User getUserFromSignupDTO(SignupDTO dto);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "surname", ignore = true),
            @Mapping(target = "role", ignore = true),
            @Mapping(source = "dto.name", target = "name"),
            @Mapping(source = "dto.email", target = "email"),
            @Mapping(source = "dto.password", target = "psw"),
    })
    User getUserFromDTO(UserDTO dto);

}
