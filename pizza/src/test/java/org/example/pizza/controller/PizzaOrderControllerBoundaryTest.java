package org.example.pizza.controller;
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
public class PizzaOrderControllerBoundaryTest {

    @Autowired
    private MockMvc mockMvc;

    // Boundary Value: Lower Boundary (1)
    @Test
    public void whenBoundaryPizzaQuantityOne_thenSuccessMessage() throws Exception {
        mockMvc.perform(post("/order")
                        .param("quantity", "1"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Order successful!")));
    }

    // Boundary Value: Upper Boundary (10)
    @Test
    public void whenBoundaryPizzaQuantityTen_thenSuccessMessage() throws Exception {
        mockMvc.perform(post("/order")
                        .param("quantity", "10"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Order successful!")));
    }

    // Boundary Value: Just Below Lower Boundary (0)
    @Test
    public void whenBoundaryPizzaQuantityZero_thenErrorMessage() throws Exception {
        mockMvc.perform(post("/order")
                        .param("quantity", "0"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Only 10 Pizza can be ordered")));
    }

    // Boundary Value: Just Above Upper Boundary (11)
    @Test
    public void whenBoundaryPizzaQuantityEleven_thenErrorMessage() throws Exception {
        mockMvc.perform(post("/order")
                        .param("quantity", "11"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Only 10 Pizza can be ordered")));
    }
}