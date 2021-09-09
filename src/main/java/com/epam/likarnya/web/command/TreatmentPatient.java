package com.epam.likarnya.web.command;

import com.epam.likarnya.DTO.TreatmentPatientDTO;
import com.epam.likarnya.Path;
import com.epam.likarnya.exception.AppException;
import com.epam.likarnya.model.User;
import com.epam.likarnya.service.MedicalCardService;
import com.epam.likarnya.service.PatientService;
import com.epam.likarnya.service.TreatmentService;
import com.epam.likarnya.service.impl.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class TreatmentPatient implements Command {
    private static final Logger logger = Logger.getLogger(TreatmentPatient.class);
    private PatientService patientService = ServiceFactory.getInstance().getPatientService();
    private MedicalCardService medicalCardService = ServiceFactory.getInstance().getMedicalCardService();
    private TreatmentService treatmentService = ServiceFactory.getInstance().getTreatmentService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        HttpSession httpSession = request.getSession();
        User doctor = (User) httpSession.getAttribute("doctor");
        List<TreatmentPatientDTO> patientsForTreating = patientService.getPatientsForTreatmentByDoctorId(doctor.getId());
        request.setAttribute("patientsForTreating", patientsForTreating);
        return Path.PAGE__DOCTOR_TREATMENT;
    }
}
