package com.akhmadreiza.restclientexample.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class DummySlowController {

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/api/slow")
    public void slowEndpoint() throws InterruptedException {
        log.info("slowEndpoint invoked");
        Thread.sleep(20000);
        log.info("slowEndpoint done");
    }
}
