package com.bramver.predict_sports;

import com.bramver.predict_sports.model.Matchday;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class PredictSportsApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private final int mockDatabaseSize = 3;


    @Test
    @WithMockUser
    void shouldReturnOkStatusWhenMatchdayIsRequested() throws Exception {
        mockMvc.perform(get("/api/matchday/13"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void shouldReturnMatchdayWhenRequested() throws Exception {
        mockMvc.perform(get("/api/matchday/13"))
                .andExpect(jsonPath("$.id").exists())
				.andExpect(jsonPath("$.id").value(notNullValue()))
				.andExpect(jsonPath("$.id").value(13));
    }

    @Test
    @WithMockUser
    void shouldNotReturnMatchdayWhenIdIsUnknown() throws Exception {
        mockMvc.perform(get("/api/matchday/999"))
                .andExpect(status().isNotFound());
        mockMvc.perform(get("/api/matchday/-1"))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser
    void shouldReturnAllMatchdaysWhenListIsRequested() throws Exception {
        String responseString = mockMvc.perform(get("/api/matchday"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(this.mockDatabaseSize))
                .andReturn()
                .getResponse()
                .getContentAsString();

       List<String> responseSeasonList = JsonPath.parse(responseString).read("$..season");
        assertThat(responseSeasonList).containsExactlyInAnyOrder("2025/2026", "2025/2026", "2026/2027");
    }

    @Test
    @WithMockUser
    void shouldReturnAPageOfMatchdays() throws Exception {
        int testSize = 1;
        String responseString = mockMvc.perform(get("/api/matchday?page=0&size=" + testSize))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        List<String> responseMatchdayList = JsonPath.parse(responseString).read("$[*]");
        assertThat(responseMatchdayList.size()).isEqualTo(testSize);

    }


    @Test
    @WithMockUser
    void shouldCreateANewMatchday() throws Exception {
        String inputSeason = "2025/2026";
        Matchday matchday = new Matchday(inputSeason);

        String location = mockMvc.perform(post("/api/matchday")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(matchday)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getHeader("Location");

        assertThat(location).isNotNull();

        String getResponseBody = mockMvc.perform(get(location))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        DocumentContext documentContext = JsonPath.parse(getResponseBody);
        Number id = documentContext.read("$.id");
        String getSeason = documentContext.read("$.season");
        assertThat(id).isNotNull();
        assertThat(getSeason).isEqualTo(inputSeason);
    }
}
