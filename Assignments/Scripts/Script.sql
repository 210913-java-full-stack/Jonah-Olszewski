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
    accountId 		INT AUTO_INCREMENT,
    accountType		VARCHAR(200)
    balance 		DECIMAL (10, 2),
    CONSTRAINT accounts_pk PRIMARY KEY (account_id), 
    CONSTRAINT accounts_accounts_customers_fk FOREIGN KEY (account_id) REFERENCES accounts_customers (account_id)
);

CREATE TABLE customers
(
    customerId 	INT AUTO_INCREMENT,
    fistName 			VARCHAR(200),
    lastName 			VARCHAR(200),
    userName 			VARCHAR(200),
	passWord 			VARCHAR(200),
	email 				VARCHAR(200),
    
    CONSTRAINT customers_pk PRIMARY KEY (customer_id), 
    CONSTRAINT customers_accounts_customers_fk FOREIGN KEY (customer_id) REFERENCES accounts_customers (customer_id),
    CONSTRAINT customers_address_fk FOREIGN KEY (address_id) REFERENCES address (address_id)
);

###########################################################
################# POPULATE FRESH DATABASE #################
###########################################################

INSERT INTO accounts_customers (customer_id, account_id) VALUES (0001, 900001);
INSERT INTO accounts_customers (customer_id, account_id) VALUES (0001, 900002);
INSERT INTO accounts_customers (customer_id, account_id) VALUES (0002, 900003);
INSERT INTO accounts_customers (customer_id, account_id) VALUES (0002, 900004);
INSERT INTO accounts_customers (customer_id, account_id) VALUES (0003, 900005);
INSERT INTO accounts_customers (customer_id, account_id) VALUES (0004, 900006);
INSERT INTO accounts_customers (customer_id, account_id) VALUES (0005, 900007);

INSERT INTO customers (customer_id, name, address_id) VALUES (0001, "Jason". "Smith", 2);
INSERT INTO customers (customer_id, name, address_id) VALUES (0002, "Amanda", "Smith", 3);
INSERT INTO customers (customer_id, name, address_id) VALUES (0003, "John", "Cross", 1);


INSERT INTO accounts (account_id, balance) VALUES (900001, 1500.50);
INSERT INTO accounts (account_id, balance) VALUES (900002, 2780.25);
INSERT INTO accounts (account_id, balance) VALUES (900003, 150);





###########################################################
################### TEST FRESH DATABASE ###################
###########################################################
