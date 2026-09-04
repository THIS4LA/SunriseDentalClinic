package com.sunrisedental.model;

import java.math.BigDecimal;

public class Treatment {

    private int treatmentId;

    private String treatmentName;

    private BigDecimal consultationFee;

    private BigDecimal treatmentFee;

    private String status;

    public Treatment() {
    }

    public Treatment(
            int treatmentId,
            String treatmentName,
            BigDecimal consultationFee,
            BigDecimal treatmentFee,
            String status) {

        this.treatmentId = treatmentId;
        this.treatmentName = treatmentName;
        this.consultationFee = consultationFee;
        this.treatmentFee = treatmentFee;
        this.status = status;
    }

    public int getTreatmentId() {
        return treatmentId;
    }

    public String getTreatmentName() {
        return treatmentName;
    }

    public BigDecimal getConsultationFee() {
        return consultationFee;
    }

    public BigDecimal getTreatmentFee() {
        return treatmentFee;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {

        return treatmentName;
    }
}
