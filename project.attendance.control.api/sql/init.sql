CREATE TABLE attendance.member(
    id INT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255) NOT NULL,
    lastname VARCHAR(255) NOT NULL,
    biometricdata LONGTEXT NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE attendance.event(
    id INT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE attendance.register(
    id BIGINT AUTO_INCREMENT NOT NULL,
    idmember INT NOT NULL,
    idevent INT NOT NULL,
    registerdate DATE NOT NULL,
    registertime TIME NOT NULL,
    FOREIGN KEY (idevent) REFERENCES attendance.event(id),
    FOREIGN KEY (idmember) REFERENCES attendance.member(id),
    PRIMARY KEY (id)
);

SELECT * FROM attendance.members
