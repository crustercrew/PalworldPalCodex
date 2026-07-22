package org.crustercrew.palworldpalcodex.dtos;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Page<T> implements Serializable {
    public Integer page;
    public  Integer size;
    public Long totalElements;
    public Integer totalPages;
    public List<T> data;
}
