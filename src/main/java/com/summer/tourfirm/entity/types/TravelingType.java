package com.summer.tourfirm.entity.types;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "travelingTypes")
public class TravelingType {

    private static final long serialVersionUID = -5647382910293847L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String travelingType;

    public TravelingType() {
    }

    public TravelingType(String travelingType) {
        this.travelingType = travelingType;
    }

    public Long getId() {
        return id;
    }

    public TravelingType setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTravelingType() {
        return travelingType;
    }

    public TravelingType setTravelingType(String travelingType) {
        this.travelingType = travelingType;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TravelingType that = (TravelingType) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
