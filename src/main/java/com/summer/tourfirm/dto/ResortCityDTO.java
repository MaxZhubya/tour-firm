package com.summer.tourfirm.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.summer.tourfirm.entity.ResortCity;
import com.summer.tourfirm.entity.enums.EntranceWay;
import com.summer.tourfirm.entity.enums.TravelingWay;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonPropertyOrder({"id", "isAbleForEntering", "entranceWays", "travelingWays", "country", "areas"})
public class ResortCityDTO {

    @JsonProperty("id")
    private Long id;

    @JsonInclude(NON_NULL)
    @JsonProperty("country")
    private CountryDTO country;

    @JsonInclude(NON_EMPTY)
    @JsonProperty("areas")
    private List<ResortAreaDTO> areas = new ArrayList<>();

    @JsonInclude(NON_NULL)
    @JsonProperty("isAbleForEntering")
    private Boolean isAbleForEntering;

    @JsonInclude(NON_EMPTY)
    @JsonProperty("entranceWays")
    private List<EntranceWay> entranceWays = new ArrayList<>();

    @JsonInclude(NON_EMPTY)
    @JsonProperty("travelingWays")
    private List<TravelingWay> travelingWays = new ArrayList<>();

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

    public List<EntranceWay> getEntranceWays() {
        return entranceWays;
    }

    public ResortCityDTO setEntranceWays(List<EntranceWay> entranceWays) {
        this.entranceWays = entranceWays;
        return this;
    }

    public List<TravelingWay> getTravelingWays() {
        return travelingWays;
    }

    public ResortCityDTO setTravelingWays(List<TravelingWay> travelingWays) {
        this.travelingWays = travelingWays;
        return this;
    }

    public static ResortCityDTO makeDTO(ResortCity city) {
        return new ResortCityDTO()
                .setId(city.getId())
                .setAbleForEntering(city.isAbleForEntering())

                // Enum Lists
                .setEntranceWays(city.getEntranceWays())
                .setTravelingWays(city.getTravelingWays())

                .setCountry(CountryDTO.makeSimpleDTO(city.getCountry()))

                .setAreas(city.getAreas().stream()
                    .map(ResortAreaDTO::makeSimpleDTO).collect(Collectors.toList()));
    }

    public static ResortCityDTO makeSimpleDTO(ResortCity city) {
        return (city != null) ? new ResortCityDTO()
                .setId(city.getId())
                .setAbleForEntering(city.isAbleForEntering())

                // Enum Lists
                .setEntranceWays(city.getEntranceWays())
                .setTravelingWays(city.getTravelingWays()) : null;
    }
}
