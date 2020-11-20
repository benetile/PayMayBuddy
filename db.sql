
CREATE SEQUENCE public.utilisateur_id_utilisateur_seq;

CREATE TABLE public.utilisateur (
                id_utilisateur INTEGER NOT NULL DEFAULT nextval('public.utilisateur_id_utilisateur_seq'),
                prenom VARCHAR(60) NOT NULL,
                nom VARCHAR(60) NOT NULL,
                email VARCHAR(60) NOT NULL,
                tel VARCHAR(15) NOT NULL,
                mot_de_passe VARCHAR(50) NOT NULL,
                CONSTRAINT utilisateur_pk PRIMARY KEY (id_utilisateur)
);


ALTER SEQUENCE public.utilisateur_id_utilisateur_seq OWNED BY public.utilisateur.id_utilisateur;

CREATE SEQUENCE public.beneficiaire_id_beneficiaire_seq;

CREATE TABLE public.beneficiaire (
                id_beneficiaire INTEGER NOT NULL DEFAULT nextval('public.beneficiaire_id_beneficiaire_seq'),
                email VARCHAR(60) NOT NULL,
                id_utilisateur INTEGER NOT NULL,
                CONSTRAINT beneficiaire_pk PRIMARY KEY (id_beneficiaire, email)
);


ALTER SEQUENCE public.beneficiaire_id_beneficiaire_seq OWNED BY public.beneficiaire.id_beneficiaire;

CREATE SEQUENCE public.compte_id_compte_seq;

CREATE TABLE public.compte (
                id_compte INTEGER NOT NULL DEFAULT nextval('public.compte_id_compte_seq'),
                iban VARCHAR(30),
                sold NUMERIC NOT NULL,
                id_utilisateur INTEGER NOT NULL,
                CONSTRAINT compte_pk PRIMARY KEY (id_compte)
);


ALTER SEQUENCE public.compte_id_compte_seq OWNED BY public.compte.id_compte;

CREATE SEQUENCE public.transaction_id_transaction_seq;

CREATE TABLE public.transaction (
                id_transaction INTEGER NOT NULL DEFAULT nextval('public.transaction_id_transaction_seq'),
                description VARCHAR(30) NOT NULL,
                date DATE NOT NULL,
                montant NUMERIC(7,2) NOT NULL,
                id_compte INTEGER NOT NULL,
                email VARCHAR(60) NOT NULL,
                id_utilisateur INTEGER NOT NULL,
                CONSTRAINT transaction_pk PRIMARY KEY (id_transaction)
);


ALTER SEQUENCE public.transaction_id_transaction_seq OWNED BY public.transaction.id_transaction;

ALTER TABLE public.compte ADD CONSTRAINT utilisateur_compte_fk
FOREIGN KEY (id_utilisateur)
REFERENCES public.utilisateur (id_utilisateur)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE public.transaction ADD CONSTRAINT utilisateur_transaction_fk
FOREIGN KEY (id_utilisateur)
REFERENCES public.utilisateur (id_utilisateur)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE public.beneficiaire ADD CONSTRAINT utilisateur_beneficiaire_fk
FOREIGN KEY (id_utilisateur)
REFERENCES public.utilisateur (id_utilisateur)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;

ALTER TABLE public.transaction ADD CONSTRAINT compte_transaction_fk
FOREIGN KEY (id_compte)
REFERENCES public.compte (id_compte)
ON DELETE CASCADE
ON UPDATE CASCADE
NOT DEFERRABLE;


INSERT INTO utilisateur VALUES(1,'Benny','Etile','etilebenny@gmail.com','07896363','benny'),
(2,'James','Bond','jamesbond@gmail.com','00777777','bond'),
(3,'lebron','james','lebron@gmail.com','0824444525','james'),
(4,'john','snow','john@yahoo.fr','0456596','loup');

INSERT INTO compte VALUES (1,'FR451521521510',5,4),(2,'FR84512051051',67,1),(3,'BEL845152541',4000,2),(4,'GR41520521515',1850,3);

INSERT INTO transaction VALUES(1,'ravitaillement','2020-10-04',50.00,3,'john@yahoo.fr',4),
(2,'aide','2020-11-12',45.00,4,'etilebenny@gmail.com',1);


UPDATE compte SET sold=500 WHERE id_compte=1; 

UPDATE compte SET sold=350 WHERE id_compte=2;

COMMIT;

UPDATE compte SET sold=10 WHERE id_compte=3; 

ROLLBACK;
