const express = require('express');
const bodyParser = require('body-parser');
const places = require('../controllers/placesController');

const router = express.Router();

router.use(bodyParser.json());
router.get('/descriptions', (req, res) => places.list_all_places(req, res));

module.exports = router;