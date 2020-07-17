package com.summer.tourfirm.dto.edit;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.summer.tourfirm.entity.enums.EntranceWay;

import java.util.ArrayList;
import java.util.List;

public class CountryEditDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("isAbleForEntering")
    private Boolean isAbleForEntering;

    @JsonProperty("cityIds")
    private List<Long> cityIds = new ArrayList<>();

    @JsonProperty("enterWays")
    private List<EntranceWay> enterWays = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getAbleForEntering() {
        return isAbleForEntering;
    }

    public void setAbleForEntering(Boolean ableForEntering) {
        isAbleForEntering = ableForEntering;
    }

    public List<Long> getCityIds() {
        return cityIds;
    }

    public void setCityIds(List<Long> cityIds) {
        this.cityIds = cityIds;
    }

    public List<EntranceWay> getEnterWays() {
        return enterWays;
    }

    public void setEnterWays(List<EntranceWay> enterWays) {
        this.enterWays = enterWays;
    }
}
