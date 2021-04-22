package com.markerhub.controller;


import cn.hutool.core.lang.Assert;
import com.markerhub.common.base.Pagination;
import com.markerhub.common.lang.Result;
import com.markerhub.common.validator.group.InsertGroup;
import com.markerhub.entity.Blog;
import com.markerhub.service.BlogService;
import com.markerhub.utils.UserUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ce
 * @since 2021-03-03
 */
@Slf4j
@RestController
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    BlogService blogService;

    @PostMapping("/save")
    @RequiresAuthentication
    public Result<Blog> add(@Validated(InsertGroup.class) @RequestBody Blog param) {
        return Result.success(blogService.addBlog(param));
    }

    @GetMapping("/list/{current}/{size}")
    public Result<Pagination<Blog>> list(@PathVariable("current") Long current, @PathVariable("size") Long size) {
        Pagination<Blog> page = new Pagination<>(current, size);
        return Result.success(blogService.query(current,size,page));
    }

    @GetMapping("/query/{id}")
    public Result<Blog> query(@PathVariable("id") Long id) {
        Blog blog = blogService.getById(id);
        Assert.notNull(blog, "博客不存在");
        return Result.success(blog);
    }

    @GetMapping("/delete/{id}/")
    public Result<?> delete(@PathVariable("id") Long id) {
        Blog blog = blogService.getById(id);
        Assert.notNull(blog, "博客不存在");
        return Result.success(blogService.removeById(id));
    }

    @PutMapping("update")
    public Result<?> update(@RequestBody Blog param) {
        Long id = param.getId();
        Blog blog = blogService.getById(id);
        Assert.notNull(blog, "博客不存在");
        Assert.isTrue(param.getId().equals(UserUtils.getUserIdNotNull()),"没有编辑权限");
        return Result.success(blogService.updateById(param));
    }

}
