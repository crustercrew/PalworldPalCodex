package org.crustercrew.palworldpalcodex.configs;

import lombok.RequiredArgsConstructor;
import org.crustercrew.palworldpalcodex.repositories.ElementTypeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {
    private final ElementTypeRepository elementTypeRepository;
    private final DataSource dataSource;

    @Override
    public void run(String... args) throws Exception {
        if (elementTypeRepository.count() == 0) {
            System.out.println(">>> Database masih kosong. Menjalankan seeder SQL...");

            ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
            populator.addScript(new ClassPathResource("db/migration/01__element_types.sql"));
            populator.addScript(new ClassPathResource("db/migration/02__work_suitabilities.sql"));
            populator.addScript(new ClassPathResource("db/migration/03__items.sql"));
            populator.addScript(new ClassPathResource("db/migration/04__pals.sql"));
            populator.addScript(new ClassPathResource("db/migration/05__pal_stats.sql"));
            populator.addScript(new ClassPathResource("db/migration/06__pal_elements.sql"));
            populator.addScript(new ClassPathResource("db/migration/07__pal_loot_items.sql"));
            populator.addScript(new ClassPathResource("db/migration/08__active_skills.sql"));
            populator.addScript(new ClassPathResource("db/migration/09__pal_active_skills.sql"));
            populator.addScript(new ClassPathResource("db/migration/10__pal_work_suitabilities.sql"));

            populator.execute(dataSource);
            System.out.println(">>> Initial data berhasil di-insert!");
        } else {
            System.out.println(">>> Data sudah terisi, melemwati proses SQL seeding.");
        }
    }
}
