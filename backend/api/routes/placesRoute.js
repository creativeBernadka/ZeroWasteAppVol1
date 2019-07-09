const express = require('express');
const bodyParser = require('body-parser');
const places = require('../controllers/placesController');

const router = express.Router();

router.use(bodyParser.json());
router.get('/descriptions', (req, res) => places.list_all_places(req, res));
router.post('/descriptions', (req, res) => places.get_description(req, res));
router.post('/descriptions/images', (req, res) => places.get_image(req, res));
module.exports = router;