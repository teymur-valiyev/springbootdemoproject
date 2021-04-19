package org.teamlearning.springbootdemo.domain.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.teamlearning.springbootdemo.domain.dto.SignupDTO;
import org.teamlearning.springbootdemo.domain.dto.UserResponseDTO;
import org.teamlearning.springbootdemo.domain.dto.UserUpdateDTO;
import org.teamlearning.springbootdemo.domain.entities.Organizations;
import org.teamlearning.springbootdemo.domain.entities.User;

@Repository
@Mapper
public interface UserMapper {
    @Results(value = {
            @Result(property = "id", column = "id")
    })
    @Insert("INSERT INTO public.organizations (name, phone, address) " +
            "VALUES (#{signupDTO.name}, #{signupDTO.phone},#{signupDTO.address})")
    void createOrganizationUser(SignupDTO signupDTO);

    @Insert("INSERT INTO public.organizations (name, phone, address) " +
            "VALUES (#{name}, #{phone},#{address})")
    @SelectKey(statement = "select max(id) from organizations", keyProperty = "id", before = false, resultType = int.class)
    void createOrganization(Organizations org);

    @Insert("INSERT INTO public.staff (organization_id, user_id) " +
            "VALUES (#{organizationId}, #{userId})")
    void createStaff(String userId, String organizationId);

    @Delete("DELETE from users where id=#{oid}")
    void deleteUser(@Param("oid") int oid);


    @Insert("INSERT INTO public.users (name, surname, email, psw, role) " +
            "VALUES (#{name}, #{surname},#{email}, #{psw},  #{role})")
//    @SelectKey(statement = "SELECT currval('users_id_seq')", keyProperty = "id", before = false, resultType = int.class)
    @SelectKey(statement = "select max(id) from users", keyProperty = "id", before = false, resultType = int.class)
    void createUser(User user);


    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "surname", column = "surname"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "psw")
    })
    @Select("select * from users  where name=#{name}")
    UserResponseDTO getUserByName(@Param("name") String name);

    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "createdAt", column = "createdAt"),
            @Result(property = "name", column = "name"),
            @Result(property = "surname", column = "surname"),
            @Result(property = "email", column = "email"),
            @Result(property = "psw", column = "psw"),
            @Result(property = "role", column = "role")
    })
    @Select("select * from users  where name=#{name}")
    User getUserByNameAll(@Param("name") String name);


    @Update("UPDATE public.users SET name=#{userUpdateDTO.name}, surname=#{userUpdateDTO.surname}, email=#{userUpdateDTO.email}, psw=#{userUpdateDTO.password} where id=#{userId}")
    void updateUser(@Param("userId") int userId, UserUpdateDTO userUpdateDTO);

}
