package org.crustercrew.palworldpalcodex.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "pal_active_skills",
        uniqueConstraints = {
                @UniqueConstraint(name = "uq_pal_active_skill", columnNames = {"pal_id", "active_skill_id"})
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PalActiveSkill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pal_id", nullable = false)
    @JsonIgnore
    @ToString.Exclude
    private Pal pal;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "active_skill_id", nullable = false)
    private ActiveSkill activeSkill;

    @Column(name = "unlock_level", nullable = false)
    private Integer unlockLevel;
}
