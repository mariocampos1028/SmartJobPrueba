package com.prueba.tecnica.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Telefono {

    @JsonProperty("number")
    private String number;

    @JsonProperty("citycode")
    private String cityCode;

    @JsonProperty("contrycode")
    private String countryCode;
}
