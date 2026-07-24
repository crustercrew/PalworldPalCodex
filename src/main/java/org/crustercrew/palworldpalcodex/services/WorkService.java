package org.crustercrew.palworldpalcodex.services;

import lombok.RequiredArgsConstructor;
import org.crustercrew.palworldpalcodex.dtos.PalResponseMapper;
import org.crustercrew.palworldpalcodex.dtos.response.PageResponse;
import org.crustercrew.palworldpalcodex.dtos.response.PalResponse;
import org.crustercrew.palworldpalcodex.dtos.response.WorkSuitabilityResponse;
import org.crustercrew.palworldpalcodex.entities.Pal;
import org.crustercrew.palworldpalcodex.repositories.PalRepository;
import org.crustercrew.palworldpalcodex.repositories.WorkSuitabilityRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkService {
    private final WorkSuitabilityRepository workSuitabilityRepository;
    private final PalRepository palRepository;
    private final PalResponseMapper palMapper;

    // 1. Get Master List Pekerjaan Base
    @Cacheable(value = "work_suitabilities",key = "'all'")
    @Transactional(readOnly = true)
    public List<WorkSuitabilityResponse> getAllWorks() {
        return workSuitabilityRepository.findAll().stream()
                .map(w -> new WorkSuitabilityResponse(
                        w.getId(),
                        w.getWorkType(),
                        w.getDescription(),
                        w.getIconUrl()
                ))
                .toList();
    }

    // 2. Get Pals by Work Type & Min Work Level
    @Cacheable(value = "pals_by_work",key = "#workType+'_'+#minLevel")
    @Transactional(readOnly = true)
    public PageResponse<PalResponse> getPalsByWork(String workType, Integer minLevel, Pageable pageable) {
        Page<Pal> palPage = palRepository.findPalsByWorkType(workType, minLevel, pageable);
        return PageResponse.from(palPage.map(palMapper::toSummary));
    }
}
