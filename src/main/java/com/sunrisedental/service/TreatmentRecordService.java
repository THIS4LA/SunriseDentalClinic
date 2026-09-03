package com.sunrisedental.service;

import com.sunrisedental.dao.TreatmentRecordDAO;
import com.sunrisedental.model.TreatmentRecord;

import java.util.List;

public class TreatmentRecordService {

    private final TreatmentRecordDAO treatmentRecordDAO;

    public TreatmentRecordService() {

        treatmentRecordDAO =
                new TreatmentRecordDAO();
    }

    public boolean addRecord(
            TreatmentRecord record) {

        validateRecord(
                record
        );

        if (treatmentRecordDAO
                .existsForAppointment(
                        record.getAppointmentNo()
                )) {

            throw new IllegalArgumentException(
                    "A treatment record already exists "
                    + "for this appointment."
            );
        }

        return treatmentRecordDAO.insert(
                record
        );
    }

    public boolean updateRecord(
            TreatmentRecord record) {

        if (record == null
                || record.getRecordId() <= 0) {

            throw new IllegalArgumentException(
                    "Please select a valid treatment record."
            );
        }

        validateRecord(
                record
        );

        return treatmentRecordDAO.update(
                record
        );
    }

    public List<TreatmentRecord> getRecordsForDentist(
            int dentistId) {

        if (dentistId <= 0) {

            throw new IllegalArgumentException(
                    "Invalid dentist."
            );
        }

        return treatmentRecordDAO
                .getRecordsByDentistId(
                        dentistId
                );
    }

    public List<TreatmentRecord> searchRecordsForDentist(
            int dentistId,
            String keyword) {

        if (dentistId <= 0) {

            throw new IllegalArgumentException(
                    "Invalid dentist."
            );
        }

        if (keyword == null
                || keyword.trim().isEmpty()) {

            return getRecordsForDentist(
                    dentistId
            );
        }

        return treatmentRecordDAO
                .searchByDentistId(
                        dentistId,
                        keyword.trim()
                );
    }

    public TreatmentRecord getByAppointmentNo(
            String appointmentNo,
            int dentistId) {

        if (appointmentNo == null
                || appointmentNo.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Appointment number is required."
            );
        }

        return treatmentRecordDAO
                .findByAppointmentNo(
                        appointmentNo.trim(),
                        dentistId
                );
    }

    private void validateRecord(
            TreatmentRecord record) {

        if (record == null) {

            throw new IllegalArgumentException(
                    "Treatment record information is required."
            );
        }

        if (record.getAppointmentNo() == null
                || record.getAppointmentNo()
                        .trim()
                        .isEmpty()) {

            throw new IllegalArgumentException(
                    "Appointment number is required."
            );
        }

        if (record.getPatientId() <= 0) {

            throw new IllegalArgumentException(
                    "Invalid patient."
            );
        }

        if (record.getDentistId() <= 0) {

            throw new IllegalArgumentException(
                    "Invalid dentist."
            );
        }

        if (record.getDiagnosis() == null
                || record.getDiagnosis()
                        .trim()
                        .isEmpty()) {

            throw new IllegalArgumentException(
                    "Diagnosis is required."
            );
        }

        if (record.getTreatmentPerformed() == null
                || record.getTreatmentPerformed()
                        .trim()
                        .isEmpty()) {

            throw new IllegalArgumentException(
                    "Treatment performed is required."
            );
        }

        if (record.isFollowUpRequired()
                && (record.getFollowUpDate() == null
                || record.getFollowUpDate()
                        .trim()
                        .isEmpty())) {

            throw new IllegalArgumentException(
                    "Please select a follow-up date."
            );
        }
    }
}