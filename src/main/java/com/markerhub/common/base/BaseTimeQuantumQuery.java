package com.markerhub.common.base;

import cn.hutool.core.date.DateUtil;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author xiams
 * @email 15555470513@163.com
 * @date 2020/10/29 18:55
 */

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class BaseTimeQuantumQuery<T> extends BasePageQuery<T> {

    @ApiModelProperty("开始时间")
    private Date startTime;

    @ApiModelProperty("结束时间")
    private Date endTime;

    public Date getStartTime() {
        return startTime == null ? null : DateUtil.beginOfDay(startTime);
    }

    public Date getEndTime() {
        return endTime == null ? null : DateUtil.endOfDay(endTime);
    }
}
