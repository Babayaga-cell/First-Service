package com.lex.dockerfirstservice.client;

import com.lex.dockerfirstservice.config.property.SecondServiceProperty;
import com.lex.dockerfirstservice.dto.CalculatorDto;
import com.lex.dockerfirstservice.dto.ComputationDto;
import com.lex.dockerfirstservice.service.AbstractHttpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class CalculateClient {
    private final AbstractHttpService httpService;
    private final SecondServiceProperty secondServiceProperty;

    @Autowired
    public CalculateClient(@Qualifier("NormalHttpService") AbstractHttpService abstractHttpService, SecondServiceProperty repositoryProperty) {
        this.httpService = abstractHttpService;
        this.secondServiceProperty = repositoryProperty;
    }

    public ComputationDto finalComputationResult(CalculatorDto calculatorDto) {
        try {
            String uri = UriComponentsBuilder.fromUriString(secondServiceProperty.getPostComputationUrl())
                    .toUriString();
            HttpEntity<CalculatorDto> requestEntity = new HttpEntity<>(calculatorDto, null);

            ComputationDto response = httpService.post(uri, requestEntity, new ParameterizedTypeReference<ComputationDto>() {
            });

            return response;
        } catch (Exception e) {
            throw e;
        }
    }

}
