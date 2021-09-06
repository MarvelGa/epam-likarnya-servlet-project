package com.epam.likarnya.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Treatment {
    public enum Appointment {
        PROCEDURE, DRUG, OPERATION
    }

    public enum AppointmentStatus {
        EXECUTED, NOT_EXECUTED
    }

    private Long id;
    private Appointment appointment;
    private AppointmentStatus appointmentStatus = AppointmentStatus.NOT_EXECUTED;
    private LocalDateTime createdAt;
    private LocalDateTime changed;
    private Long executorId;
    private MedicalCard medicalCard;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public AppointmentStatus getAppointmentStatus() {
        return appointmentStatus;
    }

    public void setAppointmentStatus(AppointmentStatus appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
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

    public Long getExecutorId() {
        return executorId;
    }

    public void setExecutorId(Long executorId) {
        this.executorId = executorId;
    }

    public MedicalCard getMedicalCard() {
        return medicalCard;
    }

    public void setMedicalCard(MedicalCard medicalCard) {
        this.medicalCard = medicalCard;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Treatment treatment = (Treatment) o;
        return Objects.equals(id, treatment.id) && appointment == treatment.appointment && appointmentStatus == treatment.appointmentStatus && Objects.equals(createdAt, treatment.createdAt) && Objects.equals(changed, treatment.changed) && Objects.equals(executorId, treatment.executorId) && Objects.equals(medicalCard, treatment.medicalCard);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, appointment, appointmentStatus, createdAt, changed, executorId, medicalCard);
    }
}
