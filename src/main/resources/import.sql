INSERT INTO `dsb`.`address` (`addressid`, `city`, `house_number`, `street`) VALUES ('11', 'Amsterdam', '1', 'Straat');
INSERT INTO `dsb`.`customer` (`customerid`, `initials`, `password`, `social_security_no`, `surname`, `username`, `address_addressid`) VALUES ('11', 'D.', 'dennis', '123456', 'Kramer', 'dennis', '11');
INSERT INTO `dsb`.`customer` (`customerid`, `initials`, `password`, `social_security_no`, `surname`, `username`, `address_addressid`) VALUES ('12', 'E.M.', 'miel', '123456', 'van Welzen', 'miel', '11');

INSERT INTO `dsb`.`company` (`company_id`, `btwno`, `kvkno`, `name`) VALUES ('11', 'NL0012345B02', '14729462', 'HvA Corp.');

INSERT INTO `dsb`.`account` (`accountid`, `account_no`, `balance`) VALUES ('110', 'NL40DSBB0123456789', '1000');
INSERT INTO `dsb`.`account` (`accountid`, `account_no`, `balance`) VALUES ('111', 'NL41DSBB0123456789', '1500');
INSERT INTO `dsb`.`account` (`accountid`, `account_no`, `balance`) VALUES ('112', 'NL42DSBB0123456789', '1250');
INSERT INTO `dsb`.`account` (`accountid`, `account_no`, `balance`) VALUES ('113', 'NL43DSBB0123456789', '10000');
INSERT INTO `dsb`.`account` (`accountid`, `account_no`, `balance`) VALUES ('114', 'NL44DSBB0123456789', '12500');
INSERT INTO `dsb`.`account` (`accountid`, `account_no`, `balance`) VALUES ('115', 'NL45DSBB0123456789', '15000');

INSERT INTO `dsb`.`consumer_account` (`accountid`) VALUES ('110');
INSERT INTO `dsb`.`consumer_account` (`accountid`) VALUES ('111');
INSERT INTO `dsb`.`consumer_account` (`accountid`) VALUES ('112');

INSERT INTO `dsb`.`smeaccount` (`accountid`, `company_company_id`) VALUES ('113', '11');
INSERT INTO `dsb`.`smeaccount` (`accountid`, `company_company_id`) VALUES ('114', '11');
INSERT INTO `dsb`.`smeaccount` (`accountid`, `company_company_id`) VALUES ('115', '11');

INSERT INTO dsb.account_holders VALUES (110, 11);
INSERT INTO dsb.account_holders VALUES (111, 11);
INSERT INTO dsb.account_holders VALUES (112, 11);
INSERT INTO dsb.account_holders VALUES (113, 11);
INSERT INTO dsb.account_holders VALUES (114, 11);
INSERT INTO dsb.account_holders VALUES (115, 11);

INSERT INTO `dsb`.`company_accounts` (`company_company_id`, `accounts_accountid`) VALUES ('11', '113');
INSERT INTO `dsb`.`company_accounts` (`company_company_id`, `accounts_accountid`) VALUES ('11', '114');
INSERT INTO `dsb`.`company_accounts` (`company_company_id`, `accounts_accountid`) VALUES ('11', '115');

INSERT INTO `dsb`.`transaction` (`transactionid`, `message`, `transaction_amount`, `transaction_timestamp`, transaction_account_credit_accountid, transaction_account_debet_accountid) VALUES ('300', 'Huur', 100, '2020-01-01 01:01:01', 110, 111);
INSERT INTO `dsb`.`transaction` (`transactionid`, `message`, `transaction_amount`, `transaction_timestamp`, transaction_account_credit_accountid, transaction_account_debet_accountid) VALUES ('301', 'Etentje', 100, '2020-01-02 01:01:01', 110, 112);
INSERT INTO `dsb`.`transaction` (`transactionid`, `message`, `transaction_amount`, `transaction_timestamp`, transaction_account_credit_accountid, transaction_account_debet_accountid) VALUES ('302', 'Hondevoer', 100, '2020-01-03 01:01:01', 111, 113);
INSERT INTO `dsb`.`transaction` (`transactionid`, `message`, `transaction_amount`, `transaction_timestamp`, transaction_account_credit_accountid, transaction_account_debet_accountid) VALUES ('303', 'Ballonvaart', 100, '2020-01-04 01:01:01', 110, 114);
INSERT INTO `dsb`.`transaction` (`transactionid`, `message`, `transaction_amount`, `transaction_timestamp`, transaction_account_credit_accountid, transaction_account_debet_accountid) VALUES ('304', 'Verjaardag', 100, '2020-01-05 01:01:01', 111, 115);

INSERT INTO `dsb`.`employee` (`employeeid`, `employee_user_name`, `initials`, `password`, `surname`) VALUES ('45', 'sicco.', 'S.', 'koetsier', 'Koetsier');
INSERT INTO `dsb`.`employee` (`employeeid`, `employee_user_name`, `initials`, `password`, `surname`) VALUES ('46', 'linda.', 'L.', 'haest', 'Haest');

INSERT INTO dsb.sector VALUES (0, 'Bouw')
INSERT INTO dsb.sector VALUES (1, 'Cultuur, sport en recreatie')
INSERT INTO dsb.sector VALUES (2, 'Detailhandel')
INSERT INTO dsb.sector VALUES (3, 'Energie, water en milieu')
INSERT INTO dsb.sector VALUES (4, 'Financiële instellingen')
INSERT INTO dsb.sector VALUES (5, 'Gezondheid')
INSERT INTO dsb.sector VALUES (6, 'Groothandel')
INSERT INTO dsb.sector VALUES (7, 'Horeca')
INSERT INTO dsb.sector VALUES (8, 'ICT en media')
INSERT INTO dsb.sector VALUES (9, 'Industrie')
INSERT INTO dsb.sector VALUES (10, 'Land- en tuinbouw')
INSERT INTO dsb.sector VALUES (11, 'Logistiek')
INSERT INTO dsb.sector VALUES (12, 'Overig')
INSERT INTO dsb.sector VALUES (13, 'Zakelijke diensten')