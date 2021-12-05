package com.example.Space.domains.SpaceShuttles;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/SpaceShuttle")
public class SpaceShuttleController {

    Logger logger = LogManager.getLogger(SpaceShuttleController.class);
    @Autowired
    private final SpaceShuttleServiceImpl spaceShuttleService;

    public SpaceShuttleController(SpaceShuttleServiceImpl spaceShuttleService) {
        this.spaceShuttleService = spaceShuttleService;
    }

    /**
     * following the "/SpaceShuttle" at the end of the link you can get all of our spaceShuttles in our database.
     *
     * @return List<SpaceShuttle>
     */
    @GetMapping()
    public List<SpaceShuttle> getSpaceShuttle() {
        return spaceShuttleService.getSpaceShuttle();
    }

    /**
     * Following the "/SpaceShuttle" at then end of the link you can create a new spaceShuttle.
     *
     * @param spaceShuttle
     * @return ResponseEntity<SpaceShuttle>
     */
    @PostMapping()
    @ResponseBody
    public ResponseEntity<SpaceShuttle> createSpaceShuttle(
            @RequestBody SpaceShuttle spaceShuttle) {
        logger.info("Request recieved for createSpaceShuttle");
        logger.info(spaceShuttle);
        return new ResponseEntity<>(spaceShuttleService.createSpaceShuttle(spaceShuttle), HttpStatus.CREATED);
    }

    /**
     * Retrieves the spaceshuttle with the rpovided id from the database.
     * @param id -id of the spaceshuttle to receive
     * @return the spaceShuttle
     */
    @GetMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<SpaceShuttle> getSpaceShuttleById (@PathVariable Long id){
        logger.info("Request recieved for getSpaceShuttleById" + id);
        return new ResponseEntity<>(spaceShuttleService.getSpaceShuttleById(id), HttpStatus.OK);
    }

    /**
     * Deletes the spaceshuttle with the privided id from the database.
     * @param id - of the sapce shuttle to delte.
     * @return responseentity
     */
    @DeleteMapping(value = "{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity deleteSpaceShuttle(@PathVariable Long id){
        logger.info("Request received for DeleteSpaceShuttleById");
        return spaceShuttleService.deleteSpaceShuttle(id);
    }
    @PutMapping()
    public ResponseEntity<SpaceShuttle> updateSpaceShuttle(@RequestBody SpaceShuttle spaceShuttle){
        logger.info("Request to update SpaceShuttle");
        logger.info(spaceShuttle);
        return new ResponseEntity<>(spaceShuttleService.updateSpaceShuttle(spaceShuttle), HttpStatus.OK);
    }
}
