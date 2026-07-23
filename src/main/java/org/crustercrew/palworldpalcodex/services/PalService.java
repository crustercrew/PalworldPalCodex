package org.crustercrew.palworldpalcodex.services;

import lombok.RequiredArgsConstructor;
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

    @Transactional(readOnly = true)
    public PageResponse<PalResponse> searchPalsNative(
            String name, String palNumber, Integer foodConsumption, String alphaTitle,
            String partnerSkill, String workType, Integer minWorkLevel, String elementType,
            Integer minAttack, Pageable pageable) {

        Page<Pal> palPage = palRepository.findPalsByWithFilter(
                name, palNumber, foodConsumption, alphaTitle, partnerSkill,
                workType, minWorkLevel, elementType, minAttack, pageable
        );

        Page<PalResponse> dtoPage = palPage.map(this::toSummary);

        return PageResponse.from(dtoPage);
    }

    @Transactional(readOnly = true)
    public PalDetailResponse getPalDetailByPalNumber(String palnumber){
        Pal palDetail = palRepository.findPalByPalNumber(palnumber)
                .orElseThrow(() -> new RuntimeException("Pal not found"));
        return toDetail(palDetail);
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
                .map(this::toSummary)
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
                .map(this::toSummary)
                .toList();

        return toPageResponse(fullList, pageable);
    }
    @Transactional(readOnly = true)
    public PageResponse<PalResponse> getTopAttackers(Pageable pageable) {
        List<PalResponse> fullList = palRepository.findAll().stream()
                .filter(pal -> pal.getStat() != null && pal.getStat().getAttack() != null)
                .sorted(Comparator.comparing((Pal p) -> p.getStat().getAttack()).reversed())
                .map(this::toSummary)
                .toList();

        return toPageResponse(fullList, pageable);
    }

    @Transactional(readOnly = true)
    public PageResponse<PalResponse> getPalsByLootItem(String itemName, Pageable pageable) {
        Page<Pal> palPage = palRepository.findPalsByLootItemName(itemName, pageable);
        return PageResponse.from(palPage.map(this::toSummary));
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

    // Convert Entity -> List DTO (Multiple Pal Response)
    private PalResponse toSummary(Pal pal) {
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
    private PalDetailResponse toDetail(Pal pal) {
        if (pal == null) return null;

        List<ElementTypeResponse> elements = pal.getElements() != null ?
                pal.getElements().stream()
                        .map(e -> new ElementTypeResponse(e.getId(), e.getName(), e.getIconUrl()))
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