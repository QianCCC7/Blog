package com.xiaoqian.blog.handler;

import com.xiaoqian.common.constants.RedisConstants;
import com.xiaoqian.common.domain.pojo.Article;
import com.xiaoqian.common.service.IArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 程序启动时的初始化任务处理器
 */
@SuppressWarnings({"unchecked", "rawtypes"})
@Component
@RequiredArgsConstructor
@Slf4j
public class InitializeTaskHandler implements CommandLineRunner {
    private final RedisTemplate redisTemplate;
    private final IArticleService articleService;

    /**
     * 将数据库文章的浏览量数据同步到 Redis
     */
    @Override
    public void run(String... args) {
        log.info("正在初始化博客文章浏览量...");
        List<Article> list = articleService.list();
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        Map<String, Integer> map = list.stream()
                .collect(Collectors.toMap(article -> String.valueOf(article.getId()),
                                        article -> article.getViewCount().intValue()));
        redisTemplate.opsForHash().putAll(RedisConstants.VIEW_COUNT_KEY, map);
    }
}
