'use strict';

var utils = require('../utils/writer.js');
var Default = require('../service/DefaultService');

module.exports.getAllPlaces = function getAllPlaces (req, res, next) {
  Default.getAllPlaces()
    .then(function (response) {
      utils.writeJson(res, response);
    })
    .catch(function (response) {
      utils.writeJson(res, response);
    });
};

module.exports.getPlaceDescriptionById = function getPlaceDescriptionById (req, res, next) {
  var placeId = req.swagger.params['placeId'].value;
  Default.getPlaceDescriptionById(placeId)
    .then(function (response) {
      utils.writeJson(res, response);
    })
    .catch(function (response) {
      utils.writeJson(res, response);
    });
};
