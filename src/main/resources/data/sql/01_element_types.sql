-- ============================================================
-- element_types dataset
-- ============================================================

TRUNCATE TABLE element_types RESTART IDENTITY CASCADE;

INSERT INTO element_types (id, name, icon_url, weak_against_id, strong_against_id) VALUES (1, 'Dark', 'https://palworld.fandom.com/wiki/Special:FilePath/Dark.png', 2, 8) ON CONFLICT (id) DO NOTHING;
INSERT INTO element_types (id, name, icon_url, weak_against_id, strong_against_id) VALUES (2, 'Dragon', 'https://palworld.fandom.com/wiki/Special:FilePath/Dragon.png', 7, 1) ON CONFLICT (id) DO NOTHING;
INSERT INTO element_types (id, name, icon_url, weak_against_id, strong_against_id) VALUES (3, 'Electric', 'https://palworld.fandom.com/wiki/Special:FilePath/Electric.png', 6, 9) ON CONFLICT (id) DO NOTHING;
INSERT INTO element_types (id, name, icon_url, weak_against_id, strong_against_id) VALUES (4, 'Fire', 'https://palworld.fandom.com/wiki/Special:FilePath/Fire.png', 9, 5) ON CONFLICT (id) DO NOTHING;
INSERT INTO element_types (id, name, icon_url, weak_against_id, strong_against_id) VALUES (5, 'Grass', 'https://palworld.fandom.com/wiki/Special:FilePath/Grass.png', 4, 6) ON CONFLICT (id) DO NOTHING;
INSERT INTO element_types (id, name, icon_url, weak_against_id, strong_against_id) VALUES (6, 'Ground', 'https://palworld.fandom.com/wiki/Special:FilePath/Ground.png', 5, 3) ON CONFLICT (id) DO NOTHING;
INSERT INTO element_types (id, name, icon_url, weak_against_id, strong_against_id) VALUES (7, 'Ice', 'https://palworld.fandom.com/wiki/Special:FilePath/Ice.png', 4, 2) ON CONFLICT (id) DO NOTHING;
INSERT INTO element_types (id, name, icon_url, weak_against_id, strong_against_id) VALUES (8, 'Neutral', 'https://palworld.fandom.com/wiki/Special:FilePath/Neutral.png', 1, NULL) ON CONFLICT (id) DO NOTHING;
INSERT INTO element_types (id, name, icon_url, weak_against_id, strong_against_id) VALUES (9, 'Water', 'https://palworld.fandom.com/wiki/Special:FilePath/Water.png', 3, 4) ON CONFLICT (id) DO NOTHING;