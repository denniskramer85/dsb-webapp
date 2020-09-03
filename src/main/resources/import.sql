INSERT INTO `dsb`.`address` (`addressid`, `city`, `house_number`, `street`) VALUES ('11', 'Amsterdam', '1', 'Straat');
INSERT INTO `dsb`.`customer` (`customerid`, `initials`, `password`, `social_security_no`, `surname`, `username`, `address_addressid`) VALUES ('11', 'D', 'dennis', '123456', 'Kramer', 'dennis', '11');

INSERT INTO `dsb`.`account` (`accountid`, `account_no`, `balance`) VALUES ('110', 'NL40DSBB0123456789', '1000');
INSERT INTO `dsb`.`account` (`accountid`, `account_no`, `balance`) VALUES ('111', 'NL41DSBB0123456789', '1500');
INSERT INTO `dsb`.`account` (`accountid`, `account_no`, `balance`) VALUES ('112', 'NL42DSBB0123456789', '1250');
INSERT INTO `dsb`.`account` (`accountid`, `account_no`, `balance`) VALUES ('113', 'NL43DSBB0123456789', '10000');
INSERT INTO `dsb`.`account` (`accountid`, `account_no`, `balance`) VALUES ('114', 'NL44DSBB0123456789', '12500');
INSERT INTO `dsb`.`account` (`accountid`, `account_no`, `balance`) VALUES ('115', 'NL45DSBB0123456789', '15000');

INSERT INTO `dsb`.`consumer_account` (`accountid`) VALUES ('110');
INSERT INTO `dsb`.`consumer_account` (`accountid`) VALUES ('111');
INSERT INTO `dsb`.`consumer_account` (`accountid`) VALUES ('112');

INSERT INTO `dsb`.`smeaccount` (`accountid`) VALUES ('113');
INSERT INTO `dsb`.`smeaccount` (`accountid`) VALUES ('114');
INSERT INTO `dsb`.`smeaccount` (`accountid`) VALUES ('115');

INSERT INTO dsb.customer_accounts VALUES (11, 110);
INSERT INTO dsb.customer_accounts VALUES (11, 111);
INSERT INTO dsb.customer_accounts VALUES (11, 112);
INSERT INTO dsb.customer_accounts VALUES (11, 113);
INSERT INTO dsb.customer_accounts VALUES (11, 114);
INSERT INTO dsb.customer_accounts VALUES (11, 115);

INSERT INTO `dsb`.`transaction` (`transactionid`, `message`, `transaction_amount`, `transaction_timestamp`, transaction_account_credit_accountid, transaction_account_debet_accountid) VALUES ('300', 'tr1', 100, '2020-01-01 01:01:01', 110, 111);
INSERT INTO `dsb`.`transaction` (`transactionid`, `message`, `transaction_amount`, `transaction_timestamp`, transaction_account_credit_accountid, transaction_account_debet_accountid) VALUES ('301', 'tr2', 100, '2020-01-02 01:01:01', 110, 112);
INSERT INTO `dsb`.`transaction` (`transactionid`, `message`, `transaction_amount`, `transaction_timestamp`, transaction_account_credit_accountid, transaction_account_debet_accountid) VALUES ('302', 'tr3', 100, '2020-01-03 01:01:01', 111, 113);
INSERT INTO `dsb`.`transaction` (`transactionid`, `message`, `transaction_amount`, `transaction_timestamp`, transaction_account_credit_accountid, transaction_account_debet_accountid) VALUES ('303', 'tr4', 100, '2020-01-04 01:01:01', 110, 114);
INSERT INTO `dsb`.`transaction` (`transactionid`, `message`, `transaction_amount`, `transaction_timestamp`, transaction_account_credit_accountid, transaction_account_debet_accountid) VALUES ('304', 'tr5', 100, '2020-01-05 01:01:01', 111, 115);


