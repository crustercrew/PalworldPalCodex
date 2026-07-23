package org.crustercrew.palworldpalcodex.dtos.response;
import java.util.*;
public record PalDetailResponse(
        Long id,
        String palNumber,
        String name,
        List<ElementTypeResponse> elements,
        String alphaTitle,
        String partnerSkill,
        Integer foodConsumption,
        String eggName,
        String eggSize,
        Integer breedPower,
        String description,
        String imageURL,
        PalStatsResponse stats,
        List<PalWorkSuitabilityResponse> workSuitabilities,
        List<PalLootItemResponse> lootItems,
        List<PalActiveSkillsResponse> activeSkills
) {
}
