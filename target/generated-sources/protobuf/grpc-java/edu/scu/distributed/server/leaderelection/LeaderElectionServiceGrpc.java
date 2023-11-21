package edu.scu.distributed.server.leaderelection;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.51.0)",
    comments = "Source: leaderElectionService.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class LeaderElectionServiceGrpc {

  private LeaderElectionServiceGrpc() {}

  public static final String SERVICE_NAME = "LeaderElectionService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<edu.scu.distributed.server.leaderelection.Empty,
      edu.scu.distributed.server.leaderelection.Leader> getLeaderMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "leader",
      requestType = edu.scu.distributed.server.leaderelection.Empty.class,
      responseType = edu.scu.distributed.server.leaderelection.Leader.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<edu.scu.distributed.server.leaderelection.Empty,
      edu.scu.distributed.server.leaderelection.Leader> getLeaderMethod() {
    io.grpc.MethodDescriptor<edu.scu.distributed.server.leaderelection.Empty, edu.scu.distributed.server.leaderelection.Leader> getLeaderMethod;
    if ((getLeaderMethod = LeaderElectionServiceGrpc.getLeaderMethod) == null) {
      synchronized (LeaderElectionServiceGrpc.class) {
        if ((getLeaderMethod = LeaderElectionServiceGrpc.getLeaderMethod) == null) {
          LeaderElectionServiceGrpc.getLeaderMethod = getLeaderMethod =
              io.grpc.MethodDescriptor.<edu.scu.distributed.server.leaderelection.Empty, edu.scu.distributed.server.leaderelection.Leader>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "leader"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  edu.scu.distributed.server.leaderelection.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  edu.scu.distributed.server.leaderelection.Leader.getDefaultInstance()))
              .setSchemaDescriptor(new LeaderElectionServiceMethodDescriptorSupplier("leader"))
              .build();
        }
      }
    }
    return getLeaderMethod;
  }

  private static volatile io.grpc.MethodDescriptor<edu.scu.distributed.server.leaderelection.HeartbeatRequest,
      edu.scu.distributed.server.leaderelection.HeartbeatResponse> getOnHeartbeatMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "onHeartbeat",
      requestType = edu.scu.distributed.server.leaderelection.HeartbeatRequest.class,
      responseType = edu.scu.distributed.server.leaderelection.HeartbeatResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<edu.scu.distributed.server.leaderelection.HeartbeatRequest,
      edu.scu.distributed.server.leaderelection.HeartbeatResponse> getOnHeartbeatMethod() {
    io.grpc.MethodDescriptor<edu.scu.distributed.server.leaderelection.HeartbeatRequest, edu.scu.distributed.server.leaderelection.HeartbeatResponse> getOnHeartbeatMethod;
    if ((getOnHeartbeatMethod = LeaderElectionServiceGrpc.getOnHeartbeatMethod) == null) {
      synchronized (LeaderElectionServiceGrpc.class) {
        if ((getOnHeartbeatMethod = LeaderElectionServiceGrpc.getOnHeartbeatMethod) == null) {
          LeaderElectionServiceGrpc.getOnHeartbeatMethod = getOnHeartbeatMethod =
              io.grpc.MethodDescriptor.<edu.scu.distributed.server.leaderelection.HeartbeatRequest, edu.scu.distributed.server.leaderelection.HeartbeatResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "onHeartbeat"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  edu.scu.distributed.server.leaderelection.HeartbeatRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  edu.scu.distributed.server.leaderelection.HeartbeatResponse.getDefaultInstance()))
              .setSchemaDescriptor(new LeaderElectionServiceMethodDescriptorSupplier("onHeartbeat"))
              .build();
        }
      }
    }
    return getOnHeartbeatMethod;
  }

  private static volatile io.grpc.MethodDescriptor<edu.scu.distributed.server.leaderelection.VoteRequest,
      edu.scu.distributed.server.leaderelection.VoteResponse> getOnRequestVoteMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "onRequestVote",
      requestType = edu.scu.distributed.server.leaderelection.VoteRequest.class,
      responseType = edu.scu.distributed.server.leaderelection.VoteResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<edu.scu.distributed.server.leaderelection.VoteRequest,
      edu.scu.distributed.server.leaderelection.VoteResponse> getOnRequestVoteMethod() {
    io.grpc.MethodDescriptor<edu.scu.distributed.server.leaderelection.VoteRequest, edu.scu.distributed.server.leaderelection.VoteResponse> getOnRequestVoteMethod;
    if ((getOnRequestVoteMethod = LeaderElectionServiceGrpc.getOnRequestVoteMethod) == null) {
      synchronized (LeaderElectionServiceGrpc.class) {
        if ((getOnRequestVoteMethod = LeaderElectionServiceGrpc.getOnRequestVoteMethod) == null) {
          LeaderElectionServiceGrpc.getOnRequestVoteMethod = getOnRequestVoteMethod =
              io.grpc.MethodDescriptor.<edu.scu.distributed.server.leaderelection.VoteRequest, edu.scu.distributed.server.leaderelection.VoteResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "onRequestVote"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  edu.scu.distributed.server.leaderelection.VoteRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  edu.scu.distributed.server.leaderelection.VoteResponse.getDefaultInstance()))
              .setSchemaDescriptor(new LeaderElectionServiceMethodDescriptorSupplier("onRequestVote"))
              .build();
        }
      }
    }
    return getOnRequestVoteMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static LeaderElectionServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<LeaderElectionServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<LeaderElectionServiceStub>() {
        @java.lang.Override
        public LeaderElectionServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new LeaderElectionServiceStub(channel, callOptions);
        }
      };
    return LeaderElectionServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static LeaderElectionServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<LeaderElectionServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<LeaderElectionServiceBlockingStub>() {
        @java.lang.Override
        public LeaderElectionServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new LeaderElectionServiceBlockingStub(channel, callOptions);
        }
      };
    return LeaderElectionServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static LeaderElectionServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<LeaderElectionServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<LeaderElectionServiceFutureStub>() {
        @java.lang.Override
        public LeaderElectionServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new LeaderElectionServiceFutureStub(channel, callOptions);
        }
      };
    return LeaderElectionServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class LeaderElectionServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void leader(edu.scu.distributed.server.leaderelection.Empty request,
        io.grpc.stub.StreamObserver<edu.scu.distributed.server.leaderelection.Leader> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getLeaderMethod(), responseObserver);
    }

    /**
     */
    public void onHeartbeat(edu.scu.distributed.server.leaderelection.HeartbeatRequest request,
        io.grpc.stub.StreamObserver<edu.scu.distributed.server.leaderelection.HeartbeatResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getOnHeartbeatMethod(), responseObserver);
    }

    /**
     */
    public void onRequestVote(edu.scu.distributed.server.leaderelection.VoteRequest request,
        io.grpc.stub.StreamObserver<edu.scu.distributed.server.leaderelection.VoteResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getOnRequestVoteMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getLeaderMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                edu.scu.distributed.server.leaderelection.Empty,
                edu.scu.distributed.server.leaderelection.Leader>(
                  this, METHODID_LEADER)))
          .addMethod(
            getOnHeartbeatMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                edu.scu.distributed.server.leaderelection.HeartbeatRequest,
                edu.scu.distributed.server.leaderelection.HeartbeatResponse>(
                  this, METHODID_ON_HEARTBEAT)))
          .addMethod(
            getOnRequestVoteMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                edu.scu.distributed.server.leaderelection.VoteRequest,
                edu.scu.distributed.server.leaderelection.VoteResponse>(
                  this, METHODID_ON_REQUEST_VOTE)))
          .build();
    }
  }

  /**
   */
  public static final class LeaderElectionServiceStub extends io.grpc.stub.AbstractAsyncStub<LeaderElectionServiceStub> {
    private LeaderElectionServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LeaderElectionServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new LeaderElectionServiceStub(channel, callOptions);
    }

    /**
     */
    public void leader(edu.scu.distributed.server.leaderelection.Empty request,
        io.grpc.stub.StreamObserver<edu.scu.distributed.server.leaderelection.Leader> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getLeaderMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void onHeartbeat(edu.scu.distributed.server.leaderelection.HeartbeatRequest request,
        io.grpc.stub.StreamObserver<edu.scu.distributed.server.leaderelection.HeartbeatResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getOnHeartbeatMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void onRequestVote(edu.scu.distributed.server.leaderelection.VoteRequest request,
        io.grpc.stub.StreamObserver<edu.scu.distributed.server.leaderelection.VoteResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getOnRequestVoteMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class LeaderElectionServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<LeaderElectionServiceBlockingStub> {
    private LeaderElectionServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LeaderElectionServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new LeaderElectionServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public edu.scu.distributed.server.leaderelection.Leader leader(edu.scu.distributed.server.leaderelection.Empty request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getLeaderMethod(), getCallOptions(), request);
    }

    /**
     */
    public edu.scu.distributed.server.leaderelection.HeartbeatResponse onHeartbeat(edu.scu.distributed.server.leaderelection.HeartbeatRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getOnHeartbeatMethod(), getCallOptions(), request);
    }

    /**
     */
    public edu.scu.distributed.server.leaderelection.VoteResponse onRequestVote(edu.scu.distributed.server.leaderelection.VoteRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getOnRequestVoteMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class LeaderElectionServiceFutureStub extends io.grpc.stub.AbstractFutureStub<LeaderElectionServiceFutureStub> {
    private LeaderElectionServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LeaderElectionServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new LeaderElectionServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<edu.scu.distributed.server.leaderelection.Leader> leader(
        edu.scu.distributed.server.leaderelection.Empty request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getLeaderMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<edu.scu.distributed.server.leaderelection.HeartbeatResponse> onHeartbeat(
        edu.scu.distributed.server.leaderelection.HeartbeatRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getOnHeartbeatMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<edu.scu.distributed.server.leaderelection.VoteResponse> onRequestVote(
        edu.scu.distributed.server.leaderelection.VoteRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getOnRequestVoteMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_LEADER = 0;
  private static final int METHODID_ON_HEARTBEAT = 1;
  private static final int METHODID_ON_REQUEST_VOTE = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final LeaderElectionServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(LeaderElectionServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_LEADER:
          serviceImpl.leader((edu.scu.distributed.server.leaderelection.Empty) request,
              (io.grpc.stub.StreamObserver<edu.scu.distributed.server.leaderelection.Leader>) responseObserver);
          break;
        case METHODID_ON_HEARTBEAT:
          serviceImpl.onHeartbeat((edu.scu.distributed.server.leaderelection.HeartbeatRequest) request,
              (io.grpc.stub.StreamObserver<edu.scu.distributed.server.leaderelection.HeartbeatResponse>) responseObserver);
          break;
        case METHODID_ON_REQUEST_VOTE:
          serviceImpl.onRequestVote((edu.scu.distributed.server.leaderelection.VoteRequest) request,
              (io.grpc.stub.StreamObserver<edu.scu.distributed.server.leaderelection.VoteResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class LeaderElectionServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    LeaderElectionServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return edu.scu.distributed.server.leaderelection.LeaderElectionServiceOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("LeaderElectionService");
    }
  }

  private static final class LeaderElectionServiceFileDescriptorSupplier
      extends LeaderElectionServiceBaseDescriptorSupplier {
    LeaderElectionServiceFileDescriptorSupplier() {}
  }

  private static final class LeaderElectionServiceMethodDescriptorSupplier
      extends LeaderElectionServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    LeaderElectionServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (LeaderElectionServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new LeaderElectionServiceFileDescriptorSupplier())
              .addMethod(getLeaderMethod())
              .addMethod(getOnHeartbeatMethod())
              .addMethod(getOnRequestVoteMethod())
              .build();
        }
      }
    }
    return result;
  }
}
