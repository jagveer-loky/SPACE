package com.example.Space.domains.SpaceShuttles;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpaceShuttleRepository extends JpaRepository<SpaceShuttle, Long> {
}
