DROP DATABASE IF EXISTS latterwin;
CREATE DATABASE latterwin;
use latterwin;

CREATE TABLE user (
  user_id int not null AUTO_INCREMENT,
  email varchar(255) not null,
  password varchar(255) not null,
  primary key(user_id)
);

INSERT INTO user VALUES (1, "user1@test.com", "password");
INSERT INTO user VALUES (2, "user2@test.com", "password");
INSERT INTO user VALUES (3, "user3@test.com", "password");
