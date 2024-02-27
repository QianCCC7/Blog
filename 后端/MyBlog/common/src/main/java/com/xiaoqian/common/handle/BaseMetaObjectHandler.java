package com.xiaoqian.common.handle;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.xiaoqian.common.domain.pojo.User;
import com.xiaoqian.common.utils.UserContext;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;

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
        Long userId;
        try {
            // 当用户处于注册状态时，仍然需要字段填充 createBy字段，该字段会从 principal中获取
            // 而注册的用户的 principal为字符串 "anonymousUser"，无法转换为 LoginUser对象，所以会爆错
            // 此时只需要在字段填充时捕获一下异常，并且重新赋值 userId即可
            userId = UserContext.getUserId();
        } catch (Exception e) {
            userId = -1L;
            e.printStackTrace();
        }
        this.setFieldValByName("createTime", LocalDateTime.now(), metaObject);
        this.setFieldValByName("createBy", userId, metaObject);
        this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        this.setFieldValByName("updateBy", userId, metaObject);
    }

    public void setUpdater(MetaObject metaObject) {
        Long userId;
        try {
            // 当定时任务执行更新浏览量的操作时不需要 token，也就没有 Authentication，此时默认 userId = -1
            Authentication authentication = UserContext.getAuthentication();
            if (Objects.isNull(authentication)) {
                userId = -1L;
            } else {
                // 当用户处于注册状态时，仍然需要字段填充 createBy字段，该字段会从 principal中获取
                // 而注册的用户的 principal为字符串 "anonymousUser"，无法转换为 LoginUser对象，所以会爆错
                // 此时只需要在字段填充时捕获一下异常，并且重新赋值 userId即可
                userId = UserContext.getUserId();
            }
        } catch (Exception e) {
            userId = -1L;
            e.printStackTrace();
        }
        this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        this.setFieldValByName("updateBy", userId, metaObject);
    }
}
