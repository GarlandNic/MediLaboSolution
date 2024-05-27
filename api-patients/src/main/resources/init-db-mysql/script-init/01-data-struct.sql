
DROP DATABASE IF EXISTS mediLabPatients;

create database mediLabPatients;
use mediLabPatients;

create table Patient(
	Id integer NOT NULL AUTO_INCREMENT PRIMARY KEY,
	Last_Name varchar(255) NOT NULL,
	First_Name varchar(255) NOT NULL,
	Date_Of_Birth date NOT NULL,
	Gender enum('M','F') NOT NULL,
	Address varchar(255),
	Tel varchar(255)
);
