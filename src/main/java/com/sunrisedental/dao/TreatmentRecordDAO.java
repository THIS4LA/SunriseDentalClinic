package com.sunrisedental.dao;

import com.sunrisedental.database.DatabaseConnection;
import com.sunrisedental.model.TreatmentRecord;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

public class TreatmentRecordDAO {

    public boolean insert(
            TreatmentRecord record) {

        String sql =
                "INSERT INTO treatment_records "
                + "(appointment_no, "
                + "patient_id, "
                + "dentist_id, "
                + "diagnosis, "
                + "treatment_performed, "
                + "clinical_notes, "
                + "recommendation, "
                + "follow_up_required, "
                + "follow_up_date) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {

            Connection con =
                    DatabaseConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(
                    1,
                    record.getAppointmentNo()
            );

            ps.setInt(
                    2,
                    record.getPatientId()
            );

            ps.setInt(
                    3,
                    record.getDentistId()
            );

            ps.setString(
                    4,
                    record.getDiagnosis()
            );

            ps.setString(
                    5,
                    record.getTreatmentPerformed()
            );

            ps.setString(
                    6,
                    record.getClinicalNotes()
            );

            ps.setString(
                    7,
                    record.getRecommendation()
            );

            ps.setBoolean(
                    8,
                    record.isFollowUpRequired()
            );

            if (record.getFollowUpDate() == null
                    || record.getFollowUpDate()
                            .trim()
                            .isEmpty()) {

                ps.setNull(
                        9,
                        java.sql.Types.DATE
                );

            } else {

                ps.setString(
                        9,
                        record.getFollowUpDate()
                );
            }

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    public boolean update(
            TreatmentRecord record) {

        String sql =
                "UPDATE treatment_records SET "
                + "diagnosis = ?, "
                + "treatment_performed = ?, "
                + "clinical_notes = ?, "
                + "recommendation = ?, "
                + "follow_up_required = ?, "
                + "follow_up_date = ? "
                + "WHERE record_id = ? "
                + "AND dentist_id = ?";

        try {

            Connection con =
                    DatabaseConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(
                    1,
                    record.getDiagnosis()
            );

            ps.setString(
                    2,
                    record.getTreatmentPerformed()
            );

            ps.setString(
                    3,
                    record.getClinicalNotes()
            );

            ps.setString(
                    4,
                    record.getRecommendation()
            );

            ps.setBoolean(
                    5,
                    record.isFollowUpRequired()
            );

            if (record.getFollowUpDate() == null
                    || record.getFollowUpDate()
                            .trim()
                            .isEmpty()) {

                ps.setNull(
                        6,
                        java.sql.Types.DATE
                );

            } else {

                ps.setString(
                        6,
                        record.getFollowUpDate()
                );
            }

            ps.setInt(
                    7,
                    record.getRecordId()
            );

            ps.setInt(
                    8,
                    record.getDentistId()
            );

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    public List<TreatmentRecord> getRecordsByDentistId(
            int dentistId) {

        List<TreatmentRecord> records =
                new ArrayList<>();

        String sql =
                "SELECT * "
                + "FROM treatment_records "
                + "WHERE dentist_id = ? "
                + "ORDER BY created_at DESC";

        try {

            Connection con =
                    DatabaseConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(
                    1,
                    dentistId
            );

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                records.add(
                        createRecordFromResultSet(
                                rs
                        )
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return records;
    }

    public List<TreatmentRecord> searchByDentistId(
            int dentistId,
            String keyword) {

        List<TreatmentRecord> records =
                new ArrayList<>();

        String sql =
                "SELECT tr.* "
                + "FROM treatment_records tr "
                + "JOIN patients p "
                + "ON tr.patient_id = p.patient_id "
                + "WHERE tr.dentist_id = ? "
                + "AND ("
                + "tr.appointment_no LIKE ? "
                + "OR p.name LIKE ? "
                + "OR tr.diagnosis LIKE ? "
                + "OR tr.treatment_performed LIKE ?"
                + ") "
                + "ORDER BY tr.created_at DESC";

        try {

            Connection con =
                    DatabaseConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            String value =
                    "%" + keyword + "%";

            ps.setInt(
                    1,
                    dentistId
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

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                records.add(
                        createRecordFromResultSet(
                                rs
                        )
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return records;
    }

    public TreatmentRecord findByAppointmentNo(
            String appointmentNo,
            int dentistId) {

        String sql =
                "SELECT * "
                + "FROM treatment_records "
                + "WHERE appointment_no = ? "
                + "AND dentist_id = ?";

        try {

            Connection con =
                    DatabaseConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(
                    1,
                    appointmentNo
            );

            ps.setInt(
                    2,
                    dentistId
            );

            ResultSet rs =
                    ps.executeQuery();

            if (rs.next()) {

                return createRecordFromResultSet(
                        rs
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }

    public boolean existsForAppointment(
            String appointmentNo) {

        String sql =
                "SELECT COUNT(*) "
                + "FROM treatment_records "
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

    private TreatmentRecord createRecordFromResultSet(
            ResultSet rs) throws Exception {

        return new TreatmentRecord(
                rs.getInt(
                        "record_id"
                ),
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
                        "diagnosis"
                ),
                rs.getString(
                        "treatment_performed"
                ),
                rs.getString(
                        "clinical_notes"
                ),
                rs.getString(
                        "recommendation"
                ),
                rs.getBoolean(
                        "follow_up_required"
                ),
                rs.getString(
                        "follow_up_date"
                )
        );
    }
}