package com.epam.likarnya;

public final class Path {

    public static final String PAGE__LOGIN = "/WEB-INF/jsp/login.jsp";
    public static final String PAGE__REGISTRATION = "/WEB-INF/jsp/register.jsp";


    public static final String DOCTOR_CABINET= "/WEB-INF/jsp/doctorCabinet.jsp";
    public static final String ADMIN_CABINET = "/WEB-INF/jsp/admin/adminCabinet.jsp";
    public static final String NURSE_CABINET = "/WEB-INF/jsp/nurseCabinet.jsp";


    //admin
    public static final String PAGE__PATIENT_REGISTRATION = "/WEB-INF/jsp/admin/patient-registration.jsp";
    public static final String PAGE__MEDIC_REGISTRATION = "/WEB-INF/jsp/admin/medworker-registration.jsp";
    public static final String PAGE__DOCTORS = "/WEB-INF/jsp/admin/list-doctors.jsp";
    public static final String PAGE__NURSES= "/WEB-INF/jsp/admin/list-nurses.jsp";
    public static final String PAGE__ADDING_MED_CAD= "/WEB-INF/jsp/admin/add-medcard.jsp";


   //doctor
   public static final String PAGE__DOCTOR_CABINET= "/WEB-INF/jsp/doctor/doctorCabinet.jsp";
   public static final String PAGE__TREATMENT_ASSIGNING= "/WEB-INF/jsp/doctor/treatment-assigning.jsp";
   public static final String PAGE__DOCTOR_TREATMENT= "/WEB-INF/jsp/doctor/treatment-patients.jsp";
   public static final String PAGE__DOCTOR_TREATMENT_HISTORY= "/WEB-INF/jsp/doctor/history.jsp";

   //nurse
   public static final String PAGE__NURSE_CABINET= "/WEB-INF/jsp/nurse/nurseCabinet.jsp";
   public static final String PAGE__NURSE_TREATMENT_HISTORY_OF_PATIENTS= "/WEB-INF/jsp/nurse/history.jsp";


    public static final String COMMAND__NURSES_LIST = "controller?command=nurses";
    public static final String COMMAND__DOCTORS_LIST = "controller?command=doctors";
    public static final String COMMAND__DOCTORS_PATIENTS_FOR_TREATMENT = "controller?command=doctor-treatment-patients";
    public static final String COMMAND__LOGIN_PAGE = "controller?command=login";


    public static final String COMMAND__ADMIN_CABINET = "controller?command=adminCabinet";
    public static final String COMMAND__DOCTOR_CABINET = "controller?command=doctorCabinet";
    public static final String COMMAND__NURSE_CABINET = "controller?command=nurseCabinet";


    public static final String ERROR_PAGE = "/WEB-INF/errorPage.jsp";
    public static final String PAGE_ERROR_PAGE = "/WEB-INF/errorPage.jsp";
}
