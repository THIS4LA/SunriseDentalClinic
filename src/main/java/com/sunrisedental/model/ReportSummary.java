package com.sunrisedental.model;

import java.math.BigDecimal;

public class ReportSummary {

    private int totalAppointments;
    private int completedAppointments;
    private int pendingAppointments;
    private int cancelledAppointments;
    private BigDecimal totalRevenue;

    public ReportSummary() {

        totalRevenue = BigDecimal.ZERO;
    }

    public int getTotalAppointments() {
        return totalAppointments;
    }

    public void setTotalAppointments(
            int totalAppointments) {

        this.totalAppointments =
                totalAppointments;
    }

    public int getCompletedAppointments() {
        return completedAppointments;
    }

    public void setCompletedAppointments(
            int completedAppointments) {

        this.completedAppointments =
                completedAppointments;
    }

    public int getPendingAppointments() {
        return pendingAppointments;
    }

    public void setPendingAppointments(
            int pendingAppointments) {

        this.pendingAppointments =
                pendingAppointments;
    }

    public int getCancelledAppointments() {
        return cancelledAppointments;
    }

    public void setCancelledAppointments(
            int cancelledAppointments) {

        this.cancelledAppointments =
                cancelledAppointments;
    }

    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(
            BigDecimal totalRevenue) {

        this.totalRevenue =
                totalRevenue;
    }
}