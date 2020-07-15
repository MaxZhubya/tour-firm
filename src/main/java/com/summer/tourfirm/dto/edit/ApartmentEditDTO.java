package com.summer.tourfirm.dto.edit;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ApartmentEditDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("buildingId")
    private Long buildingId;

    @JsonProperty("price")
    private Integer price;

    @JsonProperty("amountOfBeds")
    private Integer amountOfBeds;

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
}
