
/* Drop Tables */

IF ObJECt_ID('[LogsDocumentos]') IS NOT NULL DROP TABLE [LogsDocumentos];
IF ObJECt_ID('[Segmentacion]') IS NOT NULL DROP TABLE [Segmentacion];
IF ObJECt_ID('[Documentos]') IS NOT NULL DROP TABLE [Documentos];
IF ObJECt_ID('[Acceso]') IS NOT NULL DROP TABLE [Acceso];
IF ObJECt_ID('[Nivel_Privacidad]') IS NOT NULL DROP TABLE [Nivel_Privacidad];
IF ObJECt_ID('[Algoritmos_Cifrado]') IS NOT NULL DROP TABLE [Algoritmos_Cifrado];
IF ObJECt_ID('[Integridad]') IS NOT NULL DROP TABLE [Integridad];
IF ObJECt_ID('[Storage]') IS NOT NULL DROP TABLE [Storage];




/* Create Tables */

CREATE TABLE [Acceso]
(
	[Id_Acceso] int NOT NULL IDENTITY ,
	[Id_NivelPrivacidad] int NOT NULL,
	[TipoAcceso] varchar(max),
	[DescripcionTipoAcceso] text,
	PRIMARY KEY ([Id_Acceso])
);


CREATE TABLE [Algoritmos_Cifrado]
(
	[Id_Algoritmo] int NOT NULL IDENTITY ,
	[Algoritmo] varchar(max),
	[KeyUrl] text,
	PRIMARY KEY ([Id_Algoritmo])
);


CREATE TABLE [Documentos]
(
	[Id_Documento] int NOT NULL IDENTITY ,
	[HashId] varchar(12) NOT NULL,
	[Id_Acceso] int NOT NULL,
	[Id_integridad] int NOT NULL,
	[Nombre] varchar(max),
	[Numero_Segmentos] int,
	[HashDocumento] text,
	[Disponibilidad] bit,
	[FechaCreacionDocumento] datetime,
	PRIMARY KEY ([Id_Documento])
);


CREATE TABLE [Integridad]
(
	[Id_integridad] int NOT NULL IDENTITY ,
	[Integridad] text,
	PRIMARY KEY ([Id_integridad])
);


CREATE TABLE [LogsDocumentos]
(
	[Id_Log] int NOT NULL IDENTITY ,
	[Id_Documento] int NOT NULL,
	[Id_Usuario] int,
	[DescripcionLog] text,
	[fechaLog] datetime,
	PRIMARY KEY ([Id_Log])
);


CREATE TABLE [Nivel_Privacidad]
(
	[Id_NivelPrivacidad] int NOT NULL IDENTITY ,
	[Id_Algoritmo] int NOT NULL,
	[Id_Rol] int,
	[NivelPrivacidad] varchar(max),
	[DescripcionPrivacidad] text,
	PRIMARY KEY ([Id_NivelPrivacidad])
);


CREATE TABLE [Segmentacion]
(
	[Id_Segmentacion] int NOT NULL IDENTITY ,
	[Id_Storage] int NOT NULL,
	[Id_Documento] int NOT NULL,
	[Posicion] int,
	[UrlSegmento] text,
	[HashSegmento] text,
	[HashAnt] text,
	[HashPos] text,
	PRIMARY KEY ([Id_Segmentacion])
);


CREATE TABLE [Storage]
(
	[Id_Storage] int NOT NULL IDENTITY ,
	[NombreStorage] varchar(max),
	[UrlStorage] text,
	PRIMARY KEY ([Id_Storage])
);



/* Create Foreign Keys */

ALTER TABLE [Documentos]
	ADD FOREIGN KEY ([Id_Acceso])
	REFERENCES [Acceso] ([Id_Acceso])
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE [Nivel_Privacidad]
	ADD FOREIGN KEY ([Id_Algoritmo])
	REFERENCES [Algoritmos_Cifrado] ([Id_Algoritmo])
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE [LogsDocumentos]
	ADD FOREIGN KEY ([Id_Documento])
	REFERENCES [Documentos] ([Id_Documento])
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE [Segmentacion]
	ADD FOREIGN KEY ([Id_Documento])
	REFERENCES [Documentos] ([Id_Documento])
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE [Documentos]
	ADD FOREIGN KEY ([Id_integridad])
	REFERENCES [Integridad] ([Id_integridad])
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE [Acceso]
	ADD FOREIGN KEY ([Id_NivelPrivacidad])
	REFERENCES [Nivel_Privacidad] ([Id_NivelPrivacidad])
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE [Segmentacion]
	ADD FOREIGN KEY ([Id_Storage])
	REFERENCES [Storage] ([Id_Storage])
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;



