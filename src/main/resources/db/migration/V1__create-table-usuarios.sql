CREATE TABLE usuarios (
    idUsuario BIGINT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL UNIQUE,
    email varchar(100) NOT NULL UNIQUE,
    contrasena VARCHAR(300) NOT NULL UNIQUE,
    PRIMARY KEY (idUsuario)
);