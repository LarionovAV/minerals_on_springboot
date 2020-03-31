package com.minerals.minerals_on_springboot.Repositories;

import com.minerals.minerals_on_springboot.Model.Ore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OreRepo extends JpaRepository<Ore, Integer> {
    Ore findByName(String name);
}
