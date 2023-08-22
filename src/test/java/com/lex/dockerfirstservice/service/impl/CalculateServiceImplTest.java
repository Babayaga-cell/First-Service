package com.lex.dockerfirstservice.service.impl;

import com.lex.dockerfirstservice.client.CalculateClient;
import com.lex.dockerfirstservice.dto.CalculatorDto;
import com.lex.dockerfirstservice.dto.ComputationDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CalculateServiceImplTest {

    @Mock
    private CalculateClient calculateClient;

    private CalculateServiceImpl calculateService;

    private CalculatorDto calculatorDto;

    private ComputationDto computationDto;

    @BeforeEach
    void setUp() {
        calculateService = new CalculateServiceImpl(calculateClient);

        calculatorDto = new CalculatorDto();
        calculatorDto.setProductName("sample");
        calculatorDto.setPrice(3.0);
        calculatorDto.setUnit(2.0);

        computationDto = new ComputationDto();
        computationDto.setProductName("sample");
        computationDto.setFinalPrice(6.0);

    }

    @Test
    void getFinalComputation() {
        when(calculateClient.finalComputationResult(any(CalculatorDto.class))).thenReturn(computationDto);

        // Call the method under test
        ComputationDto response = calculateService.getFinalComputation(calculatorDto);

        assertNotNull(response);
        assertEquals(calculatorDto.getPrice()*calculatorDto.getUnit(), response.getFinalPrice());
        verify(calculateClient, times(1)).finalComputationResult(any(CalculatorDto.class));
    }

    @Test
    void getFinalComputationIsError() {

        when(calculateClient.finalComputationResult(any(CalculatorDto.class))).thenThrow(new RuntimeException("Something went wrong"));

        // Assertions
        assertThrows(RuntimeException.class, () -> calculateService.getFinalComputation(calculatorDto));
        verify(calculateClient, times(1)).finalComputationResult(any(CalculatorDto.class));
    }




}