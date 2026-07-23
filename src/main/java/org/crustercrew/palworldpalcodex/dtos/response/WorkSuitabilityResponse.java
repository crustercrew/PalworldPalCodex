package org.crustercrew.palworldpalcodex.dtos.response;

public record WorkSuitabilityResponse(
        Long id,
        String workType,
        String description,
        String iconUrl
) {
}
