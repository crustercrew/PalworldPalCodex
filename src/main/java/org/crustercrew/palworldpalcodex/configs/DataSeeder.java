package org.crustercrew.palworldpalcodex.configs;

import lombok.RequiredArgsConstructor;
import org.crustercrew.palworldpalcodex.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {
    private final ElementTypeRepository elementTypeRepository;
    private final WorkSuitabilityRepository workSuitabilityRepository;
    private final ItemRepository itemRepository;
    private final PalRepository palRepository;
    private final PalStatRepository palStatRepository;
    private final PalLootItemRepository palLootItemRepository;
    private final ActiveSkillRepository activeSkillRepository;
    private final PalActiveSkillRepository palActiveSkillRepository;
    private final PalWorkSuitabilityRepository palWorkSuitabilityRepository;
    private final DataSource dataSource;

    @Override
    public void run(String... args) throws Exception {
        // 1. Cek Element Types
        if (elementTypeRepository.count() == 0) {
            System.out.println("Seeding 01__element_types.sql ...");
            ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
            populator.addScript(new ClassPathResource("db/migration/01__element_types.sql"));
            populator.execute(dataSource);
        }

        // 2. Cek Work Suitabilities
        if (workSuitabilityRepository.count() == 0) {
            System.out.println("Seeding 02__work_suitabilities.sql ...");
            ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
            populator.addScript(new ClassPathResource("db/migration/02__work_suitabilities.sql"));
            populator.execute(dataSource);
        }

        // 3. Cek Items
        if (itemRepository.count() == 0) {
            System.out.println("Seeding 03__items.sql ...");
            ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
            populator.addScript(new ClassPathResource("db/migration/03__items.sql"));
            populator.execute(dataSource);
        }

        // 4. Cek Pals & Pal Elements
        if (palRepository.count() == 0) {
            System.out.println("Seeding 04__pals.sql & 06__pal_elements.sql ...");
            ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
            populator.addScript(new ClassPathResource("db/migration/04__pals.sql"));
            populator.addScript(new ClassPathResource("db/migration/06__pal_elements.sql"));
            populator.execute(dataSource);
        }

        // 5. Cek Pal Stats
        if (palStatRepository.count() == 0) {
            System.out.println("Seeding 05__pal_stats.sql ...");
            ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
            populator.addScript(new ClassPathResource("db/migration/05__pal_stats.sql"));
            populator.execute(dataSource);
        }

        // 6. Cek Pal Loot Items
        if (palLootItemRepository.count() == 0) {
            System.out.println("Seeding 07__pal_loot_items.sql ...");
            ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
            populator.addScript(new ClassPathResource("db/migration/07__pal_loot_items.sql"));
            populator.execute(dataSource);
        }

        // 7. Cek Active Skills
        if (activeSkillRepository.count() == 0) {
            System.out.println("Seeding 08__active_skills.sql ...");
            ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
            populator.addScript(new ClassPathResource("db/migration/08__active_skills.sql"));
            populator.execute(dataSource);
        }

        // 8. Cek Pal Active Skills
        if (palActiveSkillRepository.count() == 0) {
            System.out.println("Seeding 09__pal_active_skills.sql ...");
            ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
            populator.addScript(new ClassPathResource("db/migration/09__pal_active_skills.sql"));
            populator.execute(dataSource);
        }

        // 9. Cek Pal Work Suitabilities
        if (palWorkSuitabilityRepository.count() == 0) {
            System.out.println("Seeding 10__pal_work_suitabilities.sql ...");
            ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
            populator.addScript(new ClassPathResource("db/migration/10__pal_work_suitabilities.sql"));
            populator.execute(dataSource);
        }

        System.out.println("Data Seeder check completed.");
    }
}
