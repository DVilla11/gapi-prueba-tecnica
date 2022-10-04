package com.autentia.gapi.controllerTests;

import com.autentia.gapi.controller.UserController;
import com.autentia.gapi.domain.GapiUser;
import com.autentia.gapi.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;


import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    ObjectMapper objectMapper;

    @BeforeEach
    void configuration(){
        objectMapper = new ObjectMapper();
    }

    @Test
    void testSaveUser() throws Exception {
        GapiUser user = new GapiUser("dvilla", "123", "villa@gmail.com");
        when(userService.save(any())).then(invocation -> {
           GapiUser u = invocation.getArgument(0);
           return u;
        });

        mockMvc.perform(post("/api/v1/user").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect((ResultMatcher) jsonPath("$.userName", is("dvilla")))
                .andExpect((ResultMatcher) jsonPath("$.password", is("123")))
                .andExpect((ResultMatcher) jsonPath("$.email", is("villa@gmail.com")));

        verify(userService).save(any());

    }


}
