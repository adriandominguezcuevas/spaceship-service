package com.w2m.spaceship.application.usecase.impl;

import com.w2m.spaceship.domain.model.spaceship.PagedResult;
import com.w2m.spaceship.domain.model.spaceship.Pagination;
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

class ListSpaceshipsUseCaseImplTest {

    @Mock
    private SpaceshipRepositoryPort spaceshipRepositoryPort;

    @InjectMocks
    private ListSpaceshipsUseCaseImpl listSpaceshipsUseCaseImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should list spaceships with valid pagination")
    void shouldListSpaceshipsWithValidPagination() {
        Pagination pagination = new Pagination(1, 10, "name", "asc");
        PagedResult<SpaceshipDetail> expectedPagedResult = new PagedResult<>(/* parameters */);

        when(spaceshipRepositoryPort.findAll(pagination)).thenReturn(expectedPagedResult);

        PagedResult<SpaceshipDetail> result = listSpaceshipsUseCaseImpl.execute(pagination);

        assertEquals(expectedPagedResult, result);
        verify(spaceshipRepositoryPort).findAll(pagination);
    }

    @Test
    @DisplayName("Should handle empty pagination result")
    void shouldHandleEmptyPaginationResult() {
        Pagination pagination = new Pagination(1, 10, "name", "asc");
        PagedResult<SpaceshipDetail> emptyPagedResult = new PagedResult<>(/* parameters */);

        when(spaceshipRepositoryPort.findAll(pagination)).thenReturn(emptyPagedResult);

        PagedResult<SpaceshipDetail> result = listSpaceshipsUseCaseImpl.execute(pagination);

        assertEquals(emptyPagedResult, result);
        verify(spaceshipRepositoryPort).findAll(pagination);
    }

    @Test
    @DisplayName("Should handle null pagination gracefully")
    void shouldHandleNullPaginationGracefully() {
        Pagination pagination = null;

        PagedResult<SpaceshipDetail> result = listSpaceshipsUseCaseImpl.execute(pagination);

        verify(spaceshipRepositoryPort, never()).findAll(any());
    }
}