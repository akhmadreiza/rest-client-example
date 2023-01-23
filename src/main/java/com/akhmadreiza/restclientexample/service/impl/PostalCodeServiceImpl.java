package com.akhmadreiza.restclientexample.service.impl;

import com.akhmadreiza.restclientexample.dto.postal.PostalData;
import com.akhmadreiza.restclientexample.service.PostalCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class PostalCodeServiceImpl implements PostalCodeService {

    @Autowired
    private RestTemplate restTemplateWithTimeOut;

    @Override
    public PostalData fetchPostalCodeDetail(String postalCode) {
        ResponseEntity<PostalData> resPostalData = restTemplateWithTimeOut.getForEntity("https://nbc.vanmason.web.id/service/kodepos/" + postalCode, PostalData.class);
        return resPostalData.getBody();
    }
}
