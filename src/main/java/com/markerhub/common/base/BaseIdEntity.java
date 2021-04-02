package com.markerhub.common.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.markerhub.common.validator.group.InsertGroup;
import com.markerhub.common.validator.group.UpdateGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;

/**
 * @author shenxr
 * @date 2020-06-01 17:54
 * @description
 **/
@Getter
@Setter
public abstract class BaseIdEntity implements Serializable {
    private static final long serialVersionUID = 8852971869465905371L;

    /**
     * 正常禁用状态
     */
    public static final Integer STATUS_NORMAL = 0;
    public static final Integer STATUS_DISABLED = 1;

    /**
     * 删除标记
     */
    public static final Integer DELETE_FLAG_NORMAL = 0;
    public static final Integer DELETE_FLAG_DELETED = 1;

    /**
     * 布尔值
     */
    public static final Integer BOOLEAN_FALSE = 0;
    public static final Integer BOOLEAN_TRUE = 1;
    /**
     * 默认填充的用户ID
     */
    public static final Long DEFAULT_USER_ID = -1L;

    /**
     * 审核状态（0-未审核，1-审核通过，2-审核不通过）
     */
    public static final Integer AUDIT_STATUS_UNAUDITED = 0;
    public static final Integer AUDIT_STATUS_PASS = 1;
    public static final Integer AUDIT_STATUS_NOT_PASS = 2;
    /**
     * 初始版本号
     */
    public static final Integer INITIAL_VERSION = 1;


    @TableId(type = IdType.AUTO)
    @ApiModelProperty("主键")
    @NotNull(message = "ID不能为空", groups = UpdateGroup.class)
    @Null(message = "ID必须为空", groups = InsertGroup.class)
    private Long id;
}
