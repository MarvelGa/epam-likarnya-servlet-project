package com.epam.likarnya.web.command;

import com.epam.likarnya.DTO.MedicalCardDTO;
import com.epam.likarnya.Path;
import com.epam.likarnya.exception.AppException;
import com.epam.likarnya.model.Treatment;
import com.epam.likarnya.service.MedicalCardService;
import com.epam.likarnya.service.PatientService;
import com.epam.likarnya.service.TreatmentService;
import com.epam.likarnya.service.impl.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

public class PostTreatmentAssigning implements Command {
    private static final Logger logger = Logger.getLogger(PostTreatmentAssigning.class);
    private MedicalCardService medicalCardService = ServiceFactory.getInstance().getMedicalCardService();
    private TreatmentService treatmentService = ServiceFactory.getInstance().getTreatmentService();

    public PostTreatmentAssigning() {
    }

    public PostTreatmentAssigning(MedicalCardService medicalCardService, TreatmentService treatmentService) {
        this.medicalCardService = medicalCardService;
        this.treatmentService = treatmentService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        String diagnosis = request.getParameter("diagnosis");
        String appointment = request.getParameter("appointment");
        String patientId = request.getParameter("patientId");

        MedicalCardDTO medicalCard = medicalCardService.getMedicalCardByPatientId(Long.valueOf(patientId));

        Treatment treatment = new Treatment();
        treatment.setAppointment(Treatment.Appointment.valueOf(appointment));
        treatment.setAppointmentStatus(Treatment.AppointmentStatus.NOT_EXECUTED);
        treatment.setCreatedAt(LocalDateTime.now());

        treatmentService.setTreatmentAndDiagnosis(treatment, medicalCard, diagnosis);
        return Path.COMMAND__DOCTOR_CABINET;
    }
}
