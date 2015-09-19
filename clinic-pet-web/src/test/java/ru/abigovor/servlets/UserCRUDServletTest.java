package ru.abigovor.servlets;

import main.ru.abigovor.Client;
import main.ru.abigovor.Dog;
import main.ru.abigovor.UserException.UserException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import ru.abigovor.store.UserCache;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserCRUDServletTest extends Mockito {

    private final UserCache CACHE = UserCache.getInstance();
    private HttpServletRequest request;
    private HttpServletResponse response;


    @Before
    public void setUp() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
    }

    @After
    public void clean() {
        request = null;
        response = null;
    }

    @Test
    public void create_user() throws ServletException, IOException, UserException {
        when(request.getParameter("clientName")).thenReturn("test");
        when(request.getParameter("petName")).thenReturn("test");
        when(request.getParameterValues("1")).thenReturn(new String[]{"cat"});

        assertTrue(CACHE.isEmpty());

        new AddUserServlet().doPost(request, response);

        verify(request, atLeast(1)).getParameter("clientName");
        verify(request, atLeast(1)).getParameter("petName");
        verify(request, atLeast(1)).getParameterValues("1");
        verify(response, atLeast(1)).sendRedirect(String.format("%s%s", request.getContextPath(), "/user/view"));
        assertFalse(CACHE.isEmpty());

        CACHE.delete(CACHE.findByName("test").get(0).getId());
    }

    @Test
    public void delete_user() throws Exception {
        when(request.getParameter("id")).thenReturn("1");

        int id = 1;
        CACHE.add(new Client(id, "ClientName", new Dog("petName")));
        assertFalse(CACHE.isEmpty());

        new DeleteUserServlet().doGet(request, response);

        verify(request, atLeast(1)).getParameter("id");
        assertTrue(CACHE.isEmpty());
        verify(response).sendRedirect(String.format("%s%s", request.getContextPath(), "/user/view"));
    }


    @Test
    public void delete_user_exp_user_not_found() throws Exception {
        when(request.getParameter("id")).thenReturn("2");

        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("/views/user/index.jsp")).thenReturn(dispatcher);

        new DeleteUserServlet().doGet(request, response);

        verify(request, atLeast(1)).getIntHeader("id");
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void test_index_servlet() throws Exception {

        when(request.getMethod()).thenReturn("GET");
        when(request.getRequestURI()).thenReturn("/cpw/");
        when(request.getContextPath()).thenReturn("/cpw");
        when(request.getServletPath()).thenReturn("/");

        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("/views/user/index.jsp")).thenReturn(dispatcher);

        new IndexServlet().doGet(request, response);

        verify(dispatcher).forward(request, response);
    }

    @Test
    public void test_user_view() throws Exception {
        when(request.getMethod()).thenReturn("GET");
        when(request.getRequestURI()).thenReturn("/cpw/user/view");
        when(request.getContextPath()).thenReturn("/cpw");
        when(request.getServletPath()).thenReturn("/user/view");

        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("/views/user/UserView.jsp")).thenReturn(dispatcher);

        new UserViewServlet().doGet(request, response);

        verify(request, atLeast(1)).setAttribute("clients", CACHE.values());
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void test_search_user() throws Exception {
        when(request.getMethod()).thenReturn("POST");
        when(request.getRequestURI()).thenReturn("/cpw/search");
        when(request.getContextPath()).thenReturn("/cpw");
        when(request.getServletPath()).thenReturn("/search/");

        when(request.getParameter("search")).thenReturn("clientName");

        int id = 1;
        CACHE.add(new Client(id, "ClientName", new Dog("petName")));
        assertFalse(CACHE.isEmpty());

        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("/views/user/UserView.jsp")).thenReturn(dispatcher);

        new SearchUserServlet().doPost(request, response);
        verify(request, atLeast(1)).getParameter("search");
        verify(dispatcher).forward(request, response);

        CACHE.delete(id);
    }

    @Test
    public void test_search_user_client_not_found() throws Exception {
        when(request.getMethod()).thenReturn("POST");
        when(request.getRequestURI()).thenReturn("/cpw/search");
        when(request.getContextPath()).thenReturn("/cpw");
        when(request.getServletPath()).thenReturn("/search/");

        when(request.getParameter("search")).thenReturn("clientName");

        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("/views/user/index.jsp")).thenReturn(dispatcher);

        new SearchUserServlet().doPost(request, response);
        verify(request, atLeast(1)).getParameter("search");
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void test_edit_user_do_get() throws Exception {
        when(request.getParameter("id")).thenReturn("1");

        int id = 1;
        CACHE.add(new Client(id, "ClientName", new Dog("petName")));
        assertFalse(CACHE.isEmpty());

        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("/views/user/EditUser.jsp")).thenReturn(dispatcher);

        new EditUserServlet().doGet(request, response);
        verify(request, atLeast(1)).getParameter("id");
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void test_edit_user_do_post() throws Exception {
        when(request.getParameter("id")).thenReturn("1");
        when(request.getParameter("clientName")).thenReturn("clientName");
        when(request.getParameter("petName")).thenReturn("petName");

        int id = 1;
        CACHE.add(new Client(id, "ClientName", new Dog("petName")));
        assertFalse(CACHE.isEmpty());

        new EditUserServlet().doPost(request, response);

        verify(request, atLeast(1)).getParameter("id");
        verify(request, atLeast(1)).getParameter("clientName");
        verify(request, atLeast(1)).getParameter("petName");
        verify(response).sendRedirect(String.format("%s%s", request.getContextPath(), "/user/view"));

        CACHE.delete(id);
    }

    @Test
    public void test_edit_user_not_found_do_post() throws Exception {
        when(request.getParameter("id")).thenReturn("1");
        when(request.getParameter("clientName")).thenReturn("clientName");
        when(request.getParameter("petName")).thenReturn("petName");

        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("/views/user/index.jsp")).thenReturn(dispatcher);

        new EditUserServlet().doPost(request, response);

        verify(request, atLeast(1)).getParameter("id");
        verify(request, atLeast(1)).getParameter("clientName");
        verify(request, atLeast(1)).getParameter("petName");
        verify(dispatcher).forward(request, response);
    }
}