package org.crustercrew.palworldpalcodex.repositories;

import org.crustercrew.palworldpalcodex.entities.WorkSuitability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorkSuitabilityRepository extends JpaRepository<WorkSuitability, Long> {
    Optional<WorkSuitability> findByWorkTypeIgnoreCase(String workType);
}
