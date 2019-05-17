DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS files;

CREATE TABLE users (
	user_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	username VARCHAR(25) NOT NULL UNIQUE,
	password VARCHAR(50) NOT NULL
);

CREATE TABLE files (
	file_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	file_name VARCHAR(50) NOT NULL,
	upload_date DATE NOT NULL,
	user_id INT NOT NULL
);

INSERT INTO users (user_id, username, password) VALUES (1, "albert", "fc0376370bc38f9d1914231447340542252c9dc4");

INSERT INTO files (file_name, upload_date, user_id) VALUES ("test.txt", CURDATE(), 1);
INSERT INTO files (file_name, upload_date, user_id) VALUES ("example.pdf", CURDATE(), 1);
INSERT INTO files (file_name, upload_date, user_id) VALUES ("sample.jpg", CURDATE(), 1);
