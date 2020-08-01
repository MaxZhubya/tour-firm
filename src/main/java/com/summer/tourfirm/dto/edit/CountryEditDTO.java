package com.summer.tourfirm.dto.edit;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CountryEditDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("isAbleForEntering")
    private Boolean isAbleForEntering;

    @JsonProperty("name")
    private String name;

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

}
