package com.example.Space.domains.SpaceShuttles;

import com.example.Space.exceptions.ServerError;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.dao.DataAccessException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SpaceShuttleServiceImplTest {

  SpaceShuttle spaceShuttle;
  @InjectMocks
  private SpaceShuttleServiceImpl spaceShuttleServiceImpl;
  @Mock
  private SpaceShuttleRepository spaceShuttleRepository;
  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
    spaceShuttle = new SpaceShuttle(true, "Danny", "Danny's first trip",
        BigDecimal.valueOf(3), null);
  }

  @Test
  public void postSpaceShuttle() {
    final SpaceShuttle spaceShuttle = new SpaceShuttle(true, "Danny", "Danny's first trip",
        BigDecimal.valueOf(3), null);
    given(spaceShuttleRepository.findById(anyLong())).willReturn(Optional.empty());
    given(spaceShuttleRepository.save(spaceShuttle)).willAnswer(invocation -> invocation.getArgument(0));
    SpaceShuttle savedSpaceShuttle = spaceShuttleServiceImpl.createSpaceShuttle(spaceShuttle);
    assertThat(savedSpaceShuttle).isNotNull();
    verify(spaceShuttleRepository).save(any(SpaceShuttle.class));
  }
  @Test
  public void shouldReturnFindAll() {
    List<SpaceShuttle> data = new ArrayList<>();
    data.add(new SpaceShuttle(true, "Danny", "Danny's first trip",
        BigDecimal.valueOf(3), null));
    data.add(new SpaceShuttle(true, "Danny", "Danny's first trip",
        BigDecimal.valueOf(3), null));
    data.add(new SpaceShuttle(true, "Danny", "Danny's first trip",
        BigDecimal.valueOf(3), null));
    given(spaceShuttleRepository.findAll()).willReturn(data);

    List<SpaceShuttle> expected = spaceShuttleServiceImpl.getSpaceShuttle();
    assertEquals(expected, data);
  }
  @Test
  public void sadShouldReturnFindAll(){
    List<SpaceShuttle> data = new ArrayList<>();
    data.add(new SpaceShuttle(true, "Danny", "Danny's first trip",
            BigDecimal.valueOf(3), null));
    data.add(new SpaceShuttle(true, "Danny", "Danny's first trip",
            BigDecimal.valueOf(3), null));
    data.add(new SpaceShuttle(true, "Danny", "Danny's first trip",
            BigDecimal.valueOf(3), null));
    when(spaceShuttleServiceImpl.getSpaceShuttle()).thenThrow(new DataAccessException("error"){});
    assertThrows(ServerError.class, ()-> spaceShuttleServiceImpl.getSpaceShuttle());
  }
  @Test
  public void sadPostSpaceShuttle(){
    final SpaceShuttle spaceShuttle = new SpaceShuttle(true, "Danny", "Danny's first trip",
            BigDecimal.valueOf(3), null);
    when(spaceShuttleServiceImpl.createSpaceShuttle(spaceShuttle)).thenThrow(new DataAccessException("broken") {
    });
    assertThrows(ServerError.class, ()-> spaceShuttleServiceImpl.createSpaceShuttle(spaceShuttle));
  }
}