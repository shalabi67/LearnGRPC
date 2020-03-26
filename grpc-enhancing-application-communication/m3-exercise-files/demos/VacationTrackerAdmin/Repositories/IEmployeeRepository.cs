using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using VacationTrackerAdmin.Models;

namespace VacationTrackerAdmin.Repositories
{
    public interface IEmployeeRepository
    {
        Task<List<Employee>> GetAllAsync();
        Task<Employee> SaveAsync(Employee employee);
        Task<List<Employee>> SaveAllAsync(List<Employee> employees);
        Task<Employee> GetByBadgeNumberAsync(int badgeNumber);
    }
}
