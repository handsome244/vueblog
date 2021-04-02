package com.markerhub.common.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author shenxr
 * @date 2020-06-01 17:57
 * @description
 **/
@Getter
@Setter
public abstract class BaseLogicEntity extends BaseOperateEntity {

    private static final long serialVersionUID = -2804679585685232269L;

    @TableLogic(value = "0", delval = "1")
    @TableField(fill = FieldFill.INSERT)
    @JsonIgnore
    @ApiModelProperty("删除标记（0-未删除，1-已删除）")
    private Integer deleteFlag;
}
