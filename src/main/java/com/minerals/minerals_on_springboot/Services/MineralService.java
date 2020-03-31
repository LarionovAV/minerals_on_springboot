package com.minerals.minerals_on_springboot.Services;

import com.minerals.minerals_on_springboot.Model.Field;
import com.minerals.minerals_on_springboot.Model.Forms.MineralForm;
import com.minerals.minerals_on_springboot.Model.Mineral;
import com.minerals.minerals_on_springboot.Model.Ore;
import com.minerals.minerals_on_springboot.Model.Research;
import com.minerals.minerals_on_springboot.Repositories.FieldRepo;
import com.minerals.minerals_on_springboot.Repositories.MineralRepo;
import com.minerals.minerals_on_springboot.Repositories.OreRepo;
import com.minerals.minerals_on_springboot.Repositories.ResearchRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MineralService {
    @Autowired
    private MineralRepo mineralRepo;
    @Autowired
    private OreRepo oreRepo;
    @Autowired
    private OreService oreService;

    @Autowired
    private FieldRepo fieldRepo;
    @Autowired
    private FieldService fieldService;

    @Autowired
    private ResearchRepo researchRepo;
    @Autowired
    private ResearchService researchService;

    public Mineral create(
            String mineralName,
            Ore ore,
            String mineralDesc){
        Mineral newMineral = new Mineral();
        newMineral.setName(mineralName);
        newMineral.setDescr(mineralDesc);
        newMineral.setOre(ore);

        return mineralRepo.saveAndFlush(newMineral);
    }

    public Mineral update(
            MineralForm form){
        Mineral updatingMineral = mineralRepo.findById(form.getId()).get();
        List<Field> curFields = fieldRepo.findAllByMineral(updatingMineral);
        updatingMineral.setName(form.getName());
        updatingMineral.setDescr(form.getDescription());
        Ore newOre = oreRepo.findByName(form.getOreOfMineral());
        if (newOre != null)
            updatingMineral.setOre(newOre);
        else {
            if (!form.getNewOreName().isEmpty())
                updatingMineral.setOre(oreService.create(form.getNewOreName()));
            else
                updatingMineral.setOre(null);
        }

        Set<String> newFields = new HashSet<String>();
        for (String fieldName : form.getFields().split("\n")){
            if (!fieldName.trim().isEmpty())
                newFields.add(fieldName);
        };

        for (Field field: curFields) {
            if (newFields.contains(field.getField_name()))
                newFields.remove(field.getField_name());
            else
                fieldRepo.deleteById(field.getField_id());
        }

        for (String newField:newFields) {
            fieldService.create(newField, updatingMineral);
        }

        return mineralRepo.saveAndFlush(updatingMineral);
    }

    public void delete(Integer id){
        mineralRepo.deleteById(id);
    }


}
