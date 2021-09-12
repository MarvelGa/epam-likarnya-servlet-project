package com.epam.likarnya.exception;

public class Messages {
    private Messages() throws IllegalAccessException {
        throw new IllegalAccessException();
    }

    public static final String ERR_CANNOT_OBTAIN_CONNECTION = "Cannot obtain a connection from the pool";
    public static final String ERR_CANNOT_CLOSE_CONNECTION = "Cannot close a connection";
    public static final String ERR_CANNOT_CLOSE_RESULTSET = "Cannot close a result set";
    public static final String ERR_CANNOT_CLOSE_STATEMENT = "Cannot close a statement";
    public static final String ERR_CANNOT_CLOSE_PREPARED_STATEMENT = "Cannot close a prepared statement";
    public static final String ERR_CANNOT_OBTAIN_DATA_SOURCE = "Cannot obtain the data source";
    public static final String CANNOT_ROLLBACK_TRANSACTION = "Cannot rollback transaction";

    public static final String ERR_CANNOT_INSERT_USER = "Cannot insert new user";
    public static final String ERR_CANNOT_OBTAIN_USER_BY_ID = "Cannot obtain a user by its id";
    public static final String ERR_CANNOT_OBTAIN_USER_BY_EMAIL = "Cannot obtain a user by its email";
    public static final String ERR_CANNOT_UPDATE_USER = "Cannot update a user";
    public static final String ERR_CANNOT_DELETE_USER = "Cannot delete new user";
    public static final String ERR_CANNOT_READ_ALL_USERS = "Cannot read all users";
    public static final String ERR_CANNOT_COUNT_ALL_USERS = "Cannot count all users";
    public static final String ERR_CANNOT_READ_ALL_PATIENTS = "Cannot read all patients";
    public static final String ERR_CANNOT_CREATE_MEDIC_CARD = "Cannot create medical card";
    public static final String ERR_CANNOT_OBTAIN_MEDICAL_CARD_BY_PATIENT_ID = "Cannot obtain medical card by patient's id";
    public static final String ERR_CANNOT_CREATE_TREATMENT_AND_SET_DIAGNOSIS = "Cannot create treatment and set the diagnosis";
    public static final String ERR_CANNOT_READ_ALL_DOCTORS = "Cannot read all doctors";
    public static final String ERR_CANNOT_READ_ALL_NURSES = "Cannot read nurses doctors";

    public static final String ERR_SERVICE_LAYER_CANNOT_OBTAIN_USER_BY_EMAIL = "Cannot obtain a user by its email at service layer";
    public static final String ERR_SERVICE_LAYER_CANNOT_INSERT_USER = "Cannot insert new user at service layer";
    public static final String ERR_SERVICE_LAYER_CANNOT_READ_ALL_USERS = "Cannot read all users at service layer";
    public static final String ERR_SERVICE_LAYER_CANNOT_READ_ALL_PATIENT = "Cannot read all patients at service layer";
    public static final String ERR_SERVICE_LAYER_CANNOT_READ_PATIENT_BY_ID = "Cannot read patient by id at service layer";
    public static final String ERR_SERVICE_LAYER_CANNOT_CREATE_MEDICAL_CARD = "Cannot crete the medical card for patient at service layer";
    public static final String ERR_SERVICE_LAYER_CANNOT_FIND_PATIENTS_BY_DOCTOR_ID = "Cannot find patients by doctor id at service layer";
    public static final String ERR_SERVICE_LAYER_CANNOT_OBTAIN_MEDICAL_CARD_BY_PATIENTS_ID = "Cannot obtain medical card by patient id at service layer";
    public static final String ERR_SERVICE_LAYER_CANNOT_CREATE_TREATMENT_AND_SET_DIAGNOSIS = "Cannot create treatment and set the diagnosis at service layer";
    public static final String ERR_SERVICE_LAYER_CANNOT_EXECUTE_TREATMENT = "Cannot create execute treatment for patient at service layer";
    public static final String ERR_SERVICE_LAYER_CANNOT_OBTAIN_PATIENTS_HISTORY_BY_DOCTOR_ID = "Cannot obtain patient's history by doctor id at service layer";
    public static final String ERR_SERVICE_LAYER_CANNOT_FIND_DOCTORS = "Cannot find doctors at service layer";
    public static final String ERR_SERVICE_LAYER_CANNOT_FIND_NURSES = "Cannot find nurses at service layer";
    public static final String ERR_SERVICE_LAYER_CANNOT_OBTAIN_PATIENTS_FOR_TREATMENT_BY_NURSE = "Cannot obtain patients for treatment by nurse at service layer";

}
