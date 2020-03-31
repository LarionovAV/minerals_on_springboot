package com.minerals.minerals_on_springboot.Services;

import com.minerals.minerals_on_springboot.Model.Field;
import com.minerals.minerals_on_springboot.Model.Mineral;
import com.minerals.minerals_on_springboot.Repositories.FieldRepo;
import com.minerals.minerals_on_springboot.Repositories.MineralRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FieldService {
    @Autowired
    private FieldRepo fieldRepo;
    @Autowired
    private MineralRepo mineralRepo;
    @Autowired
    private MineralService mineralService;

    public Field create(String fieldName, Mineral mineral){
        Field newField = new Field();
        newField.setField_name(fieldName);
        newField.setMineral(mineral);
        return fieldRepo.saveAndFlush(newField);
    }

    public Field update(Integer fieldId, Integer mineralId, String fieldName){
        Field updatedField = fieldRepo.findById(fieldId).get();
        updatedField.setField_name(fieldName);
        updatedField.setMineral(mineralRepo.findById(mineralId).get());

        return fieldRepo.saveAndFlush(updatedField);
    }

    public void delete(Integer id){
        fieldRepo.deleteById(id);
    }

}
