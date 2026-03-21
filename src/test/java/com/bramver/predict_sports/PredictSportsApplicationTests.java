package com.bramver.predict_sports;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class PredictSportsApplicationTests {

    @Autowired
    private MockMvc mockMvc;

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


}
