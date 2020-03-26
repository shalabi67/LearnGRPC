"use strict";

let nextEmployeeId = 1;
let nextVacationId = 1;

const employees = [
	{
		id: nextEmployeeId++,
		badgeNumber: 64927,
		firstName: "Ann",
		lastName: "Jenkins",
		vacationAccrualRate: 6.14,
		vacationAccrued: 80,
		vacations: [],
	},
	{
		id: nextEmployeeId++,
		badgeNumber: 72453,
		firstName: "Chris",
		lastName: "Baker",
		vacationAccrualRate:6.14,
		vacationAccrued: 16,
		vacations: [
			{
				id: nextVacationId++,
				startDate: new Date(2016,2, 7),
				duration: 16,
				isCancelled: false
			},
			{
				id: nextVacationId++,
				startDate: new Date(2016, 4, 30),
				duration: 40,
				isCancelled: false
			},
			{
				id: nextVacationId++,
				startDate: new Date(2016, 5, 6),
				duration: 8,
				isCancelled: false
			}
		]
	},
	{
		id: nextEmployeeId++,
		badgeNumber: 75257,
		firstName: "Thomas",
		lastName: "Welch",
		vacationAccrualRate: 6.14,
		vacationAccrued: 64,
		vacations: [
				{
				id: nextVacationId++,
				startDate: new Date(2016, 3, 29),
				duration: 8,
				isCancelled: false
			},
			{
				id: nextVacationId++,
				startDate: new Date(2016, 4, 13),
				duration: 8,
				isCancelled: false
			}
		]
	},
	{
		id: nextEmployeeId++,
		badgeNumber: 80003,
		firstName: "Frank",
		lastName: "Hunter",
		vacationAccrualRate: 6.14,
		vacationAccrued: 48,
		vacations: [
			{
				id: nextVacationId++,
				startDate: new Date(2016, 4, 6), 
				duration : 4,
				isCancelled: false
			},
			{
				id: nextVacationId++, 
				startDate: new Date(2016, 4, 13), 
				duration: 4,
				isCancelled: false
			},
				{
				id: nextVacationId++, 
				startDate: new Date(2016, 4, 27), 
				duration: 4,
				isCancelled: false
			},
				{
				id: nextVacationId++, 
				startDate: new Date(2016, 4, 20), 
				duration: 4,
				isCancelled: false
			},
				{
				id: nextVacationId++, 
				startDate: new Date(2016, 5, 16), 
				duration: 16,
				isCancelled: false
			}
		]
	}
];

function getByBadgeNumber(badgeNumber) {
	const promise = new Promise(function (resolve, reject) {
		const result =  employees.find(function(emp) {
			return emp.badgeNumber === badgeNumber;
		});

		resolve(result);
	});
	
	return promise;
}

function addVacation(badgeNumber, vacation) {
	const promise = new Promise(function (resolve, reject) {
		getByBadgeNumber(badgeNumber).then(function (emp) {
			if (!vacation.id) {
				vacation.id = nextVacationId++;
			}

			vacation.isCancelled = false;

			if (emp) {
				emp.vacations.push(vacation);
			}
			resolve(emp);
		});
	});
	
	return promise;
}

module.exports = {
	getByBadgeNumber: getByBadgeNumber,
	addVacation: addVacation
};