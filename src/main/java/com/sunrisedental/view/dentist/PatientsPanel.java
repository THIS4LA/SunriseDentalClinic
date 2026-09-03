package com.sunrisedental.view.dentist;

import com.sunrisedental.model.Patient;

import com.sunrisedental.service.DentistService;
import com.sunrisedental.service.PatientService;

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

public class PatientsPanel
        extends JPanel {

    private final int userId;

    private int dentistId;

    private final DentistService dentistService;
    private final PatientService patientService;

    private JTextField txtSearch;

    private JButton btnSearch;
    private JButton btnRefresh;

    private JTable tblPatients;

    private DefaultTableModel tableModel;

    public PatientsPanel(
            int userId) {

        this.userId =
                userId;

        dentistService =
                new DentistService();

        patientService =
                new PatientService();

        initUI();

        loadDentist();

        loadPatients();
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

    // ==========================================================
    // HEADER
    // ==========================================================

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
                        "Patients"
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
                        "View patients assigned to your appointments"
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

    // ==========================================================
    // MAIN PANEL
    // ==========================================================

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

    // ==========================================================
    // SEARCH PANEL
    // ==========================================================

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
                        "Patient Search"
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
                e -> searchPatients()
        );

        txtSearch.addActionListener(
                e -> searchPatients()
        );

        btnRefresh.addActionListener(
                e -> {

                    txtSearch.setText("");

                    loadPatients();
                }
        );

        return panel;
    }

    // ==========================================================
    // TABLE
    // ==========================================================

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
                        "My Patients"
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
                            "Patient ID",
                            "Name",
                            "Contact Number",
                            "Email",
                            "Address"
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

        tblPatients =
                new JTable(
                        tableModel
                );

        tblPatients.setRowHeight(
                28
        );

        tblPatients.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        tblPatients
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
                        tblPatients
                ),
                BorderLayout.CENTER
        );

        return panel;
    }

    // ==========================================================
    // BUTTON STYLES
    // ==========================================================

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

    // ==========================================================
    // RESOLVE DENTIST
    // ==========================================================

    private void loadDentist() {

        try {

            dentistId =
                    dentistService
                            .getDentistIdByUserId(
                                    userId
                            );

        } catch (IllegalArgumentException e) {

            dentistId = -1;

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Dentist Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // ==========================================================
    // LOAD PATIENTS
    // ==========================================================

    private void loadPatients() {

        if (dentistId <= 0) {
            return;
        }

        List<Patient> patients =
                patientService
                        .getPatientsForDentist(
                                dentistId
                        );

        populateTable(
                patients
        );
    }

    // ==========================================================
    // SEARCH
    // ==========================================================

    private void searchPatients() {

        if (dentistId <= 0) {
            return;
        }

        String keyword =
                txtSearch
                        .getText()
                        .trim();

        List<Patient> patients =
                patientService
                        .searchPatientsForDentist(
                                dentistId,
                                keyword
                        );

        populateTable(
                patients
        );
    }

    // ==========================================================
    // TABLE DATA
    // ==========================================================

    private void populateTable(
            List<Patient> patients) {

        tableModel.setRowCount(
                0
        );

        for (Patient patient : patients) {

            tableModel.addRow(
                    new Object[]{
                        patient.getPatientId(),
                        patient.getName(),
                        patient.getContactNumber(),
                        patient.getEmail(),
                        patient.getAddress()
                    }
            );
        }
    }
}