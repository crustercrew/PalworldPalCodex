package org.crustercrew.palworldpalcodex.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "pal_stats")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PalStat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "pal_id", nullable = false, unique = true)
    @JsonIgnore
    @ToString.Exclude
    private Pal pal;

    private Integer hp;

    private Integer attack;

    private Integer defense;

    @Column(name = "min_hp")
    private Integer minHp;

    @Column(name = "max_hp")
    private Integer maxHp;

    @Column(name = "min_attack")
    private Integer minAttack;

    @Column(name = "max_attack")
    private Integer maxAttack;

    @Column(name = "min_defense")
    private Integer minDefense;

    @Column(name = "max_defense")
    private Integer maxDefense;
}
