package com.sunrisedental.service;

import com.sunrisedental.dao.AdminReportDAO;

import java.math.BigDecimal;
import java.time.LocalDate;

import java.util.List;

public class AdminReportService {

    private final AdminReportDAO reportDAO;

    public AdminReportService() {

        reportDAO =
                new AdminReportDAO();
    }

    // ==========================================================
    // APPOINTMENT REPORT
    // ==========================================================

    public List<Object[]> getAppointmentSummary(
            String fromDate,
            String toDate) {

        validateDates(
                fromDate,
                toDate
        );

        return reportDAO
                .getAppointmentSummary(
                        fromDate,
                        toDate
                );
    }

    // ==========================================================
    // REVENUE REPORT
    // ==========================================================

    public List<Object[]> getRevenueByTreatment(
            String fromDate,
            String toDate) {

        validateDates(
                fromDate,
                toDate
        );

        return reportDAO
                .getRevenueByTreatment(
                        fromDate,
                        toDate
                );
    }

    // ==========================================================
    // DENTIST WORKLOAD
    // ==========================================================

    public List<Object[]> getDentistWorkload(
            String fromDate,
            String toDate) {

        validateDates(
                fromDate,
                toDate
        );

        return reportDAO
                .getDentistWorkload(
                        fromDate,
                        toDate
                );
    }

    // ==========================================================
    // PAYMENT SUMMARY
    // ==========================================================

    public List<Object[]> getPaymentMethodSummary(
            String fromDate,
            String toDate) {

        validateDates(
                fromDate,
                toDate
        );

        return reportDAO
                .getPaymentMethodSummary(
                        fromDate,
                        toDate
                );
    }

    // ==========================================================
    // TOTAL REVENUE
    // ==========================================================

    public BigDecimal getTotalRevenue(
            String fromDate,
            String toDate) {

        validateDates(
                fromDate,
                toDate
        );

        return reportDAO
                .getTotalRevenue(
                        fromDate,
                        toDate
                );
    }

    // ==========================================================
    // VALIDATE DATE RANGE
    // ==========================================================

    private void validateDates(
            String fromDate,
            String toDate) {

        if (fromDate == null
                || fromDate.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "From date is required."
            );
        }

        if (toDate == null
                || toDate.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "To date is required."
            );
        }

        LocalDate from =
                LocalDate.parse(
                        fromDate
                );

        LocalDate to =
                LocalDate.parse(
                        toDate
                );

        if (from.isAfter(
                to)) {

            throw new IllegalArgumentException(
                    "From date cannot be after To date."
            );
        }
    }
}