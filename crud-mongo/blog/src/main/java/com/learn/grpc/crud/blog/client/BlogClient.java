package com.learn.grpc.crud.blog.client;

import com.learn.blog.Blog;
import com.learn.grpc.crud.blog.Connection;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class BlogClient {
    public static void main(String[] args) {
        System.out.println("Blog Client");

        ManagedChannel channel = ManagedChannelBuilder
                .forAddress(Connection.SERVER_URL, Connection.PORT)
                .usePlaintext()
                .build();

        BlogService blogService = new BlogService(channel);

        // adding blog
        Blog blog = blogService.createBlogObject("blog title", "This is my blog content");
        Blog newBlog = blogService.addBlog(blog);

        // finding non exiting blog
        blogService.getBlogById("ffff3f57f401191e84e2cb7a");

        // finding blog by id
        blogService.getBlogById(newBlog.getBlogId());

        channel.shutdown();
    }

}
