package com.example.Space.domains.SpaceShuttles;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = SpaceShuttleController.class)
@ActiveProfiles("test")
public class SpaceShuttleControllerTest {

    @Autowired
    private WebApplicationContext wac;
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SpaceShuttleServiceImpl spaceShuttleService;
    @Autowired
    private ObjectMapper objectMapper;

    private List<SpaceShuttle> spaceShuttle;

    SpaceShuttle spaceShuttle1;


    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        this.spaceShuttle = new ArrayList<>();
        this.spaceShuttle.add(new SpaceShuttle(true, "Danny", "Danny's first trip",
                BigDecimal.valueOf(3), null));
        this.spaceShuttle.add(new SpaceShuttle(true, "Danny", "Danny's first trip",
                BigDecimal.valueOf(3), null));
        this.spaceShuttle.add(new SpaceShuttle(true, "Danny", "Danny's first trip",
                BigDecimal.valueOf(3), null));
    }


    //  @Test
//  public void saveSpaceShuttle() throws Exception {
//    ObjectMapper mapper = new ObjectMapper();
//    SpaceShuttle spaceShuttle = new SpaceShuttle(true, "Danny", "Danny's first trip",
//        BigDecimal.valueOf(3), null);
//
//    String myClassAsJsonString = mapper.writeValueAsString(spaceShuttle);
//    System.out.println(myClassAsJsonString);
//    MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
//            .post("/SpaceShuttle")
//            .content(myClassAsJsonString)
//            .contentType(MediaType.APPLICATION_JSON)
//            .accept(MediaType.APPLICATION_JSON))
//        .andReturn();
//    int status = mvcResult.getResponse().getStatus();
//    assertEquals(201, status);
//  }
//  @Test
//  public void getSpaceShuttles() throws Exception {
//    String uri = "/SpaceShuttle";
//    MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
//        .get(uri)
//        .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
//
//    int status = mvcResult.getResponse().getStatus();
//    assertEquals(200, status);
//  }
    @Test
    public void getAllSpaceShuttle() throws Exception {
        given(spaceShuttleService.getSpaceShuttle()).willReturn(spaceShuttle);
        this.mockMvc.perform(get("/SpaceShuttle"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.size()", is(spaceShuttle.size())));
    }

    @Test
    public void createNewSpaceShuttle() throws Exception {
        given(spaceShuttleService.createSpaceShuttle(any(SpaceShuttle.class))).willAnswer((invocation) -> invocation.getArgument(0));
        SpaceShuttle spaceShuttle = new SpaceShuttle(true, "Danny", "Danny's first trip",
                BigDecimal.valueOf(3), null);
        this.mockMvc.perform(post("/SpaceShuttle")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(spaceShuttle)))
                .andExpect(status().isCreated());
    }
}