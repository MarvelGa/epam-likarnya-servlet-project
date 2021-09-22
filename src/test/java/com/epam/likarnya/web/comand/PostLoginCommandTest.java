package com.epam.likarnya.web.comand;

import com.epam.likarnya.Path;
import com.epam.likarnya.exception.AppException;
import com.epam.likarnya.model.User;
import com.epam.likarnya.service.UserService;
import com.epam.likarnya.web.command.PostLoginCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PostLoginCommandTest {
    final HttpServletRequest request = mock(HttpServletRequest.class);
    final HttpServletResponse response = mock(HttpServletResponse.class);

    @Mock
    private UserService userService;
    @Mock
    private HttpSession session;

    @InjectMocks
    PostLoginCommand command;
    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        command = new PostLoginCommand(userService);
        user = new User();
        user.setId(1L);
        user.setEmail("ivanov@gmail.com");
        user.setFirstName("Ivan");
        user.setLastName("Ivanov");
        user.setPassword("202cb962ac59075b964b07152d234b70");
        user.setRole(User.Role.ADMIN);
    }

    @Test
    void whenPassWordIsWrongThanReturnLoginPageAgain() throws ServletException, IOException, AppException {
        when(request.getMethod()).thenReturn("POST");
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("email")).thenReturn(user.getEmail());
        when(request.getParameter("password")).thenReturn("1");
        when(userService.getUserByEmail(user.getEmail())).thenReturn(user);
        String forward = command.execute(request, response);
        assertEquals(Path.PAGE__LOGIN, forward);
    }

    @Test
    void whenPassWordIsNullThanReturnLoginPageAgain() throws ServletException, IOException, AppException {
        when(request.getMethod()).thenReturn("POST");
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("email")).thenReturn(user.getEmail());
        when(request.getParameter("password")).thenReturn(null);
        when(userService.getUserByEmail(user.getEmail())).thenReturn(user);
        String forward = command.execute(request, response);
        assertEquals(Path.PAGE__LOGIN, forward);
    }

    @Test
    void whenPassWordIsEmptyThanReturnLoginPageAgain() throws ServletException, IOException, AppException {
        when(request.getMethod()).thenReturn("POST");
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("email")).thenReturn(user.getEmail());
        when(request.getParameter("password")).thenReturn("");
        when(userService.getUserByEmail(user.getEmail())).thenReturn(user);
        String forward = command.execute(request, response);
        assertEquals(Path.PAGE__LOGIN, forward);
    }

    @Test
    void whenEmailIsEmptyThanReturnLoginPageAgain() throws ServletException, IOException, AppException {
        when(request.getMethod()).thenReturn("POST");
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("email")).thenReturn("");
        when(request.getParameter("password")).thenReturn("123");
        when(userService.getUserByEmail(user.getEmail())).thenReturn(user);
        String forward = command.execute(request, response);
        assertEquals(Path.PAGE__LOGIN, forward);
    }

    @Test
    void whenEmailIsNullThanReturnLoginPageAgain() throws ServletException, IOException, AppException {
        when(request.getMethod()).thenReturn("POST");
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("email")).thenReturn(null);
        when(request.getParameter("password")).thenReturn("123");
        when(userService.getUserByEmail(user.getEmail())).thenReturn(user);
        String forward = command.execute(request, response);
        assertEquals(Path.PAGE__LOGIN, forward);
    }

    @Test
    void whenCallPostLoginCommandAndDidAuthorizationAsADMINThanReturnAdminCabinetPage() throws ServletException, IOException, AppException {
        when(request.getMethod()).thenReturn("POST");
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("email")).thenReturn("ivanov@gmail.com");
        when(request.getParameter("password")).thenReturn("123");
        when(userService.getUserByEmail(user.getEmail())).thenReturn(user);
        String forward = command.execute(request, response);
        assertEquals(Path.COMMAND__ADMIN_CABINET, forward);
    }

    @Test
    void whenCallPostLoginCommandAndDidAuthorizationAsAdminThanReturnUserCabinetPage() throws ServletException, IOException, AppException {
        when(request.getMethod()).thenReturn("POST");
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("email")).thenReturn("ivanov@gmail.com");
        when(request.getParameter("password")).thenReturn("123");
        user.setRole(User.Role.DOCTOR);
        when(userService.getUserByEmail(user.getEmail())).thenReturn(user);
        String forward = command.execute(request, response);
        assertEquals(Path.COMMAND__DOCTOR_CABINET, forward);
    }

    @Test
    void whenCallPostLoginCommandAndDidAuthorizationAsNurseThanReturnNurseCabinetPage() throws ServletException, IOException, AppException {
        when(request.getMethod()).thenReturn("POST");
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("email")).thenReturn("ivanov@gmail.com");
        when(request.getParameter("password")).thenReturn("123");
        user.setRole(User.Role.NURSE);
        when(userService.getUserByEmail(user.getEmail())).thenReturn(user);
        String forward = command.execute(request, response);
        assertEquals(Path.COMMAND__NURSE_CABINET, forward);
    }

    @Test
    void whenCallPostLoginCommandAndEmailIsNullThanReturnLoginPage() throws ServletException, IOException, AppException {
        when(request.getMethod()).thenReturn("POST");
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("email")).thenReturn(null);
        String forward = command.execute(request, response);
        assertEquals(Path.PAGE__LOGIN, forward);
    }
}
