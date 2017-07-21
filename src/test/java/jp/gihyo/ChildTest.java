package jp.gihyo;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;

import mockit.Mocked;
import mockit.integration.junit4.JMockit;

@RunWith(JMockit.class)
public class ChildTest {

    @Mocked
    private Child mock;

    @Test
    public void test() {
        Child child = new Child();
        System.out.println("child.instanceMethod() = " + child.instanceMethod());

        Parent parent = new Parent();
        System.out.println("parent.parentInstanceMethod() = " + parent.parentInstanceMethod());

        System.out.println("(this.mock == child) = " + (this.mock == child));
    }

}
