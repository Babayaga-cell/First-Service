package com.lex.dockerfirstservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lex.dockerfirstservice.dto.CalculatorDto;
import com.lex.dockerfirstservice.dto.ComputationDto;
import com.lex.dockerfirstservice.service.CalculateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CalculateControllerTest {

    private MockMvc mockMvc;

    private CalculateService calculateService;

    private CalculatorDto calculatorDto;

    private ComputationDto computationDto;

    private String requestBody;

    @BeforeEach
    void setUp() throws Exception{
        calculateService = mock(CalculateService.class);
        CalculateController calculateController = new CalculateController(calculateService);
        mockMvc = MockMvcBuilders.standaloneSetup(calculateController).build();

        calculatorDto = new CalculatorDto();
        calculatorDto.setProductName("sample");
        calculatorDto.setPrice(3.0);
        calculatorDto.setUnit(2.0);

        computationDto = new ComputationDto();
        computationDto.setProductName("sample");
        computationDto.setFinalPrice(6.0);

        ObjectMapper objectMapper = new ObjectMapper();
        requestBody = objectMapper.writeValueAsString(calculatorDto);

    }
    @Test
    void computeFinalPrice() throws Exception{
        when(calculateService.getFinalComputation(any(CalculatorDto.class))).thenReturn(computationDto);

        // Perform the HTTP POST request
        mockMvc.perform(post("/first/calculate/finalComputation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.productName").value("sample"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.finalPrice").value("6.0"));

        // Verify that the service method was called
        verify(calculateService, times(1)).getFinalComputation(any(CalculatorDto.class));

    }

    @Test
    void computeFinalPriceIsBadRequest() throws Exception{
        when(calculateService.getFinalComputation(any(CalculatorDto.class))).thenThrow(new RuntimeException("Test Exception"));

        // Perform the HTTP POST request
        mockMvc.perform(post("/first/calculate/finalComputation")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        // Verify that the service method was called
        verify(calculateService, times(0)).getFinalComputation(any(CalculatorDto.class));

    }
}