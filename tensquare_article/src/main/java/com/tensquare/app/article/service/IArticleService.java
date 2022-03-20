package com.tensquare.app.article.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tensquare.app.article.model.Article;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 文章 服务类
 * </p>
 *
 * @author freedom
 * @since 2022-03-20
 */
public interface IArticleService extends IService<Article> {
    public void add(Article article);

    void myUpdate(Article article);

    Page search(Map map, int page, int size);
}
