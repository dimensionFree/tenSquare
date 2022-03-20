package com.tensquare.app.article.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tensquare.app.article.model.Article;
import com.tensquare.app.article.service.IArticleService;
import com.tensquare.entity.PageResult;
import com.tensquare.entity.Result;
import com.tensquare.entity.StatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    @ApiOperation(value="创建文章", notes="根据article对象创建文章")
//    @ApiImplicitParam(name = "article", value = "文章详细实体article", required = true, dataType = "Article对象")
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Article article) {
        articleService.add(article);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    //修改标签数据接口
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ApiOperation(value="更新文章", notes="根据article对象创建文章")
//    @ApiImplicitParam(name = "article", value = "文章详细实体article", required = true, dataType = "Article对象")
    public Result update(@PathVariable String id, @RequestBody Article article) {
        article.setId(id);
        articleService.myUpdate(article);
        return new Result(true, StatusCode.OK, "修改成功");
    }


    //删除标签数据接口
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value="删除文章", notes="根据article对象创建文章")
//    @ApiImplicitParam(name = "article", value = "文章详细实体article", required = true, dataType = "Article对象")
    public Result delete(@PathVariable String id) {

        articleService.removeById(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }


    @RequestMapping(value="/search/{page}/{size}", method =
            RequestMethod.POST)
    public Result search(@RequestBody Map map, @PathVariable
            int page, @PathVariable int size) {
        Page page1 = articleService.search(map, page, size);
        return new Result(true, StatusCode.OK, "查询成功", new
                PageResult((int) page1.getTotal(), page1.getRecords()));
    }


    @RequestMapping(value="/exception", method = RequestMethod.GET)
    public Result exception() throws Exception {
        throw new Exception("测试统一异常处理");
    }

}

