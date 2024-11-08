package com.w2m.spaceship.infrastructure.output.database.mapper.user;

import com.w2m.spaceship.domain.model.user.Role;
import com.w2m.spaceship.domain.model.user.UserDetail;
import com.w2m.spaceship.infrastructure.output.database.entity.user.RoleEntity;
import com.w2m.spaceship.infrastructure.output.database.entity.user.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserRepositoryAdapterMapper {

    UserDetail asUserDetail(UserEntity userEntity);

    @Mapping(target = "users", ignore = true)
    Role roleEntityToRole(RoleEntity roleEntity);

}
