package com.summer.tourfirm.entity;


import javax.persistence.*;

@Entity
@Table(name = "enterprises")
public class Enterprise {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer some_value;
    private String some_string;
    private Boolean some_bool;
}
