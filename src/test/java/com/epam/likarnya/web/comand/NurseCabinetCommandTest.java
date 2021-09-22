package com.epam.likarnya.web.comand;

import com.epam.likarnya.DTO.TreatmentPatientDTO;
import com.epam.likarnya.Path;
import com.epam.likarnya.exception.AppException;
import com.epam.likarnya.model.Treatment;
import com.epam.likarnya.model.User;
import com.epam.likarnya.service.PatientService;
import com.epam.likarnya.web.command.NurseCabinet;
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
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NurseCabinetCommandTest {
    final HttpServletRequest request = mock(HttpServletRequest.class);
    final HttpServletResponse response = mock(HttpServletResponse.class);

    @Mock
    private HttpSession session;

    @Mock
    private PatientService service;

    @InjectMocks
    NurseCabinet command;

    private TreatmentPatientDTO treatmentPatientDTO;
    private User nurse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        command = new NurseCabinet(service);

        nurse = new User();
        nurse.setId(1L);
        nurse.setEmail("ivanova@gmail.com");
        nurse.setFirstName("Ivanna");
        nurse.setLastName("Ivanova");
        nurse.setPassword("202cb962ac59075b964b07152d234b70");
        nurse.setRole(User.Role.NURSE);

        treatmentPatientDTO = new TreatmentPatientDTO();
        treatmentPatientDTO.setId(1L);
        treatmentPatientDTO.setAppointment(String.valueOf(Treatment.Appointment.PROCEDURE));
    }

    @Test
    void whenCallNurseCabinetCommandThanReturnNurseCabinetPage() throws AppException, ServletException, IOException {
        when(request.getMethod()).thenReturn("GET");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(any(String.class))).thenReturn(nurse);
        when(service.getPatientsForTreatmentByNurse()).thenReturn(List.of(treatmentPatientDTO));
        String forward = command.execute(request, response);
        assertEquals(Path.PAGE__NURSE_CABINET, forward);

    }
}
