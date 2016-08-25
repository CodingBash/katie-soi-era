DROP TABLE event if exists;

CREATE TABLE event (
  eventid varchar(50) NOT NULL,
  date decimal(20,0) NOT NULL,
  classid varchar(45) NOT NULL,
  id int(11) NOT NULL auto_increment,
  PRIMARY KEY (id)
);
