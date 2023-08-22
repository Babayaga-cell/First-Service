package com.lex.dockerfirstservice.service.impl;

import com.lex.dockerfirstservice.service.AbstractHttpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service("NormalHttpService")
public class NormalWebServiceImpl extends AbstractHttpService {
    @Autowired
    public NormalWebServiceImpl(RestTemplate restTemplate){super(restTemplate);}
}
