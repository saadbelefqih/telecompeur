-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : sam. 19 juin 2021 à 21:21
-- Version du serveur :  10.4.13-MariaDB
-- Version de PHP : 7.4.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `telecompteur_db`
--

DELIMITER $$
--
-- Procédures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `new_paiement` (IN `tarif` DOUBLE, IN `idcompteur` BIGINT, IN `moins` INT, IN `annee` INT)  BEGIN
START TRANSACTION;
   INSERT INTO paiement(idcompteur, moisPaye, anneePaye,tarif,datePaiement,isPaid,libelPaiement) VALUES(idcompteur,moins,annee,tarif,current_date(),true,CONCAT(moins,'/',annee));

   update compteurdetails set id_facteure= LAST_INSERT_ID() WHERE compteurdetails.idcompteur=idcompteur and MONTH(compteurdetails.DateDernierMAJ)= moins and YEAR(telecompteur_db.compteurdetails.DateDernierMAJ)= annee;
COMMIT;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

CREATE TABLE `client` (
  `userID` bigint(11) NOT NULL,
  `nom` varchar(150) NOT NULL,
  `prenom` varchar(150) NOT NULL,
  `genre` varchar(150) NOT NULL,
  `cIN` varchar(50) NOT NULL,
  `nom_utilisateurs` varchar(150) NOT NULL,
  `motPasse` varchar(150) NOT NULL,
  `dateNaissance` date NOT NULL,
  `tel` varchar(150) NOT NULL,
  `adresse` varchar(150) NOT NULL,
  `isActive` tinyint(1) NOT NULL,
  `num_abonnement` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `client`
--

INSERT INTO `client` (`userID`, `nom`, `prenom`, `genre`, `cIN`, `nom_utilisateurs`, `motPasse`, `dateNaissance`, `tel`, `adresse`, `isActive`, `num_abonnement`) VALUES
(3, 'BELEFQIH', 'ANASS', 'Homme', 'A880', 'A.BELEFQIH', '123456', '1994-05-30', '+212700000000', 'CASA', 0, '222333444'),
(5, 'BELEFQIH', 'SAAD', 'Homme', 'A88887', 's.belefqih', '123456789', '1994-05-30', '+2126854102', 'RABAT', 1, '222333444'),
(6, 'ALAMI', 'AHMED', 'Homme', 'CB1250', 'A.ALAMI', 'CB1250', '1990-01-01', '0714789625', 'TETOUAN', 0, 'C1623780242898');

-- --------------------------------------------------------

--
-- Structure de la table `compteur`
--

CREATE TABLE `compteur` (
  `idcompteur` bigint(20) NOT NULL,
  `code` varchar(255) NOT NULL,
  `marque` varchar(100) NOT NULL,
  `id_zone` bigint(20) NOT NULL,
  `userid` bigint(20) NOT NULL,
  `dateMiseEnOeuvre` date DEFAULT curdate(),
  `isActive` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `compteur`
--

INSERT INTO `compteur` (`idcompteur`, `code`, `marque`, `id_zone`, `userid`, `dateMiseEnOeuvre`, `isActive`) VALUES
(1, '1250', 'INGELEC', 1, 5, '2021-06-15', 1),
(2, '147852369', 'hp', 2, 3, '2021-06-14', 0),
(3, 'Z1000200', 'LINKY1', 2, 6, '2021-06-15', 1),
(4, 'Z1000250', 'LINKY5', 2, 6, '2021-06-15', 0);

-- --------------------------------------------------------

--
-- Structure de la table `compteurdetails`
--

CREATE TABLE `compteurdetails` (
  `idCD` bigint(20) NOT NULL,
  `indexEauNew` double NOT NULL,
  `indexElectNew` double NOT NULL,
  `indexEauOld` double DEFAULT NULL,
  `indexElectOld` double DEFAULT NULL,
  `DateDernierMAJ` datetime NOT NULL,
  `idcompteur` bigint(20) NOT NULL,
  `id_facteure` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `compteurdetails`
--

INSERT INTO `compteurdetails` (`idCD`, `indexEauNew`, `indexElectNew`, `indexEauOld`, `indexElectOld`, `DateDernierMAJ`, `idcompteur`, `id_facteure`) VALUES
(13, 458.587, 245.687, NULL, NULL, '2021-05-25 00:00:00', 1, NULL),
(14, 458.587, 245.687, NULL, NULL, '2021-05-25 00:00:00', 1, NULL),
(15, 458.587, 245.687, NULL, NULL, '2021-05-25 00:00:00', 1, NULL),
(16, 458.587, 245.687, NULL, NULL, '2021-05-25 00:00:00', 1, NULL),
(17, 458.587, 245.687, NULL, NULL, '2021-05-25 00:00:00', 1, NULL),
(18, 458.587, 245.687, NULL, NULL, '2021-05-25 00:00:00', 1, NULL),
(19, 458.587, 245.687, NULL, NULL, '2021-06-14 00:00:00', 3, NULL),
(20, 458.587, 245.687, NULL, NULL, '2021-06-14 00:00:00', 3, NULL),
(21, 458.587, 245.687, NULL, NULL, '2021-06-14 00:00:00', 3, NULL),
(22, 458.587, 245.687, NULL, NULL, '2021-06-14 00:00:00', 3, NULL),
(23, 458.587, 245.687, NULL, NULL, '2021-06-14 00:00:00', 3, NULL),
(24, 458.587, 245.687, NULL, NULL, '2021-06-14 00:00:00', 3, NULL),
(25, 458.587, 245.687, NULL, NULL, '2021-06-14 00:00:00', 3, NULL),
(26, 458.587, 245.687, NULL, NULL, '2021-06-14 00:00:00', 3, NULL),
(27, 458.587, 245.687, NULL, NULL, '2021-06-14 00:00:00', 3, NULL),
(28, 458.587, 245.687, NULL, NULL, '2021-06-14 00:00:00', 3, NULL),
(29, 0.48, 0.05, NULL, NULL, '2021-06-15 00:00:00', 3, NULL),
(30, 0.84, 0.2, NULL, NULL, '2021-06-15 00:00:00', 3, NULL),
(31, 0.1, 0.61, NULL, NULL, '2021-06-15 00:00:00', 3, NULL),
(32, 0.48, 0.94, NULL, NULL, '2021-06-15 00:00:00', 3, NULL),
(114, 19.88, 40.04, NULL, NULL, '2021-06-18 00:00:00', 3, NULL),
(115, 21.47, 39.94, NULL, NULL, '2021-06-18 00:00:00', 3, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `lignes_paiement`
--

CREATE TABLE `lignes_paiement` (
  `codepaiement` bigint(20) NOT NULL,
  `idCD` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `paiement`
--

CREATE TABLE `paiement` (
  `codepaiement` bigint(20) NOT NULL,
  `libelPaiement` varchar(200) NOT NULL,
  `datePaiement` datetime NOT NULL,
  `tarif` double NOT NULL,
  `idcompteur` bigint(20) NOT NULL,
  `moisPaye` int(11) NOT NULL,
  `anneePaye` int(11) NOT NULL,
  `isPaid` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `paiement`
--

INSERT INTO `paiement` (`codepaiement`, `libelPaiement`, `datePaiement`, `tarif`, `idcompteur`, `moisPaye`, `anneePaye`, `isPaid`) VALUES
(1, '6/2021', '2021-06-19 00:00:00', 9612, 3, 6, 2021, 1);

-- --------------------------------------------------------

--
-- Structure de la table `responsable`
--

CREATE TABLE `responsable` (
  `userid` bigint(20) NOT NULL,
  `nom` varchar(200) NOT NULL,
  `prenom` varchar(200) NOT NULL,
  `username` varchar(200) NOT NULL,
  `password` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `responsable`
--

INSERT INTO `responsable` (`userid`, `nom`, `prenom`, `username`, `password`) VALUES
(1, 'admin', 'admin', 'admin', 'admin');

-- --------------------------------------------------------

--
-- Doublure de structure pour la vue `vue_consom_details`
-- (Voir ci-dessous la vue réelle)
--
CREATE TABLE `vue_consom_details` (
`userID` bigint(11)
,`nom` varchar(150)
,`prenom` varchar(150)
,`genre` varchar(150)
,`cIN` varchar(50)
,`num_abonnement` varchar(150)
,`idcompteur` bigint(20)
,`marque` varchar(100)
,`code` varchar(255)
,`dateMiseEnOeuvre` date
,`isActive` tinyint(1)
,`indexEauNew` double
,`indexElectNew` double
,`DateDernierMAJ` datetime
,`mois` int(2)
,`annee` int(4)
);

-- --------------------------------------------------------

--
-- Doublure de structure pour la vue `vue_consom_nonpaye`
-- (Voir ci-dessous la vue réelle)
--
CREATE TABLE `vue_consom_nonpaye` (
`sum_eau` double(20,3)
,`sum_elect` double(20,3)
,`mois` int(2)
,`annee` int(4)
,`idcompteur` bigint(20)
,`userID` bigint(11)
,`cIN` varchar(50)
,`num_abonnement` varchar(150)
,`prenom` varchar(150)
,`nom` varchar(150)
,`IdentifiantClient` varchar(503)
,`code` varchar(255)
,`marque` varchar(100)
);

-- --------------------------------------------------------

--
-- Doublure de structure pour la vue `vue_consom_paye`
-- (Voir ci-dessous la vue réelle)
--
CREATE TABLE `vue_consom_paye` (
`sum_eau` double(20,3)
,`sum_elect` double(20,3)
,`codepaiement` bigint(20)
,`libelPaiement` varchar(200)
,`datePaiement` datetime
,`tarif` double
,`moisPaye` int(11)
,`anneePaye` int(11)
,`isPaid` tinyint(1)
,`idcompteur` bigint(20)
,`code` varchar(255)
,`marque` varchar(100)
,`dateMiseEnOeuvre` date
,`compteur_isActive` tinyint(1)
,`userID` bigint(11)
,`nom` varchar(150)
,`prenom` varchar(150)
,`genre` varchar(150)
,`cIN` varchar(50)
,`nom_utilisateurs` varchar(150)
,`motPasse` varchar(150)
,`dateNaissance` date
,`tel` varchar(150)
,`adresse` varchar(150)
,`client_isActive` tinyint(1)
,`num_abonnement` varchar(150)
,`IdentifiantClient` varchar(503)
);

-- --------------------------------------------------------

--
-- Structure de la table `zone`
--

CREATE TABLE `zone` (
  `id_zone` bigint(20) NOT NULL,
  `lib_zone` varchar(200) NOT NULL,
  `latitude` double NOT NULL,
  `longitude` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `zone`
--

INSERT INTO `zone` (`id_zone`, `lib_zone`, `latitude`, `longitude`) VALUES
(1, 'CYM', 1114, 4455),
(2, 'Ocean', 8711, 845545);

-- --------------------------------------------------------

--
-- Structure de la vue `vue_consom_details`
--
DROP TABLE IF EXISTS `vue_consom_details`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vue_consom_details`  AS  select `client`.`userID` AS `userID`,`client`.`nom` AS `nom`,`client`.`prenom` AS `prenom`,`client`.`genre` AS `genre`,`client`.`cIN` AS `cIN`,`client`.`num_abonnement` AS `num_abonnement`,`compteur`.`idcompteur` AS `idcompteur`,`compteur`.`marque` AS `marque`,`compteur`.`code` AS `code`,`compteur`.`dateMiseEnOeuvre` AS `dateMiseEnOeuvre`,`compteur`.`isActive` AS `isActive`,`compteurdetails`.`indexEauNew` AS `indexEauNew`,`compteurdetails`.`indexElectNew` AS `indexElectNew`,`compteurdetails`.`DateDernierMAJ` AS `DateDernierMAJ`,month(`compteurdetails`.`DateDernierMAJ`) AS `mois`,year(`compteurdetails`.`DateDernierMAJ`) AS `annee` from ((`client` join `compteur`) join `compteurdetails`) where `client`.`userID` = `compteur`.`userid` and `compteur`.`idcompteur` = `compteurdetails`.`idcompteur` order by `client`.`userID`,`compteurdetails`.`DateDernierMAJ` ;

-- --------------------------------------------------------

--
-- Structure de la vue `vue_consom_nonpaye`
--
DROP TABLE IF EXISTS `vue_consom_nonpaye`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vue_consom_nonpaye`  AS  select round(sum(`compteurdetails`.`indexEauNew`),3) AS `sum_eau`,round(sum(`compteurdetails`.`indexElectNew`),3) AS `sum_elect`,month(`compteurdetails`.`DateDernierMAJ`) AS `mois`,year(`compteurdetails`.`DateDernierMAJ`) AS `annee`,`compteurdetails`.`idcompteur` AS `idcompteur`,`client`.`userID` AS `userID`,`client`.`cIN` AS `cIN`,`client`.`num_abonnement` AS `num_abonnement`,`client`.`prenom` AS `prenom`,`client`.`nom` AS `nom`,concat(`client`.`prenom`,' ',`client`.`nom`,' ',`client`.`cIN`,' ',`client`.`num_abonnement`) AS `IdentifiantClient`,`compteur`.`code` AS `code`,`compteur`.`marque` AS `marque` from ((`compteurdetails` join `compteur`) join `client`) where `compteur`.`idcompteur` = `compteurdetails`.`idcompteur` and `client`.`userID` = `compteur`.`userid` and !(month(`compteurdetails`.`DateDernierMAJ`) in (select `paiement`.`moisPaye` from `paiement` where `paiement`.`idcompteur` = `compteurdetails`.`idcompteur` and `paiement`.`anneePaye` = year(`compteurdetails`.`DateDernierMAJ`) and `paiement`.`isPaid` is true)) group by `compteurdetails`.`idcompteur`,month(`compteurdetails`.`DateDernierMAJ`) ;

-- --------------------------------------------------------

--
-- Structure de la vue `vue_consom_paye`
--
DROP TABLE IF EXISTS `vue_consom_paye`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vue_consom_paye`  AS  select distinct round(sum(`compteurdetails`.`indexEauNew`),3) AS `sum_eau`,round(sum(`compteurdetails`.`indexElectNew`),3) AS `sum_elect`,`paiement`.`codepaiement` AS `codepaiement`,`paiement`.`libelPaiement` AS `libelPaiement`,`paiement`.`datePaiement` AS `datePaiement`,`paiement`.`tarif` AS `tarif`,`paiement`.`moisPaye` AS `moisPaye`,`paiement`.`anneePaye` AS `anneePaye`,`paiement`.`isPaid` AS `isPaid`,`compteur`.`idcompteur` AS `idcompteur`,`compteur`.`code` AS `code`,`compteur`.`marque` AS `marque`,`compteur`.`dateMiseEnOeuvre` AS `dateMiseEnOeuvre`,`compteur`.`isActive` AS `compteur_isActive`,`client`.`userID` AS `userID`,`client`.`nom` AS `nom`,`client`.`prenom` AS `prenom`,`client`.`genre` AS `genre`,`client`.`cIN` AS `cIN`,`client`.`nom_utilisateurs` AS `nom_utilisateurs`,`client`.`motPasse` AS `motPasse`,`client`.`dateNaissance` AS `dateNaissance`,`client`.`tel` AS `tel`,`client`.`adresse` AS `adresse`,`client`.`isActive` AS `client_isActive`,`client`.`num_abonnement` AS `num_abonnement`,concat(`client`.`prenom`,' ',`client`.`nom`,' ',`client`.`cIN`,' ',`client`.`num_abonnement`) AS `IdentifiantClient` from (((`paiement` join `compteurdetails`) join `compteur`) join `client`) where `paiement`.`codepaiement` = `compteurdetails`.`id_facteure` and `compteurdetails`.`idcompteur` = `compteur`.`idcompteur` and `compteur`.`userid` = `client`.`userID` order by `client`.`userID`,`paiement`.`anneePaye` desc,`paiement`.`moisPaye` desc ;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`userID`),
  ADD UNIQUE KEY `cIN` (`cIN`);

--
-- Index pour la table `compteur`
--
ALTER TABLE `compteur`
  ADD PRIMARY KEY (`idcompteur`),
  ADD KEY `FK_Cmpt_Zone` (`id_zone`),
  ADD KEY `FK_Cmpt_Client` (`userid`);

--
-- Index pour la table `compteurdetails`
--
ALTER TABLE `compteurdetails`
  ADD PRIMARY KEY (`idCD`),
  ADD KEY `FK_Details_Cmpt` (`idcompteur`),
  ADD KEY `Fk_paiement` (`id_facteure`);

--
-- Index pour la table `lignes_paiement`
--
ALTER TABLE `lignes_paiement`
  ADD PRIMARY KEY (`codepaiement`,`idCD`),
  ADD KEY `FK_ligne_compdetails` (`idCD`);

--
-- Index pour la table `paiement`
--
ALTER TABLE `paiement`
  ADD PRIMARY KEY (`codepaiement`),
  ADD KEY `fk_paiement_compteur` (`idcompteur`);

--
-- Index pour la table `responsable`
--
ALTER TABLE `responsable`
  ADD PRIMARY KEY (`userid`);

--
-- Index pour la table `zone`
--
ALTER TABLE `zone`
  ADD PRIMARY KEY (`id_zone`),
  ADD UNIQUE KEY `lib_zone` (`lib_zone`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `client`
--
ALTER TABLE `client`
  MODIFY `userID` bigint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT pour la table `compteur`
--
ALTER TABLE `compteur`
  MODIFY `idcompteur` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `compteurdetails`
--
ALTER TABLE `compteurdetails`
  MODIFY `idCD` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=116;

--
-- AUTO_INCREMENT pour la table `paiement`
--
ALTER TABLE `paiement`
  MODIFY `codepaiement` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `responsable`
--
ALTER TABLE `responsable`
  MODIFY `userid` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `zone`
--
ALTER TABLE `zone`
  MODIFY `id_zone` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `compteur`
--
ALTER TABLE `compteur`
  ADD CONSTRAINT `FK_Cmpt_Client` FOREIGN KEY (`userid`) REFERENCES `client` (`userID`),
  ADD CONSTRAINT `FK_Cmpt_Zone` FOREIGN KEY (`id_zone`) REFERENCES `zone` (`id_zone`);

--
-- Contraintes pour la table `compteurdetails`
--
ALTER TABLE `compteurdetails`
  ADD CONSTRAINT `FK_Details_Cmpt` FOREIGN KEY (`idcompteur`) REFERENCES `compteur` (`idcompteur`),
  ADD CONSTRAINT `Fk_paiement` FOREIGN KEY (`id_facteure`) REFERENCES `paiement` (`codepaiement`);

--
-- Contraintes pour la table `lignes_paiement`
--
ALTER TABLE `lignes_paiement`
  ADD CONSTRAINT `FK_ligne_compdetails` FOREIGN KEY (`idCD`) REFERENCES `compteurdetails` (`idCD`),
  ADD CONSTRAINT `FK_ligne_paiement` FOREIGN KEY (`codepaiement`) REFERENCES `paiement` (`codepaiement`);

--
-- Contraintes pour la table `paiement`
--
ALTER TABLE `paiement`
  ADD CONSTRAINT `fk_paiement_compteur` FOREIGN KEY (`idcompteur`) REFERENCES `compteur` (`idcompteur`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
