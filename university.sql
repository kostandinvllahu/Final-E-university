CREATE SCHEMA `university` ;

CREATE TABLE `university`.`system_settings` (
  `system_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `system_title` VARCHAR(100) NOT NULL,
  `system_email` VARCHAR(45) NOT NULL,
  `system_email_password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`system_id`),
  UNIQUE INDEX `system_id_UNIQUE` (`system_id` ASC) VISIBLE);

INSERT INTO `university`.`system_settings` (`system_id`, `system_title`, `system_email`, `system_email_password`) VALUES ('1', 'University Application', 'unniapp21@gmail.com', 'UNYT2021');

CREATE TABLE `university`.`user` (
  `user_system_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `national_id` VARCHAR(45) NULL,
  `name` VARCHAR(45) NULL,
  `surname` VARCHAR(45) NULL,
  `birthday` VARCHAR(45) NULL,
  `gender` VARCHAR(45) NULL,
  `address` VARCHAR(256) NULL,
  `profile_image_path` VARCHAR(256) NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `active` INT NULL,
  PRIMARY KEY (`user_system_id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE);

  INSERT INTO `university`.`user` (`user_system_id`, `national_id`, `name`, `surname`, `birthday`, `gender`, `address`, `username`, `password`, `active`) VALUES ('1', 'unknown', 'unknown', 'unknown', '0', 'other', 'unknown', 'admin', 'admin', '1');

CREATE TABLE `university`.`user_phone_number` (
  `phone_number` VARCHAR(45) NOT NULL,
  `user_system_id` INT NOT NULL,
  PRIMARY KEY (`phone_number`));

CREATE TABLE `university`.`user_email` (
  `email` VARCHAR(45) NOT NULL,
  `user_system_id` INT NOT NULL,
  PRIMARY KEY (`email`));

CREATE TABLE `university`.`admin` (
  `user_system_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`user_system_id`),
  UNIQUE INDEX `user_system_id_UNIQUE` (`user_system_id` ASC) VISIBLE);

INSERT INTO `university`.`admin` (`user_system_id`) VALUES ('1');

CREATE TABLE `university`.`student` (
  `user_system_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `minor_subject` VARCHAR(255) NOT NULL,
  `major_subject` VARCHAR(255) NOT NULL,
  `minor_subject_gratuated` INT NOT NULL,
  `major_subject_gratuated` INT NOT NULL,
  `current_semester_number` INT NOT NULL,
  PRIMARY KEY (`user_system_id`),
  UNIQUE INDEX `user_system_id_UNIQUE` (`user_system_id` ASC) VISIBLE);

CREATE TABLE `university`.`schedule` (
  `timetable_id` INT NOT NULL,
  `day_of_week` VARCHAR(45) NOT NULL,
  `time_period` VARCHAR(45) NOT NULL,
  `location` VARCHAR(256) NOT NULL,
  `course_id` INT NOT NULL,
  PRIMARY KEY (`timetable_id`));

CREATE TABLE `university`.`timetable` (
  `timetable_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `start_date` VARCHAR(45) NOT NULL,
  `end_date` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`timetable_id`),
  UNIQUE INDEX `timetable_id_UNIQUE` (`timetable_id` ASC) VISIBLE);

INSERT INTO `university`.`timetable` (`timetable_id`, `title`, `start_date`, `end_date`) VALUES ('1', 'default', '0', '0');

CREATE TABLE `university`.`subject` (
  `subject_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `title`  VARCHAR(45) NOT NULL,
  `minor_subject` VARCHAR(45) NULL,
  UNIQUE INDEX `title_UNIQUE` (`title` ASC) VISIBLE,
  PRIMARY KEY (`subject_id`),
  UNIQUE INDEX `subject_id_UNIQUE` (`subject_id` ASC) VISIBLE);

CREATE TABLE `university`.`course` (
  `course_id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(256) NULL,
  `description` VARCHAR(256) NULL,
  `subject_id` INT NOT NULL,
  `number_of_credits` INT NULL,
  `semester_number` INT NULL,
  PRIMARY KEY (`course_id`),
  UNIQUE INDEX `course_id_UNIQUE` (`course_id` ASC) VISIBLE);

CREATE TABLE `university`.`student_course` (
  `user_system_id` INT NOT NULL,
  `course_id` INT NOT NULL);

CREATE TABLE `university`.`rating` (
  `rating_id` INT NOT NULL AUTO_INCREMENT,
  `student_id` INT NOT NULL,
  `course_id` INT NULL,
  `date` VARCHAR(45) NULL,
  `comment` VARCHAR(256) NULL,
  `value` INT NULL,
  PRIMARY KEY (`rating_id`, `student_id`),
  UNIQUE INDEX `rating_id_UNIQUE` (`rating_id` ASC) VISIBLE);

CREATE TABLE `university`.`announcement` (
  `announcement_id` INT NOT NULL AUTO_INCREMENT,
  `date` VARCHAR(45) NOT NULL,
  `title` VARCHAR(256) NULL,
  `description` VARCHAR(256) NULL,
  `course_id` INT NOT NULL,
  PRIMARY KEY (`announcement_id`),
  UNIQUE INDEX `announcement_id_UNIQUE` (`announcement_id` ASC) VISIBLE);

CREATE TABLE `university`.`connected_students` (
  `user_system_id` INT NOT NULL,
  `connected_student_user_system_id` INT NOT NULL);
