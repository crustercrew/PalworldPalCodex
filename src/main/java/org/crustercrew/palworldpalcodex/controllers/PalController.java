package org.crustercrew.palworldpalcodex.controllers;

import lombok.RequiredArgsConstructor;
import org.crustercrew.palworldpalcodex.dtos.response.APIResponse;
import org.crustercrew.palworldpalcodex.dtos.response.PageResponse;
import org.crustercrew.palworldpalcodex.dtos.response.PalDetailResponse;
import org.crustercrew.palworldpalcodex.dtos.response.PalResponse;
import org.crustercrew.palworldpalcodex.services.PalService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/pals")
@RequiredArgsConstructor
public class PalController {
    private final PalService palService;

    @GetMapping()
    public ResponseEntity<APIResponse<PageResponse<PalResponse>>> searchNative(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String palNumber,
            @RequestParam(required = false) Integer foodConsumption,
            @RequestParam(required = false) String alphaTitle,
            @RequestParam(required = false) String partnerSkill,
            @RequestParam(required = false) String workType,
            @RequestParam(required = false) Integer minWorkLevel,
            @RequestParam(required = false) String elementType,
            @RequestParam(required = false) Integer minAttack,
            Pageable pageable) {

        PageResponse<PalResponse> result = palService.searchPalsNative(
                name, palNumber, foodConsumption, alphaTitle, partnerSkill,
                workType, minWorkLevel, elementType, minAttack, pageable);

        return ResponseEntity.ok(APIResponse.success("Success search pals", result));
    }

    // 6. Detail Pal (Single Object)
    @GetMapping("/{palNumber}")
    public ResponseEntity<APIResponse<PalDetailResponse>> getByPalNumber(@PathVariable String palNumber) {
        PalDetailResponse result = palService.getPalDetailByPalNumber(palNumber);
        return ResponseEntity.ok(APIResponse.success("Pal detail found", result));
    }

    @GetMapping("/recommendations/base-workers")
    public ResponseEntity<APIResponse<PageResponse<PalResponse>>> getBaseWorkers(
            @RequestParam String workType,
            Pageable pageable) {

        PageResponse<PalResponse> result = palService.getBaseWorkerRecommendations(workType, pageable);
        return ResponseEntity.ok(APIResponse.success("Base workers recommendations for " + workType, result));
    }

    @GetMapping("/recommendations/counters")
    public ResponseEntity<APIResponse<PageResponse<PalResponse>>> getCounters(
            @RequestParam String targetElement,
            Pageable pageable) {

        PageResponse<PalResponse> result = palService.getCounterPals(targetElement, pageable);
        return ResponseEntity.ok(APIResponse.success("Counter pals for " + targetElement, result));
    }

    @GetMapping("/stats/top-attackers")
    public ResponseEntity<APIResponse<PageResponse<PalResponse>>> getTopAttackers(Pageable pageable) {
        PageResponse<PalResponse> result = palService.getTopAttackers(pageable);
        return ResponseEntity.ok(APIResponse.success("Top attackers retrieved", result));
    }

    @GetMapping("/loots/search")
    public ResponseEntity<APIResponse<PageResponse<PalResponse>>> getPalsByLoot(
            @RequestParam String itemName,
            Pageable pageable) {

        PageResponse<PalResponse> result = palService.getPalsByLootItem(itemName, pageable);
        return ResponseEntity.ok(APIResponse.success("Pals dropping " + itemName, result));
    }
}
