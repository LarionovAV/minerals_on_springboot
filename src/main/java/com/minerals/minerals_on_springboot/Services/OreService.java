package com.minerals.minerals_on_springboot.Services;

import com.minerals.minerals_on_springboot.Model.Ore;
import com.minerals.minerals_on_springboot.Repositories.OreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OreService {
    @Autowired
    private OreRepo oreRepo;

    public Ore create(String oreName){
        Ore newOre = new Ore();
        newOre.setName(oreName);

        return oreRepo.saveAndFlush(newOre);
    }

    public Ore update(Integer oreId, String newOreName){
        Ore updatingOre = oreRepo.findById(oreId).get();
        updatingOre.setName(newOreName);
        return oreRepo.saveAndFlush(updatingOre);
    }

    public void delete(Integer oreId){
        oreRepo.deleteById(oreId);
    }
}
