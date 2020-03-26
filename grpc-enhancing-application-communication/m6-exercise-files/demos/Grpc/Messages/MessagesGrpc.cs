// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: messages.proto
#region Designer generated code

using System;
using System.Threading;
using System.Threading.Tasks;
using Grpc.Core;

namespace Messages {
  public static class EmployeeService
  {
    static readonly string __ServiceName = "EmployeeService";

    static readonly Marshaller<global::Messages.GetByBadgeNumberRequest> __Marshaller_GetByBadgeNumberRequest = Marshallers.Create((arg) => global::Google.Protobuf.MessageExtensions.ToByteArray(arg), global::Messages.GetByBadgeNumberRequest.Parser.ParseFrom);
    static readonly Marshaller<global::Messages.EmployeeResponse> __Marshaller_EmployeeResponse = Marshallers.Create((arg) => global::Google.Protobuf.MessageExtensions.ToByteArray(arg), global::Messages.EmployeeResponse.Parser.ParseFrom);
    static readonly Marshaller<global::Messages.GetAllRequest> __Marshaller_GetAllRequest = Marshallers.Create((arg) => global::Google.Protobuf.MessageExtensions.ToByteArray(arg), global::Messages.GetAllRequest.Parser.ParseFrom);
    static readonly Marshaller<global::Messages.EmployeeRequest> __Marshaller_EmployeeRequest = Marshallers.Create((arg) => global::Google.Protobuf.MessageExtensions.ToByteArray(arg), global::Messages.EmployeeRequest.Parser.ParseFrom);
    static readonly Marshaller<global::Messages.AddPhotoRequest> __Marshaller_AddPhotoRequest = Marshallers.Create((arg) => global::Google.Protobuf.MessageExtensions.ToByteArray(arg), global::Messages.AddPhotoRequest.Parser.ParseFrom);
    static readonly Marshaller<global::Messages.AddPhotoResponse> __Marshaller_AddPhotoResponse = Marshallers.Create((arg) => global::Google.Protobuf.MessageExtensions.ToByteArray(arg), global::Messages.AddPhotoResponse.Parser.ParseFrom);

    static readonly Method<global::Messages.GetByBadgeNumberRequest, global::Messages.EmployeeResponse> __Method_GetByBadgeNumber = new Method<global::Messages.GetByBadgeNumberRequest, global::Messages.EmployeeResponse>(
        MethodType.Unary,
        __ServiceName,
        "GetByBadgeNumber",
        __Marshaller_GetByBadgeNumberRequest,
        __Marshaller_EmployeeResponse);

    static readonly Method<global::Messages.GetAllRequest, global::Messages.EmployeeResponse> __Method_GetAll = new Method<global::Messages.GetAllRequest, global::Messages.EmployeeResponse>(
        MethodType.ServerStreaming,
        __ServiceName,
        "GetAll",
        __Marshaller_GetAllRequest,
        __Marshaller_EmployeeResponse);

    static readonly Method<global::Messages.EmployeeRequest, global::Messages.EmployeeResponse> __Method_Save = new Method<global::Messages.EmployeeRequest, global::Messages.EmployeeResponse>(
        MethodType.Unary,
        __ServiceName,
        "Save",
        __Marshaller_EmployeeRequest,
        __Marshaller_EmployeeResponse);

    static readonly Method<global::Messages.EmployeeRequest, global::Messages.EmployeeResponse> __Method_SaveAll = new Method<global::Messages.EmployeeRequest, global::Messages.EmployeeResponse>(
        MethodType.DuplexStreaming,
        __ServiceName,
        "SaveAll",
        __Marshaller_EmployeeRequest,
        __Marshaller_EmployeeResponse);

    static readonly Method<global::Messages.AddPhotoRequest, global::Messages.AddPhotoResponse> __Method_AddPhoto = new Method<global::Messages.AddPhotoRequest, global::Messages.AddPhotoResponse>(
        MethodType.ClientStreaming,
        __ServiceName,
        "AddPhoto",
        __Marshaller_AddPhotoRequest,
        __Marshaller_AddPhotoResponse);

    /// <summary>Service descriptor</summary>
    public static global::Google.Protobuf.Reflection.ServiceDescriptor Descriptor
    {
      get { return global::Messages.MessagesReflection.Descriptor.Services[0]; }
    }

    /// <summary>Base class for server-side implementations of EmployeeService</summary>
    public abstract class EmployeeServiceBase
    {
      public virtual global::System.Threading.Tasks.Task<global::Messages.EmployeeResponse> GetByBadgeNumber(global::Messages.GetByBadgeNumberRequest request, ServerCallContext context)
      {
        throw new RpcException(new Status(StatusCode.Unimplemented, ""));
      }

      public virtual global::System.Threading.Tasks.Task GetAll(global::Messages.GetAllRequest request, IServerStreamWriter<global::Messages.EmployeeResponse> responseStream, ServerCallContext context)
      {
        throw new RpcException(new Status(StatusCode.Unimplemented, ""));
      }

      public virtual global::System.Threading.Tasks.Task<global::Messages.EmployeeResponse> Save(global::Messages.EmployeeRequest request, ServerCallContext context)
      {
        throw new RpcException(new Status(StatusCode.Unimplemented, ""));
      }

      public virtual global::System.Threading.Tasks.Task SaveAll(IAsyncStreamReader<global::Messages.EmployeeRequest> requestStream, IServerStreamWriter<global::Messages.EmployeeResponse> responseStream, ServerCallContext context)
      {
        throw new RpcException(new Status(StatusCode.Unimplemented, ""));
      }

      public virtual global::System.Threading.Tasks.Task<global::Messages.AddPhotoResponse> AddPhoto(IAsyncStreamReader<global::Messages.AddPhotoRequest> requestStream, ServerCallContext context)
      {
        throw new RpcException(new Status(StatusCode.Unimplemented, ""));
      }

    }

    /// <summary>Client for EmployeeService</summary>
    public class EmployeeServiceClient : ClientBase<EmployeeServiceClient>
    {
      /// <summary>Creates a new client for EmployeeService</summary>
      /// <param name="channel">The channel to use to make remote calls.</param>
      public EmployeeServiceClient(Channel channel) : base(channel)
      {
      }
      /// <summary>Creates a new client for EmployeeService that uses a custom <c>CallInvoker</c>.</summary>
      /// <param name="callInvoker">The callInvoker to use to make remote calls.</param>
      public EmployeeServiceClient(CallInvoker callInvoker) : base(callInvoker)
      {
      }
      /// <summary>Protected parameterless constructor to allow creation of test doubles.</summary>
      protected EmployeeServiceClient() : base()
      {
      }
      /// <summary>Protected constructor to allow creation of configured clients.</summary>
      /// <param name="configuration">The client configuration.</param>
      protected EmployeeServiceClient(ClientBaseConfiguration configuration) : base(configuration)
      {
      }

      public virtual global::Messages.EmployeeResponse GetByBadgeNumber(global::Messages.GetByBadgeNumberRequest request, Metadata headers = null, DateTime? deadline = null, CancellationToken cancellationToken = default(CancellationToken))
      {
        return GetByBadgeNumber(request, new CallOptions(headers, deadline, cancellationToken));
      }
      public virtual global::Messages.EmployeeResponse GetByBadgeNumber(global::Messages.GetByBadgeNumberRequest request, CallOptions options)
      {
        return CallInvoker.BlockingUnaryCall(__Method_GetByBadgeNumber, null, options, request);
      }
      public virtual AsyncUnaryCall<global::Messages.EmployeeResponse> GetByBadgeNumberAsync(global::Messages.GetByBadgeNumberRequest request, Metadata headers = null, DateTime? deadline = null, CancellationToken cancellationToken = default(CancellationToken))
      {
        return GetByBadgeNumberAsync(request, new CallOptions(headers, deadline, cancellationToken));
      }
      public virtual AsyncUnaryCall<global::Messages.EmployeeResponse> GetByBadgeNumberAsync(global::Messages.GetByBadgeNumberRequest request, CallOptions options)
      {
        return CallInvoker.AsyncUnaryCall(__Method_GetByBadgeNumber, null, options, request);
      }
      public virtual AsyncServerStreamingCall<global::Messages.EmployeeResponse> GetAll(global::Messages.GetAllRequest request, Metadata headers = null, DateTime? deadline = null, CancellationToken cancellationToken = default(CancellationToken))
      {
        return GetAll(request, new CallOptions(headers, deadline, cancellationToken));
      }
      public virtual AsyncServerStreamingCall<global::Messages.EmployeeResponse> GetAll(global::Messages.GetAllRequest request, CallOptions options)
      {
        return CallInvoker.AsyncServerStreamingCall(__Method_GetAll, null, options, request);
      }
      public virtual global::Messages.EmployeeResponse Save(global::Messages.EmployeeRequest request, Metadata headers = null, DateTime? deadline = null, CancellationToken cancellationToken = default(CancellationToken))
      {
        return Save(request, new CallOptions(headers, deadline, cancellationToken));
      }
      public virtual global::Messages.EmployeeResponse Save(global::Messages.EmployeeRequest request, CallOptions options)
      {
        return CallInvoker.BlockingUnaryCall(__Method_Save, null, options, request);
      }
      public virtual AsyncUnaryCall<global::Messages.EmployeeResponse> SaveAsync(global::Messages.EmployeeRequest request, Metadata headers = null, DateTime? deadline = null, CancellationToken cancellationToken = default(CancellationToken))
      {
        return SaveAsync(request, new CallOptions(headers, deadline, cancellationToken));
      }
      public virtual AsyncUnaryCall<global::Messages.EmployeeResponse> SaveAsync(global::Messages.EmployeeRequest request, CallOptions options)
      {
        return CallInvoker.AsyncUnaryCall(__Method_Save, null, options, request);
      }
      public virtual AsyncDuplexStreamingCall<global::Messages.EmployeeRequest, global::Messages.EmployeeResponse> SaveAll(Metadata headers = null, DateTime? deadline = null, CancellationToken cancellationToken = default(CancellationToken))
      {
        return SaveAll(new CallOptions(headers, deadline, cancellationToken));
      }
      public virtual AsyncDuplexStreamingCall<global::Messages.EmployeeRequest, global::Messages.EmployeeResponse> SaveAll(CallOptions options)
      {
        return CallInvoker.AsyncDuplexStreamingCall(__Method_SaveAll, null, options);
      }
      public virtual AsyncClientStreamingCall<global::Messages.AddPhotoRequest, global::Messages.AddPhotoResponse> AddPhoto(Metadata headers = null, DateTime? deadline = null, CancellationToken cancellationToken = default(CancellationToken))
      {
        return AddPhoto(new CallOptions(headers, deadline, cancellationToken));
      }
      public virtual AsyncClientStreamingCall<global::Messages.AddPhotoRequest, global::Messages.AddPhotoResponse> AddPhoto(CallOptions options)
      {
        return CallInvoker.AsyncClientStreamingCall(__Method_AddPhoto, null, options);
      }
      protected override EmployeeServiceClient NewInstance(ClientBaseConfiguration configuration)
      {
        return new EmployeeServiceClient(configuration);
      }
    }

    /// <summary>Creates service definition that can be registered with a server</summary>
    public static ServerServiceDefinition BindService(EmployeeServiceBase serviceImpl)
    {
      return ServerServiceDefinition.CreateBuilder()
          .AddMethod(__Method_GetByBadgeNumber, serviceImpl.GetByBadgeNumber)
          .AddMethod(__Method_GetAll, serviceImpl.GetAll)
          .AddMethod(__Method_Save, serviceImpl.Save)
          .AddMethod(__Method_SaveAll, serviceImpl.SaveAll)
          .AddMethod(__Method_AddPhoto, serviceImpl.AddPhoto).Build();
    }

  }
}
#endregion
