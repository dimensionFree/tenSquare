package com.tensquare.article.mapper;

import com.tensquare.article.entity.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CommentMapper extends MongoRepository<Comment,String > {
    List<Comment> findByArticleid(String articleId);
}
