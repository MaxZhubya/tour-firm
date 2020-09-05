package com.summer.tourfirm.entity.types;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "traveling_types")
public class TravelingType {

    private static final long serialVersionUID = -5647382910293847L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String type;

    public TravelingType() {
    }

    public TravelingType(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public TravelingType setId(Long id) {
        this.id = id;
        return this;
    }

    public String getType() {
        return type;
    }

    public TravelingType setType(String type) {
        this.type = type;
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
