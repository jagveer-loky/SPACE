package com.example.Space.domains.SpaceShuttles;

import com.example.Space.exceptions.ResourceNotFound;
import com.example.Space.exceptions.ServerError;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpaceShuttleServiceImpl implements SpaceShuttleService {
    private final Logger logger = LogManager.getLogger(SpaceShuttleServiceImpl.class);

    SpaceShuttleRepository spaceShuttleRepository;

    @Autowired
    public SpaceShuttleServiceImpl(SpaceShuttleRepository spaceShuttleRepository) {
        this.spaceShuttleRepository = spaceShuttleRepository;
    }

    /**
     * gets a list of all Space Shuttles.
     * Catches and exception if there is any.
     *
     * @return List of SpaceShuttles
     */
    public List<SpaceShuttle> getSpaceShuttle() {
        try {
            return spaceShuttleRepository.findAll();
        } catch (DataAccessException dae) {
            logger.error(dae.getMessage());
            throw new ServerError(dae.getMessage());
        }
    }

    /**
     * Creates a spaceShuttle, catches an excpetion if creating goes wrong.
     *
     * @param spaceShuttle
     * @return SpaceShuttle
     */
    public SpaceShuttle createSpaceShuttle(SpaceShuttle spaceShuttle) {
        try {
            logger.info("Saved SpaceShuttle");
            return spaceShuttleRepository.save(spaceShuttle);
        } catch (DataAccessException e) {
            logger.error(e.getMessage());
            throw new ServerError(e.getMessage());
        }
    }

    public SpaceShuttle getSpaceShuttleById(Long id) {
        SpaceShuttle spaceShuttle;

        try {
            spaceShuttle = spaceShuttleRepository.findById(id).orElse(null);
        } catch (DataAccessException e) {
            logger.error(e.getMessage());
            throw new ServerError(e.getMessage());
        }

        if (spaceShuttle != null) {
            return spaceShuttle;
        } else {
            logger.info("Get by id failed, it does not exist in the database: " + id);
            throw new ResourceNotFound("Get by id failed, it does not exist in the database: " + id);
        }
    }

    public ResponseEntity<Void> deleteSpaceShuttle(Long id) {
        Optional<SpaceShuttle> spaceShuttle;
        try {
            spaceShuttle = spaceShuttleRepository.findById(id);
        } catch (DataAccessException e) {
            logger.error(e.getMessage());
            throw new ServerError(e.getMessage());
        }
        if (spaceShuttle.isPresent()) {
            spaceShuttleRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        throw new ResourceNotFound("SpaceShuttle Id: " + id + " does not exist in the database");
    }

    public SpaceShuttle updateSpaceShuttle(SpaceShuttle spaceShuttle) {
        SpaceShuttle existingSpaceShuttle;
        try {
            existingSpaceShuttle = spaceShuttleRepository.findById(spaceShuttle.getId()).orElse(null);
        } catch (DataAccessException e) {
            logger.error(e.getMessage());
            throw new ServerError(e.getMessage());
        }
        if (existingSpaceShuttle != null) {
            try {
                return spaceShuttleRepository.save(spaceShuttle);
            } catch (DataAccessException e) {
                logger.error(e.getMessage());
                throw new ServerError(e.getMessage());
            }
        } else {
            logger.error("Product with id:" + spaceShuttle.getId() + " does not exist");
            throw new ResourceNotFound("Product with id:" + spaceShuttle.getId() + " does not exist");
        }
    }
}