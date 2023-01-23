package com.akhmadreiza.restclientexample;

import com.akhmadreiza.restclientexample.dto.postal.PostalData;
import com.akhmadreiza.restclientexample.dto.postal.PostalDetail;
import com.akhmadreiza.restclientexample.service.PostalCodeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Slf4j
class RestClientExampleApplicationTests {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PostalCodeService postalCodeService;

    @Test
    void contextLoads() {
    }

    @Test
    void testPrintDto() throws JsonProcessingException {
        PostalDetail postalDetail = PostalDetail.builder()
                .kelurahan("Sawah")
                .kecamatan("Ciputat")
                .jenis("Kota")
                .kabupaten("Tangerang Selatan")
                .provinsi("Banten")
                .build();
        List<PostalDetail> postalDetailList = new ArrayList<>();
        postalDetailList.add(postalDetail);

        PostalData postalData = new PostalData();
        postalData.setKodepos(postalDetailList);

        System.out.println(objectMapper.writeValueAsString(postalData));
    }

    @Test
    void testHitAPI() throws JsonProcessingException {
        //prepare data
        String inquiryPostalCode = "15413";

        //do test
        PostalData postalData = postalCodeService.fetchPostalCodeDetail(inquiryPostalCode);
        System.out.println(objectMapper.writeValueAsString(postalData));

        //assert
        List<PostalDetail> postalDetailList = postalData.getKodepos();
        Assertions.assertEquals(2, postalDetailList.size());
        Assertions.assertEquals("Sawah Baru", postalDetailList.get(0).getKelurahan());
        Assertions.assertEquals("Sawah Lama", postalDetailList.get(1).getKelurahan());
    }
}
