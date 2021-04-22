package com.markerhub.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.markerhub.common.base.Pagination;
import com.markerhub.entity.Blog;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ce
 * @since 2021-03-03
 */
public interface BlogService extends IService<Blog> {

    /**
     * 查询博客列表
     * @param current
     * @param size
     * @param page
     * @return
     */
    Pagination<Blog> query(Long current, Long size, Pagination<Blog> page);

    /**
     * 新增博客
     * @param param
     * @return
     */
    Blog addBlog(Blog param);

}
