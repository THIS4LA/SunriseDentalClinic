package com.sunrisedental.dao;

import com.sunrisedental.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

public class AdminReportDAO {

    // ==========================================================
    // APPOINTMENT SUMMARY
    // ==========================================================

    public List<Object[]> getAppointmentSummary(
            String fromDate,
            String toDate) {

        List<Object[]> rows =
                new ArrayList<>();

        String sql =
                "SELECT "
                + "a.appointment_no, "
                + "p.name AS patient_name, "
                + "d.dentist_name, "
                + "a.treatment_type, "
                + "a.appointment_date, "
                + "a.appointment_time, "
                + "a.status "
                + "FROM appointments a "
                + "JOIN patients p "
                + "ON a.patient_id = p.patient_id "
                + "JOIN dentists d "
                + "ON a.dentist_id = d.dentist_id "
                + "WHERE a.appointment_date "
                + "BETWEEN ? AND ? "
                + "ORDER BY "
                + "a.appointment_date ASC, "
                + "a.appointment_time ASC";

        try {

            Connection con =
                    DatabaseConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(
                            sql
                    );

            ps.setString(
                    1,
                    fromDate
            );

            ps.setString(
                    2,
                    toDate
            );

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                rows.add(
                        new Object[]{
                            rs.getString(
                                    "appointment_no"
                            ),

                            rs.getString(
                                    "patient_name"
                            ),

                            rs.getString(
                                    "dentist_name"
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

        return rows;
    }

    // ==========================================================
    // REVENUE BY TREATMENT
    // ==========================================================

    public List<Object[]> getRevenueByTreatment(
            String fromDate,
            String toDate) {

        List<Object[]> rows =
                new ArrayList<>();

        String sql =
                "SELECT "
                + "a.treatment_type, "
                + "COUNT(b.bill_id) AS bill_count, "
                + "SUM(b.total_amount) AS total_revenue "
                + "FROM bills b "
                + "JOIN appointments a "
                + "ON b.appointment_no = a.appointment_no "
                + "WHERE a.appointment_date "
                + "BETWEEN ? AND ? "
                + "AND b.payment_status = 'PAID' "
                + "GROUP BY a.treatment_type "
                + "ORDER BY total_revenue DESC";

        try {

            Connection con =
                    DatabaseConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(
                            sql
                    );

            ps.setString(
                    1,
                    fromDate
            );

            ps.setString(
                    2,
                    toDate
            );

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                rows.add(
                        new Object[]{
                            rs.getString(
                                    "treatment_type"
                            ),

                            rs.getInt(
                                    "bill_count"
                            ),

                            rs.getBigDecimal(
                                    "total_revenue"
                            )
                        }
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return rows;
    }

    // ==========================================================
    // DENTIST WORKLOAD
    // ==========================================================

    public List<Object[]> getDentistWorkload(
            String fromDate,
            String toDate) {

        List<Object[]> rows =
                new ArrayList<>();

        String sql =
                "SELECT "
                + "d.dentist_id, "
                + "d.dentist_name, "
                + "COUNT(a.appointment_no) AS total_appointments, "
                + "SUM(CASE "
                + "WHEN a.status = 'COMPLETED' "
                + "THEN 1 ELSE 0 END) AS completed, "
                + "SUM(CASE "
                + "WHEN a.status = 'PENDING' "
                + "THEN 1 ELSE 0 END) AS pending, "
                + "SUM(CASE "
                + "WHEN a.status = 'CANCELLED' "
                + "THEN 1 ELSE 0 END) AS cancelled "
                + "FROM dentists d "
                + "LEFT JOIN appointments a "
                + "ON d.dentist_id = a.dentist_id "
                + "AND a.appointment_date "
                + "BETWEEN ? AND ? "
                + "GROUP BY "
                + "d.dentist_id, "
                + "d.dentist_name "
                + "ORDER BY total_appointments DESC";

        try {

            Connection con =
                    DatabaseConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(
                            sql
                    );

            ps.setString(
                    1,
                    fromDate
            );

            ps.setString(
                    2,
                    toDate
            );

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                rows.add(
                        new Object[]{
                            rs.getInt(
                                    "dentist_id"
                            ),

                            rs.getString(
                                    "dentist_name"
                            ),

                            rs.getInt(
                                    "total_appointments"
                            ),

                            rs.getInt(
                                    "completed"
                            ),

                            rs.getInt(
                                    "pending"
                            ),

                            rs.getInt(
                                    "cancelled"
                            )
                        }
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return rows;
    }

    // ==========================================================
    // PAYMENT METHOD SUMMARY
    // ==========================================================

    public List<Object[]> getPaymentMethodSummary(
            String fromDate,
            String toDate) {

        List<Object[]> rows =
                new ArrayList<>();

        String sql =
                "SELECT "
                + "b.payment_method, "
                + "COUNT(b.bill_id) AS bill_count, "
                + "SUM(b.total_amount) AS total_amount "
                + "FROM bills b "
                + "JOIN appointments a "
                + "ON b.appointment_no = a.appointment_no "
                + "WHERE a.appointment_date "
                + "BETWEEN ? AND ? "
                + "AND b.payment_status = 'PAID' "
                + "GROUP BY b.payment_method "
                + "ORDER BY total_amount DESC";

        try {

            Connection con =
                    DatabaseConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(
                            sql
                    );

            ps.setString(
                    1,
                    fromDate
            );

            ps.setString(
                    2,
                    toDate
            );

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                rows.add(
                        new Object[]{
                            rs.getString(
                                    "payment_method"
                            ),

                            rs.getInt(
                                    "bill_count"
                            ),

                            rs.getBigDecimal(
                                    "total_amount"
                            )
                        }
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return rows;
    }

    // ==========================================================
    // TOTAL PAID REVENUE
    // ==========================================================

    public java.math.BigDecimal getTotalRevenue(
            String fromDate,
            String toDate) {

        String sql =
                "SELECT COALESCE("
                + "SUM(b.total_amount), 0) "
                + "FROM bills b "
                + "JOIN appointments a "
                + "ON b.appointment_no = a.appointment_no "
                + "WHERE a.appointment_date "
                + "BETWEEN ? AND ? "
                + "AND b.payment_status = 'PAID'";

        try {

            Connection con =
                    DatabaseConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(
                            sql
                    );

            ps.setString(
                    1,
                    fromDate
            );

            ps.setString(
                    2,
                    toDate
            );

            ResultSet rs =
                    ps.executeQuery();

            if (rs.next()) {

                return rs.getBigDecimal(
                        1
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return java.math.BigDecimal.ZERO;
    }
}