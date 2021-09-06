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


    public static final String COMMAND__ADMIN_CABINET = "controller?command=adminCabinet";
    public static final String COMMAND__DOCTOR_CABINET = "controller?command=doctorCabinet";
    public static final String COMMAND__NURSE_CABINET = "controller?command=nurseCabinet";


    public static final String ERROR_PAGE = "/WEB-INF/errorPage.jsp";
    public static final String PAGE_ERROR_PAGE = "/WEB-INF/errorPage.jsp";
}
