package com.summer.tourfirm.dto.edit;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ApartmentEditDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("buildingId")
    private Long buildingId;

    @JsonProperty("number")
    private String number;

    @JsonProperty("price")
    private Integer price;

    @JsonProperty("amountOfBeds")
    private Integer amountOfBeds;

    @JsonProperty("amountOfRooms")
    private Integer amountOfRooms;

    @JsonProperty("ifBathroomExist")
    private Boolean ifBathroomExist;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getAmountOfBeds() {
        return amountOfBeds;
    }

    public void setAmountOfBeds(Integer amountOfBeds) {
        this.amountOfBeds = amountOfBeds;
    }

    public Integer getAmountOfRooms() {
        return amountOfRooms;
    }

    public void setAmountOfRooms(Integer amountOfRooms) {
        this.amountOfRooms = amountOfRooms;
    }

    public Boolean getIfBathroomExist() {
        return ifBathroomExist;
    }

    public void setIfBathroomExist(Boolean ifBathroomExist) {
        this.ifBathroomExist = ifBathroomExist;
    }
}
