package com.minerals.minerals_on_springboot.Model.Forms;

import com.minerals.minerals_on_springboot.Model.Field;
import com.minerals.minerals_on_springboot.Model.Mineral;
import com.minerals.minerals_on_springboot.Repositories.FieldRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MineralForm {

    private Integer id;
    private String name;
    private String description;
    private String fields;
    private String oreOfMineral;
    private String newOreName;

    public MineralForm(){ }
    public MineralForm(Mineral mineral, FieldRepo fieldRepo){
        fields = "";
        id = mineral.getMinID();
        name = mineral.getName();
        description = mineral.getDescr();
        List<Field> fieldsList = fieldRepo.findAllByMineral(mineral);
        for(Field field : fieldsList){
            fields += field.getField_name() + '\n';
        }
        if (mineral.getOre() != null)
            oreOfMineral = mineral.getOre().getName();
        else
            oreOfMineral = "";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFields() {
        return fields;
    }

    public void setFields(String fields) {
        this.fields = fields;
    }

    public String getOreOfMineral() {
        return oreOfMineral;
    }

    public void setOreOfMineral(String ore) {
        this.oreOfMineral = ore;
    }

    public String getNewOreName() {
        return newOreName;
    }

    public void setNewOreName(String newOreName) {
        this.newOreName = newOreName;
    }
}
