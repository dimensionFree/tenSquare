package com.tensquare.article.Service;

import com.tensquare.article.entity.Comment;
import com.tensquare.article.mapper.CommentMapper;
import com.tensquare.entity.Result;
import com.tensquare.entity.StatusCode;
import com.tensquare.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private RedisTemplate redisTemplate;


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

    public List<Comment> findByarticleId(String articleId) {
        return commentMapper.findByArticleid(articleId);
    }

    public Result thumbup(String id) {
            ////查询评论
    //Comment comment = commentDao.findById(id).get();
    ////修改点赞数
    //comment.setThumbup(comment.getThumbup() + 1);
    //commentDao.save(comment);
    //修改条件
        String userId="123";
        Object result = redisTemplate.opsForValue().get("thumpup_" + userId + "_" + id);
        if (result!=null){
            return new Result(false, StatusCode.REMOTEERROR, "不能重复点赞");
        }
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
    //修改的数据
        Update update = new Update();
    //在原来的基础上加一
        update.inc("thumbup", 1);
        mongoTemplate.updateFirst(query, update, "comment");
        redisTemplate.opsForValue().set("thumpup_" + userId + "_" + id,true);
        return new Result(true, StatusCode.OK, "点赞成功",findById(id).getThumbup());
    }
}
