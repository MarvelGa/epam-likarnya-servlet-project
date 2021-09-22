package com.epam.likarnya.web.comand;

import com.epam.likarnya.Path;
import com.epam.likarnya.exception.AppException;
import com.epam.likarnya.model.Patient;
import com.epam.likarnya.model.User;
import com.epam.likarnya.service.PatientService;
import com.epam.likarnya.service.UserService;
import com.epam.likarnya.web.command.AdminCabinet;
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
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AdminCabinetCommandTest {
    final HttpServletRequest request = mock(HttpServletRequest.class);
    final HttpServletResponse response = mock(HttpServletResponse.class);

    @Mock
    private HttpSession session;

    @Mock
    private PatientService patientService;

    @InjectMocks
    AdminCabinet command;

    private User admin;
    private Patient patient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        command = new AdminCabinet(patientService);
        admin = new User();
        admin.setId(1L);
        admin.setEmail("ivanov@gmail.com");
        admin.setFirstName("Ivan");
        admin.setLastName("Ivanov");
        admin.setPassword("202cb962ac59075b964b07152d234b70");
        admin.setRole(User.Role.ADMIN);

        patient = new Patient();
        patient.setFirstName("Andrey");
        patient.setLastName("Andreev");
        patient.setGender(Patient.Gender.MALE);
        patient.setDateOfBirth(LocalDate.of(1990, 4, 14));
    }

    @Test
    void whenCallGetAdminCabinetCommandThanReturnAdminCabinetPage() throws ServletException, IOException, AppException {
        when(request.getMethod()).thenReturn("GET");
        when(request.getSession()).thenReturn(session);
        when(patientService.getPatientWithOutMedicalCard()).thenReturn(List.of(patient));
        String forward = command.execute(request, response);
        assertEquals(Path.ADMIN_CABINET, forward);
    }
}
