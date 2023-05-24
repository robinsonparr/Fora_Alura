CREATE TABLE respuestas (
    idRespuesta BIGINT NOT NULL AUTO_INCREMENT,
    mensaje VARCHAR(300) NOT NULL UNIQUE,
    fechaCreacion DATETIME NOT NULL,
    solucion BOOLEAN NOT NULL,
    idTopico BIGINT NOT NULL,
    idAutor BIGINT NOT NULL,
    PRIMARY KEY (idRespuesta),
    FOREIGN KEY(idTopico) REFERENCES topicos(idTopico) ON DELETE CASCADE,
    FOREIGN KEY(idAutor) REFERENCES usuarios(idUsuario) ON DELETE CASCADE
);