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
public class PizzaOrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // Equivalence Partition: Valid Input (1-10)
    @Test
    public void whenValidPizzaQuantity_thenSuccessMessage() throws Exception {
        mockMvc.perform(post("/order")
                        .param("quantity", "5"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Order successful!")));
    }

    // Equivalence Partition: Invalid Input (0 or below)
    @Test
    public void whenInvalidPizzaQuantityLow_thenErrorMessage() throws Exception {
        mockMvc.perform(post("/order")
                        .param("quantity", "0"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Only 10 Pizza can be ordered")));
    }

    // Equivalence Partition: Invalid Input (11 or above)
    @Test
    public void whenInvalidPizzaQuantityHigh_thenErrorMessage() throws Exception {
        mockMvc.perform(post("/order")
                        .param("quantity", "11"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Only 10 Pizza can be ordered")));
    }
}