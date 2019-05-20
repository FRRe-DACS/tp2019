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
