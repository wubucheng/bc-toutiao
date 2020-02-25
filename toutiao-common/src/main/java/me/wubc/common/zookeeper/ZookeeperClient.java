package me.wubc.common.zookeeper;

import com.google.common.collect.Maps;
import lombok.Data;
import me.wubc.common.zookeeper.sequence.ZkSequence;
import me.wubc.common.zookeeper.sequence.ZkSequenceEnum;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Map;

/**
 * @author wbc
 * @date 2020/02/16
 * @desc
 **/
@Data
public class ZookeeperClient {

    private String host;
    private String sequencePath;

    // 重试休眠时间
    private final int SLEEP_TIME_MS = 1000;
    // 最大重试1000次
    private final int MAX_RETRIES = 1000;
    //会话超时时间
    private final int SESSION_TIMEOUT = 30 * 1000;
    //连接超时时间
    private final int CONNECTION_TIMEOUT = 3 * 1000;

    private CuratorFramework client;

    private Map<String, ZkSequence> zkSequenceMap = Maps.newConcurrentMap();

    public ZookeeperClient(String host, String sequencePath) {
        this.host = host;
        this.sequencePath = sequencePath;
    }

    @PostConstruct
    public void init() {
        this.client = CuratorFrameworkFactory.builder()
                .connectString(this.host)
                .connectionTimeoutMs(CONNECTION_TIMEOUT)
                .sessionTimeoutMs(SESSION_TIMEOUT)
                .retryPolicy(new ExponentialBackoffRetry(SLEEP_TIME_MS, MAX_RETRIES))
                .build();
        // start()方法来完成创建客户端
        this.client.start();
        System.out.println("已连接zookeeper服务");
        this.initZkSequence();
    }

    /**
     * 初始化节点路径，并加入到map中:目的是每个节点都有对应的zksequence实例操作
     */
    public void initZkSequence() {
        Arrays.stream(ZkSequenceEnum.values())
                .forEach(zkSequenceEnum -> {
                    // 拼接节点路径
                    String name = zkSequenceEnum.name();
                    String path = this.sequencePath + name;
                    ZkSequence zkSequence = new ZkSequence(path, this.client);
                    zkSequenceMap.put(name, zkSequence);
                });
    }

    /**
     * 获取自增ID
     */
    public Long sequence(ZkSequenceEnum zkSequenceEnum) {
        try {
            ZkSequence zkSequence = zkSequenceMap.get(zkSequenceEnum.name());
            if (zkSequence != null) {
                Long sequence = zkSequence.sequence();
                return sequence;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
