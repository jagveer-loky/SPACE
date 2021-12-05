package com.example.Space.domains.SpaceShuttles;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SpaceShuttleService {
    List<SpaceShuttle> getSpaceShuttle();

    public SpaceShuttle createSpaceShuttle(SpaceShuttle spaceShuttle);

    SpaceShuttle getSpaceShuttleById(Long id);

    ResponseEntity deleteSpaceShuttle(Long id);

    SpaceShuttle updateSpaceShuttle(SpaceShuttle spaceShuttle);
}
