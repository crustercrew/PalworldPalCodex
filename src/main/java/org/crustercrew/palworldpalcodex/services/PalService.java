package org.crustercrew.palworldpalcodex.services;

import lombok.RequiredArgsConstructor;
import org.crustercrew.palworldpalcodex.dtos.PalResponseMapper;
import org.crustercrew.palworldpalcodex.dtos.response.*;
import org.crustercrew.palworldpalcodex.entities.ElementType;
import org.crustercrew.palworldpalcodex.entities.Pal;
import org.crustercrew.palworldpalcodex.repositories.ElementTypeRepository;
import org.crustercrew.palworldpalcodex.repositories.PalRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PalService {

    private final PalRepository palRepository;
    private final ElementTypeRepository elementTypeRepository;
    private final PalResponseMapper palMapper = new PalResponseMapper();

    @Transactional(readOnly = true)
    public PageResponse<PalResponse> searchPalsNative(
            String name, String palNumber, Integer foodConsumption, String alphaTitle,
            String partnerSkill, String workType, Integer minWorkLevel, String elementType,
            Integer minAttack, Pageable pageable) {

        Page<Pal> palPage = palRepository.findPalsByWithFilter(
                name, palNumber, foodConsumption, alphaTitle, partnerSkill,
                workType, minWorkLevel, elementType, minAttack, pageable
        );

        Page<PalResponse> dtoPage = palPage.map(palMapper::toSummary);

        return PageResponse.from(dtoPage);
    }

    @Transactional(readOnly = true)
    public PalDetailResponse getPalDetailByPalNumber(String palnumber){
        Pal palDetail = palRepository.findPalByPalNumber(palnumber)
                .orElseThrow(() -> new RuntimeException("Pal not found"));
        return palMapper.toDetail(palDetail);
    }

    @Transactional(readOnly = true)
    public PageResponse<PalResponse> getBaseWorkerRecommendations(String workType, Pageable pageable) {
        List<PalResponse> fullList = palRepository.findAll().stream()
                .filter(pal -> pal.getWorkSuitabilities() != null &&
                        pal.getWorkSuitabilities().stream()
                                .anyMatch(pws -> pws.getWorkSuitability().getWorkType().equalsIgnoreCase(workType)))
                .sorted((p1, p2) -> {
                    int lvl1 = getWorkLevel(p1, workType);
                    int lvl2 = getWorkLevel(p2, workType);
                    if (lvl1 != lvl2) return Integer.compare(lvl2, lvl1);
                    return Integer.compare(
                            p1.getFoodConsumption() != null ? p1.getFoodConsumption() : 0,
                            p2.getFoodConsumption() != null ? p2.getFoodConsumption() : 0
                    );
                })
                .map(palMapper::toSummary)
                .toList();

        return toPageResponse(fullList, pageable);
    }

    @Transactional(readOnly = true)
    public PageResponse<PalResponse> getCounterPals(String targetElementName, Pageable pageable) {
        ElementType targetElement = elementTypeRepository.findByNameIgnoreCase(targetElementName)
                .orElseThrow(() -> new RuntimeException("Element type not found: " + targetElementName));

        ElementType counterElement = targetElement.getWeakAgainst();

        if (counterElement == null) {
            return toPageResponse(Collections.emptyList(), pageable);
        }

        List<PalResponse> fullList = palRepository.findAll().stream()
                .filter(pal -> pal.getElements() != null &&
                        pal.getElements().stream().anyMatch(e -> e.getId().equals(counterElement.getId())))
                .sorted(Comparator.comparing((Pal p) -> p.getStat() != null && p.getStat().getAttack() != null ?
                        p.getStat().getAttack() : 0).reversed())
                .map(palMapper::toSummary)
                .toList();

        return toPageResponse(fullList, pageable);
    }
    @Transactional(readOnly = true)
    public PageResponse<PalResponse> getTopAttackers(Pageable pageable) {
        List<PalResponse> fullList = palRepository.findAll().stream()
                .filter(pal -> pal.getStat() != null && pal.getStat().getAttack() != null)
                .sorted(Comparator.comparing((Pal p) -> p.getStat().getAttack()).reversed())
                .map(palMapper::toSummary)
                .toList();

        return toPageResponse(fullList, pageable);
    }

    @Transactional(readOnly = true)
    public PageResponse<PalResponse> getPalsByLootItem(String itemName, Pageable pageable) {
        Page<Pal> palPage = palRepository.findPalsByLootItemName(itemName, pageable);
        return PageResponse.from(palPage.map(palMapper::toSummary));
    }


    // MAPPER
    private <T> PageResponse<T> toPageResponse(List<T> list, Pageable pageable) {
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), list.size());

        List<T> pageContent = (start <= list.size()) ? list.subList(start, end) : Collections.emptyList();
        Page<T> page = new PageImpl<>(pageContent, pageable, list.size());

        return PageResponse.from(page);
    }

    private int getWorkLevel(Pal pal, String workType) {
        return pal.getWorkSuitabilities().stream()
                .filter(pws -> pws.getWorkSuitability().getWorkType().equalsIgnoreCase(workType))
                .mapToInt(pws -> pws.getWorkLevel() != null ? pws.getWorkLevel() : 0)
                .findFirst()
                .orElse(0);
    }


}