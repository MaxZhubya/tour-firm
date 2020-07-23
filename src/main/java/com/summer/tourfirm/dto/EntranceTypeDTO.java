package com.summer.tourfirm.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.summer.tourfirm.entity.types.EntranceType;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@JsonPropertyOrder({"id", "type"})
public class EntranceTypeDTO {

    @JsonProperty("id")
    private Long id;

    @JsonInclude(NON_EMPTY)
    @JsonProperty("type")
    private String type;

    public Long getId() {
        return id;
    }

    public EntranceTypeDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getType() {
        return type;
    }

    public EntranceTypeDTO setType(String type) {
        this.type = type;
        return this;
    }

    public static EntranceTypeDTO makeDTO(EntranceType type) {
        return new EntranceTypeDTO()
                .setId(type.getId())
                .setType(type.getType());
    }

    public static EntranceTypeDTO makeSimpleDTO(EntranceType type) {
        return (type != null) ? new EntranceTypeDTO()
                .setId(type.getId())
                .setType(type.getType()) : null;
    }
}
