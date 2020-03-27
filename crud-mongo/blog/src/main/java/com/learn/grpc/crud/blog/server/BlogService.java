package com.learn.grpc.crud.blog.server;

import com.learn.blog.*;
import com.learn.grpc.crud.blog.server.mongodb.MongoBlogService;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;

public class BlogService extends BlogServiceGrpc.BlogServiceImplBase {
    MongoBlogService mongoService = new MongoBlogService();

    @Override
    public void createBlog(BlogRequest request, StreamObserver<BlogResponse> responseObserver) {
        Blog newBlog = mongoService.addBlog(request.getBlog());

        responseObserver.onNext(createBlogResponseObject(newBlog));

        responseObserver.onCompleted();
    }

    @Override
    public void getBlog(BlogByIdRequest request, StreamObserver<BlogResponse> responseObserver) {
        Blog blog = mongoService.getBlogById(request.getBlogid());
        if(blog == null) {
            responseObserver.onError(Status
                    .NOT_FOUND
                    .withDescription("Could not find blog")
                    .augmentDescription("blog id = " + request.getBlogid())
                    .asRuntimeException());

            return;
        }

        responseObserver.onNext(createBlogResponseObject(blog));
        responseObserver.onCompleted();
    }

    private BlogResponse createBlogResponseObject(Blog blog) {
        return BlogResponse.newBuilder()
                .setBlog(blog)
                .build();
    }
}
