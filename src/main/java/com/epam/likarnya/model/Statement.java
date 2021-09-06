package com.epam.likarnya.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Statement {
    public enum PatientStatus {
        NEW, DIAGNOSED ,DISCHARGED
    }

    private Long id;
    private Patient patient;
    private PatientStatus patientStatus = PatientStatus.NEW;
    private LocalDateTime createdAt;
    private LocalDateTime changed;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public PatientStatus getPatientStatus() {
        return patientStatus;
    }

    public void setPatientStatus(PatientStatus patientStatus) {
        this.patientStatus = patientStatus;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getChanged() {
        return changed;
    }

    public void setChanged(LocalDateTime changed) {
        this.changed = changed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Statement statement = (Statement) o;
        return Objects.equals(id, statement.id) && Objects.equals(patient, statement.patient) && patientStatus == statement.patientStatus && Objects.equals(createdAt, statement.createdAt) && Objects.equals(changed, statement.changed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, patient, patientStatus, createdAt, changed);
    }
}
