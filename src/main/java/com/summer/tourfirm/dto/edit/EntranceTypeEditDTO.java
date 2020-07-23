package com.summer.tourfirm.dto.edit;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EntranceTypeEditDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("type")
    private String type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
