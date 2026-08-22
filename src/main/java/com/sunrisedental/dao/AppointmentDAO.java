package com.sunrisedental.dao;

import com.sunrisedental.database.DatabaseConnection;
import com.sunrisedental.model.Appointment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

public class AppointmentDAO {

    // ----------------------------------------------------
    // INSERT APPOINTMENT
    // ----------------------------------------------------

    public boolean insert(Appointment appointment) {

        String sql =
                "INSERT INTO appointments "
                + "(appointment_no, "
                + "patient_name, "
                + "dentist_name, "
                + "treatment_type, "
                + "appointment_date, "
                + "appointment_time, "
                + "status, "
                + "notes) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {

            Connection con =
                    DatabaseConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(
                    1,
                    appointment.getAppointmentNo()
            );

            ps.setString(
                    2,
                    appointment.getPatientName()
            );

            ps.setString(
                    3,
                    appointment.getDentistName()
            );

            ps.setString(
                    4,
                    appointment.getTreatmentType()
            );

            ps.setString(
                    5,
                    appointment.getAppointmentDate()
            );

            ps.setString(
                    6,
                    appointment.getAppointmentTime()
            );

            ps.setString(
                    7,
                    appointment.getStatus()
            );

            ps.setString(
                    8,
                    appointment.getNotes()
            );

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    // ----------------------------------------------------
    // UPDATE APPOINTMENT
    // ----------------------------------------------------

    public boolean update(Appointment appointment) {

        String sql =
                "UPDATE appointments SET "
                + "patient_name = ?, "
                + "dentist_name = ?, "
                + "treatment_type = ?, "
                + "appointment_date = ?, "
                + "appointment_time = ?, "
                + "notes = ? "
                + "WHERE appointment_no = ?";

        try {

            Connection con =
                    DatabaseConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(
                    1,
                    appointment.getPatientName()
            );

            ps.setString(
                    2,
                    appointment.getDentistName()
            );

            ps.setString(
                    3,
                    appointment.getTreatmentType()
            );

            ps.setString(
                    4,
                    appointment.getAppointmentDate()
            );

            ps.setString(
                    5,
                    appointment.getAppointmentTime()
            );

            ps.setString(
                    6,
                    appointment.getNotes()
            );

            ps.setString(
                    7,
                    appointment.getAppointmentNo()
            );

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    // ----------------------------------------------------
    // CANCEL APPOINTMENT
    // ----------------------------------------------------

    public boolean cancel(String appointmentNo) {

        String sql =
                "UPDATE appointments "
                + "SET status = 'CANCELLED' "
                + "WHERE appointment_no = ?";

        try {

            Connection con =
                    DatabaseConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(
                    1,
                    appointmentNo
            );

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    // ----------------------------------------------------
    // GET ALL APPOINTMENTS
    // ----------------------------------------------------

    public List<Appointment> getAll() {

        List<Appointment> appointments =
                new ArrayList<>();

        String sql =
                "SELECT * FROM appointments "
                + "ORDER BY appointment_date DESC, "
                + "appointment_time DESC";

        try {

            Connection con =
                    DatabaseConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                Appointment appointment =
                        createAppointmentFromResultSet(rs);

                appointments.add(
                        appointment
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return appointments;
    }

    // ----------------------------------------------------
    // SEARCH APPOINTMENTS
    // ----------------------------------------------------

    public List<Appointment> search(
            String keyword) {

        List<Appointment> appointments =
                new ArrayList<>();

        String sql =
                "SELECT * FROM appointments "
                + "WHERE appointment_no LIKE ? "
                + "OR patient_name LIKE ? "
                + "OR dentist_name LIKE ? "
                + "OR treatment_type LIKE ? "
                + "OR status LIKE ? "
                + "ORDER BY appointment_date DESC, "
                + "appointment_time DESC";

        try {

            Connection con =
                    DatabaseConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            String searchValue =
                    "%" + keyword + "%";

            ps.setString(
                    1,
                    searchValue
            );

            ps.setString(
                    2,
                    searchValue
            );

            ps.setString(
                    3,
                    searchValue
            );

            ps.setString(
                    4,
                    searchValue
            );

            ps.setString(
                    5,
                    searchValue
            );

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                Appointment appointment =
                        createAppointmentFromResultSet(rs);

                appointments.add(
                        appointment
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return appointments;
    }

    // ----------------------------------------------------
    // CHECK DOUBLE BOOKING
    // ----------------------------------------------------

    public boolean isDentistBooked(
            String dentistName,
            String appointmentDate,
            String appointmentTime) {

        String sql =
                "SELECT COUNT(*) "
                + "FROM appointments "
                + "WHERE dentist_name = ? "
                + "AND appointment_date = ? "
                + "AND appointment_time = ? "
                + "AND status <> 'CANCELLED'";

        try {

            Connection con =
                    DatabaseConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(
                    1,
                    dentistName
            );

            ps.setString(
                    2,
                    appointmentDate
            );

            ps.setString(
                    3,
                    appointmentTime
            );

            ResultSet rs =
                    ps.executeQuery();

            if (rs.next()) {

                return rs.getInt(1) > 0;
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return false;
    }

    // ----------------------------------------------------
    // DOUBLE BOOKING CHECK FOR UPDATE
    // ----------------------------------------------------

    public boolean isDentistBookedExcept(
            String dentistName,
            String appointmentDate,
            String appointmentTime,
            String currentAppointmentNo) {

        String sql =
                "SELECT COUNT(*) "
                + "FROM appointments "
                + "WHERE dentist_name = ? "
                + "AND appointment_date = ? "
                + "AND appointment_time = ? "
                + "AND appointment_no <> ? "
                + "AND status <> 'CANCELLED'";

        try {

            Connection con =
                    DatabaseConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(
                    1,
                    dentistName
            );

            ps.setString(
                    2,
                    appointmentDate
            );

            ps.setString(
                    3,
                    appointmentTime
            );

            ps.setString(
                    4,
                    currentAppointmentNo
            );

            ResultSet rs =
                    ps.executeQuery();

            if (rs.next()) {

                return rs.getInt(1) > 0;
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return false;
    }

    // ----------------------------------------------------
    // GET NEXT ID FOR APPOINTMENT NUMBER
    // ----------------------------------------------------

    public int getNextAppointmentSequence() {

        String sql =
                "SELECT COALESCE(MAX(appointment_id), 0) + 1 "
                + "FROM appointments";

        try {

            Connection con =
                    DatabaseConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery();

            if (rs.next()) {

                return rs.getInt(1);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return 1;
    }

    // ----------------------------------------------------
    // CONVERT RESULTSET TO APPOINTMENT OBJECT
    // ----------------------------------------------------

    private Appointment createAppointmentFromResultSet(
            ResultSet rs) throws Exception {

        return new Appointment(
                rs.getString("appointment_no"),
                rs.getString("patient_name"),
                rs.getString("dentist_name"),
                rs.getString("treatment_type"),
                rs.getString("appointment_date"),
                rs.getString("appointment_time"),
                rs.getString("status"),
                rs.getString("notes")
        );
    }
}