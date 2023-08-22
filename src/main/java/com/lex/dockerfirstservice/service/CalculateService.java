package com.lex.dockerfirstservice.service;

import com.lex.dockerfirstservice.dto.CalculatorDto;
import com.lex.dockerfirstservice.dto.ComputationDto;

public interface CalculateService {
    public ComputationDto getFinalComputation(CalculatorDto calculatorDto);
}
