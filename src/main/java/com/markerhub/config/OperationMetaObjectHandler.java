package com.markerhub.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.markerhub.common.base.BaseIdEntity;
import com.markerhub.utils.UserUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author shenxr
 * @date 2020-05-22 15:52
 * @description
 **/
@Slf4j
@Component
public class OperationMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        if (metaObject.hasSetter("createTime")) {
            this.fillStrategy(metaObject, "createTime", new Date());
        }
        if (metaObject.hasSetter("createBy")) {
            this.fillStrategy(metaObject, "createBy", UserUtils.getUserId().orElse(BaseIdEntity.DEFAULT_USER_ID));
        }
        if (metaObject.hasSetter("deleteFlag")) {
            this.fillStrategy(metaObject, "deleteFlag", BaseIdEntity.DELETE_FLAG_NORMAL);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        if (metaObject.hasSetter("updateTime")) {
            this.fillStrategy(metaObject, "updateTime", new Date());
        }
        if (metaObject.hasSetter("updateBy")) {
            this.fillStrategy(metaObject, "updateBy", UserUtils.getUserId().orElse(BaseIdEntity.DEFAULT_USER_ID));
        }
    }
}
