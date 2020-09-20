package com.summer.tourfirm.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.summer.tourfirm.entity.Apartment;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonPropertyOrder({"id", "number", "price", "amountOfBeds", "amountOfRooms", "ifBathroomExist", "building", "reservedApartments"})
public class ApartmentDTO {

    @JsonProperty("id")
    private Long id;

    @JsonInclude(NON_NULL)
    @JsonProperty("building")
    private LiveBuildingDTO building;

    @JsonInclude(NON_NULL)
    @JsonProperty("reservedApartments")
    private List<ReservedApartmentDTO> reservedApartments = new ArrayList<>();

    @JsonInclude(NON_EMPTY)
    @JsonProperty("number")
    private String number;

    @JsonInclude(NON_NULL)
    @JsonProperty("price")
    private Integer price;

    @JsonInclude(NON_NULL)
    @JsonProperty("amountOfBeds")
    private Integer amountOfBeds;

    @JsonInclude(NON_NULL)
    @JsonProperty("amountOfRooms")
    private Integer amountOfRooms;

    @JsonInclude(NON_NULL)
    @JsonProperty("ifBathroomExist")
    private Boolean ifBathroomExist;

    public Long getId() {
        return id;
    }

    public ApartmentDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public LiveBuildingDTO getBuilding() {
        return building;
    }

    public ApartmentDTO setBuilding(LiveBuildingDTO building) {
        this.building = building;
        return this;
    }

    public List<ReservedApartmentDTO> getReservedApartments() {
        return reservedApartments;
    }

    public ApartmentDTO setReservedApartments(List<ReservedApartmentDTO> reservedApartments) {
        this.reservedApartments = reservedApartments;
        return this;
    }

    public String getNumber() {
        return number;
    }

    public ApartmentDTO setNumber(String number) {
        this.number = number;
        return this;
    }

    public Integer getPrice() {
        return price;
    }

    public ApartmentDTO setPrice(Integer price) {
        this.price = price;
        return this;
    }

    public Integer getAmountOfBeds() {
        return amountOfBeds;
    }

    public ApartmentDTO setAmountOfBeds(Integer amountOfBeds) {
        this.amountOfBeds = amountOfBeds;
        return this;
    }

    public Integer getAmountOfRooms() {
        return amountOfRooms;
    }

    public ApartmentDTO setAmountOfRooms(Integer amountOfRooms) {
        this.amountOfRooms = amountOfRooms;
        return this;
    }

    public Boolean getIfBathroomExist() {
        return ifBathroomExist;
    }

    public ApartmentDTO setIfBathroomExist(Boolean ifBathroomExist) {
        this.ifBathroomExist = ifBathroomExist;
        return this;
    }

    public static ApartmentDTO makeDTO(Apartment apartment) {
        return new ApartmentDTO()
                .setId(apartment.getId())
                .setNumber(apartment.getNumber())
                .setPrice(apartment.getPrice())
                .setAmountOfBeds(apartment.getAmountOfBeds())
                .setAmountOfRooms(apartment.getAmountOfRooms())
                .setIfBathroomExist(apartment.isIfBathroomExist())

                .setBuilding(LiveBuildingDTO.makeSimpleDTO(apartment.getBuilding()))

                .setReservedApartments(apartment.getReservedApartments().stream()
                    .map(ReservedApartmentDTO::makeSimpleDTO).collect(Collectors.toList()));
    }

    public static ApartmentDTO makeSimpleDTO(Apartment apartment) {
        return (apartment != null) ? new ApartmentDTO()
                .setId(apartment.getId())
                .setNumber(apartment.getNumber())
                .setPrice(apartment.getPrice())
                .setAmountOfBeds(apartment.getAmountOfBeds())
                .setAmountOfRooms(apartment.getAmountOfRooms())
                .setIfBathroomExist(apartment.isIfBathroomExist())
                .setReservedApartments(apartment.getReservedApartments().stream()
                        .map(ReservedApartmentDTO::makeSimpleDTO).collect(Collectors.toList()))
                : null;
    }

}
