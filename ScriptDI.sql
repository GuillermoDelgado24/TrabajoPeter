DROP DATABASE IF EXISTS InterfazTic;
CREATE DATABASE InterfazTic;
USE InterfazTic;

CREATE TABLE Espacios (
ID_Espacio int AUTO_INCREMENT Primary Key,
Descripcion varchar(255)
);

CREATE TABLE Usuarios (
ID_Usuario INT AUTO_INCREMENT PRIMARY KEY,
nombre_usuario varchar(100) NOT NULL UNIQUE,
nombre_apellidos VARCHAR(150) NOT NULL,
correo_electronico VARCHAR(150) NOT NULL,
telefono VARCHAR(20),
contrasena varchar(500)
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


CREATE TABLE Incidencias (
ID_Incidencia INT AUTO_INCREMENT PRIMARY KEY,
estado ENUM('alta','asignada','en curso','cerrada') NOT NULL DEFAULT 'alta',
resultado_cierre ENUM('con éxito','con éxito parcial','sin_éxito') DEFAULT NULL,
f_cierre DATE,
f_entrada DATE NOT NULL,
tipo_incidencia VARCHAR(100) NOT NULL,
ID_Usuario INT,
ID_Tecnico INT DEFAULT NULL,
descripcion_incidencia VARCHAR(500) NOT NULL,
descripcion_solucion VARCHAR(500) DEFAULT NULL,
CONSTRAINT fk_incidencia_usuario
FOREIGN KEY (ID_Usuario)
REFERENCES Usuarios(ID_Usuario),
CONSTRAINT fk_incidencia_tecnico
FOREIGN KEY (ID_Tecnico)
REFERENCES Tecnicos(ID_Tecnico),
CHECK ( (estado = 'cerrada' AND resultado_cierre IS NULL) 
OR (estado = 'cerrada' AND resultado_cierre IS NOT NULL) )
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
);

CREATE TABLE Dispositivos (
ID_Dispositivo INT AUTO_INCREMENT PRIMARY KEY,
ID_Espacio INT,
Descripcion varchar(255),
Marca varchar(50),
Modelo varchar(50),
CONSTRAINT FK_DISPOSITIVO_ESPACIO
FOREIGN KEY (ID_Espacio)
REFERENCES Espacios(ID_Espacio)
);
