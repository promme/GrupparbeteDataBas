CREATE DATABASE IF NOT EXISTS `Grupparbete`;
USE `Grupparbete`;

-- -----------------------------------------------------
-- Table `Grupparbete`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Grupparbete`.`User` (
  `User_Id` INT UNSIGNED NOT NULL,
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
  `Name` VARCHAR(45) NULL,
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
  `Address` VARCHAR(45) NULL,
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