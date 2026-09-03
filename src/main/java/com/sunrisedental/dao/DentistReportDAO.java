package com.sunrisedental.dao;

import com.sunrisedental.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

public class DentistReportDAO {

    // ==========================================================
    // APPOINTMENT SUMMARY
    // ==========================================================

    public List<Object[]> getAppointmentSummary(
            int dentistId,
            String fromDate,
            String toDate) {

        List<Object[]> data =
                new ArrayList<>();

        String sql =
                "SELECT "
                + "a.appointment_no, "
                + "p.name AS patient_name, "
                + "a.treatment_type, "
                + "a.appointment_date, "
                + "a.appointment_time, "
                + "a.status "
                + "FROM appointments a "
                + "JOIN patients p "
                + "ON a.patient_id = p.patient_id "
                + "WHERE a.dentist_id = ? "
                + "AND a.appointment_date "
                + "BETWEEN ? AND ? "
                + "ORDER BY "
                + "a.appointment_date ASC, "
                + "a.appointment_time ASC";

        try {

            Connection con =
                    DatabaseConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(
                    1,
                    dentistId
            );

            ps.setString(
                    2,
                    fromDate
            );

            ps.setString(
                    3,
                    toDate
            );

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                data.add(
                        new Object[]{
                            rs.getString(
                                    "appointment_no"
                            ),
                            rs.getString(
                                    "patient_name"
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
                            )
                        }
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return data;
    }

    // ==========================================================
    // TREATMENT SUMMARY
    // ==========================================================

    public List<Object[]> getTreatmentSummary(
            int dentistId,
            String fromDate,
            String toDate) {

        List<Object[]> data =
                new ArrayList<>();

        String sql =
                "SELECT "
                + "treatment_type, "
                + "COUNT(*) AS total "
                + "FROM appointments "
                + "WHERE dentist_id = ? "
                + "AND appointment_date "
                + "BETWEEN ? AND ? "
                + "AND status = 'COMPLETED' "
                + "GROUP BY treatment_type "
                + "ORDER BY total DESC";

        try {

            Connection con =
                    DatabaseConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(
                    1,
                    dentistId
            );

            ps.setString(
                    2,
                    fromDate
            );

            ps.setString(
                    3,
                    toDate
            );

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                data.add(
                        new Object[]{
                            rs.getString(
                                    "treatment_type"
                            ),
                            rs.getInt(
                                    "total"
                            )
                        }
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return data;
    }

    // ==========================================================
    // STATUS SUMMARY
    // ==========================================================

    public List<Object[]> getStatusSummary(
            int dentistId,
            String fromDate,
            String toDate) {

        List<Object[]> data =
                new ArrayList<>();

        String sql =
                "SELECT "
                + "status, "
                + "COUNT(*) AS total "
                + "FROM appointments "
                + "WHERE dentist_id = ? "
                + "AND appointment_date "
                + "BETWEEN ? AND ? "
                + "GROUP BY status "
                + "ORDER BY status ASC";

        try {

            Connection con =
                    DatabaseConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(
                    1,
                    dentistId
            );

            ps.setString(
                    2,
                    fromDate
            );

            ps.setString(
                    3,
                    toDate
            );

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                data.add(
                        new Object[]{
                            rs.getString(
                                    "status"
                            ),
                            rs.getInt(
                                    "total"
                            )
                        }
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return data;
    }

    // ==========================================================
    // DISTINCT PATIENT COUNT
    // ==========================================================

    public int getPatientCount(
            int dentistId,
            String fromDate,
            String toDate) {

        String sql =
                "SELECT COUNT(DISTINCT patient_id) "
                + "FROM appointments "
                + "WHERE dentist_id = ? "
                + "AND appointment_date "
                + "BETWEEN ? AND ?";

        try {

            Connection con =
                    DatabaseConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(
                    1,
                    dentistId
            );

            ps.setString(
                    2,
                    fromDate
            );

            ps.setString(
                    3,
                    toDate
            );

            ResultSet rs =
                    ps.executeQuery();

            if (rs.next()) {

                return rs.getInt(1);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return 0;
    }
}