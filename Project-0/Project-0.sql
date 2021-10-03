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
	junction_id		INT NOT NULL AUTO_INCREMENT,
	account_id 		INT NOT NULL,
	customer_id 	INT NOT NULL,
	INDEX (account_id),
	INDEX (customer_id),
	CONSTRAINT accounts_customers_pk PRIMARY KEY (junction_id)
	#CONSTRAINT accounts_customers_account_fk  FOREIGN KEY (account_id) REFERENCES accounts (account_id),
	#CONSTRAINT accounts_customers_customer_fk FOREIGN KEY (customer_id) REFERENCES customers (customer_id)
);

CREATE TABLE customers
(
    customer_id 	INT(10) NOT NULL,
    first_name 		VARCHAR(20),
    last_name 		VARCHAR(20),
    username 		VARCHAR(20),
	password 		VARCHAR(20),
	email 			VARCHAR(40),
    
    CONSTRAINT customers_pk PRIMARY KEY (customer_id),
    CONSTRAINT customers_accounts_customers_fk FOREIGN KEY (customer_id) REFERENCES accounts_customers (customer_id)
);

CREATE TABLE accounts
(
    account_id 		INT(10) NOT NULL,
    account_type	VARCHAR(20),
    balance 		DECIMAL (10, 2),
    CONSTRAINT accounts_pk PRIMARY KEY (account_id), 
    CONSTRAINT accounts_accounts_customers_fk FOREIGN KEY (account_id) REFERENCES accounts_customers (account_id)
);



###########################################################
################# POPULATE FRESH DATABASE #################
###########################################################

INSERT INTO accounts_customers (customer_id, account_id) VALUES (001, 001);
INSERT INTO accounts_customers (customer_id, account_id) VALUES (002, 002);
INSERT INTO accounts_customers (customer_id, account_id) VALUES (003, 003);

INSERT INTO accounts (account_id, account_type, balance) VALUES (001, "Chekings" ,1500.50);
INSERT INTO accounts (account_id, account_type, balance) VALUES (002, "Savings" ,2780.25);
INSERT INTO accounts (account_id, account_type, balance) VALUES (003, "Chekings" ,150);


INSERT INTO customers (customer_id, first_name, last_name, username, password, email) VALUES (001, "Jullye", "Jones", "User123", "Pass123", "someEmail1@spi.org");
INSERT INTO customers (customer_id, first_name, last_name, username, password, email) VALUES (002, "Rick", "Mcdowell", "Test1", "ABCDEFG!", "someEmail2@gmail.com");
INSERT INTO customers (customer_id, first_name, last_name, username, password, email) VALUES (003, "Mike", "Oliver", "MkXo001", "dqS%DYnB^yDY", "someEmail3@rev.net");


###########################################################
################### TEST FRESH DATABASE ###################
###########################################################

select * from customers c 
join accounts_customers ac on c.customer_id = ac.customer_id
join accounts a on ac.account_id = a.account_id

