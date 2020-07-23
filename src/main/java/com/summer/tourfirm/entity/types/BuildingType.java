package com.summer.tourfirm.entity.types;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "buildingTypes")
public class BuildingType {

    private static final long serialVersionUID = -8745466743271287L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String type;

    public BuildingType() {
    }

    public BuildingType(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public BuildingType setId(Long id) {
        this.id = id;
        return this;
    }

    public String getType() {
        return type;
    }

    public BuildingType setType(String type) {
        this.type = type;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BuildingType that = (BuildingType) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
