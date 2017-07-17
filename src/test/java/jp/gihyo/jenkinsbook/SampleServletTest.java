package jp.gihyo.jenkinsbook;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.powermock.api.mockito.PowerMockito;

import jp.gihyo.jenkinsbook.action.SampleAction;

public class SampleServletTest {

    @Rule
    public MockitoRule mockito = MockitoJUnit.rule();

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Mock
    RequestDispatcher dispatcher;

    @Mock
    HttpSession sesstion;

    @InjectMocks
    SampleServlet sampleServlet = PowerMockito.spy(new SampleServlet());

    @Before
    public void setUp() throws ServletException, IOException {
        doNothing().when(dispatcher).forward(request, response);
        doNothing().when(sesstion).setAttribute(anyString(), any());
        when(request.getRequestDispatcher("./WEB-INF/error.jsp")).thenReturn(dispatcher);
        when(request.getRequestDispatcher("./WEB-INF/result.jsp")).thenReturn(dispatcher);
        when(request.getSession(true)).thenReturn(sesstion);

    }

    @Test
    public void testDoGet() throws Exception {
        sampleServlet.doGet(request, response);

        verify(request).setCharacterEncoding("Shift_JIS");
        verify(request, times(1)).getRequestDispatcher(anyString());
    }

    @Test
    public void doPostTest() throws Exception {
        
        when(request.getParameter("action")).thenReturn(null);
        sampleServlet.doPost(request, response);
        
        when(request.getParameter("action")).thenReturn("hel");
        sampleServlet.doPost(request, response);

        when(request.getParameter("action")).thenReturn("hello");
        sampleServlet.doPost(request, response);
        
        when(request.getParameter("FirstName")).thenReturn("a");
        when(request.getParameter("LastName")).thenReturn("b");
        sampleServlet.doPost(request, response);
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
