package com.sunrisedental.dao;

import com.sunrisedental.database.DatabaseConnection;
import com.sunrisedental.model.Appointment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

public class AppointmentDAO {

    public boolean insert(Appointment appointment) {

        String sql
                = "INSERT INTO appointments "
                + "(appointment_no, patient_id, dentist_id, "
                + "treatment_type, appointment_date, appointment_time, "
                + "status, notes) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {

            Connection con
                    = DatabaseConnection.getConnection();

            PreparedStatement ps
                    = con.prepareStatement(sql);

            ps.setString(
                    1,
                    appointment.getAppointmentNo()
            );

            ps.setInt(
                    2,
                    appointment.getPatientId()
            );

            ps.setInt(
                    3,
                    appointment.getDentistId()
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

    public boolean update(Appointment appointment) {

        String sql
                = "UPDATE appointments SET "
                + "patient_id = ?, "
                + "dentist_id = ?, "
                + "treatment_type = ?, "
                + "appointment_date = ?, "
                + "appointment_time = ?, "
                + "notes = ? "
                + "WHERE appointment_no = ?";

        try {

            Connection con
                    = DatabaseConnection.getConnection();

            PreparedStatement ps
                    = con.prepareStatement(sql);

            ps.setInt(
                    1,
                    appointment.getPatientId()
            );

            ps.setInt(
                    2,
                    appointment.getDentistId()
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

    public boolean cancel(String appointmentNo) {

        String sql
                = "UPDATE appointments "
                + "SET status = 'CANCELLED' "
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

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }

    public List<Appointment> getAll() {

        List<Appointment> appointments
                = new ArrayList<>();

        String sql
                = "SELECT * FROM appointments "
                + "ORDER BY appointment_date DESC, "
                + "appointment_time DESC";

        try {

            Connection con
                    = DatabaseConnection.getConnection();

            PreparedStatement ps
                    = con.prepareStatement(sql);

            ResultSet rs
                    = ps.executeQuery();

            while (rs.next()) {

                appointments.add(
                        createAppointmentFromResultSet(rs)
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return appointments;
    }

    public List<Appointment> search(
            String keyword) {

        List<Appointment> appointments
                = new ArrayList<>();

        String sql
                = "SELECT a.* "
                + "FROM appointments a "
                + "JOIN patients p "
                + "ON a.patient_id = p.patient_id "
                + "JOIN dentists d "
                + "ON a.dentist_id = d.dentist_id "
                + "WHERE a.appointment_no LIKE ? "
                + "OR p.name LIKE ? "
                + "OR d.dentist_name LIKE ? "
                + "OR a.treatment_type LIKE ? "
                + "OR a.status LIKE ? "
                + "ORDER BY a.appointment_date DESC, "
                + "a.appointment_time DESC";

        try {

            Connection con
                    = DatabaseConnection.getConnection();

            PreparedStatement ps
                    = con.prepareStatement(sql);

            String searchValue
                    = "%" + keyword + "%";

            ps.setString(1, searchValue);
            ps.setString(2, searchValue);
            ps.setString(3, searchValue);
            ps.setString(4, searchValue);
            ps.setString(5, searchValue);

            ResultSet rs
                    = ps.executeQuery();

            while (rs.next()) {

                appointments.add(
                        createAppointmentFromResultSet(rs)
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return appointments;
    }

    public boolean isDentistBooked(
            int dentistId,
            String appointmentDate,
            String appointmentTime) {

        String sql
                = "SELECT COUNT(*) "
                + "FROM appointments "
                + "WHERE dentist_id = ? "
                + "AND appointment_date = ? "
                + "AND appointment_time = ? "
                + "AND status <> 'CANCELLED'";

        try {

            Connection con
                    = DatabaseConnection.getConnection();

            PreparedStatement ps
                    = con.prepareStatement(sql);

            ps.setInt(
                    1,
                    dentistId
            );

            ps.setString(
                    2,
                    appointmentDate
            );

            ps.setString(
                    3,
                    appointmentTime
            );

            ResultSet rs
                    = ps.executeQuery();

            if (rs.next()) {

                return rs.getInt(1) > 0;
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return false;
    }

    public int countTodayAppointments() {

        String sql
                = "SELECT COUNT(*) "
                + "FROM appointments "
                + "WHERE appointment_date = CURDATE() "
                + "AND status <> 'CANCELLED'";

        try {

            Connection con
                    = DatabaseConnection.getConnection();

            PreparedStatement ps
                    = con.prepareStatement(sql);

            ResultSet rs
                    = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    public int countTodayAppointmentsByStatus(
            String status) {

        String sql
                = "SELECT COUNT(*) "
                + "FROM appointments "
                + "WHERE appointment_date = CURDATE() "
                + "AND status = ?";

        try {

            Connection con
                    = DatabaseConnection.getConnection();

            PreparedStatement ps
                    = con.prepareStatement(sql);

            ps.setString(
                    1,
                    status
            );

            ResultSet rs
                    = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    public boolean isDentistBookedExcept(
            int dentistId,
            String appointmentDate,
            String appointmentTime,
            String currentAppointmentNo) {

        String sql
                = "SELECT COUNT(*) "
                + "FROM appointments "
                + "WHERE dentist_id = ? "
                + "AND appointment_date = ? "
                + "AND appointment_time = ? "
                + "AND appointment_no <> ? "
                + "AND status <> 'CANCELLED'";

        try {

            Connection con
                    = DatabaseConnection.getConnection();

            PreparedStatement ps
                    = con.prepareStatement(sql);

            ps.setInt(
                    1,
                    dentistId
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

            ResultSet rs
                    = ps.executeQuery();

            if (rs.next()) {

                return rs.getInt(1) > 0;
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return false;
    }

    public int getNextAppointmentSequence() {

        String sql
                = "SELECT COALESCE(MAX(appointment_id), 0) + 1 "
                + "FROM appointments";

        try {

            Connection con
                    = DatabaseConnection.getConnection();

            PreparedStatement ps
                    = con.prepareStatement(sql);

            ResultSet rs
                    = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return 1;
    }

    private Appointment createAppointmentFromResultSet(
            ResultSet rs) throws Exception {

        return new Appointment(
                rs.getString("appointment_no"),
                rs.getInt("patient_id"),
                rs.getInt("dentist_id"),
                rs.getString("treatment_type"),
                rs.getString("appointment_date"),
                rs.getString("appointment_time"),
                rs.getString("status"),
                rs.getString("notes")
        );
    }

    //Methods for Specific Dentist Appointments
    public List<Appointment> getAppointmentsByDentistId(
            int dentistId) {

        List<Appointment> appointments
                = new ArrayList<>();

        String sql
                = "SELECT "
                + "a.appointment_no, "
                + "a.patient_id, "
                + "a.dentist_id, "
                + "a.treatment_type, "
                + "a.appointment_date, "
                + "a.appointment_time, "
                + "a.status, "
                + "a.notes "
                + "FROM appointments a "
                + "WHERE a.dentist_id = ? "
                + "ORDER BY "
                + "a.appointment_date ASC, "
                + "a.appointment_time ASC";

        try {

            Connection con
                    = DatabaseConnection.getConnection();

            PreparedStatement ps
                    = con.prepareStatement(sql);

            ps.setInt(
                    1,
                    dentistId
            );

            ResultSet rs
                    = ps.executeQuery();

            while (rs.next()) {

                Appointment appointment
                        = new Appointment(
                                rs.getString(
                                        "appointment_no"
                                ),
                                rs.getInt(
                                        "patient_id"
                                ),
                                rs.getInt(
                                        "dentist_id"
                                ),
                                rs.getString(
                                        "treatment_type"
                                ),
                                rs.getString(
                                        "appointment_date"
                                ),
                                rs.getString(
                                        "appointment_time"
                                ),
                                rs.getString(
                                        "status"
                                ),
                                rs.getString(
                                        "notes"
                                )
                        );

                appointments.add(
                        appointment
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return appointments;
    }

    public List<Appointment> searchAppointmentsByDentistId(
            int dentistId,
            String keyword) {

        List<Appointment> appointments
                = new ArrayList<>();

        String sql
                = "SELECT "
                + "a.appointment_no, "
                + "a.patient_id, "
                + "a.dentist_id, "
                + "a.treatment_type, "
                + "a.appointment_date, "
                + "a.appointment_time, "
                + "a.status, "
                + "a.notes "
                + "FROM appointments a "
                + "JOIN patients p "
                + "ON a.patient_id = p.patient_id "
                + "WHERE a.dentist_id = ? "
                + "AND ("
                + "a.appointment_no LIKE ? "
                + "OR p.name LIKE ? "
                + "OR a.treatment_type LIKE ? "
                + "OR a.status LIKE ?"
                + ") "
                + "ORDER BY "
                + "a.appointment_date ASC, "
                + "a.appointment_time ASC";

        try {

            Connection con
                    = DatabaseConnection.getConnection();

            PreparedStatement ps
                    = con.prepareStatement(sql);

            String searchValue
                    = "%" + keyword + "%";

            ps.setInt(
                    1,
                    dentistId
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

            ResultSet rs
                    = ps.executeQuery();

            while (rs.next()) {

                Appointment appointment
                        = new Appointment(
                                rs.getString(
                                        "appointment_no"
                                ),
                                rs.getInt(
                                        "patient_id"
                                ),
                                rs.getInt(
                                        "dentist_id"
                                ),
                                rs.getString(
                                        "treatment_type"
                                ),
                                rs.getString(
                                        "appointment_date"
                                ),
                                rs.getString(
                                        "appointment_time"
                                ),
                                rs.getString(
                                        "status"
                                ),
                                rs.getString(
                                        "notes"
                                )
                        );

                appointments.add(
                        appointment
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return appointments;
    }

    public List<Appointment> getScheduleByDentistAndDate(
            int dentistId,
            String appointmentDate) {

        List<Appointment> appointments
                = new ArrayList<>();

        String sql
                = "SELECT "
                + "appointment_no, "
                + "patient_id, "
                + "dentist_id, "
                + "treatment_type, "
                + "appointment_date, "
                + "appointment_time, "
                + "status, "
                + "notes "
                + "FROM appointments "
                + "WHERE dentist_id = ? "
                + "AND appointment_date = ? "
                + "AND status <> 'CANCELLED' "
                + "ORDER BY appointment_time ASC";

        try {

            Connection con
                    = DatabaseConnection.getConnection();

            PreparedStatement ps
                    = con.prepareStatement(
                            sql
                    );

            ps.setInt(
                    1,
                    dentistId
            );

            ps.setString(
                    2,
                    appointmentDate
            );

            ResultSet rs
                    = ps.executeQuery();

            while (rs.next()) {

                Appointment appointment
                        = new Appointment(
                                rs.getString(
                                        "appointment_no"
                                ),
                                rs.getInt(
                                        "patient_id"
                                ),
                                rs.getInt(
                                        "dentist_id"
                                ),
                                rs.getString(
                                        "treatment_type"
                                ),
                                rs.getString(
                                        "appointment_date"
                                ),
                                rs.getString(
                                        "appointment_time"
                                ),
                                rs.getString(
                                        "status"
                                ),
                                rs.getString(
                                        "notes"
                                )
                        );

                appointments.add(
                        appointment
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return appointments;
    }

    public int countTodayAppointmentsForDentist(
            int dentistId) {

        String sql
                = "SELECT COUNT(*) "
                + "FROM appointments "
                + "WHERE dentist_id = ? "
                + "AND appointment_date = CURDATE() "
                + "AND status <> 'CANCELLED'";

        try {

            Connection con
                    = DatabaseConnection.getConnection();

            PreparedStatement ps
                    = con.prepareStatement(
                            sql
                    );

            ps.setInt(
                    1,
                    dentistId
            );

            ResultSet rs
                    = ps.executeQuery();

            if (rs.next()) {

                return rs.getInt(
                        1
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return 0;
    }

    public int countTodayAppointmentsByStatusForDentist(
            int dentistId,
            String status) {

        String sql
                = "SELECT COUNT(*) "
                + "FROM appointments "
                + "WHERE dentist_id = ? "
                + "AND appointment_date = CURDATE() "
                + "AND status = ?";

        try {

            Connection con
                    = DatabaseConnection.getConnection();

            PreparedStatement ps
                    = con.prepareStatement(
                            sql
                    );

            ps.setInt(
                    1,
                    dentistId
            );

            ps.setString(
                    2,
                    status
            );

            ResultSet rs
                    = ps.executeQuery();

            if (rs.next()) {

                return rs.getInt(
                        1
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return 0;
    }

    //Appointment Status For Reception
    public boolean updateAppointmentStatus(
            String appointmentNo,
            String status) {

        String sql
                = "UPDATE appointments "
                + "SET status = ? "
                + "WHERE appointment_no = ?";

        try {

            Connection con
                    = DatabaseConnection.getConnection();

            PreparedStatement ps
                    = con.prepareStatement(
                            sql
                    );

            ps.setString(
                    1,
                    status
            );

            ps.setString(
                    2,
                    appointmentNo
            );

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }
}
