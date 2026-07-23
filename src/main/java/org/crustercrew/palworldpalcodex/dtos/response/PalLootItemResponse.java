package org.crustercrew.palworldpalcodex.dtos.response;

public record PalLootItemResponse(
    String itemName,
    String category,
    String dropTotal,
    Double dropRate,
    String iconUrl
){}
