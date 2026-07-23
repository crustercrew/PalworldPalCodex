package org.crustercrew.palworldpalcodex.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity
@Table(name = "pals")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pal_number", length = 5)
    private String palNumber;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "pal_elements",
            joinColumns = @JoinColumn(name = "pal_id"),
            inverseJoinColumns = @JoinColumn(name = "element_id")
    )
    @Builder.Default
    private Set<ElementType> elements = new HashSet<>();

    @Column(name = "alpha_title")
    private String alphaTitle;

    @Column(name = "partner_skill", columnDefinition = "TEXT")
    private String partnerSkill;

    @Column(name = "food_consumption")
    private Integer foodConsumption;

    private String eggName;

    private String eggSize;

    @Column(name = "breed_power")
    private Integer breedPower;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    private String imageURL;

    @OneToOne(mappedBy = "pal", cascade = CascadeType.ALL, orphanRemoval = true)
    private PalStat stat;

    @Builder.Default
    @OneToMany(mappedBy = "pal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PalWorkSuitability> workSuitabilities = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "pal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PalLootItem> lootItems = new ArrayList<>();
    @Builder.Default
    @OneToMany(mappedBy = "pal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PalActiveSkill> activeSkills = new ArrayList<>();

    public void setStat(PalStat stat) {
        if (stat != null) {
            stat.setPal(this);
        }
        this.stat = stat;
    }

    public void addElement(ElementType element) {
        this.elements.add(element);
    }

    public void addWorkSuitability(PalWorkSuitability work) {
        workSuitabilities.add(work);
        work.setPal(this);
    }

    public void addLootItem(PalLootItem loot) {
        lootItems.add(loot);
        loot.setPal(this);
    }

    public void addActiveSkill(PalActiveSkill skill) {
        activeSkills.add(skill);
        skill.setPal(this);
    }
}
