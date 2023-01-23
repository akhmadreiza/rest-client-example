package com.akhmadreiza.restclientexample.service;

import com.akhmadreiza.restclientexample.dto.postal.PostalData;

public interface PostalCodeService {
    PostalData fetchPostalCodeDetail(String postalCode);
}
