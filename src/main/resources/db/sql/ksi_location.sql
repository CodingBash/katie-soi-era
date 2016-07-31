DROP TABLE location if exists;

CREATE TABLE location (
  id int(11) NOT NULL auto_increment,
  user varchar(45) NOT NULL,
  location varchar(45) NOT NULL,
  eventid varchar(45) DEFAULT NULL,
  PRIMARY KEY (id)
);
