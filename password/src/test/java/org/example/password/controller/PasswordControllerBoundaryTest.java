package org.example.password.controller;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PasswordControllerBoundaryTest {

    @Autowired
    private MockMvc mockMvc;

    // Boundary Value: Just Below Lower Boundary (5 characters)
    @Test
    public void whenBoundaryPasswordFiveCharacters_thenErrorMessage() throws Exception {
        mockMvc.perform(post("/validatePassword")
                        .param("password", "abcde"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Password must be between 6 and 10 characters.")));
    }

    // Boundary Value: Lower Boundary (6 characters)
    @Test
    public void whenBoundaryPasswordSixCharacters_thenSuccessMessage() throws Exception {
        mockMvc.perform(post("/validatePassword")
                        .param("password", "abcdef"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Password is valid.")));
    }

    // Boundary Value: Upper Boundary (10 characters)
    @Test
    public void whenBoundaryPasswordTenCharacters_thenSuccessMessage() throws Exception {
        mockMvc.perform(post("/validatePassword")
                        .param("password", "abcdefghij"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Password is valid.")));
    }

    // Boundary Value: Just Above Upper Boundary (11 characters)
    @Test
    public void whenBoundaryPasswordElevenCharacters_thenErrorMessage() throws Exception {
        mockMvc.perform(post("/validatePassword")
                        .param("password", "abcdefghijk"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Password must be between 6 and 10 characters.")));
    }
}