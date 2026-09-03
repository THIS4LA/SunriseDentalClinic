package com.sunrisedental.view.admin;

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

public class AdminDashboardFrame
        extends JFrame {

    private JPanel pnlSidebar;
    private JPanel pnlContent;

    private JButton btnDashboard;
    private JButton btnUsers;
    private JButton btnDentists;
    private JButton btnPatients;
    private JButton btnAppointments;
    private JButton btnTreatments;
    private JButton btnBilling;
    private JButton btnReports;
    private JButton btnAuditLogs;
    private JButton btnHelp;
    private JButton btnLogout;

    private final int userId;

    public AdminDashboardFrame(
            int userId) {

        this.userId =
                userId;

        initUI();

        setTitle(
                "Sunrise Dental Clinic - Admin Dashboard"
        );

        setSize(
                1100,
                650
        );

        setLocationRelativeTo(
                null
        );

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

        pnlSidebar =
                new JPanel();

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
                        13,
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

        JLabel lblClinic =
                new JLabel(
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

        JLabel lblRole =
                new JLabel(
                        "Administrator",
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

        btnDashboard =
                createSidebarButton(
                        "Dashboard"
                );

        btnUsers =
                createSidebarButton(
                        "Users"
                );

        btnDentists =
                createSidebarButton(
                        "Dentists"
                );

        btnPatients =
                createSidebarButton(
                        "Patients"
                );

        btnAppointments =
                createSidebarButton(
                        "Appointments"
                );

        btnTreatments =
                createSidebarButton(
                        "Treatments"
                );

        btnBilling =
                createSidebarButton(
                        "Billing"
                );

        btnReports =
                createSidebarButton(
                        "Reports"
                );

        btnAuditLogs =
                createSidebarButton(
                        "Audit Logs"
                );

        btnHelp =
                createSidebarButton(
                        "Help"
                );

        btnLogout =
                createSidebarButton(
                        "Logout"
                );

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
                btnUsers
        );

        pnlSidebar.add(
                btnDentists
        );

        pnlSidebar.add(
                btnPatients
        );

        pnlSidebar.add(
                btnAppointments
        );

        pnlSidebar.add(
                btnTreatments
        );

        pnlSidebar.add(
                btnBilling
        );

        pnlSidebar.add(
                btnReports
        );

        pnlSidebar.add(
                btnAuditLogs
        );

        pnlSidebar.add(
                btnHelp
        );

        pnlSidebar.add(
                btnLogout
        );

        btnDashboard.addActionListener(
                e -> showDashboard()
        );

        btnUsers.addActionListener(
                e -> showUsers()
        );

        btnDentists.addActionListener(
                e -> showDentists()
        );

        btnPatients.addActionListener(
                e -> showPatients()
        );

        btnAppointments.addActionListener(
                e -> showAppointments()
        );

        btnTreatments.addActionListener(
                e -> showTreatments()
        );

        btnBilling.addActionListener(
                e -> showBilling()
        );

        btnReports.addActionListener(
                e -> showReports()
        );

        btnAuditLogs.addActionListener(
                e -> showAuditLogs()
        );

        btnHelp.addActionListener(
                e -> showHelp()
        );

        btnLogout.addActionListener(
                e -> logout()
        );
    }

    private JButton createSidebarButton(
            String text) {

        JButton button =
                new JButton(
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

        pnlContent =
                new JPanel(
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

        DashboardPanel dashboardPanel =
                new DashboardPanel(
                        userId,
                        () -> showUsers(),
                        () -> showDentists(),
                        () -> showTreatments(),
                        () -> showReports()
                );

        pnlContent.add(
                dashboardPanel,
                BorderLayout.CENTER
        );

        refreshContent();
    }

    // ==========================================================
    // TEMPORARY SECTIONS
    // ==========================================================

    private void showUsers() {

        showTemporaryPanel(
                "User Management"
        );
    }

    private void showDentists() {

        showTemporaryPanel(
                "Dentist Management"
        );
    }

    private void showPatients() {

        showTemporaryPanel(
                "Patient Management"
        );
    }

    private void showAppointments() {

        showTemporaryPanel(
                "Appointment Management"
        );
    }

    private void showTreatments() {

        showTemporaryPanel(
                "Treatment Management"
        );
    }

    private void showBilling() {

        showTemporaryPanel(
                "Billing Management"
        );
    }

    private void showReports() {

        showTemporaryPanel(
                "Admin Reports"
        );
    }

    private void showAuditLogs() {

        showTemporaryPanel(
                "Audit Logs"
        );
    }

    private void showHelp() {

        showTemporaryPanel(
                "Admin Help Center"
        );
    }

    private void showTemporaryPanel(
            String title) {

        pnlContent.removeAll();

        pnlContent.add(
                createTemporaryPanel(
                        title
                ),
                BorderLayout.CENTER
        );

        refreshContent();
    }

    private JPanel createTemporaryPanel(
            String title) {

        JPanel panel =
                new JPanel(
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

        JLabel lblTitle =
                new JLabel(
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

    private void refreshContent() {

        pnlContent.revalidate();

        pnlContent.repaint();
    }

    // ==========================================================
    // LOGOUT
    // ==========================================================

    private void logout() {

        int result =
                JOptionPane.showConfirmDialog(
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
}