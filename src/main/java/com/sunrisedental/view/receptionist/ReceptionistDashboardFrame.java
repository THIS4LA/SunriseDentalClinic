package com.sunrisedental.view.receptionist;

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

        JLabel lblClinic
                = new JLabel(
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

        JLabel lblRole
                = new JLabel(
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

        btnDashboard
                = createSidebarButton(
                        "Dashboard"
                );

        btnPatients
                = createSidebarButton(
                        "Patients"
                );

        btnAppointments
                = createSidebarButton(
                        "Appointments"
                );

        btnBilling
                = createSidebarButton(
                        "Billing"
                );

        btnReports
                = createSidebarButton(
                        "Reports"
                );

        btnHelp
                = createSidebarButton(
                        "Help"
                );

        btnLogout
                = createSidebarButton(
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

        //action listners
        btnDashboard.addActionListener(
                e -> showDashboard()
        );
        btnPatients.addActionListener(
                e -> showPatients()
        );
        btnAppointments.addActionListener(
                e -> showAppointments()
        );
        btnBilling.addActionListener(
                e -> showBilling()
        );
        btnReports.addActionListener(
                e -> showReports()
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

        JButton button
                = new JButton(text);

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

    private JPanel createCard(
            String title,
            String value) {

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
                = new JLabel(title);

        lblTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        15
                )
        );

        JLabel lblValue
                = new JLabel(value);

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

    //methods
    private void showDashboard() {

        pnlContent.removeAll();

        DashboardPanel dashboardPanel
                = new DashboardPanel(
                        () -> showPatients(),
                        () -> showAppointments(),
                        () -> showBilling()
                );

        pnlContent.add(
                dashboardPanel,
                BorderLayout.CENTER
        );

        pnlContent.revalidate();

        pnlContent.repaint();
    }

    private void showPatients() {

        pnlContent.removeAll();

        pnlContent.add(
                new PatientPanel(),
                BorderLayout.CENTER
        );

        pnlContent.revalidate();
        pnlContent.repaint();
    }

    private void showAppointments() {

        pnlContent.removeAll();

        pnlContent.add(
                new AppointmentPanel(),
                BorderLayout.CENTER
        );

        pnlContent.revalidate();
        pnlContent.repaint();
    }

    private void showBilling() {

        pnlContent.removeAll();

        pnlContent.add(
                new BillingPanel(),
                BorderLayout.CENTER
        );

        pnlContent.revalidate();
        pnlContent.repaint();
    }

    private void showReports() {

        pnlContent.removeAll();

        pnlContent.add(
                new ReportsPanel(),
                BorderLayout.CENTER
        );

        pnlContent.revalidate();
        pnlContent.repaint();
    }

    private void showHelp() {

        pnlContent.removeAll();

        pnlContent.add(
                new HelpPanel(),
                BorderLayout.CENTER
        );

        pnlContent.revalidate();

        pnlContent.repaint();
    }

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
