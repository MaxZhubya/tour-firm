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

    @JsonProperty("entranceTypes")
    private List<EntranceType> entranceTypes = new ArrayList<>();

    @JsonProperty("travelingTypes")
    private List<TravelingType> travelingTypes = new ArrayList<>();

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

    public List<EntranceType> getEntranceTypes() {
        return entranceTypes;
    }

    public void setEntranceTypes(List<EntranceType> entranceTypes) {
        this.entranceTypes = entranceTypes;
    }

    public List<TravelingType> getTravelingTypes() {
        return travelingTypes;
    }

    public void setTravelingTypes(List<TravelingType> travelingTypes) {
        this.travelingTypes = travelingTypes;
    }
}
