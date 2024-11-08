package com.w2m.spaceship.application.usecase.impl;

import com.w2m.spaceship.application.validator.FuelTankValidator;
import com.w2m.spaceship.domain.model.spaceship.SpaceshipDetail;
import com.w2m.spaceship.domain.model.spaceship.UpdateSpaceshipCommand;
import com.w2m.spaceship.domain.ports.spaceship.SpaceshipRepositoryPort;
import com.w2m.spaceship.exception.FuelTankLevelExceedsCapacityException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class UpdateSpaceshipUseCaseImplTest {

    @Mock
    private SpaceshipRepositoryPort spaceshipRepositoryPort;

    @Mock
    private FuelTankValidator fuelTankValidator;

    @InjectMocks
    private UpdateSpaceshipUseCaseImpl updateSpaceshipUseCaseImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should update spaceship successfully")
    void shouldUpdateSpaceshipSuccessfully() {
        UpdateSpaceshipCommand command = new UpdateSpaceshipCommand(/* parameters */);
        SpaceshipDetail expectedSpaceshipDetail = new SpaceshipDetail(/* parameters */);

        when(spaceshipRepositoryPort.update(command)).thenReturn(expectedSpaceshipDetail);

        SpaceshipDetail result = updateSpaceshipUseCaseImpl.execute(command);

        assertEquals(expectedSpaceshipDetail, result);
        verify(fuelTankValidator).validateFuelTankLevel(command);
        verify(spaceshipRepositoryPort).update(command);
    }

    @Test
    @DisplayName("Should handle invalid fuel tank level")
    void shouldHandleInvalidFuelTankLevel() {
        UpdateSpaceshipCommand command = new UpdateSpaceshipCommand(/* parameters */);
        doThrow(new FuelTankLevelExceedsCapacityException("Fuel tank level must be less than fuel tank")).when(fuelTankValidator).validateFuelTankLevel(command);

        assertThrows(FuelTankLevelExceedsCapacityException.class, () -> updateSpaceshipUseCaseImpl.execute(command));

        verify(fuelTankValidator).validateFuelTankLevel(command);
        verify(spaceshipRepositoryPort, never()).update(any());
    }
}