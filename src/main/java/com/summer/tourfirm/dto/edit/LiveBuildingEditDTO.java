package com.summer.tourfirm.dto.edit;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.summer.tourfirm.entity.enums.BuildingType;

import java.util.ArrayList;
import java.util.List;

public class LiveBuildingEditDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("apartmentIds")
    private List<Long> apartmentIds = new ArrayList<>();

    @JsonProperty("type")
    private BuildingType type;

    @JsonProperty("availableApartmentCount")
    private Integer availableApartmentCount;

    @JsonProperty("ifPoolExist")
    private Boolean ifPoolExist;

    @JsonProperty("ifParkingExist")
    private Boolean ifParkingExist;

    @JsonProperty("distanceToBeach")
    private Integer distanceToBeach;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Long> getApartmentIds() {
        return apartmentIds;
    }

    public void setApartmentIds(List<Long> apartmentIds) {
        this.apartmentIds = apartmentIds;
    }

    public BuildingType getType() {
        return type;
    }

    public void setType(BuildingType type) {
        this.type = type;
    }

    public Integer getAvailableApartmentCount() {
        return availableApartmentCount;
    }

    public void setAvailableApartmentCount(Integer availableApartmentCount) {
        this.availableApartmentCount = availableApartmentCount;
    }

    public Boolean getIfPoolExist() {
        return ifPoolExist;
    }

    public void setIfPoolExist(Boolean ifPoolExist) {
        this.ifPoolExist = ifPoolExist;
    }

    public Boolean getIfParkingExist() {
        return ifParkingExist;
    }

    public void setIfParkingExist(Boolean ifParkingExist) {
        this.ifParkingExist = ifParkingExist;
    }

    public Integer getDistanceToBeach() {
        return distanceToBeach;
    }

    public void setDistanceToBeach(Integer distanceToBeach) {
        this.distanceToBeach = distanceToBeach;
    }
}
