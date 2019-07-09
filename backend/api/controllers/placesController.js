const dbConnection = require("../models/placesModel");

exports.list_all_places = function (req, res) {

    dbConnection.query('SELECT * FROM places', function (err, rows, fields) {
        if (err) throw err;

        return res.json({
            rows
        })
    });
};