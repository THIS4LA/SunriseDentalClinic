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
        // CLINIC TITLE
        // ======================================================

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

        // ======================================================
        // ROLE LABEL
        // ======================================================

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

        // ======================================================
        // SIDEBAR BUTTONS
        // ======================================================

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

        btnLogout =
                createSidebarButton(
                        "Logout"
                );

        // ======================================================
        // ADD COMPONENTS TO SIDEBAR
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
                btnLogout
        );

        // ======================================================
        // ACTION LISTENERS
        // ======================================================

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

        btnLogout.addActionListener(
                e -> logout()
        );
    }

    // ==========================================================
    // SIDEBAR BUTTON STYLE
    // ==========================================================

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
    // USERS
    // ==========================================================

    private void showUsers() {

        pnlContent.removeAll();

        pnlContent.add(
                new UsersPanel(
                        userId
                ),
                BorderLayout.CENTER
        );

        refreshContent();
    }

    // ==========================================================
    // DENTISTS
    // ==========================================================

    private void showDentists() {

        pnlContent.removeAll();

        pnlContent.add(
                new DentistsPanel(),
                BorderLayout.CENTER
        );

        refreshContent();
    }

    // ==========================================================
    // PATIENTS
    // ==========================================================

    private void showPatients() {

        pnlContent.removeAll();

        pnlContent.add(
                new PatientsPanel(),
                BorderLayout.CENTER
        );

        refreshContent();
    }

    // ==========================================================
    // APPOINTMENTS
    // ==========================================================

    private void showAppointments() {

        pnlContent.removeAll();

        pnlContent.add(
                new AppointmentsPanel(),
                BorderLayout.CENTER
        );

        refreshContent();
    }

    // ==========================================================
    // TREATMENTS
    // ==========================================================

    private void showTreatments() {

        pnlContent.removeAll();

        pnlContent.add(
                new TreatmentsPanel(),
                BorderLayout.CENTER
        );

        refreshContent();
    }

    // ==========================================================
    // BILLING
    // ==========================================================

    private void showBilling() {

        pnlContent.removeAll();

        pnlContent.add(
                new BillingPanel(),
                BorderLayout.CENTER
        );

        refreshContent();
    }

    // ==========================================================
    // REPORTS
    // ==========================================================

    private void showReports() {

        pnlContent.removeAll();

        pnlContent.add(
                new ReportsPanel(),
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