using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using VacationTrackerAdmin.Models;

namespace VacationTrackerAdmin.Repositories.Converters
{
    public class EmployeeConverter
    {
        private readonly VacationConverter _vacationConverter;

        public EmployeeConverter(VacationConverter vacationConverter)
        {
            _vacationConverter = vacationConverter;
        }

        public Employee ToModel(ProtocolBuffers.Employee employee)
        {
            return new Employee
            {
                Id = employee.Id,
                BadgeNumber = employee.BadgeNumber,
                FirstName = employee.FirstName,
                LastName = employee.LastName,
                VacationAccrualRate = employee.VacationAccrualRate,
                VacationAccrued = employee.VacationAccrued,
                Vacations = _vacationConverter.ToModels(employee.Vacations)
            };
        }

        public ProtocolBuffers.Employee ToMessage(Employee employee)
        {
            var result = new ProtocolBuffers.Employee
            {
                Id = employee.Id,
                BadgeNumber = employee.BadgeNumber,
                FirstName = employee.FirstName,
                LastName = employee.LastName,
                VacationAccrualRate = employee.VacationAccrualRate,
                VacationAccrued = employee.VacationAccrued
            };

            result.Vacations.AddRange(_vacationConverter.ToMessages(employee.Vacations));

            return result;
        }
    }
}
