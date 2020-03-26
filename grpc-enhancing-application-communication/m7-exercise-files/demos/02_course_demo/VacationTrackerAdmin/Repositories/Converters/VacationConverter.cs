using Google.Protobuf.Collections;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace VacationTrackerAdmin.Repositories.Converters
{
    public class VacationConverter
    {

        public Models.Vacation ToModel(ProtocolBuffers.Vacation vacation)
        {
            var startDate = new DateTime(1970, 1, 1, 0, 0, 0);
            startDate = startDate.AddSeconds(vacation.StartDate);

            return new Models.Vacation
            {
                Id = vacation.Id,
                StartDate = startDate,
                Duration = vacation.Duration,
                IsCancelled = vacation.IsCancelled
            };
        }

        internal List<Models.Vacation> ToModels(RepeatedField<ProtocolBuffers.Vacation> vacations)
        {
            var result = new List<Models.Vacation>();

            foreach (ProtocolBuffers.Vacation vac in vacations)
            {
                result.Add(ToModel(vac));
            }

            return result;
        }

        public ProtocolBuffers.Vacation ToMessage(Models.Vacation vacation)
        {
            return new ProtocolBuffers.Vacation
            {
                Id = vacation.Id,
                StartDate = vacation.StartDate.Ticks,
                Duration = vacation.Duration,
                IsCancelled = vacation.IsCancelled
            };
        }

        public List<ProtocolBuffers.Vacation> ToMessages(List<Models.Vacation> vacations)
        {
            return vacations.Select(vac => ToMessage(vac)).ToList();
        }
    }
}
