package com.sunrisedental.view;

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

public class ReceptionistDashboardFrame extends JFrame {

    private JPanel pnlSidebar;
    private JPanel pnlContent;

    private JButton btnDashboard;
    private JButton btnPatients;
    private JButton btnAppointments;
    private JButton btnBilling;
    private JButton btnReports;
    private JButton btnHelp;
    private JButton btnLogout;

    public ReceptionistDashboardFrame() {

        initUI();

        setTitle(
                "Sunrise Dental Clinic - Receptionist Dashboard"
        );

        setSize(1100, 650);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );
    }

    private void initUI() {

        setLayout(new BorderLayout());

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

    private void createSidebar() {

        pnlSidebar = new JPanel();

        pnlSidebar.setPreferredSize(
                new Dimension(230, 0)
        );

        pnlSidebar.setBackground(
                new Color(31, 78, 121)
        );

        pnlSidebar.setLayout(
                new GridLayout(
                        10,
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

        lblClinic.setForeground(Color.WHITE);

        lblClinic.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        18
                )
        );

        JLabel lblRole =
                new JLabel(
                        "Receptionist",
                        SwingConstants.CENTER
                );

        lblRole.setForeground(
                new Color(
                        220,
                        230,
                        240
                )
        );

        btnDashboard =
                createSidebarButton(
                        "Dashboard"
                );

        btnPatients =
                createSidebarButton(
                        "Patients"
                );

        btnAppointments =
                createSidebarButton(
                        "Appointments"
                );

        btnBilling =
                createSidebarButton(
                        "Billing"
                );

        btnReports =
                createSidebarButton(
                        "Reports"
                );

        btnHelp =
                createSidebarButton(
                        "Help"
                );

        btnLogout =
                createSidebarButton(
                        "Logout"
                );

        pnlSidebar.add(lblClinic);
        pnlSidebar.add(lblRole);

        pnlSidebar.add(btnDashboard);
        pnlSidebar.add(btnPatients);
        pnlSidebar.add(btnAppointments);
        pnlSidebar.add(btnBilling);
        pnlSidebar.add(btnReports);
        pnlSidebar.add(btnHelp);
        pnlSidebar.add(btnLogout);

        btnLogout.addActionListener(
                e -> logout()
        );
    }

    private JButton createSidebarButton(
            String text) {

        JButton button =
                new JButton(text);

        button.setFocusPainted(false);

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

    private void createContentArea() {

        pnlContent = new JPanel();

        pnlContent.setBackground(
                new Color(
                        245,
                        247,
                        250
                )
        );

        pnlContent.setLayout(
                new BorderLayout()
        );

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

        pnlContent.add(
                pnlHeader,
                BorderLayout.NORTH
        );

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

        pnlContent.add(
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
                new JLabel(title);

        lblTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        15
                )
        );

        JLabel lblValue =
                new JLabel(value);

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

    private void logout() {

        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to logout?",
                        "Logout",
                        JOptionPane.YES_NO_OPTION
                );

        if (result ==
                JOptionPane.YES_OPTION) {

            new LoginFrame()
                    .setVisible(true);

            dispose();
        }
    }

    public static void main(
            String[] args) {

        java.awt.EventQueue.invokeLater(
                () -> {

                    new ReceptionistDashboardFrame()
                            .setVisible(true);
                }
        );
    }
}