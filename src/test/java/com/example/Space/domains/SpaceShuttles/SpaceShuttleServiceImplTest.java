package com.example.Space.domains.SpaceShuttles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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

    List<SpaceShuttle> expected = spaceShuttleServiceImpl.getSpaceShuttle(spaceShuttle);
    assertEquals(expected, data);

  }
}