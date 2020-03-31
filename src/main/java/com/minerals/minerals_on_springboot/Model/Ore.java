package com.minerals.minerals_on_springboot.Model;

import javax.persistence.*;

@Entity
@Table(name = "ore")
public class Ore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "oreID")
    private Integer oreID;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;

    public Ore() {
    }

    public Integer getOreID() {
        return oreID;
    }

    public void setOreID(Integer oreID) {
        this.oreID = oreID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
