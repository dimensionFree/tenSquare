package com.tensquare.app.article.service.impl;

import com.tensquare.app.article.model.Article;
import com.tensquare.app.article.dao.ArticleMapper;
import com.tensquare.app.article.service.IArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
