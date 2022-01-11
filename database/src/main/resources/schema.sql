DROP TABLE IF EXISTS employee;
CREATE TABLE employee(
                     id INT NOT NULL AUTO_INCREMENT,
                     first_name VARCHAR(60) NOT NULL,
                     last_name VARCHAR(60) NOT NULL,
                     department_id VARCHAR(60) NOT NULL,
                     job_title VARCHAR(60) NOT NULL,
                     gender VARCHAR(60) NOT NULL,
                     date_of_birth DATE,
                     PRIMARY KEY (id)
);
