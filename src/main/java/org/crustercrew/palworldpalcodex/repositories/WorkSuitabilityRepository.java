package org.crustercrew.palworldpalcodex.repositories;

import org.crustercrew.palworldpalcodex.entities.WorkSuitability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkSuitabilityRepository extends JpaRepository<WorkSuitability, Long> {
}
