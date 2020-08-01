package com.summer.tourfirm.dto.edit;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class ResortAreaEditDTO {

    @JsonProperty("id")
    private Long id;

    @NotNull
    @JsonProperty("cityId")
    private Long cityId;

    @JsonProperty("name")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
