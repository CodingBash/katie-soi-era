CREATE TABLE `user_event` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user` varchar(45) NOT NULL,
  `event` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_idx` (`user`),
  KEY `event_idx` (`event`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
