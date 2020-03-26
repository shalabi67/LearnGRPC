using System;

namespace VacationTrackerAdmin.Models
{
    public class Vacation
    {
        public int Id { get; set; }
        public DateTime StartDate { get; set; }
        public float Duration { get; set; }
        public bool IsCancelled { get; set; }
    }
}