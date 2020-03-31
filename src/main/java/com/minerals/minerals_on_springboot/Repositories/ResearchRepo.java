package com.minerals.minerals_on_springboot.Repositories;

import com.minerals.minerals_on_springboot.Model.Mineral;
import com.minerals.minerals_on_springboot.Model.Research;
import com.minerals.minerals_on_springboot.Model.Researcher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResearchRepo extends JpaRepository<Research, Integer> {
    List<Research> findByResearchersContains(Researcher researcher);
    List<Research> findByMineral(Mineral mineral);
}
