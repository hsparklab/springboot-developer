package com.hsparklab.springbootdeveloper;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class QuizControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void mockMvcSetUp(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .build();
    }

    @DisplayName("GET /quiz?code=1 then 201," +
            "응답본문은 Create!")
    @Test
    public void getQuiz1() throws Exception{
        //given
        final String url = "/quiz";

        //when
        final ResultActions resultActions = mockMvc.perform(get(url)
                .param("code", "1")
        );

        //result
        resultActions
                .andExpect(status().isCreated())
                .andExpect(content().string("Created"));

    }
    @DisplayName("GET /quiz?code=2 then 400," +
            "응답본문은 Create!")
    @Test
    public void getQuiz2() throws Exception{
        //given
        final String url = "/quiz";

        //when
        final ResultActions resultActions = mockMvc.perform(get(url)
                .param("code", "2")
        );

        //result
        resultActions
                .andExpect(status().isBadRequest())
                .andExpect(content().string("bad Request!"));

    }
    @DisplayName("post /quiz?code=1 then 403," +
            "응답본문은 Forbidden")
    @Test
    public void postQuiz1() throws Exception{
        //given
        final String url = "/quiz";

        //when
        final ResultActions resultActions = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new QuizController.Code(1)))
                );

        //then
        resultActions
                .andExpect(status().isForbidden())
                .andExpect(content().string("Forbidden"));

    }

    @DisplayName("post /quiz?code=13 then 200," +
            "응답본문은 OK")
    @Test
    public void postQuiz13() throws Exception{
        //given
        final String url = "/quiz";

        //when
        final ResultActions resultActions = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new QuizController.Code(13)))
        );

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string("OK"));

    }

}