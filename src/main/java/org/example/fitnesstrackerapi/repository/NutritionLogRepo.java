package org.example.fitnesstrackerapi.repository;

import org.example.fitnesstrackerapi.model.entity.NutritionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NutritionLogRepo extends JpaRepository<NutritionLog, Long> {
}
