using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;

namespace VacationTrackerAdmin.Models
{
    public class Employee
    {
        public int Id { get; set; }
        
        [Display(Name = "Badge#")]
        public int BadgeNumber { get; set; }

        [Display(Name = "First Name")]
        public string FirstName { get; set; }

        [Display(Name = "Last Name")]
        public string LastName { get; set; }

        [Display(Name = "Accrual Rate")]
        public float VacationAccrualRate { get; set; }

        [Display(Name = "Total Hours")]
        public float VacationAccrued { get; set; }
        
        public List<Vacation> Vacations { get; set; } = new List<Vacation>();
    }
}
