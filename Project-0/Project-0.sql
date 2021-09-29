###########################################################
################## CREATE FRESH DATABASE ##################
###########################################################
DROP DATABASE IF EXISTS Project_0;
CREATE DATABASE Project_0;

USE Project_0;

DROP TABLE IF EXISTS accounts;
DROP TABLE IF EXISTS customers;
DROP TABLE IF EXISTS accounts_customers;

CREATE TABLE accounts_customers 
(
	junction_id INT AUTO_INCREMENT,
	accountId 	INT NOT NULL ,
	customerId INT NOT NULL,
	INDEX (accountId),
	INDEX (customerId),
	CONSTRAINT accounts_customers_fk PRIMARY KEY (junction_id)
);


CREATE TABLE accounts
(
    accountId 		INT NOT NULL,
    accountType		VARCHAR(200),
    balance 		DECIMAL (10, 2),
    CONSTRAINT accounts_pk PRIMARY KEY (accountId), 
    CONSTRAINT accounts_accounts_customers_fk FOREIGN KEY (accountId) REFERENCES accounts_customers (accountId)
);

CREATE TABLE customers
(
    customerId 			INT NOT NULL,
    fistName 			VARCHAR(200),
    lastName 			VARCHAR(200),
    userName 			VARCHAR(200),
	passWord 			VARCHAR(200),
	email 				VARCHAR(200),
    
    CONSTRAINT customers_pk PRIMARY KEY (customerId), 
    CONSTRAINT customers_accounts_customers_fk FOREIGN KEY (customerId) REFERENCES accounts_customers (customerId)
);



###########################################################
################# POPULATE FRESH DATABASE #################
###########################################################

INSERT INTO accounts_customers (customerId, accountId) VALUES (0001, 900001);
INSERT INTO accounts_customers (customerId, accountId) VALUES (0002, 900002);
INSERT INTO accounts_customers (customerId, accountId) VALUES (0003, 900003);


INSERT INTO customers (customerId, fistName, lastName, userName, passWord, email) VALUES (0001, "Jullye", "Jones", "User123", "Pass123", "someEmail1@spiral.org");
INSERT INTO customers (customerId, fistName, lastName, userName, passWord, email) VALUES (0002, "Rick", "Mcdowell", "Test1", "ABCDEFG!", "someEmail2@gmail.com");
INSERT INTO customers (customerId, fistName, lastName, userName, passWord, email) VALUES (0003, "Mike", "Oliver", "MkXo001", "dqS%DYnB^yDY", "someEmail3@revature.net");


INSERT INTO accounts (accountId, accountType, balance) VALUES (900001, "Chekings" ,1500.50);
INSERT INTO accounts (accountId, accountType, balance) VALUES (900002, "Savings" ,2780.25);
INSERT INTO accounts (accountId, accountType, balance) VALUES (900003, "Chekings" ,150);



###########################################################
################### TEST FRESH DATABASE ###################
###########################################################

select * from customers c 
join accounts_customers ac on c.customerId = ac.customerId
join accounts a on ac.accountId = a.accountId

