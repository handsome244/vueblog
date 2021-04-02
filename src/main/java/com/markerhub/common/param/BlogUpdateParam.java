package com.markerhub.common.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Admin
 * @Description
 * @date 2021/3/10 17:13
 */
@Data
public class BlogUpdateParam {

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("状态")
    private Integer status;
}
