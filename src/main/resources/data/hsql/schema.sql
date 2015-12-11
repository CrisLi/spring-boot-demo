DROP TABLE greetings IF EXISTS;

CREATE TABLE greetings (
   id BIGINT GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) NOT NULL,
   text VARCHAR(100) NOT NULL,
   scheduled_date_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
   PRIMARY KEY (ID)
);

DROP TABLE accounts IF EXISTS;

CREATE TABLE accounts (
   id BIGINT GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) NOT NULL,
   username VARCHAR(100) NOT NULL,
   password VARCHAR(100) NOT NULL,
   role VARCHAR(100) NOT NULL,
   PRIMARY KEY (ID)
);
