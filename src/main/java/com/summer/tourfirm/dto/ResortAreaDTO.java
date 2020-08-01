package com.summer.tourfirm.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.summer.tourfirm.entity.ResortArea;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonPropertyOrder({"id", "name", "city", "buildings"})
public class ResortAreaDTO {

    @JsonProperty("id")
    private Long id;

    @JsonInclude(NON_NULL)
    @JsonProperty("city")
    private ResortCityDTO city;

    @JsonInclude(NON_NULL)
    @JsonProperty("buildings")
    private List<LiveBuildingDTO> buildings = new ArrayList<>();

    @JsonInclude(NON_EMPTY)
    @JsonProperty("name")
    private String name;

    public Long getId() {
        return id;
    }

    public ResortAreaDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public ResortCityDTO getCity() {
        return city;
    }

    public ResortAreaDTO setCity(ResortCityDTO city) {
        this.city = city;
        return this;
    }

    public List<LiveBuildingDTO> getBuildings() {
        return buildings;
    }

    public ResortAreaDTO setBuildings(List<LiveBuildingDTO> buildings) {
        this.buildings = buildings;
        return this;
    }

    public String getName() {
        return name;
    }

    public ResortAreaDTO setName(String name) {
        this.name = name;
        return this;
    }

    public static ResortAreaDTO makeDTO(ResortArea area) {
        return new ResortAreaDTO()
                .setId(area.getId())
                .setName(area.getName())

                .setCity(ResortCityDTO.makeSimpleDTO(area.getCity()))

                .setBuildings(area.getBuildings().stream()
                    .map(LiveBuildingDTO::makeSimpleDTO).collect(Collectors.toList()));
    }

    public static ResortAreaDTO makeSimpleDTO(ResortArea area) {
        return (area != null) ? new ResortAreaDTO()
                .setId(area.getId())
                .setName(area.getName()) : null;
    }
}
