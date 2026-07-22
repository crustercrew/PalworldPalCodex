package org.crustercrew.palworldpalcodex.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "work_suitabilities")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkSuitability {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "work_type", nullable = false, unique = true)
    private String workType;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "icon_url")
    private String iconUrl;
}
