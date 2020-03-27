package com.learn.grpc.crud.blog.server;

import com.learn.blog.*;
import com.learn.grpc.crud.blog.server.mongodb.MongoBlogService;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import org.bson.types.ObjectId;

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
        if(!isValidId(request.getBlogid())) {
            responseObserver.onError(Status
                    .INVALID_ARGUMENT
                    .withDescription("blog is is invalid")
                    .augmentDescription("blog id = " + request.getBlogid())
                    .asRuntimeException());

            return;
        }

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

    @Override
    public void updateBlog(BlogRequest request, StreamObserver<BlogResponse> responseObserver) {
        String blogId = request.getBlog().getBlogId();
        if(!isValidId(blogId)) {
            responseObserver.onError(Status
                    .INVALID_ARGUMENT
                    .withDescription("blog is is invalid")
                    .augmentDescription("blog id = " +blogId)
                    .asRuntimeException());

            return;
        }

        Blog updatedBlog = mongoService.updateBlog(request.getBlog());
        if(updatedBlog == null) {
            responseObserver.onError(Status
                    .NOT_FOUND
                    .withDescription("Could not find blog")
                    .augmentDescription("blog id = " + blogId)
                    .asRuntimeException());

            return;
        }

        responseObserver.onNext(createBlogResponseObject(updatedBlog));
        responseObserver.onCompleted();
    }

    private BlogResponse createBlogResponseObject(Blog blog) {
        return BlogResponse.newBuilder()
                .setBlog(blog)
                .build();
    }

    private boolean isValidId(String id) {
        try {
            new ObjectId(id);
            return true;
        } catch(Exception e) {
            return false;
        }
    }
}
