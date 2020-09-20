package com.summer.tourfirm.dto.edit;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

public class ReservedApartmentEditDTO {

    @JsonProperty("id")
    private Long id;

    @NotNull
    @JsonProperty("apartmentId")
    private Long apartmentId;

    @NotNull
    @JsonProperty("dateIn")
    private ZonedDateTime dateIn;

    @NotNull
    @JsonProperty("dateOut")
    private ZonedDateTime dateOut;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(Long apartmentId) {
        this.apartmentId = apartmentId;
    }

    public ZonedDateTime getDateIn() {
        return dateIn;
    }

    public void setDateIn(ZonedDateTime dateIn) {
        this.dateIn = dateIn;
    }

    public ZonedDateTime getDateOut() {
        return dateOut;
    }

    public void setDateOut(ZonedDateTime dateOut) {
        this.dateOut = dateOut;
    }
}
