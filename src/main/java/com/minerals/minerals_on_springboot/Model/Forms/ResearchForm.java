package com.minerals.minerals_on_springboot.Model.Forms;

import com.minerals.minerals_on_springboot.Model.Researcher;

import java.util.List;

public class ResearchForm {
    String text;
    String newResqarchers;
    int researcher_1;
    int researcher_2;
    int researcher_3;

    public ResearchForm(){
    }

    public String getText() {
        return text;
    }

    public String getNewResqarchers() {
        return newResqarchers;
    }

    public void setNewResqarchers(String newResqarchers) {
        this.newResqarchers = newResqarchers;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getResearcher_1() {
        return researcher_1;
    }

    public void setResearcher_1(int researcher_1) {
        this.researcher_1 = researcher_1;
    }

    public int getResearcher_2() {
        return researcher_2;
    }

    public void setResearcher_2(int researcher_2) {
        this.researcher_2 = researcher_2;
    }

    public int getResearcher_3() {
        return researcher_3;
    }

    public void setResearcher_3(int researcher_3) {
        this.researcher_3 = researcher_3;
    }

    public void setResearchers(List<Researcher> researchers){
        switch (researchers.size()){
            case 3:
                researcher_3 = researchers.get(2).getResearcherID();
            case 2:
                researcher_2 = researchers.get(1).getResearcherID();
            case 1:
                researcher_1 = researchers.get(0).getResearcherID();
            case 0:
                break;
        }
    }
}
