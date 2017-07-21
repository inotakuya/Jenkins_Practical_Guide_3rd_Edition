package jp.gihyo;

import org.junit.Test;
import org.junit.runner.RunWith;

import mockit.Expectations;
import mockit.Mocked;
import mockit.integration.junit4.JMockit;

@RunWith(JMockit.class)
public class HogeTest {

    @Mocked
    private Hoge hoge;
    
    @Test
    public void test1() throws Exception {
        System.out.println(this.hoge.getFuga());
        System.out.println(this.hoge.getString());
        System.out.println(this.hoge.getObject());
        System.out.println(this.hoge.getCollection());
        System.out.println(this.hoge.getInt());
    }

    @Test
    public void test2() {
        new Expectations() {
            {
                hoge.getFuga();
                result = null;
            }
        };
        System.out.println(this.hoge.getFuga());
    }
    
    

}
