package com.epam.likarnya.web.comand;

import com.epam.likarnya.Path;
import com.epam.likarnya.exception.AppException;
import com.epam.likarnya.web.command.GetLoginCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetLoginCommandTest {
    final HttpServletRequest request = mock(HttpServletRequest.class);
    final HttpServletResponse response = mock(HttpServletResponse.class);

    @InjectMocks
    GetLoginCommand command;

    @BeforeEach
    void setUp()  {
        MockitoAnnotations.openMocks(this);
        command = new GetLoginCommand();
    }

    @Test
    void whenCallGetLoginCommandCommandThanReturnLoginPage() throws ServletException, IOException, AppException {
        when(request.getMethod()).thenReturn("GET");
        String forward = command.execute(request, response);
        assertEquals(Path.PAGE__LOGIN, forward);
    }
}
