create database da;
use da;

CREATE TABLE user (
    id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY ,
    firstname varchar(45) DEFAULT NULL,
    lastname varchar(45) DEFAULT NULL,
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE card (
    id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    card_number int(22) DEFAULT NULL,
    pin int(4) DEFAULT NULL,
    amount dec(10),
    user_id int(11),

    CONSTRAINT card_user_id_fk FOREIGN KEY (user_id) references user(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into card (card_number, pin, amount, user_id) values (111222333, 1234, 777, 9);

