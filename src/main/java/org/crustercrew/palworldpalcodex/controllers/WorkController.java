package org.crustercrew.palworldpalcodex.controllers;

import lombok.RequiredArgsConstructor;
import org.crustercrew.palworldpalcodex.dtos.response.APIResponse;
import org.crustercrew.palworldpalcodex.dtos.response.PageResponse;
import org.crustercrew.palworldpalcodex.dtos.response.PalResponse;
import org.crustercrew.palworldpalcodex.dtos.response.WorkSuitabilityResponse;
import org.crustercrew.palworldpalcodex.services.WorkService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/works")
@RequiredArgsConstructor
public class WorkController {
    private final WorkService workService;
    // GET /api/v1/works -> List seluruh Master Tipe Pekerjaan Base
    @GetMapping
    public ResponseEntity<APIResponse<List<WorkSuitabilityResponse>>> getAllWorks() {
        List<WorkSuitabilityResponse> result = workService.getAllWorks();
        return ResponseEntity.ok(APIResponse.success("Fetched all work suitabilities", result));
    }

    // GET /api/v1/works/Mining/pals?minLevel=2 -> List Pal dengan Skill Mining Min Lvl 2 (Terpaginasi)
    @GetMapping("/{workType}/pals")
    public ResponseEntity<APIResponse<PageResponse<PalResponse>>> getPalsByWork(
            @PathVariable String workType,
            @RequestParam(required = false) Integer minLevel,
            Pageable pageable) {

        PageResponse<PalResponse> result = workService.getPalsByWork(workType, minLevel, pageable);
        return ResponseEntity.ok(APIResponse.success("Pals fetched for work: " + workType, result));
    }
}
