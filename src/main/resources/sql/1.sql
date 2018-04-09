SET MODE POSTGRESQL;

CREATE TABLE Person (
  id         INT PRIMARY KEY AUTO_INCREMENT,
  first_name VARCHAR(255) NOT NULL,
  last_name  VARCHAR(255),
  permission BOOLEAN         DEFAULT FALSE,
  dob        DATE,
  email      VARCHAR(255) NOT NULL,
  password   VARCHAR(255) NOT NULL,
  address    VARCHAR(255),
  telephone  VARCHAR(15)
);