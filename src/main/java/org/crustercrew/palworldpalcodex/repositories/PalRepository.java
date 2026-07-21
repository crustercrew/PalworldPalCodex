package org.crustercrew.palworldpalcodex.repositories;

import org.crustercrew.palworldpalcodex.entities.Pal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface PalRepository extends JpaRepository<Pal, Long> {
    Optional<Pal> findByPalNumber(Integer palNumber);
    List<Pal> findByNameContainingIgnoreCase(String name);
}