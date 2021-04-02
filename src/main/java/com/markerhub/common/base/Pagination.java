package com.markerhub.common.base;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author shenxr
 * @date 2021-01-05 10:26
 * @description
 **/
@Getter
@ApiModel("分页参数")
public class Pagination<T> implements IPage<T> {
    private static final long serialVersionUID = 2291766842735576412L;

    @ApiModelProperty(value = "查询数据列表", hidden = true)
    private List<T> records = Collections.emptyList();

    @ApiModelProperty(value = "总数", hidden = true)
    private long total = 0;

    @ApiModelProperty(value = "每页显示条数", required = true)
    private long size = 10;

    @ApiModelProperty(value = "当前页", required = true)
    private long current = 1;

    @ApiModelProperty(value = "排序", hidden = true)
    private final List<OrderItem> orders = new ArrayList<>();

    private List<String> asc;

    private List<String> desc;

    public Pagination() {
    }

    public Pagination(long current, long size) {
        this.current = current;
        this.size = size;
    }

    @ApiModelProperty(hidden = true)
    public void setAsc(List<String> asc) {
        if (CollUtil.isNotEmpty(asc)) {
            orders.addAll(asc.stream().distinct().map(OrderItem::asc).collect(Collectors.toList()));
        }
    }

    @ApiModelProperty(hidden = true)
    public void setDesc(List<String> desc) {
        if (CollUtil.isNotEmpty(desc)) {
            orders.addAll(desc.stream().distinct().map(OrderItem::desc).collect(Collectors.toList()));
        }
    }

    public Pagination<T> asc(String... columns) {
        this.setAsc(Arrays.asList(columns));
        return this;
    }

    public Pagination<T> desc(String... columns) {
        this.setDesc(Arrays.asList(columns));
        return this;
    }

    @Override
    public List<OrderItem> orders() {
        return orders;
    }

    @Override
    public IPage<T> setRecords(List<T> records) {
        this.records = records;
        return this;
    }


    @Override
    public IPage<T> setTotal(long total) {
        this.total = total;
        return this;
    }

    @Override
    public IPage<T> setSize(long size) {
        this.size = size;
        return this;
    }

    @Override
    public IPage<T> setCurrent(long current) {
        this.current = current;
        return this;
    }

    @Override
    @ApiModelProperty("总页数")
    public long getPages() {
        if (getSize() == 0) {
            return 0L;
        }
        long pages = getTotal() / getSize();
        if (getTotal() % getSize() != 0) {
            pages++;
        }
        return pages;
    }

    @Override
    @ApiModelProperty("总页数")
    public IPage<T> setPages(long pages) {
        return this;
    }
}
