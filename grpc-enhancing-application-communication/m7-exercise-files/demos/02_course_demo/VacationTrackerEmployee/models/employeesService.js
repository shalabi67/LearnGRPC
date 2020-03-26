"use strict";

const PROTO_PATH = '../pb/messages.proto';
const SERVER_ADDRESS = 'localhost:8080';
const grpc = require('grpc');

const EmployeeService = grpc.load(PROTO_PATH).EmployeeService;
const client = new EmployeeService(SERVER_ADDRESS, grpc.credentials.createInsecure());

function getByBadgeNumber(badgeNumber) {
	const promise = new Promise(function(resolve, reject) {
		client.getByBadgeNumber({
			badgeNumber: badgeNumber
		}, function (error, response) {
			if (error) {
				reject(error);
				return;
			}

			const employee = response.employee;
			// transform date from UNIX time stamp
			employee.vacations.forEach(function (vac) {
				vac.startDate = new Date(parseInt(vac.startDate)*1000);
			});

			resolve(response.employee);
		});

	});
	return promise;
}

function addVacation(badgeNumber, vacation) {
	const promise = new Promise(function (resolve, reject) {
		getByBadgeNumber(badgeNumber).then(function (response) {
			if (error) {
				reject(error);
				return;
			}

			const employee = response.employee;
			vacation.isCancelled = false;
			vac.startDate = vac.startDate.getTime();

			employee.vacations.push(vacation);

			client.save({
				employee: employee
			}, function (error, response) {
				if (error) {
					reject(error);
					return;
				}

				resolve(response.employee);
			});
		});
	});

	return promise;
}

module.exports = {
	getByBadgeNumber: getByBadgeNumber,
	addVacation: addVacation
};