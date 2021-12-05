package com.example.Space.domains.SpaceShuttles;

import com.example.Space.exceptions.ResourceNotFound;
import com.example.Space.exceptions.ServerError;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
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
        spaceShuttle.setId(1L);
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
    public void sadShouldReturnFindAll() {
        List<SpaceShuttle> data = new ArrayList<>();
        data.add(new SpaceShuttle(true, "Danny", "Danny's first trip",
                BigDecimal.valueOf(3), null));
        data.add(new SpaceShuttle(true, "Danny", "Danny's first trip",
                BigDecimal.valueOf(3), null));
        data.add(new SpaceShuttle(true, "Danny", "Danny's first trip",
                BigDecimal.valueOf(3), null));
        when(spaceShuttleServiceImpl.getSpaceShuttle()).thenThrow(new DataAccessException("error") {
        });
        assertThrows(ServerError.class, () -> spaceShuttleServiceImpl.getSpaceShuttle());
    }

    @Test
    public void sadPostSpaceShuttle() {
        final SpaceShuttle spaceShuttle = new SpaceShuttle(true, "Danny", "Danny's first trip",
                BigDecimal.valueOf(3), null);
        when(spaceShuttleServiceImpl.createSpaceShuttle(spaceShuttle)).thenThrow(new DataAccessException("broken") {
        });
        assertThrows(ServerError.class, () -> spaceShuttleServiceImpl.createSpaceShuttle(spaceShuttle));
    }

    @Test
    public void findSpaceShuttleByIdHappy() {
        final Long id = 3L;
        final SpaceShuttle spaceShuttle = new SpaceShuttle(true, "Danny", "Danny's first trip",
                BigDecimal.valueOf(3), null);
        spaceShuttle.setId(3L);
        given(spaceShuttleRepository.findById(id)).willReturn(Optional.of(spaceShuttle));
        final Optional<SpaceShuttle> expected = Optional.ofNullable(spaceShuttleServiceImpl.getSpaceShuttleById(id));
        assertThat(expected).isNotNull();
    }

    @Test
    public void sadFindSpaceShuttleById() {
        final Long id = 3L;
        final SpaceShuttle spaceShuttle = new SpaceShuttle(true, "Danny", "Danny's first trip",
                BigDecimal.valueOf(3), null);
        spaceShuttle.setId(3L);
        when(spaceShuttleRepository.findById(3L)).thenThrow(new DataAccessException("it broke") {
        });
        assertThrows(ServerError.class, () -> spaceShuttleServiceImpl.getSpaceShuttleById(3L));
    }

    @Test
    public void sadderFindSpaceShuttleById() {
        final Long id = 3L;
        final SpaceShuttle spaceShuttle = new SpaceShuttle(true, "Danny", "Danny's first trip",
                BigDecimal.valueOf(3), null);
        spaceShuttle.setId(3L);
        when(spaceShuttleRepository.findById(3L)).thenThrow(new DataAccessException("it broke") {
        });
        assertThrows(ResourceNotFound.class, () -> spaceShuttleServiceImpl.getSpaceShuttleById(null));
    }

    @Test
    public void happyShouldDeleteShuttle() {
        final Long spaceid = 1L;
        when(spaceShuttleRepository.findById(spaceShuttle.getId())).thenReturn(Optional.of(spaceShuttle));
        spaceShuttleServiceImpl.deleteSpaceShuttle(spaceid);
        verify(spaceShuttleRepository, times(1)).deleteById(spaceid);
    }

    @Test
    public void sadShouldDeleteShuttle() {
        final Long spaceid = 1L;
        when(spaceShuttleRepository.findById(spaceShuttle.getId())).thenThrow(new DataAccessException("broken") {
        });
        assertThrows(ServerError.class, () -> spaceShuttleServiceImpl.deleteSpaceShuttle(spaceid));
    }

    @Test
    public void sadderShouldDeleteShuttle() {
        final Long spaceid = 1L;
        when(spaceShuttleRepository.findById(spaceShuttle.getId())).thenReturn(Optional.of(spaceShuttle));
        spaceShuttleServiceImpl.deleteSpaceShuttle(spaceid);
        assertThrows(ResourceNotFound.class, () -> spaceShuttleServiceImpl.deleteSpaceShuttle(2L));
    }

    @Test
    public void updateUser() {
        SpaceShuttle testSpaceShuttle = new SpaceShuttle(true, "Danny", "Danny's first trip",
                BigDecimal.valueOf(3), null);
        testSpaceShuttle.setId(2L);
        SpaceShuttle saveSpaceShuttle = new SpaceShuttle(true, "Danny", "Danny's first trip",
                BigDecimal.valueOf(3), null);
        saveSpaceShuttle.setId(2L);
        when(spaceShuttleRepository.findById(2L)).thenReturn(Optional.of(testSpaceShuttle));
        when(spaceShuttleRepository.save(testSpaceShuttle)).thenReturn(saveSpaceShuttle);
        SpaceShuttle actual = spaceShuttleServiceImpl.updateSpaceShuttle(testSpaceShuttle);
        assertEquals(actual, saveSpaceShuttle);
    }

    @Test
    public void sadupdateUser() {
        SpaceShuttle testSpaceShuttle = new SpaceShuttle(true, "Danny", "Danny's first trip",
                BigDecimal.valueOf(3), null);
        testSpaceShuttle.setId(2L);
        SpaceShuttle saveSpaceShuttle = new SpaceShuttle(true, "Danny", "Danny's first trip",
                BigDecimal.valueOf(3), null);
        saveSpaceShuttle.setId(2L);
        when(spaceShuttleRepository.findById(2L)).thenReturn(Optional.of(testSpaceShuttle));
        when(spaceShuttleRepository.save(testSpaceShuttle)).thenReturn(saveSpaceShuttle);
        when(spaceShuttleServiceImpl.updateSpaceShuttle(testSpaceShuttle)).thenThrow(new DataAccessException("wow") {
        });
        assertThrows(ServerError.class, () -> spaceShuttleServiceImpl.updateSpaceShuttle(testSpaceShuttle));
    }

}