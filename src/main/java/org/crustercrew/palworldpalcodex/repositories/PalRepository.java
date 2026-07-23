package org.crustercrew.palworldpalcodex.repositories;

import org.crustercrew.palworldpalcodex.entities.Pal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import java.util.*;

@Repository
public interface PalRepository extends JpaRepository<Pal, Long> {

    Optional<Pal> findPalByPalNumber(String palNumber);

    @Query(value = """
            SELECT DISTINCT p.* FROM pals p
            LEFT JOIN pal_stats ps ON p.id = ps.pal_id
            LEFT JOIN pal_work_suitabilities pws ON p.id = pws.pal_id
            LEFT JOIN work_suitabilities ws ON pws.work_suitability_id = ws.id
            LEFT JOIN pal_elements pe ON p.id = pe.pal_id
            LEFT JOIN element_types et ON pe.element_id = et.id
			WHERE (CAST(:name AS VARCHAR) IS NULL OR p.name ILIKE CONCAT('%', CAST(:name AS VARCHAR), '%'))
	          AND (CAST(:palNumber AS VARCHAR) IS NULL OR p.pal_number = CAST(:palNumber AS VARCHAR))
	          AND (CAST(:foodConsumption AS INTEGER) IS NULL OR p.food_consumption = CAST(:foodConsumption AS INTEGER))
	          AND (CAST(:alphaTitle AS VARCHAR) IS NULL OR p.alpha_title ILIKE CONCAT('%', CAST(:alphaTitle AS VARCHAR), '%'))
	          AND (CAST(:partnerSkill AS VARCHAR) IS NULL OR p.partner_skill ILIKE CONCAT('%', CAST(:partnerSkill AS VARCHAR), '%'))
	          AND (CAST(:workType AS VARCHAR) IS NULL OR ws.work_type = CAST(:workType AS VARCHAR))
	          AND (CAST(:minWorkLevel AS INTEGER) IS NULL OR pws.work_level >= CAST(:minWorkLevel AS INTEGER))
	          AND (CAST(:elementType AS VARCHAR) IS NULL OR et.name = CAST(:elementType AS VARCHAR))
	          AND (CAST(:minAttack AS INTEGER) IS NULL OR ps.attack >= CAST(:minAttack AS INTEGER))
        """,
            countQuery = """
            SELECT count(DISTINCT p.id) FROM pals p
            LEFT JOIN pal_stats ps ON p.id = ps.pal_id
            LEFT JOIN pal_work_suitabilities pws ON p.id = pws.pal_id
            LEFT JOIN work_suitabilities ws ON pws.work_suitability_id = ws.id
            LEFT JOIN pal_elements pe ON p.id = pe.pal_id
            LEFT JOIN element_types et ON pe.element_id = et.id
			WHERE (CAST(:name AS VARCHAR) IS NULL OR p.name ILIKE CONCAT('%', CAST(:name AS VARCHAR), '%'))
	          AND (CAST(:palNumber AS VARCHAR) IS NULL OR p.pal_number = CAST(:palNumber AS VARCHAR))
	          AND (CAST(:foodConsumption AS INTEGER) IS NULL OR p.food_consumption = CAST(:foodConsumption AS INTEGER))
	          AND (CAST(:alphaTitle AS VARCHAR) IS NULL OR p.alpha_title ILIKE CONCAT('%', CAST(:alphaTitle AS VARCHAR), '%'))
	          AND (CAST(:partnerSkill AS VARCHAR) IS NULL OR p.partner_skill ILIKE CONCAT('%', CAST(:partnerSkill AS VARCHAR), '%'))
	          AND (CAST(:workType AS VARCHAR) IS NULL OR ws.work_type = CAST(:workType AS VARCHAR))
	          AND (CAST(:minWorkLevel AS INTEGER) IS NULL OR pws.work_level >= CAST(:minWorkLevel AS INTEGER))
	          AND (CAST(:elementType AS VARCHAR) IS NULL OR et.name = CAST(:elementType AS VARCHAR))
	          AND (CAST(:minAttack AS INTEGER) IS NULL OR ps.attack >= CAST(:minAttack AS INTEGER))
        """,
            nativeQuery = true)
    Page<Pal> findPalsByWithFilter(
            @Param("name") String name,
            @Param("palNumber") String palNumber,
            @Param("foodConsumption") Integer foodConsumption,
            @Param("alphaTitle") String alphaTitle,
            @Param("partnerSkill") String partnerSkill,
            @Param("workType") String workType,
            @Param("minWorkLevel") Integer minWorkLevel,
            @Param("elementType") String elementType,
            @Param("minAttack") Integer minAttack,
            Pageable pageable
    );

    @Query(value = """
        SELECT DISTINCT p.* FROM pals p
        INNER JOIN pal_loot_items pli ON p.id = pli.pal_id
        INNER JOIN items i ON pli.item_id = i.id
        WHERE i.name ILIKE CONCAT('%', :itemName, '%')
        """, nativeQuery = true)
    Page<Pal> findPalsByLootItemName(@Param("itemName") String itemName,Pageable pageable);
}
