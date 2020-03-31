package com.minerals.minerals_on_springboot.Repositories;

import com.minerals.minerals_on_springboot.Model.Research;
import com.minerals.minerals_on_springboot.Model.Researcher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResearcherRepo extends JpaRepository<Researcher, Integer> {
    Researcher findByName(String name);
    List<Researcher> findByResearchSetContaining(Research research);
}
