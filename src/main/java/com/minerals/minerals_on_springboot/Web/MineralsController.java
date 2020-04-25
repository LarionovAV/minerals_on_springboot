package com.minerals.minerals_on_springboot.Web;

import com.minerals.minerals_on_springboot.Config.Authentication;
import com.minerals.minerals_on_springboot.Model.Forms.MineralForm;
import com.minerals.minerals_on_springboot.Model.Mineral;
import com.minerals.minerals_on_springboot.Model.Ore;
import com.minerals.minerals_on_springboot.Repositories.*;
import com.minerals.minerals_on_springboot.Services.FieldService;
import com.minerals.minerals_on_springboot.Services.MineralService;
import com.minerals.minerals_on_springboot.Services.OreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

@Controller
public class MineralsController {

    @Autowired
    MineralRepo mineralRepo;
    @Autowired
    FieldRepo fieldRepo;
    @Autowired
    OreRepo oreRepo;
    @Autowired
    ResearchRepo researchRepo;
    @Autowired
    ResearcherRepo researcherRepo;

    @Autowired
    MineralService mineralService;
    @Autowired
    OreService oreService;
    @Autowired
    FieldService fieldService;

    @GetMapping("/minerals")
    public String displayMineralsList(Model model){
        model.addAttribute("newMineral", new MineralForm());
        model.addAttribute("oreList", oreRepo.findAll());
        model.addAttribute("mineralsList", mineralRepo.findAll());
        model.addAttribute("auth", Authentication.getAuth().isAuth());
        return "minerals";
    }

    @PostMapping("/minerals")
    public String addMineral(
            @ModelAttribute("newMineral") MineralForm mineral,
            @RequestParam("file") MultipartFile file) throws IOException, URISyntaxException {

        Ore ore = null;
        if (mineral.getOreOfMineral().isEmpty()) {
            if (!mineral.getNewOreName().isEmpty()) {
                ore = oreService.create(mineral.getNewOreName());
            }
        }
        else
            ore = oreRepo.findByName(mineral.getOreOfMineral());
        Mineral added = mineralService.create(mineral.getName(), ore, mineral.getDescription());
        String[] fields = mineral.getFields().split("\n");
        for (String field : fields) {
            if (!field.isEmpty())
                fieldService.create(field, added);
        }
        if (file != null) {
            file.transferTo(
                    new File(
                            getClass().getClassLoader().
                                    getResource("static/img/minerals").getPath() +
                                    "/image_for_" + added.getMinID() + ".jpg"
                    )
            );
        }

        return "redirect:minerals";
    }

    public String deleteMineral(Integer id){
        mineralService.delete(id);
        return "redirect:/minerals";
    }
    public String updateMineral(MineralForm form, MultipartFile file) throws IOException {
        if (file != null)
            file.transferTo(
                    new File(
                            getClass().getClassLoader().
                                    getResource("static/img/minerals").getPath() +
                                    "/image_for_" + form.getId() + ".jpg"
                    )
            );
        mineralService.update(form);

        return "redirect:/minerals/" + form.getId();
    }



    @GetMapping("/minerals/{id}")
    public String displaySingleMineral(@PathVariable("id") Integer id, Model model){
        Mineral displayedMineral = mineralRepo.findById(id).get();

        model.addAttribute("displayedMineral", new MineralForm(displayedMineral, fieldRepo));
        model.addAttribute("fieldsList", fieldRepo.findAllByMineral(displayedMineral));
        model.addAttribute("researchesList", researchRepo.findByMineral(displayedMineral));
        model.addAttribute("oreList", oreRepo.findAll());
        model.addAttribute("auth", Authentication.getAuth().isAuth());

        return "singleMineralInfo";
    }

    @PostMapping(value = "/minerals/{id}")
    public String actionWithSingleMineral(
            @PathVariable("id") Integer id,
            @RequestParam("method") String action,
            @RequestParam("file") MultipartFile file,
            @ModelAttribute(name = "displayedMineral") MineralForm form
                                         ) throws IOException {

        if (action.equals("delete"))
            return deleteMineral(id);
        else if (action.equals("update"))
            return updateMineral(form, file);

        return "redirect:/minerals";
    }
}
