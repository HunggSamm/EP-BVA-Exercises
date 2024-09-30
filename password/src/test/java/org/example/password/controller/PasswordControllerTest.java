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
public class PasswordControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // Equivalence Partition: Valid Password (6-10 characters)
    @Test
    public void whenValidPassword_thenSuccessMessage() throws Exception {
        mockMvc.perform(post("/validatePassword")
                        .param("password", "abcdef"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Password is valid.")));
    }

    // Equivalence Partition: Invalid Password (less than 6 characters)
    @Test
    public void whenInvalidPasswordShort_thenErrorMessage() throws Exception {
        mockMvc.perform(post("/validatePassword")
                        .param("password", "abc"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Password must be between 6 and 10 characters.")));
    }

    // Equivalence Partition: Invalid Password (more than 10 characters)
    @Test
    public void whenInvalidPasswordLong_thenErrorMessage() throws Exception {
        mockMvc.perform(post("/validatePassword")
                        .param("password", "abcdefghijkl"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Password must be between 6 and 10 characters.")));
    }
}