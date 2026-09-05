package com.sunrisedental.service;

import com.sunrisedental.model.Appointment;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class AppointmentServiceTest {

    private AppointmentService appointmentService;


    @BeforeEach
    void setUp() {

        appointmentService
                = new AppointmentService();
    }


    // =========================================================
    // ADD APPOINTMENT VALIDATION TESTS
    // =========================================================

    @Test
    void shouldRejectNullAppointment() {

        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> appointmentService
                                .addAppointment(null)
                );

        assertEquals(
                "Appointment information is required.",
                exception.getMessage()
        );
    }


    @Test
    void shouldRejectMissingAppointmentNumber() {

        Appointment appointment
                = createValidAppointment();

        appointment.setAppointmentNo("");


        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> appointmentService
                                .addAppointment(
                                        appointment
                                )
                );

        assertEquals(
                "Appointment number is required.",
                exception.getMessage()
        );
    }


    @Test
    void shouldRejectNullAppointmentNumber() {

        Appointment appointment
                = createValidAppointment();

        appointment.setAppointmentNo(null);


        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> appointmentService
                                .addAppointment(
                                        appointment
                                )
                );

        assertEquals(
                "Appointment number is required.",
                exception.getMessage()
        );
    }


    @Test
    void shouldRejectInvalidPatientId() {

        Appointment appointment
                = createValidAppointment();

        appointment.setPatientId(0);


        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> appointmentService
                                .addAppointment(
                                        appointment
                                )
                );

        assertEquals(
                "Please select a valid patient.",
                exception.getMessage()
        );
    }


    @Test
    void shouldRejectNegativePatientId() {

        Appointment appointment
                = createValidAppointment();

        appointment.setPatientId(-1);


        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> appointmentService
                                .addAppointment(
                                        appointment
                                )
                );

        assertEquals(
                "Please select a valid patient.",
                exception.getMessage()
        );
    }


    @Test
    void shouldRejectInvalidDentistId() {

        Appointment appointment
                = createValidAppointment();

        appointment.setDentistId(0);


        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> appointmentService
                                .addAppointment(
                                        appointment
                                )
                );

        assertEquals(
                "Please select a valid dentist.",
                exception.getMessage()
        );
    }


    @Test
    void shouldRejectNegativeDentistId() {

        Appointment appointment
                = createValidAppointment();

        appointment.setDentistId(-1);


        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> appointmentService
                                .addAppointment(
                                        appointment
                                )
                );

        assertEquals(
                "Please select a valid dentist.",
                exception.getMessage()
        );
    }


    @Test
    void shouldRejectNullTreatment() {

        Appointment appointment
                = createValidAppointment();

        appointment.setTreatmentType(null);


        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> appointmentService
                                .addAppointment(
                                        appointment
                                )
                );

        assertEquals(
                "Please select a treatment.",
                exception.getMessage()
        );
    }


    @Test
    void shouldRejectEmptyTreatment() {

        Appointment appointment
                = createValidAppointment();

        appointment.setTreatmentType("");


        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> appointmentService
                                .addAppointment(
                                        appointment
                                )
                );

        assertEquals(
                "Please select a treatment.",
                exception.getMessage()
        );
    }


    @Test
    void shouldRejectBlankTreatment() {

        Appointment appointment
                = createValidAppointment();

        appointment.setTreatmentType("   ");


        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> appointmentService
                                .addAppointment(
                                        appointment
                                )
                );

        assertEquals(
                "Please select a treatment.",
                exception.getMessage()
        );
    }


    @Test
    void shouldRejectDefaultTreatmentOption() {

        Appointment appointment
                = createValidAppointment();

        appointment.setTreatmentType(
                "Select Treatment"
        );


        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> appointmentService
                                .addAppointment(
                                        appointment
                                )
                );

        assertEquals(
                "Please select a treatment.",
                exception.getMessage()
        );
    }


    // =========================================================
    // DATE VALIDATION TESTS
    // =========================================================

    @Test
    void shouldRejectNullAppointmentDate() {

        Appointment appointment
                = createValidAppointment();

        appointment.setAppointmentDate(null);


        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> appointmentService
                                .addAppointment(
                                        appointment
                                )
                );

        assertEquals(
                "Appointment date is required.",
                exception.getMessage()
        );
    }


    @Test
    void shouldRejectEmptyAppointmentDate() {

        Appointment appointment
                = createValidAppointment();

        appointment.setAppointmentDate("");


        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> appointmentService
                                .addAppointment(
                                        appointment
                                )
                );

        assertEquals(
                "Appointment date is required.",
                exception.getMessage()
        );
    }


    @Test
    void shouldRejectInvalidAppointmentDateFormat() {

        Appointment appointment
                = createValidAppointment();

        appointment.setAppointmentDate(
                "10/12/2026"
        );


        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> appointmentService
                                .addAppointment(
                                        appointment
                                )
                );

        assertEquals(
                "Invalid appointment date.",
                exception.getMessage()
        );
    }


    @Test
    void shouldRejectInvalidTextAppointmentDate() {

        Appointment appointment
                = createValidAppointment();

        appointment.setAppointmentDate(
                "invalid-date"
        );


        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> appointmentService
                                .addAppointment(
                                        appointment
                                )
                );

        assertEquals(
                "Invalid appointment date.",
                exception.getMessage()
        );
    }


    @Test
    void shouldRejectPastAppointmentDate() {

        Appointment appointment
                = createValidAppointment();

        appointment.setAppointmentDate(
                LocalDate.now()
                        .minusDays(1)
                        .toString()
        );


        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> appointmentService
                                .addAppointment(
                                        appointment
                                )
                );

        assertEquals(
                "Appointment date cannot be in the past.",
                exception.getMessage()
        );
    }


    // =========================================================
    // TIME VALIDATION TESTS
    // =========================================================

    @Test
    void shouldRejectNullAppointmentTime() {

        Appointment appointment
                = createValidAppointment();

        appointment.setAppointmentTime(null);


        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> appointmentService
                                .addAppointment(
                                        appointment
                                )
                );

        assertEquals(
                "Appointment time is required.",
                exception.getMessage()
        );
    }


    @Test
    void shouldRejectEmptyAppointmentTime() {

        Appointment appointment
                = createValidAppointment();

        appointment.setAppointmentTime("");


        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> appointmentService
                                .addAppointment(
                                        appointment
                                )
                );

        assertEquals(
                "Appointment time is required.",
                exception.getMessage()
        );
    }


    @Test
    void shouldRejectInvalidAppointmentTimeFormat() {

        Appointment appointment
                = createValidAppointment();

        appointment.setAppointmentTime(
                "10.30"
        );


        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> appointmentService
                                .addAppointment(
                                        appointment
                                )
                );

        assertEquals(
                "Invalid appointment time.",
                exception.getMessage()
        );
    }


    @Test
    void shouldRejectInvalidTextAppointmentTime() {

        Appointment appointment
                = createValidAppointment();

        appointment.setAppointmentTime(
                "morning"
        );


        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> appointmentService
                                .addAppointment(
                                        appointment
                                )
                );

        assertEquals(
                "Invalid appointment time.",
                exception.getMessage()
        );
    }


    @Test
    void shouldRejectAppointmentBeforeOpeningTime() {

        Appointment appointment
                = createValidAppointment();

        appointment.setAppointmentTime(
                "07:59"
        );


        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> appointmentService
                                .addAppointment(
                                        appointment
                                )
                );

        assertEquals(
                "Appointment time must be between "
                + "08:00 and 18:00.",
                exception.getMessage()
        );
    }


    @Test
    void shouldRejectAppointmentAfterClosingTime() {

        Appointment appointment
                = createValidAppointment();

        appointment.setAppointmentTime(
                "18:01"
        );


        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> appointmentService
                                .addAppointment(
                                        appointment
                                )
                );

        assertEquals(
                "Appointment time must be between "
                + "08:00 and 18:00.",
                exception.getMessage()
        );
    }


    // =========================================================
    // CANCEL APPOINTMENT TESTS
    // =========================================================

    @Test
    void shouldRejectNullAppointmentNumberWhenCancelling() {

        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> appointmentService
                                .cancelAppointment(null)
                );

        assertEquals(
                "Appointment number is required.",
                exception.getMessage()
        );
    }


    @Test
    void shouldRejectEmptyAppointmentNumberWhenCancelling() {

        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> appointmentService
                                .cancelAppointment("")
                );

        assertEquals(
                "Appointment number is required.",
                exception.getMessage()
        );
    }


    @Test
    void shouldRejectBlankAppointmentNumberWhenCancelling() {

        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> appointmentService
                                .cancelAppointment("   ")
                );

        assertEquals(
                "Appointment number is required.",
                exception.getMessage()
        );
    }


    // =========================================================
    // DENTIST APPOINTMENT TESTS
    // =========================================================

    @Test
    void shouldRejectInvalidDentistIdWhenGettingAppointments() {

        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> appointmentService
                                .getAppointmentsForDentist(
                                        0
                                )
                );

        assertEquals(
                "Invalid dentist.",
                exception.getMessage()
        );
    }


    @Test
    void shouldRejectNegativeDentistIdWhenGettingAppointments() {

        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> appointmentService
                                .getAppointmentsForDentist(
                                        -1
                                )
                );

        assertEquals(
                "Invalid dentist.",
                exception.getMessage()
        );
    }


    @Test
    void shouldRejectInvalidDentistIdWhenSearchingAppointments() {

        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> appointmentService
                                .searchAppointmentsForDentist(
                                        0,
                                        "APT"
                                )
                );

        assertEquals(
                "Invalid dentist.",
                exception.getMessage()
        );
    }


    // =========================================================
    // DENTIST SCHEDULE TESTS
    // =========================================================

    @Test
    void shouldRejectInvalidDentistIdWhenLoadingSchedule() {

        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> appointmentService
                                .getScheduleForDentist(
                                        0,
                                        LocalDate.now()
                                                .toString()
                                )
                );

        assertEquals(
                "Invalid dentist.",
                exception.getMessage()
        );
    }


    @Test
    void shouldRejectNullScheduleDate() {

        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> appointmentService
                                .getScheduleForDentist(
                                        1,
                                        null
                                )
                );

        assertEquals(
                "Schedule date is required.",
                exception.getMessage()
        );
    }


    @Test
    void shouldRejectEmptyScheduleDate() {

        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> appointmentService
                                .getScheduleForDentist(
                                        1,
                                        ""
                                )
                );

        assertEquals(
                "Schedule date is required.",
                exception.getMessage()
        );
    }


    @Test
    void shouldRejectBlankScheduleDate() {

        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> appointmentService
                                .getScheduleForDentist(
                                        1,
                                        "   "
                                )
                );

        assertEquals(
                "Schedule date is required.",
                exception.getMessage()
        );
    }


    // =========================================================
    // DENTIST DASHBOARD COUNT TESTS
    // =========================================================

    @Test
    void shouldRejectInvalidDentistIdForTodayAppointmentCount() {

        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> appointmentService
                                .getTodayAppointmentCountForDentist(
                                        0
                                )
                );

        assertEquals(
                "Invalid dentist.",
                exception.getMessage()
        );
    }


    @Test
    void shouldRejectInvalidDentistIdForTodayPendingCount() {

        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> appointmentService
                                .getTodayPendingCountForDentist(
                                        0
                                )
                );

        assertEquals(
                "Invalid dentist.",
                exception.getMessage()
        );
    }


    @Test
    void shouldRejectInvalidDentistIdForTodayCompletedCount() {

        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> appointmentService
                                .getTodayCompletedCountForDentist(
                                        0
                                )
                );

        assertEquals(
                "Invalid dentist.",
                exception.getMessage()
        );
    }


    // =========================================================
    // UPDATE STATUS TESTS
    // =========================================================

    @Test
    void shouldRejectNullAppointmentNumberWhenUpdatingStatus() {

        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> appointmentService
                                .updateAppointmentStatus(
                                        null,
                                        "COMPLETED"
                                )
                );

        assertEquals(
                "Appointment number is required.",
                exception.getMessage()
        );
    }


    @Test
    void shouldRejectEmptyAppointmentNumberWhenUpdatingStatus() {

        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> appointmentService
                                .updateAppointmentStatus(
                                        "",
                                        "COMPLETED"
                                )
                );

        assertEquals(
                "Appointment number is required.",
                exception.getMessage()
        );
    }


    @Test
    void shouldRejectNullAppointmentStatus() {

        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> appointmentService
                                .updateAppointmentStatus(
                                        "APT-2026-0001",
                                        null
                                )
                );

        assertEquals(
                "Appointment status is required.",
                exception.getMessage()
        );
    }


    @Test
    void shouldRejectEmptyAppointmentStatus() {

        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> appointmentService
                                .updateAppointmentStatus(
                                        "APT-2026-0001",
                                        ""
                                )
                );

        assertEquals(
                "Appointment status is required.",
                exception.getMessage()
        );
    }


    @Test
    void shouldRejectBlankAppointmentStatus() {

        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> appointmentService
                                .updateAppointmentStatus(
                                        "APT-2026-0001",
                                        "   "
                                )
                );

        assertEquals(
                "Appointment status is required.",
                exception.getMessage()
        );
    }


    // =========================================================
    // FIND APPOINTMENT TESTS
    // =========================================================

    @Test
    void shouldRejectNullAppointmentNumberWhenFindingAppointment() {

        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> appointmentService
                                .findByAppointmentNo(
                                        null
                                )
                );

        assertEquals(
                "Appointment number is required.",
                exception.getMessage()
        );
    }


    @Test
    void shouldRejectEmptyAppointmentNumberWhenFindingAppointment() {

        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> appointmentService
                                .findByAppointmentNo(
                                        ""
                                )
                );

        assertEquals(
                "Appointment number is required.",
                exception.getMessage()
        );
    }


    @Test
    void shouldRejectBlankAppointmentNumberWhenFindingAppointment() {

        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> appointmentService
                                .findByAppointmentNo(
                                        "   "
                                )
                );

        assertEquals(
                "Appointment number is required.",
                exception.getMessage()
        );
    }


    // =========================================================
    // TEST DATA HELPER
    // =========================================================

    private Appointment createValidAppointment() {

        Appointment appointment
                = new Appointment();

        appointment.setAppointmentNo(
                "APT-TEST-0001"
        );

        appointment.setPatientId(
                1
        );

        appointment.setDentistId(
                1
        );

        appointment.setTreatmentType(
                "Dental Cleaning"
        );

        /*
         * Future date is used so that this test data does not
         * become invalid tomorrow.
         */
        appointment.setAppointmentDate(
                LocalDate.now()
                        .plusDays(7)
                        .toString()
        );

        appointment.setAppointmentTime(
                "10:00"
        );

        return appointment;
    }

}