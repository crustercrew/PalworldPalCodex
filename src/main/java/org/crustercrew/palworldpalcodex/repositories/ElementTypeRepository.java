package org.crustercrew.palworldpalcodex.repositories;

import org.crustercrew.palworldpalcodex.entities.ElementType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ElementTypeRepository extends JpaRepository<ElementType, Long> {
    Optional<ElementType> findByNameIgnoreCase(String name);
}
