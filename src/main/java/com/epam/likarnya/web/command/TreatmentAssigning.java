package com.epam.likarnya.web.command;

import com.epam.likarnya.Path;
import com.epam.likarnya.exception.AppException;
import com.epam.likarnya.model.Patient;
import com.epam.likarnya.service.MedicalCardService;
import com.epam.likarnya.service.PatientService;
import com.epam.likarnya.service.impl.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TreatmentAssigning implements Command {
    private static final Logger logger = Logger.getLogger(TreatmentAssigning.class);
    private PatientService patientService = ServiceFactory.getInstance().getPatientService();

    public TreatmentAssigning() {
    }

    public TreatmentAssigning(PatientService patientService) {
        this.patientService = patientService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        String patientId = request.getParameter("patientId");
        if (patientId!=null){
            Patient patient = patientService.getPatientById(Long.valueOf(patientId));
            request.setAttribute("onePatient", patient);
        }
        return Path.PAGE__TREATMENT_ASSIGNING;
    }
}
