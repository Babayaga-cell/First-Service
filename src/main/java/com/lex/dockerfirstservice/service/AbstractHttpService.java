package com.lex.dockerfirstservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;


@Slf4j
public abstract class AbstractHttpService {
    private final RestTemplate restTemplate;

    protected AbstractHttpService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public <U> U get(String url, HttpEntity httpEntity, ParameterizedTypeReference<U> responseType, Object... uriVariables){
        return exchange(url, HttpMethod.GET, httpEntity, responseType, uriVariables);
    }

    public <U> U post(String url, HttpEntity httpEntity, ParameterizedTypeReference<U> responseType, Object... uriVariables){
        return exchange(url, HttpMethod.POST, httpEntity, responseType, uriVariables);
    }


    private <U> U exchange(String url, HttpMethod httpMethod, HttpEntity<?> requestEntity, ParameterizedTypeReference<U> responseType , Object... uriVariables){
        return restTemplate.exchange(url,
                httpMethod,
                requestEntity,
                responseType,
                uriVariables).getBody();
    }

}
