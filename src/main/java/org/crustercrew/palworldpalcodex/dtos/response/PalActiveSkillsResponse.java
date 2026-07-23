package org.crustercrew.palworldpalcodex.dtos.response;

public record PalActiveSkillsResponse(
        String skillName,
        String elementName,
        Integer power,
        Integer cooldownSeconds,
        Integer unlockLevel,
        String description
) {
}
