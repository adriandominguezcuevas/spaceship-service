package com.w2m.spaceship.aspect;

import com.w2m.spaceship.exception.FuelTankLevelExceedsCapacityException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class FuelTankLevelExceedsCapacityAspect {

    private static final Logger logger = LoggerFactory.getLogger(FuelTankLevelExceedsCapacityAspect.class);

    @Around("execution(* com.w2m.spaceship.application.validator.*.*(..))")
    public Object logWhenFuelTankLevelExceedsCapacity(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            return joinPoint.proceed();
        } catch (FuelTankLevelExceedsCapacityException ex) {
            Object[] args = joinPoint.getArgs();
            String element = args.length > 0 ? String.valueOf(args[0]) : "unknown";
            logger.warn("Error in relation between fuel tank level and fuel tank capacity: {}", element);
            throw ex;
        }
    }
}
