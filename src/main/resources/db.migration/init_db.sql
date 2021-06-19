CREATE TABLE Users
(
    Id_user     serial       NOT NULL,
    Last_name   varchar(30)  NOT NULL,
    First_name  varchar(30)  NOT NULL,
    Middle_name varchar(30)  NOT NULL,
    Username    varchar(30)  NOT NULL unique,
    Password    varchar(150) NOT NULL,
    Count       int   default 0,
    Id_picture  int,
    CONSTRAINT Users_pk PRIMARY KEY (Id_user)
);

CREATE TABLE Pictures
(
    Id_picture serial NOT NULL,
    Name       varchar(200) NOT NULL,
    Type       varchar(100) NOT NULL,
    Data       bytea  NOT NULL default pg_read_binary_file('D:\default.png'),
    CONSTRAINT Pictures_pk PRIMARY KEY (Id_picture)
);

ALTER TABLE Users
    ADD CONSTRAINT Users_fk0 FOREIGN KEY (Id_picture) REFERENCES Pictures (Id_picture);

INSERT INTO Users (Last_name, First_name, Middle_name, Username, Password)
VALUES ('Admin', 'Admin', 'Admin', 'admin123', 'admin123');


