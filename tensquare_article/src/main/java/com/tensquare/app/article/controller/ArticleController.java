package com.tensquare.app.article.controller;


import com.tensquare.app.article.model.Article;
import com.tensquare.app.article.service.IArticleService;
import com.tensquare.entity.Result;
import com.tensquare.entity.StatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 文章 前端控制器
 * </p>
 *
 * @author freedom
 * @since 2022-03-20
 */
@RestController
@RequestMapping("/article")
@Api(value = "article Controller", tags = { "article api" })
public class ArticleController {
    @Autowired
    IArticleService articleService;


    @ApiOperation(value="查询文章", notes="查询所有文章")
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll(){
        List<Article> list = articleService.list();
        return new Result(true, StatusCode.OK,"查询成功",list);
    }

    @ApiOperation(value="查询文章1", notes="根据id查询文章")

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result findById(@PathVariable String id) {
        System.out.println(id);

        Article article = articleService.getById(id);
        System.out.println(article);
        return new Result(true, StatusCode.OK, "查询成功",
                article);
    }

    //新增标签数据接口
    @ApiOperation(value="创建用户", notes="根据User对象创建用户")
    @ApiImplicitParam(name = "article", value = "文章详细实体article", required = true, dataType = "Article对象")
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Article article) {
        articleService.save(article);
        return new Result(true, StatusCode.OK, "添加成功");
    }

}

