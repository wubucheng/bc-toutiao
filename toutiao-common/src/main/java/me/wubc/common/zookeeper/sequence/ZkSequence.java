package me.wubc.common.zookeeper.sequence;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.atomic.AtomicValue;
import org.apache.curator.framework.recipes.atomic.DistributedAtomicLong;
import org.apache.curator.retry.ExponentialBackoffRetry;


/**
 * @author wbc
 * @date 2020/02/16
 * @desc 自增ID生成器 不同自增ID类型对应有不同的实例
 **/
public class ZkSequence {

    /**
     * 主要使用到DistributedAtomicLong来原子性分布式的增长ID
     */
    private DistributedAtomicLong distributedAtomicLong;

    /**
     * 初始化重试策略
     */
    RetryPolicy retryPolicy = new ExponentialBackoffRetry(500, 3);


    /**
     * 使用到CuratorFramework客户端连接zookeeper
     *
     * @param sequenceName
     * @param client
     */
    public ZkSequence(String sequenceName, CuratorFramework client) {
        this.distributedAtomicLong = new DistributedAtomicLong(client, sequenceName, retryPolicy);
    }


    /**
     * 生成序列
     *
     * @return
     * @throws Exception
     */
    public Long sequence() throws Exception {
        AtomicValue<Long> sequence = this.distributedAtomicLong.increment();
        if (sequence.succeeded()) {
            return sequence.postValue();
        }
        return null;
    }


}
