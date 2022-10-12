select * from test where idx=14;

delete from test 
where idx in (2,3,4);

set SQL_SAFE_UPDATES = 0;
ALTER TABLE test
ADD COLUMN idx INT NOT NULL AUTO_INCREMENT,
ADD PRIMARY KEY (idx);

CREATE TABLE `springboot`.`학생` (
  `idx` INT NOT NULL AUTO_INCREMENT,
  `학년` VARCHAR(45) NULL,
  `이름` VARCHAR(45) NULL,
  PRIMARY KEY (`idx`));
  
CREATE TABLE `springboot`.`수강` (
  `학생idx` INT NOT NULL,
  `idx` INT NOT NULL AUTO_INCREMENT,
  `과목명` VARCHAR(45) NULL,
  PRIMARY KEY (`idx`),
  INDEX `학생idx_idx` (`학생idx` ASC) VISIBLE,
  CONSTRAINT `학생idx`
    FOREIGN KEY (`학생idx`)
    REFERENCES `springboot`.`학생` (`idx`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

INSERT INTO `springboot`.`학생` (`idx`, `학년`, `이름`) VALUES ('1', '2', '이길동');
INSERT INTO `springboot`.`학생` (`idx`, `학년`, `이름`) VALUES ('2', '2', '박길동');
INSERT INTO `springboot`.`학생` (`idx`, `학년`, `이름`) VALUES ('3', '2', '최길동');

INSERT INTO `springboot`.`수강` (`학생idx`, `idx`, `과목명`) VALUES ('1', '1', '수학\\');
INSERT INTO `springboot`.`수강` (`학생idx`, `idx`, `과목명`) VALUES ('1', '2', '과학');
INSERT INTO `springboot`.`수강` (`학생idx`, `idx`, `과목명`) VALUES ('2', '3', '국어');
INSERT INTO `springboot`.`수강` (`학생idx`, `idx`, `과목명`) VALUES ('2', '4', '영어');
INSERT INTO `springboot`.`수강` (`학생idx`, `idx`, `과목명`) VALUES ('3', '5', '컴퓨터');
INSERT INTO `springboot`.`수강` (`학생idx`, `idx`, `과목명`) VALUES ('3', '6', '수학');


select * from 학생;
select * from 수강;
