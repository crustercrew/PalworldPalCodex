package org.crustercrew.palworldpalcodex.dtos;

import org.crustercrew.palworldpalcodex.dtos.response.*;
import org.crustercrew.palworldpalcodex.entities.ElementType;
import org.crustercrew.palworldpalcodex.entities.Pal;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class PalResponseMapper {
    // Convert Entity -> List DTO (Multiple Pal Response)
    public PalResponse toSummary(Pal pal) {
        if (pal == null) return null;

        List<String> elements = pal.getElements() != null ?
                pal.getElements().stream()
                        .map(ElementType::getName)
                        .toList() : Collections.emptyList();

        List<PalWorkSuitabilityResponse> works = pal.getWorkSuitabilities() != null ?
                pal.getWorkSuitabilities().stream()
                        .map(pws -> new PalWorkSuitabilityResponse(
                                pws.getWorkSuitability().getWorkType(),
                                pws.getWorkLevel(),
                                pws.getWorkSuitability().getIconUrl()
                        )).toList() : Collections.emptyList();

        return new PalResponse(
                pal.getId(),
                pal.getPalNumber(),
                pal.getName(),
                elements,
                pal.getAlphaTitle(),
                pal.getPartnerSkill(),
                pal.getFoodConsumption(),
                pal.getImageURL(),
                works
        );
    }

    // Convert Entity -> Detail DTO (Single or detail Pal Response)
    public PalDetailResponse toDetail(Pal pal) {
        if (pal == null) return null;

        List<ElementTypeResponse> elements = pal.getElements() != null ?
                pal.getElements().stream()
                        .map(e -> new ElementTypeResponse(
                                e.getId(),
                                e.getName(),
                                e.getIconUrl(),
                                e.getWeakAgainst() != null ? e.getWeakAgainst().getName() : null,
                                e.getStrongAgainst() != null ? e.getStrongAgainst().getName() : null
                        ))
                        .toList() : Collections.emptyList();

        PalStatsResponse statDto = pal.getStat() != null ?
                new PalStatsResponse(
                        pal.getStat().getHp(),
                        pal.getStat().getAttack(),
                        pal.getStat().getDefense(),
                        pal.getStat().getMinHp(),
                        pal.getStat().getMaxHp(),
                        pal.getStat().getMinAttack(),
                        pal.getStat().getMaxAttack(),
                        pal.getStat().getMinDefense(),
                        pal.getStat().getMaxDefense()
                ) : null;

        List<PalWorkSuitabilityResponse> works = pal.getWorkSuitabilities() != null ?
                pal.getWorkSuitabilities().stream()
                        .map(pws -> new PalWorkSuitabilityResponse(
                                pws.getWorkSuitability().getWorkType(),
                                pws.getWorkLevel(),
                                pws.getWorkSuitability().getIconUrl()
                        )).toList() : Collections.emptyList();

        List<PalLootItemResponse> loots = pal.getLootItems() != null ?
                pal.getLootItems().stream()
                        .map(pli -> new PalLootItemResponse(
                                pli.getItem().getName(),
                                pli.getItem().getCategory(),
                                pli.getDropTotal(),
                                pli.getDropRate(),
                                pli.getItem().getIconUrl()
                        )).toList() : Collections.emptyList();

        List<PalActiveSkillsResponse> skills = pal.getActiveSkills() != null ?
                pal.getActiveSkills().stream()
                        .map(pas -> new PalActiveSkillsResponse(
                                pas.getActiveSkill().getName(),
                                pas.getActiveSkill().getElement() != null ? pas.getActiveSkill().getElement().getName() : null,
                                pas.getActiveSkill().getPower(),
                                pas.getActiveSkill().getCooldownSeconds(),
                                pas.getUnlockLevel(),
                                pas.getActiveSkill().getDescription()
                        )).toList() : Collections.emptyList();

        return new PalDetailResponse(
                pal.getId(),
                pal.getPalNumber(),
                pal.getName(),
                elements,
                pal.getAlphaTitle(),
                pal.getPartnerSkill(),
                pal.getFoodConsumption(),
                pal.getEggName(),
                pal.getEggSize(),
                pal.getBreedPower(),
                pal.getDescription(),
                pal.getImageURL(),
                statDto,
                works,
                loots,
                skills
        );
    }
}
