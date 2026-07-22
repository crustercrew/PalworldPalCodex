package org.crustercrew.palworldpalcodex.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "pal_work_suitabilities",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uq_pal_work_suitability",
                        columnNames = {"pal_id", "work_suitability_id"}
                )
        }
)
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "work_suitability_id", nullable = false)
    private WorkSuitability workSuitability;

    @Column(name = "work_level", nullable = false)
    private Integer workLevel;
}
