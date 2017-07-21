package jp.gihyo;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;

import mockit.Mocked;
import mockit.integration.junit4.JMockit;

@RunWith(JMockit.class)
public class ParentTest {
    
    @Mocked
    private Child child;

    @Test
    public void test1() {
        System.out.println("finalInstanceMethod() = " + child.finalInstanceMethod());
        System.out.println("instanceField = " + child.instanceField);
        System.out.println("parentInstanceField = " + child.parentInstanceField);
        System.out.println("CLASS_FIELD = " + Child.CLASS_FIELD);
        System.out.println("PARENT_CLASS_FIELD = " + Child.PARENT_CLASS_FIELD);
    }

}
