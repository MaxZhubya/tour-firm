package com.summer.tourfirm.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.summer.tourfirm.entity.types.BuildingType;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@JsonPropertyOrder({"id", "type"})
public class BuildingTypeDTO {

    @JsonProperty("id")
    private Long id;

    @JsonInclude(NON_EMPTY)
    @JsonProperty("type")
    private String type;

    public Long getId() {
        return id;
    }

    public BuildingTypeDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getType() {
        return type;
    }

    public BuildingTypeDTO setType(String type) {
        this.type = type;
        return this;
    }

    public static BuildingTypeDTO makeDTO(BuildingType type) {
        return new BuildingTypeDTO()
                .setId(type.getId())
                .setType(type.getType());
    }

    public static BuildingTypeDTO makeSimpleDTO(BuildingType type) {
        return (type != null) ? new BuildingTypeDTO()
                .setId(type.getId())
                .setType(type.getType()) : null;
    }
}
