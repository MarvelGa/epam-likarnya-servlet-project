package com.epam.likarnya.web.comand;

import com.epam.likarnya.Path;
import com.epam.likarnya.exception.AppException;
import com.epam.likarnya.model.Patient;
import com.epam.likarnya.model.Statement;
import com.epam.likarnya.service.MedicalCardService;
import com.epam.likarnya.service.PatientService;
import com.epam.likarnya.web.command.PostCreateMedicalCard;
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
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PostCreateMedicalCardCommandTest {
    final HttpServletRequest request = mock(HttpServletRequest.class);
    final HttpServletResponse response = mock(HttpServletResponse.class);

    @Mock
    private HttpSession session;

    @Mock
    private PatientService patientService;

    @Mock
    private MedicalCardService medicalCardService;

    @InjectMocks
    PostCreateMedicalCard command;

    private Patient patient;
    private Statement statement;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        command = new PostCreateMedicalCard(patientService, medicalCardService);

        patient = new Patient();
        patient.setId(1L);
        patient.setFirstName("Andrey");
        patient.setLastName("Andreev");
        patient.setGender(Patient.Gender.MALE);
        patient.setDateOfBirth(LocalDate.of(1990, 4, 14));

        statement = new Statement();
        statement.setCreatedAt(LocalDateTime.now());
        statement.setPatientStatus(Statement.PatientStatus.NEW);
        statement.setPatient(patient);
    }

    @Test
    void whenCallGetAdminCabinetCommandThanReturnAdminCabinetPage() throws ServletException, IOException, AppException {
        when(request.getMethod()).thenReturn("GET");
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("doctor")).thenReturn("1");
        when(request.getParameter("patientId")).thenReturn("1");
        when(request.getParameter("complaints")).thenReturn("some complaints");
        when(patientService.getPatientById(Long.valueOf(1))).thenReturn(patient);
        when(medicalCardService.createMedicalCard(statement, 1L, "some complaints")).thenReturn(1L);
        String forward = command.execute(request, response);
        assertEquals(Path.COMMAND__ADMIN_CABINET, forward);
    }
}
