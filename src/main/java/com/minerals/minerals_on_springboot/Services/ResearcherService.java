package com.minerals.minerals_on_springboot.Services;

import com.minerals.minerals_on_springboot.Model.Research;
import com.minerals.minerals_on_springboot.Model.Researcher;
import com.minerals.minerals_on_springboot.Repositories.ResearcherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ResearcherService {

    @Autowired
    private ResearcherRepo researcherRepo;

    public Researcher create(String researcherName){
        Researcher newResearcher = new Researcher();
        newResearcher.setName(researcherName);
        return researcherRepo.saveAndFlush(newResearcher);
    }

    public Researcher update(Integer researcherId, String newResearcherName, Set<Research> researchSet){
        Researcher updatingResearcher = researcherRepo.findById(researcherId).get();
        updatingResearcher.setName(newResearcherName);
        updatingResearcher.getResearchSet().clear();
        updatingResearcher.getResearchSet().addAll(researchSet);
        return researcherRepo.saveAndFlush(updatingResearcher);
    }

    public void delete(Integer researcherId){
        researcherRepo.deleteById(researcherId);
    }
}
