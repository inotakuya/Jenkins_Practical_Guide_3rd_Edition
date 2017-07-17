package jp.gihyo.jenkinsbook;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.lang.reflect.Method;

import org.junit.Before;
import org.junit.Test;

import jp.gihyo.jenkinsbook.action.SampleAction;

public class SampleServletTest {

    private SampleServlet sampleServlet;

    @Before
    public void setUp() {
        sampleServlet = new SampleServlet();
    }

    @Test
    public void testCreateAction() throws Exception {
        String name = "hello";
        String hel = "hel";
        Class<SampleServlet> clazz = SampleServlet.class;
        Method method = clazz.getDeclaredMethod("createAction", String.class);
        method.setAccessible(true);
        SampleAction result = (SampleAction) method.invoke(clazz.newInstance(), name);
        assertThat(result, instanceOf(SampleAction.class));

        SampleAction result2 = (SampleAction) method.invoke(clazz.newInstance(), hel);
        assertNull(result2);

    }

}
