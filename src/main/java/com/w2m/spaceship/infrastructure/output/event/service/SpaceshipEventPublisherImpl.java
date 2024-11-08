package com.w2m.spaceship.infrastructure.output.event.service;

import com.w2m.spaceship.domain.model.spaceship.SpaceshipDetail;
import com.w2m.spaceship.domain.ports.spaceship.SpaceshipEventPublisher;
import com.w2m.spaceship.infrastructure.output.event.mapper.SpaceshipEventPublisherMapper;

import java.util.UUID;

public class SpaceshipEventPublisherImpl implements SpaceshipEventPublisher {

    private final SpaceshipEventPublisherMapper spaceshipEventPublisherMapper;

    public SpaceshipEventPublisherImpl(SpaceshipEventPublisherMapper spaceshipEventPublisherMapper) {
        this.spaceshipEventPublisherMapper = spaceshipEventPublisherMapper;
    }

    @Override
    public void publishCreateSpaceshipEvent(SpaceshipDetail spaceshipDetail) {

    }

    @Override
    public void publishUpdateSpaceshipEvent(SpaceshipDetail spaceshipDetail) {

    }

    @Override
    public void publishDeleteSpaceshipEvent(UUID id) {

    }
}
