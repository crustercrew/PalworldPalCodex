-- ============================================================
-- work_suitabilities dataset
-- ============================================================

TRUNCATE TABLE work_suitabilities RESTART IDENTITY CASCADE;

INSERT INTO work_suitabilities (id, work_type, description, icon_url) VALUES (1, 'Kindling', 'Heats campfires, cooking pots and furnaces.', 'https://palworld.fandom.com/wiki/Special:FilePath/Kindling.png') ON CONFLICT (id) DO NOTHING;
INSERT INTO work_suitabilities (id, work_type, description, icon_url) VALUES (2, 'Watering', 'Waters crops and activates water-powered devices.', 'https://palworld.fandom.com/wiki/Special:FilePath/Watering.png') ON CONFLICT (id) DO NOTHING;
INSERT INTO work_suitabilities (id, work_type, description, icon_url) VALUES (3, 'Planting', 'Plants seeds in berry plantations.', 'https://palworld.fandom.com/wiki/Special:FilePath/Planting.png') ON CONFLICT (id) DO NOTHING;
INSERT INTO work_suitabilities (id, work_type, description, icon_url) VALUES (4, 'Generating Electricity', 'Powers electrical generators.', 'https://palworld.fandom.com/wiki/Special:FilePath/Generating_Electricity.png') ON CONFLICT (id) DO NOTHING;
INSERT INTO work_suitabilities (id, work_type, description, icon_url) VALUES (5, 'Handiwork', 'Crafts items at crafting benches.', 'https://palworld.fandom.com/wiki/Special:FilePath/Handiwork.png') ON CONFLICT (id) DO NOTHING;
INSERT INTO work_suitabilities (id, work_type, description, icon_url) VALUES (6, 'Gathering', 'Gathers items from berry plantations and other sources.', 'https://palworld.fandom.com/wiki/Special:FilePath/Gathering.png') ON CONFLICT (id) DO NOTHING;
INSERT INTO work_suitabilities (id, work_type, description, icon_url) VALUES (7, 'Lumbering', 'Chops down trees.', 'https://palworld.fandom.com/wiki/Special:FilePath/Lumbering.png') ON CONFLICT (id) DO NOTHING;
INSERT INTO work_suitabilities (id, work_type, description, icon_url) VALUES (8, 'Mining', 'Mines ore and other minerals.', 'https://palworld.fandom.com/wiki/Special:FilePath/Mining.png') ON CONFLICT (id) DO NOTHING;
INSERT INTO work_suitabilities (id, work_type, description, icon_url) VALUES (9, 'Medicine Production', 'Produces medicine at a medical bench.', 'https://palworld.fandom.com/wiki/Special:FilePath/Medicine_Production.png') ON CONFLICT (id) DO NOTHING;
INSERT INTO work_suitabilities (id, work_type, description, icon_url) VALUES (10, 'Cooling', 'Cools refrigerators.', 'https://palworld.fandom.com/wiki/Special:FilePath/Cooling.png') ON CONFLICT (id) DO NOTHING;
INSERT INTO work_suitabilities (id, work_type, description, icon_url) VALUES (11, 'Transporting', 'Transports items around the base.', 'https://palworld.fandom.com/wiki/Special:FilePath/Transporting.png') ON CONFLICT (id) DO NOTHING;
INSERT INTO work_suitabilities (id, work_type, description, icon_url) VALUES (12, 'Farming', 'Farms special resources at a ranch.', 'https://palworld.fandom.com/wiki/Special:FilePath/Farming.png') ON CONFLICT (id) DO NOTHING;