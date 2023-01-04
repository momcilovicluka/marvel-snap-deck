-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema marvelSnapDeck
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema marvelSnapDeck
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `marvelSnapDeck` DEFAULT CHARACTER SET utf8 ;
-- -----------------------------------------------------
-- Schema is2
-- -----------------------------------------------------
USE `marvelSnapDeck` ;

-- -----------------------------------------------------
-- Table `marvelSnapDeck`.`Tip`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `marvelSnapDeck`.`Tip` (
  `idTip` INT NOT NULL AUTO_INCREMENT,
  `tip` VARCHAR(50) NULL,
  PRIMARY KEY (`idTip`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `marvelSnapDeck`.`Karta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `marvelSnapDeck`.`Karta` (
  `idKarta` INT NOT NULL AUTO_INCREMENT,
  `naziv` VARCHAR(50) NULL,
  `opis` VARCHAR(200) NULL,
  `Tip_idTip` INT NOT NULL,
  `slika` BLOB NULL,
  PRIMARY KEY (`idKarta`),
  INDEX `fk_Karta_Tip1_idx` (`Tip_idTip` ASC) VISIBLE,
  CONSTRAINT `fk_Karta_Tip1`
    FOREIGN KEY (`Tip_idTip`)
    REFERENCES `marvelSnapDeck`.`Tip` (`idTip`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `marvelSnapDeck`.`Kategorija`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `marvelSnapDeck`.`Kategorija` (
  `idKategorija` INT NOT NULL AUTO_INCREMENT,
  `naziv` VARCHAR(50) NULL,
  PRIMARY KEY (`idKategorija`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `marvelSnapDeck`.`Deck`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `marvelSnapDeck`.`Deck` (
  `idDeck` INT NOT NULL AUTO_INCREMENT,
  `Kategorija_idKategorija` INT NOT NULL,
  `Opis` VARCHAR(200) NULL,
  PRIMARY KEY (`idDeck`),
  INDEX `fk_Deck_Kategorija1_idx` (`Kategorija_idKategorija` ASC) VISIBLE,
  CONSTRAINT `fk_Deck_Kategorija1`
    FOREIGN KEY (`Kategorija_idKategorija`)
    REFERENCES `marvelSnapDeck`.`Kategorija` (`idKategorija`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `marvelSnapDeck`.`KartaDecka`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `marvelSnapDeck`.`KartaDecka` (
  `idKartaDecka` INT NOT NULL AUTO_INCREMENT,
  `Deck_idDeck` INT NOT NULL,
  `Karta_idKarta` INT NOT NULL,
  INDEX `fk_Deck_has_Karta_Karta1_idx` (`Karta_idKarta` ASC) VISIBLE,
  INDEX `fk_Deck_has_Karta_Deck_idx` (`Deck_idDeck` ASC) VISIBLE,
  PRIMARY KEY (`idKartaDecka`),
  CONSTRAINT `fk_Deck_has_Karta_Deck`
    FOREIGN KEY (`Deck_idDeck`)
    REFERENCES `marvelSnapDeck`.`Deck` (`idDeck`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Deck_has_Karta_Karta1`
    FOREIGN KEY (`Karta_idKarta`)
    REFERENCES `marvelSnapDeck`.`Karta` (`idKarta`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `marvelSnapDeck`.`UserRole`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `marvelSnapDeck`.`UserRole` (
  `idUserRole` INT NOT NULL AUTO_INCREMENT,
  `naziv` VARCHAR(50) NULL,
  PRIMARY KEY (`idUserRole`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `marvelSnapDeck`.`Korisnik`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `marvelSnapDeck`.`Korisnik` (
  `idKorisnik` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(50) NULL,
  `password` VARCHAR(50) NULL,
  `ime` VARCHAR(50) NULL,
  `prezime` VARCHAR(50) NULL,
  `UserRole_idUserRole` INT NOT NULL,
  PRIMARY KEY (`idKorisnik`),
  INDEX `fk_Korisnik_UserRole1_idx` (`UserRole_idUserRole` ASC) VISIBLE,
  CONSTRAINT `fk_Korisnik_UserRole1`
    FOREIGN KEY (`UserRole_idUserRole`)
    REFERENCES `marvelSnapDeck`.`UserRole` (`idUserRole`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `marvelSnapDeck`.`Omiljeni`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `marvelSnapDeck`.`Omiljeni` (
  `idOmiljeni` INT NOT NULL AUTO_INCREMENT,
  `Deck_idDeck` INT NOT NULL,
  `Korisnik_idKorisnik` INT NOT NULL,
  INDEX `fk_Deck_has_Korisnik_Korisnik1_idx` (`Korisnik_idKorisnik` ASC) VISIBLE,
  INDEX `fk_Deck_has_Korisnik_Deck1_idx` (`Deck_idDeck` ASC) VISIBLE,
  PRIMARY KEY (`idOmiljeni`),
  CONSTRAINT `fk_Deck_has_Korisnik_Deck1`
    FOREIGN KEY (`Deck_idDeck`)
    REFERENCES `marvelSnapDeck`.`Deck` (`idDeck`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Deck_has_Korisnik_Korisnik1`
    FOREIGN KEY (`Korisnik_idKorisnik`)
    REFERENCES `marvelSnapDeck`.`Korisnik` (`idKorisnik`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `marvelSnapDeck`.`Komentar`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `marvelSnapDeck`.`Komentar` (
  `komentarId` INT NOT NULL AUTO_INCREMENT,
  `Korisnik_idKorisnik` INT NOT NULL,
  `Deck_idDeck` INT NOT NULL,
  INDEX `fk_Korisnik_has_Deck_Deck1_idx` (`Deck_idDeck` ASC) VISIBLE,
  INDEX `fk_Korisnik_has_Deck_Korisnik1_idx` (`Korisnik_idKorisnik` ASC) VISIBLE,
  PRIMARY KEY (`komentarId`),
  CONSTRAINT `fk_Korisnik_has_Deck_Korisnik1`
    FOREIGN KEY (`Korisnik_idKorisnik`)
    REFERENCES `marvelSnapDeck`.`Korisnik` (`idKorisnik`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Korisnik_has_Deck_Deck1`
    FOREIGN KEY (`Deck_idDeck`)
    REFERENCES `marvelSnapDeck`.`Deck` (`idDeck`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `marvelSnapDeck`.`Prijatelji`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `marvelSnapDeck`.`Prijatelji` (
  `idPrijatelji` INT NOT NULL AUTO_INCREMENT,
  `Korisnik_idKorisnik1` INT NOT NULL,
  `Korisnik_idKorisnik2` INT NOT NULL,
  INDEX `fk_Korisnik_has_Korisnik_Korisnik2_idx` (`Korisnik_idKorisnik2` ASC) VISIBLE,
  INDEX `fk_Korisnik_has_Korisnik_Korisnik1_idx` (`Korisnik_idKorisnik1` ASC) VISIBLE,
  PRIMARY KEY (`idPrijatelji`),
  CONSTRAINT `fk_Korisnik_has_Korisnik_Korisnik1`
    FOREIGN KEY (`Korisnik_idKorisnik1`)
    REFERENCES `marvelSnapDeck`.`Korisnik` (`idKorisnik`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Korisnik_has_Korisnik_Korisnik2`
    FOREIGN KEY (`Korisnik_idKorisnik2`)
    REFERENCES `marvelSnapDeck`.`Korisnik` (`idKorisnik`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `marvelSnapDeck`.`Poruka`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `marvelSnapDeck`.`Poruka` (
  `idPoruka` INT NOT NULL AUTO_INCREMENT,
  `Prijatelji_idPrijatelji` INT NOT NULL,
  `Prijatelji_idPrijatelji1` INT NOT NULL,
  `poruka` VARCHAR(200) NULL,
  INDEX `fk_Prijatelji_has_Prijatelji_Prijatelji2_idx` (`Prijatelji_idPrijatelji1` ASC) VISIBLE,
  INDEX `fk_Prijatelji_has_Prijatelji_Prijatelji1_idx` (`Prijatelji_idPrijatelji` ASC) VISIBLE,
  PRIMARY KEY (`idPoruka`),
  CONSTRAINT `fk_Prijatelji_has_Prijatelji_Prijatelji1`
    FOREIGN KEY (`Prijatelji_idPrijatelji`)
    REFERENCES `marvelSnapDeck`.`Prijatelji` (`idPrijatelji`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Prijatelji_has_Prijatelji_Prijatelji2`
    FOREIGN KEY (`Prijatelji_idPrijatelji1`)
    REFERENCES `marvelSnapDeck`.`Prijatelji` (`idPrijatelji`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
