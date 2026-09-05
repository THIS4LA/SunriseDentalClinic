package com.sunrisedental.service;

import com.sunrisedental.model.Appointment;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class BillingServiceTest {

    private BillingService billingService;


    @BeforeEach
    void setUp() {

        billingService
                = new BillingService();
    }


    // =========================================================
    // CALCULATE TOTAL TESTS
    // =========================================================

    @Test
    void shouldCalculateBillTotalWithoutDiscount() {

        BigDecimal consultationFee
                = new BigDecimal(
                        "1500.00"
                );

        BigDecimal treatmentFee
                = new BigDecimal(
                        "5000.00"
                );

        BigDecimal discount
                = BigDecimal.ZERO;


        BigDecimal total
                = billingService
                        .calculateTotal(
                                consultationFee,
                                treatmentFee,
                                discount
                        );


        assertEquals(
                new BigDecimal(
                        "6500.00"
                ),
                total
        );
    }


    @Test
    void shouldCalculateBillTotalWithDiscount() {

        BigDecimal consultationFee
                = new BigDecimal(
                        "1500.00"
                );

        BigDecimal treatmentFee
                = new BigDecimal(
                        "5000.00"
                );

        BigDecimal discount
                = new BigDecimal(
                        "500.00"
                );


        BigDecimal total
                = billingService
                        .calculateTotal(
                                consultationFee,
                                treatmentFee,
                                discount
                        );


        assertEquals(
                new BigDecimal(
                        "6000.00"
                ),
                total
        );
    }


    @Test
    void shouldTreatNullDiscountAsZero() {

        BigDecimal consultationFee
                = new BigDecimal(
                        "1500.00"
                );

        BigDecimal treatmentFee
                = new BigDecimal(
                        "5000.00"
                );


        BigDecimal total
                = billingService
                        .calculateTotal(
                                consultationFee,
                                treatmentFee,
                                null
                        );


        assertEquals(
                new BigDecimal(
                        "6500.00"
                ),
                total
        );
    }


    @Test
    void shouldAllowZeroConsultationFee() {

        BigDecimal consultationFee
                = new BigDecimal(
                        "0.00"
                );

        BigDecimal treatmentFee
                = new BigDecimal(
                        "5000.00"
                );

        BigDecimal discount
                = BigDecimal.ZERO;


        BigDecimal total
                = billingService
                        .calculateTotal(
                                consultationFee,
                                treatmentFee,
                                discount
                        );


        assertEquals(
                new BigDecimal(
                        "5000.00"
                ),
                total
        );
    }


    @Test
    void shouldAllowZeroTreatmentFee() {

        BigDecimal consultationFee
                = new BigDecimal(
                        "1500.00"
                );

        BigDecimal treatmentFee
                = new BigDecimal(
                        "0.00"
                );

        BigDecimal discount
                = BigDecimal.ZERO;


        BigDecimal total
                = billingService
                        .calculateTotal(
                                consultationFee,
                                treatmentFee,
                                discount
                        );


        assertEquals(
                new BigDecimal(
                        "1500.00"
                ),
                total
        );
    }


    @Test
    void shouldAllowDiscountEqualToSubtotal() {

        BigDecimal consultationFee
                = new BigDecimal(
                        "1500.00"
                );

        BigDecimal treatmentFee
                = new BigDecimal(
                        "5000.00"
                );

        BigDecimal discount
                = new BigDecimal(
                        "6500.00"
                );


        BigDecimal total
                = billingService
                        .calculateTotal(
                                consultationFee,
                                treatmentFee,
                                discount
                        );


        assertEquals(
                new BigDecimal(
                        "0.00"
                ),
                total
        );
    }


    @Test
    void shouldRejectNegativeDiscount() {

        BigDecimal consultationFee
                = new BigDecimal(
                        "1500.00"
                );

        BigDecimal treatmentFee
                = new BigDecimal(
                        "5000.00"
                );

        BigDecimal discount
                = new BigDecimal(
                        "-100.00"
                );


        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> billingService
                                .calculateTotal(
                                        consultationFee,
                                        treatmentFee,
                                        discount
                                )
                );


        assertEquals(
                "Discount cannot be negative.",
                exception.getMessage()
        );
    }


    @Test
    void shouldRejectDiscountGreaterThanSubtotal() {

        BigDecimal consultationFee
                = new BigDecimal(
                        "1500.00"
                );

        BigDecimal treatmentFee
                = new BigDecimal(
                        "5000.00"
                );

        BigDecimal discount
                = new BigDecimal(
                        "7000.00"
                );


        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> billingService
                                .calculateTotal(
                                        consultationFee,
                                        treatmentFee,
                                        discount
                                )
                );


        assertEquals(
                "Discount cannot be greater "
                + "than the bill amount.",
                exception.getMessage()
        );
    }


    // =========================================================
    // GET PATIENT VALIDATION TESTS
    // =========================================================

    @Test
    void shouldRejectNullAppointmentWhenGettingPatient() {

        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> billingService
                                .getPatient(
                                        null
                                )
                );


        assertEquals(
                "Appointment is required.",
                exception.getMessage()
        );
    }


    @Test
    void shouldRejectZeroPatientIdWhenGettingPatient() {

        Appointment appointment
                = createAppointment();

        appointment.setPatientId(
                0
        );


        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> billingService
                                .getPatient(
                                        appointment
                                )
                );


        assertEquals(
                "Invalid patient ID.",
                exception.getMessage()
        );
    }


    @Test
    void shouldRejectNegativePatientIdWhenGettingPatient() {

        Appointment appointment
                = createAppointment();

        appointment.setPatientId(
                -1
        );


        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> billingService
                                .getPatient(
                                        appointment
                                )
                );


        assertEquals(
                "Invalid patient ID.",
                exception.getMessage()
        );
    }


    // =========================================================
    // GET DENTIST VALIDATION TESTS
    // =========================================================

    @Test
    void shouldRejectNullAppointmentWhenGettingDentist() {

        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> billingService
                                .getDentist(
                                        null
                                )
                );


        assertEquals(
                "Appointment information is required.",
                exception.getMessage()
        );
    }


    // =========================================================
    // GET BILL BY ID VALIDATION TESTS
    // =========================================================

    @Test
    void shouldRejectZeroBillId() {

        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> billingService
                                .getBillById(
                                        0
                                )
                );


        assertEquals(
                "Invalid bill.",
                exception.getMessage()
        );
    }


    @Test
    void shouldRejectNegativeBillId() {

        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> billingService
                                .getBillById(
                                        -1
                                )
                );


        assertEquals(
                "Invalid bill.",
                exception.getMessage()
        );
    }


    // =========================================================
    // UPDATE PAYMENT DETAILS - BILL ID TESTS
    // =========================================================

    @Test
    void shouldRejectZeroBillIdWhenUpdatingPaymentDetails() {

        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> billingService
                                .updatePaymentDetails(
                                        0,
                                        "CASH",
                                        "PAID"
                                )
                );


        assertEquals(
                "Invalid bill.",
                exception.getMessage()
        );
    }


    @Test
    void shouldRejectNegativeBillIdWhenUpdatingPaymentDetails() {

        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> billingService
                                .updatePaymentDetails(
                                        -1,
                                        "CASH",
                                        "PAID"
                                )
                );


        assertEquals(
                "Invalid bill.",
                exception.getMessage()
        );
    }


    // =========================================================
    // UPDATE PAYMENT DETAILS - PAYMENT METHOD TESTS
    // =========================================================

    @Test
    void shouldRejectNullPaymentMethod() {

        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> billingService
                                .updatePaymentDetails(
                                        1,
                                        null,
                                        "PAID"
                                )
                );


        assertEquals(
                "Payment method is required.",
                exception.getMessage()
        );
    }


    @Test
    void shouldRejectEmptyPaymentMethod() {

        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> billingService
                                .updatePaymentDetails(
                                        1,
                                        "",
                                        "PAID"
                                )
                );


        assertEquals(
                "Payment method is required.",
                exception.getMessage()
        );
    }


    @Test
    void shouldRejectBlankPaymentMethod() {

        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> billingService
                                .updatePaymentDetails(
                                        1,
                                        "   ",
                                        "PAID"
                                )
                );


        assertEquals(
                "Payment method is required.",
                exception.getMessage()
        );
    }


    @Test
    void shouldRejectInvalidPaymentMethod() {

        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> billingService
                                .updatePaymentDetails(
                                        1,
                                        "CHEQUE",
                                        "PAID"
                                )
                );


        assertEquals(
                "Invalid payment method.",
                exception.getMessage()
        );
    }


    @Test
    void shouldRejectInvalidOnlinePaymentMethod() {

        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> billingService
                                .updatePaymentDetails(
                                        1,
                                        "ONLINE",
                                        "PAID"
                                )
                );


        assertEquals(
                "Invalid payment method.",
                exception.getMessage()
        );
    }


    // =========================================================
    // UPDATE PAYMENT DETAILS - PAYMENT STATUS TESTS
    // =========================================================

    @Test
    void shouldRejectNullPaymentStatus() {

        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> billingService
                                .updatePaymentDetails(
                                        1,
                                        "CASH",
                                        null
                                )
                );


        assertEquals(
                "Payment status is required.",
                exception.getMessage()
        );
    }


    @Test
    void shouldRejectEmptyPaymentStatus() {

        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> billingService
                                .updatePaymentDetails(
                                        1,
                                        "CASH",
                                        ""
                                )
                );


        assertEquals(
                "Payment status is required.",
                exception.getMessage()
        );
    }


    @Test
    void shouldRejectBlankPaymentStatus() {

        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> billingService
                                .updatePaymentDetails(
                                        1,
                                        "CASH",
                                        "   "
                                )
                );


        assertEquals(
                "Payment status is required.",
                exception.getMessage()
        );
    }


    @Test
    void shouldRejectInvalidPaymentStatus() {

        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> billingService
                                .updatePaymentDetails(
                                        1,
                                        "CASH",
                                        "CANCELLED"
                                )
                );


        assertEquals(
                "Invalid payment status.",
                exception.getMessage()
        );
    }


    @Test
    void shouldRejectCompletedAsPaymentStatus() {

        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        () -> billingService
                                .updatePaymentDetails(
                                        1,
                                        "CARD",
                                        "COMPLETED"
                                )
                );


        assertEquals(
                "Invalid payment status.",
                exception.getMessage()
        );
    }


    // =========================================================
    // TEST DATA HELPER
    // =========================================================

    private Appointment createAppointment() {

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

        return appointment;
    }

}