package com.akhmadreiza.restclientexample.dto.postal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostalDetail {
    private String kelurahan;
    private String kecamatan;
    private String jenis;
    private String kabupaten;
    private String provinsi;
}
