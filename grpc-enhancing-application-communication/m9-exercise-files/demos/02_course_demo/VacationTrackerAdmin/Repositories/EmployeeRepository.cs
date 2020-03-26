using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using VacationTrackerAdmin.Models;

namespace VacationTrackerAdmin.Repositories
{
    public class EmployeeRepository : IEmployeeRepository
    {
        private static int _nextEmployeeId = 1;
        private static int _nextVacationId = 1;

        private List<Employee> _employees = new List<Employee>
        {
            new Employee {
                Id = _nextEmployeeId++,
                BadgeNumber = 64927,
                FirstName = "Ann",
                LastName = "Jenkins",
                VacationAccrualRate = (float)6.14,
                VacationAccrued = 80,
                Vacations = new List<Vacation>()
            },
            new Employee {
                Id = _nextEmployeeId++,
                BadgeNumber = 72453,
                FirstName = "Chris",
                LastName = "Baker",
                VacationAccrualRate = (float)6.14,
                VacationAccrued = 16,
                Vacations = new List<Vacation>
                {
                    new Vacation {Id = _nextVacationId++, StartDate = new DateTime(2016, 3, 7), Duration = 16 },
                    new Vacation {Id = _nextVacationId++, StartDate = new DateTime(2016, 5, 30), Duration = 40 },
                    new Vacation {Id = _nextVacationId++, StartDate = new DateTime(2016, 6, 6), Duration = 8 }
                }
            },
            new Employee {
                Id = _nextEmployeeId++,
                BadgeNumber = 75257,
                FirstName = "Thomas",
                LastName = "Welch",
                VacationAccrualRate = (float)6.14,
                VacationAccrued = 64,
                Vacations = new List<Vacation>
                {
                    new Vacation {Id = _nextVacationId++, StartDate = new DateTime(2016, 4, 29), Duration = 8 },
                    new Vacation {Id = _nextVacationId++, StartDate = new DateTime(2016, 5, 13), Duration = 8 }
                }
            },
            new Employee {
                Id = _nextEmployeeId++,
                BadgeNumber = 80003,
                FirstName = "Frank",
                LastName = "Hunter",
                VacationAccrualRate = (float)6.14,
                VacationAccrued = 48,
                Vacations = new List<Vacation>
                {
                    new Vacation {Id = _nextVacationId++, StartDate = new DateTime(2016, 5, 6), Duration = 4 },
                    new Vacation {Id = _nextVacationId++, StartDate = new DateTime(2016, 5, 13), Duration = 4 },
                    new Vacation {Id = _nextVacationId++, StartDate = new DateTime(2016, 5, 20), Duration = 4 },
                    new Vacation {Id = _nextVacationId++, StartDate = new DateTime(2016, 5, 27), Duration = 4 },
                    new Vacation {Id = _nextVacationId++, StartDate = new DateTime(2016, 6, 16), Duration = 16 }
                }
            }
        };

        public async Task<List<Employee>> GetAllAsync()
        {
            return _employees;
        }

        public async Task<Employee> GetByBadgeNumberAsync(int badgeNumber)
        {
            return _employees.FirstOrDefault(emp => emp.BadgeNumber == badgeNumber);
        }

        public async Task<Employee> SaveAsync(Employee employee)
        {
            if (employee.Id != 0)
            {
                Employee emp = _employees.FirstOrDefault(e => e.Id == employee.Id);
                if (emp == null)
                {
                    _employees.Add(employee);
                }
                else
                {
                    emp.BadgeNumber = employee.BadgeNumber;
                    emp.FirstName = employee.FirstName;
                    emp.LastName = employee.LastName;
                    emp.VacationAccrualRate = employee.VacationAccrualRate;
                    emp.VacationAccrued = employee.VacationAccrued;

                    employee.Vacations
                        .FindAll(v => v.Id == 0)
                        .ForEach(v => v.Id = _nextVacationId++);
                    emp.Vacations = employee.Vacations;

                    employee = emp;
                }
            }
            else
            {
                employee.Id = _nextEmployeeId++;
                _employees.Add(employee);
            }

            return employee;
        }

        public async Task<List<Employee>> SaveAllAsync(List<Employee> employees)
        {
            var tasks = new List<Task<Employee>>();

            foreach (Employee e in employees)
            {
                tasks.Add(SaveAsync(e));
            }

            Task.WaitAll(tasks.ToArray());

            return employees;
        }
    }
}
