
DROP DATABASE IF EXISTS mediLabPatients;

create database mediLabPatients;
use mediLabPatients;

create table Patient(
	Id integer NOT NULL AUTO_INCREMENT PRIMARY KEY,
	LastName varchar(255) NOT NULL,
	FirstName varchar(255) NOT NULL,
	DateOfBirth date NOT NULL,
	Gender enum('M','F') NOT NULL,
	Address varchar(255),
	Tel varchar(255)
);

-- Data for testing 
insert into Patient(LastName, FirstName, DateOfBirth, Gender, Address, Tel) values("TestNone", "Test", "1966-12-31", 'F', "1 Brookside St", "100-222-3333");
insert into Patient(LastName, FirstName, DateOfBirth, Gender, Address, Tel) values("TestBorderline", "Test", "1945-06-24", 'M', "2 High St", "200-333-4444");
insert into Patient(LastName, FirstName, DateOfBirth, Gender, Address, Tel) values("TestInDanger", "Test", "2004-06-18", 'M', "3 Club Road", "300-444-5555");
insert into Patient(LastName, FirstName, DateOfBirth, Gender, Address, Tel) values("TestEarlyOnset", "Test", "2002-06-28", 'F', "4 Valley Dr", "400-555-6666");
