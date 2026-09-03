package com.sunrisedental.view.admin;

import com.sunrisedental.model.Patient;
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

    private final PatientService patientService;

    private int selectedPatientId =
            -1;

    private JTextField txtPatientId;
    private JTextField txtName;
    private JTextField txtAddress;
    private JTextField txtContactNumber;
    private JTextField txtEmail;

    private JTextField txtSearch;

    private JButton btnSave;
    private JButton btnUpdate;
    private JButton btnClear;
    private JButton btnSearch;
    private JButton btnRefresh;

    private JTable tblPatients;
    private DefaultTableModel tableModel;

    public PatientsPanel() {

        patientService =
                new PatientService();

        initUI();

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
                createMainContent(),
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
                        "Patient Management"
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
                        "View and manage patient records"
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
    // MAIN CONTENT
    // ==========================================================

    private JPanel createMainContent() {

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
                createPatientForm(),
                BorderLayout.NORTH
        );

        main.add(
                createPatientTable(),
                BorderLayout.CENTER
        );

        return main;
    }

    // ==========================================================
    // PATIENT FORM
    // ==========================================================

    private JPanel createPatientForm() {

        JPanel container =
                new JPanel(
                        new BorderLayout(
                                0,
                                15
                        )
                );

        container.setBackground(
                Color.WHITE
        );

        container.setBorder(
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
                        "Patient Details"
                );

        lblTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        17
                )
        );

        JPanel fields =
                new JPanel(
                        new GridLayout(
                                3,
                                4,
                                15,
                                10
                        )
                );

        fields.setOpaque(
                false
        );

        txtPatientId =
                new JTextField();

        txtPatientId.setEditable(
                false
        );

        txtPatientId.setBackground(
                new Color(
                        245,
                        245,
                        245
                )
        );

        txtName =
                new JTextField();

        txtAddress =
                new JTextField();

        txtContactNumber =
                new JTextField();

        txtEmail =
                new JTextField();

        fields.add(
                new JLabel(
                        "Patient ID"
                )
        );

        fields.add(
                txtPatientId
        );

        fields.add(
                new JLabel(
                        "Patient Name"
                )
        );

        fields.add(
                txtName
        );

        fields.add(
                new JLabel(
                        "Address"
                )
        );

        fields.add(
                txtAddress
        );

        fields.add(
                new JLabel(
                        "Contact Number"
                )
        );

        fields.add(
                txtContactNumber
        );

        fields.add(
                new JLabel(
                        "Email"
                )
        );

        fields.add(
                txtEmail
        );

        fields.add(
                new JLabel()
        );

        fields.add(
                new JLabel()
        );

        JPanel buttons =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                10,
                                0
                        )
                );

        buttons.setOpaque(
                false
        );

        btnSave =
                createPrimaryButton(
                        "Add Patient"
                );

        btnUpdate =
                createSecondaryButton(
                        "Update"
                );

        btnClear =
                createSecondaryButton(
                        "Clear"
                );

        buttons.add(
                btnSave
        );

        buttons.add(
                btnUpdate
        );

        buttons.add(
                btnClear
        );

        container.add(
                lblTitle,
                BorderLayout.NORTH
        );

        container.add(
                fields,
                BorderLayout.CENTER
        );

        container.add(
                buttons,
                BorderLayout.SOUTH
        );

        btnSave.addActionListener(
                e -> savePatient()
        );

        btnUpdate.addActionListener(
                e -> updatePatient()
        );

        btnClear.addActionListener(
                e -> clearForm()
        );

        return container;
    }

    // ==========================================================
    // PATIENT TABLE
    // ==========================================================

    private JPanel createPatientTable() {

        JPanel container =
                new JPanel(
                        new BorderLayout(
                                0,
                                12
                        )
                );

        container.setBackground(
                Color.WHITE
        );

        container.setBorder(
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

        JPanel search =
                new JPanel(
                        new BorderLayout()
                );

        search.setOpaque(
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
                        17
                )
        );

        JPanel searchRight =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                8,
                                0
                        )
                );

        searchRight.setOpaque(
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

        txtSearch.setToolTipText(
                "Search by patient ID, name, contact number or email"
        );

        btnSearch =
                createPrimaryButton(
                        "Search"
                );

        btnRefresh =
                createSecondaryButton(
                        "Refresh"
                );

        searchRight.add(
                txtSearch
        );

        searchRight.add(
                btnSearch
        );

        searchRight.add(
                btnRefresh
        );

        search.add(
                lblTitle,
                BorderLayout.WEST
        );

        search.add(
                searchRight,
                BorderLayout.EAST
        );

        tableModel =
                new DefaultTableModel(
                        new Object[]{
                            "Patient ID",
                            "Name",
                            "Address",
                            "Contact Number",
                            "Email"
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

        JScrollPane scrollPane =
                new JScrollPane(
                        tblPatients
                );

        container.add(
                search,
                BorderLayout.NORTH
        );

        container.add(
                scrollPane,
                BorderLayout.CENTER
        );

        btnSearch.addActionListener(
                e -> searchPatients()
        );

        txtSearch.addActionListener(
                e -> searchPatients()
        );

        btnRefresh.addActionListener(
                e -> {

                    txtSearch.setText(
                            ""
                    );

                    loadPatients();
                }
        );

        tblPatients
                .getSelectionModel()
                .addListSelectionListener(
                        e -> {

                            if (!e.getValueIsAdjusting()) {

                                loadSelectedPatient();
                            }
                        }
                );

        return container;
    }

    // ==========================================================
    // SAVE PATIENT
    // ==========================================================

    private void savePatient() {

        try {

            Patient patient =
                    new Patient(
                            txtName
                                    .getText()
                                    .trim(),

                            txtAddress
                                    .getText()
                                    .trim(),

                            txtContactNumber
                                    .getText()
                                    .trim(),

                            txtEmail
                                    .getText()
                                    .trim()
                    );

            boolean success =
                    patientService
                            .addPatient(
                                    patient
                            );

            if (success) {

                JOptionPane.showMessageDialog(
                        this,
                        "Patient added successfully.",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );

                clearForm();

                loadPatients();
            }

        } catch (IllegalArgumentException e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }

    // ==========================================================
    // UPDATE PATIENT
    // ==========================================================

    private void updatePatient() {

        try {

            if (selectedPatientId <= 0) {

                throw new IllegalArgumentException(
                        "Please select a patient first."
                );
            }

            Patient patient =
                    new Patient(
                            selectedPatientId,

                            txtName
                                    .getText()
                                    .trim(),

                            txtAddress
                                    .getText()
                                    .trim(),

                            txtContactNumber
                                    .getText()
                                    .trim(),

                            txtEmail
                                    .getText()
                                    .trim()
                    );

            boolean success =
                    patientService
                            .updatePatient(
                                    patient
                            );

            if (success) {

                JOptionPane.showMessageDialog(
                        this,
                        "Patient updated successfully.",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );

                clearForm();

                loadPatients();
            }

        } catch (IllegalArgumentException e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }

    // ==========================================================
    // LOAD PATIENTS
    // ==========================================================

    private void loadPatients() {

        List<Patient> patients =
                patientService
                        .getAllPatients();

        populateTable(
                patients
        );
    }

    // ==========================================================
    // SEARCH PATIENTS
    // ==========================================================

    private void searchPatients() {

        String keyword =
                txtSearch
                        .getText()
                        .trim();

        List<Patient> patients =
                patientService
                        .searchPatients(
                                keyword
                        );

        populateTable(
                patients
        );
    }

    // ==========================================================
    // POPULATE TABLE
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
                        patient.getAddress(),
                        patient.getContactNumber(),
                        patient.getEmail()
                    }
            );
        }
    }

    // ==========================================================
    // LOAD SELECTED PATIENT
    // ==========================================================

    private void loadSelectedPatient() {

        int row =
                tblPatients
                        .getSelectedRow();

        if (row == -1) {

            return;
        }

        selectedPatientId =
                Integer.parseInt(
                        tableModel
                                .getValueAt(
                                        row,
                                        0
                                )
                                .toString()
                );

        txtPatientId.setText(
                String.valueOf(
                        selectedPatientId
                )
        );

        txtName.setText(
                safeValue(
                        tableModel
                                .getValueAt(
                                        row,
                                        1
                                )
                )
        );

        txtAddress.setText(
                safeValue(
                        tableModel
                                .getValueAt(
                                        row,
                                        2
                                )
                )
        );

        txtContactNumber.setText(
                safeValue(
                        tableModel
                                .getValueAt(
                                        row,
                                        3
                                )
                )
        );

        txtEmail.setText(
                safeValue(
                        tableModel
                                .getValueAt(
                                        row,
                                        4
                                )
                )
        );
    }

    // ==========================================================
    // CLEAR FORM
    // ==========================================================

    private void clearForm() {

        selectedPatientId =
                -1;

        txtPatientId.setText(
                ""
        );

        txtName.setText(
                ""
        );

        txtAddress.setText(
                ""
        );

        txtContactNumber.setText(
                ""
        );

        txtEmail.setText(
                ""
        );

        tblPatients.clearSelection();

        txtName.requestFocus();
    }

    // ==========================================================
    // SAFE VALUE
    // ==========================================================

    private String safeValue(
            Object value) {

        return value == null
                ? ""
                : value.toString();
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

        button.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        button.setPreferredSize(
                new Dimension(
                        145,
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

        button.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        button.setPreferredSize(
                new Dimension(
                        145,
                        34
                )
        );

        return button;
    }
}