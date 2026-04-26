-- ==========================================
-- 1. SUPPRESSION DES TABLES SI ELLES EXISTENT
-- ==========================================
DROP TABLE IF EXISTS LoyaltyPoints;
DROP TABLE IF EXISTS Consignes;
DROP TABLE IF EXISTS Utilisateurs;

-- ==========================================
-- 2. TABLE UTILISATEURS (base du système)
-- ==========================================
CREATE TABLE Utilisateurs (
    id_utilisateur INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    date_creation TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ==========================================
-- 3. TABLE CONSIGNES (bouteilles retournées)
-- ==========================================
CREATE TABLE Consignes (
    id_consignation INT AUTO_INCREMENT PRIMARY KEY,
    id_utilisateur INT NOT NULL,
    type_bouteille VARCHAR(50) NOT NULL,
    quantite INT NOT NULL,
    date_retour TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    points_gagnes INT NOT NULL,

    CONSTRAINT fk_consigne_user
    FOREIGN KEY (id_utilisateur)
    REFERENCES Utilisateurs(id_utilisateur)
    ON DELETE CASCADE
);

-- ==========================================
-- 4. TABLE LOYALTY POINTS (récompenses écologiques)
-- ==========================================
CREATE TABLE LoyaltyPoints (
    id_point INT AUTO_INCREMENT PRIMARY KEY,
    id_utilisateur INT NOT NULL,
    total_points INT DEFAULT 0,
    niveau VARCHAR(50) DEFAULT 'Bronze',
    derniere_mise_a_jour TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_loyalty_user
    FOREIGN KEY (id_utilisateur)
    REFERENCES Utilisateurs(id_utilisateur)
    ON DELETE CASCADE
);

-- ==========================================
-- 5. TRIGGER : mise à jour automatique des points
-- ==========================================
DELIMITER //

CREATE TRIGGER after_consigne_insert
AFTER INSERT ON Consignes
FOR EACH ROW
BEGIN
    -- Si utilisateur existe déjà dans LoyaltyPoints
    IF EXISTS (SELECT 1 FROM LoyaltyPoints WHERE id_utilisateur = NEW.id_utilisateur) THEN

        UPDATE LoyaltyPoints
        SET total_points = total_points + NEW.points_gagnes,
            derniere_mise_a_jour = NOW()
        WHERE id_utilisateur = NEW.id_utilisateur;

    ELSE

        INSERT INTO LoyaltyPoints (id_utilisateur, total_points)
        VALUES (NEW.id_utilisateur, NEW.points_gagnes);

    END IF;
END//

DELIMITER ;

-- ==========================================
-- 6. EXEMPLES D'INSERTION
-- ==========================================

INSERT INTO Utilisateurs (nom, email)
VALUES ('Ahmed Ben Ali', 'ahmed@mail.com');

INSERT INTO Consignes (id_utilisateur, type_bouteille, quantite, points_gagnes)
VALUES (1, 'Plastique', 5, 10);

INSERT INTO Consignes (id_utilisateur, type_bouteille, quantite, points_gagnes)
VALUES (1, 'Verre', 3, 15);

-- ==========================================
-- 7. AFFICHAGE DES POINTS
-- ==========================================
SELECT u.nom, l.total_points, l.niveau
FROM Utilisateurs u
JOIN LoyaltyPoints l ON u.id_utilisateur = l.id_utilisateur;