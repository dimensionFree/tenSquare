package com.tensquare.app.article.service.impl;

import com.tensquare.app.article.model.Tb_articlePo;
import com.tensquare.app.article.dao.Tb_articleMapper;
import com.tensquare.app.article.service.ITb_articleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文章 服务实现类
 * </p>
 *
 * @author dx
 * @since 2022-03-20
 */
@Service
public class Tb_articleServiceImpl extends ServiceImpl<Tb_articleMapper, Tb_articlePo> implements ITb_articleService {

}
