package com.minerals.minerals_on_springboot.Services;

import com.minerals.minerals_on_springboot.Model.Mineral;
import com.minerals.minerals_on_springboot.Model.Research;
import com.minerals.minerals_on_springboot.Model.Researcher;
import com.minerals.minerals_on_springboot.Repositories.ResearchRepo;
import com.minerals.minerals_on_springboot.Repositories.ResearcherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ResearchService {
    @Autowired
    private ResearchRepo researchRepo;

    public Research create(Mineral mineral, String researchText){
        Research newResearch = new Research();
        newResearch.setMineral(mineral);
        newResearch.setText(researchText);
        return researchRepo.saveAndFlush(newResearch);
    }

    public void update(Integer researchId, Set<Researcher> updatedResearchers, String updatedText){
        Research research = researchRepo.findById(researchId).get();
        research.getResearchers().clear();
        research.getResearchers().addAll(updatedResearchers);
        research.setText(updatedText);
        researchRepo.saveAndFlush(research);
    }

    public void delete(Integer researchId){
        researchRepo.deleteById(researchId);
    }
}
