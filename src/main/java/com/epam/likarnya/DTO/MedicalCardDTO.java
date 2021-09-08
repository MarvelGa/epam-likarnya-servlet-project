package com.epam.likarnya.DTO;

import java.util.Objects;

public class MedicalCardDTO {
    private Long id;
    private String complaints;
    private String diagnosis;
    private Long statementId;
    private Long doctorId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getStatementId() {
        return statementId;
    }

    public void setStatementId(Long statementId) {
        this.statementId = statementId;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicalCardDTO that = (MedicalCardDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(complaints, that.complaints) && Objects.equals(diagnosis, that.diagnosis) && Objects.equals(statementId, that.statementId) && Objects.equals(doctorId, that.doctorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, complaints, diagnosis, statementId, doctorId);
    }
}
