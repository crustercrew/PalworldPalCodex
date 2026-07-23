package org.crustercrew.palworldpalcodex.dtos.response;

public record PalStatsResponse(
        Integer hp,
        Integer attack,
        Integer defense,
        Integer minHp,
        Integer maxHp,
        Integer minAttack,
        Integer maxAttack,
        Integer minDefense,
        Integer maxDefense
) {
}
