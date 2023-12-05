-- all-in-one
CREATE TABLE employee (
  id SERIAL PRIMARY KEY,
  first_name varchar(45) DEFAULT NULL,
  last_name varchar(45) DEFAULT NULL,
  email varchar(45) DEFAULT NULL
);

INSERT INTO employee VALUES 
	(1,'Leslie','Andrews','leslie@udemy.com'),
	(2,'Emma','Baumgarten','emma@udemy.com'),
	(3,'Avani','Gupta','avani@udemy.com'),
	(4,'Yuri','Petrov','yuri@udemy.com'),
	(5,'Juan','Vega','juan@udemy.com');