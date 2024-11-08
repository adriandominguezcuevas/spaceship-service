package com.w2m.spaceship.infrastructure.output.database.adapter.spaceship;

import com.w2m.spaceship.domain.model.spaceship.*;
import com.w2m.spaceship.infrastructure.output.database.entity.spaceship.SpaceShipEntity;
import com.w2m.spaceship.infrastructure.output.database.mapper.spaceship.SpaceshipRepositoryAdapterMapper;
import com.w2m.spaceship.infrastructure.output.database.repository.spaceship.SpaceshipJpaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SpaceshipRepositoryAdapterTest {

    @Mock
    private SpaceshipJpaRepository spaceshipJpaRepository;

    @Mock
    private SpaceshipRepositoryAdapterMapper spaceshipRepositoryAdapterMapper;

    @InjectMocks
    private SpaceshipRepositoryAdapter spaceshipRepositoryAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should save spaceship successfully")
    void shouldSaveSpaceshipSuccessfully() {
        CreateSpaceshipCommand command = new CreateSpaceshipCommand(/* parameters */);
        SpaceShipEntity entity = new SpaceShipEntity(/* parameters */);
        SpaceshipDetail expectedDetail = new SpaceshipDetail(/* parameters */);

        when(spaceshipRepositoryAdapterMapper.asSpaceShipEntity(command)).thenReturn(entity);
        when(spaceshipJpaRepository.save(entity)).thenReturn(entity);
        when(spaceshipRepositoryAdapterMapper.asSpaceshipDetail(entity)).thenReturn(expectedDetail);

        SpaceshipDetail result = spaceshipRepositoryAdapter.save(command);

        assertEquals(expectedDetail, result);
        verify(spaceshipJpaRepository).save(entity);
    }

    @Test
    @DisplayName("Should find spaceship by ID")
    void shouldFindSpaceshipById() {
        UUID id = UUID.randomUUID();
        SpaceShipEntity entity = new SpaceShipEntity(/* parameters */);
        SpaceshipDetail expectedDetail = new SpaceshipDetail(/* parameters */);

        when(spaceshipJpaRepository.findById(id)).thenReturn(Optional.of(entity));
        when(spaceshipRepositoryAdapterMapper.asSpaceshipDetail(entity)).thenReturn(expectedDetail);

        Optional<SpaceshipDetail> result = spaceshipRepositoryAdapter.findById(id);

        assertTrue(result.isPresent());
        assertEquals(expectedDetail, result.get());
    }

    @Test
    @DisplayName("Should return empty when spaceship not found by ID")
    void shouldReturnEmptyWhenSpaceshipNotFoundById() {
        UUID id = UUID.randomUUID();

        when(spaceshipJpaRepository.findById(id)).thenReturn(Optional.empty());

        Optional<SpaceshipDetail> result = spaceshipRepositoryAdapter.findById(id);

        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Should update spaceship successfully")
    void shouldUpdateSpaceshipSuccessfully() {
        UpdateSpaceshipCommand command = new UpdateSpaceshipCommand(/* parameters */);
        SpaceShipEntity existingEntity = new SpaceShipEntity(/* parameters */);
        SpaceshipDetail expectedDetail = new SpaceshipDetail(/* parameters */);

        when(spaceshipJpaRepository.findById(command.getId())).thenReturn(Optional.of(existingEntity));
        doNothing().when(spaceshipRepositoryAdapterMapper).asSpaceShipEntity(command, existingEntity);
        when(spaceshipJpaRepository.save(existingEntity)).thenReturn(existingEntity);
        when(spaceshipRepositoryAdapterMapper.asSpaceshipDetail(existingEntity)).thenReturn(expectedDetail);

        SpaceshipDetail result = spaceshipRepositoryAdapter.update(command);

        assertEquals(expectedDetail, result);
        verify(spaceshipJpaRepository).save(existingEntity);
    }

    @Test
    @DisplayName("Should throw exception when updating non-existent spaceship")
    void shouldThrowExceptionWhenUpdatingNonExistentSpaceship() {
        UpdateSpaceshipCommand command = new UpdateSpaceshipCommand(/* parameters */);

        when(spaceshipJpaRepository.findById(command.getId())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> spaceshipRepositoryAdapter.update(command));
    }

    @Test
    @DisplayName("Should delete spaceship by ID")
    void shouldDeleteSpaceshipById() {
        UUID id = UUID.randomUUID();

        spaceshipRepositoryAdapter.delete(id);

        verify(spaceshipJpaRepository).deleteById(id);
    }
}