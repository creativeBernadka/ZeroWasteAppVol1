'use strict';


/**
 * Get all places
 * Returns all avaliable zero waste places
 *
 * returns List
 **/
exports.getAllPlaces = function() {
  return new Promise(function(resolve, reject) {
    var examples = {};
    examples['application/json'] = [ {
  "place_name" : "The White Tawern",
  "latitude" : 6.02745618307040320615897144307382404804229736328125,
  "id" : 0,
  "type_of_place" : "restauracja",
  "longitude" : 1.46581298050294517310021547018550336360931396484375
}, {
  "place_name" : "The White Tawern",
  "latitude" : 6.02745618307040320615897144307382404804229736328125,
  "id" : 0,
  "type_of_place" : "restauracja",
  "longitude" : 1.46581298050294517310021547018550336360931396484375
} ];
    if (Object.keys(examples).length > 0) {
      resolve(examples[Object.keys(examples)[0]]);
    } else {
      resolve();
    }
  });
}


/**
 * Find place by ID
 * Returns a single place with its detailed description
 *
 * placeId Long ID of place to return
 * returns PlaceWithDescription
 **/
exports.getPlaceDescriptionById = function(placeId) {
  return new Promise(function(resolve, reject) {
    var examples = {};
    examples['application/json'] = {
  "place_name" : "The Abacus Flavour",
  "website" : "http://eloquentjavascript.net/05_higher_order.html",
  "photoUrls" : [ "photoUrls", "photoUrls" ],
  "start_hour" : "07:00:00",
  "rating" : 6,
  "phone_number" : "694 791 559",
  "id" : 0,
  "end_hour" : "15:00:00",
  "type_of_place" : "restauracja",
  "day_of_week" : 1
};
    if (Object.keys(examples).length > 0) {
      resolve(examples[Object.keys(examples)[0]]);
    } else {
      resolve();
    }
  });
}

