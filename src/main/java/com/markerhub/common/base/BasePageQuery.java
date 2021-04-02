package com.markerhub.common.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author shenxr
 * @date 2021-01-21 10:56
 * @description
 **/
@Data
public abstract class BasePageQuery<T> {

    @ApiModelProperty("分页参数")
    @NotNull(message = "分页参数不能为空")
    private Pagination<T> page;

}
