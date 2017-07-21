package jp.gihyo;

import static org.hamcrest.CoreMatchers.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import mockit.Expectations;
import mockit.Mocked;
import mockit.Tested;
import mockit.integration.junit4.JMockit;

@RunWith(JMockit.class)
public class HogeTest3 {

    @Mocked
    private Hoge hoge;

    @Test
    public void test1() throws Exception {
        new Expectations() {
            {
                hoge.method();
                List<String> list = Arrays.asList("a", "b", "c");
                result = list;
            }
        };

        System.out.println("hoge.method() = " + hoge.method());
        System.out.println("hoge.method() = " + hoge.method());
        System.out.println("hoge.method() = " + hoge.method());
        System.out.println("hoge.method() = " + hoge.method());
    }

    @Test
    public void test2() {
        new Expectations() {
            {
                hoge.method("hoge");
                result = "mock";
            }
        };
        System.out.println("hoge.method(\"hoge\") = " + hoge.method("hoge"));
        System.out.println("hoge.method(\"fuga\") = " + hoge.method("fuga"));
    }

    @Test
    public void test3() {
        new Expectations() {
            {
                hoge.method(anyString);
                result = "1";
                result = "2";
            }
        };
        System.out.println("hoge.method(\"hoge\") = " + hoge.method("hoge"));
        System.out.println("hoge.method(\"fuga\") = " + hoge.method("fuga"));
    }

    @Test
    public void test4() {
        new Expectations() {
            {
                hoge.method((String)withAny("hoge"));
                result = "mock";
            }
        };
        System.out.println("hoge.method(\"test\") = " + hoge.method("any string"));
    }
}
