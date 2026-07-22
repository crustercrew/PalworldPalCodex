package org.crustercrew.palworldpalcodex.repositories;

import org.crustercrew.palworldpalcodex.entities.ActiveSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActiveSkillRepository extends JpaRepository<ActiveSkill, Long> {
}
