package com.sunrisedental.service;

import com.sunrisedental.dao.TreatmentDAO;
import com.sunrisedental.model.Treatment;

import java.math.BigDecimal;
import java.util.List;

public class TreatmentService {

    private final TreatmentDAO treatmentDAO;

    public TreatmentService() {

        treatmentDAO =
                new TreatmentDAO();
    }

    // ==========================================================
    // ADD TREATMENT
    // ==========================================================

    public boolean addTreatment(
            Treatment treatment) {

        validateTreatment(
                treatment
        );

        if (treatmentDAO
                .treatmentNameExists(
                        treatment
                                .getTreatmentName()
                                .trim()
                )) {

            throw new IllegalArgumentException(
                    "Treatment already exists."
            );
        }

        return treatmentDAO
                .insertTreatment(
                        treatment
                );
    }

    // ==========================================================
    // UPDATE TREATMENT
    // ==========================================================

    public boolean updateTreatment(
            Treatment treatment) {

        if (treatment == null
                || treatment.getTreatmentId() <= 0) {

            throw new IllegalArgumentException(
                    "Invalid treatment."
            );
        }

        validateTreatment(
                treatment
        );

        return treatmentDAO
                .updateTreatment(
                        treatment
                );
    }

    // ==========================================================
    // GET ALL
    // ==========================================================

    public List<Treatment> getAllTreatments() {

        return treatmentDAO
                .getAllTreatments();
    }

    // ==========================================================
    // GET ACTIVE
    // ==========================================================

    public List<Treatment> getActiveTreatments() {

        return treatmentDAO
                .getActiveTreatments();
    }

    // ==========================================================
    // SEARCH
    // ==========================================================

    public List<Treatment> searchTreatments(
            String keyword) {

        if (keyword == null
                || keyword.trim().isEmpty()) {

            return getAllTreatments();
        }

        return treatmentDAO
                .searchTreatments(
                        keyword.trim()
                );
    }

    // ==========================================================
    // ACTIVATE
    // ==========================================================

    public boolean activateTreatment(
            int treatmentId) {

        if (treatmentId <= 0) {

            throw new IllegalArgumentException(
                    "Invalid treatment."
            );
        }

        return treatmentDAO
                .updateStatus(
                        treatmentId,
                        "ACTIVE"
                );
    }

    // ==========================================================
    // DEACTIVATE
    // ==========================================================

    public boolean deactivateTreatment(
            int treatmentId) {

        if (treatmentId <= 0) {

            throw new IllegalArgumentException(
                    "Invalid treatment."
            );
        }

        return treatmentDAO
                .updateStatus(
                        treatmentId,
                        "INACTIVE"
                );
    }

    // ==========================================================
    // FIND BY NAME
    // ==========================================================

    public Treatment getTreatmentByName(
            String treatmentName) {

        if (treatmentName == null
                || treatmentName.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Treatment name is required."
            );
        }

        return treatmentDAO
                .findByName(
                        treatmentName.trim()
                );
    }

    // ==========================================================
    // VALIDATION
    // ==========================================================

    private void validateTreatment(
            Treatment treatment) {

        if (treatment == null) {

            throw new IllegalArgumentException(
                    "Treatment information is required."
            );
        }

        if (treatment.getTreatmentName() == null
                || treatment
                        .getTreatmentName()
                        .trim()
                        .isEmpty()) {

            throw new IllegalArgumentException(
                    "Treatment name is required."
            );
        }

        BigDecimal consultationFee =
                treatment.getConsultationFee();

        if (consultationFee == null) {

            throw new IllegalArgumentException(
                    "Consultation fee is required."
            );
        }

        if (consultationFee.compareTo(
                BigDecimal.ZERO) < 0) {

            throw new IllegalArgumentException(
                    "Consultation fee cannot be negative."
            );
        }

        BigDecimal treatmentFee =
                treatment.getTreatmentFee();

        if (treatmentFee == null) {

            throw new IllegalArgumentException(
                    "Treatment fee is required."
            );
        }

        if (treatmentFee.compareTo(
                BigDecimal.ZERO) < 0) {

            throw new IllegalArgumentException(
                    "Treatment fee cannot be negative."
            );
        }

        if (treatment.getStatus() == null
                || (!treatment
                        .getStatus()
                        .equalsIgnoreCase(
                                "ACTIVE"
                        )
                && !treatment
                        .getStatus()
                        .equalsIgnoreCase(
                                "INACTIVE"
                        ))) {

            throw new IllegalArgumentException(
                    "Invalid treatment status."
            );
        }
    }
}