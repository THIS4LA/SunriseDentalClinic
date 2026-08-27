package com.sunrisedental.model;

import java.math.BigDecimal;

public class Bill {

    private int billId;

    private String billNo;

    private String appointmentNo;

    private BigDecimal consultationFee;

    private BigDecimal treatmentFee;

    private BigDecimal discount;

    private BigDecimal totalAmount;

    private String paymentMethod;

    private String paymentStatus;

    private String createdAt;

    public Bill() {
    }

    public Bill(
            String billNo,
            String appointmentNo,
            BigDecimal consultationFee,
            BigDecimal treatmentFee,
            BigDecimal discount,
            BigDecimal totalAmount,
            String paymentMethod,
            String paymentStatus) {

        this.billNo = billNo;
        this.appointmentNo = appointmentNo;
        this.consultationFee = consultationFee;
        this.treatmentFee = treatmentFee;
        this.discount = discount;
        this.totalAmount = totalAmount;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public String getBillNo() {
        return billNo;
    }

    public String getAppointmentNo() {
        return appointmentNo;
    }

    public BigDecimal getConsultationFee() {
        return consultationFee;
    }

    public BigDecimal getTreatmentFee() {
        return treatmentFee;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(
            String createdAt) {

        this.createdAt = createdAt;
    }
}