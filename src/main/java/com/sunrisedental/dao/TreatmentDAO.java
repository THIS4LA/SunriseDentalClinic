package com.sunrisedental.dao;

import com.sunrisedental.database.DatabaseConnection;
import com.sunrisedental.model.Treatment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TreatmentDAO {

    public Treatment findByName(
            String treatmentName) {

        String sql =
                "SELECT * FROM treatments "
                + "WHERE treatment_name = ? "
                + "AND status = 'ACTIVE'";

        try {

            Connection con =
                    DatabaseConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(
                    1,
                    treatmentName
            );

            ResultSet rs =
                    ps.executeQuery();

            if (rs.next()) {

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

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }
}