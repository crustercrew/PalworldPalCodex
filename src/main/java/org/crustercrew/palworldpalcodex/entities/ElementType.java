package org.crustercrew.palworldpalcodex.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "element_types")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ElementType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "icon_url")
    private String iconUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "weak_against_id")
    @ToString.Exclude
    private ElementType weakAgainst;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "strong_against_id")
    @ToString.Exclude
    private ElementType strongAgainst;
}
