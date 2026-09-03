package com.sunrisedental.view.admin;

import com.sunrisedental.service.AppointmentService;
import com.sunrisedental.service.DentistService;
import com.sunrisedental.service.PatientService;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DashboardPanel
        extends JPanel {

    private final int userId;

    private final AppointmentService appointmentService;
    private final DentistService dentistService;
    private final PatientService patientService;

    private final Runnable openUsers;
    private final Runnable openDentists;
    private final Runnable openTreatments;
    private final Runnable openReports;

    private JLabel lblTotalUsers;
    private JLabel lblActiveDentists;
    private JLabel lblTotalPatients;
    private JLabel lblTodayAppointments;

    public DashboardPanel(
            int userId,
            Runnable openUsers,
            Runnable openDentists,
            Runnable openTreatments,
            Runnable openReports) {

        this.userId =
                userId;

        this.openUsers =
                openUsers;

        this.openDentists =
                openDentists;

        this.openTreatments =
                openTreatments;

        this.openReports =
                openReports;

        appointmentService =
                new AppointmentService();

        dentistService =
                new DentistService();

        patientService =
                new PatientService();

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
                        "Admin Dashboard"
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
                        "Welcome, Administrator"
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
        // KPI CARDS
        // ======================================================

        JPanel pnlCards =
                new JPanel(
                        new GridLayout(
                                1,
                                4,
                                20,
                                0
                        )
                );

        pnlCards.setOpaque(
                false
        );

        lblTotalUsers =
                new JLabel(
                        "0"
                );

        lblActiveDentists =
                new JLabel(
                        "0"
                );

        lblTotalPatients =
                new JLabel(
                        "0"
                );

        lblTodayAppointments =
                new JLabel(
                        "0"
                );

        pnlCards.add(
                createCard(
                        "Total Users",
                        lblTotalUsers
                )
        );

        pnlCards.add(
                createCard(
                        "Active Dentists",
                        lblActiveDentists
                )
        );

        pnlCards.add(
                createCard(
                        "Total Patients",
                        lblTotalPatients
                )
        );

        pnlCards.add(
                createCard(
                        "Today's Appointments",
                        lblTodayAppointments
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

        JButton btnUsers =
                createActionButton(
                        "Manage Users"
                );

        JButton btnDentists =
                createActionButton(
                        "Manage Dentists"
                );

        JButton btnTreatments =
                createActionButton(
                        "Manage Treatments"
                );

        JButton btnReports =
                createActionButton(
                        "View Reports"
                );

        btnUsers.addActionListener(
                e -> openUsers.run()
        );

        btnDentists.addActionListener(
                e -> openDentists.run()
        );

        btnTreatments.addActionListener(
                e -> openTreatments.run()
        );

        btnReports.addActionListener(
                e -> openReports.run()
        );

        pnlActions.add(
                btnUsers
        );

        pnlActions.add(
                btnDentists
        );

        pnlActions.add(
                btnTreatments
        );

        pnlActions.add(
                btnReports
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
    // KPI CARD
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
                        14
                )
        );

        lblValue.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        27
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
    // KPI DATA
    // ==========================================================

    private void loadDashboardStatistics() {

        /*
         * Add the real user count after we create
         * UserService / UserDAO admin methods.
         */
        lblTotalUsers.setText(
                "0"
        );

        lblActiveDentists.setText(
                String.valueOf(
                        dentistService
                                .getActiveDentists()
                                .size()
                )
        );

        lblTotalPatients.setText(
                String.valueOf(
                        patientService
                                .getAllPatients()
                                .size()
                )
        );

        lblTodayAppointments.setText(
                String.valueOf(
                        appointmentService
                                .getTodayAppointmentCount()
                )
        );
    }

    public void refreshDashboard() {

        loadDashboardStatistics();
    }
}