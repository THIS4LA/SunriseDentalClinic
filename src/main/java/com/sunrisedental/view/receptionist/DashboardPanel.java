package com.sunrisedental.view.receptionist;

import com.sunrisedental.service.AppointmentService;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DashboardPanel extends JPanel {

    private final AppointmentService appointmentService;

    private final Runnable openPatients;
    private final Runnable openAppointments;
    private final Runnable openBilling;

    private JLabel lblTodayAppointments;
    private JLabel lblPending;
    private JLabel lblCompleted;

    public DashboardPanel(
            Runnable openPatients,
            Runnable openAppointments,
            Runnable openBilling) {

        this.openPatients
                = openPatients;

        this.openAppointments
                = openAppointments;

        this.openBilling
                = openBilling;

        appointmentService
                = new AppointmentService();

        initUI();

        loadDashboardStatistics();
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

    // ==========================================================
    // HEADER
    // ==========================================================
    private void createHeader() {

        JPanel pnlHeader
                = new JPanel(
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

        JLabel lblTitle
                = new JLabel(
                        "Receptionist Dashboard"
                );

        lblTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        24
                )
        );

        JLabel lblWelcome
                = new JLabel(
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

    // ==========================================================
    // DASHBOARD CONTENT
    // ==========================================================
    private void createDashboardContent() {

        JPanel pnlDashboard
                = new JPanel();

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
        JPanel pnlCards
                = new JPanel(
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

        lblTodayAppointments
                = new JLabel(
                        "0"
                );

        lblPending
                = new JLabel(
                        "0"
                );

        lblCompleted
                = new JLabel(
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
        JPanel pnlActions
                = new JPanel(
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

        JButton btnRegisterPatient
                = createActionButton(
                        "Register Patient"
                );

        JButton btnNewAppointment
                = createActionButton(
                        "New Appointment"
                );

        JButton btnSearchAppointment
                = createActionButton(
                        "Search Appointment"
                );

        JButton btnGenerateBill
                = createActionButton(
                        "Generate Bill"
                );

        // ======================================================
        // NAVIGATION
        // ======================================================
        btnRegisterPatient.addActionListener(
                e -> openPatients.run()
        );

        btnNewAppointment.addActionListener(
                e -> openAppointments.run()
        );

        btnSearchAppointment.addActionListener(
                e -> openAppointments.run()
        );

        btnGenerateBill.addActionListener(
                e -> openBilling.run()
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

    // ==========================================================
    // STATISTIC CARD
    // ==========================================================
    private JPanel createCard(
            String title,
            JLabel lblValue) {

        JPanel panel
                = new JPanel(
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

        JLabel lblTitle
                = new JLabel(
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

        JButton button
                = new JButton(
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
    // LOAD ACTUAL DATABASE VALUES
    // ==========================================================
    private void loadDashboardStatistics() {

        int today
                = appointmentService
                        .getTodayAppointmentCount();

        int pending
                = appointmentService
                        .getTodayPendingCount();

        int completed
                = appointmentService
                        .getTodayCompletedCount();

        lblTodayAppointments.setText(
                String.valueOf(
                        today
                )
        );

        lblPending.setText(
                String.valueOf(
                        pending
                )
        );

        lblCompleted.setText(
                String.valueOf(
                        completed
                )
        );
    }

    // ==========================================================
    // REFRESH DASHBOARD
    // ==========================================================
    public void refreshDashboard() {

        loadDashboardStatistics();
    }
}
