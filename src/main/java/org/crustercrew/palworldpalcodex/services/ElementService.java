package org.crustercrew.palworldpalcodex.services;


import lombok.RequiredArgsConstructor;
import org.crustercrew.palworldpalcodex.dtos.PalResponseMapper;
import org.crustercrew.palworldpalcodex.dtos.response.ElementTypeResponse;
import org.crustercrew.palworldpalcodex.dtos.response.PageResponse;
import org.crustercrew.palworldpalcodex.dtos.response.PalResponse;
import org.crustercrew.palworldpalcodex.entities.Pal;
import org.crustercrew.palworldpalcodex.repositories.ElementTypeRepository;
import org.crustercrew.palworldpalcodex.repositories.PalRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ElementService {
    private final ElementTypeRepository elementTypeRepository;
    private final PalRepository palRepository;
    private final PalResponseMapper palMapper = new PalResponseMapper();

    // 1. Get Master List Elemen
    @Transactional(readOnly = true)
    public List<ElementTypeResponse> getAllElements() {
        return elementTypeRepository.findAll().stream()
                .map(e -> new ElementTypeResponse(
                        e.getId(),
                        e.getName(),
                        e.getIconUrl(),
                        e.getWeakAgainst() != null ? e.getWeakAgainst().getName() : null,
                        e.getStrongAgainst() != null ? e.getStrongAgainst().getName() : null
                ))
                .toList();
    }

    // 2. Get Pals by Element Name (Terpaginasi)
    @Transactional(readOnly = true)
    public PageResponse<PalResponse> getPalsByElement(String elementName, Pageable pageable) {
        Page<Pal> palPage = palRepository.findPalsByElementName(elementName, pageable);
        return PageResponse.from(palPage.map(palMapper::toSummary));
    }
}
