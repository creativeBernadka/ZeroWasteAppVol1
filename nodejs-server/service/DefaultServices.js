const util = require('util');
const dbConnection = require("../models/placesModel");
const query = util.promisify(dbConnection.query).bind(dbConnection);

/**
 * Get all places
 * Returns all available zero waste places
 * returns List
 **/
exports.getAllPlaces = function(req, res) {
    console.log("TUTAJ 1");
  dbConnection.query(
      'SELECT places_id, place_name, latitude, longitude, type_of_place FROM places',
      function (err, rows, fields) {
        if (err) throw err;
        return res.json({
            places: rows
          })
      });
};


/**
 * Find place by ID
 * Returns a single place with its detailed description
 *
 * placeId Long ID of place to return
 * returns PlaceWithDescription
 **/
exports.getPlaceDescriptionById = async function(req, res, placeId) {

    let description = {};
    try{
        description = await query(
            'SELECT places.place_name, places.rating, places.type_of_place, places.phone_number, places.website, ' +
            'opening_hours.start_hour, opening_hours.end_hour, opening_hours.day_of_week ' +
            'FROM places INNER JOIN opening_hours ON places.places_id = opening_hours.place_id ' +
            'WHERE places.places_id = ? LIMIT 1',
            [placeId]
        );
    }
    catch (err) {
        throw err
    }

    return res.json({
        description
    })

    // dbConnection.query(
    //     'SELECT images_url.url FROM images_url INNER JOIN places ON places.places_id = images_url.place_id ' +
    //     'WHERE places.places_id = ?',
    //     [placeId],
    //     function (err, rows) {
    //         if (err) throw err;
    //         return res.json({
    //             images: rows
    //         })
    //     }
    // );
    //
    // console.log("WYNIK 2", result);
};