package com.sunrisedental.service;

import com.sunrisedental.dao.AppointmentDAO;
import com.sunrisedental.model.Appointment;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import java.util.List;

public class AppointmentService {

    private final AppointmentDAO appointmentDAO;

    public AppointmentService() {

        appointmentDAO
                = new AppointmentDAO();
    }

    public boolean addAppointment(
            Appointment appointment) {

        validateAppointment(
                appointment
        );

        boolean booked
                = appointmentDAO.isDentistBooked(
                        appointment.getDentistId(),
                        appointment.getAppointmentDate(),
                        appointment.getAppointmentTime()
                );

        if (booked) {

            throw new IllegalArgumentException(
                    "The selected dentist is already "
                    + "booked for this date and time."
            );
        }

        return appointmentDAO.insert(
                appointment
        );
    }

    public boolean updateAppointment(
            Appointment appointment) {

        validateAppointment(
                appointment
        );

        boolean booked
                = appointmentDAO
                        .isDentistBookedExcept(
                                appointment.getDentistId(),
                                appointment.getAppointmentDate(),
                                appointment.getAppointmentTime(),
                                appointment.getAppointmentNo()
                        );

        if (booked) {

            throw new IllegalArgumentException(
                    "The selected dentist is already "
                    + "booked for this date and time."
            );
        }

        return appointmentDAO.update(
                appointment
        );
    }

    public boolean cancelAppointment(
            String appointmentNo) {

        if (appointmentNo == null
                || appointmentNo.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Appointment number is required."
            );
        }

        return appointmentDAO.cancel(
                appointmentNo
        );
    }

    public List<Appointment>
            getAllAppointments() {

        return appointmentDAO.getAll();
    }

    public List<Appointment>
            searchAppointments(
                    String keyword) {

        if (keyword == null
                || keyword.trim().isEmpty()) {

            return getAllAppointments();
        }

        return appointmentDAO.search(
                keyword.trim()
        );
    }

    public String generateAppointmentNumber() {

        int sequence
                = appointmentDAO
                        .getNextAppointmentSequence();

        int year
                = LocalDate.now()
                        .getYear();

        return String.format(
                "APT-%d-%04d",
                year,
                sequence
        );
    }

    public int getTodayAppointmentCount() {

        return appointmentDAO
                .countTodayAppointments();
    }

    public int getTodayPendingCount() {

        return appointmentDAO
                .countTodayAppointmentsByStatus(
                        "PENDING"
                );
    }

    public int getTodayCompletedCount() {

        return appointmentDAO
                .countTodayAppointmentsByStatus(
                        "COMPLETED"
                );
    }

    private void validateAppointment(
            Appointment appointment) {

        if (appointment == null) {

            throw new IllegalArgumentException(
                    "Appointment information is required."
            );
        }

        if (appointment.getAppointmentNo() == null
                || appointment.getAppointmentNo()
                        .trim()
                        .isEmpty()) {

            throw new IllegalArgumentException(
                    "Appointment number is required."
            );
        }

        if (appointment.getPatientId() <= 0) {

            throw new IllegalArgumentException(
                    "Please select a valid patient."
            );
        }

        if (appointment.getDentistId() <= 0) {

            throw new IllegalArgumentException(
                    "Please select a valid dentist."
            );
        }

        if (appointment.getTreatmentType() == null
                || appointment.getTreatmentType()
                        .trim()
                        .isEmpty()
                || appointment.getTreatmentType()
                        .equalsIgnoreCase(
                                "Select Treatment"
                        )) {

            throw new IllegalArgumentException(
                    "Please select a treatment."
            );
        }

        validateDate(
                appointment.getAppointmentDate()
        );

        validateTime(
                appointment.getAppointmentTime()
        );
    }

    private void validateDate(
            String dateText) {

        if (dateText == null
                || dateText.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Appointment date is required."
            );
        }

        try {

            LocalDate appointmentDate
                    = LocalDate.parse(
                            dateText
                    );

            if (appointmentDate.isBefore(
                    LocalDate.now())) {

                throw new IllegalArgumentException(
                        "Appointment date cannot be in the past."
                );
            }

        } catch (DateTimeParseException e) {

            throw new IllegalArgumentException(
                    "Invalid appointment date."
            );
        }
    }

    private void validateTime(
            String timeText) {

        if (timeText == null
                || timeText.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Appointment time is required."
            );
        }

        try {

            LocalTime appointmentTime
                    = LocalTime.parse(
                            timeText
                    );

            LocalTime openingTime
                    = LocalTime.of(
                            8,
                            0
                    );

            LocalTime closingTime
                    = LocalTime.of(
                            18,
                            0
                    );

            if (appointmentTime.isBefore(openingTime)
                    || appointmentTime.isAfter(closingTime)) {

                throw new IllegalArgumentException(
                        "Appointment time must be between "
                        + "08:00 and 18:00."
                );
            }

        } catch (DateTimeParseException e) {

            throw new IllegalArgumentException(
                    "Invalid appointment time."
            );
        }
    }

    //Methods for Specific Dentist Appointments
    public List<Appointment> getAppointmentsForDentist(
            int dentistId) {

        if (dentistId <= 0) {

            throw new IllegalArgumentException(
                    "Invalid dentist."
            );
        }

        return appointmentDAO
                .getAppointmentsByDentistId(
                        dentistId
                );
    }

    public List<Appointment> searchAppointmentsForDentist(
            int dentistId,
            String keyword) {

        if (dentistId <= 0) {

            throw new IllegalArgumentException(
                    "Invalid dentist."
            );
        }

        if (keyword == null
                || keyword.trim().isEmpty()) {

            return getAppointmentsForDentist(
                    dentistId
            );
        }

        return appointmentDAO
                .searchAppointmentsByDentistId(
                        dentistId,
                        keyword.trim()
                );
    }

    public List<Appointment> getScheduleForDentist(
            int dentistId,
            String appointmentDate) {

        if (dentistId <= 0) {

            throw new IllegalArgumentException(
                    "Invalid dentist."
            );
        }

        if (appointmentDate == null
                || appointmentDate.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Schedule date is required."
            );
        }

        return appointmentDAO
                .getScheduleByDentistAndDate(
                        dentistId,
                        appointmentDate
                );
    }

}
