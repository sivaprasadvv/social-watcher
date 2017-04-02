CREATE TABLE `login` (
  `login_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) NOT NULL,
  `created_date` datetime NOT NULL,
  `isEnabled` tinyint(1) NOT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`login_id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1

GO

CREATE TABLE `role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) NOT NULL,
  `created_date` datetime NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `role_name` varchar(255) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `role_name` (`role_name`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1

GO

CREATE TABLE `login_role` (
  `login_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  KEY `FK97D4838C10F1E606` (`login_id`),
  KEY `FK97D4838C990F2F8E` (`role_id`),
  CONSTRAINT `FK97D4838C990F2F8E` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`),
  CONSTRAINT `FK97D4838C10F1E606` FOREIGN KEY (`login_id`) REFERENCES `login` (`login_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1

GO

CREATE TABLE `address` (
  `address_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `created_by` bigint(20) NOT NULL,
  `created_date` datetime NOT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `street1` varchar(255) DEFAULT NULL,
  `street2` varchar(255) DEFAULT NULL,
  `zip_postal` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`address_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1

GO

CREATE TABLE `user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) NOT NULL,
  `created_date` datetime NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `isAssigned` tinyint(1) DEFAULT NULL,
  `isEnabled` tinyint(1) NOT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) NOT NULL,
  `login` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `FK36EBCB9E86F7BE` (`login`),
  CONSTRAINT `FK36EBCB9E86F7BE` FOREIGN KEY (`login`) REFERENCES `login` (`login_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1

GO

CREATE TABLE `client` (
  `client_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `allowed_query_words_count` bigint(20) DEFAULT NULL,
  `client_name` varchar(255) NOT NULL,
  `created_by` bigint(20) NOT NULL,
  `created_date` datetime NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `isAssigned` tinyint(1) DEFAULT NULL,
  `isEnabled` tinyint(1) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `address` bigint(20) NOT NULL,
  `login` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`client_id`),
  KEY `FKAF12F3CB6707C5D4` (`address`),
  KEY `FKAF12F3CB9E86F7BE` (`login`),
  CONSTRAINT `FKAF12F3CB9E86F7BE` FOREIGN KEY (`login`) REFERENCES `login` (`login_id`),
  CONSTRAINT `FKAF12F3CB6707C5D4` FOREIGN KEY (`address`) REFERENCES `address` (`address_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1

GO

CREATE TABLE `client_user` (
  `client_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`client_id`,`user_id`),
  KEY `FKF5A657BF3E39F36E` (`user_id`),
  KEY `FKF5A657BFF2BCDE6E` (`client_id`),
  CONSTRAINT `FKF5A657BFF2BCDE6E` FOREIGN KEY (`client_id`) REFERENCES `client` (`client_id`),
  CONSTRAINT `FKF5A657BF3E39F36E` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1

GO

CREATE TABLE `partner` (
  `partner_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `allowed_clients_count` bigint(20) DEFAULT NULL,
  `created_by` bigint(20) NOT NULL,
  `created_date` datetime NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `isEnabled` tinyint(1) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `partner_name` varchar(255) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `address` bigint(20) NOT NULL,
  `login` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`partner_id`),
  KEY `FKD0BCDCC86707C5D4` (`address`),
  KEY `FKD0BCDCC89E86F7BE` (`login`),
  CONSTRAINT `FKD0BCDCC89E86F7BE` FOREIGN KEY (`login`) REFERENCES `login` (`login_id`),
  CONSTRAINT `FKD0BCDCC86707C5D4` FOREIGN KEY (`address`) REFERENCES `address` (`address_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1

GO

CREATE TABLE `partner_client` (
  `partner_id` bigint(20) NOT NULL,
  `client_id` bigint(20) NOT NULL,
  PRIMARY KEY (`partner_id`,`client_id`),
  KEY `FK1EC1A322F2BCDE6E` (`client_id`),
  KEY `FK1EC1A322CAB76AA6` (`partner_id`),
  CONSTRAINT `FK1EC1A322CAB76AA6` FOREIGN KEY (`partner_id`) REFERENCES `partner` (`partner_id`),
  CONSTRAINT `FK1EC1A322F2BCDE6E` FOREIGN KEY (`client_id`) REFERENCES `client` (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1

GO

CREATE TABLE `socialmedia` (
  `socialmedia_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) NOT NULL,
  `created_date` datetime NOT NULL,
  `isEnabled` tinyint(1) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`socialmedia_id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1

GO

CREATE TABLE `query` (
  `query_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) NOT NULL,
  `created_date` datetime NOT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `query_text` varchar(255) NOT NULL,
  `client_id` bigint(20) DEFAULT NULL,
  `socialmedia_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`query_id`),
  KEY `FK66F18C8F2BCDE6E` (`client_id`),
  KEY `FK66F18C870165E26` (`socialmedia_id`),
  CONSTRAINT `FK66F18C870165E26` FOREIGN KEY (`socialmedia_id`) REFERENCES `socialmedia` (`socialmedia_id`),
  CONSTRAINT `FK66F18C8F2BCDE6E` FOREIGN KEY (`client_id`) REFERENCES `client` (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1

GO

CREATE TABLE `query_result` (
  `query_result_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `comment` text,
  `created_by` bigint(20) NOT NULL,
  `created_date` datetime NOT NULL,
  `description` text,
  `isDeleted` varchar(255) DEFAULT NULL,
  `isReviewed` tinyint(1) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `place` varchar(255) DEFAULT NULL,
  `profile_image_url` varchar(255) DEFAULT NULL,
  `RANK` bigint(20) DEFAULT NULL,
  `reviewed_by` bigint(20) DEFAULT NULL,
  `reviewed_date` datetime DEFAULT NULL,
  `source_created_date` datetime NOT NULL,
  `source_id` varchar(255) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `url` text,
  `query_id` bigint(20) NOT NULL,
  PRIMARY KEY (`query_result_id`),
  UNIQUE KEY `source_id` (`source_id`),
  KEY `FK5C8EBB74532075A6` (`query_id`),
  CONSTRAINT `FK5C8EBB74532075A6` FOREIGN KEY (`query_id`) REFERENCES `query` (`query_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1

GO

CREATE TABLE `application_properties` (
  `application_property_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) NOT NULL,
  `created_date` datetime NOT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`application_property_id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1

GO