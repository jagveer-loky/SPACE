package com.example.Space.domains.SpaceShuttles;

import com.example.Space.exceptions.ServerError;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpaceShuttleServiceImpl implements SpaceShuttleService {
    private final Logger logger = LogManager.getLogger(SpaceShuttleServiceImpl.class);

    SpaceShuttleRepository spaceShuttleRepository;

    @Autowired
    public SpaceShuttleServiceImpl(SpaceShuttleRepository spaceShuttleRepository) {
        this.spaceShuttleRepository = spaceShuttleRepository;
    }

    public List<SpaceShuttle> getSpaceShuttle() {
        try {
            return spaceShuttleRepository.findAll();
        } catch (DataAccessException dae) {
            logger.error(dae.getMessage());
            throw new ServerError(dae.getMessage());
        }
    }

    public SpaceShuttle createSpaceShuttle(SpaceShuttle spaceShuttle) {
        try {
            logger.info("Saved SpaceShuttle");
            return spaceShuttleRepository.save(spaceShuttle);
        } catch (DataAccessException e) {
            logger.error(e.getMessage());
            throw new ServerError(e.getMessage());
        }
    }
}
