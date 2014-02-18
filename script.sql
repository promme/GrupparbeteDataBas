CREATE DATABASE IF NOT EXISTS `Grupparbete`;
USE `Grupparbete`;

-- -----------------------------------------------------
-- Table `Grupparbete`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Grupparbete`.`User` (
  `User_Id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `Username` VARCHAR(45) NOT NULL,
  `Password` VARCHAR(45) NOT NULL,
  `IP_address` VARCHAR(45) NULL,
  `Registry_date` DATE NULL,
  PRIMARY KEY (`User_Id`),
  UNIQUE INDEX `user_Id_UNIQUE` (`User_Id` ASC),
  UNIQUE INDEX `Username_UNIQUE` (`Username` ASC));


-- -----------------------------------------------------
-- Table `Grupparbete`.`PersonData`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Grupparbete`.`PersonData` (
  `firstName` VARCHAR(45) NULL,
  `lastName` VARCHAR(45) NULL,
  `Email` VARCHAR(45) NULL,
  `Personnr` VARCHAR(13) NOT NULL,
  PRIMARY KEY (`Personnr`),
  UNIQUE INDEX `Personnr_UNIQUE` (`Personnr` ASC));


-- -----------------------------------------------------
-- Table `Grupparbete`.`UserPerson`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Grupparbete`.`UserPerson` (
  `User_Id` INT UNSIGNED NOT NULL,
  `Personnr` VARCHAR(13) NOT NULL,
  PRIMARY KEY (`User_Id`),
  INDEX `fk_UserPerson_User1_idx` (`User_Id` ASC),
  INDEX `fk_UserPerson_PersonData1_idx` (`Personnr` ASC),
  UNIQUE INDEX `User_Id_UNIQUE` (`User_Id` ASC),
  CONSTRAINT `fk_UserPerson_User1`
    FOREIGN KEY (`User_Id`)
    REFERENCES `Grupparbete`.`User` (`User_Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_UserPerson_PersonData1`
    FOREIGN KEY (`Personnr`)
    REFERENCES `Grupparbete`.`PersonData` (`Personnr`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `Grupparbete`.`Admin`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Grupparbete`.`Admin` (
  `Admin_Id` INT UNSIGNED NOT NULL,
  `Address` TEXT NULL,
  INDEX `fk_Admin_User1_idx` (`Admin_Id` ASC),
  PRIMARY KEY (`Admin_Id`),
  CONSTRAINT `fk_Admin_User1`
    FOREIGN KEY (`Admin_Id`)
    REFERENCES `Grupparbete`.`User` (`User_Id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE);

-- -----------------------------------------------------
-- Table `Grupparbete`.`AdminInfo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Grupparbete`.`AdminInfo` (
  `Mobile_nr` VARCHAR(45) NOT NULL,
  `Admin_Id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`Mobile_nr`),
  UNIQUE INDEX `Mobile_nr_UNIQUE` (`Mobile_nr` ASC),
  CONSTRAINT `fk_AdminInfo_Admin1`
    FOREIGN KEY (`Admin_Id`)
    REFERENCES `Grupparbete`.`Admin` (`Admin_Id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE);

-- -----------------------------------------------------
-- Table `Grupparbete`.`BannedUser`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Grupparbete`.`BannedUser` (
  `daysBanned` INT UNSIGNED NOT NULL,
  `User_Id` INT UNSIGNED NOT NULL,
  INDEX `fk_BannedUser_User1_idx` (`User_Id` ASC),
  PRIMARY KEY (`User_Id`),
  UNIQUE INDEX `User_Id_UNIQUE` (`User_Id` ASC),
  CONSTRAINT `fk_BannedUser_User1`
    FOREIGN KEY (`User_Id`)
    REFERENCES `Grupparbete`.`User` (`User_Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

insert into user (User_id,Username, Password,IP_address,registry_date) values (1,'user1',password('12345'),'1',CURDATE());
insert into persondata (firstName,lastName, Email,Personnr) values ('Namn','Namn','namn@namn.com','1111111111111');
insert into userperson (User_id,Personnr) values (1, '1111111111111');
insert into admin(admin_id,address) values(1,'Gatan 1');
insert into admininfo(Mobile_nr,Admin_id) values(123456789,1);

CREATE USER user1@localhost IDENTIFIED BY '12345';
Grant SELECT,INSERT on User TO user1@localhost; 
Grant SELECT,INSERT on Admin TO user1@localhost; 
Grant SELECT,INSERT on BannedUser TO user1@localhost;
Grant SELECT,INSERT on PersonData TO user1@localhost; 
Grant SELECT,INSERT on UserPerson TO user1@localhost; 
 
grant create user on *.* to user1@localhost with grant option;

insert into user (User_id,Username,Password,IP_address,registry_date) values (2,'user2',password('12345'),'1',CURDATE());
insert into userperson values (2, '1111111111111');
CREATE USER user2@localhost IDENTIFIED BY '12345';
Grant SELECT on User TO user2@localhost; 
Grant SELECT on Admin TO user2@localhost; 
Grant SELECT on BannedUser TO user2@localhost; 

insert into user (User_id,Username, Password,IP_address,registry_date) values (3,'user3',password('12345'),'1',CURDATE());
insert into persondata (firstName,lastName, Email,Personnr) values ('Namn3','Namn3','namn3@namn.com','1111111111113');
insert into userperson (User_id,Personnr) values (3, '1111111111113');
insert into admin(admin_id,address) values(3,'Gatan 3');
insert into admininfo(Mobile_nr,Admin_id) values(323456789,3);
insert into admininfo(Mobile_nr,Admin_id) values(333456789,3);
insert into admininfo(Mobile_nr,Admin_id) values(423456789,3);


CREATE USER user3@localhost IDENTIFIED BY '12345';
Grant SELECT,INSERT on User TO user3@localhost; 
Grant SELECT,INSERT on Admin TO user3@localhost;
Grant SELECT,INSERT on BannedUser TO user3@localhost; 
Grant SELECT,INSERT on PersonData TO user3@localhost; 
grant create user on *.* to user3@localhost with grant option;
Grant SELECT,INSERT on UserPerson TO user3@localhost;

insert into user (User_id,Username, Password,IP_address,registry_date) values (4,'user4','12345','1',CURDATE());
insert into persondata (firstName,lastName, Email,Personnr) values ('Namn4','Nman4','namn4@namn.com','1111111111114');
insert into userperson (User_id,Personnr) values (4, '1111111111114');
insert into BannedUser(User_id,daysBanned) values(4,100);

CREATE USER user4@localhost IDENTIFIED BY '12345';
Grant SELECT on User TO user4@localhost; 
Grant SELECT on Admin TO user4@localhost;
Grant SELECT on BannedUser TO user4@localhost; 
			