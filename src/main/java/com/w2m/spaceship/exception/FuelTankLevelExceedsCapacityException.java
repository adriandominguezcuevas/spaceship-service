package com.w2m.spaceship.exception;

public class FuelTankLevelExceedsCapacityException extends RuntimeException {
    public FuelTankLevelExceedsCapacityException(String message) {
        super(message);
    }
}
