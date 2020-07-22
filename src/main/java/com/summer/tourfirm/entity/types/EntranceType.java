package com.summer.tourfirm.entity.types;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "enterTypes")
public class EntranceType {

    private static final long serialVersionUID = -8745466712345678L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String enterType;

    public EntranceType() {
    }

    public EntranceType(String enterType) {
        this.enterType = enterType;
    }

    public Long getId() {
        return id;
    }

    public EntranceType setId(Long id) {
        this.id = id;
        return this;
    }

    public String getEnterType() {
        return enterType;
    }

    public EntranceType setEnterType(String enterType) {
        this.enterType = enterType;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntranceType that = (EntranceType) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
