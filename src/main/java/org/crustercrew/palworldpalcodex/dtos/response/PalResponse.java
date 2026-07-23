package org.crustercrew.palworldpalcodex.dtos.response;
import java.util.*;


public record PalResponse(
        Long id,
        String palNumber,
        String name,
        List<String> elements,
        String alphaTitle,
        String partnerSkill,
        Integer foodConsumption,
        String imageURL,
        List<PalWorkSuitabilityResponse> workSuitabilities
) {
}
