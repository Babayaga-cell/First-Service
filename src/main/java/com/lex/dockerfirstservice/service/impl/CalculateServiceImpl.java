package com.lex.dockerfirstservice.service.impl;

import com.lex.dockerfirstservice.client.CalculateClient;
import com.lex.dockerfirstservice.dto.CalculatorDto;
import com.lex.dockerfirstservice.dto.ComputationDto;
import com.lex.dockerfirstservice.service.CalculateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalculateServiceImpl implements CalculateService {

    private final CalculateClient client;

    @Autowired
    public CalculateServiceImpl(CalculateClient client){
        this.client = client;
    }

    @Override
    public ComputationDto getFinalComputation(CalculatorDto calculatorDto) {
        return client.finalComputationResult(calculatorDto);
    }
}
