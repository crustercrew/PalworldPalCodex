-- ============================================================
-- element_types dataset
-- ============================================================

TRUNCATE TABLE element_types RESTART IDENTITY CASCADE;

-- =========================================================================

-- PASS 1: INSERT SEMUA ELEMEN DASAR (TANPA FOREIGN KEY DULU)

-- =========================================================================

INSERT INTO element_types (id, name, icon_url) VALUES

(1, 'Dark', 'https://palworld.fandom.com/wiki/Special:FilePath/Dark.png'),
(2, 'Dragon', 'https://palworld.fandom.com/wiki/Special:FilePath/Dragon.png'),
(3, 'Electric', 'https://palworld.fandom.com/wiki/Special:FilePath/Electric.png'),
(4, 'Fire', 'https://palworld.fandom.com/wiki/Special:FilePath/Fire.png'),
(5, 'Grass', 'https://palworld.fandom.com/wiki/Special:FilePath/Grass.png'),
(6, 'Ground', 'https://palworld.fandom.com/wiki/Special:FilePath/Ground.png'),
(7, 'Ice', 'https://palworld.fandom.com/wiki/Special:FilePath/Ice.png'),
(8, 'Neutral', 'https://palworld.fandom.com/wiki/Special:FilePath/Neutral.png'),
(9, 'Water', 'https://palworld.fandom.com/wiki/Special:FilePath/Water.png')

ON CONFLICT (id) DO NOTHING;


-- =========================================================================

-- PASS 2: UPDATE RELASI WEAK & STRONG AGAINST (SETELAH SEMUA ID SUDAH ADA)

-- =========================================================================

UPDATE element_types SET weak_against_id = 2, strong_against_id = 8 WHERE id = 1;
UPDATE element_types SET weak_against_id = 7, strong_against_id = 1 WHERE id = 2;
UPDATE element_types SET weak_against_id = 6, strong_against_id = 9 WHERE id = 3;
UPDATE element_types SET weak_against_id = 9, strong_against_id = 5 WHERE id = 4;
UPDATE element_types SET weak_against_id = 4, strong_against_id = 6 WHERE id = 5;
UPDATE element_types SET weak_against_id = 5, strong_against_id = 3 WHERE id = 6;
UPDATE element_types SET weak_against_id = 4, strong_against_id = 2 WHERE id = 7;
UPDATE element_types SET weak_against_id = 1, strong_against_id = NULL WHERE id = 8;
UPDATE element_types SET weak_against_id = 3, strong_against_id = 4 WHERE id = 9;

