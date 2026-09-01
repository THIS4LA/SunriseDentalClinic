package com.sunrisedental.view.dentist;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DashboardPanel extends JPanel {

    private final int userId;

    private final Runnable openAppointments;
    private final Runnable openPatients;
    private final Runnable openTreatmentRecords;
    private final Runnable openSchedule;

    private JLabel lblTodayAppointments;
    private JLabel lblPending;
    private JLabel lblCompleted;

    public DashboardPanel(
            int userId,
            Runnable openAppointments,
            Runnable openPatients,
            Runnable openTreatmentRecords,
            Runnable openSchedule) {

        this.userId = userId;

        this.openAppointments =
                openAppointments;

        this.openPatients =
                openPatients;

        this.openTreatmentRecords =
                openTreatmentRecords;

        this.openSchedule =
                openSchedule;

        initUI();

        loadDashboardStatistics();
    }

    // ==========================================================
    // MAIN UI
    // ==========================================================

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

    // ==========================================================
    // HEADER
    // ==========================================================

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
                        "Dentist Dashboard"
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
                        "Welcome, Dentist"
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

    // ==========================================================
    // DASHBOARD CONTENT
    // ==========================================================

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

        // ======================================================
        // STATISTIC CARDS
        // ======================================================

        JPanel pnlCards =
                new JPanel(
                        new GridLayout(
                                1,
                                3,
                                20,
                                0
                        )
                );

        pnlCards.setOpaque(
                false
        );

        lblTodayAppointments =
                new JLabel(
                        "0"
                );

        lblPending =
                new JLabel(
                        "0"
                );

        lblCompleted =
                new JLabel(
                        "0"
                );

        pnlCards.add(
                createCard(
                        "Today's Appointments",
                        lblTodayAppointments
                )
        );

        pnlCards.add(
                createCard(
                        "Pending",
                        lblPending
                )
        );

        pnlCards.add(
                createCard(
                        "Completed",
                        lblCompleted
                )
        );

        // ======================================================
        // QUICK ACTIONS
        // ======================================================

        JPanel pnlActions =
                new JPanel(
                        new GridLayout(
                                2,
                                2,
                                20,
                                20
                        )
                );

        pnlActions.setOpaque(
                false
        );

        JButton btnAppointments =
                createActionButton(
                        "My Appointments"
                );

        JButton btnPatients =
                createActionButton(
                        "View Patients"
                );

        JButton btnTreatmentRecords =
                createActionButton(
                        "Treatment Records"
                );

        JButton btnSchedule =
                createActionButton(
                        "My Schedule"
                );

        // ======================================================
        // ACTION LISTENERS
        // ======================================================

        btnAppointments.addActionListener(
                e -> openAppointments.run()
        );

        btnPatients.addActionListener(
                e -> openPatients.run()
        );

        btnTreatmentRecords.addActionListener(
                e -> openTreatmentRecords.run()
        );

        btnSchedule.addActionListener(
                e -> openSchedule.run()
        );

        pnlActions.add(
                btnAppointments
        );

        pnlActions.add(
                btnPatients
        );

        pnlActions.add(
                btnTreatmentRecords
        );

        pnlActions.add(
                btnSchedule
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

    // ==========================================================
    // DASHBOARD CARD
    // ==========================================================

    private JPanel createCard(
            String title,
            JLabel lblValue) {

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

    // ==========================================================
    // QUICK ACTION BUTTON
    // ==========================================================

    private JButton createActionButton(
            String text) {

        JButton button =
                new JButton(
                        text
                );

        button.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
                )
        );

        button.setFocusPainted(
                false
        );

        return button;
    }

    // ==========================================================
    // DASHBOARD STATISTICS
    // ==========================================================

    private void loadDashboardStatistics() {

        /*
         * We will connect these values to the database
         * after creating DentistService / DentistDAO.
         *
         * The queries must use userId so that the dentist
         * only sees their own appointments.
         */

        lblTodayAppointments.setText(
                "0"
        );

        lblPending.setText(
                "0"
        );

        lblCompleted.setText(
                "0"
        );
    }

    // ==========================================================
    // REFRESH
    // ==========================================================

    public void refreshDashboard() {

        loadDashboardStatistics();
    }
}