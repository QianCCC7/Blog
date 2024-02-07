package com.xiaoqian.common.handle;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.xiaoqian.common.utils.UserContext;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 操作数据库前自动填充需要更新的内容，只支持单个对象，不支持批量插入更新时的填充
 */
@Component
public class BaseMetaObjectHandler implements MetaObjectHandler {
    /**
     * 只要对数据库执行了插入语句，那么就会执行到这个方法
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        setCreator(metaObject);
        setUpdater(metaObject);
    }

    /**
     * 只要对数据库执行了更新语句，那么就会执行到这个方法
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        setUpdater(metaObject);
    }

    public void setCreator(MetaObject metaObject) {
        Long userId = UserContext.getUserId();
        this.setFieldValByName("createTime", LocalDateTime.now(), metaObject);
        this.setFieldValByName("createBy",userId , metaObject);
        this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        this.setFieldValByName("updateBy", userId, metaObject);
    }

    public void setUpdater(MetaObject metaObject) {
        Long userId = UserContext.getUserId();
        this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        this.setFieldValByName("updateBy", userId, metaObject);
    }
}
