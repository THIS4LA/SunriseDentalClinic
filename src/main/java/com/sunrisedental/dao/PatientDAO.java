package com.sunrisedental.dao;

import com.sunrisedental.database.DatabaseConnection;
import com.sunrisedental.model.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO {

    public boolean insert(Patient patient) {

        String sql
                = "INSERT INTO patients "
                + "(name, address, contact_number, email) "
                + "VALUES (?, ?, ?, ?)";

        try {

            Connection con
                    = DatabaseConnection.getConnection();

            PreparedStatement ps
                    = con.prepareStatement(sql);

            ps.setString(
                    1,
                    patient.getName()
            );

            ps.setString(
                    2,
                    patient.getAddress()
            );

            ps.setString(
                    3,
                    patient.getContactNumber()
            );

            ps.setString(
                    4,
                    patient.getEmail()
            );

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    public boolean update(Patient patient) {

        String sql
                = "UPDATE patients "
                + "SET name = ?, "
                + "address = ?, "
                + "contact_number = ?, "
                + "email = ? "
                + "WHERE patient_id = ?";

        try {

            Connection con
                    = DatabaseConnection.getConnection();

            PreparedStatement ps
                    = con.prepareStatement(sql);

            ps.setString(
                    1,
                    patient.getName()
            );

            ps.setString(
                    2,
                    patient.getAddress()
            );

            ps.setString(
                    3,
                    patient.getContactNumber()
            );

            ps.setString(
                    4,
                    patient.getEmail()
            );

            ps.setInt(
                    5,
                    patient.getPatientId()
            );

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    public boolean delete(int patientId) {

        String sql
                = "DELETE FROM patients "
                + "WHERE patient_id = ?";

        try {

            Connection con
                    = DatabaseConnection.getConnection();

            PreparedStatement ps
                    = con.prepareStatement(sql);

            ps.setInt(
                    1,
                    patientId
            );

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    public Patient findById(int patientId) {

        String sql
                = "SELECT patient_id, name, address, contact_number, email "
                + "FROM patients "
                + "WHERE patient_id = ?";

        try {

            Connection con
                    = DatabaseConnection.getConnection();

            PreparedStatement ps
                    = con.prepareStatement(sql);

            ps.setInt(
                    1,
                    patientId
            );

            ResultSet rs
                    = ps.executeQuery();

            if (rs.next()) {

                return new Patient(
                        rs.getInt("patient_id"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("contact_number"),
                        rs.getString("email")
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }

    public List<Patient> getAll() {

        List<Patient> patients
                = new ArrayList<>();

        String sql
                = "SELECT patient_id, name, address, contact_number, email "
                + "FROM patients "
                + "ORDER BY patient_id DESC";

        try {

            Connection con
                    = DatabaseConnection.getConnection();

            PreparedStatement ps
                    = con.prepareStatement(sql);

            ResultSet rs
                    = ps.executeQuery();

            while (rs.next()) {

                Patient patient
                        = new Patient(
                                rs.getInt("patient_id"),
                                rs.getString("name"),
                                rs.getString("address"),
                                rs.getString("contact_number"),
                                rs.getString("email")
                        );

                patients.add(
                        patient
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return patients;
    }

    public List<Patient> getPatientsByDentistId(
            int dentistId) {

        List<Patient> patients
                = new ArrayList<>();

        String sql
                = "SELECT DISTINCT "
                + "p.patient_id, "
                + "p.name, "
                + "p.address, "
                + "p.contact_number, "
                + "p.email "
                + "FROM patients p "
                + "JOIN appointments a "
                + "ON p.patient_id = a.patient_id "
                + "WHERE a.dentist_id = ? "
                + "ORDER BY p.name ASC";

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

                Patient patient
                        = new Patient(
                                rs.getInt(
                                        "patient_id"
                                ),
                                rs.getString(
                                        "name"
                                ),
                                rs.getString(
                                        "address"
                                ),
                                rs.getString(
                                        "contact_number"
                                ),
                                rs.getString(
                                        "email"
                                )
                        );

                patients.add(
                        patient
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return patients;
    }

    public List<Patient> search(String keyword) {

        List<Patient> patients
                = new ArrayList<>();

        String sql
                = "SELECT patient_id, name, address, contact_number, email "
                + "FROM patients "
                + "WHERE name LIKE ? "
                + "OR contact_number LIKE ? "
                + "OR email LIKE ? "
                + "ORDER BY patient_id DESC";

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

            ResultSet rs
                    = ps.executeQuery();

            while (rs.next()) {

                Patient patient
                        = new Patient(
                                rs.getInt("patient_id"),
                                rs.getString("name"),
                                rs.getString("address"),
                                rs.getString("contact_number"),
                                rs.getString("email")
                        );

                patients.add(
                        patient
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return patients;
    }

    public List<Patient> searchPatientsByDentistId(
            int dentistId,
            String keyword) {

        List<Patient> patients
                = new ArrayList<>();

        String sql
                = "SELECT DISTINCT "
                + "p.patient_id, "
                + "p.name, "
                + "p.address, "
                + "p.contact_number, "
                + "p.email "
                + "FROM patients p "
                + "JOIN appointments a "
                + "ON p.patient_id = a.patient_id "
                + "WHERE a.dentist_id = ? "
                + "AND ("
                + "p.name LIKE ? "
                + "OR p.contact_number LIKE ? "
                + "OR p.email LIKE ? "
                + "OR CAST(p.patient_id AS CHAR) LIKE ?"
                + ") "
                + "ORDER BY p.name ASC";

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

                Patient patient
                        = new Patient(
                                rs.getInt(
                                        "patient_id"
                                ),
                                rs.getString(
                                        "name"
                                ),
                                rs.getString(
                                        "address"
                                ),
                                rs.getString(
                                        "contact_number"
                                ),
                                rs.getString(
                                        "email"
                                )
                        );

                patients.add(
                        patient
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return patients;
    }
}
