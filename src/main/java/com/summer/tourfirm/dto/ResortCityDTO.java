package com.summer.tourfirm.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.summer.tourfirm.entity.ResortCity;
import com.summer.tourfirm.entity.enums.Entrance;
import com.summer.tourfirm.entity.enums.Traveling;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonPropertyOrder({"id", "isAbleForEntering", "entranceWays", "travelingWays", "country", "areas"})
public class ResortCityDTO {

    @JsonProperty("id")
    private Long id;

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
    @JsonProperty("entranceTypes")
    private List<EntranceTypeDTO> entranceTypes = new ArrayList<>();

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

    public Boolean getAbleForEntering() {
        return isAbleForEntering;
    }

    public ResortCityDTO setAbleForEntering(Boolean ableForEntering) {
        isAbleForEntering = ableForEntering;
        return this;
    }

    public List<EntranceTypeDTO> getEntranceTypes() {
        return entranceTypes;
    }

    public ResortCityDTO setEntranceTypes(List<EntranceTypeDTO> entranceTypes) {
        this.entranceTypes = entranceTypes;
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
                .setAbleForEntering(city.isAbleForEntering())

                // Enum Lists
                .setEntranceTypes(city.getEntranceTypes())
                .setTravelingTypes(city.getTravelingTypes())

                .setCountry(CountryDTO.makeSimpleDTO(city.getCountry()))

                .setAreas(city.getAreas().stream()
                    .map(ResortAreaDTO::makeSimpleDTO).collect(Collectors.toList()));
    }

    public static ResortCityDTO makeSimpleDTO(ResortCity city) {
        return (city != null) ? new ResortCityDTO()
                .setId(city.getId())
                .setAbleForEntering(city.isAbleForEntering())

                // Enum Lists
                .setEntranceTypes(city.getEntranceTypes())
                .setTravelingTypes(city.getTravelingTypes()) : null;
    }
}
