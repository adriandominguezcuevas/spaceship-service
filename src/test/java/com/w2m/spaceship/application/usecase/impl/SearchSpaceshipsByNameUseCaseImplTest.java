package com.w2m.spaceship.application.usecase.impl;

import com.w2m.spaceship.domain.model.spaceship.PagedResult;
import com.w2m.spaceship.domain.model.spaceship.Pagination;
import com.w2m.spaceship.domain.model.spaceship.SearchSpaceshipByNameCommand;
import com.w2m.spaceship.domain.model.spaceship.SpaceshipDetail;
import com.w2m.spaceship.domain.ports.spaceship.SpaceshipRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class SearchSpaceshipsByNameUseCaseImplTest {

    @Mock
    private SpaceshipRepositoryPort spaceshipRepositoryPort;

    @InjectMocks
    private SearchSpaceshipsByNameUseCaseImpl searchSpaceshipsByNameUseCaseImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should return spaceships for valid name")
    void shouldReturnSpaceshipsForValidName() {
        Pagination pagination = new Pagination(1, 10, "name", "asc");
        SearchSpaceshipByNameCommand command = new SearchSpaceshipByNameCommand("Falcon", pagination);
        PagedResult<SpaceshipDetail> expectedPagedResult = new PagedResult<>(/* parameters */);

        when(spaceshipRepositoryPort.findByName(command)).thenReturn(expectedPagedResult);

        PagedResult<SpaceshipDetail> result = searchSpaceshipsByNameUseCaseImpl.execute(command);

        assertEquals(expectedPagedResult, result);
        verify(spaceshipRepositoryPort).findByName(command);
    }

    @Test
    @DisplayName("Should handle empty result for non-existent name")
    void shouldHandleEmptyResultForNonExistentName() {
        Pagination pagination = new Pagination(1, 10, "name", "asc");
        SearchSpaceshipByNameCommand command = new SearchSpaceshipByNameCommand("NonExistent", pagination);
        PagedResult<SpaceshipDetail> emptyPagedResult = new PagedResult<>(/* parameters */);

        when(spaceshipRepositoryPort.findByName(command)).thenReturn(emptyPagedResult);

        PagedResult<SpaceshipDetail> result = searchSpaceshipsByNameUseCaseImpl.execute(command);

        assertEquals(emptyPagedResult, result);
        verify(spaceshipRepositoryPort).findByName(command);
    }

    @Test
    @DisplayName("Should handle null command gracefully")
    void shouldHandleNullCommandGracefully() {
        SearchSpaceshipByNameCommand command = null;

        PagedResult<SpaceshipDetail> result = searchSpaceshipsByNameUseCaseImpl.execute(command);

        verify(spaceshipRepositoryPort, never()).findByName(any());
    }
}