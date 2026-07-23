package org.crustercrew.palworldpalcodex.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageResponse<T> implements Serializable {
    private Integer page;
    private Integer size;
    private Long totalElements;
    private Integer totalPages;
    private List<T> data;

    public static <T> PageResponse<T> from(org.springframework.data.domain.Page<T> springPage) {
        return PageResponse.<T>builder()
                .page(springPage.getNumber())
                .size(springPage.getSize())
                .totalElements(springPage.getTotalElements())
                .totalPages(springPage.getTotalPages())
                .data(springPage.getContent())
                .build();
    }
}
