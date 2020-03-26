"use strict";

const express = require('express');
const router = express.Router();
const model = require('../models/employees');

/* GET home page. */
router.get('/login', function(req, res, next) {
  res.render('login');
});

router.get('/', function (req, res, next) {
  
  if (!req.query.badgeNumber) {
    res.redirect('/login');
    return;
  }
  model.getByBadgeNumber(parseInt(req.query.badgeNumber)).then(function (employee) {
    if (!employee) {
      res.redirect('/login');
    } else {
      res.render('index', {badgeNumber: req.query.badgeNumber});
    }
  });
});

module.exports = router;
