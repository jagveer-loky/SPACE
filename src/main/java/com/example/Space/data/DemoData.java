package com.example.Space.data;

import com.example.Space.domains.SpaceShuttles.SpaceShuttle;
import com.example.Space.domains.SpaceShuttles.SpaceShuttleRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
@Component
public class DemoData implements CommandLineRunner {

  private final Logger logger = LogManager.getLogger(DemoData.class);
  @Autowired
  private SpaceShuttleRepository spaceShuttleRepository;
  @Autowired
  private Environment env;

  @Override
  public void run(String... strings) {
    boolean loadData;

    try {
      // Retrieve the value of custom property in application.yml
      loadData = Boolean.parseBoolean(env.getProperty("spaceshuttle.load"));
    } catch (NumberFormatException nfe) {
      logger.error("config variable loadData could not be parsed, falling back to default");
      loadData = true;
    }

    if (loadData) {
      seedDatabase();
    }
  }

  private void seedDatabase(){
    SpaceShuttle spaceShuttle = new SpaceShuttle(true, "Danny", "Danny's first trip",
        BigDecimal.valueOf(3), LocalDate.now());
    spaceShuttleRepository.save(spaceShuttle);
    SpaceShuttle spaceShuttle2 = new SpaceShuttle(true, "Danny", "Danny's first trip",
        BigDecimal.valueOf(3), LocalDate.now());
    spaceShuttleRepository.save(spaceShuttle2);
    SpaceShuttle spaceShuttle3 = new SpaceShuttle(true, "Danny", "Danny's first trip",
        BigDecimal.valueOf(3), LocalDate.now());
    spaceShuttleRepository.save(spaceShuttle3);
  }
}
