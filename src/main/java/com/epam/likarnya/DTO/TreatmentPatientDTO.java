package com.epam.likarnya.DTO;

import java.util.Objects;

public class TreatmentPatientDTO {
    private Long id;

    private String firstName;

    private String lastName;

    private String dateOfBirth;

    private String gender;

    private String complaints;

    private String diagnosis;

    private String appointment;

    private String appointmentStatus;

    private String doctorFirstName;

    private String doctorLastName;

    private String doctorCategory;

    private String treatmentId;

    private String statementId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getComplaints() {
        return complaints;
    }

    public void setComplaints(String complaints) {
        this.complaints = complaints;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getAppointment() {
        return appointment;
    }

    public void setAppointment(String appointment) {
        this.appointment = appointment;
    }

    public String getAppointmentStatus() {
        return appointmentStatus;
    }

    public void setAppointmentStatus(String appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }

    public String getDoctorFirstName() {
        return doctorFirstName;
    }

    public void setDoctorFirstName(String doctorFirstName) {
        this.doctorFirstName = doctorFirstName;
    }

    public String getDoctorLastName() {
        return doctorLastName;
    }

    public void setDoctorLastName(String doctorLastName) {
        this.doctorLastName = doctorLastName;
    }

    public String getDoctorCategory() {
        return doctorCategory;
    }

    public void setDoctorCategory(String doctorCategory) {
        this.doctorCategory = doctorCategory;
    }

    public String getTreatmentId() {
        return treatmentId;
    }

    public void setTreatmentId(String treatmentId) {
        this.treatmentId = treatmentId;
    }

    public String getStatementId() {
        return statementId;
    }

    public void setStatementId(String statementId) {
        this.statementId = statementId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TreatmentPatientDTO that = (TreatmentPatientDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(dateOfBirth, that.dateOfBirth) && Objects.equals(gender, that.gender) && Objects.equals(complaints, that.complaints) && Objects.equals(diagnosis, that.diagnosis) && Objects.equals(appointment, that.appointment) && Objects.equals(appointmentStatus, that.appointmentStatus) && Objects.equals(doctorFirstName, that.doctorFirstName) && Objects.equals(doctorLastName, that.doctorLastName) && Objects.equals(doctorCategory, that.doctorCategory) && Objects.equals(treatmentId, that.treatmentId) && Objects.equals(statementId, that.statementId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, dateOfBirth, gender, complaints, diagnosis, appointment, appointmentStatus, doctorFirstName, doctorLastName, doctorCategory, treatmentId, statementId);
    }
}
