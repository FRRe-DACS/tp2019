-- Especialidad

CREATE SEQUENCE public.especialidad_id_seq
    START WITH 100
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE especialidad (
	id 			BIGINT 		NOT NULL,
	version 	INT			NULL, 
	nombre 		VARCHAR(50) NOT NULL, 
	descripcion TEXT
);

ALTER TABLE ONLY public.especialidad
    ADD CONSTRAINT especialidad_pk PRIMARY KEY (id);

-- Obra Social

CREATE SEQUENCE public.obra_social_id_seq
    START WITH 100
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE obra_social (
	id 				BIGINT 			NOT NULL,
	version 		INT				NULL, 
	rnos			INT				NOT NULL, 
	denominacion	VARCHAR(200)	NOT NULL, 
	sigla			VARCHAR(50)		NULL, 
	domicilio		VARCHAR(55)		NOT NULL, 
	codigo_postal	INT				NOT NULL, 
	provincia		VARCHAR(50)		NOT NULL, 
	telefono		VARCHAR(50)		NULL, 
	otros_telefonos	VARCHAR(50)		NULL, 
	e_mail			VARCHAR(50)		NULL, 
	web				VARCHAR(255)	NULL
);

ALTER TABLE ONLY public.obra_social
    ADD CONSTRAINT obra_social_pk PRIMARY KEY (id);

ALTER TABLE ONLY public.obra_social
	ADD CONSTRAINT obra_social_rnos_uk UNIQUE (rnos);
    
-- Medico

CREATE SEQUENCE public.medico_id_seq
    START WITH 100
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 
CREATE TABLE medico (
	id 					BIGINT 		NOT NULL,
	version 			INT			NULL,
	nombre 				VARCHAR(50) NOT NULL, 
	apellido 			VARCHAR(50) NOT NULL,
	fecha_nacimiento 	DATE 		NOT NULL, 
	nro_matricula 		INT 		NOT NULL, 
	id_especialidad 	BIGINT 		NOT NULL
);

ALTER TABLE ONLY public.medico
    ADD CONSTRAINT medico_pk PRIMARY KEY (id);

ALTER TABLE ONLY public.medico
	ADD CONSTRAINT medico_nro_matricula_uk UNIQUE (nro_matricula);

ALTER TABLE ONLY public.medico
	ADD CONSTRAINT medico_id_especialidad_fk
	FOREIGN KEY (id_especialidad) REFERENCES especialidad;

-- Medicos - Obra Social

CREATE TABLE medico_obra_social (
	medico_id 				BIGINT 			NOT NULL,
	obra_social_id 			BIGINT 			NOT NULL
);

ALTER TABLE ONLY public.medico_obra_social
    ADD CONSTRAINT medico_obra_social_pk PRIMARY KEY (medico_id, obra_social_id);
