package com.minerals.minerals_on_springboot.Repositories;

import com.minerals.minerals_on_springboot.Model.Mineral;
import com.minerals.minerals_on_springboot.Model.Ore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MineralRepo extends JpaRepository<Mineral, Integer> {
    Mineral findByName(String name);
    List<Mineral> findByOre(Ore ore);
}
