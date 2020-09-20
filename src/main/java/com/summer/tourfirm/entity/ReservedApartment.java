package com.summer.tourfirm.entity;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Objects;

@Entity
@Table(name = "reserved_apartments")
public class ReservedApartment {

    private static final long serialVersionUID = -1192837465647382L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "apartment_id", referencedColumnName = "id")
    private Apartment apartment;

    private ZonedDateTime dateIn;

    private ZonedDateTime dateOut;

    public ReservedApartment() {
    }

    public ReservedApartment(ZonedDateTime dateIn, ZonedDateTime dateOut) {
        this.dateIn = dateIn;
        this.dateOut = dateOut;
    }

    public Long getId() {
        return id;
    }

    public ReservedApartment setId(Long id) {
        this.id = id;
        return this;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public ReservedApartment setApartment(Apartment apartment) {
        this.apartment = apartment;
        return this;
    }

    public ZonedDateTime getDateIn() {
        return dateIn;
    }

    public ReservedApartment setDateIn(ZonedDateTime dateIn) {
        this.dateIn = dateIn;
        return this;
    }

    public ZonedDateTime getDateOut() {
        return dateOut;
    }

    public ReservedApartment setDateOut(ZonedDateTime dateOut) {
        this.dateOut = dateOut;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservedApartment that = (ReservedApartment) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
