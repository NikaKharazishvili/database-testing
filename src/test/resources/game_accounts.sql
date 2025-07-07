-- Create the database and switch to it
CREATE DATABASE game_accounts;
USE game_accounts;

-- Create the accounts table
CREATE TABLE accounts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(20) NOT NULL UNIQUE,
    password VARCHAR(20) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Insert sample data into accounts table
INSERT INTO accounts (username, password, email)
VALUES
('ShadowFang', 'DragonSlayer', 'shadowfang@mail.com'),
('NightReaper', 'DarkKnight', 'nightreaper@mail.com'),
('StormCaller', 'ThunderGod', 'stormcaller@mail.com'),
('IronFist', 'DragonSlayer', 'ironfist@mail.com'),
('FrostMage', 'IceStorm', 'frostmage@mail.com'),
('BloodRaven', 'DarkKnight', 'bloodraven@mail.com'),
('SilverWolf', 'MoonHowl', 'silverwolf@mail.com'),
('ArcaneMaster', 'Fireball123', 'arcanemaster@mail.com'),
('HellBringer', 'Inferno666', 'hellbringer@mail.com'),
('WarriorX', 'DragonSlayer', 'warriorx@mail.com'),
('VenomStrike', 'PoisonFang', 'venomstrike@mail.com'),
('DarkHunter', 'ShadowBlade', 'darkhunter@mail.com'),
('BattleLord', 'KingSlayer', 'battlelord@mail.com'),
('GhostWalker', 'SpiritTouch', 'ghostwalker@mail.com'),
('SilentArrow', 'DarkKnight', 'silentarrow@mail.com'),
('BlazingKnight', 'Inferno666', 'blazingknight@mail.com'),
('SoulBinder', 'NecroMaster', 'soulbinder@mail.com'),
('ThunderLancer', 'StormBringer', 'thunderlancer@mail.com'),
('DemonSlayer', 'HolyBlade', 'demonslayer@mail.com'),
('ShadowAssassin', 'NightPierce', 'shadowassassin@mail.com'),
('CrimsonSwordsman', 'DragonSlayer', 'crimsonswordsman@mail.com'),
('MysticRogue', 'DarkKnight', 'mysticrogue@mail.com'),
('TitanWarlord', 'IronShield', 'titanwarlord@mail.com'),
('EmeraldSorcerer', 'NaturePower', 'emeraldsorcerer@mail.com'),
('ChaosKnight', 'Inferno666', 'chaosknight@mail.com');