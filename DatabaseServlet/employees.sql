-- Run inside MySQL:  mysql -u root -p < employees.sql
CREATE DATABASE IF NOT EXISTS test;
USE test;

DROP TABLE IF EXISTS Employees;
CREATE TABLE Employees (
    id    INT,
    age   INT,
    first VARCHAR(20),
    last  VARCHAR(20)
);

INSERT INTO Employees (id, age, first, last) VALUES (101, 20, 'Zara', 'Ali');
INSERT INTO Employees (id, age, first, last) VALUES (102, 25, 'Mahnaz', 'Fatma');
INSERT INTO Employees (id, age, first, last) VALUES (103, 28, 'Zaid', 'Khan');

SELECT * FROM Employees;
