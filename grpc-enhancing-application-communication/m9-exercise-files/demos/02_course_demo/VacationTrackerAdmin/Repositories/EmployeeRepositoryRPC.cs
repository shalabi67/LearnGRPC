using Grpc.Core;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;
using VacationTrackerAdmin.Models;
using VacationTrackerAdmin.Repositories.Converters;

namespace VacationTrackerAdmin.Repositories
{
    public class EmployeeRepositoryRPC : IEmployeeRepository
    {
        private EmployeeConverter _employeeConverter;
        ProtocolBuffers.EmployeeService.EmployeeServiceClient _employeeServiceClient;
                
        public EmployeeRepositoryRPC(EmployeeConverter employeeConverter)
        {
            var channel = new Channel("localhost:8080", ChannelCredentials.Insecure);
            _employeeServiceClient = new ProtocolBuffers.EmployeeService.EmployeeServiceClient(channel);


            _employeeConverter = employeeConverter;
        }

        public async Task<List<Employee>> GetAllAsync()
        {
            var result = new List<Employee>();
            using (var call = _employeeServiceClient.GetAll(new ProtocolBuffers.GetAllRequest()))
            {
                while (await call.ResponseStream.MoveNext())
                {
                    result.Add(_employeeConverter.ToModel(call.ResponseStream.Current.Employee));
                }
            }

            return result;
            
        }

        public async Task<Employee> GetByBadgeNumberAsync(int badgeNumber)
        {
            var request = new ProtocolBuffers.GetByBadgeNumberRequest
            {
                BadgeNumber = badgeNumber
            };

            var response = await _employeeServiceClient.GetByBadgeNumberAsync(request);

            return _employeeConverter.ToModel(response.Employee);
        }

        public async Task<Employee> SaveAsync(Employee employee)
        {
            var request = new ProtocolBuffers.EmployeeRequest
            {
                Employee = _employeeConverter.ToMessage(employee)
            };

            var response = await _employeeServiceClient.SaveAsync(request);

            return _employeeConverter.ToModel(response.Employee);
        }

        public async Task<List<Employee>> SaveAllAsync(List<Employee> employees)
        {
            var result = new List<Employee>();
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
    }
}