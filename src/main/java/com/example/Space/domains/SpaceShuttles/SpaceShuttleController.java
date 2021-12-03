package com.example.Space.domains.SpaceShuttles;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/SpaceShuttle")
public class SpaceShuttleController {

    Logger logger = LogManager.getLogger(SpaceShuttleController.class);
    @Autowired
    private final SpaceShuttleServiceImpl spaceShuttleService;

    public SpaceShuttleController(SpaceShuttleServiceImpl spaceShuttleService){
        this.spaceShuttleService = spaceShuttleService;
    }

    @GetMapping()
    public ResponseEntity<List<SpaceShuttle>> getSpaceShuttle(
            @RequestParam(required = false)MultiValueMap<String, String> params) {
        logger.info("request received for getSpaceShuttle");
        SpaceShuttle spaceShuttle = new SpaceShuttle();
        return new ResponseEntity<>(spaceShuttleService.getSpaceShuttle(spaceShuttle), HttpStatus.OK);
    }
    @PostMapping()
    @ResponseBody
    public ResponseEntity<SpaceShuttle> createSpaceShuttle(
            @RequestBody SpaceShuttle spaceShuttle){
        logger.info("Request recieved for createSpaceShuttle");
        return new ResponseEntity<>(spaceShuttleService.createSpaceShuttle(spaceShuttle), HttpStatus.CREATED);
    }
}
