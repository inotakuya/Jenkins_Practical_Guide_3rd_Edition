package jp.gihyo;

import org.junit.Test;
import org.junit.runner.RunWith;

import mockit.Expectations;
import mockit.Mocked;
import mockit.Tested;
import mockit.integration.junit4.JMockit;

@RunWith(JMockit.class)
public class HogeTest2 {

    @Tested
    private Hoge hoge;
    
    @Test
    public void test1() throws Exception {
        System.out.println(this.hoge);
    }
}
