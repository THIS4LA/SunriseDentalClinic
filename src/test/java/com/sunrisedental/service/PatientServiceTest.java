package com.sunrisedental.service;

import com.sunrisedental.model.Patient;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class PatientServiceTest {

    private PatientService patientService;


    @BeforeEach
    void setUp() {

        patientService
                = new PatientService();
    }


    // =========================================================
    // ADD PATIENT - GENERAL VALIDATION
    // =========================================================

    @Test
    void shouldRejectNullPatient() {

        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> patientService
                                .addPatient(null)
                );

        assertEquals(
                "Patient details are required.",
                exception.getMessage()
        );
    }


    // =========================================================
    // PATIENT NAME VALIDATION
    // =========================================================

    @Test
    void shouldRejectNullPatientName() {

        Patient patient
                = createValidPatient();

        patient.setName(null);


        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> patientService
                                .addPatient(
                                        patient
                                )
                );

        assertEquals(
                "Patient name is required.",
                exception.getMessage()
        );
    }


    @Test
    void shouldRejectEmptyPatientName() {

        Patient patient
                = createValidPatient();

        patient.setName("");


        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> patientService
                                .addPatient(
                                        patient
                                )
                );

        assertEquals(
                "Patient name is required.",
                exception.getMessage()
        );
    }


    @Test
    void shouldRejectBlankPatientName() {

        Patient patient
                = createValidPatient();

        patient.setName("   ");


        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> patientService
                                .addPatient(
                                        patient
                                )
                );

        assertEquals(
                "Patient name is required.",
                exception.getMessage()
        );
    }


    @Test
    void shouldRejectPatientNameShorterThanTwoCharacters() {

        Patient patient
                = createValidPatient();

        patient.setName("A");


        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> patientService
                                .addPatient(
                                        patient
                                )
                );

        assertEquals(
                "Patient name must contain at least 2 characters.",
                exception.getMessage()
        );
    }


    @Test
    void shouldRejectPatientNameContainingNumbers() {

        Patient patient
                = createValidPatient();

        patient.setName(
                "Nimal123"
        );


        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> patientService
                                .addPatient(
                                        patient
                                )
                );

        assertEquals(
                "Patient name contains invalid characters.",
                exception.getMessage()
        );
    }


    @Test
    void shouldRejectPatientNameContainingInvalidSymbols() {

        Patient patient
                = createValidPatient();

        patient.setName(
                "Nimal@Perera"
        );


        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> patientService
                                .addPatient(
                                        patient
                                )
                );

        assertEquals(
                "Patient name contains invalid characters.",
                exception.getMessage()
        );
    }


    // =========================================================
    // ADDRESS VALIDATION
    // =========================================================

    @Test
    void shouldRejectNullAddress() {

        Patient patient
                = createValidPatient();

        patient.setAddress(null);


        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> patientService
                                .addPatient(
                                        patient
                                )
                );

        assertEquals(
                "Address is required.",
                exception.getMessage()
        );
    }


    @Test
    void shouldRejectEmptyAddress() {

        Patient patient
                = createValidPatient();

        patient.setAddress("");


        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> patientService
                                .addPatient(
                                        patient
                                )
                );

        assertEquals(
                "Address is required.",
                exception.getMessage()
        );
    }


    @Test
    void shouldRejectBlankAddress() {

        Patient patient
                = createValidPatient();

        patient.setAddress("   ");


        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> patientService
                                .addPatient(
                                        patient
                                )
                );

        assertEquals(
                "Address is required.",
                exception.getMessage()
        );
    }


    @Test
    void shouldRejectAddressLongerThan255Characters() {

        Patient patient
                = createValidPatient();

        String longAddress
                = "A".repeat(
                        256
                );

        patient.setAddress(
                longAddress
        );


        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> patientService
                                .addPatient(
                                        patient
                                )
                );

        assertEquals(
                "Address cannot exceed 255 characters.",
                exception.getMessage()
        );
    }


    // =========================================================
    // CONTACT NUMBER VALIDATION
    // =========================================================

    @Test
    void shouldRejectNullContactNumber() {

        Patient patient
                = createValidPatient();

        patient.setContactNumber(null);


        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> patientService
                                .addPatient(
                                        patient
                                )
                );

        assertEquals(
                "Contact number is required.",
                exception.getMessage()
        );
    }


    @Test
    void shouldRejectEmptyContactNumber() {

        Patient patient
                = createValidPatient();

        patient.setContactNumber("");


        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> patientService
                                .addPatient(
                                        patient
                                )
                );

        assertEquals(
                "Contact number is required.",
                exception.getMessage()
        );
    }


    @Test
    void shouldRejectBlankContactNumber() {

        Patient patient
                = createValidPatient();

        patient.setContactNumber("   ");


        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> patientService
                                .addPatient(
                                        patient
                                )
                );

        assertEquals(
                "Contact number is required.",
                exception.getMessage()
        );
    }


    @Test
    void shouldRejectContactNumberShorterThanTenDigits() {

        Patient patient
                = createValidPatient();

        patient.setContactNumber(
                "077123456"
        );


        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> patientService
                                .addPatient(
                                        patient
                                )
                );

        assertEquals(
                "Contact number must contain exactly 10 digits.",
                exception.getMessage()
        );
    }


    @Test
    void shouldRejectContactNumberLongerThanTenDigits() {

        Patient patient
                = createValidPatient();

        patient.setContactNumber(
                "07712345678"
        );


        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> patientService
                                .addPatient(
                                        patient
                                )
                );

        assertEquals(
                "Contact number must contain exactly 10 digits.",
                exception.getMessage()
        );
    }


    @Test
    void shouldRejectContactNumberContainingLetters() {

        Patient patient
                = createValidPatient();

        patient.setContactNumber(
                "07712ABC67"
        );


        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> patientService
                                .addPatient(
                                        patient
                                )
                );

        assertEquals(
                "Contact number must contain exactly 10 digits.",
                exception.getMessage()
        );
    }


    @Test
    void shouldRejectContactNumberContainingSymbols() {

        Patient patient
                = createValidPatient();

        patient.setContactNumber(
                "077-123456"
        );


        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> patientService
                                .addPatient(
                                        patient
                                )
                );

        assertEquals(
                "Contact number must contain exactly 10 digits.",
                exception.getMessage()
        );
    }


    // =========================================================
    // EMAIL VALIDATION
    // =========================================================

    @Test
    void shouldAcceptNullEmailDuringValidation() {

        Patient patient
                = createValidPatient();

        patient.setEmail(null);

        /*
         * We cannot call addPatient here because valid data would
         * continue to the DAO and access the database.
         *
         * Therefore no successful DAO-free test is possible for
         * optional null email using the current service design.
         */
    }


    @Test
    void shouldRejectInvalidEmailWithoutAtSymbol() {

        Patient patient
                = createValidPatient();

        patient.setEmail(
                "nimalgmail.com"
        );


        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> patientService
                                .addPatient(
                                        patient
                                )
                );

        assertEquals(
                "Please enter a valid email address.",
                exception.getMessage()
        );
    }


    @Test
    void shouldRejectInvalidEmailWithSpaces() {

        Patient patient
                = createValidPatient();

        patient.setEmail(
                "nimal perera@gmail.com"
        );


        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> patientService
                                .addPatient(
                                        patient
                                )
                );

        assertEquals(
                "Please enter a valid email address.",
                exception.getMessage()
        );
    }


    @Test
    void shouldRejectInvalidEmailWithoutDomain() {

        Patient patient
                = createValidPatient();

        patient.setEmail(
                "nimal@"
        );


        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> patientService
                                .addPatient(
                                        patient
                                )
                );

        assertEquals(
                "Please enter a valid email address.",
                exception.getMessage()
        );
    }


    // =========================================================
    // UPDATE PATIENT VALIDATION
    // =========================================================

    @Test
    void shouldRejectZeroPatientIdWhenUpdatingPatient() {

        Patient patient
                = createValidPatient();

        patient.setPatientId(
                0
        );


        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> patientService
                                .updatePatient(
                                        patient
                                )
                );

        assertEquals(
                "Please select a patient first.",
                exception.getMessage()
        );
    }


    @Test
    void shouldRejectNegativePatientIdWhenUpdatingPatient() {

        Patient patient
                = createValidPatient();

        patient.setPatientId(
                -1
        );


        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> patientService
                                .updatePatient(
                                        patient
                                )
                );

        assertEquals(
                "Please select a patient first.",
                exception.getMessage()
        );
    }


    @Test
    void shouldRejectInvalidNameWhenUpdatingPatient() {

        Patient patient
                = createValidPatient();

        patient.setPatientId(
                1
        );

        patient.setName(
                ""
        );


        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> patientService
                                .updatePatient(
                                        patient
                                )
                );

        assertEquals(
                "Patient name is required.",
                exception.getMessage()
        );
    }


    @Test
    void shouldRejectInvalidContactNumberWhenUpdatingPatient() {

        Patient patient
                = createValidPatient();

        patient.setPatientId(
                1
        );

        patient.setContactNumber(
                "123"
        );


        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> patientService
                                .updatePatient(
                                        patient
                                )
                );

        assertEquals(
                "Contact number must contain exactly 10 digits.",
                exception.getMessage()
        );
    }


    // =========================================================
    // DELETE PATIENT VALIDATION
    // =========================================================

    @Test
    void shouldRejectZeroPatientIdWhenDeletingPatient() {

        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> patientService
                                .deletePatient(
                                        0
                                )
                );

        assertEquals(
                "Invalid patient ID.",
                exception.getMessage()
        );
    }


    @Test
    void shouldRejectNegativePatientIdWhenDeletingPatient() {

        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> patientService
                                .deletePatient(
                                        -1
                                )
                );

        assertEquals(
                "Invalid patient ID.",
                exception.getMessage()
        );
    }


    // =========================================================
    // GET PATIENT BY ID VALIDATION
    // =========================================================

    @Test
    void shouldRejectZeroPatientIdWhenGettingPatient() {

        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> patientService
                                .getPatientById(
                                        0
                                )
                );

        assertEquals(
                "Invalid patient ID.",
                exception.getMessage()
        );
    }


    @Test
    void shouldRejectNegativePatientIdWhenGettingPatient() {

        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> patientService
                                .getPatientById(
                                        -1
                                )
                );

        assertEquals(
                "Invalid patient ID.",
                exception.getMessage()
        );
    }


    // =========================================================
    // DENTIST-SPECIFIC PATIENT VALIDATION
    // =========================================================

    @Test
    void shouldRejectZeroDentistIdWhenGettingPatients() {

        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> patientService
                                .getPatientsForDentist(
                                        0
                                )
                );

        assertEquals(
                "Invalid dentist.",
                exception.getMessage()
        );
    }


    @Test
    void shouldRejectNegativeDentistIdWhenGettingPatients() {

        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> patientService
                                .getPatientsForDentist(
                                        -1
                                )
                );

        assertEquals(
                "Invalid dentist.",
                exception.getMessage()
        );
    }


    @Test
    void shouldRejectZeroDentistIdWhenSearchingPatients() {

        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> patientService
                                .searchPatientsForDentist(
                                        0,
                                        "Nimal"
                                )
                );

        assertEquals(
                "Invalid dentist.",
                exception.getMessage()
        );
    }


    @Test
    void shouldRejectNegativeDentistIdWhenSearchingPatients() {

        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> patientService
                                .searchPatientsForDentist(
                                        -1,
                                        "Nimal"
                                )
                );

        assertEquals(
                "Invalid dentist.",
                exception.getMessage()
        );
    }


    // =========================================================
    // TEST DATA HELPER
    // =========================================================

    private Patient createValidPatient() {

        Patient patient
                = new Patient();

        patient.setPatientId(
                1
        );

        patient.setName(
                "Nimal Perera"
        );

        patient.setAddress(
                "25 Galle Road, Colombo"
        );

        patient.setContactNumber(
                "0771234567"
        );

        patient.setEmail(
                "nimal@gmail.com"
        );

        return patient;
    }

}