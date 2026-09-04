package com.sunrisedental.dao;

import com.sunrisedental.database.DatabaseConnection;
import com.sunrisedental.model.ReportSummary;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class ReportDAO {

    // ==========================================================
    // REPORT SUMMARY
    // ==========================================================
    public ReportSummary getSummary(
            String fromDate,
            String toDate) {

        ReportSummary summary
                = new ReportSummary();

        String appointmentSql
                = "SELECT "
                + "COUNT(*) AS total, "
                + "SUM(CASE "
                + "WHEN status = 'COMPLETED' "
                + "THEN 1 ELSE 0 END) AS completed, "
                + "SUM(CASE "
                + "WHEN status = 'PENDING' "
                + "THEN 1 ELSE 0 END) AS pending, "
                + "SUM(CASE "
                + "WHEN status = 'CANCELLED' "
                + "THEN 1 ELSE 0 END) AS cancelled "
                + "FROM appointments "
                + "WHERE appointment_date "
                + "BETWEEN ? AND ?";

        String revenueSql
                = "SELECT "
                + "COALESCE("
                + "SUM(total_amount), 0"
                + ") AS revenue "
                + "FROM bills "
                + "WHERE payment_status = 'PAID' "
                + "AND DATE(created_at) "
                + "BETWEEN ? AND ?";

        try (
                Connection con
                = DatabaseConnection.getConnection()
                ) {

            // ==================================================
            // APPOINTMENT SUMMARY
            // ==================================================
            try (
                    PreparedStatement ps
                    = con.prepareStatement(
                            appointmentSql
                    )
                    ) {

                ps.setString(
                        1,
                        fromDate
                );

                ps.setString(
                        2,
                        toDate
                );

                try (
                        ResultSet rs
                        = ps.executeQuery()
                        ) {

                    if (rs.next()) {

                        summary.setTotalAppointments(
                                rs.getInt(
                                        "total"
                                )
                        );

                        summary.setCompletedAppointments(
                                rs.getInt(
                                        "completed"
                                )
                        );

                        summary.setPendingAppointments(
                                rs.getInt(
                                        "pending"
                                )
                        );

                        summary.setCancelledAppointments(
                                rs.getInt(
                                        "cancelled"
                                )
                        );
                    }
                }
            }

            // ==================================================
            // REVENUE SUMMARY
            // ==================================================
            try (
                    PreparedStatement revenuePs
                    = con.prepareStatement(
                            revenueSql
                    )
                    ) {

                revenuePs.setString(
                        1,
                        fromDate
                );

                revenuePs.setString(
                        2,
                        toDate
                );

                try (
                        ResultSet revenueRs
                        = revenuePs.executeQuery()
                        ) {

                    if (revenueRs.next()) {

                        BigDecimal revenue
                                = revenueRs.getBigDecimal(
                                        "revenue"
                                );

                        summary.setTotalRevenue(
                                revenue == null
                                ? BigDecimal.ZERO
                                : revenue
                        );
                    }
                }
            }

        } catch (SQLException e) {

            throw new RuntimeException(
                    "Unable to load report summary.",
                    e
            );
        }

        return summary;
    }

    // ==========================================================
    // APPOINTMENT REPORT
    // ==========================================================
    public List<Object[]> getAppointmentReport(
            String fromDate,
            String toDate) {

        List<Object[]> data
                = new ArrayList<>();

        /*
         * appointments now stores dentist_id instead of
         * dentist_name.
         *
         * Therefore we JOIN dentists to obtain the
         * dentist's readable name.
         */
        String sql
                = "SELECT "
                + "a.appointment_no, "
                + "p.name AS patient_name, "
                + "d.dentist_name AS dentist_name, "
                + "a.treatment_type, "
                + "a.appointment_date, "
                + "a.appointment_time, "
                + "a.status "
                + "FROM appointments a "
                + "INNER JOIN patients p "
                + "ON a.patient_id = p.patient_id "
                + "INNER JOIN dentists d "
                + "ON a.dentist_id = d.dentist_id "
                + "WHERE a.appointment_date "
                + "BETWEEN ? AND ? "
                + "ORDER BY "
                + "a.appointment_date ASC, "
                + "a.appointment_time ASC";

        try (
                Connection con
                = DatabaseConnection.getConnection();

                PreparedStatement ps
                = con.prepareStatement(sql)
                ) {

            ps.setString(
                    1,
                    fromDate
            );

            ps.setString(
                    2,
                    toDate
            );

            try (
                    ResultSet rs
                    = ps.executeQuery()
                    ) {

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
                                        "dentist_name"
                                ),
                                rs.getString(
                                        "treatment_type"
                                ),
                                rs.getDate(
                                        "appointment_date"
                                ),
                                rs.getTime(
                                        "appointment_time"
                                ),
                                rs.getString(
                                        "status"
                                )
                            }
                    );
                }
            }

        } catch (SQLException e) {

            throw new RuntimeException(
                    "Unable to load appointment report.",
                    e
            );
        }

        return data;
    }

    // ==========================================================
    // REVENUE BY TREATMENT
    // ==========================================================
    public List<Object[]> getRevenueByTreatment(
            String fromDate,
            String toDate) {

        List<Object[]> data
                = new ArrayList<>();

        String sql
                = "SELECT "
                + "a.treatment_type, "
                + "COUNT(b.bill_id) AS bills, "
                + "COALESCE("
                + "SUM(b.total_amount), 0"
                + ") AS revenue "
                + "FROM bills b "
                + "INNER JOIN appointments a "
                + "ON b.appointment_no "
                + "= a.appointment_no "
                + "WHERE b.payment_status = 'PAID' "
                + "AND DATE(b.created_at) "
                + "BETWEEN ? AND ? "
                + "GROUP BY a.treatment_type "
                + "ORDER BY revenue DESC";

        try (
                Connection con
                = DatabaseConnection.getConnection();

                PreparedStatement ps
                = con.prepareStatement(sql)
                ) {

            ps.setString(
                    1,
                    fromDate
            );

            ps.setString(
                    2,
                    toDate
            );

            try (
                    ResultSet rs
                    = ps.executeQuery()
                    ) {

                while (rs.next()) {

                    data.add(
                            new Object[]{
                                rs.getString(
                                        "treatment_type"
                                ),
                                rs.getInt(
                                        "bills"
                                ),
                                rs.getBigDecimal(
                                        "revenue"
                                )
                            }
                    );
                }
            }

        } catch (SQLException e) {

            throw new RuntimeException(
                    "Unable to load revenue by treatment report.",
                    e
            );
        }

        return data;
    }

    // ==========================================================
    // DENTIST WORKLOAD
    // ==========================================================
    public List<Object[]> getDentistWorkload(
            String fromDate,
            String toDate) {

        List<Object[]> data
                = new ArrayList<>();

        /*
         * dentist_name is no longer stored in appointments.
         *
         * We group using dentist_id and display
         * d.dentist_name from the dentists table.
         */
        String sql
                = "SELECT "
                + "d.dentist_name AS dentist_name, "
                + "COUNT(a.appointment_id) AS total, "
                + "SUM(CASE "
                + "WHEN a.status = 'COMPLETED' "
                + "THEN 1 ELSE 0 END) AS completed, "
                + "SUM(CASE "
                + "WHEN a.status = 'PENDING' "
                + "THEN 1 ELSE 0 END) AS pending, "
                + "SUM(CASE "
                + "WHEN a.status = 'CANCELLED' "
                + "THEN 1 ELSE 0 END) AS cancelled "
                + "FROM appointments a "
                + "INNER JOIN dentists d "
                + "ON a.dentist_id = d.dentist_id "
                + "WHERE a.appointment_date "
                + "BETWEEN ? AND ? "
                + "GROUP BY "
                + "d.dentist_id, "
                + "d.dentist_name "
                + "ORDER BY total DESC";

        try (
                Connection con
                = DatabaseConnection.getConnection();

                PreparedStatement ps
                = con.prepareStatement(sql)
                ) {

            ps.setString(
                    1,
                    fromDate
            );

            ps.setString(
                    2,
                    toDate
            );

            try (
                    ResultSet rs
                    = ps.executeQuery()
                    ) {

                while (rs.next()) {

                    data.add(
                            new Object[]{
                                rs.getString(
                                        "dentist_name"
                                ),
                                rs.getInt(
                                        "total"
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
            }

        } catch (SQLException e) {

            throw new RuntimeException(
                    "Unable to load dentist workload report.",
                    e
            );
        }

        return data;
    }

    // ==========================================================
    // PAYMENT METHOD REPORT
    // ==========================================================
    public List<Object[]> getPaymentMethodReport(
            String fromDate,
            String toDate) {

        List<Object[]> data
                = new ArrayList<>();

        String sql
                = "SELECT "
                + "payment_method, "
                + "COUNT(*) AS payments, "
                + "COALESCE("
                + "SUM(total_amount), 0"
                + ") AS amount "
                + "FROM bills "
                + "WHERE payment_status = 'PAID' "
                + "AND DATE(created_at) "
                + "BETWEEN ? AND ? "
                + "GROUP BY payment_method "
                + "ORDER BY amount DESC";

        try (
                Connection con
                = DatabaseConnection.getConnection();

                PreparedStatement ps
                = con.prepareStatement(sql)
                ) {

            ps.setString(
                    1,
                    fromDate
            );

            ps.setString(
                    2,
                    toDate
            );

            try (
                    ResultSet rs
                    = ps.executeQuery()
                    ) {

                while (rs.next()) {

                    data.add(
                            new Object[]{
                                rs.getString(
                                        "payment_method"
                                ),
                                rs.getInt(
                                        "payments"
                                ),
                                rs.getBigDecimal(
                                        "amount"
                                )
                            }
                    );
                }
            }

        } catch (SQLException e) {

            throw new RuntimeException(
                    "Unable to load payment method report.",
                    e
            );
        }

        return data;
    }
}