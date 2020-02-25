package me.wubc.common.zookeeper.sequence;

import me.wubc.common.zookeeper.ZookeeperClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wbc
 * @date 2020/02/17
 * @desc
 **/
@Component
public class Sequences {

    @Autowired
    private ZookeeperClient client;

    public Long sequenceApLikes() {
        return client.sequence(ZkSequenceEnum.AP_LIKES);
    }

    public Long sequenceApReadBehavior() {
        return this.client.sequence(ZkSequenceEnum.AP_READ_BEHAVIOR);
    }

    public Long sequenceApCollection() {
        return this.client.sequence(ZkSequenceEnum.AP_COLLECTION);
    }

    public Long sequenceApUserFollow() {
        return this.client.sequence(ZkSequenceEnum.AP_USER_FOLLOW);
    }

    public Long sequenceApUserFan() {
        return this.client.sequence(ZkSequenceEnum.AP_USER_FAN);
    }


}
