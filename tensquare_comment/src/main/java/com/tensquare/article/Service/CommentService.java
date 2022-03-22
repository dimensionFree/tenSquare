package com.tensquare.article.Service;

import com.tensquare.article.entity.Comment;
import com.tensquare.article.mapper.CommentMapper;
import com.tensquare.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private CommentMapper commentMapper;

    public Comment findById(String id) {

        return commentMapper.findById(id).get();
    }

    public List<Comment> findAll() {

        return commentMapper.findAll();
    }

    public void save(Comment comment) {
        String id = idWorker.nextId() + "";
        comment.set_id(id);
        //初始化数据
        comment.setPublishdate(new Date());
        comment.setThumbup(0);
        commentMapper.save(comment);
    }

    public void update(Comment comment) {
        commentMapper.save(comment);
    }

    public void deleteById(String id) {
        commentMapper.deleteById(id);
    }
}
