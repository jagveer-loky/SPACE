package com.example.Space.domains.SpaceShuttles;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ApiTest {

  @Autowired
  private WebApplicationContext wac;

  private MockMvc mockMvc;

  @Before
  public void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
  }


  @Test
  public void getAllSpaceShuttle() throws Exception {
    mockMvc.perform(get("/SpaceShuttle"))
        .andExpect(status().isOk());
  }


  @Test
  public void shouldFetchSpaceShuttleByID() throws Exception {

    this.mockMvc.perform(get("/SpaceShuttle/1"))
        .andExpect(status().isOk());
  }

  @Test
  public void shouldDeleteSpaceShuttle() throws Exception {

    mockMvc.perform(delete("/SpaceShuttle/3"))
        .andExpect(status().isNoContent());
  }

  @Test
  public void shouldUpdateSpaceShuttle() throws Exception {
    ObjectMapper objectMapper = new ObjectMapper();
    SpaceShuttle spaceShuttle = new SpaceShuttle(true, "Danny", "Danny's first trip",
        BigDecimal.valueOf(5), null);
    final Long spaceid = 2L;
    spaceShuttle.setId(spaceid);
    mockMvc.perform(put("/SpaceShuttle")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(objectMapper.writeValueAsString(spaceShuttle)))
        .andExpect(status().isOk());
  }

  @Test
  public void createNewSpaceShuttle() throws Exception {
    ObjectMapper objectMapper = new ObjectMapper();
    SpaceShuttle spaceShuttle = new SpaceShuttle(true, "Danny", "Danny's first trip",
        BigDecimal.valueOf(5), null);
    mockMvc.perform(post("/SpaceShuttle")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(objectMapper.writeValueAsString(spaceShuttle)))
        .andExpect(status().isCreated());
  }
}
