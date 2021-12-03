package com.example.Space.domains.SpaceShuttles;

import java.util.List;

public interface SpaceShuttleService {
    List<SpaceShuttle> getSpaceShuttle(SpaceShuttle spaceShuttle);
    public SpaceShuttle createSpaceShuttle(SpaceShuttle spaceShuttle);
}
