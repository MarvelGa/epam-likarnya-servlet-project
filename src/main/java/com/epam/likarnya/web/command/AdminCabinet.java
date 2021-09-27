package com.epam.likarnya.web.command;

import com.epam.likarnya.Path;
import com.epam.likarnya.exception.AppException;
import com.epam.likarnya.model.Patient;
import com.epam.likarnya.service.PatientService;
import com.epam.likarnya.service.UserService;
import com.epam.likarnya.service.impl.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AdminCabinet implements Command {
    private static final Logger logger = Logger.getLogger(AdminCabinet.class);
    private PatientService patientService = ServiceFactory.getInstance().getPatientService();

    public AdminCabinet() {
    }

    public AdminCabinet(PatientService patientService) {
        this.patientService = patientService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        List<Patient> patients = patientService.getPatientWithOutMedicalCard();
        request.setAttribute("patients", patients);
        String sort = request.getParameter("sorting");
        if (sort != null && patients.size() != 0) {
            if (!sort.isEmpty()) {
                if (sort.equals("ASC-NAME")) {
                    patients = patients.stream()
                            .sorted(Comparator.comparing(Patient::getFirstName))
                            .collect(Collectors.toList());
                }
                if (sort.equals("DESC-NAME")) {
                    patients = patients.stream()
                            .sorted(Comparator.comparing(Patient::getFirstName).reversed())
                            .collect(Collectors.toList());
                }
                if (sort.equals("ASC")) {
                    patients = patients.stream()
                            .sorted(Comparator.comparing(Patient::getLastName))
                            .collect(Collectors.toList());
                }
                if (sort.equals("DESC")) {
                    patients = patients.stream()
                            .sorted(Comparator.comparing(Patient::getLastName).reversed())
                            .collect(Collectors.toList());
                }
                if (sort.equals("DECREASE")) {
                    patients = patients.stream()
                            .sorted(Comparator.comparing(Patient::getDateOfBirth).reversed())
                            .collect(Collectors.toList());
                }
                if (sort.equals("INCREASE")) {
                    patients = patients.stream()
                            .sorted(Comparator.comparing(Patient::getDateOfBirth))
                            .collect(Collectors.toList());
                }
                request.setAttribute("patients", patients);
                request.setAttribute("sort", sort);
            }
        }
        return Path.ADMIN_CABINET;
    }
}
