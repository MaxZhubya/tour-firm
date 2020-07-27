package com.summer.tourfirm.dto.edit;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.summer.tourfirm.entity.types.EntranceType;
import com.summer.tourfirm.entity.types.TravelingType;

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

    @JsonProperty("entranceTypeIds")
    private List<Long> entranceTypeIds = new ArrayList<>();

    @JsonProperty("travelingTypeIds")
    private List<Long> travelingTypeIds = new ArrayList<>();

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

    public Boolean getIsAbleForEntering() {
        return isAbleForEntering;
    }

    public void setIsAbleForEntering(Boolean isAbleForEntering) {
        this.isAbleForEntering = isAbleForEntering;
    }

    public List<Long> getEntranceTypesIds() {
        return entranceTypeIds;
    }

    public void setEntranceTypesIds(List<Long> entranceTypeIds) {
        this.entranceTypeIds = entranceTypeIds;
    }

    public List<Long> getTravelingTypesIds() {
        return travelingTypeIds;
    }

    public void setTravelingTypesIds(List<Long> travelingTypeIds) {
        this.travelingTypeIds = travelingTypeIds;
    }
}
