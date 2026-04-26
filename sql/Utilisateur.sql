CREATE TABLE Utilisateurs (
    id_utilisateur INT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    mot_de_passe VARCHAR(255) NOT NULL,
    role ENUM('ETUDIANT','CUISINIERE') NOT NULL,
    specialite VARCHAR(100),
    date_creation TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Portefeuilles (
    id_portefeuille INT PRIMARY KEY AUTO_INCREMENT,
    id_utilisateur INT UNIQUE NOT NULL,
    solde DECIMAL(10,2) DEFAULT 0.00,
    FOREIGN KEY (id_utilisateur)
    REFERENCES Utilisateurs(id_utilisateur)
    ON DELETE CASCADE
);

CREATE TABLE Transactions (
    id_transaction INT PRIMARY KEY AUTO_INCREMENT,
    id_portefeuille INT NOT NULL,
    montant DECIMAL(10,2) NOT NULL,
    type_transaction ENUM('RECHARGE','PREPAIEMENT') NOT NULL,
    date_transaction TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_portefeuille)
    REFERENCES Portefeuilles(id_portefeuille)
    ON DELETE CASCADE
);

INSERT INTO Utilisateurs (
nom,
email,
mot_de_passe,
role
)
VALUES (
'Aziz',
aziz@email.com,
'1234',
'ETUDIANT'
);

INSERT INTO Portefeuilles (
id_utilisateur,
solde
)
VALUES (
1,
50.00
);

START TRANSACTION;

UPDATE Portefeuilles
SET solde = solde + 20.00
WHERE id_portefeuille = 1;

INSERT INTO Transactions(
id_portefeuille,
montant,
type_transaction
)
VALUES(
1,
20.00,
'RECHARGE'
);

UPDATE Portefeuilles
SET solde = solde - 8.00
WHERE id_portefeuille = 1
AND solde >= 8.00;

INSERT INTO Transactions(
id_portefeuille,
montant,
type_transaction
)
VALUES(
1,
8.00,
'PREPAIEMENT'
);

COMMIT;