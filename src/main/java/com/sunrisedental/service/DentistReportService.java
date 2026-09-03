package com.sunrisedental.service;

import com.sunrisedental.dao.DentistReportDAO;

import java.time.LocalDate;

import java.util.List;

public class DentistReportService {

    private final DentistReportDAO reportDAO;

    public DentistReportService() {

        reportDAO =
                new DentistReportDAO();
    }

    public List<Object[]> getAppointmentSummary(
            int dentistId,
            String fromDate,
            String toDate) {

        validate(
                dentistId,
                fromDate,
                toDate
        );

        return reportDAO
                .getAppointmentSummary(
                        dentistId,
                        fromDate,
                        toDate
                );
    }

    public List<Object[]> getTreatmentSummary(
            int dentistId,
            String fromDate,
            String toDate) {

        validate(
                dentistId,
                fromDate,
                toDate
        );

        return reportDAO
                .getTreatmentSummary(
                        dentistId,
                        fromDate,
                        toDate
                );
    }

    public List<Object[]> getStatusSummary(
            int dentistId,
            String fromDate,
            String toDate) {

        validate(
                dentistId,
                fromDate,
                toDate
        );

        return reportDAO
                .getStatusSummary(
                        dentistId,
                        fromDate,
                        toDate
                );
    }

    public int getPatientCount(
            int dentistId,
            String fromDate,
            String toDate) {

        validate(
                dentistId,
                fromDate,
                toDate
        );

        return reportDAO
                .getPatientCount(
                        dentistId,
                        fromDate,
                        toDate
                );
    }

    private void validate(
            int dentistId,
            String fromDate,
            String toDate) {

        if (dentistId <= 0) {

            throw new IllegalArgumentException(
                    "Invalid dentist."
            );
        }

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

        if (from.isAfter(to)) {

            throw new IllegalArgumentException(
                    "From date cannot be after To date."
            );
        }
    }
}