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