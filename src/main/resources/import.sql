INSERT INTO `dsb`.`address` (`addressid`, `city`, `house_number`, `street`) VALUES ('11', 'Amsterdam', '1', 'Straat');
INSERT INTO `dsb`.`customer` (`customerid`, `initials`, `password`, `social_security_no`, `surname`, `username`, `address_addressid`) VALUES ('11', 'D', 'dennis', '123456', 'Kramer', 'dennis', '11');

INSERT INTO `dsb`.`account` (`dtype`, `accountid`, `account_no`, `balance`) VALUES ('ConsumerAccount', '20', 'NL40DSBB0123456789', '200');
INSERT INTO `dsb`.`account` (`dtype`, `accountid`, `account_no`, `balance`) VALUES ('ConsumerAccount', '21', 'NL30DSBB0123456789', '300');
INSERT INTO `dsb`.`account` (`dtype`, `accountid`, `account_no`, `balance`) VALUES ('ConsumerAccount', '22', 'NL20DSBB0123456789', '400');
INSERT INTO `dsb`.`account` (`dtype`, `accountid`, `account_no`, `balance`) VALUES ('SMEAccount', '23', 'NL50DSBB0123456789', '10000');
INSERT INTO `dsb`.`account` (`dtype`, `accountid`, `account_no`, `balance`) VALUES ('SMEAccount', '24', 'NL60DSBB0123456789', '20000');
INSERT INTO `dsb`.`account` (`dtype`, `accountid`, `account_no`, `balance`) VALUES ('SMEAccount', '25', 'NL70DSBB0123456789', '15000');

INSERT INTO `dsb`.`customer_accounts` (`holders_customerid`, `accounts_accountid`) VALUES (11, 20);
INSERT INTO `dsb`.`customer_accounts` (`holders_customerid`, `accounts_accountid`) VALUES (11, 21);
INSERT INTO `dsb`.`customer_accounts` (`holders_customerid`, `accounts_accountid`) VALUES (11, 22);
INSERT INTO `dsb`.`customer_accounts` (`holders_customerid`, `accounts_accountid`) VALUES (11, 23);
INSERT INTO `dsb`.`customer_accounts` (`holders_customerid`, `accounts_accountid`) VALUES (11, 24);
INSERT INTO `dsb`.`customer_accounts` (`holders_customerid`, `accounts_accountid`) VALUES (11, 25);

INSERT INTO `dsb`.`transaction_dummy` (`transactionid`, `message`, `transaction_amount`, `transaction_timestamp`, transaction_account_credit_accountid, transaction_account_debet_accountid) VALUES ('300', 'tr1', 100, '2020-01-01 01:01:01', 20, 21);
INSERT INTO `dsb`.`transaction_dummy` (`transactionid`, `message`, `transaction_amount`, `transaction_timestamp`, transaction_account_credit_accountid, transaction_account_debet_accountid) VALUES ('301', 'tr2', 100, '2020-01-02 01:01:01', 20, 22);
INSERT INTO `dsb`.`transaction_dummy` (`transactionid`, `message`, `transaction_amount`, `transaction_timestamp`, transaction_account_credit_accountid, transaction_account_debet_accountid) VALUES ('302', 'tr3', 100, '2020-01-03 01:01:01', 20, 23);
INSERT INTO `dsb`.`transaction_dummy` (`transactionid`, `message`, `transaction_amount`, `transaction_timestamp`, transaction_account_credit_accountid, transaction_account_debet_accountid) VALUES ('303', 'tr4', 100, '2020-01-04 01:01:01', 20, 24);
INSERT INTO `dsb`.`transaction_dummy` (`transactionid`, `message`, `transaction_amount`, `transaction_timestamp`, transaction_account_credit_accountid, transaction_account_debet_accountid) VALUES ('304', 'tr5', 100, '2020-01-05 01:01:01', 20, 25);
INSERT INTO `dsb`.`transaction_dummy` (`transactionid`, `message`, `transaction_amount`, `transaction_timestamp`, transaction_account_credit_accountid, transaction_account_debet_accountid) VALUES ('305', 'tr6', 100, '2020-01-06 01:01:01', 21, 20);
INSERT INTO `dsb`.`transaction_dummy` (`transactionid`, `message`, `transaction_amount`, `transaction_timestamp`, transaction_account_credit_accountid, transaction_account_debet_accountid) VALUES ('306', 'tr7', 100, '2020-01-07 01:01:01', 22, 20);
INSERT INTO `dsb`.`transaction_dummy` (`transactionid`, `message`, `transaction_amount`, `transaction_timestamp`, transaction_account_credit_accountid, transaction_account_debet_accountid) VALUES ('307', 'tr8', 100, '2020-01-08 01:01:01', 22, 20);
INSERT INTO `dsb`.`transaction_dummy` (`transactionid`, `message`, `transaction_amount`, `transaction_timestamp`, transaction_account_credit_accountid, transaction_account_debet_accountid) VALUES ('308', 'tr9', 100, '2020-01-09 01:01:01', 23, 20);
INSERT INTO `dsb`.`transaction_dummy` (`transactionid`, `message`, `transaction_amount`, `transaction_timestamp`, transaction_account_credit_accountid, transaction_account_debet_accountid) VALUES ('309', 'tr10', 100, '2020-01-10 01:01:01', 24, 20);
INSERT INTO `dsb`.`transaction_dummy` (`transactionid`, `message`, `transaction_amount`, `transaction_timestamp`, transaction_account_credit_accountid, transaction_account_debet_accountid) VALUES ('310', 'tr11', 100, '2020-01-11 01:01:01', 25, 21);
INSERT INTO `dsb`.`transaction_dummy` (`transactionid`, `message`, `transaction_amount`, `transaction_timestamp`, transaction_account_credit_accountid, transaction_account_debet_accountid) VALUES ('311', 'tr12', 100, '2020-01-12 01:01:01', 20, 21);
INSERT INTO `dsb`.`transaction_dummy` (`transactionid`, `message`, `transaction_amount`, `transaction_timestamp`, transaction_account_credit_accountid, transaction_account_debet_accountid) VALUES ('312', 'tr13', 100, '2020-01-13 01:01:01', 20, 22);
INSERT INTO `dsb`.`transaction_dummy` (`transactionid`, `message`, `transaction_amount`, `transaction_timestamp`, transaction_account_credit_accountid, transaction_account_debet_accountid) VALUES ('313', 'tr14', 100, '2020-01-14 01:01:01', 20, 23);
INSERT INTO `dsb`.`transaction_dummy` (`transactionid`, `message`, `transaction_amount`, `transaction_timestamp`, transaction_account_credit_accountid, transaction_account_debet_accountid) VALUES ('314', 'tr15', 100, '2020-01-15 01:01:01', 20, 24);
INSERT INTO `dsb`.`transaction_dummy` (`transactionid`, `message`, `transaction_amount`, `transaction_timestamp`, transaction_account_credit_accountid, transaction_account_debet_accountid) VALUES ('315', 'tr16', 100, '2020-01-16 01:01:01', 20, 25);




