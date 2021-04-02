package com.markerhub.common.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author shenxr
 * @date 2020-06-01 17:56
 * @description
 **/
@Getter
@Setter
public abstract class BaseOperateEntity extends BaseIdEntity {

    private static final long serialVersionUID = 2665505293908816391L;

    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty("创建人")
    @TableField(fill = FieldFill.INSERT)
    private Long createBy;

    @ApiModelProperty("更新时间")
    @TableField(fill = FieldFill.UPDATE)
    @JsonIgnore
    private Date updateTime;

    @ApiModelProperty("更新人")
    @TableField(fill = FieldFill.UPDATE)
    @JsonIgnore
    private Long updateBy;

    public void clearOperate() {
        this.createTime = this.updateTime = null;
        this.createBy = this.updateBy = null;
    }
}
