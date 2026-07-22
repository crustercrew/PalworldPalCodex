package org.crustercrew.palworldpalcodex.repositories;

import org.crustercrew.palworldpalcodex.entities.PalWorkSuitability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PalWorkSuitabilityRepository extends JpaRepository<PalWorkSuitability, Long> {
}
