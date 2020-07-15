package com.summer.tourfirm.dto.edit;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class ResortCityEditDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("areaIds")
    private List<Long> areaIds = new ArrayList<>();

    @JsonProperty("isAbleForEntering")
    private Boolean isAbleForEntering;

    @JsonProperty("entranceWays")
    private List<Enum> entranceWays = new ArrayList<>();

    @JsonProperty("travelingWays")
    private List<Enum> travelingWays = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Long> getAreaIds() {
        return areaIds;
    }

    public void setAreaIds(List<Long> areaIds) {
        this.areaIds = areaIds;
    }

    public Boolean getAbleForEntering() {
        return isAbleForEntering;
    }

    public void setAbleForEntering(Boolean ableForEntering) {
        isAbleForEntering = ableForEntering;
    }

    public List<Enum> getEntranceWays() {
        return entranceWays;
    }

    public void setEntranceWays(List<Enum> entranceWays) {
        this.entranceWays = entranceWays;
    }

    public List<Enum> getTravelingWays() {
        return travelingWays;
    }

    public void setTravelingWays(List<Enum> travelingWays) {
        this.travelingWays = travelingWays;
    }
}
