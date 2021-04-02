package com.markerhub.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.markerhub.common.base.BaseLogicEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author ce
 * @since 2021-03-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("m_blog")
@ApiModel("博客实体类")
public class Blog extends BaseLogicEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户id")
    private Long userId;

    @NotEmpty
    @ApiModelProperty("标题")
    private String title;

    @NotEmpty
    @ApiModelProperty("描述")
    private String description;

    @NotEmpty
    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("状态")
    private Integer status;


}
