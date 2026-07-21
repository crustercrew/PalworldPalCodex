package org.crustercrew.palworldpalcodex.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "pal_work_suitabilities")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PalWorkSuitability {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pal_id", nullable = false)
    @JsonIgnore
    @ToString.Exclude
    private Pal pal;

    @Column(name = "work_type", nullable = false)
    private String workType;

    @Column(name = "work_level", nullable = false)
    private Integer workLevel;
}
