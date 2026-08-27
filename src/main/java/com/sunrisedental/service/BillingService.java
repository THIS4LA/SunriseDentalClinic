package com.sunrisedental.service;

import com.sunrisedental.dao.AppointmentDAO;
import com.sunrisedental.dao.BillDAO;
import com.sunrisedental.dao.TreatmentDAO;
import com.sunrisedental.dao.PatientDAO;

import com.sunrisedental.model.Appointment;
import com.sunrisedental.model.Bill;
import com.sunrisedental.model.Treatment;
import com.sunrisedental.model.Patient;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.sunrisedental.database.DatabaseConnection;

public class BillingService {

    private final BillDAO billDAO;

    private final AppointmentDAO appointmentDAO;

    private final TreatmentDAO treatmentDAO;

    private final PatientDAO patientDAO;

    public BillingService() {

        appointmentDAO
                = new AppointmentDAO();

        patientDAO
                = new PatientDAO();

        treatmentDAO
                = new TreatmentDAO();

        billDAO
                = new BillDAO();
    }

    public Patient getPatient(
            Appointment appointment) {

        if (appointment == null) {

            throw new IllegalArgumentException(
                    "Appointment is required."
            );
        }

        int patientId
                = appointment.getPatientId();

        if (patientId <= 0) {

            throw new IllegalArgumentException(
                    "Invalid patient ID."
            );
        }

        Patient patient
                = patientDAO.findById(
                        patientId
                );

        if (patient == null) {

            throw new IllegalArgumentException(
                    "Patient information could not be found."
            );
        }

        return patient;
    }

    public Appointment findByAppointmentNo(
            String appointmentNo) {

        String sql
                = "SELECT * FROM appointments "
                + "WHERE appointment_no = ?";

        try {

            Connection con
                    = DatabaseConnection.getConnection();

            PreparedStatement ps
                    = con.prepareStatement(sql);

            ps.setString(
                    1,
                    appointmentNo
            );

            ResultSet rs
                    = ps.executeQuery();

            if (rs.next()) {

                return new Appointment(
                        rs.getString("appointment_no"),
                        rs.getInt("patient_id"),
                        rs.getString("dentist_name"),
                        rs.getString("treatment_type"),
                        rs.getString("appointment_date"),
                        rs.getString("appointment_time"),
                        rs.getString("status"),
                        rs.getString("notes")
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }

    public Treatment getTreatment(
            Appointment appointment) {

        Treatment treatment
                = treatmentDAO.findByName(
                        appointment
                                .getTreatmentType()
                );

        if (treatment == null) {

            throw new IllegalArgumentException(
                    "Treatment pricing was not found."
            );
        }

        return treatment;
    }

    public BigDecimal calculateTotal(
            BigDecimal consultationFee,
            BigDecimal treatmentFee,
            BigDecimal discount) {

        if (discount == null) {

            discount
                    = BigDecimal.ZERO;
        }

        BigDecimal subtotal
                = consultationFee.add(
                        treatmentFee
                );

        if (discount.compareTo(
                BigDecimal.ZERO) < 0) {

            throw new IllegalArgumentException(
                    "Discount cannot be negative."
            );
        }

        if (discount.compareTo(
                subtotal) > 0) {

            throw new IllegalArgumentException(
                    "Discount cannot be greater "
                    + "than the bill amount."
            );
        }

        return subtotal.subtract(
                discount
        );
    }

    public String generateBillNumber() {

        int sequence
                = billDAO.getNextBillSequence();

        int year
                = LocalDate.now()
                        .getYear();

        return String.format(
                "BILL-%d-%04d",
                year,
                sequence
        );
    }

    public boolean saveBill(
            Bill bill) {

        if (billDAO.existsForAppointment(
                bill.getAppointmentNo())) {

            throw new IllegalArgumentException(
                    "A bill has already been generated "
                    + "for this appointment."
            );
        }

        if (bill.getPaymentMethod() == null
                || bill.getPaymentMethod()
                        .equals(
                                "Select Payment Method"
                        )) {

            throw new IllegalArgumentException(
                    "Please select a payment method."
            );
        }

        return billDAO.insert(
                bill
        );
    }

    public List<Bill> getAllBills() {

        return billDAO.getAll();
    }
}
