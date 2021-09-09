package com.epam.likarnya.web.command;

import com.epam.likarnya.Path;
import com.epam.likarnya.exception.AppException;
import com.epam.likarnya.model.Patient;
import com.epam.likarnya.model.Statement;
import com.epam.likarnya.service.MedicalCardService;
import com.epam.likarnya.service.PatientService;
import com.epam.likarnya.service.impl.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

public class PostCreateMedicalCard implements Command {
    private static final Logger logger = Logger.getLogger(PostCreateMedicalCard.class);
    private PatientService patientService = ServiceFactory.getInstance().getPatientService();
    private MedicalCardService medicalCardService = ServiceFactory.getInstance().getMedicalCardService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        String doctorId = request.getParameter("doctor");
        String patientId = request.getParameter("patientId");
        String complaints = request.getParameter("complaints");

        Patient patient = patientService.getPatientById(Long.valueOf(patientId));

        Statement statement = new Statement();
        statement.setCreatedAt(LocalDateTime.now());
        statement.setPatientStatus(Statement.PatientStatus.NEW);
        statement.setPatient(patient);

        medicalCardService.createMedicalCard(statement, java.lang.Long.valueOf(doctorId), complaints);

        return Path.COMMAND__ADMIN_CABINET;
    }
}
