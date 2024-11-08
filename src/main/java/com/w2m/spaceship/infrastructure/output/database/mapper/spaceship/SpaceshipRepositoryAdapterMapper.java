package com.w2m.spaceship.infrastructure.output.database.mapper.spaceship;

import com.w2m.spaceship.domain.model.spaceship.CreateSpaceshipCommand;
import com.w2m.spaceship.domain.model.spaceship.SpaceshipDetail;
import com.w2m.spaceship.domain.model.spaceship.UpdateSpaceshipCommand;
import com.w2m.spaceship.infrastructure.output.database.entity.spaceship.SpaceShipEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SpaceshipRepositoryAdapterMapper {

    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now())")
    SpaceShipEntity asSpaceShipEntity(CreateSpaceshipCommand command);

    SpaceshipDetail asSpaceshipDetail(SpaceShipEntity spaceShipEntity);

    @Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now())")
    void asSpaceShipEntity(UpdateSpaceshipCommand command, @MappingTarget SpaceShipEntity entity);

}
