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
                .setBlogid(blogId)
                .build();

        System.out.println("Getting blog for id " + blogId);
        BlogServiceGrpc.BlogServiceBlockingStub blogService = BlogServiceGrpc.newBlockingStub(channel);

        try {
            BlogResponse blogResponse = blogService.getBlog(request);
            printBlog(blogResponse.getBlog());

            return blogResponse.getBlog();
        }catch(StatusRuntimeException e) {
            if(e.getStatus().getCode() == Status.NOT_FOUND.getCode()) {
                System.out.println("Blog not found for blog id " + blogId);
            } else {
                e.printStackTrace();
            }
        }

        return null;
    }

    private void printBlog(Blog blog) {
        System.out.println(blog.toString());
    }
}
