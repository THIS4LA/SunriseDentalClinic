package com.sunrisedental.service;

import com.sunrisedental.dao.PatientDAO;
import com.sunrisedental.model.Patient;

import java.util.List;

public class PatientService {

    private final PatientDAO patientDAO;

    public PatientService() {

        patientDAO =
                new PatientDAO();
    }

    public boolean addPatient(
            Patient patient) {

        validatePatient(
                patient
        );

        return patientDAO.insert(
                patient
        );
    }

    public boolean updatePatient(
            Patient patient) {

        if (patient.getPatientId() <= 0) {

            throw new IllegalArgumentException(
                    "Please select a patient first."
            );
        }

        validatePatient(
                patient
        );

        return patientDAO.update(
                patient
        );
    }

    public boolean deletePatient(
            int patientId) {

        if (patientId <= 0) {

            throw new IllegalArgumentException(
                    "Invalid patient ID."
            );
        }

        Patient patient =
                patientDAO.findById(
                        patientId
                );

        if (patient == null) {

            throw new IllegalArgumentException(
                    "Patient not found."
            );
        }

        return patientDAO.delete(
                patientId
        );
    }

    public Patient getPatientById(
            int patientId) {

        if (patientId <= 0) {

            throw new IllegalArgumentException(
                    "Invalid patient ID."
            );
        }

        return patientDAO.findById(
                patientId
        );
    }

    public List<Patient> getAllPatients() {

        return patientDAO.getAll();
    }

    public List<Patient> searchPatients(
            String keyword) {

        if (keyword == null
                || keyword.trim().isEmpty()) {

            return getAllPatients();
        }

        return patientDAO.search(
                keyword.trim()
        );
    }

    private void validatePatient(
            Patient patient) {

        if (patient == null) {

            throw new IllegalArgumentException(
                    "Patient details are required."
            );
        }

        validateName(
                patient.getName()
        );

        validateAddress(
                patient.getAddress()
        );

        validateContactNumber(
                patient.getContactNumber()
        );

        validateEmail(
                patient.getEmail()
        );
    }

    private void validateName(
            String name) {

        if (name == null
                || name.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Patient name is required."
            );
        }

        if (name.trim().length() < 2) {

            throw new IllegalArgumentException(
                    "Patient name must contain at least 2 characters."
            );
        }

        if (!name.matches(
                "[A-Za-z .'-]+"
        )) {

            throw new IllegalArgumentException(
                    "Patient name contains invalid characters."
            );
        }
    }

    private void validateAddress(
            String address) {

        if (address == null
                || address.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Address is required."
            );
        }

        if (address.trim().length() > 255) {

            throw new IllegalArgumentException(
                    "Address cannot exceed 255 characters."
            );
        }
    }

    private void validateContactNumber(
            String contactNumber) {

        if (contactNumber == null
                || contactNumber.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Contact number is required."
            );
        }

        if (!contactNumber.matches(
                "\\d{10}"
        )) {

            throw new IllegalArgumentException(
                    "Contact number must contain exactly 10 digits."
            );
        }
    }

    private void validateEmail(
            String email) {

        if (email == null
                || email.trim().isEmpty()) {

            return;
        }

        if (!email.matches(
                "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"
        )) {

            throw new IllegalArgumentException(
                    "Please enter a valid email address."
            );
        }
    }
}