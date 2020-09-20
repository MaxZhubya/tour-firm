package com.summer.tourfirm.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.summer.tourfirm.entity.ReservedApartment;

import java.time.ZonedDateTime;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonPropertyOrder({"id", "date_in", "date_out", "apartment"})
public class ReservedApartmentDTO {

    @JsonProperty("id")
    private Long id;

    @JsonInclude(NON_NULL)
    @JsonProperty("apartment")
    private ApartmentDTO apartment;

    @JsonInclude(NON_NULL)
    @JsonFormat(pattern="yyyy-MM-dd")
    @JsonProperty("date_in")
    private ZonedDateTime dateIn;

    @JsonInclude(NON_NULL)
    @JsonFormat(pattern="yyyy-MM-dd")
    @JsonProperty("date_out")
    private ZonedDateTime dateOut;

    public Long getId() {
        return id;
    }

    public ReservedApartmentDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public ApartmentDTO getApartment() {
        return apartment;
    }

    public ReservedApartmentDTO setApartment(ApartmentDTO apartment) {
        this.apartment = apartment;
        return this;
    }

    public ZonedDateTime getDateIn() {
        return dateIn;
    }

    public ReservedApartmentDTO setDateIn(ZonedDateTime dateIn) {
        this.dateIn = dateIn;
        return this;
    }

    public ZonedDateTime getDateOut() {
        return dateOut;
    }

    public ReservedApartmentDTO setDateOut(ZonedDateTime dateOut) {
        this.dateOut = dateOut;
        return this;
    }

    public static ReservedApartmentDTO makeDTO(ReservedApartment reservedApartment) {
        return new ReservedApartmentDTO()
                .setId(reservedApartment.getId())
                .setDateIn(reservedApartment.getDateIn())
                .setDateOut(reservedApartment.getDateOut())

                .setApartment(ApartmentDTO.makeSimpleDTO(reservedApartment.getApartment()));
    }

    public static ReservedApartmentDTO makeSimpleDTO(ReservedApartment reservedApartment) {
        return (reservedApartment != null) ? new ReservedApartmentDTO()
                .setId(reservedApartment.getId())
                .setDateIn(reservedApartment.getDateIn())
                .setDateOut(reservedApartment.getDateOut()) : null;
    }
}
