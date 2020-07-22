package com.summer.tourfirm.dto.edit;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.summer.tourfirm.entity.enums.Entrance;
import com.summer.tourfirm.entity.enums.Traveling;

import java.util.ArrayList;
import java.util.List;

public class ResortCityEditDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("countryId")
    private Long countryId;

    @JsonProperty("areaIds")
    private List<Long> areaIds = new ArrayList<>();

    @JsonProperty("isAbleForEntering")
    private Boolean isAbleForEntering;

    @JsonProperty("entranceWays")
    private List<Entrance> entranceWays = new ArrayList<>();

    @JsonProperty("travelingWays")
    private List<Traveling> travelingWays = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
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

    public List<Entrance> getEntranceWays() {
        return entranceWays;
    }

    public void setEntranceWays(List<Entrance> entranceWays) {
        this.entranceWays = entranceWays;
    }

    public List<Traveling> getTravelingWays() {
        return travelingWays;
    }

    public void setTravelingWays(List<Traveling> travelingWays) {
        this.travelingWays = travelingWays;
    }
}
