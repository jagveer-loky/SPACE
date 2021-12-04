package com.example.Space.domains.SpaceShuttles;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SpaceShuttleControllerTest {

  @Autowired
  private WebApplicationContext wac;
  private MockMvc mockMvc;
  @Autowired
  private SpaceShuttleRepository spaceShuttleRepository;

  @Before
  public void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
  }



  @Test
  public void saveSpaceShuttle() throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    SpaceShuttle spaceShuttle = new SpaceShuttle(true, "Danny", "Danny's first trip",
        BigDecimal.valueOf(3), null);

    String myClassAsJsonString = mapper.writeValueAsString(spaceShuttle);
    System.out.println(myClassAsJsonString);
    MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
            .post("/SpaceShuttle")
            .content(myClassAsJsonString)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
        .andReturn();
    int status = mvcResult.getResponse().getStatus();
    assertEquals(201, status);
  }
  @Test
  public void getSpaceShuttles() throws Exception {
    String uri = "/SpaceShuttle";
    MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
        .get(uri)
        .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

    int status = mvcResult.getResponse().getStatus();
    assertEquals(200, status);
  }
}