package com.minerals.minerals_on_springboot.Model;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="mineral")
public class Mineral {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "minID")
    private Integer minID;

    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Column(name = "descr")
    private String descr;
    @ManyToOne(targetEntity = Ore.class, fetch = FetchType.EAGER)
    @JoinColumn(name="oreID")
    private Ore ore;

    public Mineral() {
    }

    public Integer getMinID() {
        return minID;
    }

    public void setMinID(Integer minID) {
        this.minID = minID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public Ore getOre() {
        return ore;
    }

    public void setOre(Ore ore) {
        this.ore = ore;
    }
}
