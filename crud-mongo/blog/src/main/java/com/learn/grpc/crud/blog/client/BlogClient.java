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
        Blog blog = blogService.createBlogObject("blog title 1", "This is my blog content");
        Blog newBlog = blogService.addBlog(blog);

        // reading blog
        // reading non exiting blog
        blogService.getBlogById("ffff3f57f401191e84e2cb7a");
        // reading invalid blog
        blogService.getBlogById("invalid id");
        // reading blog by id
        blogService.getBlogById(newBlog.getBlogId());

        // updating blog
        Blog updatBlog = newBlog.toBuilder().setTitle(newBlog.getTitle() + "  UPDATED").build();
        blogService.updateBlog(updatBlog);
        // update invalid blog id
        updatBlog = newBlog.toBuilder().setBlogId("invalid id").build();
        blogService.updateBlog(updatBlog);
        // updating blog by non exiting id
        updatBlog = newBlog.toBuilder().setBlogId("ffff3f57f401191e84e2cb7a").build();
        blogService.updateBlog(updatBlog);

        channel.shutdown();
    }


}
