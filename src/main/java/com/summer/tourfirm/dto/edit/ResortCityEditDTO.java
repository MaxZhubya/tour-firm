package com.summer.tourfirm.dto.edit;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ResortCityEditDTO {

    @JsonProperty("id")
    private Long id;

    @NotEmpty
    @JsonProperty("name")
    private String name;

    @NotNull
    @JsonProperty("countryId")
    private Long countryId;

    @JsonProperty("isAbleForEntering")
    private Boolean isAbleForEntering;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public Boolean getIsAbleForEntering() {
        return isAbleForEntering;
    }

    public void setIsAbleForEntering(Boolean isAbleForEntering) {
        this.isAbleForEntering = isAbleForEntering;
    }

}
