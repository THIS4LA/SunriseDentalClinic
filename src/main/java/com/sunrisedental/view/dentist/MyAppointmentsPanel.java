package com.sunrisedental.view.dentist;

import com.sunrisedental.model.Appointment;
import com.sunrisedental.service.AppointmentService;
import com.sunrisedental.service.DentistService;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class MyAppointmentsPanel
        extends JPanel {

    private final int userId;

    private int dentistId;

    private final AppointmentService appointmentService;
    private final DentistService dentistService;

    private JTextField txtSearch;

    private JButton btnSearch;
    private JButton btnRefresh;

    private JTable tblAppointments;

    private DefaultTableModel tableModel;

    public MyAppointmentsPanel(
            int userId) {

        this.userId =
                userId;

        appointmentService =
                new AppointmentService();

        dentistService =
                new DentistService();

        initUI();

        loadDentist();

        loadAppointments();
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

        setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        25,
                        20,
                        25
                )
        );

        add(
                createHeader(),
                BorderLayout.NORTH
        );

        add(
                createMainPanel(),
                BorderLayout.CENTER
        );
    }

    private JPanel createHeader() {

        JPanel panel =
                new JPanel(
                        new GridLayout(
                                2,
                                1,
                                0,
                                3
                        )
                );

        panel.setOpaque(
                false
        );

        JLabel lblTitle =
                new JLabel(
                        "My Appointments"
                );

        lblTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        24
                )
        );

        JLabel lblDescription =
                new JLabel(
                        "View appointments assigned to you"
                );

        lblDescription.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        lblDescription.setForeground(
                new Color(
                        100,
                        100,
                        100
                )
        );

        panel.add(
                lblTitle
        );

        panel.add(
                lblDescription
        );

        return panel;
    }

    private JPanel createMainPanel() {

        JPanel main =
                new JPanel(
                        new BorderLayout(
                                0,
                                15
                        )
                );

        main.setOpaque(
                false
        );

        main.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        0,
                        0,
                        0
                )
        );

        main.add(
                createFilterPanel(),
                BorderLayout.NORTH
        );

        main.add(
                createTablePanel(),
                BorderLayout.CENTER
        );

        return main;
    }

    private JPanel createFilterPanel() {

        JPanel panel =
                new JPanel(
                        new BorderLayout(
                                10,
                                0
                        )
                );

        panel.setBackground(
                Color.WHITE
        );

        panel.setBorder(
                BorderFactory.createCompoundBorder(

                        BorderFactory.createLineBorder(
                                new Color(
                                        220,
                                        225,
                                        230
                                )
                        ),

                        BorderFactory.createEmptyBorder(
                                15,
                                15,
                                15,
                                15
                        )
                )
        );

        JLabel lblTitle =
                new JLabel(
                        "Appointment Search"
                );

        lblTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        16
                )
        );

        JPanel pnlSearch =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                8,
                                0
                        )
                );

        pnlSearch.setOpaque(
                false
        );

        txtSearch =
                new JTextField();

        txtSearch.setPreferredSize(
                new Dimension(
                        220,
                        32
                )
        );

        btnSearch =
                createPrimaryButton(
                        "Search"
                );

        btnRefresh =
                createSecondaryButton(
                        "Refresh"
                );

        pnlSearch.add(
                txtSearch
        );

        pnlSearch.add(
                btnSearch
        );

        pnlSearch.add(
                btnRefresh
        );

        panel.add(
                lblTitle,
                BorderLayout.WEST
        );

        panel.add(
                pnlSearch,
                BorderLayout.EAST
        );

        btnSearch.addActionListener(
                e -> searchAppointments()
        );

        txtSearch.addActionListener(
                e -> searchAppointments()
        );

        btnRefresh.addActionListener(
                e -> {

                    txtSearch.setText("");

                    loadAppointments();
                }
        );

        return panel;
    }

    private JPanel createTablePanel() {

        JPanel panel =
                new JPanel(
                        new BorderLayout(
                                0,
                                10
                        )
                );

        panel.setBackground(
                Color.WHITE
        );

        panel.setBorder(
                BorderFactory.createCompoundBorder(

                        BorderFactory.createLineBorder(
                                new Color(
                                        220,
                                        225,
                                        230
                                )
                        ),

                        BorderFactory.createEmptyBorder(
                                15,
                                15,
                                15,
                                15
                        )
                )
        );

        JLabel lblTitle =
                new JLabel(
                        "Assigned Appointments"
                );

        lblTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        17
                )
        );

        tableModel =
                new DefaultTableModel(
                        new Object[]{
                            "Appointment No",
                            "Patient",
                            "Treatment",
                            "Date",
                            "Time",
                            "Status"
                        },
                        0
                ) {

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column) {

                        return false;
                    }
                };

        tblAppointments =
                new JTable(
                        tableModel
                );

        tblAppointments.setRowHeight(
                28
        );

        tblAppointments.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        tblAppointments
                .getTableHeader()
                .setFont(
                        new Font(
                                "Segoe UI",
                                Font.BOLD,
                                13
                        )
                );

        panel.add(
                lblTitle,
                BorderLayout.NORTH
        );

        panel.add(
                new JScrollPane(
                        tblAppointments
                ),
                BorderLayout.CENTER
        );

        return panel;
    }

    private JButton createPrimaryButton(
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
                        31,
                        78,
                        121
                )
        );

        button.setForeground(
                Color.WHITE
        );

        button.setPreferredSize(
                new Dimension(
                        110,
                        34
                )
        );

        return button;
    }

    private JButton createSecondaryButton(
            String text) {

        JButton button =
                new JButton(
                        text
                );

        button.setFocusPainted(
                false
        );

        button.setPreferredSize(
                new Dimension(
                        110,
                        34
                )
        );

        return button;
    }

    private void loadDentist() {

        try {

            dentistId =
                    dentistService
                            .getDentistIdByUserId(
                                    userId
                            );

        } catch (IllegalArgumentException e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Dentist Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void loadAppointments() {

        if (dentistId <= 0) {
            return;
        }

        List<Appointment> appointments =
                appointmentService
                        .getAppointmentsForDentist(
                                dentistId
                        );

        populateTable(
                appointments
        );
    }

    private void searchAppointments() {

        if (dentistId <= 0) {
            return;
        }

        String keyword =
                txtSearch
                        .getText()
                        .trim();

        List<Appointment> appointments =
                appointmentService
                        .searchAppointmentsForDentist(
                                dentistId,
                                keyword
                        );

        populateTable(
                appointments
        );
    }

    private void populateTable(
            List<Appointment> appointments) {

        tableModel.setRowCount(
                0
        );

        for (Appointment appointment
                : appointments) {

            tableModel.addRow(
                    new Object[]{
                        appointment.getAppointmentNo(),
                        appointment.getPatientId(),
                        appointment.getTreatmentType(),
                        appointment.getAppointmentDate(),
                        appointment.getAppointmentTime(),
                        appointment.getStatus()
                    }
            );
        }
    }
}