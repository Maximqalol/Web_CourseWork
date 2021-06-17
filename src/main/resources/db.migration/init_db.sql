CREATE TABLE Users (
	Id_user serial NOT NULL,
	Last_name varchar(30) NOT NULL,
	First_name varchar(30) NOT NULL,
	Middle_name varchar(30) NOT NULL,
	Username varchar(30) NOT NULL unique,
	Password varchar(150) NOT NULL,
	Photo bytea default pg_read_binary_file('D:\default.png'),
	Count int default 0,
	CONSTRAINT Users_pk PRIMARY KEY (Id_user)
);

INSERT INTO Users (Last_name, First_name, Middle_name, Username, Password) VALUES ('Admin', 'Admin', 'Admin', 'admin123', 'admin123');


