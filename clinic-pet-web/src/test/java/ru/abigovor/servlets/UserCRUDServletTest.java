package ru.abigovor.servlets;

import main.ru.abigovor.UserException.UserException;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;
import ru.abigovor.models.Client;
import ru.abigovor.store.Storage;
import ru.abigovor.store.UserCache;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserCRUDServletTest extends Mockito {

    private final Storage STORAGE = UserCache.getInstance();
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
        when(request.getParameter("clientName")).thenReturn("testName");
        when(request.getParameter("clientSurname")).thenReturn("testSurname");
        when(request.getParameter("sexH")).thenReturn("f");

        new AddUserServlet().doPost(request, response);

        verify(request, atLeast(1)).getParameter("clientName");
        verify(request, atLeast(1)).getParameter("clientSurname");
        verify(request, atLeast(1)).getParameter("sexH");
        verify(response, atLeast(1)).sendRedirect(String.format("%s%s", request.getContextPath(), "/user/view"));

        STORAGE.delete(STORAGE.findByName("testName").iterator().next().getId());
    }

    @Test
    public void delete_user() throws Exception {
        Client client = new Client(1, "ClientName", "surname", "pswd", 'f', null);
        client.setRole(1);
        int id = STORAGE.add(client);
        when(request.getParameter("id")).thenReturn(String.valueOf(id));

        new DeleteUserServlet().doGet(request, response);

        verify(request, atLeast(1)).getParameter("id");
        verify(response).sendRedirect(String.format("%s%s", request.getContextPath(), "/user/view"));
    }


    @Test(expected = IllegalArgumentException.class)
    public void delete_user_exp_user_not_found() throws Exception {
        when(request.getParameter("id")).thenReturn("-1");

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

        verify(request, atLeast(1)).setAttribute("clients", STORAGE.values());
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void test_search_user() throws Exception {
        when(request.getMethod()).thenReturn("POST");
        when(request.getRequestURI()).thenReturn("/cpw/search");
        when(request.getContextPath()).thenReturn("/cpw");
        when(request.getServletPath()).thenReturn("/search/");

        Client client = new Client(1, "ClientName", "surname", "pswd", 'f', null);
        client.setRole(1);
        int id = STORAGE.add(client);

        when(request.getParameter("search")).thenReturn("ClientName");

        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("/views/user/UserView.jsp")).thenReturn(dispatcher);

        new SearchUserServlet().doPost(request, response);
        verify(request, atLeast(1)).getParameter("search");
        verify(dispatcher).forward(request, response);

        STORAGE.delete(id);
    }

    @Ignore
    @Test(expected = IllegalStateException.class)
    public void test_search_user_client_not_found() throws Exception {
        when(request.getMethod()).thenReturn("POST");
        when(request.getRequestURI()).thenReturn("/cpw/search");
        when(request.getContextPath()).thenReturn("/cpw");
        when(request.getServletPath()).thenReturn("/search/");

        when(request.getParameter("search")).thenReturn("clientNameNotFound");

        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("/views/user/index.jsp")).thenReturn(dispatcher);

        new SearchUserServlet().doPost(request, response);
        verify(request, atLeast(1)).getParameter("search");
        verify(dispatcher).forward(request, response);
    }


    @Test
    public void test_edit_user_do_get() throws Exception {
        when(request.getParameter("id")).thenReturn("1");

        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("/views/user/EditUser.jsp")).thenReturn(dispatcher);

        new EditUserServlet().doGet(request, response);
        verify(request, atLeast(1)).getParameter("id");
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void test_edit_user_do_post() throws Exception {
        Client client = new Client(1, "ClientName", "surname", "pswd", 'f', null);
        client.setRole(1);
        int id = STORAGE.add(client);

        when(request.getParameter("id")).thenReturn(String.valueOf(id));
        when(request.getParameter("clientName")).thenReturn("NewClientName");
        when(request.getParameter("clientSurname")).thenReturn("NewClientSurname");
        when(request.getParameter("sex")).thenReturn("m");

        new EditUserServlet().doPost(request, response);

        verify(request, atLeast(1)).getParameter("id");
        verify(request, atLeast(1)).getParameter("clientName");
        verify(request, atLeast(1)).getParameter("clientSurname");
        verify(request, atLeast(1)).getParameter("sex");
        verify(response).sendRedirect(String.format("%s%s", request.getContextPath(), "/user/view"));

        STORAGE.delete(id);
    }
}