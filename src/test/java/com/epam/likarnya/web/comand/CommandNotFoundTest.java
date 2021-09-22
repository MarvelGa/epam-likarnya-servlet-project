package com.epam.likarnya.web.comand;

import com.epam.likarnya.Path;
import com.epam.likarnya.web.command.CommandNotFound;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandNotFoundTest {
    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @InjectMocks
    private CommandNotFound command;


    @BeforeEach
    void setUp() throws ServletException, IOException {
        MockitoAnnotations.openMocks(this);
        command = new CommandNotFound();
    }

    @Test
    void whenCommandNotFoundThanReturnErrorPage() {
        String forward = command.execute(request, response);
        assertEquals(Path.PAGE_ERROR_PAGE, forward);
    }
}
