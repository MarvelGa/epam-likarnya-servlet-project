package com.epam.likarnya.web.comand;

import com.epam.likarnya.DTO.PatientDTO;
import com.epam.likarnya.Path;
import com.epam.likarnya.exception.AppException;
import com.epam.likarnya.model.Patient;
import com.epam.likarnya.model.User;
import com.epam.likarnya.service.PatientService;
import com.epam.likarnya.web.command.DoctorCabinet;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DoctorHistoryCommandTest {
    final HttpServletRequest request = mock(HttpServletRequest.class);
    final HttpServletResponse response = mock(HttpServletResponse.class);

    @Mock
    private HttpSession session;

    @Mock
    private PatientService patientService;

    @InjectMocks
    DoctorCabinet command;

    private User doctor;
    private Patient patient;
    private PatientDTO patientDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        command = new  DoctorCabinet(patientService);
        doctor = new User();
        doctor.setId(1L);
        doctor.setEmail("ivanov@gmail.com");
        doctor.setFirstName("Ivan");
        doctor.setLastName("Ivanov");
        doctor.setPassword("202cb962ac59075b964b07152d234b70");
        doctor.setRole(User.Role.DOCTOR);

        patient = new Patient();
        patient.setFirstName("Andrey");
        patient.setLastName("Andreev");
        patient.setGender(Patient.Gender.MALE);
        patient.setDateOfBirth(LocalDate.of(1990, 4, 14));

        patientDto = new PatientDTO();
        patientDto.setFirstName("Andrey");
        patientDto.setLastName("Andreev");
        patientDto.setGender(String.valueOf(Patient.Gender.MALE));
        patientDto.setDateOfBirth(String.valueOf(LocalDate.of(1990, 4, 14)));
    }

    @Test
    void whenCallGetAdminCabinetCommandThanReturnAdminCabinetPage() throws ServletException, IOException, AppException {
        when(request.getMethod()).thenReturn("GET");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(any(String.class))).thenReturn(doctor);
        when(patientService.getPatientsByDoctorId(doctor.getId())).thenReturn(List.of(patientDto));
        String forward = command.execute(request, response);
        assertEquals(Path.PAGE__DOCTOR_CABINET, forward);
    }
}
