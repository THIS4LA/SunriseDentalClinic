package com.sunrisedental.service;

import com.sunrisedental.dao.PatientDAO;
import com.sunrisedental.model.Patient;

import java.util.List;

public class PatientService {

    private final PatientDAO patientDAO;

    public PatientService() {
        patientDAO = new PatientDAO();
    }

    public boolean addPatient(Patient patient) {

        validatePatient(patient);

        return patientDAO.insert(patient);
    }

    public boolean updatePatient(Patient patient) {

        if (patient.getPatientId() <= 0) {
            throw new IllegalArgumentException(
                    "Please select a patient first."
            );
        }

        validatePatient(patient);

        return patientDAO.update(patient);
    }

    public boolean deletePatient(int patientId) {

        if (patientId <= 0) {
            throw new IllegalArgumentException(
                    "Please select a patient first."
            );
        }

        return patientDAO.delete(patientId);
    }

    public List<Patient> getAllPatients() {
        return patientDAO.getAll();
    }

    public List<Patient> searchPatients(String keyword) {

        if (keyword == null
                || keyword.trim().isEmpty()) {

            return getAllPatients();
        }

        return patientDAO.search(
                keyword.trim()
        );
    }

    private void validatePatient(Patient patient) {

        if (patient.getName() == null
                || patient.getName().trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Patient name is required."
            );
        }

        if (patient.getAddress() == null
                || patient.getAddress().trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Address is required."
            );
        }

        if (patient.getContactNumber() == null
                || patient.getContactNumber().trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Contact number is required."
            );
        }

        if (!patient.getContactNumber()
                .matches("\\d{10}")) {

            throw new IllegalArgumentException(
                    "Contact number must contain 10 digits."
            );
        }
    }
}