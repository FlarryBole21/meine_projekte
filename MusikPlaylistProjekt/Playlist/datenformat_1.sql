-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 29. Mrz 2023 um 11:24
-- Server-Version: 10.4.27-MariaDB
-- PHP-Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `playlist2023`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `datenformat_1`
--

CREATE TABLE `datenformat_1` (
  `id` int(11) NOT NULL,
  `interpret` varchar(100) NOT NULL,
  `titel` varchar(100) NOT NULL,
  `album` varchar(100) NOT NULL,
  `erscheinungsdatum` date NOT NULL,
  `laenge` time NOT NULL,
  `urheber_musik` varchar(100) NOT NULL,
  `urheber_text` varchar(100) NOT NULL,
  `genre` varchar(100) NOT NULL,
  `dateiname` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Daten für Tabelle `datenformat_1`
--

INSERT INTO `datenformat_1` (`id`, `interpret`, `titel`, `album`, `erscheinungsdatum`, `laenge`, `urheber_musik`, `urheber_text`, `genre`, `dateiname`) VALUES
(1, 'Lars Lustlos', 'Projects suck', 'Total Confusion', '2023-03-20', '00:03:00', 'cbm GmbH', 'Power Group', 'Hardcore', 'audio_1.mp3'),
(2, 'Juergen Liebel', 'Alles klar, wunderbar', 'Codesounds', '2023-02-06', '00:03:01', 'cbm GmbH', 'Power Group', 'Volksmusik', 'audio_2.mp3'),
(3, 'Black & White', 'My Life Is Pain, My Pain Is Life', 'Empire Of Truth', '2020-03-14', '00:06:59', 'Broken Brain Productions', 'Bernt das Brot', 'Heavy Metal', 'audio_3.mp3'),
(4, 'Stinky Fishman', 'Forellenblues', 'Stories of Pain and Depression', '2024-04-02', '00:00:33', 'Krabbenpulchaoten', 'Wattwills', 'German Fishcore', 'audio_4.mp3'),
(5, 'Betons & Matrix', 'Laser & Rain', 'Schwapp & Schwipp', '2022-08-08', '00:01:00', 'Antilonly', 'MissionYingYang', 'Selfoperator', 'audio_5.mp3'),
(6, 'Hunters and Hunt', 'No Pain No Gain', 'Hatred', '2017-03-08', '00:01:34', 'Broken Brain Productions', 'Wattwills', 'Hardcore', 'audio_6.mp3');

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `datenformat_1`
--
ALTER TABLE `datenformat_1`
  ADD PRIMARY KEY (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
