CREATE TABLE programmeur (
                             id INT PRIMARY KEY,
                             nom VARCHAR(255),
                             prenom VARCHAR(255),
                             adresse VARCHAR(255),
                             pseudo VARCHAR(255),
                             responsable VARCHAR(255),
                             hobby VARCHAR(255),
                             naissance INT,
                             salaire DOUBLE,
                             prime DOUBLE
);

CREATE TABLE projet (
                        id INT PRIMARY KEY AUTO_INCREMENT,
                        intitule VARCHAR(255) NOT NULL,
                        date_debut DATE,
                        date_fin_prevue DATE,
                        etat VARCHAR(50)
);

CREATE TABLE programmeur_projet (
                                     programmeur_id INT,
                                     projet_id INT,
                                     PRIMARY KEY (programmeur_id, projet_id),
                                     FOREIGN KEY (programmeur_id) REFERENCES programmeur(id) ON DELETE CASCADE,
                                     FOREIGN KEY (projet_id) REFERENCES projet(id) ON DELETE CASCADE
);