package com.summer.tourfirm.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.summer.tourfirm.entity.Image;
import com.summer.tourfirm.entity.LiveBuilding;
import com.summer.tourfirm.entity.enums.BuildingEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonPropertyOrder({"id", "type", "number", "address", "name", "availableApartmentCount", "ifPoolExist", "ifParkingExist",
                    "distanceToBeach", "area", "apartments", "images"})
public class LiveBuildingDTO {

    @JsonProperty("id")
    private Long id;

    @JsonInclude(NON_NULL)
    @JsonProperty("area")
    private ResortAreaDTO area;

    @JsonInclude(NON_NULL)
    @JsonProperty("apartments")
    private List<ApartmentDTO> apartments = new ArrayList<>();

    @JsonInclude(NON_NULL)
    @JsonProperty("images")
    private List<String> images = new ArrayList<>();

    @JsonInclude(NON_NULL)
    @JsonProperty("type")
    private BuildingEnum type;

    @JsonInclude(NON_EMPTY)
    @JsonProperty("number")
    private String number;

    @JsonInclude(NON_EMPTY)
    @JsonProperty("address")
    private String address;

    @JsonInclude(NON_EMPTY)
    @JsonProperty("name")
    private String name;

    @JsonInclude(NON_NULL)
    @JsonProperty("ifPoolExist")
    private Boolean ifPoolExist;

    @JsonInclude(NON_NULL)
    @JsonProperty("ifParkingExist")
    private Boolean ifParkingExist;

    @JsonInclude(NON_NULL)
    @JsonProperty("distanceToBeach")
    private Integer distanceToBeach;

    public Long getId() {
        return id;
    }

    public LiveBuildingDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public ResortAreaDTO getArea() {
        return area;
    }

    public LiveBuildingDTO setArea(ResortAreaDTO area) {
        this.area = area;
        return this;
    }

    public List<ApartmentDTO> getApartments() {
        return apartments;
    }

    public LiveBuildingDTO setApartments(List<ApartmentDTO> apartments) {
        this.apartments = apartments;
        return this;
    }

    public List<String> getImages() {
        return images;
    }

    public LiveBuildingDTO setImages(List<String> images) {
        this.images = images;
        return this;
    }

    public BuildingEnum getType() {
        return type;
    }

    public LiveBuildingDTO setType(BuildingEnum type) {
        this.type = type;
        return this;
    }

    public String getNumber() {
        return number;
    }

    public LiveBuildingDTO setNumber(String number) {
        this.number = number;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public LiveBuildingDTO setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getName() {
        return name;
    }

    public LiveBuildingDTO setName(String name) {
        this.name = name;
        return this;
    }

    public Boolean getIfPoolExist() {
        return ifPoolExist;
    }

    public LiveBuildingDTO setIfPoolExist(Boolean ifPoolExist) {
        this.ifPoolExist = ifPoolExist;
        return this;
    }

    public Boolean getIfParkingExist() {
        return ifParkingExist;
    }

    public LiveBuildingDTO setIfParkingExist(Boolean ifParkingExist) {
        this.ifParkingExist = ifParkingExist;
        return this;
    }

    public Integer getDistanceToBeach() {
        return distanceToBeach;
    }

    public LiveBuildingDTO setDistanceToBeach(Integer distanceToBeach) {
        this.distanceToBeach = distanceToBeach;
        return this;
    }

    public static LiveBuildingDTO makeDTO(LiveBuilding building) {
        return new LiveBuildingDTO()
                .setId(building.getId())
                .setType(building.getType())
                .setNumber(building.getNumber())
                .setAddress(building.getAddress())
                .setName(building.getName())
                .setIfPoolExist(building.isIfPoolExist())
                .setIfParkingExist(building.isIfParkingExist())
                .setDistanceToBeach(building.getDistanceToBeach())

                .setArea(ResortAreaDTO.makeSimpleDTO(building.getArea()))

                .setApartments(building.getApartments().stream()
                        .map(ApartmentDTO::makeSimpleDTO).collect(Collectors.toList()))

                .setImages(building.getImages().stream()
                        .map(Image::getName).collect(Collectors.toList()));
    }

    public static LiveBuildingDTO makeSimpleDTO(LiveBuilding building) {
        return (building != null) ? new LiveBuildingDTO()
                .setId(building.getId())
                .setType(building.getType())
                .setNumber(building.getNumber())
                .setAddress(building.getAddress())
                .setName(building.getName())
                .setIfPoolExist(building.isIfPoolExist())
                .setIfParkingExist(building.isIfParkingExist())
                .setDistanceToBeach(building.getDistanceToBeach()) : null;
    }

}
