package com.sunrisedental.view.dentist;

import com.sunrisedental.view.LoginFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class DentistDashboardFrame extends JFrame {

    private JPanel pnlSidebar;
    private JPanel pnlContent;

    private JButton btnDashboard;
    private JButton btnAppointments;
    private JButton btnSchedule;
    private JButton btnPatients;
    private JButton btnTreatmentRecords;
    private JButton btnReports;
    private JButton btnProfile;
    private JButton btnHelp;
    private JButton btnLogout;

    private final int userId;

    public DentistDashboardFrame(int userId) {

        this.userId = userId;

        initUI();

        setTitle(
                "Sunrise Dental Clinic - Dentist Dashboard"
        );

        setSize(
                1100,
                650
        );

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );
    }

    // ==========================================================
    // MAIN UI
    // ==========================================================
    private void initUI() {

        setLayout(
                new BorderLayout()
        );

        createSidebar();

        createContentArea();

        add(
                pnlSidebar,
                BorderLayout.WEST
        );

        add(
                pnlContent,
                BorderLayout.CENTER
        );
    }

    // ==========================================================
    // SIDEBAR
    // ==========================================================
    private void createSidebar() {

        pnlSidebar
                = new JPanel();

        pnlSidebar.setPreferredSize(
                new Dimension(
                        230,
                        0
                )
        );

        pnlSidebar.setBackground(
                new Color(
                        31,
                        78,
                        121
                )
        );

        pnlSidebar.setLayout(
                new GridLayout(
                        11,
                        1,
                        0,
                        8
                )
        );

        pnlSidebar.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        15,
                        20,
                        15
                )
        );

        // ======================================================
        // CLINIC NAME
        // ======================================================
        JLabel lblClinic
                = new JLabel(
                        "SUNRISE DENTAL",
                        SwingConstants.CENTER
                );

        lblClinic.setForeground(
                Color.WHITE
        );

        lblClinic.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        18
                )
        );

        // ======================================================
        // ROLE
        // ======================================================
        JLabel lblRole
                = new JLabel(
                        "Dentist",
                        SwingConstants.CENTER
                );

        lblRole.setForeground(
                new Color(
                        220,
                        230,
                        240
                )
        );

        lblRole.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        // ======================================================
        // BUTTONS
        // ======================================================
        btnDashboard
                = createSidebarButton(
                        "Dashboard"
                );

        btnAppointments
                = createSidebarButton(
                        "My Appointments"
                );

        btnSchedule
                = createSidebarButton(
                        "My Schedule"
                );

        btnPatients
                = createSidebarButton(
                        "Patients"
                );

        btnTreatmentRecords
                = createSidebarButton(
                        "Treatment Records"
                );

        btnReports
                = createSidebarButton(
                        "Reports"
                );

        btnProfile
                = createSidebarButton(
                        "Profile"
                );

        btnHelp
                = createSidebarButton(
                        "Help"
                );

        btnLogout
                = createSidebarButton(
                        "Logout"
                );

        // ======================================================
        // ADD TO SIDEBAR
        // ======================================================
        pnlSidebar.add(
                lblClinic
        );

        pnlSidebar.add(
                lblRole
        );

        pnlSidebar.add(
                btnDashboard
        );

        pnlSidebar.add(
                btnAppointments
        );

        pnlSidebar.add(
                btnSchedule
        );

        pnlSidebar.add(
                btnPatients
        );

        pnlSidebar.add(
                btnTreatmentRecords
        );

        pnlSidebar.add(
                btnReports
        );

        pnlSidebar.add(
                btnProfile
        );

        pnlSidebar.add(
                btnHelp
        );

        pnlSidebar.add(
                btnLogout
        );

        // ======================================================
        // ACTION LISTENERS
        // ======================================================
        btnDashboard.addActionListener(
                e -> showDashboard()
        );

        btnAppointments.addActionListener(
                e -> showAppointments()
        );

        btnSchedule.addActionListener(
                e -> showSchedule()
        );

        btnPatients.addActionListener(
                e -> showPatients()
        );

        btnTreatmentRecords.addActionListener(
                e -> showTreatmentRecords()
        );

        btnReports.addActionListener(
                e -> showReports()
        );

        btnProfile.addActionListener(
                e -> showProfile()
        );

        btnHelp.addActionListener(
                e -> showHelp()
        );

        btnLogout.addActionListener(
                e -> logout()
        );
    }

    // ==========================================================
    // SIDEBAR BUTTON STYLE
    // ==========================================================
    private JButton createSidebarButton(
            String text) {

        JButton button
                = new JButton(
                        text
                );

        button.setFocusPainted(
                false
        );

        button.setBackground(
                new Color(
                        42,
                        95,
                        145
                )
        );

        button.setForeground(
                Color.WHITE
        );

        button.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        return button;
    }

    // ==========================================================
    // CONTENT AREA
    // ==========================================================
    private void createContentArea() {

        pnlContent
                = new JPanel(
                        new BorderLayout()
                );

        pnlContent.setBackground(
                new Color(
                        245,
                        247,
                        250
                )
        );

        showDashboard();
    }

    // ==========================================================
    // DASHBOARD
    // ==========================================================
    private void showDashboard() {

        pnlContent.removeAll();

        DashboardPanel dashboardPanel
                = new DashboardPanel(
                        userId,
                        () -> showAppointments(),
                        () -> showPatients(),
                        () -> showTreatmentRecords(),
                        () -> showSchedule()
                );

        pnlContent.add(
                dashboardPanel,
                BorderLayout.CENTER
        );

        pnlContent.revalidate();

        pnlContent.repaint();
    }

    // ==========================================================
    // APPOINTMENTS
    // ==========================================================
    private void showAppointments() {

        pnlContent.removeAll();

        pnlContent.add(
                new MyAppointmentsPanel(
                        userId
                ),
                BorderLayout.CENTER
        );

        refreshContent();
    }

    // ==========================================================
    // SCHEDULE
    // ==========================================================
    private void showSchedule() {

        pnlContent.removeAll();

        /*
        pnlContent.add(
                new DentistSchedulePanel(
                        userId
                ),
                BorderLayout.CENTER
        );
         */
        pnlContent.add(
                createTemporaryPanel(
                        "My Schedule"
                ),
                BorderLayout.CENTER
        );

        refreshContent();
    }

    // ==========================================================
    // PATIENTS
    // ==========================================================
    private void showPatients() {

        pnlContent.removeAll();

        /*
        pnlContent.add(
                new DentistPatientsPanel(
                        userId
                ),
                BorderLayout.CENTER
        );
         */
        pnlContent.add(
                createTemporaryPanel(
                        "Patients"
                ),
                BorderLayout.CENTER
        );

        refreshContent();
    }

    // ==========================================================
    // TREATMENT RECORDS
    // ==========================================================
    private void showTreatmentRecords() {

        pnlContent.removeAll();

        /*
        pnlContent.add(
                new TreatmentRecordsPanel(
                        userId
                ),
                BorderLayout.CENTER
        );
         */
        pnlContent.add(
                createTemporaryPanel(
                        "Treatment Records"
                ),
                BorderLayout.CENTER
        );

        refreshContent();
    }

    // ==========================================================
    // REPORTS
    // ==========================================================
    private void showReports() {

        pnlContent.removeAll();

        /*
        pnlContent.add(
                new DentistReportsPanel(
                        userId
                ),
                BorderLayout.CENTER
        );
         */
        pnlContent.add(
                createTemporaryPanel(
                        "Dentist Reports"
                ),
                BorderLayout.CENTER
        );

        refreshContent();
    }

    // ==========================================================
    // PROFILE
    // ==========================================================
    private void showProfile() {

        pnlContent.removeAll();

        /*
        pnlContent.add(
                new DentistProfilePanel(
                        userId
                ),
                BorderLayout.CENTER
        );
         */
        pnlContent.add(
                createTemporaryPanel(
                        "Dentist Profile"
                ),
                BorderLayout.CENTER
        );

        refreshContent();
    }

    // ==========================================================
    // HELP
    // ==========================================================
    private void showHelp() {

        pnlContent.removeAll();

        /*
        pnlContent.add(
                new DentistHelpPanel(),
                BorderLayout.CENTER
        );
         */
        pnlContent.add(
                createTemporaryPanel(
                        "Dentist Help Center"
                ),
                BorderLayout.CENTER
        );

        refreshContent();
    }

    // ==========================================================
    // REFRESH CONTENT
    // ==========================================================
    private void refreshContent() {

        pnlContent.revalidate();

        pnlContent.repaint();
    }

    // ==========================================================
    // TEMPORARY PANEL
    // Remove this when actual panels are created.
    // ==========================================================
    private JPanel createTemporaryPanel(
            String title) {

        JPanel panel
                = new JPanel(
                        new BorderLayout()
                );

        panel.setBackground(
                new Color(
                        245,
                        247,
                        250
                )
        );

        panel.setBorder(
                BorderFactory.createEmptyBorder(
                        30,
                        30,
                        30,
                        30
                )
        );

        JLabel lblTitle
                = new JLabel(
                        title
                );

        lblTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        24
                )
        );

        panel.add(
                lblTitle,
                BorderLayout.NORTH
        );

        return panel;
    }

    // ==========================================================
    // LOGOUT
    // ==========================================================
    private void logout() {

        int result
                = JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to logout?",
                        "Logout",
                        JOptionPane.YES_NO_OPTION
                );

        if (result
                == JOptionPane.YES_OPTION) {

            new LoginFrame()
                    .setVisible(
                            true
                    );

            dispose();
        }
    }

//    public void setVisible(boolean b) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
}
