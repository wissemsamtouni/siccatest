-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : mar. 23 août 2022 à 03:15
-- Version du serveur : 10.4.22-MariaDB
-- Version de PHP : 7.4.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `siccaplan`
--

-- --------------------------------------------------------

--
-- Structure de la table `bonplan`
--

CREATE TABLE `bonplan` (
  `id_plan` int(250) NOT NULL,
  `nom_bonplan` varchar(250) NOT NULL,
  `adresse` varchar(250) NOT NULL,
  `description_bonplan` varchar(2000) NOT NULL,
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `categorie`
--

CREATE TABLE `categorie` (
  `id` int(11) NOT NULL,
  `type_categorie` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `evennement`
--

CREATE TABLE `evennement` (
  `id_evennement` int(11) NOT NULL,
  `date_debut` date NOT NULL,
  `date_fin` date NOT NULL,
  `prix_ticket` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `location`
--

CREATE TABLE `location` (
  `id_location` int(11) NOT NULL,
  `date_debut` date NOT NULL,
  `date_fin` date NOT NULL,
  `id_utilisateur` int(11) NOT NULL,
  `ref_materiel` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `materiel`
--

CREATE TABLE `materiel` (
  `ref_materiel` int(255) NOT NULL,
  `id_categorie` int(11) NOT NULL,
  `quantite` int(255) NOT NULL,
  `description_materiel` varchar(255) NOT NULL,
  `prix_location` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `promo`
--

CREATE TABLE `promo` (
  `id_promo` int(11) NOT NULL,
  `date_expiration` date NOT NULL,
  `pourcentage_reduction` int(11) NOT NULL,
  `id_utilisateur` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `promo`
--

INSERT INTO `promo` (`id_promo`, `date_expiration`, `pourcentage_reduction`, `id_utilisateur`) VALUES
(10, '2022-08-01', 20, 10);

-- --------------------------------------------------------

--
-- Structure de la table `reservation`
--

CREATE TABLE `reservation` (
  `id_reservation` int(11) NOT NULL,
  `id_utilisateur` int(11) NOT NULL,
  `id_evennement` int(11) NOT NULL,
  `disponibilte` tinyint(1) NOT NULL,
  `nbr_ticket` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `id_utilisateur` int(11) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  `mail` varchar(100) NOT NULL,
  `login` varchar(50) NOT NULL,
  `mdp` varchar(50) NOT NULL,
  `tel` int(20) NOT NULL,
  `role` varchar(50) NOT NULL,
  `adresse` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id_utilisateur`, `nom`, `prenom`, `mail`, `login`, `mdp`, `tel`, `role`, `adresse`) VALUES
(10, 'ahmed', 'jbeli', 'ahmed.jbeli@esprit.tn', 'ahmed', '7c222fb2927d828af22f592134e8932480637c0d', 93834278, 'Client', 'lkef'),
(11, 'hamza', 'laamari', 'hamza@esprit', 'hamza', '7c222fb2927d828af22f592134e8932480637c0d', 12365478, 'Client', 'lkef'),
(12, 'wissem', 'samtouni', 'sa.wissem.11@esprit.tn', 'wissemsamtouni', '7c222fb2927d828af22f592134e8932480637c0d', 93834278, 'Admin', 'GHARDIMAOU'),
(13, 'rabie', 'zouita', 'rabie.zouita@esprit.tn', 'rabie', '7c222fb2927d828af22f592134e8932480637c0d', 93936565, 'Client', 'kef');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `bonplan`
--
ALTER TABLE `bonplan`
  ADD PRIMARY KEY (`id_plan`),
  ADD KEY `id_categorie` (`id`);

--
-- Index pour la table `categorie`
--
ALTER TABLE `categorie`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `evennement`
--
ALTER TABLE `evennement`
  ADD PRIMARY KEY (`id_evennement`);

--
-- Index pour la table `location`
--
ALTER TABLE `location`
  ADD PRIMARY KEY (`id_location`),
  ADD KEY `id_utilisateur` (`id_utilisateur`),
  ADD KEY `ref_materiel` (`ref_materiel`);

--
-- Index pour la table `materiel`
--
ALTER TABLE `materiel`
  ADD PRIMARY KEY (`ref_materiel`),
  ADD KEY `id_categorie` (`id_categorie`);

--
-- Index pour la table `promo`
--
ALTER TABLE `promo`
  ADD PRIMARY KEY (`id_promo`),
  ADD KEY `id_utilisateur` (`id_utilisateur`);

--
-- Index pour la table `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`id_reservation`),
  ADD KEY `id_evennement` (`id_evennement`),
  ADD KEY `id_utilisateur` (`id_utilisateur`);

--
-- Index pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`id_utilisateur`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `bonplan`
--
ALTER TABLE `bonplan`
  MODIFY `id_plan` int(250) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `categorie`
--
ALTER TABLE `categorie`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `evennement`
--
ALTER TABLE `evennement`
  MODIFY `id_evennement` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `location`
--
ALTER TABLE `location`
  MODIFY `id_location` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `promo`
--
ALTER TABLE `promo`
  MODIFY `id_promo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT pour la table `reservation`
--
ALTER TABLE `reservation`
  MODIFY `id_reservation` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `id_utilisateur` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `bonplan`
--
ALTER TABLE `bonplan`
  ADD CONSTRAINT `bonplan_ibfk_1` FOREIGN KEY (`id`) REFERENCES `categorie` (`id`);

--
-- Contraintes pour la table `location`
--
ALTER TABLE `location`
  ADD CONSTRAINT `location_ibfk_1` FOREIGN KEY (`id_utilisateur`) REFERENCES `utilisateur` (`id_utilisateur`),
  ADD CONSTRAINT `location_ibfk_2` FOREIGN KEY (`ref_materiel`) REFERENCES `materiel` (`ref_materiel`);

--
-- Contraintes pour la table `materiel`
--
ALTER TABLE `materiel`
  ADD CONSTRAINT `materiel_ibfk_1` FOREIGN KEY (`id_categorie`) REFERENCES `categorie` (`id`);

--
-- Contraintes pour la table `promo`
--
ALTER TABLE `promo`
  ADD CONSTRAINT `promo_ibfk_1` FOREIGN KEY (`id_utilisateur`) REFERENCES `utilisateur` (`id_utilisateur`);

--
-- Contraintes pour la table `reservation`
--
ALTER TABLE `reservation`
  ADD CONSTRAINT `reservation_ibfk_2` FOREIGN KEY (`id_evennement`) REFERENCES `evennement` (`id_evennement`),
  ADD CONSTRAINT `reservation_ibfk_3` FOREIGN KEY (`id_utilisateur`) REFERENCES `utilisateur` (`id_utilisateur`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
