package com.tensquare.app.article.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tensquare.app.article.model.Article;
import com.tensquare.app.article.dao.ArticleMapper;
import com.tensquare.app.article.service.IArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tensquare.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 文章 服务实现类
 * </p>
 *
 * @author freedom
 * @since 2022-03-20
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {
    @Autowired
    IdWorker idWorker;

    public void add(Article article){
        article.setId(idWorker.nextId()+"");
        baseMapper.insert(article);
    }

    @Override
    public void myUpdate(Article article) {

        //1.
//        baseMapper.updateById(article);


        //2.
        QueryWrapper wrapper=new QueryWrapper<Article>();
        wrapper.eq("id",article.getId());
        baseMapper.update(article,wrapper);

    }

    @Override
    public Page search(Map map, int page, int size) {
        //设置查询条件
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        Set<String> keySet = map.keySet();
        for (String key : keySet) {
            // if (map.get(key) != null) {
            //     wrapper.eq(key, map.get(key));
            // }

            //第一个参数是否把后面的条件加入到查询条件中
            //和上面的if判断的写法是一样的效果，实现动态sql
            wrapper.eq(map.get(key) != null, key, map.get(key));
        }

        //设置分页参数
        Page<Article> pageData = new Page<>(page, size);

        //执行查询
        //第一个是分页参数，第二个是查询条件

        IPage<Article> articleIPage = baseMapper.selectPage(pageData, wrapper);

        pageData.setRecords(articleIPage.getRecords());

        //返回
        return pageData;
    }




}
