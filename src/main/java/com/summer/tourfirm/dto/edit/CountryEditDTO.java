package com.summer.tourfirm.dto.edit;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.summer.tourfirm.entity.types.EntranceType;

import java.util.ArrayList;
import java.util.List;

public class CountryEditDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("isAbleForEntering")
    private Boolean isAbleForEntering;

    @JsonProperty("name")
    private String name;

    @JsonProperty("cityIds")
    private List<Long> cityIds = new ArrayList<>();

    @JsonProperty("enterTypesIds")
    private List<Long> enterTypesIds = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getIsAbleForEntering() {
        return isAbleForEntering;
    }

    public void setIsAbleForEntering(Boolean isAbleForEntering) {
        this.isAbleForEntering = isAbleForEntering;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Long> getCityIds() {
        return cityIds;
    }

    public void setCityIds(List<Long> cityIds) {
        this.cityIds = cityIds;
    }

    public List<Long> getEnterTypesIds() {
        return enterTypesIds;
    }

    public void setEnterTypesIds(List<Long> enterTypesIds) {
        this.enterTypesIds = enterTypesIds;
    }
}
