package com.markerhub.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.markerhub.common.base.Pagination;
import com.markerhub.entity.Blog;
import com.markerhub.mapper.BlogMapper;
import com.markerhub.service.BlogService;
import com.markerhub.utils.UserUtils;
import org.springframework.stereotype.Service;

import static com.markerhub.common.base.BaseIdEntity.STATUS_NORMAL;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ce
 * @since 2021-03-03
 */
@Service("blogService")
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

    @Override
    public Pagination<Blog> query(Integer current, Integer size, Pagination<Blog> page) {
        LambdaQueryWrapper<Blog> wrapper = Wrappers.lambdaQuery();
        wrapper.orderByDesc(Blog::getCreateTime);
        return page(page, wrapper);
    }

    @Override
    public Blog addBlog(Blog param) {
        Blog blog = new Blog();
        blog.setContent(param.getContent());
        blog.setDescription(param.getDescription());
        blog.setTitle(param.getTitle());
        blog.setUserId(UserUtils.getUserIdNotNull());
        blog.setStatus(STATUS_NORMAL);
        save(blog);
        return blog;
    }
}
