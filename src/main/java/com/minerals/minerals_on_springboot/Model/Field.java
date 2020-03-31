package com.minerals.minerals_on_springboot.Model;

import javax.persistence.*;

@Entity
@Table(name = "field")
public class Field {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="fieldID")
    private Integer fieldID;
    @Basic(optional = false)
    private String name;

    @ManyToOne(targetEntity = Mineral.class)
    private Mineral mineral;

    public Field() {
    }

    public Integer getField_id() {
        return fieldID;
    }

    public void setField_id(Integer field_id) {
        this.fieldID = field_id;
    }

    public String getField_name() {
        return name;
    }

    public void setField_name(String field_name) {
        this.name = field_name;
    }

    public Mineral getMineral() {
        return mineral;
    }

    public void setMineral(Mineral mineral) {
        this.mineral = mineral;
    }
}
