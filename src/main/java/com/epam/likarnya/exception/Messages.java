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


    public static final String ERR_SERVICE_LAYER_CANNOT_OBTAIN_USER_BY_EMAIL = "Cannot obtain a user by its email at service layer";
    public static final String ERR_SERVICE_LAYER_CANNOT_INSERT_USER = "Cannot insert new user at service layer";
    public static final String ERR_SERVICE_LAYER_CANNOT_READ_ALL_USERS = "Cannot read all users at service layer";
    public static final String ERR_SERVICE_LAYER_CANNOT_READ_ALL_PATIENT = "Cannot read all patients at service layer";
    public static final String ERR_SERVICE_LAYER_CANNOT_READ_PATIENT_BY_ID = "Cannot read patient by id at service layer";
}
