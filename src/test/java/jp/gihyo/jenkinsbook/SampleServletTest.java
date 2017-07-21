package jp.gihyo.jenkinsbook;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNull;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.junit.runner.RunWith;

import jp.gihyo.jenkinsbook.action.SampleAction;
import mockit.Deencapsulation;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;
import mockit.Tested;
import mockit.Verifications;
import mockit.integration.junit4.JMockit;

@RunWith(JMockit.class)
public class SampleServletTest {

    @Mocked
    HttpServletRequest req;

    @Mocked
    HttpServletResponse res;

    @Mocked
    RequestDispatcher requestDispatcher;

    @Mocked
    SampleAction sampleAction;

    @Tested
    SampleServlet servlet;


    @Test
    public void testDoGet() throws Exception {}

    @Test
    public void doPostTest() throws Exception {
        
        servlet.doPost(req, res);
        
        new Expectations() {
            {
                req.getParameter("action");
                result = "actionName";
              
            }
        };
        
        servlet.doPost(req, res);

        new MockUp<SampleServlet>() {
            @Mock
            private SampleAction createAction(String name){
                return sampleAction;
            }
        };

        servlet.doPost(req, res);
        
        
        new Expectations() {
            {
                sampleAction.checkParameter(req);
                result = true;
              
            }
        };
        
        servlet.doPost(req, res);
        
        new Verifications() {
            {
                req.setCharacterEncoding("Windows-31J");
                times = 1;
                requestDispatcher.forward(req, res);
                times = 1;
                sampleAction.checkParameter(req);
                times = 1;
            }

        };
    }

    @Test
    public void testCreateAction() throws Exception {
        SampleServlet s = new SampleServlet();

        SampleAction action = Deencapsulation.invoke(s, "createAction", "hello");
        
        assertThat(action, instanceOf(SampleAction.class));
        
        SampleAction result = Deencapsulation.invoke(s, "createAction", "hell");
        
        assertNull(result);
    }


}
