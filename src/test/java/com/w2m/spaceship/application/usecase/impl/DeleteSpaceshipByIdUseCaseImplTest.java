package com.w2m.spaceship.application.usecase.impl;

import com.w2m.spaceship.domain.ports.spaceship.SpaceshipRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.mockito.Mockito.*;

class DeleteSpaceshipByIdUseCaseImplTest {

    @Mock
    private SpaceshipRepositoryPort spaceshipRepositoryPort;

    @InjectMocks
    private DeleteSpaceshipByIdUseCaseImpl deleteSpaceshipByIdUseCaseImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should delete spaceship by ID successfully")
    void shouldDeleteSpaceshipByIdSuccessfully() {
        UUID spaceshipId = UUID.randomUUID();

        deleteSpaceshipByIdUseCaseImpl.execute(spaceshipId);

        verify(spaceshipRepositoryPort).delete(spaceshipId);
    }

    @Test
    @DisplayName("Should handle null ID gracefully")
    void shouldHandleNullIdGracefully() {
        deleteSpaceshipByIdUseCaseImpl.execute(null);

        verify(spaceshipRepositoryPort, never()).delete(any());
    }
}