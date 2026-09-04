package com.sunrisedental.dao;

import com.sunrisedental.database.DatabaseConnection;
import com.sunrisedental.model.Bill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

public class BillDAO {

    public boolean insert(
            Bill bill) {

        String sql
                = "INSERT INTO bills "
                + "(bill_no, "
                + "appointment_no, "
                + "consultation_fee, "
                + "treatment_fee, "
                + "discount, "
                + "total_amount, "
                + "payment_method, "
                + "payment_status) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {

            Connection con
                    = DatabaseConnection.getConnection();

            PreparedStatement ps
                    = con.prepareStatement(sql);

            ps.setString(
                    1,
                    bill.getBillNo()
            );

            ps.setString(
                    2,
                    bill.getAppointmentNo()
            );

            ps.setBigDecimal(
                    3,
                    bill.getConsultationFee()
            );

            ps.setBigDecimal(
                    4,
                    bill.getTreatmentFee()
            );

            ps.setBigDecimal(
                    5,
                    bill.getDiscount()
            );

            ps.setBigDecimal(
                    6,
                    bill.getTotalAmount()
            );

            ps.setString(
                    7,
                    bill.getPaymentMethod()
            );

            ps.setString(
                    8,
                    bill.getPaymentStatus()
            );

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    public boolean existsForAppointment(
            String appointmentNo) {

        String sql
                = "SELECT COUNT(*) "
                + "FROM bills "
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

                return rs.getInt(1) > 0;
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return false;
    }

    public int getNextBillSequence() {

        String sql
                = "SELECT COALESCE(MAX(bill_id), 0) + 1 "
                + "FROM bills";

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

    public List<Bill> getAll() {

        List<Bill> bills
                = new ArrayList<>();

        String sql
                = "SELECT * FROM bills "
                + "ORDER BY bill_id DESC";

        try {

            Connection con
                    = DatabaseConnection.getConnection();

            PreparedStatement ps
                    = con.prepareStatement(sql);

            ResultSet rs
                    = ps.executeQuery();

            while (rs.next()) {

                Bill bill
                        = new Bill(
                                rs.getString(
                                        "bill_no"
                                ),
                                rs.getString(
                                        "appointment_no"
                                ),
                                rs.getBigDecimal(
                                        "consultation_fee"
                                ),
                                rs.getBigDecimal(
                                        "treatment_fee"
                                ),
                                rs.getBigDecimal(
                                        "discount"
                                ),
                                rs.getBigDecimal(
                                        "total_amount"
                                ),
                                rs.getString(
                                        "payment_method"
                                ),
                                rs.getString(
                                        "payment_status"
                                )
                        );

                bill.setCreatedAt(
                        rs.getString(
                                "created_at"
                        )
                );

                bills.add(bill);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return bills;
    }

    public List<Bill> getAllBills() {

        List<Bill> bills
                = new ArrayList<>();

        String sql
                = "SELECT "
                + "b.bill_id, "
                + "b.bill_no, "
                + "b.appointment_no, "
                + "a.patient_id, "
                + "a.dentist_id, "
                + "a.treatment_type, "
                + "b.consultation_fee, "
                + "b.treatment_fee, "
                + "b.discount, "
                + "b.total_amount, "
                + "b.payment_method, "
                + "b.payment_status, "
                + "b.created_at "
                + "FROM bills b "
                + "JOIN appointments a "
                + "ON b.appointment_no = a.appointment_no "
                + "ORDER BY b.bill_id DESC";

        try {

            Connection con
                    = DatabaseConnection.getConnection();

            PreparedStatement ps
                    = con.prepareStatement(
                            sql
                    );

            ResultSet rs
                    = ps.executeQuery();

            while (rs.next()) {

                bills.add(
                        createBillFromResultSet(
                                rs
                        )
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return bills;
    }

    public List<Bill> searchBills(
            String keyword) {

        List<Bill> bills
                = new ArrayList<>();

        String sql
                = "SELECT "
                + "b.bill_id, "
                + "b.bill_no, "
                + "b.appointment_no, "
                + "a.patient_id, "
                + "a.dentist_id, "
                + "a.treatment_type, "
                + "b.consultation_fee, "
                + "b.treatment_fee, "
                + "b.discount, "
                + "b.total_amount, "
                + "b.payment_method, "
                + "b.payment_status, "
                + "b.created_at "
                + "FROM bills b "
                + "JOIN appointments a "
                + "ON b.appointment_no = a.appointment_no "
                + "WHERE CAST(b.bill_id AS CHAR) LIKE ? "
                + "OR b.bill_no LIKE ? "
                + "OR b.appointment_no LIKE ? "
                + "OR a.treatment_type LIKE ? "
                + "OR b.payment_method LIKE ? "
                + "OR b.payment_status LIKE ? "
                + "ORDER BY b.bill_id DESC";

        try {

            Connection con
                    = DatabaseConnection.getConnection();

            PreparedStatement ps
                    = con.prepareStatement(
                            sql
                    );

            String value
                    = "%"
                    + keyword
                    + "%";

            ps.setString(
                    1,
                    value
            );

            ps.setString(
                    2,
                    value
            );

            ps.setString(
                    3,
                    value
            );

            ps.setString(
                    4,
                    value
            );

            ps.setString(
                    5,
                    value
            );

            ps.setString(
                    6,
                    value
            );

            ResultSet rs
                    = ps.executeQuery();

            while (rs.next()) {

                bills.add(
                        createBillFromResultSet(
                                rs
                        )
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return bills;
    }

    public Bill getBillById(
            int billId) {

        String sql
                = "SELECT "
                + "b.bill_id, "
                + "b.bill_no, "
                + "b.appointment_no, "
                + "a.patient_id, "
                + "a.dentist_id, "
                + "a.treatment_type, "
                + "b.consultation_fee, "
                + "b.treatment_fee, "
                + "b.discount, "
                + "b.total_amount, "
                + "b.payment_method, "
                + "b.payment_status, "
                + "b.created_at "
                + "FROM bills b "
                + "JOIN appointments a "
                + "ON b.appointment_no = a.appointment_no "
                + "WHERE b.bill_id = ?";

        try {

            Connection con
                    = DatabaseConnection.getConnection();

            PreparedStatement ps
                    = con.prepareStatement(
                            sql
                    );

            ps.setInt(
                    1,
                    billId
            );

            ResultSet rs
                    = ps.executeQuery();

            if (rs.next()) {

                return createBillFromResultSet(
                        rs
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }

    public boolean updatePaymentDetails(
            int billId,
            String paymentMethod,
            String paymentStatus) {

        String sql
                = "UPDATE bills "
                + "SET payment_method = ?, "
                + "payment_status = ? "
                + "WHERE bill_id = ?";

        try {

            Connection con
                    = DatabaseConnection.getConnection();

            PreparedStatement ps
                    = con.prepareStatement(
                            sql
                    );

            ps.setString(
                    1,
                    paymentMethod
            );

            ps.setString(
                    2,
                    paymentStatus
            );

            ps.setInt(
                    3,
                    billId
            );

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    private Bill createBillFromResultSet(
            ResultSet rs)
            throws Exception {

        Bill bill
                = new Bill(
                        rs.getString(
                                "bill_no"
                        ),
                        rs.getString(
                                "appointment_no"
                        ),
                        rs.getBigDecimal(
                                "consultation_fee"
                        ),
                        rs.getBigDecimal(
                                "treatment_fee"
                        ),
                        rs.getBigDecimal(
                                "discount"
                        ),
                        rs.getBigDecimal(
                                "total_amount"
                        ),
                        rs.getString(
                                "payment_method"
                        ),
                        rs.getString(
                                "payment_status"
                        )
                );

        bill.setBillId(
                rs.getInt(
                        "bill_id"
                )
        );

        bill.setPatientId(
                rs.getInt(
                        "patient_id"
                )
        );

        bill.setDentistId(
                rs.getInt(
                        "dentist_id"
                )
        );

        bill.setTreatmentName(
                rs.getString(
                        "treatment_type"
                )
        );

        bill.setCreatedAt(
                rs.getString(
                        "created_at"
                )
        );

        return bill;
    }
}
