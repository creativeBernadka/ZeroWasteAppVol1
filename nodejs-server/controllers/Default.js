'use strict';

const utils = require('../utils/writer.js');
const Default = require('../service/DefaultServices');

module.exports.getAllPlaces = function getAllPlaces (req, res, next) {
  Default.getAllPlaces(req, res);
};

module.exports.getPlaceDescriptionById = function getPlaceDescriptionById (req, res, next) {
  const placeId = req.swagger.params['placeId'].value;
  Default.getPlaceDescriptionById(req, res, placeId);
};
