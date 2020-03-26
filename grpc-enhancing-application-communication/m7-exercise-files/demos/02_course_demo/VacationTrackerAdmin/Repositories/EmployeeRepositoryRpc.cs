using Grpc.Core;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;
using VacationTrackerAdmin.Models;
using VacationTrackerAdmin.ProtocolBuffers;
using VacationTrackerAdmin.Repositories.Converters;

namespace VacationTrackerAdmin.Repositories
{
    public class EmployeeRepositoryRpc : IEmployeeRepository
    {
        private readonly EmployeeConverter _employeeConverter;
        private readonly EmployeeService.EmployeeServiceClient _employeeServiceClient;

        public EmployeeRepositoryRpc(EmployeeConverter employeeConverter)
        {
            var channel = new Channel("localhost:8080", ChannelCredentials.Insecure);
            _employeeServiceClient = new ProtocolBuffers.EmployeeService.EmployeeServiceClient(channel);

            _employeeConverter = employeeConverter;
        }

        public async Task<List<Models.Employee>> GetAllAsync()
        {
            var result = new List<Models.Employee>();
            using (var call = _employeeServiceClient.GetAll(new ProtocolBuffers.GetAllRequest()))
            {
                while (await call.ResponseStream.MoveNext())
                {
                    result.Add(_employeeConverter.ToModel(call.ResponseStream.Current.Employee));
                }
            }

            return result;
        }

        public async Task<Models.Employee> GetByBadgeNumberAsync(int badgeNumber)
        {
            var request = new ProtocolBuffers.GetByBadgeNumberRequest()
            {
                BadgeNumber = badgeNumber
            };

            var response = await _employeeServiceClient.GetByBadgeNumberAsync(request);

            return _employeeConverter.ToModel(response.Employee);

        }

        public async Task<List<Models.Employee>> SaveAllAsync(List<Models.Employee> employees)
        {
            var result = new List<Models.Employee>();
            var _mutex = new Mutex();

            using (var call = _employeeServiceClient.SaveAll())
            {
                var responseReaderTask = Task.Run(async () =>
                {
                    while (await call.ResponseStream.MoveNext())
                    {
                        _mutex.WaitOne();

                        result.Add(_employeeConverter.ToModel(call.ResponseStream.Current.Employee));

                        _mutex.ReleaseMutex();
                    }
                });

                foreach (var emp in employees)
                {
                    var request = new ProtocolBuffers.EmployeeRequest
                    {
                        Employee = _employeeConverter.ToMessage(emp)
                    };
                    await call.RequestStream.WriteAsync(request);
                };

                await call.RequestStream.CompleteAsync();
                await responseReaderTask;

            }


            return result;
        }

        public async Task<Models.Employee> SaveAsync(Models.Employee employee)
        {
            var request = new ProtocolBuffers.EmployeeRequest
            {
                Employee = _employeeConverter.ToMessage(employee)
            };

            var response = await _employeeServiceClient.SaveAsync(request);

            return _employeeConverter.ToModel(response.Employee);
        }
    }
}
