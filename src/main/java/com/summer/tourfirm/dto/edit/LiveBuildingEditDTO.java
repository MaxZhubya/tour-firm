package com.summer.tourfirm.dto.edit;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.summer.tourfirm.entity.enums.BuildingEnum;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class LiveBuildingEditDTO {

    @JsonProperty("id")
    private Long id;

    @NotNull
    @JsonProperty("areaId")
    private Long areaId;

    @JsonProperty("type")
    private BuildingEnum type;

    @JsonProperty("number")
    private String number;

    @JsonProperty("address")
    private String address;

    @JsonProperty("name")
    private String name;

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

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public BuildingEnum getType() {
        return type;
    }

    public void setType(BuildingEnum type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //    public Integer getAvailableApartmentCount() {
//        return availableApartmentCount;
//    }
//
//    public void setAvailableApartmentCount(Integer availableApartmentCount) {
//        this.availableApartmentCount = availableApartmentCount;
//    }

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
