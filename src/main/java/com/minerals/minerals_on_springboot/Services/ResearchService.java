package com.minerals.minerals_on_springboot.Services;

import com.minerals.minerals_on_springboot.Model.Forms.ResearchForm;
import com.minerals.minerals_on_springboot.Model.Mineral;
import com.minerals.minerals_on_springboot.Model.Research;
import com.minerals.minerals_on_springboot.Model.Researcher;
import com.minerals.minerals_on_springboot.Repositories.ResearchRepo;
import com.minerals.minerals_on_springboot.Repositories.ResearcherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ResearchService {
    @Autowired
    private ResearchRepo researchRepo;
    @Autowired
    private ResearcherRepo researcherRepo;
    @Autowired
    private ResearcherService researcherService;

    public Research create(Mineral mineral, ResearchForm researchForm){
        Research newResearch = new Research();
        newResearch.setMineral(mineral);
        newResearch.setText(researchForm.getText());
        List<Researcher> researchers = new ArrayList<>();
        int newResCount = 0;
        if (researchForm.getResearcher_1() == -1)
            newResCount++;
        else
            researchers.add(researcherRepo.findById(researchForm.getResearcher_1()).get());

        if (researchForm.getResearcher_2() == -1)
            newResCount++;
        else
            researchers.add(researcherRepo.findById(researchForm.getResearcher_2()).get());

        if (researchForm.getResearcher_3() == -1)
            newResCount++;
        else
            researchers.add(researcherRepo.findById(researchForm.getResearcher_2()).get());

        String[] newRes = researchForm.getNewResqarchers().split(",");


        for (int i = 0; i < newRes.length && newResCount > 0; i++, newResCount--){
            String name = newRes[i].trim();
            if (!name.isEmpty())
                researchers.add(researcherService.create(name));
        }

        newResearch.setResearchers(researchers);
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
