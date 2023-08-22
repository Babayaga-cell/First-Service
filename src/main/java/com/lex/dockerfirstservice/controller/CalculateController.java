package com.lex.dockerfirstservice.controller;

import com.lex.dockerfirstservice.dto.CalculatorDto;
import com.lex.dockerfirstservice.dto.ComputationDto;
import com.lex.dockerfirstservice.service.CalculateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("first/calculate")
public class CalculateController {

    private final CalculateService calculateService;

    @Autowired
    public CalculateController(CalculateService calculateService){
        this.calculateService = calculateService;
    }

    @PostMapping("/finalComputation")
    public ComputationDto computeFinalPrice(@RequestBody CalculatorDto calculatorDto){
        return calculateService.getFinalComputation(calculatorDto);
    }

}
