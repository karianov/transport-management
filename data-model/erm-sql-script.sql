-- -----------------------------------------------------
-- Schema transport_management_db
-- -----------------------------------------------------
CREATE DATABASE IF NOT EXISTS `transport_management_db` DEFAULT CHARACTER SET utf8 ;
USE `transport_management_db` ;

-- -----------------------------------------------------
-- Table `transport_management_db`.`country`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `transport_management_db`.`country` (
  `country_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(35) NOT NULL,
  PRIMARY KEY (`country_id`),
  UNIQUE INDEX `country_id_UNIQUE` (`country_id` ASC) VISIBLE),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `transport_management_db`.`department`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `transport_management_db`.`department` (
  `department_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(35) NOT NULL,
  `fk_country_id` INT NOT NULL,
  PRIMARY KEY (`department_id`, `fk_country_id`),
  UNIQUE INDEX `department_id_UNIQUE` (`department_id` ASC) VISIBLE,
  INDEX `fk_department_country_idx` (`fk_country_id` ASC) VISIBLE,
  UNIQUE INDEX `country_and_department_unique` (`name` ASC, `fk_country_id` ASC) VISIBLE,
  CONSTRAINT `fk_department_country`
    FOREIGN KEY (`fk_country_id`)
    REFERENCES `transport_management_db`.`country` (`country_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `transport_management_db`.`city`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `transport_management_db`.`city` (
  `city_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(35) NOT NULL,
  `fk_department_id` INT NOT NULL,
  PRIMARY KEY (`city_id`, `fk_department_id`),
  UNIQUE INDEX `city_id_UNIQUE` (`city_id` ASC) VISIBLE,
  INDEX `fk_city_department_idx` (`fk_department_id` ASC) VISIBLE,
  UNIQUE INDEX `department_and_city_unique` (`name` ASC, `fk_department_id` ASC) VISIBLE,
  CONSTRAINT `fk_city_department`
    FOREIGN KEY (`fk_department_id`)
    REFERENCES `transport_management_db`.`department` (`department_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `transport_management_db`.`identification_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `transport_management_db`.`identification_type` (
  `identification_type_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(35) NOT NULL,
  `abbreviation` VARCHAR(4) NOT NULL,
  `description` VARCHAR(255) NULL,
  PRIMARY KEY (`identification_type_id`),
  UNIQUE INDEX `identification_type_id_UNIQUE` (`identification_type_id` ASC) VISIBLE,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE,
  UNIQUE INDEX `abbreviation_UNIQUE` (`abbreviation` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `transport_management_db`.`person`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `transport_management_db`.`person` (
  `person_id` INT NOT NULL AUTO_INCREMENT,
  `identification_number` VARCHAR(35) NOT NULL,
  `full_name` VARCHAR(70) NOT NULL,
  `address` VARCHAR(70) NOT NULL,
  `phone_number` VARCHAR(30) NOT NULL,
  `fk_identification_type_id` INT NOT NULL,
  `fk_city_id` INT NOT NULL,
  PRIMARY KEY (`person_id`, `fk_identification_type_id`, `fk_city_id`),
  UNIQUE INDEX `person_id_UNIQUE` (`person_id` ASC) VISIBLE,
  UNIQUE INDEX `identificationnumber_and_identificationtype_unique` (`identification_number` ASC, `fk_identification_type_id` ASC) VISIBLE,
  INDEX `fk_person_city_idx` (`fk_city_id` ASC) VISIBLE,
  INDEX `fk_person_identification_type_idx` (`fk_identification_type_id` ASC) VISIBLE,
  CONSTRAINT `fk_person_city`
    FOREIGN KEY (`fk_city_id`)
    REFERENCES `transport_management_db`.`city` (`city_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_person_identification_type`
    FOREIGN KEY (`fk_identification_type_id`)
    REFERENCES `transport_management_db`.`identification_type` (`identification_type_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `transport_management_db`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `transport_management_db`.`user` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(35) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `fk_person_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `fk_person_id`),
  UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC) VISIBLE,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE,
  INDEX `fk_user_person1_idx` (`fk_person_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_person`
    FOREIGN KEY (`fk_person_id`)
    REFERENCES `transport_management_db`.`person` (`person_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `transport_management_db`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `transport_management_db`.`role` (
  `role_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(35) NOT NULL,
  `abbreviation` VARCHAR(4) NOT NULL,
  `description` VARCHAR(255) NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE INDEX `role_id_UNIQUE` (`role_id` ASC) VISIBLE,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE,
  UNIQUE INDEX `abbreviation_UNIQUE` (`abbreviation` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `transport_management_db`.`authority`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `transport_management_db`.`authority` (
  `authority_id` INT NOT NULL AUTO_INCREMENT,
  `fk_user_id` INT NOT NULL,
  `fk_role_id` INT NOT NULL,
  PRIMARY KEY (`authority_id`, `fk_user_id`, `fk_role_id`),
  INDEX `fk_role_has_user_id` (`fk_role_id` ASC) VISIBLE,
  INDEX `fk_user_has_role_id` (`fk_user_id` ASC) VISIBLE,
  UNIQUE INDEX `authority_id_UNIQUE` (`authority_id` ASC) VISIBLE,
  UNIQUE INDEX `user_and_role_unique` (`fk_user_id` ASC, `fk_role_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_has_role_id`
    FOREIGN KEY (`fk_user_id`)
    REFERENCES `transport_management_db`.`user` (`user_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_role_has_user_id`
    FOREIGN KEY (`fk_role_id`)
    REFERENCES `transport_management_db`.`role` (`role_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `transport_management_db`.`company`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `transport_management_db`.`company` (
  `company_id` INT NOT NULL AUTO_INCREMENT,
  `identification_number` VARCHAR(30) NOT NULL,
  `full_name` VARCHAR(70) NOT NULL,
  `address` VARCHAR(70) NOT NULL,
  `phone_number` VARCHAR(30) NOT NULL,
  `fk_identification_type_id` INT NOT NULL,
  `fk_city_id` INT NOT NULL,
  `fk_legal_representative` INT NOT NULL,
  PRIMARY KEY (`company_id`, `fk_identification_type_id`, `fk_city_id`, `fk_legal_representative`),
  UNIQUE INDEX `company_id_UNIQUE` (`company_id` ASC) VISIBLE,
  UNIQUE INDEX `identificationnumber_and_fullname_unique` (`identification_number` ASC, `full_name` ASC) VISIBLE,
  INDEX `fk_company_city_idx` (`fk_city_id` ASC) VISIBLE,
  INDEX `fk_company_person_idx` (`fk_legal_representative` ASC) VISIBLE,
  INDEX `fk_company_identification_type_idx` (`fk_identification_type_id` ASC) VISIBLE,
  CONSTRAINT `fk_company_city`
    FOREIGN KEY (`fk_city_id`)
    REFERENCES `transport_management_db`.`city` (`city_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_company_person`
    FOREIGN KEY (`fk_legal_representative`)
    REFERENCES `transport_management_db`.`person` (`person_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_company_identification_type`
    FOREIGN KEY (`fk_identification_type_id`)
    REFERENCES `transport_management_db`.`identification_type` (`identification_type_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `transport_management_db`.`brand`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `transport_management_db`.`brand` (
  `brand_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(35) NOT NULL,
  PRIMARY KEY (`brand_id`),
  UNIQUE INDEX `brand_id_UNIQUE` (`brand_id` ASC) VISIBLE,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `transport_management_db`.`product_line`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `transport_management_db`.`product_line` (
  `product_line_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(35) NOT NULL,
  `fk_brand_id` INT NOT NULL,
  PRIMARY KEY (`product_line_id`, `fk_brand_id`),
  UNIQUE INDEX `product_line_id_UNIQUE` (`product_line_id` ASC) VISIBLE,
  INDEX `fk_product_line_brand_idx` (`fk_brand_id` ASC) VISIBLE,
  UNIQUE INDEX `productline_and_brand_unique` (`name` ASC, `fk_brand_id` ASC) VISIBLE,
  CONSTRAINT `fk_product_line_brand`
    FOREIGN KEY (`fk_brand_id`)
    REFERENCES `transport_management_db`.`brand` (`brand_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `transport_management_db`.`vehicle`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `transport_management_db`.`vehicle` (
  `vehicle_id` INT NOT NULL AUTO_INCREMENT,
  `plate` VARCHAR(10) NOT NULL,
  `engine` VARCHAR(35) NOT NULL,
  `chassis` VARCHAR(35) NOT NULL,
  `model` VARCHAR(4) NOT NULL,
  `registration_date` DATE NOT NULL,
  `seated_passengers` INT NOT NULL,
  `standing_passengers` INT NOT NULL,
  `dry_weight` VARCHAR(10) NOT NULL,
  `gross_weight` VARCHAR(10) NOT NULL,
  `doors` INT NOT NULL,
  `fk_product_line_id` INT NOT NULL,
  PRIMARY KEY (`vehicle_id`, `fk_product_line_id`),
  UNIQUE INDEX `vehicle_id_UNIQUE` (`vehicle_id` ASC) VISIBLE,
  UNIQUE INDEX `plate_UNIQUE` (`plate` ASC) VISIBLE,
  INDEX `fk_vehicle_product_line_idx` (`fk_product_line_id` ASC) VISIBLE,
  CONSTRAINT `fk_vehicle_product_line`
    FOREIGN KEY (`fk_product_line_id`)
    REFERENCES `transport_management_db`.`product_line` (`product_line_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `transport_management_db`.`company-vehicle`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `transport_management_db`.`company-vehicle` (
  `company-vehicle_id` INT NOT NULL AUTO_INCREMENT,
  `fk_company_id` INT NOT NULL,
  `fk_vehicle_id` INT NOT NULL,
  PRIMARY KEY (`company-vehicle_id`, `fk_company_id`, `fk_vehicle_id`),
  INDEX `fk_company_has_vehicle_vehicle_idx` (`fk_vehicle_id` ASC) VISIBLE,
  INDEX `fk_company_has_vehicle_company_idx` (`fk_company_id` ASC) VISIBLE,
  UNIQUE INDEX `id_company-vehicle_UNIQUE` (`company-vehicle_id` ASC) VISIBLE,
  UNIQUE INDEX `company_and_vehicle_unique` (`fk_company_id` ASC, `fk_vehicle_id` ASC) VISIBLE,
  CONSTRAINT `fk_company_has_vehicle_company`
    FOREIGN KEY (`fk_company_id`)
    REFERENCES `transport_management_db`.`company` (`company_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_company_has_vehicle_vehicle`
    FOREIGN KEY (`fk_vehicle_id`)
    REFERENCES `transport_management_db`.`vehicle` (`vehicle_id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `transport_management_db`.`vehicle-person`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `transport_management_db`.`vehicle-person` (
  `vehicle-person_id` INT NOT NULL AUTO_INCREMENT,
  `fk_person_id` INT NOT NULL,
  `fk_vehicle_id` INT NOT NULL,
  PRIMARY KEY (`vehicle-person_id`, `fk_person_id`, `fk_vehicle_id`),
  INDEX `fk_vehicle_has_person_person_idx` (`fk_person_id` ASC) VISIBLE,
  INDEX `fk_vehicle_has_person_vehicle_idx` (`fk_vehicle_id` ASC) VISIBLE,
  UNIQUE INDEX `vehicle-person_id_UNIQUE` (`vehicle-person_id` ASC) VISIBLE,
  UNIQUE INDEX `vehicle_and_person_unique` (`fk_person_id` ASC, `fk_vehicle_id` ASC) VISIBLE,
  CONSTRAINT `fk_vehicle_has_person_vehicle`
    FOREIGN KEY (`fk_vehicle_id`)
    REFERENCES `transport_management_db`.`vehicle` (`vehicle_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_vehicle_has_person_person`
    FOREIGN KEY (`fk_person_id`)
    REFERENCES `transport_management_db`.`person` (`person_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
