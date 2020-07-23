package com.summer.tourfirm.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.summer.tourfirm.entity.LiveBuilding;
import com.summer.tourfirm.entity.enums.Building;
import com.summer.tourfirm.entity.types.BuildingType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonPropertyOrder({"id", "type", "availableApartmentCount", "ifPoolExist", "ifParkingExist", "distanceToBeach", "area", "apartments"})
public class LiveBuildingDTO {

    @JsonProperty("id")
    private Long id;

    @JsonInclude(NON_NULL)
    @JsonProperty("area")
    private ResortAreaDTO area;

    @JsonInclude(NON_NULL)
    @JsonProperty("apartments")
    private List<ApartmentDTO> apartments = new ArrayList<>();

    @JsonInclude(NON_NULL)
    @JsonProperty("type")
    private BuildingType type;

    @JsonInclude(NON_NULL)
    @JsonProperty("availableApartmentCount")
    private Integer availableApartmentCount;

    @JsonInclude(NON_NULL)
    @JsonProperty("ifPoolExist")
    private Boolean ifPoolExist;

    @JsonInclude(NON_NULL)
    @JsonProperty("ifParkingExist")
    private Boolean ifParkingExist;

    @JsonInclude(NON_NULL)
    @JsonProperty("distanceToBeach")
    private Integer distanceToBeach;

    public Long getId() {
        return id;
    }

    public LiveBuildingDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public ResortAreaDTO getArea() {
        return area;
    }

    public LiveBuildingDTO setArea(ResortAreaDTO area) {
        this.area = area;
        return this;
    }

    public List<ApartmentDTO> getApartments() {
        return apartments;
    }

    public LiveBuildingDTO setApartments(List<ApartmentDTO> apartments) {
        this.apartments = apartments;
        return this;
    }

    public BuildingType getType() {
        return type;
    }

    public LiveBuildingDTO setType(BuildingType type) {
        this.type = type;
        return this;
    }

    public Integer getAvailableApartmentCount() {
        return availableApartmentCount;
    }

    public LiveBuildingDTO setAvailableApartmentCount(Integer availableApartmentCount) {
        this.availableApartmentCount = availableApartmentCount;
        return this;
    }

    public Boolean getIfPoolExist() {
        return ifPoolExist;
    }

    public LiveBuildingDTO setIfPoolExist(Boolean ifPoolExist) {
        this.ifPoolExist = ifPoolExist;
        return this;
    }

    public Boolean getIfParkingExist() {
        return ifParkingExist;
    }

    public LiveBuildingDTO setIfParkingExist(Boolean ifParkingExist) {
        this.ifParkingExist = ifParkingExist;
        return this;
    }

    public Integer getDistanceToBeach() {
        return distanceToBeach;
    }

    public LiveBuildingDTO setDistanceToBeach(Integer distanceToBeach) {
        this.distanceToBeach = distanceToBeach;
        return this;
    }

    public static LiveBuildingDTO makeDTO(LiveBuilding building) {
        return new LiveBuildingDTO()
                .setId(building.getId())
                .setType(building.getType())
                .setAvailableApartmentCount(building.getAvailableApartmentCount())
                .setIfPoolExist(building.isIfPoolExist())
                .setIfParkingExist(building.isIfParkingExist())
                .setDistanceToBeach(building.getDistanceToBeach())

                .setArea(ResortAreaDTO.makeSimpleDTO(building.getArea()))

                .setApartments(building.getApartments().stream()
                        .map(ApartmentDTO::makeSimpleDTO).collect(Collectors.toList()));
    }

    public static LiveBuildingDTO makeSimpleDTO(LiveBuilding building) {
        return (building != null) ? new LiveBuildingDTO()
                .setId(building.getId())
                .setType(building.getType())
                .setAvailableApartmentCount(building.getAvailableApartmentCount())
                .setIfPoolExist(building.isIfPoolExist())
                .setIfParkingExist(building.isIfParkingExist())
                .setDistanceToBeach(building.getDistanceToBeach()) : null;
    }

}
