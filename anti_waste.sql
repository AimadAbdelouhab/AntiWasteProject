-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  ven. 16 juil. 2021 à 11:06
-- Version du serveur :  5.7.24
-- Version de PHP :  7.2.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `anti_waste`
--

-- --------------------------------------------------------

--
-- Structure de la table `adresse`
--

DROP TABLE IF EXISTS `adresse`;
CREATE TABLE IF NOT EXISTS `adresse` (
  `id_adresse` bigint(20) NOT NULL AUTO_INCREMENT,
  `code_postal` varchar(255) DEFAULT NULL,
  `numero_de_rue` int(11) NOT NULL,
  `pays` varchar(255) DEFAULT NULL,
  `rue` varchar(255) DEFAULT NULL,
  `ville` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_adresse`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `adresse`
--

INSERT INTO `adresse` (`id_adresse`, `code_postal`, `numero_de_rue`, `pays`, `rue`, `ville`) VALUES
(1, '37200', 6, 'France', 'Rue Gaspard Coriolis', 'Tours'),
(2, '48900', 7, 'Algérie', 'Avenue de la pain', 'Akbou'),
(3, '06001', 65, 'Algérie', 'Lotissement Rayan', 'Akbou'),
(4, '06001', 1, 'Algérie', 'Rue du 1er Novembre', 'Akbou'),
(5, '37200', 1, 'Algérie', 'Rue Targa ouzemour', 'Béjaia'),
(6, '06001', 128, 'Algérie', 'Rue de Guendouza', 'Akbou');

-- --------------------------------------------------------

--
-- Structure de la table `commercant`
--

DROP TABLE IF EXISTS `commercant`;
CREATE TABLE IF NOT EXISTS `commercant` (
  `id_commercant` bigint(20) NOT NULL AUTO_INCREMENT,
  `categorie` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `logo` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `id_adresse` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_commercant`),
  KEY `FK40kao80nl1rvj72xx1g2tm4dc` (`id_adresse`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `commercant`
--

INSERT INTO `commercant` (`id_commercant`, `categorie`, `description`, `logo`, `nom`, `id_adresse`) VALUES
(1, 'boulangerie', 'Fabiola et Damien Marville vous accueillent dans leur boulangerie où l\'on aime se rendre, car elle fait partie des meilleures pour dénicher une bonne baguette', 'https://www.thonescoeurdesvallees.com/wp-content/uploads/wpetourisme/9014787-diaporama.jpg', 'Boulangerie Marville', 1),
(2, 'epicerie', 'description', 'https://i.pinimg.com/600x315/5b/18/49/5b184980732ce9de2150eec156496bde.jpg', 'Epicerie du coin', 2),
(3, 'epicerie', 'description', 'https://media.timeout.com/images/105633861/320/210/image.jpg', 'Le Zingam', 3),
(4, 'epicerie', 'description', 'http://img.over-blog.com/500x372/0/11/18/70/IMGP4378.JPG', 'Le 38 Gourmet', 4),
(5, 'boucherie', 'description', 'https://www.lagrandeepicerie.com/dw/image/v2/BBWG_PRD/on/demandware.static/-/Library-Sites-LGEsharedLibrary/default/dwee256232/LGE_V3/MAGASINS/UNIVERS/boucherie/boucherie-rive-gauche.jpg', 'La Boucherie', 1),
(6, 'boucherie', 'description', 'https://epiceries.imgix.net/shop_images/323886/original/155924_0006_f_1595610400.jpg?ixlib=rails-4.2.0&auto=format%2Ccompress&fit=crop&crop=faces&ch=Width,DPR&h=375', '1001 Délices', 2),
(7, 'primeur', 'description', 'https://images.ollca.com/fit-in/528x367/ollca/7df3f247-b895-4032-b6f4-a51efcf86ff8/shop/ac04d6f6-b2ec-4480-8f7c-04e331c5a9ef/1614176403.jpg', 'Le marché', 3),
(8, 'primeur', 'description', 'https://images.ollca.com/fit-in/528x367/ollca/7df3f247-b895-4032-b6f4-a51efcf86ff8/shop/59c51211-805b-4e77-a5a7-8b4786188015/1614176402.jpg', 'Los Leguminos', 4);

-- --------------------------------------------------------

--
-- Structure de la table `commercant_utilisateurs`
--

DROP TABLE IF EXISTS `commercant_utilisateurs`;
CREATE TABLE IF NOT EXISTS `commercant_utilisateurs` (
  `commercants_id_commercant` bigint(20) NOT NULL,
  `utilisateurs_id_utilisateur` bigint(20) NOT NULL,
  KEY `FK62eq0bs1lavv02lpdfhj503xm` (`utilisateurs_id_utilisateur`),
  KEY `FKp81sh7sdu57rgvwg0wwbu2ybt` (`commercants_id_commercant`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `commercant_utilisateurs`
--

INSERT INTO `commercant_utilisateurs` (`commercants_id_commercant`, `utilisateurs_id_utilisateur`) VALUES
(3, 1);

-- --------------------------------------------------------

--
-- Structure de la table `panier`
--

DROP TABLE IF EXISTS `panier`;
CREATE TABLE IF NOT EXISTS `panier` (
  `id_panier` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_et_heure` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `nbrdexemplaires` bigint(20) NOT NULL,
  `prix` double NOT NULL,
  `titre` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `id_commercant` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_panier`),
  KEY `FK8c49td4thke9k9bx9mfb9f0y0` (`id_commercant`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `panier`
--

INSERT INTO `panier` (`id_panier`, `date_et_heure`, `description`, `image`, `nbrdexemplaires`, `prix`, `titre`, `type`, `id_commercant`) VALUES
(1, '2021-07-07 15:40:00', 'Panier de 5 croissant et 5 pains au chocolat', 'https://scally.typepad.com/.a/6a00d8341c676f53ef01901cce2c05970b-pi', 0, 10, 'Panier de gourmandides', 'viénoiserie', 1),
(2, '2021-07-07 15:40:00', 'description', 'https://lh3.googleusercontent.com/proxy/zH2P5FifUqaE-jJavLO_jMSnaaHFt8SqSLJe0V0IsTberbLfbF98jSb6_5gHBVaBMkT5osd-oQGAg-k3DEHexUbroIMC9FWjnTZPaq4yGShdqlPfLxcWhvz8cAPl_v9_', 3, 15, 'Panier d\'épices', 'variété', 3),
(3, '2021-07-07 14:50:00', 'description', 'https://santecool.net/wp-content/uploads/2019/02/ab.jpg', 3, 52, 'Panier de légumes secs', 'lentilles', 3),
(4, '2021-07-08 13:40:00', 'decription', 'https://doucefrugalite.files.wordpress.com/2020/06/les-sodas.jpg', 2, 20, 'Panier de boissons', 'viande', 3),
(5, '2021-07-07 20:40:00', 'decription', 'https://images.theconversation.com/files/350975/original/file-20200804-18-vk0jr7.jpg?ixlib=rb-1.1.0&rect=44%2C269%2C5000%2C2500&q=45&auto=format&w=668&h=324&fit=crop', 4, 9.99, 'Panier de viandes', 'gazouz', 5),
(6, '2021-07-08 13:40:00', 'decription', 'https://i.pinimg.com/originals/3a/2e/7b/3a2e7b58d5b242b40c4e566dd1866d81.jpg', 2, 12, 'Panier de bouzelouf', 'gazouz', 5),
(7, '2021-07-07 15:40:00', 'decription', 'https://img.cuisineaz.com/680x357/2015/10/14/i48315-recettes-pour-une-chicken-party.jpg', 12, 13, 'Panier de charcuterie', 'viande', 5),
(8, '2021-07-07 14:50:00', 'decription', 'https://static.pourdebon.com/images/476-355/10ea3d3c881a524fc8e3731da0bcc0a6/roti_porc_1kg.jpeg', 1, 20, 'Panier de volaille', 'gazouz', 5),
(9, '2021-07-08 13:40:00', 'decription', 'https://www.unlockfood.ca/EatRightOntario/media/Website-images-resized/How-to-store-fruit-to-keep-it-fresh-resized.jpg', 2, 19.99, 'Panier de fruits', 'fruit', 7),
(10, '2021-07-07 20:40:00', 'decription', 'https://res.cloudinary.com/hv9ssmzrz/image/fetch/c_fill,f_auto,h_360,q_auto,w_740/https://s3-eu-west-1.amazonaws.com/images-ca-1-0-1-eu/tag_photos/original/15/legumes-flickr-6018347885_010e2ffbb5_o.jpg', 1, 22, 'Panier de légumes', 'legumes', 7);

-- --------------------------------------------------------

--
-- Structure de la table `reserver`
--

DROP TABLE IF EXISTS `reserver`;
CREATE TABLE IF NOT EXISTS `reserver` (
  `id_reservation` bigint(20) NOT NULL AUTO_INCREMENT,
  `etat` varchar(255) DEFAULT NULL,
  `nombre_exemplaires_reserves` int(11) NOT NULL,
  `id_panier` bigint(20) DEFAULT NULL,
  `id_utilisateur` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_reservation`),
  KEY `FK3y6pwvrr85kwrdhwlsjhhtvjx` (`id_panier`),
  KEY `FKrd9wf3obg4nd1ol11mqxsbivm` (`id_utilisateur`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `reserver`
--

INSERT INTO `reserver` (`id_reservation`, `etat`, `nombre_exemplaires_reserves`, `id_panier`, `id_utilisateur`) VALUES
(1, 'réservé', 1, 1, 4);

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
CREATE TABLE IF NOT EXISTS `utilisateur` (
  `id_utilisateur` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `mot_de_passe` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `numero_de_telephone` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_utilisateur`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id_utilisateur`, `email`, `mot_de_passe`, `nom`, `numero_de_telephone`, `prenom`) VALUES
(1, 'utilisateur@email.com', 'motdepasse', 'abdelouhab', '+33775436980', 'aimad'),
(4, 'aimad.abdelouhab@univ-tours.fr', 'motdepasse', 'abdelouhab', '+33671679893', 'aimad');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
