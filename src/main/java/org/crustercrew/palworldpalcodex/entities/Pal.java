package org.crustercrew.palworldpalcodex.entities;

import jakarta.persistence.*;
import lombok.*;
import org.crustercrew.palworldpalcodex.enumerate.ElementType;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Column(name = "pal_number", nullable = false, unique = true)
    private Integer palNumber;

    @Column(name = "name", nullable = false)
    private String name;


    @ElementCollection(targetClass = ElementType.class, fetch = FetchType.EAGER)
    @CollectionTable(
            name = "pal_element_types",
            joinColumns = @JoinColumn(name = "pal_id")
    )
    @Enumerated(EnumType.STRING)
    @Column(name = "element_type")
    private Set<ElementType> elementType = new HashSet<>();

    @Column(name = "alpha_title")
    private String alphaTitle;

    @Column(name = "partner_skill")
    private String partnerSkill;

    @Column(name = "food_consumption")
    private Integer foodConsumption;

    private String eggName;

    private String eggSize;

    @Column(name = "breed_power")
    private Integer breedPower;

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
