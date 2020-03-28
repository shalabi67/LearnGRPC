package com.learn.grpc.crud.blog.client;

import com.learn.blog.*;
import io.grpc.ManagedChannel;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;

public class BlogService {
    private ManagedChannel channel;
    public BlogService(ManagedChannel channel) {
        this.channel = channel;
    }

    public Blog createBlogObject(String title, String content) {
        return Blog.newBuilder()
                .setContent(content)
                .setTitle(title)
                .build();
    }
    public Blog addBlog(Blog blog) {
        BlogRequest blogRequest = BlogRequest.newBuilder()
                .setBlog(blog)
                .build();

        System.out.println("Adding blog");
        BlogServiceGrpc.BlogServiceBlockingStub blogService = BlogServiceGrpc.newBlockingStub(channel);
        BlogResponse blogResponse = blogService.createBlog(blogRequest);

        Blog newBlog =  blogResponse.getBlog();
        printBlog(newBlog);

        return newBlog;
    }

    public Blog getBlogById(String blogId) {
        BlogByIdRequest request = BlogByIdRequest.newBuilder()
                .setBlogId(blogId)
                .build();

        System.out.println("Getting blog for id " + blogId);
        BlogServiceGrpc.BlogServiceBlockingStub blogService = BlogServiceGrpc.newBlockingStub(channel);

        try {
            BlogResponse blogResponse = blogService.getBlog(request);
            printBlog(blogResponse.getBlog());

            return blogResponse.getBlog();
        }catch(StatusRuntimeException e) {
            manageException(e, blogId);
        }

        return null;
    }

    Blog updateBlog(Blog blog) {
        BlogRequest blogRequest = BlogRequest.newBuilder()
                .setBlog(blog)
                .build();

        System.out.println("Updating blog");
        BlogServiceGrpc.BlogServiceBlockingStub blogService = BlogServiceGrpc.newBlockingStub(channel);

        try {
            BlogResponse blogResponse = blogService.updateBlog(blogRequest);
            printBlog(blogResponse.getBlog());

            return blogResponse.getBlog();
        }catch(StatusRuntimeException e) {
            manageException(e, blog.getBlogId());
        }

        return null;
    }

    public Blog deleteBlogById(String blogId) {
        BlogByIdRequest request = BlogByIdRequest.newBuilder()
                .setBlogId(blogId)
                .build();

        System.out.println("Deleting blog for id " + blogId);
        BlogServiceGrpc.BlogServiceBlockingStub blogService = BlogServiceGrpc.newBlockingStub(channel);

        try {
            BlogResponse blogResponse = blogService.deleteBlogById(request);
            System.out.println("deleted blog");
            printBlog(blogResponse.getBlog());

            return blogResponse.getBlog();
        }catch(StatusRuntimeException e) {
            manageException(e, blogId);
        }

        return null;
    }

    private void printBlog(Blog blog) {
        System.out.println(blog.toString());
    }

    private void manageException(StatusRuntimeException e, String blogId) {
        if(e.getStatus().getCode() == Status.NOT_FOUND.getCode()) {
            System.out.println("Blog not found for blog id " + blogId);
        } else if(e.getStatus().getCode() == Status.INVALID_ARGUMENT.getCode()) {
            System.out.println("Blog id is invalid" + blogId);
        } else {
            e.printStackTrace();
        }
    }
}
