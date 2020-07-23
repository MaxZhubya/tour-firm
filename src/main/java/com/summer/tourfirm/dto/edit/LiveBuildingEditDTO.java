package com.summer.tourfirm.dto.edit;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.summer.tourfirm.entity.enums.Building;
import com.summer.tourfirm.entity.types.BuildingType;

import java.util.ArrayList;
import java.util.List;

public class LiveBuildingEditDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("apartmentIds")
    private List<Long> apartmentIds = new ArrayList<>();

    @JsonProperty("typeId")
    private BuildingType typeId;

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

    public BuildingType getTypeId() {
        return typeId;
    }

    public void setTypeId(BuildingType typeId) {
        this.typeId = typeId;
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
