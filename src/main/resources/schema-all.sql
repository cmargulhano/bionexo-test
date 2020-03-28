DROP TABLE ubs IF EXISTS;

CREATE TABLE ubs  (
    id BIGINT IDENTITY NOT NULL PRIMARY KEY,
    name VARCHAR(255),
    address VARCHAR(255),
    city VARCHAR(255),
    neighborhood VARCHAR(255),
    municipally VARCHAR(255),
    phone VARCHAR(255),
    latitude VARCHAR(255),
    longitude VARCHAR(255),
    size VARCHAR(255),
    adaptationforseniors VARCHAR(255),
    medicalequipment VARCHAR(255),
    medicine VARCHAR(255)
);