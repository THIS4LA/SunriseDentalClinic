package com.sunrisedental.model;

public class TreatmentRecord {

    private int recordId;
    private String appointmentNo;
    private int patientId;
    private int dentistId;

    private String diagnosis;
    private String treatmentPerformed;
    private String clinicalNotes;
    private String recommendation;

    private boolean followUpRequired;
    private String followUpDate;

    public TreatmentRecord() {
    }

    public TreatmentRecord(
            int recordId,
            String appointmentNo,
            int patientId,
            int dentistId,
            String diagnosis,
            String treatmentPerformed,
            String clinicalNotes,
            String recommendation,
            boolean followUpRequired,
            String followUpDate) {

        this.recordId = recordId;
        this.appointmentNo = appointmentNo;
        this.patientId = patientId;
        this.dentistId = dentistId;
        this.diagnosis = diagnosis;
        this.treatmentPerformed = treatmentPerformed;
        this.clinicalNotes = clinicalNotes;
        this.recommendation = recommendation;
        this.followUpRequired = followUpRequired;
        this.followUpDate = followUpDate;
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public String getAppointmentNo() {
        return appointmentNo;
    }

    public void setAppointmentNo(
            String appointmentNo) {

        this.appointmentNo = appointmentNo;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(
            int patientId) {

        this.patientId = patientId;
    }

    public int getDentistId() {
        return dentistId;
    }

    public void setDentistId(
            int dentistId) {

        this.dentistId = dentistId;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(
            String diagnosis) {

        this.diagnosis = diagnosis;
    }

    public String getTreatmentPerformed() {
        return treatmentPerformed;
    }

    public void setTreatmentPerformed(
            String treatmentPerformed) {

        this.treatmentPerformed
                = treatmentPerformed;
    }

    public String getClinicalNotes() {
        return clinicalNotes;
    }

    public void setClinicalNotes(
            String clinicalNotes) {

        this.clinicalNotes
                = clinicalNotes;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(
            String recommendation) {

        this.recommendation
                = recommendation;
    }

    public boolean isFollowUpRequired() {
        return followUpRequired;
    }

    public void setFollowUpRequired(
            boolean followUpRequired) {

        this.followUpRequired
                = followUpRequired;
    }

    public String getFollowUpDate() {
        return followUpDate;
    }

    public void setFollowUpDate(
            String followUpDate) {

        this.followUpDate
                = followUpDate;
    }
}
