package com.summer.tourfirm.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.summer.tourfirm.entity.types.TravelingType;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@JsonPropertyOrder({"id", "type"})
public class TravelingTypeDTO {

    @JsonProperty("id")
    private Long id;

    @JsonInclude(NON_EMPTY)
    @JsonProperty("type")
    private String type;

    public Long getId() {
        return id;
    }

    public TravelingTypeDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getType() {
        return type;
    }

    public TravelingTypeDTO setType(String type) {
        this.type = type;
        return this;
    }

    public static TravelingTypeDTO makeDTO(TravelingType type) {
        return new TravelingTypeDTO()
                .setId(type.getId())
                .setType(type.getType());
    }

    public static TravelingTypeDTO makeSimpleDTO(TravelingType type) {
        return (type != null) ? new TravelingTypeDTO()
                .setId(type.getId())
                .setType(type.getType()) : null;
    }
}
