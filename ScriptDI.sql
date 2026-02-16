DROP DATABASE IF EXISTS InterfazTic;
CREATE DATABASE InterfazTic;
USE InterfazTic;

CREATE TABLE Espacios (
ID_Espacio int AUTO_INCREMENT Primary Key,
Descripcion varchar(255) UNIQUE
);

CREATE TABLE Usuarios (
ID_Usuario INT AUTO_INCREMENT PRIMARY KEY,
nombre_usuario varchar(100) NOT NULL UNIQUE,
nombre_apellidos VARCHAR(150) NOT NULL,
correo_electronico VARCHAR(150) NOT NULL,
telefono VARCHAR(20),
contrasena varchar(64)
);

CREATE TABLE Tecnicos (
ID_Tecnico INT AUTO_INCREMENT,
ID_Usuario INT,
PRIMARY KEY (ID_Tecnico, ID_Usuario),
CONSTRAINT fk_tecnico_usuario
FOREIGN KEY (ID_Usuario)
REFERENCES Usuarios(ID_Usuario)
ON DELETE CASCADE
ON UPDATE CASCADE
);

CREATE TABLE Gestores (
ID_Gestor INT AUTO_INCREMENT,
ID_Usuario INT,
es_administrador BOOLEAN default false,
PRIMARY KEY (ID_Gestor, ID_Usuario),
CONSTRAINT fk_gestor_usuario
FOREIGN KEY (ID_Usuario)
REFERENCES Usuarios(ID_Usuario)
ON DELETE CASCADE
ON UPDATE CASCADE
);

CREATE TABLE Tipos_Incidencias (
tipo_incidencia VARCHAR(100) PRIMARY KEY
); 

CREATE TABLE Incidencias (
ID_Incidencia INT AUTO_INCREMENT PRIMARY KEY,
estado ENUM('alta','asignada','en curso','cerrada') DEFAULT 'alta',
resultado_cierre ENUM('con éxito','con éxito parcial','sin_éxito') DEFAULT NULL,
f_cierre DATE,
f_entrada DATE NOT NULL,
tipo_incidencia VARCHAR(100) DEFAULT NULL,
ID_Usuario INT,
ID_Tecnico INT DEFAULT NULL,
descripcion_incidencia VARCHAR(500) NOT NULL,
descripcion_solucion VARCHAR(500) DEFAULT NULL,
prioridad INT,
CONSTRAINT fk_incidencia_usuario
FOREIGN KEY (ID_Usuario)
REFERENCES Usuarios(ID_Usuario)
ON delete set null,
CONSTRAINT fk_incidencia_tecnico
FOREIGN KEY (ID_Tecnico)
REFERENCES Tecnicos(ID_Tecnico)
on delete set null,
CONSTRAINT fk_tipo_incidencia
FOREIGN KEY (tipo_incidencia)
REFERENCES Tipos_Incidencias(tipo_incidencia)
);

CREATE TABLE Incidencias_Espacios (
ID_Espacio INT,
ID_Incidencia INT,
PRIMARY KEY (ID_Incidencia, ID_Espacio),
CONSTRAINT FK_IE_Incidencia
FOREIGN KEY (ID_Incidencia)
REFERENCES Incidencias(ID_Incidencia),
CONSTRAINT FK_IE_Espacio
FOREIGN KEY (ID_Espacio)
REFERENCES Espacios(ID_Espacio)
on delete cascade on update cascade
);

CREATE TABLE Dispositivos (
ID_Dispositivo INT AUTO_INCREMENT PRIMARY KEY,
ID_Espacio INT,
tipo varchar(100),
descripcion varchar(255),
marca varchar(50),
modelo varchar(50),
CONSTRAINT FK_DISPOSITIVO_ESPACIO
FOREIGN KEY (ID_Espacio)
REFERENCES Espacios(ID_Espacio)
on Delete cascade on update cascade
);


INSERT INTO Espacios (Descripcion) VALUES
('Aula 101'),
('Aula 202'),
('Laboratorio Informática'),
('Despacho Dirección'),
('Sala de Profesores');

INSERT INTO Usuarios (nombre_usuario, nombre_apellidos, correo_electronico, telefono, contrasena) VALUES
('admin', 'Administrador del Sistema', 'admin@centro.es', '600000001', SHA2('admin123', 256)),
('tecnico1', 'Pedro López Ruiz', 'pedro.lopez@centro.es', '600000002', SHA2('tec2026', 256)),
('tecnico2', 'Ana Torres Martín', 'ana.torres@centro.es', '600000003', SHA2('passwordSegura', 256)),
('usuario1', 'Luis Fernández Gómez', 'luis.fernandez@centro.es', '600000004', SHA2('usuario123', 256)),
('usuario2', 'Elena Moreno Díaz', 'elena.moreno@centro.es', '600000005', SHA2('claveElena', 256));

INSERT INTO Tecnicos (ID_Usuario) VALUES
(2), -- tecnico1
(3); -- tecnico2


INSERT INTO Gestores (ID_Usuario, es_administrador) VALUES
(1, TRUE),   -- admin
(2, FALSE);  -- tecnico1 como gestor

INSERT INTO Tipos_Incidencias (tipo_incidencia) VALUES
('Hardware'),
('Software'),
('Red'),
('Proyector'),
('Impresora');



INSERT INTO Incidencias (
estado,
resultado_cierre,
f_cierre,
f_entrada,
tipo_incidencia,
ID_Usuario,
ID_Tecnico,
descripcion_incidencia,
descripcion_solucion,
prioridad
) VALUES
(
'alta',
NULL,
NULL,
'2026-02-01',
'Software',
4,
NULL,
'El ordenador no arranca correctamente',
NULL,
4
),
(
'en curso',
NULL,
NULL,
'2026-02-02',
'Red',
5,
1,
'No hay conexión a Internet en el aula',
'Revisando cableado y switch',
7
),
(
'cerrada',
'con éxito',
'2026-02-03',
'2026-02-01',
'Proyector',
4,
2,
'El proyector no enciende',
'Sustitución del cable de alimentación',
3
);


INSERT INTO Incidencias_Espacios (ID_Incidencia, ID_Espacio) VALUES
(1, 3), -- Laboratorio Informática
(2, 1), -- Aula 101
(3, 2); -- Aula 202

INSERT INTO Dispositivos (ID_Espacio, tipo, descripcion, marca, modelo) VALUES (3, 'Ordenador', 'PC del profesor', 'Dell', 'OptiPlex 7090'), (3, 'Switch', 'Switch principal del laboratorio', 'Cisco', 'SG350'), (1, 'Router', 'Router del aula', 'TP-Link', 'Archer C6'), (2, 'Proyector', 'Proyector del aula', 'Epson', 'EB-X49'), (5, 'Impresora', 'Impresora compartida', 'HP', 'LaserJet Pro M404');

