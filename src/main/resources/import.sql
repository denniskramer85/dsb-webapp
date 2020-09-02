INSERT INTO `dsb`.`address` (`addressid`, `city`, `house_number`, `street`) VALUES ('11', 'Amsterdam', '1', 'Straat');
INSERT INTO `dsb`.`customer` (`customerid`, `initials`, `password`, `social_security_no`, `surname`, `username`, `address_addressid`) VALUES ('11', 'D', 'dennis', '123456', 'Kramer', 'dennis', '11');

INSERT INTO `dsb`.`account` (`accountid`, `account_no`, `balance`) VALUES ('20', 'NL40DSBB1234567890', '200');
INSERT INTO `dsb`.`account` (`accountid`, `account_no`, `balance`) VALUES ('21', 'NL30DSBB1234567890', '300');
INSERT INTO `dsb`.`account` (`accountid`, `account_no`, `balance`) VALUES ('22', 'NL20DSBB1234567890', '400');
INSERT INTO `dsb`.`customer_accounts` (`holders_customerid`, `accounts_accountid`) VALUES ('11', '20')
INSERT INTO `dsb`.`customer_accounts` (`holders_customerid`, `accounts_accountid`) VALUES ('11', '21')
INSERT INTO `dsb`.`customer_accounts` (`holders_customerid`, `accounts_accountid`) VALUES ('11', '22')
