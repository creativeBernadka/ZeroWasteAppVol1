LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/places.csv'
INTO TABLE places
FIELDS TERMINATED BY ','
enclosed by '"'
LINES TERMINATED BY '\r\n'
ignore 1 rows
(
	places_id,
	place_name,
    latitude ,
    longitude ,
    rating ,
    type_of_place,
    phone_number,
    place_description,
    website
);
    
LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/opening_hours.csv'
INTO TABLE opening_hours
FIELDS TERMINATED BY ','
enclosed by '"'
LINES TERMINATED BY '\r\n'
ignore 1 rows 
(
	hours_id,
	place_id,
    day_of_week,
    @start_hour,
    @end_hour
)
SET start_hour = str_to_date(@start_hour, '%H:%i'),
	end_hour = str_to_date(@end_hour, '%H:%i')
;

LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/images_url.csv'
INTO TABLE images_url
FIELDS TERMINATED BY ','
enclosed by '"'
LINES TERMINATED BY '\r\n'
ignore 1 rows 
(
	images_id,
    place_id,
    url
);