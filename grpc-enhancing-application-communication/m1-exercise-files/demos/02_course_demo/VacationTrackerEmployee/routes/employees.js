"use strict";
const express = require('express');
const router = express.Router();
const model = require('../models/employees');

router.post('/:badgeNumber/vacations/new', function(req, res, next) {
  	const badgeNumber = parseInt(req.params.badgeNumber);
	const employee = model.getByBadgeNumber(badgeNumber);
	
	const newVacation = req.body;
	const d = new Date(newVacation.startDate);
	// correct for timezone offset
	d.setMinutes(d.getMinutes() + d.getTimezoneOffset());
	newVacation.startDate = d;
	
	model.addVacation(badgeNumber, newVacation).then(function () {
		res.redirect('/employees/' + badgeNumber + '/vacations');
	});
});

router.get('/:badgeNumber/vacations/new', function(req, res, next) {
	const badgeNumber = parseInt(req.params.badgeNumber);
	model.getByBadgeNumber(badgeNumber).then(function(employee) {
		if (!employee) {
			res.redirect('/');
			return;
		}

		res.render('requestVacation', {employee: employee});
	});
});

router.get('/:badgeNumber/vacations', function(req, res, next) {
	const badgeNumber = parseInt(req.params.badgeNumber);
		model.getByBadgeNumber(badgeNumber).then(function(employee) {
			if (!employee) {
			res.redirect('/');
			return;
		}

		res.render('vacationReport', {employee: employee});
	});
});

module.exports = router;
