package com.summer.tourfirm.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.summer.tourfirm.entity.ResortCity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonPropertyOrder({"id", "isAbleForEntering", "name", "enterTypes", "travelingTypes", "country", "areas"})
public class ResortCityDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonInclude(NON_NULL)
    @JsonProperty("country")
    private CountryDTO country;

    @JsonInclude(NON_NULL)
    @JsonProperty("areas")
    private List<ResortAreaDTO> areas = new ArrayList<>();

    @JsonInclude(NON_NULL)
    @JsonProperty("isAbleForEntering")
    private Boolean isAbleForEntering;

    @JsonInclude(NON_NULL)
    @JsonProperty("enterTypes")
    private List<EntranceTypeDTO> enterTypes = new ArrayList<>();

    @JsonInclude(NON_NULL)
    @JsonProperty("travelingTypes")
    private List<TravelingTypeDTO> travelingTypes = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public ResortCityDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ResortCityDTO setName(String name) {
        this.name = name;
        return this;
    }

    public CountryDTO getCountry() {
        return country;
    }

    public ResortCityDTO setCountry(CountryDTO country) {
        this.country = country;
        return this;
    }

    public List<ResortAreaDTO> getAreas() {
        return areas;
    }

    public ResortCityDTO setAreas(List<ResortAreaDTO> areas) {
        this.areas = areas;
        return this;
    }

    public Boolean getIsAbleForEntering() {
        return isAbleForEntering;
    }

    public ResortCityDTO setIsAbleForEntering(Boolean isAbleForEntering) {
        this.isAbleForEntering = isAbleForEntering;
        return this;
    }

    public List<EntranceTypeDTO> getEnterTypes() {
        return enterTypes;
    }

    public ResortCityDTO setEnterTypes(List<EntranceTypeDTO> enterTypes) {
        this.enterTypes = enterTypes;
        return this;
    }

    public List<TravelingTypeDTO> getTravelingTypes() {
        return travelingTypes;
    }

    public ResortCityDTO setTravelingTypes(List<TravelingTypeDTO> travelingTypes) {
        this.travelingTypes = travelingTypes;
        return this;
    }

    public static ResortCityDTO makeDTO(ResortCity city) {
        return new ResortCityDTO()
                .setId(city.getId())
                .setName(city.getName())
                .setIsAbleForEntering(city.getIsAbleForEntering())

                // Enum Lists
                .setEnterTypes(city.getEnterTypes().stream()
                    .map(EntranceTypeDTO::makeSimpleDTO).collect(Collectors.toList()))

                .setTravelingTypes(city.getTravelingTypes().stream()
                    .map(TravelingTypeDTO::makeSimpleDTO).collect(Collectors.toList()))

                .setCountry(CountryDTO.makeSimpleDTO(city.getCountry()))

                .setAreas(city.getAreas().stream()
                    .map(ResortAreaDTO::makeSimpleDTO).collect(Collectors.toList()));
    }

    public static ResortCityDTO makeSimpleDTO(ResortCity city) {
        return (city != null) ? new ResortCityDTO()
                .setId(city.getId())
                .setName(city.getName())
                .setIsAbleForEntering(city.getIsAbleForEntering())

                // Enum Lists
                .setEnterTypes(city.getEnterTypes().stream()
                    .map(EntranceTypeDTO::makeSimpleDTO).collect(Collectors.toList()))

                .setTravelingTypes(city.getTravelingTypes().stream()
                    .map(TravelingTypeDTO::makeSimpleDTO).collect(Collectors.toList())) : null;
    }
}
