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

INSERT INTO `dsb`.`address` (`addressid`, `city`, `house_number`, `street`) VALUES ('99999999', 'Wognum', '6', 'Dick Ketlaan');
INSERT INTO `dsb`.`address` (`addressid`, `city`, `house_number`, `street`) VALUES ('11', 'Amsterdam', '1', 'Straat');
INSERT INTO `dsb`.`customer` (`customerid`, `initials`, `password`, `social_security_no`, `surname`, `username`, `address_addressid`) VALUES ('99999999', 'DSB', 'dsbb', '000000', 'Bank', 'dsbb', '99999999');
INSERT INTO `dsb`.`customer` (`customerid`, `initials`, `password`, `social_security_no`, `surname`, `username`, `address_addressid`) VALUES ('11', 'D.', 'dennis', '123456', 'Kramer', 'dennis', '11');
INSERT INTO `dsb`.`customer` (`customerid`, `initials`, `password`, `social_security_no`, `surname`, `username`, `address_addressid`) VALUES ('12', 'E.M.', 'miel', '123456', 'van Welzen', 'miel', '11');
INSERT INTO `dsb`.`customer` (`customerid`, `initials`, `password`, `social_security_no`, `surname`, `username`, `address_addressid`) VALUES ('13', 'M.C.', 'asd', '123456', 'Escher', 'asd', '11');
INSERT INTO `dsb`.`customer` (`customerid`, `initials`, `password`, `social_security_no`, `surname`, `username`, `address_addressid`) VALUES ('14', 'A.M.G', 'anni', '123456', 'Schmit', 'anni', '11');

INSERT INTO `dsb`.`role` (`roleid`, `role_name`) VALUES('1', 'Retail')
INSERT INTO `dsb`.`role` (`roleid`, `role_name`) VALUES('2', 'Commercial')

INSERT INTO `dsb`.`employee` (`employeeid`, `employee_user_name`, `initials`, `password`, `surname`, `role_roleid`) VALUES ('44', 'linda', 'L.', 'linda', 'Haest', '1' );
INSERT INTO `dsb`.`employee` (`employeeid`, `employee_user_name`, `initials`, `password`, `surname`, `role_roleid`) VALUES ('45', 'thijs', 'T.', 'thijs', 'Rodenburg', '2');

INSERT INTO `dsb`.`company` (`company_id`, `btwno`, `kvkno`, `name`, `sector_sector_id`, `account_manager`) VALUES ('11', 'NL0012345B03', '14729462', 'HvA Corp.', 12, '45');
INSERT INTO `dsb`.`company` (`company_id`, `btwno`, `kvkno`, `name`, `sector_sector_id`, `account_manager`) VALUES ('12', 'NL0012345B04', '66666666', 'Evil Corp.', 4, '45');
INSERT INTO `dsb`.`company` (`company_id`, `btwno`, `kvkno`, `name`, `sector_sector_id`, `account_manager`) VALUES ('13', 'NL0012345B05', '26492741', 'AvH Corp.', 12, '45');
INSERT INTO `dsb`.`company` (`company_id`, `btwno`, `kvkno`, `name`, `account_manager`, `sector_sector_id`) VALUES ('14', 'NL0012345B06', '26492744', 'ThijsICT', '45', '6');

INSERT INTO `dsb`.`account` (`accountid`, `account_no`, `balance`) VALUES ('99999999', 'NL39DSBB0000000001', 0);
INSERT INTO `dsb`.`account` (`accountid`, `account_no`, `balance`) VALUES ('110', 'NL32DSBB0123456780', 0);
INSERT INTO `dsb`.`account` (`accountid`, `account_no`, `balance`) VALUES ('111', 'NL05DSBB0123456781', 0);
INSERT INTO `dsb`.`account` (`accountid`, `account_no`, `balance`) VALUES ('112', 'NL75DSBB0123456782', 0);
INSERT INTO `dsb`.`account` (`accountid`, `account_no`, `balance`) VALUES ('113', 'NL48DSBB0123456783', 0);
INSERT INTO `dsb`.`account` (`accountid`, `account_no`, `balance`) VALUES ('114', 'NL21DSBB0123456784', 0);
INSERT INTO `dsb`.`account` (`accountid`, `account_no`, `balance`) VALUES ('115', 'NL91DSBB0123456785', 0);
INSERT INTO `dsb`.`account` (`accountid`, `account_no`, `balance`) VALUES ('116', 'NL64DSBB0123456786', 0);
INSERT INTO `dsb`.`account` (`accountid`, `account_no`, `balance`) VALUES ('117', 'NL37DSBB0123456787', 0);
INSERT INTO `dsb`.`account` (`accountid`, `account_no`, `balance`) VALUES ('118', 'NL10DSBB0123456788', 0);
INSERT INTO `dsb`.`account` (`accountid`, `account_no`, `balance`) VALUES ('119', 'NL80DSBB0123456789', 0);
INSERT INTO `dsb`.`account` (`accountid`, `account_no`, `balance`) VALUES ('120', 'NL53DSBB0123456790', 0);
INSERT INTO `dsb`.`account` (`accountid`, `account_no`, `balance`) VALUES ('121', 'NL26DSBB0123456791', 0);

INSERT INTO `dsb`.`consumer_account` (`accountid`) VALUES ('110');
INSERT INTO `dsb`.`consumer_account` (`accountid`) VALUES ('111');
INSERT INTO `dsb`.`consumer_account` (`accountid`) VALUES ('112');
INSERT INTO `dsb`.`consumer_account` (`accountid`) VALUES ('99999999');

INSERT INTO `dsb`.`smeaccount` (`accountid`, `company_company_id`) VALUES ('113', '11');
INSERT INTO `dsb`.`smeaccount` (`accountid`, `company_company_id`) VALUES ('114', '11');
INSERT INTO `dsb`.`smeaccount` (`accountid`, `company_company_id`) VALUES ('115', '11');
INSERT INTO `dsb`.`smeaccount` (`accountid`, `company_company_id`) VALUES ('116', '12');
INSERT INTO `dsb`.`smeaccount` (`accountid`, `company_company_id`) VALUES ('117', '12');
INSERT INTO `dsb`.`smeaccount` (`accountid`, `company_company_id`) VALUES ('118', '12');
INSERT INTO `dsb`.`smeaccount` (`accountid`, `company_company_id`) VALUES ('119', '13');
INSERT INTO `dsb`.`smeaccount` (`accountid`, `company_company_id`) VALUES ('120', '14');
INSERT INTO `dsb`.`smeaccount` (`accountid`, `company_company_id`) VALUES ('121', '14');


INSERT INTO `dsb`.`company_accounts` (`company_company_id`, `accounts_accountid`) VALUES ('11', '113');
INSERT INTO `dsb`.`company_accounts` (`company_company_id`, `accounts_accountid`) VALUES ('11', '114');
INSERT INTO `dsb`.`company_accounts` (`company_company_id`, `accounts_accountid`) VALUES ('11', '115');
INSERT INTO `dsb`.`company_accounts` (`company_company_id`, `accounts_accountid`) VALUES ('12', '116');
INSERT INTO `dsb`.`company_accounts` (`company_company_id`, `accounts_accountid`) VALUES ('12', '117');
INSERT INTO `dsb`.`company_accounts` (`company_company_id`, `accounts_accountid`) VALUES ('12', '118');
INSERT INTO `dsb`.`company_accounts` (`company_company_id`, `accounts_accountid`) VALUES ('14', '119');

INSERT INTO `dsb`.`account_holders` (`accounts_accountid`, `holders_customerid`) VALUES (110, 11);
INSERT INTO `dsb`.`account_holders` (`accounts_accountid`, `holders_customerid`) VALUES (111, 11);
INSERT INTO `dsb`.`account_holders` (`accounts_accountid`, `holders_customerid`) VALUES (112, 11);
INSERT INTO `dsb`.`account_holders` (`accounts_accountid`, `holders_customerid`) VALUES (113, 11);
INSERT INTO `dsb`.`account_holders` (`accounts_accountid`, `holders_customerid`) VALUES (114, 11);
INSERT INTO `dsb`.`account_holders` (`accounts_accountid`, `holders_customerid`) VALUES (115, 11);
INSERT INTO `dsb`.`account_holders` (`accounts_accountid`, `holders_customerid`) VALUES (110, 13);

INSERT INTO `dsb`.`transaction` (`transactionid`, `message`, `transaction_amount`, `transaction_timestamp`, credit_account_accountid, debit_account_accountid) VALUES ('99999980', 'DSB Startkapitaal', 1000, '2020-01-01 01:01:01', 110, 99999999);
INSERT INTO `dsb`.`transaction` (`transactionid`, `message`, `transaction_amount`, `transaction_timestamp`, credit_account_accountid, debit_account_accountid) VALUES ('99999981', 'DSB Startkapitaal', 1250, '2020-01-01 01:01:01', 111, 99999999);
INSERT INTO `dsb`.`transaction` (`transactionid`, `message`, `transaction_amount`, `transaction_timestamp`, credit_account_accountid, debit_account_accountid) VALUES ('99999982', 'DSB Startkapitaal', 1500, '2020-01-01 01:01:01', 112, 99999999);
INSERT INTO `dsb`.`transaction` (`transactionid`, `message`, `transaction_amount`, `transaction_timestamp`, credit_account_accountid, debit_account_accountid) VALUES ('99999983', 'DSB Startkapitaal', 10000, '2020-01-01 01:01:01', 113, 99999999);
INSERT INTO `dsb`.`transaction` (`transactionid`, `message`, `transaction_amount`, `transaction_timestamp`, credit_account_accountid, debit_account_accountid) VALUES ('99999984', 'DSB Startkapitaal', 20000, '2020-01-01 01:01:01', 114, 99999999);
INSERT INTO `dsb`.`transaction` (`transactionid`, `message`, `transaction_amount`, `transaction_timestamp`, credit_account_accountid, debit_account_accountid) VALUES ('99999985', 'DSB Startkapitaal', 30000, '2020-01-01 01:01:01', 115, 99999999);
INSERT INTO `dsb`.`transaction` (`transactionid`, `message`, `transaction_amount`, `transaction_timestamp`, credit_account_accountid, debit_account_accountid) VALUES ('99999986', 'DSB Startkapitaal', 15000, '2020-01-01 01:01:01', 116, 99999999);
INSERT INTO `dsb`.`transaction` (`transactionid`, `message`, `transaction_amount`, `transaction_timestamp`, credit_account_accountid, debit_account_accountid) VALUES ('99999987', 'DSB Startkapitaal', 17000, '2020-01-01 01:01:01', 117, 99999999);
INSERT INTO `dsb`.`transaction` (`transactionid`, `message`, `transaction_amount`, `transaction_timestamp`, credit_account_accountid, debit_account_accountid) VALUES ('99999988', 'DSB Startkapitaal', 12000, '2020-01-01 01:01:01', 118, 99999999);
INSERT INTO `dsb`.`transaction` (`transactionid`, `message`, `transaction_amount`, `transaction_timestamp`, credit_account_accountid, debit_account_accountid) VALUES ('99999989', 'DSB Startkapitaal', 42000, '2020-01-01 01:01:01', 119, 99999999);
INSERT INTO `dsb`.`transaction` (`transactionid`, `message`, `transaction_amount`, `transaction_timestamp`, credit_account_accountid, debit_account_accountid) VALUES ('99999990', 'DSB Startkapitaal', 50000, '2020-01-01 01:01:01', 120, 99999999);
INSERT INTO `dsb`.`transaction` (`transactionid`, `message`, `transaction_amount`, `transaction_timestamp`, credit_account_accountid, debit_account_accountid) VALUES ('99999991', 'DSB Startkapitaal', 610000, '2020-01-01 01:01:01', 121, 99999999);


INSERT INTO `dsb`.`transaction` (`transactionid`, `message`, `transaction_amount`, `transaction_timestamp`, credit_account_accountid, debit_account_accountid) VALUES ('300', 'Huur', 100, '2020-01-01 01:01:01', 110, 111);
INSERT INTO `dsb`.`transaction` (`transactionid`, `message`, `transaction_amount`, `transaction_timestamp`, credit_account_accountid, debit_account_accountid) VALUES ('301', 'Etentje', 100, '2020-01-02 01:01:01', 110, 112);
INSERT INTO `dsb`.`transaction` (`transactionid`, `message`, `transaction_amount`, `transaction_timestamp`, credit_account_accountid, debit_account_accountid) VALUES ('302', 'Hondevoer', 100, '2020-01-03 01:01:01', 111, 113);
INSERT INTO `dsb`.`transaction` (`transactionid`, `message`, `transaction_amount`, `transaction_timestamp`, credit_account_accountid, debit_account_accountid) VALUES ('303', 'Ballonvaart', 100, '2020-01-04 01:01:01', 110, 114);
INSERT INTO `dsb`.`transaction` (`transactionid`, `message`, `transaction_amount`, `transaction_timestamp`, credit_account_accountid, debit_account_accountid) VALUES ('304', 'Verjaardag', 100, '2020-01-05 01:01:01', 111, 115);
INSERT INTO `dsb`.`transaction` (`transactionid`, `message`, `transaction_amount`, `transaction_timestamp`, credit_account_accountid, debit_account_accountid) VALUES ('305', 'Lunch', '12', '2020-01-05 01:01:01', 116, 118);
INSERT INTO `dsb`.`transaction` (`transactionid`, `message`, `transaction_amount`, `transaction_timestamp`, credit_account_accountid, debit_account_accountid) VALUES ('306', 'Vissen', '150', '2020-01-05 01:01:01', 117, 119);
INSERT INTO `dsb`.`transaction` (`transactionid`, `message`, `transaction_amount`, `transaction_timestamp`, credit_account_accountid, debit_account_accountid) VALUES ('307', 'Boekenclub', '1100', '2020-01-05 01:01:01', 119, 117);

INSERT INTO `dsb`.`token_payment_machine` (`tokenid`, `security_code`, `sme_account_accountid`) VALUES ('1', '25481', '113');
INSERT INTO `dsb`.`token_payment_machine` (`tokenid`, `security_code`, `sme_account_accountid`) VALUES ('2', '26847', '114');
INSERT INTO `dsb`.`token_payment_machine` (`tokenid`, `security_code`, `sme_account_accountid`) VALUES ('3', '0', '115');
INSERT INTO `dsb`.`token_payment_machine` (`tokenid`, `security_code`, `sme_account_accountid`) VALUES ('4', '53697', '116');
INSERT INTO `dsb`.`token_payment_machine` (`tokenid`, `security_code`, `sme_account_accountid`) VALUES ('5', '35875', '117');
INSERT INTO `dsb`.`token_payment_machine` (`tokenid`, `security_code`, `sme_account_accountid`) VALUES ('6', '0', '118');
INSERT INTO `dsb`.`token_payment_machine` (`tokenid`, `security_code`, `sme_account_accountid`) VALUES ('7', '88697', '119');
INSERT INTO `dsb`.`token_payment_machine` (`tokenid`, `security_code`, `sme_account_accountid`) VALUES ('8', '0', '120');


INSERT INTO dsb.account_holder_token VALUES (110,0,110,13)

