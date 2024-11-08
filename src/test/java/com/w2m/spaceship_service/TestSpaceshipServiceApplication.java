package com.w2m.spaceship_service;

import com.w2m.spaceship.SpaceshipServiceApplication;
import org.springframework.boot.SpringApplication;

public class TestSpaceshipServiceApplication {

	public static void main(String[] args) {
		SpringApplication.from(SpaceshipServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
