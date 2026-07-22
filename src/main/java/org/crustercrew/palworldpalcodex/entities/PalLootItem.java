package org.crustercrew.palworldpalcodex.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "pal_loot_items",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uq_pal_loot_item",
                        columnNames = {"pal_id", "item_id"}
                )
        }
)
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @Column(name = "size")
    private String size;

    @Column(name = "drop_rate")
    private Double dropRate;
}
