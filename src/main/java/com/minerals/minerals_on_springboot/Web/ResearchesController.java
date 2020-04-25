package com.minerals.minerals_on_springboot.Web;

import com.minerals.minerals_on_springboot.Config.Authentication;
import com.minerals.minerals_on_springboot.Model.Forms.ResearchForm;
import com.minerals.minerals_on_springboot.Model.Mineral;
import com.minerals.minerals_on_springboot.Model.Research;
import com.minerals.minerals_on_springboot.Model.Researcher;
import com.minerals.minerals_on_springboot.Repositories.MineralRepo;
import com.minerals.minerals_on_springboot.Repositories.ResearchRepo;
import com.minerals.minerals_on_springboot.Repositories.ResearcherRepo;
import com.minerals.minerals_on_springboot.Services.ResearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ResearchesController {
    @Autowired
    ResearchRepo researchRepo;
    @Autowired
    MineralRepo mineralRepo;
    @Autowired
    ResearcherRepo researcherRepo;

    @Autowired
    ResearchService researchService;
    Research selectedRes = null;
    @GetMapping("/minerals/updateResearches/{minId}")
    public String openUpdatingPage(
            @PathVariable("minId") Integer id,
            Model model){
        Mineral updatingMineral = mineralRepo.findById(id).get();

        model.addAttribute("displayedMineral", updatingMineral);
        model.addAttribute("researchList", researchRepo.findByMineral(updatingMineral));
        model.addAttribute("selectedResearch", null);
        model.addAttribute("researchersList", researcherRepo.findAll());
        model.addAttribute("newResearch", new ResearchForm());
        model.addAttribute("auth", Authentication.getAuth().isAuth());
        return "researchesUpdatePage";
    }

    @GetMapping("/minerals/updateResearches/{minId}/{resId}")
    public String updateResearch(
            @PathVariable("minId") Integer minId,
            @PathVariable("resId") Integer resId,
            Model model){
        ResearchForm rForm = new ResearchForm();
        Mineral updatingMineral = mineralRepo.findById(minId).get();
        Research updatingResearch = researchRepo.findById(resId).get();

        rForm.setResearchers(updatingResearch.getResearchers());
        rForm.setText(updatingResearch.getText());

        model.addAttribute("displayedMineral", updatingMineral);
        model.addAttribute("researchList", researchRepo.findByMineral(updatingMineral));
        model.addAttribute("selectedResearch", rForm);
        model.addAttribute("newResearch", new ResearchForm());
        model.addAttribute("researchersList", researcherRepo.findAll());
        model.addAttribute("auth", Authentication.getAuth().isAuth());

        return "researchesUpdatePage";
    }

    @PostMapping("/minerals/updateResearches/{minId}")
    public String newResearch(
            @PathVariable("minId") Integer minId,
            @ModelAttribute("newResearch") ResearchForm resForm
            ){
        researchService.create(mineralRepo.findById(minId).get(), resForm);
        return "redirect:/minerals/updateResearches/" + minId;
    }
}
