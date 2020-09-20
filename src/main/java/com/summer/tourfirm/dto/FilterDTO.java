package com.summer.tourfirm.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZonedDateTime;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

public class FilterDTO {

    @JsonInclude(NON_EMPTY)
    @JsonProperty("country")
    private String country;

    @JsonInclude(NON_EMPTY)
    @JsonProperty("city")
    private String city;

    @JsonInclude(NON_NULL)
    @JsonProperty("dateIn")
    private ZonedDateTime dataIn;

    @JsonInclude(NON_NULL)
    @JsonProperty("dateOut")
    private ZonedDateTime dataOut;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public ZonedDateTime getDataIn() {
        return dataIn;
    }

    public void setDataIn(ZonedDateTime dataIn) {
        this.dataIn = dataIn;
    }

    public ZonedDateTime getDataOut() {
        return dataOut;
    }

    public void setDataOut(ZonedDateTime dataOut) {
        this.dataOut = dataOut;
    }
}
