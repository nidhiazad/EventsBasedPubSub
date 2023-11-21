package edu.scu.distributed.server.services;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.51.0)",
    comments = "Source: TopicService.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class TopicServiceGrpc {

  private TopicServiceGrpc() {}

  public static final String SERVICE_NAME = "TopicService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<edu.scu.distributed.server.services.SubscribeRequest,
      edu.scu.distributed.server.services.Status> getSubscribeToTopicMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "subscribeToTopic",
      requestType = edu.scu.distributed.server.services.SubscribeRequest.class,
      responseType = edu.scu.distributed.server.services.Status.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<edu.scu.distributed.server.services.SubscribeRequest,
      edu.scu.distributed.server.services.Status> getSubscribeToTopicMethod() {
    io.grpc.MethodDescriptor<edu.scu.distributed.server.services.SubscribeRequest, edu.scu.distributed.server.services.Status> getSubscribeToTopicMethod;
    if ((getSubscribeToTopicMethod = TopicServiceGrpc.getSubscribeToTopicMethod) == null) {
      synchronized (TopicServiceGrpc.class) {
        if ((getSubscribeToTopicMethod = TopicServiceGrpc.getSubscribeToTopicMethod) == null) {
          TopicServiceGrpc.getSubscribeToTopicMethod = getSubscribeToTopicMethod =
              io.grpc.MethodDescriptor.<edu.scu.distributed.server.services.SubscribeRequest, edu.scu.distributed.server.services.Status>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "subscribeToTopic"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  edu.scu.distributed.server.services.SubscribeRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  edu.scu.distributed.server.services.Status.getDefaultInstance()))
              .setSchemaDescriptor(new TopicServiceMethodDescriptorSupplier("subscribeToTopic"))
              .build();
        }
      }
    }
    return getSubscribeToTopicMethod;
  }

  private static volatile io.grpc.MethodDescriptor<edu.scu.distributed.server.services.Message,
      edu.scu.distributed.server.services.Status> getPublishMessageMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "publishMessage",
      requestType = edu.scu.distributed.server.services.Message.class,
      responseType = edu.scu.distributed.server.services.Status.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<edu.scu.distributed.server.services.Message,
      edu.scu.distributed.server.services.Status> getPublishMessageMethod() {
    io.grpc.MethodDescriptor<edu.scu.distributed.server.services.Message, edu.scu.distributed.server.services.Status> getPublishMessageMethod;
    if ((getPublishMessageMethod = TopicServiceGrpc.getPublishMessageMethod) == null) {
      synchronized (TopicServiceGrpc.class) {
        if ((getPublishMessageMethod = TopicServiceGrpc.getPublishMessageMethod) == null) {
          TopicServiceGrpc.getPublishMessageMethod = getPublishMessageMethod =
              io.grpc.MethodDescriptor.<edu.scu.distributed.server.services.Message, edu.scu.distributed.server.services.Status>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "publishMessage"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  edu.scu.distributed.server.services.Message.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  edu.scu.distributed.server.services.Status.getDefaultInstance()))
              .setSchemaDescriptor(new TopicServiceMethodDescriptorSupplier("publishMessage"))
              .build();
        }
      }
    }
    return getPublishMessageMethod;
  }

  private static volatile io.grpc.MethodDescriptor<edu.scu.distributed.server.services.GetMessageRequest,
      edu.scu.distributed.server.services.Status> getGetMessagesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getMessages",
      requestType = edu.scu.distributed.server.services.GetMessageRequest.class,
      responseType = edu.scu.distributed.server.services.Status.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<edu.scu.distributed.server.services.GetMessageRequest,
      edu.scu.distributed.server.services.Status> getGetMessagesMethod() {
    io.grpc.MethodDescriptor<edu.scu.distributed.server.services.GetMessageRequest, edu.scu.distributed.server.services.Status> getGetMessagesMethod;
    if ((getGetMessagesMethod = TopicServiceGrpc.getGetMessagesMethod) == null) {
      synchronized (TopicServiceGrpc.class) {
        if ((getGetMessagesMethod = TopicServiceGrpc.getGetMessagesMethod) == null) {
          TopicServiceGrpc.getGetMessagesMethod = getGetMessagesMethod =
              io.grpc.MethodDescriptor.<edu.scu.distributed.server.services.GetMessageRequest, edu.scu.distributed.server.services.Status>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getMessages"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  edu.scu.distributed.server.services.GetMessageRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  edu.scu.distributed.server.services.Status.getDefaultInstance()))
              .setSchemaDescriptor(new TopicServiceMethodDescriptorSupplier("getMessages"))
              .build();
        }
      }
    }
    return getGetMessagesMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static TopicServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TopicServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TopicServiceStub>() {
        @java.lang.Override
        public TopicServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TopicServiceStub(channel, callOptions);
        }
      };
    return TopicServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static TopicServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TopicServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TopicServiceBlockingStub>() {
        @java.lang.Override
        public TopicServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TopicServiceBlockingStub(channel, callOptions);
        }
      };
    return TopicServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static TopicServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TopicServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TopicServiceFutureStub>() {
        @java.lang.Override
        public TopicServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TopicServiceFutureStub(channel, callOptions);
        }
      };
    return TopicServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class TopicServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void subscribeToTopic(edu.scu.distributed.server.services.SubscribeRequest request,
        io.grpc.stub.StreamObserver<edu.scu.distributed.server.services.Status> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSubscribeToTopicMethod(), responseObserver);
    }

    /**
     */
    public void publishMessage(edu.scu.distributed.server.services.Message request,
        io.grpc.stub.StreamObserver<edu.scu.distributed.server.services.Status> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getPublishMessageMethod(), responseObserver);
    }

    /**
     */
    public void getMessages(edu.scu.distributed.server.services.GetMessageRequest request,
        io.grpc.stub.StreamObserver<edu.scu.distributed.server.services.Status> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetMessagesMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSubscribeToTopicMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                edu.scu.distributed.server.services.SubscribeRequest,
                edu.scu.distributed.server.services.Status>(
                  this, METHODID_SUBSCRIBE_TO_TOPIC)))
          .addMethod(
            getPublishMessageMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                edu.scu.distributed.server.services.Message,
                edu.scu.distributed.server.services.Status>(
                  this, METHODID_PUBLISH_MESSAGE)))
          .addMethod(
            getGetMessagesMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                edu.scu.distributed.server.services.GetMessageRequest,
                edu.scu.distributed.server.services.Status>(
                  this, METHODID_GET_MESSAGES)))
          .build();
    }
  }

  /**
   */
  public static final class TopicServiceStub extends io.grpc.stub.AbstractAsyncStub<TopicServiceStub> {
    private TopicServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TopicServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TopicServiceStub(channel, callOptions);
    }

    /**
     */
    public void subscribeToTopic(edu.scu.distributed.server.services.SubscribeRequest request,
        io.grpc.stub.StreamObserver<edu.scu.distributed.server.services.Status> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSubscribeToTopicMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void publishMessage(edu.scu.distributed.server.services.Message request,
        io.grpc.stub.StreamObserver<edu.scu.distributed.server.services.Status> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getPublishMessageMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getMessages(edu.scu.distributed.server.services.GetMessageRequest request,
        io.grpc.stub.StreamObserver<edu.scu.distributed.server.services.Status> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetMessagesMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class TopicServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<TopicServiceBlockingStub> {
    private TopicServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TopicServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TopicServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public edu.scu.distributed.server.services.Status subscribeToTopic(edu.scu.distributed.server.services.SubscribeRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSubscribeToTopicMethod(), getCallOptions(), request);
    }

    /**
     */
    public edu.scu.distributed.server.services.Status publishMessage(edu.scu.distributed.server.services.Message request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getPublishMessageMethod(), getCallOptions(), request);
    }

    /**
     */
    public edu.scu.distributed.server.services.Status getMessages(edu.scu.distributed.server.services.GetMessageRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetMessagesMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class TopicServiceFutureStub extends io.grpc.stub.AbstractFutureStub<TopicServiceFutureStub> {
    private TopicServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TopicServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TopicServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<edu.scu.distributed.server.services.Status> subscribeToTopic(
        edu.scu.distributed.server.services.SubscribeRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSubscribeToTopicMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<edu.scu.distributed.server.services.Status> publishMessage(
        edu.scu.distributed.server.services.Message request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getPublishMessageMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<edu.scu.distributed.server.services.Status> getMessages(
        edu.scu.distributed.server.services.GetMessageRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetMessagesMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SUBSCRIBE_TO_TOPIC = 0;
  private static final int METHODID_PUBLISH_MESSAGE = 1;
  private static final int METHODID_GET_MESSAGES = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final TopicServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(TopicServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SUBSCRIBE_TO_TOPIC:
          serviceImpl.subscribeToTopic((edu.scu.distributed.server.services.SubscribeRequest) request,
              (io.grpc.stub.StreamObserver<edu.scu.distributed.server.services.Status>) responseObserver);
          break;
        case METHODID_PUBLISH_MESSAGE:
          serviceImpl.publishMessage((edu.scu.distributed.server.services.Message) request,
              (io.grpc.stub.StreamObserver<edu.scu.distributed.server.services.Status>) responseObserver);
          break;
        case METHODID_GET_MESSAGES:
          serviceImpl.getMessages((edu.scu.distributed.server.services.GetMessageRequest) request,
              (io.grpc.stub.StreamObserver<edu.scu.distributed.server.services.Status>) responseObserver);
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

  private static abstract class TopicServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    TopicServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return edu.scu.distributed.server.services.TopicServiceOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("TopicService");
    }
  }

  private static final class TopicServiceFileDescriptorSupplier
      extends TopicServiceBaseDescriptorSupplier {
    TopicServiceFileDescriptorSupplier() {}
  }

  private static final class TopicServiceMethodDescriptorSupplier
      extends TopicServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    TopicServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (TopicServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new TopicServiceFileDescriptorSupplier())
              .addMethod(getSubscribeToTopicMethod())
              .addMethod(getPublishMessageMethod())
              .addMethod(getGetMessagesMethod())
              .build();
        }
      }
    }
    return result;
  }
}
