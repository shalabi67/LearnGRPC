using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using VacationTrackerAdmin.Repositories;
using VacationTrackerAdmin.Models;

// For more information on enabling MVC for empty projects, visit http://go.microsoft.com/fwlink/?LinkID=397860

namespace VacationTrackerAdmin.Controllers
{
    [Route("employees")]
    public class EmployeeController : Controller
    {
        private IEmployeeRepository _employeeRepository;

        public EmployeeController(IEmployeeRepository employeeRepository)
        {
            _employeeRepository = employeeRepository;
        }

        [Route("add")]
        [HttpGet]
        public async Task<IActionResult> GetAdd()
        {
            return View("Add");
        }

        [Route("add")]
        [HttpPost]
        public async Task<IActionResult> PostAdd(List<Employee> employees)
        {
            employees = employees.Where(e => e.BadgeNumber != 0).ToList();

            await _employeeRepository.SaveAllAsync(employees);

            return new RedirectToActionResult("index", "home", null);
        }

        [Route("update")]
        [HttpGet]
        public IActionResult GetId()
        {
            return View("IdForm");
        }

        [Route("", Name = "getUpdate")]
        [HttpGet]
        public async Task<IActionResult> GetUpdate(int badgeNumber)
        {
            var employee = await _employeeRepository.GetByBadgeNumberAsync(badgeNumber);

            if (employee == null)
            {
                return new RedirectToActionResult("index", "home", null);
            }

            return View("Update", employee);
        }

        [Route("")]
        [HttpPost]
        public async Task<IActionResult> PostUpdate(Employee employee)
        {
            employee = await _employeeRepository.SaveAsync(employee);

            return new RedirectToRouteResult("getUpdate", new { badgeNumber = employee.BadgeNumber });
        }
    }
}
