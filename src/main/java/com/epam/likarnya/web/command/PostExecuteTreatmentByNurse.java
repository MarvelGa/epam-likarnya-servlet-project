package com.epam.likarnya.web.command;

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

public class PostExecuteTreatmentByNurse implements Command {
    private static final Logger logger = Logger.getLogger(PostExecuteTreatmentByNurse.class);
    private TreatmentService treatmentService = ServiceFactory.getInstance().getTreatmentService();

    public PostExecuteTreatmentByNurse() {
    }

    public PostExecuteTreatmentByNurse(TreatmentService treatmentService) {
        this.treatmentService = treatmentService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        HttpSession httpSession = request.getSession();
        User  nurse = (User) httpSession.getAttribute("nurse");
        Long treatmentId = Long.valueOf(request.getParameter("treatmentId"));
        Long statementId = Long.valueOf(request.getParameter("statementId"));
        treatmentService.executeTreatment(nurse.getId(), treatmentId, statementId);
        return Path.COMMAND__NURSE_CABINET;
    }
}
