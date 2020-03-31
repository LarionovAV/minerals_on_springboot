package com.minerals.minerals_on_springboot.Model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "research")
public class Research {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "researchID")
    private  Integer researchID;
    @Column(name = "text")
    private String text;

    @ManyToOne(targetEntity = Mineral.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "minID", nullable = false)
    private Mineral mineral;

    @ManyToMany
    @JoinTable(
            name = "research_link",
            joinColumns  = {@JoinColumn(name = "researchID")},
            inverseJoinColumns ={@JoinColumn(name = "researcherID")})
    private Set<Researcher> researchers;

    public Research() {
    }

    public Integer getResearchID() {
        return researchID;
    }

    public void setResearchID(Integer researchID) {
        this.researchID = researchID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Mineral getMineral() {
        return mineral;
    }

    public void setMineral(Mineral mineral) {
        this.mineral = mineral;
    }

    public Set<Researcher> getResearchers() {
        return researchers;
    }

    public void setResearchers(Set<Researcher> researchers) {
        this.researchers = researchers;
    }

    public String getResearchersInRow(){
        String researchersRow = "";
        for (Researcher res: researchers) {
            researchersRow += res.getName() + "; ";
        }

        return researchersRow.trim();
    }
}
