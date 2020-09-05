package com.summer.tourfirm.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.summer.tourfirm.entity.Image;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonPropertyOrder({"id", "building", "name"})
public class ImageDTO {

    @JsonProperty("id")
    private Long id;

    @JsonInclude(NON_NULL)
    @JsonProperty("building")
    private LiveBuildingDTO building;

    @JsonInclude(NON_EMPTY)
    @JsonProperty("name")
    private String name;

    public Long getId() {
        return id;
    }

    public ImageDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public LiveBuildingDTO getBuilding() {
        return building;
    }

    public ImageDTO setBuilding(LiveBuildingDTO building) {
        this.building = building;
        return this;
    }

    public String getName() {
        return name;
    }

    public ImageDTO setName(String name) {
        this.name = name;
        return this;
    }

    public static ImageDTO makeDTO(Image image) {
        return new ImageDTO()
                .setId(image.getId())
                .setBuilding(LiveBuildingDTO.makeSimpleDTO(image.getBuilding()))
                .setName(image.getName());
    }

    public static ImageDTO makeSimpleDTO(Image image) {
        return (image != null) ? new ImageDTO()
                .setId(image.getId())
                .setName(image.getName()) : null;
    }
}
