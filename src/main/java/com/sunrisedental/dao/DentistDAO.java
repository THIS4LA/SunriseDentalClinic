package com.sunrisedental.dao;

import com.sunrisedental.database.DatabaseConnection;
import com.sunrisedental.model.Dentist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

public class DentistDAO {

    // ==========================================================
    // INSERT DENTIST
    // ==========================================================
    public boolean insert(
            Dentist dentist) {

        String sql
                = "INSERT INTO dentists "
                + "(user_id, "
                + "dentist_name, "
                + "specialization, "
                + "contact_number, "
                + "email, "
                + "status) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try {

            Connection con
                    = DatabaseConnection.getConnection();

            PreparedStatement ps
                    = con.prepareStatement(sql);

            ps.setInt(
                    1,
                    dentist.getUserId()
            );

            ps.setString(
                    2,
                    dentist.getName()
            );

            ps.setString(
                    3,
                    dentist.getSpecialization()
            );

            ps.setString(
                    4,
                    dentist.getContactNumber()
            );

            ps.setString(
                    5,
                    dentist.getEmail()
            );

            ps.setString(
                    6,
                    dentist.getStatus()
            );

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    // ==========================================================
    // UPDATE DENTIST
    // ==========================================================
    public boolean update(
            Dentist dentist) {

        String sql
                = "UPDATE dentists SET "
                + "dentist_name = ?, "
                + "specialization = ?, "
                + "contact_number = ?, "
                + "email = ?, "
                + "status = ? "
                + "WHERE dentist_id = ?";

        try {

            Connection con
                    = DatabaseConnection.getConnection();

            PreparedStatement ps
                    = con.prepareStatement(sql);

            ps.setString(
                    1,
                    dentist.getName()
            );

            ps.setString(
                    2,
                    dentist.getSpecialization()
            );

            ps.setString(
                    3,
                    dentist.getContactNumber()
            );

            ps.setString(
                    4,
                    dentist.getEmail()
            );

            ps.setString(
                    5,
                    dentist.getStatus()
            );

            ps.setInt(
                    6,
                    dentist.getDentistId()
            );

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    public boolean updateOwnProfile(
            int dentistId,
            String contactNumber,
            String email) {

        String sql
                = "UPDATE dentists "
                + "SET contact_number = ?, "
                + "email = ? "
                + "WHERE dentist_id = ?";

        try {

            Connection con
                    = DatabaseConnection.getConnection();

            PreparedStatement ps
                    = con.prepareStatement(sql);

            ps.setString(
                    1,
                    contactNumber
            );

            ps.setString(
                    2,
                    email
            );

            ps.setInt(
                    3,
                    dentistId
            );

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    // ==========================================================
    // FIND BY DENTIST ID
    // ==========================================================
    public Dentist findByDentistId(
            int dentistId) {

        String sql
                = "SELECT * "
                + "FROM dentists "
                + "WHERE dentist_id = ?";

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

            if (rs.next()) {

                return createDentistFromResultSet(
                        rs
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }

    // ==========================================================
    // FIND BY USER ID
    // ==========================================================
    public Dentist findByUserId(
            int userId) {

        String sql
                = "SELECT * "
                + "FROM dentists "
                + "WHERE user_id = ?";

        try {

            Connection con
                    = DatabaseConnection.getConnection();

            PreparedStatement ps
                    = con.prepareStatement(sql);

            ps.setInt(
                    1,
                    userId
            );

            ResultSet rs
                    = ps.executeQuery();

            if (rs.next()) {

                return createDentistFromResultSet(
                        rs
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }

    // ==========================================================
    // GET DENTIST ID FROM USER ID
    // ==========================================================
    public int getDentistIdByUserId(
            int userId) {

        String sql
                = "SELECT dentist_id "
                + "FROM dentists "
                + "WHERE user_id = ?";

        try {

            Connection con
                    = DatabaseConnection.getConnection();

            PreparedStatement ps
                    = con.prepareStatement(sql);

            ps.setInt(
                    1,
                    userId
            );

            ResultSet rs
                    = ps.executeQuery();

            if (rs.next()) {

                return rs.getInt(
                        "dentist_id"
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return -1;
    }

    // ==========================================================
    // GET ALL DENTISTS
    // ==========================================================
    public List<Dentist> getAll() {

        List<Dentist> dentists
                = new ArrayList<>();

        String sql
                = "SELECT * "
                + "FROM dentists "
                + "ORDER BY dentist_name ASC";

        try {

            Connection con
                    = DatabaseConnection.getConnection();

            PreparedStatement ps
                    = con.prepareStatement(sql);

            ResultSet rs
                    = ps.executeQuery();

            while (rs.next()) {

                dentists.add(
                        createDentistFromResultSet(
                                rs
                        )
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return dentists;
    }

    // ==========================================================
    // GET ACTIVE DENTISTS
    // ==========================================================
    public List<Dentist> getActiveDentists() {

        List<Dentist> dentists
                = new ArrayList<>();

        String sql
                = "SELECT * "
                + "FROM dentists "
                + "WHERE status = 'ACTIVE' "
                + "ORDER BY dentist_name ASC";

        try {

            Connection con
                    = DatabaseConnection.getConnection();

            PreparedStatement ps
                    = con.prepareStatement(sql);

            ResultSet rs
                    = ps.executeQuery();

            while (rs.next()) {

                dentists.add(
                        createDentistFromResultSet(
                                rs
                        )
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return dentists;
    }

    // ==========================================================
    // SEARCH DENTISTS
    // ==========================================================
    public List<Dentist> search(
            String keyword) {

        List<Dentist> dentists
                = new ArrayList<>();

        String sql
                = "SELECT * "
                + "FROM dentists "
                + "WHERE dentist_name LIKE ? "
                + "OR specialization LIKE ? "
                + "OR email LIKE ? "
                + "OR contact_number LIKE ? "
                + "ORDER BY dentist_name ASC";

        try {

            Connection con
                    = DatabaseConnection.getConnection();

            PreparedStatement ps
                    = con.prepareStatement(sql);

            String searchValue
                    = "%" + keyword + "%";

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

            ResultSet rs
                    = ps.executeQuery();

            while (rs.next()) {

                dentists.add(
                        createDentistFromResultSet(
                                rs
                        )
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return dentists;
    }

    // ==========================================================
    // CHECK USER ALREADY LINKED TO DENTIST
    // ==========================================================
    public boolean existsByUserId(
            int userId) {

        String sql
                = "SELECT COUNT(*) "
                + "FROM dentists "
                + "WHERE user_id = ?";

        try {

            Connection con
                    = DatabaseConnection.getConnection();

            PreparedStatement ps
                    = con.prepareStatement(sql);

            ps.setInt(
                    1,
                    userId
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

    // ==========================================================
    // CHECK EMAIL
    // ==========================================================
    public boolean existsByEmail(
            String email) {

        String sql
                = "SELECT COUNT(*) "
                + "FROM dentists "
                + "WHERE email = ?";

        try {

            Connection con
                    = DatabaseConnection.getConnection();

            PreparedStatement ps
                    = con.prepareStatement(sql);

            ps.setString(
                    1,
                    email
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

    // ==========================================================
    // CHANGE STATUS
    // ==========================================================
    public boolean updateStatus(
            int dentistId,
            String status) {

        String sql
                = "UPDATE dentists "
                + "SET status = ? "
                + "WHERE dentist_id = ?";

        try {

            Connection con
                    = DatabaseConnection.getConnection();

            PreparedStatement ps
                    = con.prepareStatement(sql);

            ps.setString(
                    1,
                    status
            );

            ps.setInt(
                    2,
                    dentistId
            );

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    // ==========================================================
    // RESULTSET -> DENTIST
    // ==========================================================
    private Dentist createDentistFromResultSet(
            ResultSet rs) throws Exception {

        return new Dentist(
                rs.getInt(
                        "dentist_id"
                ),
                rs.getInt(
                        "user_id"
                ),
                rs.getString(
                        "dentist_name"
                ),
                rs.getString(
                        "specialization"
                ),
                rs.getString(
                        "contact_number"
                ),
                rs.getString(
                        "email"
                ),
                rs.getString(
                        "status"
                )
        );
    }
}
