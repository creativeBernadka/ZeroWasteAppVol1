const dbConnection = require("../models/placesModel");

exports.list_all_places = function (req, res) {

    dbConnection.query(
        'SELECT place_name, latitude, longitude, type_of_place FROM places',
        function (err, rows, fields) {
            if (err) throw err;
            return res.json({
                places: rows
            })
        });
};

exports.get_description = function (req, res) {

    const place = req.body;

    dbConnection.query(
        'SELECT places.place_name, places.rating, places.type_of_place, places.phone_number, places.website, ' +
        'opening_hours.start_hour, opening_hours.end_hour, opening_hours.day_of_week ' +
        'FROM places INNER JOIN opening_hours ON places.places_id = opening_hours.place_id ' +
        'WHERE( place_name = ? and latitude = ? and longitude = ? and day_of_week = ?) LIMIT 1',
        [place.place_name, place.latitude, place.longitude, place.day_of_week],
        function (err, rows) {
            if (err) throw err;
            return res.json({
                place: rows
            })
        }
    )
};

exports.get_image = function (req, res) {

    const place = req.body;

    dbConnection.query(
        'SELECT images_url.url FROM images_url INNER JOIN places ON places.places_id = images_url.place_id ' +
        'WHERE( places.place_name = ? and places.latitude = ? and places.longitude = ?)',
        [place.place_name, place.latitude, place.longitude],
        function (err, rows) {
            if (err) throw err;
            return res.json({
                images: rows
            })
        }
    )
}