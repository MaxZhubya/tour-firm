package com.summer.tourfirm.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.summer.tourfirm.entity.Country;
import com.summer.tourfirm.entity.enums.EntranceWay;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonPropertyOrder({"id", "isAbleForEntering", "cities", "enterWays"})
public class CountryDTO {

    @JsonProperty("id")
    private Long id;

    @JsonInclude(NON_NULL)
    @JsonProperty("isAbleForEntering")
    private Boolean isAbleForEntering;

    @JsonInclude(NON_EMPTY)
    @JsonProperty("cities")
    private List<ResortCityDTO> cities = new ArrayList<>();

    @JsonInclude(NON_EMPTY)
    @JsonProperty("enterWays")
    private List<EntranceWay> enterWays = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public CountryDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public Boolean getAbleForEntering() {
        return isAbleForEntering;
    }

    public CountryDTO setAbleForEntering(Boolean ableForEntering) {
        isAbleForEntering = ableForEntering;
        return this;
    }

    public List<ResortCityDTO> getCities() {
        return cities;
    }

    public CountryDTO setCities(List<ResortCityDTO> cities) {
        this.cities = cities;
        return this;
    }

    public List<EntranceWay> getEnterWays() {
        return enterWays;
    }

    public CountryDTO setEnterWays(List<EntranceWay> enterWays) {
        this.enterWays = enterWays;
        return this;
    }

    public static CountryDTO makeDTO(Country country) {
        return new CountryDTO()
                .setId(country.getId())
                .setAbleForEntering(country.isAbleForEntering())

                // Массив enum
                .setEnterWays(country.getEnterWays())

                .setCities(country.getCities().stream()
                    .map(ResortCityDTO::makeSimpleDTO).collect(Collectors.toList()));
    }

    public static CountryDTO makeSimpleDTO(Country country) {
        return (country != null) ? new CountryDTO()
                .setId(country.getId())
                .setAbleForEntering(country.isAbleForEntering())
                .setEnterWays(country.getEnterWays()) : null;
    }

}
