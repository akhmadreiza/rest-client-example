package com.akhmadreiza.restclientexample;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.net.SocketTimeoutException;

@SpringBootTest
@Slf4j
public class TestTimeout {
    @Autowired
    @Qualifier("restTemplateWithTimeOut")
    private RestTemplate restTemplate;

    @Test
    void testHitSlowAPIConnectTimeOut() {
        try {
            log.info("test connect timeout begin");
            restTemplate.getForEntity("https://hub.dummyapis.com:81/delay?seconds=1", Object.class);
        } catch (Exception e) {
            log.info("test connect timeout end");
            Assertions.assertTrue(e instanceof ResourceAccessException);
            Assertions.assertTrue(e.getCause() instanceof SocketTimeoutException);
            Assertions.assertEquals("connect timed out", e.getCause().getMessage());
        }
    }

    @Test
    void testHitSlowAPIReadTimeOut() {
        try {
            log.info("test read timeout begin");
            restTemplate.getForEntity("https://hub.dummyapis.com/delay?seconds=7", Object.class);
        } catch (Exception e) {
            log.info("test read timeout end");
            Assertions.assertTrue(e instanceof ResourceAccessException);
            Assertions.assertTrue(e.getCause() instanceof SocketTimeoutException);
            Assertions.assertEquals("Read timed out", e.getCause().getMessage());
        }
    }
}
