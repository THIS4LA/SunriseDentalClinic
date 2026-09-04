package com.sunrisedental.dao;

import com.sunrisedental.database.DatabaseConnection;
import com.sunrisedental.model.Treatment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

public class TreatmentDAO {

    // ==========================================================
    // FIND ACTIVE TREATMENT BY NAME
    // ==========================================================

    public Treatment findByName(
            String treatmentName) {

        String sql =
                "SELECT "
                + "treatment_id, "
                + "treatment_name, "
                + "consultation_fee, "
                + "treatment_fee, "
                + "status "
                + "FROM treatments "
                + "WHERE treatment_name = ? "
                + "AND status = 'ACTIVE'";

        try {

            Connection con =
                    DatabaseConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(
                            sql
                    );

            ps.setString(
                    1,
                    treatmentName
            );

            ResultSet rs =
                    ps.executeQuery();

            if (rs.next()) {

                return createTreatmentFromResultSet(
                        rs
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }

    // ==========================================================
    // INSERT TREATMENT
    // ==========================================================

    public boolean insertTreatment(
            Treatment treatment) {

        String sql =
                "INSERT INTO treatments "
                + "(treatment_name, "
                + "consultation_fee, "
                + "treatment_fee, "
                + "status) "
                + "VALUES (?, ?, ?, ?)";

        try {

            Connection con =
                    DatabaseConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(
                            sql
                    );

            ps.setString(
                    1,
                    treatment.getTreatmentName()
            );

            ps.setBigDecimal(
                    2,
                    treatment.getConsultationFee()
            );

            ps.setBigDecimal(
                    3,
                    treatment.getTreatmentFee()
            );

            ps.setString(
                    4,
                    treatment.getStatus()
            );

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    // ==========================================================
    // UPDATE TREATMENT
    // ==========================================================

    public boolean updateTreatment(
            Treatment treatment) {

        String sql =
                "UPDATE treatments SET "
                + "treatment_name = ?, "
                + "consultation_fee = ?, "
                + "treatment_fee = ?, "
                + "status = ? "
                + "WHERE treatment_id = ?";

        try {

            Connection con =
                    DatabaseConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(
                            sql
                    );

            ps.setString(
                    1,
                    treatment.getTreatmentName()
            );

            ps.setBigDecimal(
                    2,
                    treatment.getConsultationFee()
            );

            ps.setBigDecimal(
                    3,
                    treatment.getTreatmentFee()
            );

            ps.setString(
                    4,
                    treatment.getStatus()
            );

            ps.setInt(
                    5,
                    treatment.getTreatmentId()
            );

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    // ==========================================================
    // UPDATE TREATMENT STATUS
    // ==========================================================

    public boolean updateStatus(
            int treatmentId,
            String status) {

        String sql =
                "UPDATE treatments "
                + "SET status = ? "
                + "WHERE treatment_id = ?";

        try {

            Connection con =
                    DatabaseConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(
                            sql
                    );

            ps.setString(
                    1,
                    status
            );

            ps.setInt(
                    2,
                    treatmentId
            );

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    // ==========================================================
    // GET ALL TREATMENTS
    // ==========================================================

    public List<Treatment> getAllTreatments() {

        List<Treatment> treatments =
                new ArrayList<>();

        String sql =
                "SELECT "
                + "treatment_id, "
                + "treatment_name, "
                + "consultation_fee, "
                + "treatment_fee, "
                + "status "
                + "FROM treatments "
                + "ORDER BY treatment_name ASC";

        try {

            Connection con =
                    DatabaseConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(
                            sql
                    );

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                treatments.add(
                        createTreatmentFromResultSet(
                                rs
                        )
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return treatments;
    }

    // ==========================================================
    // GET ACTIVE TREATMENTS
    // ==========================================================

    public List<Treatment> getActiveTreatments() {

        List<Treatment> treatments =
                new ArrayList<>();

        String sql =
                "SELECT "
                + "treatment_id, "
                + "treatment_name, "
                + "consultation_fee, "
                + "treatment_fee, "
                + "status "
                + "FROM treatments "
                + "WHERE status = 'ACTIVE' "
                + "ORDER BY treatment_name ASC";

        try {

            Connection con =
                    DatabaseConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(
                            sql
                    );

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                treatments.add(
                        createTreatmentFromResultSet(
                                rs
                        )
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return treatments;
    }

    // ==========================================================
    // SEARCH TREATMENTS
    // ==========================================================

    public List<Treatment> searchTreatments(
            String keyword) {

        List<Treatment> treatments =
                new ArrayList<>();

        String sql =
                "SELECT "
                + "treatment_id, "
                + "treatment_name, "
                + "consultation_fee, "
                + "treatment_fee, "
                + "status "
                + "FROM treatments "
                + "WHERE CAST(treatment_id AS CHAR) LIKE ? "
                + "OR treatment_name LIKE ? "
                + "OR status LIKE ? "
                + "ORDER BY treatment_name ASC";

        try {

            Connection con =
                    DatabaseConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(
                            sql
                    );

            String value =
                    "%"
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

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                treatments.add(
                        createTreatmentFromResultSet(
                                rs
                        )
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return treatments;
    }

    // ==========================================================
    // CHECK TREATMENT NAME EXISTS
    // ==========================================================

    public boolean treatmentNameExists(
            String treatmentName) {

        String sql =
                "SELECT treatment_id "
                + "FROM treatments "
                + "WHERE treatment_name = ?";

        try {

            Connection con =
                    DatabaseConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(
                            sql
                    );

            ps.setString(
                    1,
                    treatmentName
            );

            ResultSet rs =
                    ps.executeQuery();

            return rs.next();

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    // ==========================================================
    // FIND TREATMENT BY ID
    // ==========================================================

    public Treatment findById(
            int treatmentId) {

        String sql =
                "SELECT "
                + "treatment_id, "
                + "treatment_name, "
                + "consultation_fee, "
                + "treatment_fee, "
                + "status "
                + "FROM treatments "
                + "WHERE treatment_id = ?";

        try {

            Connection con =
                    DatabaseConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(
                            sql
                    );

            ps.setInt(
                    1,
                    treatmentId
            );

            ResultSet rs =
                    ps.executeQuery();

            if (rs.next()) {

                return createTreatmentFromResultSet(
                        rs
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }

    // ==========================================================
    // RESULT SET MAPPER
    // ==========================================================

    private Treatment createTreatmentFromResultSet(
            ResultSet rs)
            throws Exception {

        return new Treatment(
                rs.getInt(
                        "treatment_id"
                ),

                rs.getString(
                        "treatment_name"
                ),

                rs.getBigDecimal(
                        "consultation_fee"
                ),

                rs.getBigDecimal(
                        "treatment_fee"
                ),

                rs.getString(
                        "status"
                )
        );
    }
}