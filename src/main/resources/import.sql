INSERT INTO dsb.sector VALUES (1, 'Bouw')
INSERT INTO dsb.sector VALUES (2, 'Cultuur, sport en recreatie')
INSERT INTO dsb.sector VALUES (3, 'Detailhandel')
INSERT INTO dsb.sector VALUES (4, 'Energie, water en milieu')
INSERT INTO dsb.sector VALUES (5, 'Financiële instellingen')
INSERT INTO dsb.sector VALUES (6, 'Gezondheid')
INSERT INTO dsb.sector VALUES (7, 'Groothandel')
INSERT INTO dsb.sector VALUES (8, 'Horeca')
INSERT INTO dsb.sector VALUES (9, 'ICT en media')
INSERT INTO dsb.sector VALUES (10, 'Industrie')
INSERT INTO dsb.sector VALUES (11, 'Land- en tuinbouw')
INSERT INTO dsb.sector VALUES (12, 'Logistiek')
INSERT INTO dsb.sector VALUES (13, 'Overig')
INSERT INTO dsb.sector VALUES (14, 'Zakelijke diensten')

INSERT INTO `dsb`.`address` (`addressid`, `city`, `house_number`, `street`) VALUES ('99999999', 'Wognum', '6', 'Dick Ketlaan');
INSERT INTO `dsb`.`user` (`userid`, `initials`, `password`, `surname`, `username`) VALUES ('99999999', 'DSB', 'dsbb', 'Bank', 'dsbb');
INSERT INTO `dsb`.`customer` (`social_security_no`, `userid`, `address_addressid`) VALUES ('000000', '99999999', '99999999');

INSERT INTO `dsb`.`role` (`roleid`, `role_name`) VALUES('1', 'Consumer')
INSERT INTO `dsb`.`role` (`roleid`, `role_name`) VALUES('2', 'Commercial')

INSERT INTO `dsb`.`user` (`userid`, `initials`, `password`, `surname`, `username`) VALUES ('999999999', 'L.', 'linda', 'Haest', 'linda' );
INSERT INTO `dsb`.`user` (`userid`, `initials`, `password`, `surname`, `username`) VALUES ('999999998', 'T.', 'thijs', 'Rodenburg', 'thijs' );

INSERT INTO `dsb`.`employee` (`userid`, `role_roleid`) VALUES ('999999999', '1' );
INSERT INTO `dsb`.`employee` (`userid`, `role_roleid`) VALUES ('999999998', '2' );

INSERT INTO `dsb`.`account` (`accountid`, `account_no`, `balance`) VALUES ('99999999', 'NL39DSBB0000000001', 0);

INSERT INTO `dsb`.`consumer_account` (`accountid`) VALUES ('99999999');
