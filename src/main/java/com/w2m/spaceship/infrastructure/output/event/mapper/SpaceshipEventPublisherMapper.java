package com.w2m.spaceship.infrastructure.output.event.mapper;

import com.w2m.spaceship.domain.enums.ActionEventType;
import com.w2m.spaceship.domain.model.spaceship.SpaceshipDetail;
import com.w2m.spaceship.infrastructure.output.event.model.SpaceshipDetailEvent;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SpaceshipEventPublisherMapper {

    SpaceshipDetailEvent asSpaceshipDetailEvent(SpaceshipDetail spaceshipDetail, ActionEventType action);

}
