DROP TABLE user_event if exists;

CREATE TABLE user_event (
  id int(11) NOT NULL auto_increment,
  user varchar(45) NOT NULL,
  event varchar(45) NOT NULL,
  PRIMARY KEY (id),
);


