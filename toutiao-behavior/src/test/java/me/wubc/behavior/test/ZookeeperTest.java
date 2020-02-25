package me.wubc.behavior.test;

import me.wubc.behavior.BehaviorJarApplication;
import me.wubc.common.zookeeper.sequence.Sequences;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author wbc
 * @date 2020/02/17
 * @desc
 **/
@SpringBootTest(classes = BehaviorJarApplication.class )
@RunWith(SpringRunner.class)
public class ZookeeperTest {

    @Autowired
    private Sequences sequences;

    @Test
    public void test(){
        Long aLong = sequences.sequenceApReadBehavior();
        System.out.println("id is： "+aLong);
    }
}
