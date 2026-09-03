package com.sunrisedental.service;

import com.sunrisedental.dao.DentistDAO;
import com.sunrisedental.model.Dentist;

import java.util.List;

public class DentistService {

    private final DentistDAO dentistDAO;

    public DentistService() {

        dentistDAO
                = new DentistDAO();
    }

    // ==========================================================
    // ADD DENTIST
    // ==========================================================
    public boolean addDentist(
            Dentist dentist) {

        validateDentist(
                dentist
        );

        if (dentist.getUserId() <= 0) {

            throw new IllegalArgumentException(
                    "A valid user account is required."
            );
        }

        if (dentistDAO.existsByUserId(
                dentist.getUserId())) {

            throw new IllegalArgumentException(
                    "This user account is already linked "
                    + "to a dentist."
            );
        }

        if (dentist.getEmail() != null
                && !dentist.getEmail().trim().isEmpty()
                && dentistDAO.existsByEmail(
                        dentist.getEmail().trim())) {

            throw new IllegalArgumentException(
                    "This email address is already used "
                    + "by another dentist."
            );
        }

        return dentistDAO.insert(
                dentist
        );
    }

    // ==========================================================
    // UPDATE DENTIST
    // ==========================================================
    public boolean updateDentist(
            Dentist dentist) {

        if (dentist == null) {

            throw new IllegalArgumentException(
                    "Dentist information is required."
            );
        }

        if (dentist.getDentistId() <= 0) {

            throw new IllegalArgumentException(
                    "Invalid dentist."
            );
        }

        validateDentist(
                dentist
        );

        Dentist existing
                = dentistDAO.findByDentistId(
                        dentist.getDentistId()
                );

        if (existing == null) {

            throw new IllegalArgumentException(
                    "Dentist was not found."
            );
        }

        if (dentist.getEmail() != null
                && !dentist.getEmail().trim().isEmpty()) {

            Dentist dentistByUser
                    = dentistDAO.findByUserId(
                            dentist.getUserId()
                    );

            /*
             * We don't reject the dentist's own email here.
             * If you later need stronger duplicate-email validation,
             * add existsByEmailExceptDentist().
             */
        }

        return dentistDAO.update(
                dentist
        );
    }

    public boolean updateOwnProfile(
            int dentistId,
            String contactNumber,
            String email) {

        if (dentistId <= 0) {

            throw new IllegalArgumentException(
                    "Invalid dentist."
            );
        }

        if (contactNumber == null
                || contactNumber.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Contact number is required."
            );
        }

        contactNumber
                = contactNumber.trim();

        if (!contactNumber.matches(
                "^[0-9+\\- ]{9,15}$")) {

            throw new IllegalArgumentException(
                    "Invalid contact number."
            );
        }

        if (email != null
                && !email.trim().isEmpty()) {

            email
                    = email.trim();

            if (!email.matches(
                    "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {

                throw new IllegalArgumentException(
                        "Invalid email address."
                );
            }

        } else {

            email = "";
        }

        return dentistDAO
                .updateOwnProfile(
                        dentistId,
                        contactNumber,
                        email
                );
    }

    // ==========================================================
    // GET BY DENTIST ID
    // ==========================================================
    public Dentist getDentistById(
            int dentistId) {

        if (dentistId <= 0) {

            throw new IllegalArgumentException(
                    "Invalid dentist ID."
            );
        }

        Dentist dentist
                = dentistDAO.findByDentistId(
                        dentistId
                );

        if (dentist == null) {

            throw new IllegalArgumentException(
                    "Dentist was not found."
            );
        }

        return dentist;
    }

    // ==========================================================
    // GET DENTIST PROFILE USING LOGIN USER ID
    // ==========================================================
    public Dentist getDentistByUserId(
            int userId) {

        if (userId <= 0) {

            throw new IllegalArgumentException(
                    "Invalid logged-in user."
            );
        }

        Dentist dentist
                = dentistDAO.findByUserId(
                        userId
                );

        if (dentist == null) {

            throw new IllegalArgumentException(
                    "Dentist profile was not found "
                    + "for this user account."
            );
        }

        return dentist;
    }

    // ==========================================================
    // GET DENTIST ID FROM LOGIN USER ID
    // ==========================================================
    public int getDentistIdByUserId(
            int userId) {

        if (userId <= 0) {

            throw new IllegalArgumentException(
                    "Invalid logged-in user."
            );
        }

        int dentistId
                = dentistDAO.getDentistIdByUserId(
                        userId
                );

        if (dentistId <= 0) {

            throw new IllegalArgumentException(
                    "Dentist profile was not found "
                    + "for this user account."
            );
        }

        return dentistId;
    }

    // ==========================================================
    // GET ALL
    // ==========================================================
    public List<Dentist> getAllDentists() {

        return dentistDAO.getAll();
    }

    // ==========================================================
    // GET ACTIVE
    // ==========================================================
    public List<Dentist> getActiveDentists() {

        return dentistDAO.getActiveDentists();
    }

    // ==========================================================
    // SEARCH
    // ==========================================================
    public List<Dentist> searchDentists(
            String keyword) {

        if (keyword == null
                || keyword.trim().isEmpty()) {

            return getAllDentists();
        }

        return dentistDAO.search(
                keyword.trim()
        );
    }

    // ==========================================================
    // ACTIVATE
    // ==========================================================
    public boolean activateDentist(
            int dentistId) {

        validateDentistId(
                dentistId
        );

        return dentistDAO.updateStatus(
                dentistId,
                "ACTIVE"
        );
    }

    // ==========================================================
    // DEACTIVATE
    // ==========================================================
    public boolean deactivateDentist(
            int dentistId) {

        validateDentistId(
                dentistId
        );

        return dentistDAO.updateStatus(
                dentistId,
                "INACTIVE"
        );
    }

    // ==========================================================
    // VALIDATION
    // ==========================================================
    private void validateDentist(
            Dentist dentist) {

        if (dentist == null) {

            throw new IllegalArgumentException(
                    "Dentist information is required."
            );
        }

        if (dentist.getName() == null
                || dentist.getName()
                        .trim()
                        .isEmpty()) {

            throw new IllegalArgumentException(
                    "Dentist name is required."
            );
        }

        if (dentist.getName()
                .trim()
                .length() < 2) {

            throw new IllegalArgumentException(
                    "Dentist name is too short."
            );
        }

        if (dentist.getSpecialization() == null
                || dentist.getSpecialization()
                        .trim()
                        .isEmpty()) {

            throw new IllegalArgumentException(
                    "Specialization is required."
            );
        }

        if (dentist.getContactNumber() == null
                || dentist.getContactNumber()
                        .trim()
                        .isEmpty()) {

            throw new IllegalArgumentException(
                    "Contact number is required."
            );
        }

        String contactNumber
                = dentist.getContactNumber()
                        .trim();

        if (!contactNumber.matches(
                "^[0-9+\\- ]{9,15}$")) {

            throw new IllegalArgumentException(
                    "Invalid contact number."
            );
        }

        if (dentist.getEmail() != null
                && !dentist.getEmail()
                        .trim()
                        .isEmpty()) {

            String email
                    = dentist.getEmail()
                            .trim();

            if (!email.matches(
                    "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {

                throw new IllegalArgumentException(
                        "Invalid email address."
                );
            }
        }

        if (dentist.getStatus() == null
                || dentist.getStatus()
                        .trim()
                        .isEmpty()) {

            dentist.setStatus(
                    "ACTIVE"
            );
        }

        String status
                = dentist.getStatus()
                        .trim()
                        .toUpperCase();

        if (!status.equals("ACTIVE")
                && !status.equals("INACTIVE")) {

            throw new IllegalArgumentException(
                    "Dentist status must be ACTIVE "
                    + "or INACTIVE."
            );
        }

        dentist.setStatus(
                status
        );
    }

    private void validateDentistId(
            int dentistId) {

        if (dentistId <= 0) {

            throw new IllegalArgumentException(
                    "Invalid dentist ID."
            );
        }

        Dentist dentist
                = dentistDAO.findByDentistId(
                        dentistId
                );

        if (dentist == null) {

            throw new IllegalArgumentException(
                    "Dentist was not found."
            );
        }
    }
}
