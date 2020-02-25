package me.wubc.common.zookeeper;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author wbc
 * @date 2020/02/16
 * @desc
 **/
@Data
@Configuration
@ConfigurationProperties(prefix = "zk")
@PropertySource("classpath:zookeeper.properties")
public class ZkConfig {

    private String host;
    private String sequencePath;

    /**
     * 初始化zookeeperClient，交给spring管理
     *
     * @return
     */
    @Bean
    public ZookeeperClient zookeeperClient() {
        return new ZookeeperClient(this.host, this.sequencePath);
    }
}
