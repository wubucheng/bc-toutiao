package me.wubc.article.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * mysql初始化扫描配置类
 */
@Configuration
@ComponentScan("me.wubc.common.mysql.core")
public class MysqlConfig {
}
