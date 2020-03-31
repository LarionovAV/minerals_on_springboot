package com.minerals.minerals_on_springboot.Repositories;

import com.minerals.minerals_on_springboot.Model.Field;
import com.minerals.minerals_on_springboot.Model.Mineral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FieldRepo extends JpaRepository<Field, Integer> {
    Field findByName(String name);
    List<Field> findAllByMineral(Mineral mineral);
}
