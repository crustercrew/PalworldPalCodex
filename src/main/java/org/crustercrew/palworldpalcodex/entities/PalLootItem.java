package org.crustercrew.palworldpalcodex.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "pal_loot_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PalLootItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pal_id", nullable = false)
    @JsonIgnore
    @ToString.Exclude
    private Pal pal;

    @Column(name = "item_name", nullable = false)
    private String itemName;

    @Column(name = "size")
    private String size;
}
