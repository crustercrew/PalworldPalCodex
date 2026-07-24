package org.crustercrew.palworldpalcodex.controllers;

import lombok.RequiredArgsConstructor;
import org.crustercrew.palworldpalcodex.dtos.response.APIResponse;
import org.crustercrew.palworldpalcodex.dtos.response.ElementTypeResponse;
import org.crustercrew.palworldpalcodex.dtos.response.PageResponse;
import org.crustercrew.palworldpalcodex.dtos.response.PalResponse;
import org.crustercrew.palworldpalcodex.services.ElementService;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/elements")
@RequiredArgsConstructor
public class ElementController {
    private final ElementService elementService;

    // GET /api/v1/elements -> List seluruh Master Elemen & Kelemahannya
    @GetMapping
    public ResponseEntity<APIResponse<List<ElementTypeResponse>>> getAllElements() {
        List<ElementTypeResponse> result = elementService.getAllElements();
        return ResponseEntity.ok(APIResponse.success("Fetched all element types", result));
    }

    // GET /api/v1/elements/Fire/pals -> List Pal dengan Elemen Fire (Terpaginasi)
    @GetMapping("/{elementName}/pals")
    public ResponseEntity<APIResponse<PageResponse<PalResponse>>> getPalsByElement(
            @PathVariable String elementName,
            @ParameterObject @PageableDefault(page = 0, size = 10,sort="id",direction = Sort.Direction.ASC) Pageable pageable
    ) {
        PageResponse<PalResponse> result = elementService.getPalsByElement(elementName, pageable);
        return ResponseEntity.ok(APIResponse.success("Pals fetched for element: " + elementName, result));
    }
}
