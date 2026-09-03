package com.sunrisedental.view.dentist;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import com.sunrisedental.model.Appointment;
import com.sunrisedental.model.Patient;
import com.sunrisedental.model.TreatmentRecord;

import com.sunrisedental.service.AppointmentService;
import com.sunrisedental.service.DentistService;
import com.sunrisedental.service.PatientService;
import com.sunrisedental.service.TreatmentRecordService;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import java.time.LocalDate;

import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import javax.swing.table.DefaultTableModel;

public class TreatmentRecordsPanel
        extends JPanel {

    private final int userId;

    private int dentistId;
    private int selectedRecordId = -1;

    private final DentistService dentistService;
    private final AppointmentService appointmentService;
    private final PatientService patientService;
    private final TreatmentRecordService treatmentRecordService;

    private JComboBox<Appointment> cmbAppointment;

    private JTextField txtPatient;
    private JTextField txtDiagnosis;
    private JTextField txtTreatmentPerformed;
    private JTextArea txtClinicalNotes;
    private JTextArea txtRecommendation;

    private JCheckBox chkFollowUpRequired;
    private DatePicker followUpDatePicker;

    private JTextField txtSearch;

    private JButton btnSave;
    private JButton btnUpdate;
    private JButton btnClear;
    private JButton btnSearch;
    private JButton btnRefresh;

    private JTable tblRecords;
    private DefaultTableModel tableModel;

    public TreatmentRecordsPanel(
            int userId) {

        this.userId =
                userId;

        dentistService =
                new DentistService();

        appointmentService =
                new AppointmentService();

        patientService =
                new PatientService();

        treatmentRecordService =
                new TreatmentRecordService();

        initUI();

        loadDentist();

        loadAppointments();

        loadRecords();
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

        panel.setOpaque(false);

        JLabel lblTitle =
                new JLabel(
                        "Treatment Records"
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
                        "Manage clinical treatment records for your patients"
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

        main.setOpaque(false);

        main.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        0,
                        0,
                        0
                )
        );

        main.add(
                createFormPanel(),
                BorderLayout.NORTH
        );

        main.add(
                createTablePanel(),
                BorderLayout.CENTER
        );

        return main;
    }

    private JPanel createFormPanel() {

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
                        "Clinical Record Details"
                );

        lblTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        17
                )
        );

        container.add(
                lblTitle,
                BorderLayout.NORTH
        );

        JPanel fields =
                new JPanel(
                        new GridLayout(
                                5,
                                4,
                                12,
                                10
                        )
                );

        fields.setOpaque(false);

        cmbAppointment =
                new JComboBox<>();

        txtPatient =
                new JTextField();

        txtPatient.setEditable(false);

        txtDiagnosis =
                new JTextField();

        txtTreatmentPerformed =
                new JTextField();

        txtClinicalNotes =
                new JTextArea(
                        2,
                        20
                );

        txtClinicalNotes.setLineWrap(true);
        txtClinicalNotes.setWrapStyleWord(true);

        txtRecommendation =
                new JTextArea(
                        2,
                        20
                );

        txtRecommendation.setLineWrap(true);
        txtRecommendation.setWrapStyleWord(true);

        chkFollowUpRequired =
                new JCheckBox(
                        "Required"
                );

        DatePickerSettings settings =
                new DatePickerSettings();

        settings.setFormatForDatesCommonEra(
                "yyyy-MM-dd"
        );

        followUpDatePicker =
                new DatePicker(
                        settings
                );

        fields.add(
                new JLabel("Appointment")
        );

        fields.add(
                cmbAppointment
        );

        fields.add(
                new JLabel("Patient")
        );

        fields.add(
                txtPatient
        );

        fields.add(
                new JLabel("Diagnosis")
        );

        fields.add(
                txtDiagnosis
        );

        fields.add(
                new JLabel("Treatment Performed")
        );

        fields.add(
                txtTreatmentPerformed
        );

        fields.add(
                new JLabel("Clinical Notes")
        );

        fields.add(
                new JScrollPane(
                        txtClinicalNotes
                )
        );

        fields.add(
                new JLabel("Recommendation")
        );

        fields.add(
                new JScrollPane(
                        txtRecommendation
                )
        );

        fields.add(
                new JLabel("Follow-up")
        );

        fields.add(
                chkFollowUpRequired
        );

        fields.add(
                new JLabel("Follow-up Date")
        );

        fields.add(
                followUpDatePicker
        );

        container.add(
                fields,
                BorderLayout.CENTER
        );

        JPanel buttons =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                10,
                                0
                        )
                );

        buttons.setOpaque(false);

        btnSave =
                createPrimaryButton(
                        "Save Record"
                );

        btnUpdate =
                createSecondaryButton(
                        "Update Record"
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
                buttons,
                BorderLayout.SOUTH
        );

        cmbAppointment.addActionListener(
                e -> loadSelectedAppointmentPatient()
        );

        chkFollowUpRequired.addActionListener(
                e -> followUpDatePicker
                        .setEnabled(
                                chkFollowUpRequired
                                        .isSelected()
                        )
        );

        btnSave.addActionListener(
                e -> saveRecord()
        );

        btnUpdate.addActionListener(
                e -> updateRecord()
        );

        btnClear.addActionListener(
                e -> clearForm()
        );

        return container;
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

        JPanel searchPanel =
                new JPanel(
                        new BorderLayout()
                );

        searchPanel.setOpaque(false);

        JLabel lblTitle =
                new JLabel(
                        "My Treatment Records"
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

        searchRight.setOpaque(false);

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

        searchRight.add(
                txtSearch
        );

        searchRight.add(
                btnSearch
        );

        searchRight.add(
                btnRefresh
        );

        searchPanel.add(
                lblTitle,
                BorderLayout.WEST
        );

        searchPanel.add(
                searchRight,
                BorderLayout.EAST
        );

        panel.add(
                searchPanel,
                BorderLayout.NORTH
        );

        tableModel =
                new DefaultTableModel(
                        new Object[]{
                            "Record ID",
                            "Appointment",
                            "Patient ID",
                            "Diagnosis",
                            "Treatment",
                            "Follow-up"
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

        tblRecords =
                new JTable(
                        tableModel
                );

        tblRecords.setRowHeight(
                28
        );

        tblRecords.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        panel.add(
                new JScrollPane(
                        tblRecords
                ),
                BorderLayout.CENTER
        );

        btnSearch.addActionListener(
                e -> searchRecords()
        );

        txtSearch.addActionListener(
                e -> searchRecords()
        );

        btnRefresh.addActionListener(
                e -> {

                    txtSearch.setText("");

                    loadRecords();
                }
        );

        tblRecords
                .getSelectionModel()
                .addListSelectionListener(
                        e -> {

                            if (!e.getValueIsAdjusting()) {

                                loadSelectedRecord();
                            }
                        }
                );

        return panel;
    }

    private JButton createPrimaryButton(
            String text) {

        JButton button =
                new JButton(
                        text
                );

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

        button.setPreferredSize(
                new Dimension(
                        130,
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

        button.setFocusPainted(false);

        button.setPreferredSize(
                new Dimension(
                        130,
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

            dentistId = -1;

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

        cmbAppointment.removeAllItems();

        List<Appointment> appointments =
                appointmentService
                        .getAppointmentsForDentist(
                                dentistId
                        );

        for (Appointment appointment
                : appointments) {

            cmbAppointment.addItem(
                    appointment
            );
        }

        cmbAppointment.setSelectedIndex(
                -1
        );
    }

    private void loadSelectedAppointmentPatient() {

        Appointment appointment =
                (Appointment)
                        cmbAppointment
                                .getSelectedItem();

        if (appointment == null) {

            txtPatient.setText("");

            return;
        }

        Patient patient =
                patientService
                        .getPatientById(
                                appointment.getPatientId()
                        );

        if (patient != null) {

            txtPatient.setText(
                    patient.getName()
            );
        }
    }

    private void saveRecord() {

        try {

            Appointment appointment =
                    (Appointment)
                            cmbAppointment
                                    .getSelectedItem();

            if (appointment == null) {

                throw new IllegalArgumentException(
                        "Please select an appointment."
                );
            }

            TreatmentRecord record =
                    new TreatmentRecord(
                            0,
                            appointment.getAppointmentNo(),
                            appointment.getPatientId(),
                            dentistId,
                            txtDiagnosis
                                    .getText()
                                    .trim(),
                            txtTreatmentPerformed
                                    .getText()
                                    .trim(),
                            txtClinicalNotes
                                    .getText()
                                    .trim(),
                            txtRecommendation
                                    .getText()
                                    .trim(),
                            chkFollowUpRequired
                                    .isSelected(),
                            getFollowUpDate()
                    );

            if (treatmentRecordService
                    .addRecord(
                            record
                    )) {

                JOptionPane.showMessageDialog(
                        this,
                        "Treatment record saved successfully."
                );

                clearForm();

                loadRecords();
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

    private void updateRecord() {

        try {

            if (selectedRecordId <= 0) {

                throw new IllegalArgumentException(
                        "Please select a treatment record first."
                );
            }

            Appointment appointment =
                    (Appointment)
                            cmbAppointment
                                    .getSelectedItem();

            if (appointment == null) {

                throw new IllegalArgumentException(
                        "Please select an appointment."
                );
            }

            TreatmentRecord record =
                    new TreatmentRecord(
                            selectedRecordId,
                            appointment.getAppointmentNo(),
                            appointment.getPatientId(),
                            dentistId,
                            txtDiagnosis
                                    .getText()
                                    .trim(),
                            txtTreatmentPerformed
                                    .getText()
                                    .trim(),
                            txtClinicalNotes
                                    .getText()
                                    .trim(),
                            txtRecommendation
                                    .getText()
                                    .trim(),
                            chkFollowUpRequired
                                    .isSelected(),
                            getFollowUpDate()
                    );

            if (treatmentRecordService
                    .updateRecord(
                            record
                    )) {

                JOptionPane.showMessageDialog(
                        this,
                        "Treatment record updated successfully."
                );

                clearForm();

                loadRecords();
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

    private String getFollowUpDate() {

        if (!chkFollowUpRequired
                .isSelected()) {

            return null;
        }

        LocalDate date =
                followUpDatePicker
                        .getDate();

        if (date == null) {

            throw new IllegalArgumentException(
                    "Please select a follow-up date."
            );
        }

        return date.toString();
    }

    private void loadRecords() {

        if (dentistId <= 0) {
            return;
        }

        List<TreatmentRecord> records =
                treatmentRecordService
                        .getRecordsForDentist(
                                dentistId
                        );

        populateTable(
                records
        );
    }

    private void searchRecords() {

        List<TreatmentRecord> records =
                treatmentRecordService
                        .searchRecordsForDentist(
                                dentistId,
                                txtSearch
                                        .getText()
                                        .trim()
                        );

        populateTable(
                records
        );
    }

    private void populateTable(
            List<TreatmentRecord> records) {

        tableModel.setRowCount(
                0
        );

        for (TreatmentRecord record
                : records) {

            tableModel.addRow(
                    new Object[]{
                        record.getRecordId(),
                        record.getAppointmentNo(),
                        record.getPatientId(),
                        record.getDiagnosis(),
                        record.getTreatmentPerformed(),
                        record.isFollowUpRequired()
                                ? record.getFollowUpDate()
                                : "No"
                    }
            );
        }
    }

    private void loadSelectedRecord() {

        int row =
                tblRecords
                        .getSelectedRow();

        if (row == -1) {
            return;
        }

        selectedRecordId =
                Integer.parseInt(
                        tableModel
                                .getValueAt(
                                        row,
                                        0
                                )
                                .toString()
                );

        String appointmentNo =
                tableModel
                        .getValueAt(
                                row,
                                1
                        )
                        .toString();

        TreatmentRecord record =
                treatmentRecordService
                        .getByAppointmentNo(
                                appointmentNo,
                                dentistId
                        );

        if (record == null) {
            return;
        }

        selectAppointment(
                appointmentNo
        );

        txtDiagnosis.setText(
                record.getDiagnosis()
        );

        txtTreatmentPerformed.setText(
                record.getTreatmentPerformed()
        );

        txtClinicalNotes.setText(
                record.getClinicalNotes()
        );

        txtRecommendation.setText(
                record.getRecommendation()
        );

        chkFollowUpRequired.setSelected(
                record.isFollowUpRequired()
        );

        if (record.getFollowUpDate() != null
                && !record.getFollowUpDate()
                        .isEmpty()) {

            followUpDatePicker.setDate(
                    LocalDate.parse(
                            record.getFollowUpDate()
                    )
            );

        } else {

            followUpDatePicker.clear();
        }
    }

    private void selectAppointment(
            String appointmentNo) {

        for (int i = 0;
                i < cmbAppointment.getItemCount();
                i++) {

            Appointment appointment =
                    cmbAppointment.getItemAt(i);

            if (appointment
                    .getAppointmentNo()
                    .equals(
                            appointmentNo
                    )) {

                cmbAppointment.setSelectedIndex(
                        i
                );

                return;
            }
        }
    }

    private void clearForm() {

        selectedRecordId = -1;

        cmbAppointment.setSelectedIndex(
                -1
        );

        txtPatient.setText("");

        txtDiagnosis.setText("");

        txtTreatmentPerformed.setText("");

        txtClinicalNotes.setText("");

        txtRecommendation.setText("");

        chkFollowUpRequired.setSelected(
                false
        );

        followUpDatePicker.clear();

        followUpDatePicker.setEnabled(
                false
        );

        tblRecords.clearSelection();
    }
}