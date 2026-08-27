package com.sunrisedental.service;

import com.sunrisedental.dao.ReportDAO;
import com.sunrisedental.model.ReportSummary;

import java.time.LocalDate;
import java.util.List;

public class ReportService {

    private final ReportDAO reportDAO;

    public ReportService() {

        reportDAO = new ReportDAO();
    }

    public ReportSummary getSummary(
            LocalDate fromDate,
            LocalDate toDate) {

        validateDateRange(
                fromDate,
                toDate
        );

        return reportDAO.getSummary(
                fromDate.toString(),
                toDate.toString()
        );
    }

    public List<Object[]> getAppointmentReport(
            LocalDate fromDate,
            LocalDate toDate) {

        validateDateRange(
                fromDate,
                toDate
        );

        return reportDAO.getAppointmentReport(
                fromDate.toString(),
                toDate.toString()
        );
    }

    public List<Object[]> getRevenueByTreatment(
            LocalDate fromDate,
            LocalDate toDate) {

        validateDateRange(
                fromDate,
                toDate
        );

        return reportDAO.getRevenueByTreatment(
                fromDate.toString(),
                toDate.toString()
        );
    }

    public List<Object[]> getDentistWorkload(
            LocalDate fromDate,
            LocalDate toDate) {

        validateDateRange(
                fromDate,
                toDate
        );

        return reportDAO.getDentistWorkload(
                fromDate.toString(),
                toDate.toString()
        );
    }

    public List<Object[]> getPaymentMethodReport(
            LocalDate fromDate,
            LocalDate toDate) {

        validateDateRange(
                fromDate,
                toDate
        );

        return reportDAO.getPaymentMethodReport(
                fromDate.toString(),
                toDate.toString()
        );
    }

    private void validateDateRange(
            LocalDate fromDate,
            LocalDate toDate) {

        if (fromDate == null) {

            throw new IllegalArgumentException(
                    "Please select the From Date."
            );
        }

        if (toDate == null) {

            throw new IllegalArgumentException(
                    "Please select the To Date."
            );
        }

        if (fromDate.isAfter(toDate)) {

            throw new IllegalArgumentException(
                    "From Date cannot be after To Date."
            );
        }
    }
}