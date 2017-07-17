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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import jp.gihyo.jenkinsbook.action.SampleAction;

public class SampleServletTest {

    @Mock
    HttpServletRequest request;
    
    @Mock
    HttpServletResponse response;
    
    @Mock
    RequestDispatcher dispatcher;
    
    @InjectMocks
    SampleServlet sampleServlet = new SampleServlet();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testDoGet() throws Exception {


        doNothing().when(dispatcher).forward(request, response);
        when(request.getRequestDispatcher("./WEB-INF/error.jsp")).thenReturn(dispatcher);
        sampleServlet.doGet(request, response);

        verify(request).setCharacterEncoding("Shift_JIS");
        verify(request, times(1)).getRequestDispatcher(anyString());
    }

    @Test
    public void doPostTest() throws Exception {
        doNothing().when(dispatcher).forward(request, response);
        when(request.getRequestDispatcher("./WEB-INF/error.jsp")).thenReturn(dispatcher);
        when(request.getParameter("action")).thenReturn("hello");
        sampleServlet.doPost(request, response);
        verify(request, times(1)).setCharacterEncoding("Windows-31J");
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
