package com.tensquare.article.mapper;

import com.tensquare.article.entity.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentMapper extends MongoRepository<Comment,String > {
}
