'use strict';

const utils = require('../utils/writer.js');
const Controller = require('../service/DefaultServices');

module.exports.getAllPlaces = function getAllPlaces (req, res, next) {
  Controller.getAllPlaces(req, res);
};

module.exports.getPlaceDescriptionById = function getPlaceDescriptionById (req, res, next) {
  const placeId = req.swagger.params['placeId'].value;
  Controller.getPlaceDescriptionById(req, res, placeId);
};
