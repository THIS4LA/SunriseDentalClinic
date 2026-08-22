package com.sunrisedental.view.receptionist;

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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class PatientPanel extends JPanel {

    private final PatientService patientService;

    private JTextField txtPatientId;
    private JTextField txtName;
    private JTextField txtContact;
    private JTextField txtEmail;
    private JTextArea txtAddress;
    private JTextField txtSearch;

    private JButton btnSave;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JButton btnClear;
    private JButton btnSearch;

    private JTable tblPatients;
    private DefaultTableModel tableModel;

    public PatientPanel() {

        patientService = new PatientService();

        initUI();

        loadPatients();
    }

    private void initUI() {

        setLayout(new BorderLayout());

        setBackground(
                new Color(245, 247, 250)
        );

        setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        25,
                        20,
                        25
                )
        );

        createHeader();

        createMainContent();
    }

    private void createHeader() {

        JPanel pnlHeader =
                new JPanel(
                        new BorderLayout()
                );

        pnlHeader.setOpaque(false);

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
                        "Register, search and manage patient information"
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

        JPanel pnlTitle =
                new JPanel();

        pnlTitle.setOpaque(false);

        pnlTitle.setLayout(
                new GridLayout(
                        2,
                        1,
                        0,
                        3
                )
        );

        pnlTitle.add(lblTitle);
        pnlTitle.add(lblDescription);

        pnlHeader.add(
                pnlTitle,
                BorderLayout.WEST
        );

        add(
                pnlHeader,
                BorderLayout.NORTH
        );
    }

    private void createMainContent() {

        JPanel pnlMain =
                new JPanel(
                        new BorderLayout(
                                0,
                                20
                        )
                );

        pnlMain.setOpaque(false);

        pnlMain.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        0,
                        0,
                        0
                )
        );

        pnlMain.add(
                createFormPanel(),
                BorderLayout.NORTH
        );

        pnlMain.add(
                createTablePanel(),
                BorderLayout.CENTER
        );

        add(
                pnlMain,
                BorderLayout.CENTER
        );
    }

    private JPanel createFormPanel() {

        JPanel pnlFormContainer =
                new JPanel(
                        new BorderLayout()
                );

        pnlFormContainer.setBackground(
                Color.WHITE
        );

        pnlFormContainer.setBorder(
                BorderFactory.createCompoundBorder(

                        BorderFactory.createLineBorder(
                                new Color(
                                        220,
                                        225,
                                        230
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

        JLabel lblFormTitle =
                new JLabel(
                        "Patient Details"
                );

        lblFormTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        17
                )
        );

        pnlFormContainer.add(
                lblFormTitle,
                BorderLayout.NORTH
        );

        JPanel pnlFields =
                new JPanel(
                        new GridLayout(
                                3,
                                4,
                                15,
                                12
                        )
                );

        pnlFields.setOpaque(false);

        pnlFields.setBorder(
                BorderFactory.createEmptyBorder(
                        15,
                        0,
                        15,
                        0
                )
        );

        JLabel lblPatientId =
                new JLabel(
                        "Patient ID"
                );

        txtPatientId =
                new JTextField();

        txtPatientId.setEditable(false);

        JLabel lblName =
                new JLabel(
                        "Patient Name"
                );

        txtName =
                new JTextField();

        JLabel lblContact =
                new JLabel(
                        "Contact Number"
                );

        txtContact =
                new JTextField();

        JLabel lblEmail =
                new JLabel(
                        "Email"
                );

        txtEmail =
                new JTextField();

        JLabel lblAddress =
                new JLabel(
                        "Address"
                );

        txtAddress =
                new JTextArea(
                        2,
                        20
                );

        txtAddress.setLineWrap(true);
        txtAddress.setWrapStyleWord(true);

        JScrollPane addressScroll =
                new JScrollPane(
                        txtAddress
                );

        pnlFields.add(lblPatientId);
        pnlFields.add(txtPatientId);

        pnlFields.add(lblName);
        pnlFields.add(txtName);

        pnlFields.add(lblContact);
        pnlFields.add(txtContact);

        pnlFields.add(lblEmail);
        pnlFields.add(txtEmail);

        pnlFields.add(lblAddress);
        pnlFields.add(addressScroll);

        pnlFields.add(
                new JLabel()
        );

        pnlFields.add(
                new JLabel()
        );

        pnlFormContainer.add(
                pnlFields,
                BorderLayout.CENTER
        );

        JPanel pnlButtons =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                10,
                                0
                        )
                );

        pnlButtons.setOpaque(false);

        btnSave =
                createPrimaryButton(
                        "Save"
                );

        btnUpdate =
                createSecondaryButton(
                        "Update"
                );

        btnDelete =
                createSecondaryButton(
                        "Delete"
                );

        btnClear =
                createSecondaryButton(
                        "Clear"
                );

        pnlButtons.add(btnSave);
        pnlButtons.add(btnUpdate);
        pnlButtons.add(btnDelete);
        pnlButtons.add(btnClear);

        pnlFormContainer.add(
                pnlButtons,
                BorderLayout.SOUTH
        );

        btnSave.addActionListener(
                e -> savePatient()
        );

        btnUpdate.addActionListener(
                e -> updatePatient()
        );

        btnDelete.addActionListener(
                e -> deletePatient()
        );

        btnClear.addActionListener(
                e -> clearForm()
        );

        return pnlFormContainer;
    }

    private JPanel createTablePanel() {

        JPanel pnlTableContainer =
                new JPanel(
                        new BorderLayout(
                                0,
                                12
                        )
                );

        pnlTableContainer.setBackground(
                Color.WHITE
        );

        pnlTableContainer.setBorder(
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

        JPanel pnlSearch =
                new JPanel(
                        new BorderLayout(
                                10,
                                0
                        )
                );

        pnlSearch.setOpaque(false);

        JLabel lblListTitle =
                new JLabel(
                        "Patients"
                );

        lblListTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        17
                )
        );

        JPanel pnlSearchRight =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                8,
                                0
                        )
                );

        pnlSearchRight.setOpaque(false);

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

        pnlSearchRight.add(
                txtSearch
        );

        pnlSearchRight.add(
                btnSearch
        );

        pnlSearch.add(
                lblListTitle,
                BorderLayout.WEST
        );

        pnlSearch.add(
                pnlSearchRight,
                BorderLayout.EAST
        );

        pnlTableContainer.add(
                pnlSearch,
                BorderLayout.NORTH
        );

        tableModel =
                new DefaultTableModel(
                        new Object[]{
                            "ID",
                            "Name",
                            "Contact",
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

        tblPatients.setRowHeight(28);

        tblPatients.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        tblPatients.getTableHeader()
                .setFont(
                        new Font(
                                "Segoe UI",
                                Font.BOLD,
                                13
                        )
                );

        JScrollPane tableScroll =
                new JScrollPane(
                        tblPatients
                );

        pnlTableContainer.add(
                tableScroll,
                BorderLayout.CENTER
        );

        btnSearch.addActionListener(
                e -> searchPatients()
        );

        txtSearch.addActionListener(
                e -> searchPatients()
        );

        tblPatients.getSelectionModel()
                .addListSelectionListener(
                        e -> {

                            if (!e.getValueIsAdjusting()) {
                                loadSelectedPatient();
                            }
                        }
                );

        return pnlTableContainer;
    }

    private JButton createPrimaryButton(
            String text) {

        JButton button =
                new JButton(text);

        button.setFocusPainted(false);

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
                        110,
                        34
                )
        );

        return button;
    }

    private JButton createSecondaryButton(
            String text) {

        JButton button =
                new JButton(text);

        button.setFocusPainted(false);

        button.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        button.setPreferredSize(
                new Dimension(
                        110,
                        34
                )
        );

        return button;
    }

    private void savePatient() {

        try {

            Patient patient =
                    new Patient(
                            txtName.getText().trim(),
                            txtAddress.getText().trim(),
                            txtContact.getText().trim(),
                            txtEmail.getText().trim()
                    );

            boolean success =
                    patientService.addPatient(
                            patient
                    );

            if (success) {

                JOptionPane.showMessageDialog(
                        this,
                        "Patient registered successfully.",
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

    private void updatePatient() {

        if (txtPatientId
                .getText()
                .trim()
                .isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a patient first.",
                    "Patient",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        try {

            int patientId =
                    Integer.parseInt(
                            txtPatientId.getText()
                    );

            Patient patient =
                    new Patient(
                            patientId,
                            txtName.getText().trim(),
                            txtAddress.getText().trim(),
                            txtContact.getText().trim(),
                            txtEmail.getText().trim()
                    );

            boolean success =
                    patientService.updatePatient(
                            patient
                    );

            if (success) {

                JOptionPane.showMessageDialog(
                        this,
                        "Patient updated successfully."
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

    private void deletePatient() {

        if (txtPatientId
                .getText()
                .trim()
                .isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a patient first."
            );

            return;
        }

        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to delete this patient?",
                        "Delete Patient",
                        JOptionPane.YES_NO_OPTION
                );

        if (result
                != JOptionPane.YES_OPTION) {

            return;
        }

        try {

            int patientId =
                    Integer.parseInt(
                            txtPatientId.getText()
                    );

            boolean success =
                    patientService.deletePatient(
                            patientId
                    );

            if (success) {

                JOptionPane.showMessageDialog(
                        this,
                        "Patient deleted successfully."
                );

                clearForm();

                loadPatients();
            }

        } catch (IllegalArgumentException e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage()
            );
        }
    }

    private void searchPatients() {

        String keyword =
                txtSearch
                        .getText()
                        .trim();

        List<Patient> patients =
                patientService.searchPatients(
                        keyword
                );

        populateTable(
                patients
        );
    }

    private void loadPatients() {

        List<Patient> patients =
                patientService.getAllPatients();

        populateTable(
                patients
        );
    }

    private void populateTable(
            List<Patient> patients) {

        tableModel.setRowCount(0);

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

    private void loadSelectedPatient() {

        int row =
                tblPatients.getSelectedRow();

        if (row == -1) {
            return;
        }

        txtPatientId.setText(
                tableModel
                        .getValueAt(
                                row,
                                0
                        )
                        .toString()
        );

        txtName.setText(
                tableModel
                        .getValueAt(
                                row,
                                1
                        )
                        .toString()
        );

        txtContact.setText(
                safeValue(
                        tableModel.getValueAt(
                                row,
                                2
                        )
                )
        );

        txtEmail.setText(
                safeValue(
                        tableModel.getValueAt(
                                row,
                                3
                        )
                )
        );

        txtAddress.setText(
                safeValue(
                        tableModel.getValueAt(
                                row,
                                4
                        )
                )
        );
    }

    private String safeValue(
            Object value) {

        return value == null
                ? ""
                : value.toString();
    }

    private void clearForm() {

        txtPatientId.setText("");
        txtName.setText("");
        txtAddress.setText("");
        txtContact.setText("");
        txtEmail.setText("");

        tblPatients.clearSelection();

        txtName.requestFocus();
    }
}