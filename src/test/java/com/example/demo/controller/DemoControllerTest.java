package com.example.demo.controller;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Skeleton template for a controller test using MockMvc.
 *
 * You can use annotations from JUnit 5 such as @ParameterizedTest, @ValueSauce,
 * @CsvSource and @MethodSource for your test data.
 *
 * Example usage of mockMvc for a GET request
 * mockMvc.perform(get("/path-to-your-endpoint").param("your-query-param", param-value))
 *                 .andExpect(status().whateverStatusCodeYouExpect())
 *                 .andExpect(content().string("string-you-expect-in-response")).
 *                 .andExpect(jsonPath("$.jsonField").value("json-value-you-expect"));
 */
@SpringBootTest
@AutoConfigureMockMvc
class DemoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testRemoveFirstAndLastCharacter() throws Exception {
        mockMvc.perform(get("/remove").param("input", "eloquent"))
                .andExpect(status().isOk())
                .andExpect(content().string("loquen"));

        mockMvc.perform(get("/remove").param("input", "country"))
                .andExpect(status().isOk())
                .andExpect(content().string("ountr"));

        mockMvc.perform(get("/remove").param("input", "person"))
                .andExpect(status().isOk())
                .andExpect(content().string("erso"));

        mockMvc.perform(get("/remove").param("input", "xyz"))
                .andExpect(status().isOk())
                .andExpect(content().string("y"));
    }

    @Test
    public void test2Characters() throws Exception {
        mockMvc.perform(get("/remove").param("input", "ab"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    public void test1Character() throws Exception {
        mockMvc.perform(get("/remove").param("input", "a"))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void testEmptyString() throws Exception {
        mockMvc.perform(get("/remove").param("input", ""))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testSpecialCharacters() throws Exception {
        mockMvc.perform(get("/remove").param("input", "*123*%qwerty+"))
                .andExpect(status().isOk())
                .andExpect(content().string(is("123_%qwerty")));
    }
}
