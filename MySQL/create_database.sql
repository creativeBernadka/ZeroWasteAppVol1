CREATE DATABASE places_db;

USE places_db;

CREATE TABLE places (
	places_id INT NOT NULL AUTO_INCREMENT,
    place_name varchar(255) NOT NULL,
    latitude double NOT NULL,
    longitude double NOT NULL,
    rating double,
    type_of_place varchar(255),
    phone_number varchar(20),
    place_description varchar(1000),
    website varchar(255),
    PRIMARY KEY (places_id)
);

CREATE TABLE opening_hours (
	hours_id INT NOT NULL AUTO_INCREMENT,
    place_id INT NOT NULL REFERENCES places(places_id),
    day_of_week INT NOT NULL,
    start_hour time NOT NULL,
    end_hour time NOT NULL,
    primary key (hours_id)
);

CREATE TABLE images_url (
	images_id INT NOT NULL AUTO_INCREMENT,
    place_id INT NOT NULL REFERENCES places(places_id),
    url varchar(2083) NOT NULL,
    primary key (images_id)
);

#drop table places;
#drop table opening_hours;
#drop table images_url;