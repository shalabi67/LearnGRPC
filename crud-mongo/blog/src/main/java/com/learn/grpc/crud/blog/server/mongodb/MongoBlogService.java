package com.learn.grpc.crud.blog.server.mongodb;

import com.learn.blog.Blog;
import com.learn.grpc.crud.blog.Connection;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.List;

public class MongoBlogService {
    private static final String ID = "_id";
    private static final String TITLE = "title";
    private static final String CONTENT = "content";
    private MongoClient mongoClient = MongoClients.create(Connection.MONGO_URL);
    private MongoDatabase blogDatabase = mongoClient.getDatabase("blogData");
    private MongoCollection<Document> blogsTable = blogDatabase.getCollection("blogs");

    private Document createDocument(Blog blog) {
        Document document = new Document(TITLE, blog.getTitle())
                .append(CONTENT, blog.getContent());

        return document;
    }

    public Blog createBlog(Document document) {
        Blog blog = Blog.newBuilder()
                .setBlogId(document.getObjectId(ID).toString())
                .setTitle(document.getString(TITLE))
                .setContent(document.getString(CONTENT))
                .build();

        return blog;
    }

    public Blog addBlog(Blog blog) {
        Document document = createDocument(blog);
        blogsTable.insertOne(document);

        return createBlog(document);
    }

    public Blog getBlogById(String blogId) {
        Document blogDocument = blogsTable.find(Filters.eq(ID, new ObjectId(blogId))).first();
        if(blogDocument != null) {
            return createBlog(blogDocument);
        }

        return null;
    }

    public Blog updateBlog(Blog blog) {
        Document document = createDocument(blog);
        //document.append(ID, blog.getBlogId());

        Document updatedDocument = blogsTable.findOneAndReplace(Filters.eq(ID, new ObjectId(blog.getBlogId())), document);
        if(updatedDocument == null) {
            return null;
        }
        return createBlog(updatedDocument);
    }

    public Blog deleteBlogById(String blogId) {
        Document blogDocument = blogsTable.findOneAndDelete(Filters.eq(ID, new ObjectId(blogId)));
        if(blogDocument != null) {
            return createBlog(blogDocument);
        }

        return null;
    }

    public FindIterable<Document> find() {
        return blogsTable.find();
    }
}
