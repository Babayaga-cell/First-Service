package com.lex.dockerfirstservice.client;

import com.lex.dockerfirstservice.config.property.SecondServiceProperty;
import com.lex.dockerfirstservice.dto.CalculatorDto;
import com.lex.dockerfirstservice.dto.ComputationDto;
import com.lex.dockerfirstservice.service.AbstractHttpService;
import com.lex.dockerfirstservice.service.impl.CalculateServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.ParameterizedTypeReference;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CalculateClientTest {

    @Mock
    private AbstractHttpService httpService;

    @Mock
    private SecondServiceProperty secondServiceProperty;

    @InjectMocks
    private CalculateClient calculateClient;

    private CalculatorDto calculatorDto;

    private ComputationDto computationDto;


    @BeforeEach
    void setUp() {

        calculatorDto = new CalculatorDto();
        calculatorDto.setProductName("sample");
        calculatorDto.setPrice(3.0);
        calculatorDto.setUnit(2.0);

        computationDto = new ComputationDto();
        computationDto.setProductName("sample");
        computationDto.setFinalPrice(6.0);

    }

    @Test
    void finalComputationResult() {
        String computeUrl = "http://example.com/compute";


        when(secondServiceProperty.getPostComputationUrl()).thenReturn(computeUrl);


        when(httpService.post(anyString(), any(), any(ParameterizedTypeReference.class)))
                .thenReturn(computationDto);


        ComputationDto response = calculateClient.finalComputationResult(calculatorDto);

        // Assertions
        assertNotNull(response);
        assertEquals(calculatorDto.getPrice()*calculatorDto.getUnit(), response.getFinalPrice());
    }

    @Test
    void finalComputationResultThrowError() {
        String computeUrl = "http://example.com/compute";

        when(secondServiceProperty.getPostComputationUrl()).thenReturn(computeUrl);

        when(httpService.post(anyString(), any(), any(ParameterizedTypeReference.class)))
                .thenThrow(new RuntimeException("Error in HTTP service"));

        // Assertions
        assertThrows(RuntimeException.class, () -> calculateClient.finalComputationResult(calculatorDto));
    }


}