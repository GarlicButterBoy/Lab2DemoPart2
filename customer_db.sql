--Customers table for the Lab 7 example for WEBD4201
--The script should be run against a webd4201example_db on port 5432 in pgAdmin.
--webd4201example_db should be owned by a user with id db_user with a password of db_password 

DROP TABLE IF EXISTS Customers;
CREATE TABLE Customers(
	PhoneNumber VARCHAR(10) PRIMARY KEY,
	Address VARCHAR(30) NOT NULL,
	Name VARCHAR(40) NOT NULL
);
ALTER TABLE Customers OWNER TO db_user;
INSERT INTO Customers(PhoneNumber, Address, Name) VALUES ('2345678901', 'Vancouver', 'Sue');
INSERT INTO Customers(PhoneNumber, Address, Name) VALUES ('6139321023', 'Kingston', 'Brian');
INSERT INTO Customers(PhoneNumber, Address, Name) VALUES ('4165701234', 'Toronto', 'Janet');
INSERT INTO Customers(PhoneNumber, Address, Name) VALUES ('9054320173', 'Oshawa', 'Fred');
INSERT INTO Customers(PhoneNumber, Address, Name) VALUES ('9052440011', 'Bowmanville', 'Margaret');
SELECT * FROM Customers;