package com.minerals.minerals_on_springboot.Model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "researcher")
public class Researcher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "researcherID")
    private  Integer researcherID;
    @Column(name = "name")
    private String name;

    @ManyToMany(targetEntity = Research.class, fetch = FetchType.EAGER)
    @JoinTable(
            name = "research_link",
            joinColumns ={@JoinColumn(name = "researcherID")},
            inverseJoinColumns = {@JoinColumn(name = "researchID")}
    )
    private Set<Research> researchSet = new HashSet<Research>();

    public Researcher() {
    }

    public Integer getResearcherID() {
        return researcherID;
    }

    public void setResearcherID(Integer researcherID) {
        this.researcherID = researcherID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Research> getResearchSet() {
        return researchSet;
    }

    public void setResearchSet(Set<Research> researchSet) {
        this.researchSet = researchSet;
    }
}
