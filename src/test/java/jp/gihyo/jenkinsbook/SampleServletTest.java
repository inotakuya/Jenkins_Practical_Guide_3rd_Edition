package jp.gihyo.jenkinsbook;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.lang.reflect.Method;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    public void testDoGet() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        
        doNothing().when(dispatcher).forward(request, response);
        when(request.getRequestDispatcher("./WEB-INF/error.jsp")).thenReturn(dispatcher);
        new SampleServlet().doGet(request, response);
        
        verify(request).setCharacterEncoding("Shift_JIS");
        verify(request,times(1)).getRequestDispatcher(anyString());
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
