package org.crustercrew.palworldpalcodex.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "active_skills")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActiveSkill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name; // Contoh: "Ignis Blast", "Dragon Cannon"

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "element_id")
    private ElementType element;

    private Integer power; // Stat damage power

    @Column(name = "cooldown_seconds")
    private Integer cooldownSeconds; // Charge Time (CT)

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
}
