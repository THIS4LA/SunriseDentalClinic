package com.sunrisedental.view.receptionist;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DashboardPanel extends JPanel {

    public DashboardPanel() {

        initUI();
    }

    private void initUI() {

        setLayout(
                new BorderLayout()
        );

        setBackground(
                new Color(
                        245,
                        247,
                        250
                )
        );

        createHeader();
        createDashboardContent();
    }

    private void createHeader() {

        JPanel pnlHeader =
                new JPanel(
                        new BorderLayout()
                );

        pnlHeader.setBackground(
                Color.WHITE
        );

        pnlHeader.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        30,
                        20,
                        30
                )
        );

        JLabel lblTitle =
                new JLabel(
                        "Receptionist Dashboard"
                );

        lblTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        24
                )
        );

        JLabel lblWelcome =
                new JLabel(
                        "Welcome, Receptionist"
                );

        lblWelcome.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        pnlHeader.add(
                lblTitle,
                BorderLayout.WEST
        );

        pnlHeader.add(
                lblWelcome,
                BorderLayout.EAST
        );

        add(
                pnlHeader,
                BorderLayout.NORTH
        );
    }

    private void createDashboardContent() {

        JPanel pnlDashboard =
                new JPanel();

        pnlDashboard.setBackground(
                new Color(
                        245,
                        247,
                        250
                )
        );

        pnlDashboard.setBorder(
                BorderFactory.createEmptyBorder(
                        25,
                        30,
                        25,
                        30
                )
        );

        pnlDashboard.setLayout(
                new GridLayout(
                        2,
                        1,
                        20,
                        25
                )
        );

        JPanel pnlCards =
                new JPanel(
                        new GridLayout(
                                1,
                                3,
                                20,
                                0
                        )
                );

        pnlCards.setOpaque(false);

        pnlCards.add(
                createCard(
                        "Today's Appointments",
                        "0"
                )
        );

        pnlCards.add(
                createCard(
                        "Pending",
                        "0"
                )
        );

        pnlCards.add(
                createCard(
                        "Completed",
                        "0"
                )
        );

        JPanel pnlActions =
                new JPanel(
                        new GridLayout(
                                2,
                                2,
                                20,
                                20
                        )
                );

        pnlActions.setOpaque(false);

        JButton btnRegisterPatient =
                new JButton(
                        "Register Patient"
                );

        JButton btnNewAppointment =
                new JButton(
                        "New Appointment"
                );

        JButton btnSearchAppointment =
                new JButton(
                        "Search Appointment"
                );

        JButton btnGenerateBill =
                new JButton(
                        "Generate Bill"
                );

        pnlActions.add(
                btnRegisterPatient
        );

        pnlActions.add(
                btnNewAppointment
        );

        pnlActions.add(
                btnSearchAppointment
        );

        pnlActions.add(
                btnGenerateBill
        );

        pnlDashboard.add(
                pnlCards
        );

        pnlDashboard.add(
                pnlActions
        );

        add(
                pnlDashboard,
                BorderLayout.CENTER
        );
    }

    private JPanel createCard(
            String title,
            String value) {

        JPanel panel =
                new JPanel(
                        new BorderLayout()
                );

        panel.setBackground(
                Color.WHITE
        );

        panel.setBorder(
                BorderFactory.createCompoundBorder(

                        BorderFactory.createLineBorder(
                                new Color(
                                        220,
                                        220,
                                        220
                                )
                        ),

                        BorderFactory.createEmptyBorder(
                                20,
                                20,
                                20,
                                20
                        )
                )
        );

        JLabel lblTitle =
                new JLabel(
                        title
                );

        lblTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        15
                )
        );

        JLabel lblValue =
                new JLabel(
                        value
                );

        lblValue.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        28
                )
        );

        panel.add(
                lblTitle,
                BorderLayout.NORTH
        );

        panel.add(
                lblValue,
                BorderLayout.CENTER
        );

        return panel;
    }
}