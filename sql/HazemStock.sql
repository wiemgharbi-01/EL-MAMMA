CREATE TABLE Plat (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(100),
    prix DECIMAL(5,2)
);

CREATE TABLE Utilisateur (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(100),
    email VARCHAR(100)
);

CREATE TABLE Stock (
    id INT PRIMARY KEY AUTO_INCREMENT,
    plat_id INT,
    quantite INT,
    FOREIGN KEY (plat_id) REFERENCES Plat(id)
);

CREATE TABLE Reservations (
    id INT PRIMARY KEY AUTO_INCREMENT,
    utilisateur_id INT,
    plat_id INT,
    date_reservation DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (utilisateur_id) REFERENCES Utilisateur(id),
    FOREIGN KEY (plat_id) REFERENCES Plat(id)
);