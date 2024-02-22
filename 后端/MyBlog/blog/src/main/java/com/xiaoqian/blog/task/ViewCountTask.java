package com.xiaoqian.blog.task;

import com.xiaoqian.common.constants.RedisConstants;
import com.xiaoqian.common.domain.pojo.Article;
import com.xiaoqian.common.service.IArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 更新浏览量的定时任务
 */
@SuppressWarnings({"unchecked", "rawtypes"})
@Component
@RequiredArgsConstructor
@Slf4j
public class ViewCountTask {
    private final RedisTemplate redisTemplate;
    private final IArticleService articleService;

    /**
     * 更新浏览量的定时任务，时间间隔为 30s
     */
    @Scheduled(cron = "0/15 * * * * ?")
    public void UpdateTask() {
        log.info("Redis正在同步文章浏览器至数据库...");
        // 1. 获取Redis中文章的浏览量
        Map<String, Integer> map = redisTemplate.opsForHash().entries(RedisConstants.VIEW_COUNT_KEY);
        if (CollectionUtils.isEmpty(map)) {
            return;
        }
        // 2. 更新数据库
        List<Article> articleList = map.entrySet().stream()
                .map(entry -> new Article().setId(Long.valueOf(entry.getKey())).setViewCount(Long.valueOf(entry.getValue())))
                .collect(Collectors.toList());
        articleService.updateBatchById(articleList);
    }
}
