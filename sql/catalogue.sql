CREATE TABLE Plats (
    id_plat INT PRIMARY KEY AUTO_INCREMENT,
    nom_plat VARCHAR(100) NOT NULL,
    description_plat TEXT,
    prix_unitaire DECIMAL(10, 3) NOT NULL,
    temps_preparation INT,
    note_moyenne DECIMAL(2, 1),
    disponibilite BOOLEAN DEFAULT TRUE
);

INSERT INTO Plats (nom_plat, description_plat, prix_unitaire, temps_preparation, note_moyenne) VALUES 
('Chakchouka', 'Plat traditionnel aux oeufs', 6.500, 15, 4.5),
('Assiette Kebab', 'Viande grillée et frites', 9.500, 10, 4.8),
('Soupe Hlelem', 'Soupe aux légumes', 4.000, 20, 4.2),
('Sandwich Thon', 'Pain maison et harissa', 5.500, 5, 4.0),
('Couscous Poulet', 'Couscous tunisien', 12.000, 30, 4.9);

SELECT * FROM Plats 
WHERE prix_unitaire <= 7.000 
ORDER BY prix_unitaire ASC;

SELECT * FROM Plats 
WHERE note_moyenne >= 4.5 
ORDER BY note_moyenne DESC;

SELECT * FROM Plats 
WHERE nom_plat LIKE '%Kebab%';