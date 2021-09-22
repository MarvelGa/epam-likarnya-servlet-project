package com.epam.likarnya.web.command;

import com.epam.likarnya.DTO.TreatmentPatientDTO;
import com.epam.likarnya.Path;
import com.epam.likarnya.exception.AppException;
import com.epam.likarnya.model.User;
import com.epam.likarnya.service.PatientService;
import com.epam.likarnya.service.impl.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class NurseCabinet implements Command {
    private static final Logger logger = Logger.getLogger(NurseCabinet.class);
    private PatientService patientService = ServiceFactory.getInstance().getPatientService();

    public NurseCabinet() {
    }

    public NurseCabinet(PatientService patientService) {
        this.patientService = patientService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        HttpSession session =request.getSession();
        User nurse = (User) session.getAttribute("nurse");
        List<TreatmentPatientDTO> patientsForTreatingByProcedureOrDrug = patientService.getPatientsForTreatmentByNurse();
        request.setAttribute("patientsForTreatingByProcedureOrDrug", patientsForTreatingByProcedureOrDrug);
        return Path.PAGE__NURSE_CABINET;
    }
}
