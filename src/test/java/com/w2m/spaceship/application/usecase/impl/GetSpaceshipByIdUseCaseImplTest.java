package com.w2m.spaceship.application.usecase.impl;

import com.w2m.spaceship.domain.model.spaceship.SpaceshipDetail;
import com.w2m.spaceship.domain.ports.spaceship.SpaceshipRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class GetSpaceshipByIdUseCaseImplTest {

    @Mock
    private SpaceshipRepositoryPort spaceshipRepositoryPort;

    @InjectMocks
    private GetSpaceshipByIdUseCaseImpl getSpaceshipByIdUseCaseImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should return spaceship details for valid ID")
    void shouldReturnSpaceshipDetailsForValidId() {
        UUID spaceshipId = UUID.randomUUID();
        SpaceshipDetail expectedSpaceshipDetail = new SpaceshipDetail(/* parameters */);

        when(spaceshipRepositoryPort.findById(spaceshipId)).thenReturn(Optional.of(expectedSpaceshipDetail));

        Optional<SpaceshipDetail> result = getSpaceshipByIdUseCaseImpl.execute(spaceshipId);

        assertTrue(result.isPresent());
        assertEquals(expectedSpaceshipDetail, result.get());
        verify(spaceshipRepositoryPort).findById(spaceshipId);
    }

    @Test
    @DisplayName("Should return empty for non-existent ID")
    void shouldReturnEmptyForNonExistentId() {
        UUID spaceshipId = UUID.randomUUID();

        when(spaceshipRepositoryPort.findById(spaceshipId)).thenReturn(Optional.empty());

        Optional<SpaceshipDetail> result = getSpaceshipByIdUseCaseImpl.execute(spaceshipId);

        assertTrue(result.isEmpty());
        verify(spaceshipRepositoryPort).findById(spaceshipId);
    }

    @Test
    @DisplayName("Should handle null ID gracefully")
    void shouldHandleNullIdGracefully() {
        UUID spaceshipId = null;

        Optional<SpaceshipDetail> result = getSpaceshipByIdUseCaseImpl.execute(spaceshipId);

        assertTrue(result.isEmpty());
        verify(spaceshipRepositoryPort, never()).findById(any());
    }
}