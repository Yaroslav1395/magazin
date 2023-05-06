CREATE TABLE `coupons` (
    `id` int NOT NULL AUTO_INCREMENT,
    `name` varchar(100) NOT NULL,
    `percent` decimal(38,2) NOT NULL,
    `active_until` datetime(6) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `subscribes` (
    `id` int NOT NULL AUTO_INCREMENT,
    `email` varchar(100) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(100) NOT NULL,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `password` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `roles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `role` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `users_roles` (
  `user_id` int NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKj6m8fwv7oqv74fcehir1a9ffy` (`role_id`),
  CONSTRAINT `FK2o0jvgh89lemvvo17cbqvdxaa` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKj6m8fwv7oqv74fcehir1a9ffy` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
);

CREATE TABLE `categories` (
  `id` int NOT NULL AUTO_INCREMENT,
  `image` varchar(200) NOT NULL,
  `name` varchar(200) NOT NULL,
  `src` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `companies` (
  `id` int NOT NULL AUTO_INCREMENT,
  `image` varchar(200) NOT NULL,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `product_images` (
  `id` int NOT NULL,
  `image_four` varchar(300) NOT NULL,
  `image_one` varchar(300) NOT NULL,
  `image_three` varchar(300) NOT NULL,
  `image_two` varchar(300) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `products` (
  `id` int NOT NULL AUTO_INCREMENT,
  `amount` int NOT NULL,
  `description` varchar(2000) NOT NULL,
  `name` varchar(100) NOT NULL,
  `price` decimal(38,2) NOT NULL,
  `receipt_date` datetime(6),
  `title` varchar(200) NOT NULL,
  `category_id` int DEFAULT NULL,
  `company_id` int DEFAULT NULL,
  `product_image_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKog2rp4qthbtt2lfyhfo32lsw9` (`category_id`),
  KEY `FKr67nkbovcmogr3o5xkmfepgl1` (`company_id`),
  KEY `FK8vw42l3whg2uo3p6r55l9xbdk` (`product_image_id`),
  CONSTRAINT `FK8vw42l3whg2uo3p6r55l9xbdk` FOREIGN KEY (`product_image_id`) REFERENCES `product_images` (`id`),
  CONSTRAINT `FKog2rp4qthbtt2lfyhfo32lsw9` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`),
  CONSTRAINT `FKr67nkbovcmogr3o5xkmfepgl1` FOREIGN KEY (`company_id`) REFERENCES `companies` (`id`)
);

CREATE TABLE `reviews` (
  `id` int NOT NULL AUTO_INCREMENT,
  `date_time` datetime(6) NOT NULL,
  `message` varchar(500) NOT NULL,
  `product_id` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpl51cejpw4gy5swfar8br9ngi` (`product_id`),
  KEY `FKcgy7qjc1r99dp117y9en6lxye` (`user_id`),
  CONSTRAINT `FKcgy7qjc1r99dp117y9en6lxye` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKpl51cejpw4gy5swfar8br9ngi` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
);

CREATE TABLE `orders` (
  `id` int NOT NULL AUTO_INCREMENT,
  `date_time` datetime(6) DEFAULT NULL,
  `total` decimal(38,2) NOT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK32ql8ubntj5uh44ph9659tiih` (`user_id`),
  CONSTRAINT `FK32ql8ubntj5uh44ph9659tiih` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
);

CREATE TABLE `orders_products` (
  `product_id` int NOT NULL,
  `order_id` int NOT NULL,
  KEY `FKe4y1sseio787e4o5hrml7omt5` (`order_id`),
  KEY `FK43vke5jd6eyasd92t3k24kdxq` (`product_id`),
  CONSTRAINT `FK43vke5jd6eyasd92t3k24kdxq` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  CONSTRAINT `FKe4y1sseio787e4o5hrml7omt5` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
);