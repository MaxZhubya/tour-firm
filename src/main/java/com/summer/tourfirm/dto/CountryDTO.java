package com.summer.tourfirm.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.summer.tourfirm.entity.Country;
import com.summer.tourfirm.entity.types.EntranceType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonPropertyOrder({"id", "isAbleForEntering", "name", "cities", "enterTypes"})
public class CountryDTO {

    @JsonProperty("id")
    private Long id;

    @JsonInclude(NON_NULL)
    @JsonProperty("isAbleForEntering")
    private Boolean isAbleForEntering;

    @JsonInclude(NON_EMPTY)
    @JsonProperty("name")
    private String name;

    @JsonInclude(NON_NULL)
    @JsonProperty("cities")
    private List<ResortCityDTO> cities = new ArrayList<>();

    @JsonInclude(NON_NULL)
    @JsonProperty("enterTypes")
    private List<EntranceTypeDTO> enterTypes = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public CountryDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public Boolean getIsAbleForEntering() {
        return isAbleForEntering;
    }

    public CountryDTO setIsAbleForEntering(Boolean isAbleForEntering) {
        this.isAbleForEntering = isAbleForEntering;
        return this;
    }

    public String getName() {
        return name;
    }

    public CountryDTO setName(String name) {
        this.name = name;
        return this;
    }

    public List<ResortCityDTO> getCities() {
        return cities;
    }

    public CountryDTO setCities(List<ResortCityDTO> cities) {
        this.cities = cities;
        return this;
    }

    public List<EntranceTypeDTO> getEnterTypes() {
        return enterTypes;
    }

    public CountryDTO setEnterTypes(List<EntranceTypeDTO> enterTypes) {
        this.enterTypes = enterTypes;
        return this;
    }

    public static CountryDTO makeDTO(Country country) {
        return new CountryDTO()
                .setId(country.getId())
                .setIsAbleForEntering(country.isAbleForEntering())
                .setName(country.getName())

                // Массив enum
                .setEnterTypes(country.getEnterTypes().stream()
                    .map(EntranceTypeDTO::makeSimpleDTO).collect(Collectors.toList()))

                .setCities(country.getCities().stream()
                    .map(ResortCityDTO::makeSimpleDTO).collect(Collectors.toList()));
    }

    public static CountryDTO makeSimpleDTO(Country country) {
        return (country != null) ? new CountryDTO()
                .setId(country.getId())
                .setIsAbleForEntering(country.isAbleForEntering())
                .setName(country.getName())
                .setEnterTypes(country.getEnterTypes().stream()
                        .map(EntranceTypeDTO::makeSimpleDTO).collect(Collectors.toList())) : null;
    }

}
