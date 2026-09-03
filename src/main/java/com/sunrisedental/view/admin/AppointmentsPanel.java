package com.sunrisedental.view.admin;

import com.sunrisedental.model.Appointment;
import com.sunrisedental.model.Dentist;
import com.sunrisedental.model.Patient;

import com.sunrisedental.service.AppointmentService;
import com.sunrisedental.service.DentistService;
import com.sunrisedental.service.PatientService;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.components.TimePicker;
import com.github.lgooddatepicker.components.TimePickerSettings;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import java.time.LocalDate;
import java.time.LocalTime;

import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
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

public class AppointmentsPanel
        extends JPanel {

    private final AppointmentService appointmentService;
    private final PatientService patientService;
    private final DentistService dentistService;

    private JTextField txtAppointmentNo;

    private JComboBox<Patient> cmbPatient;
    private JComboBox<Dentist> cmbDentist;
    private JComboBox<String> cmbTreatment;
    private JComboBox<String> cmbStatus;

    private DatePicker datePicker;
    private TimePicker timePicker;

    private JTextArea txtNotes;

    private JTextField txtSearch;

    private JButton btnUpdate;
    private JButton btnComplete;
    private JButton btnCancel;
    private JButton btnClear;
    private JButton btnSearch;
    private JButton btnRefresh;

    private JTable tblAppointments;
    private DefaultTableModel tableModel;

    public AppointmentsPanel() {

        appointmentService =
                new AppointmentService();

        patientService =
                new PatientService();

        dentistService =
                new DentistService();

        initUI();

        loadPatients();

        loadDentists();

        loadAppointments();
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
                        "Appointment Management"
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
                        "View and manage clinic appointments"
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
                createAppointmentForm(),
                BorderLayout.NORTH
        );

        main.add(
                createAppointmentTable(),
                BorderLayout.CENTER
        );

        return main;
    }

    // ==========================================================
    // APPOINTMENT FORM
    // ==========================================================

    private JPanel createAppointmentForm() {

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
                        "Appointment Details"
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
                                5,
                                4,
                                15,
                                10
                        )
                );

        fields.setOpaque(
                false
        );

        txtAppointmentNo =
                new JTextField();

        txtAppointmentNo.setEditable(
                false
        );

        txtAppointmentNo.setBackground(
                new Color(
                        245,
                        245,
                        245
                )
        );

        cmbPatient =
                new JComboBox<>();

        cmbDentist =
                new JComboBox<>();

        cmbTreatment =
                new JComboBox<>(
                        new String[]{
                            "Dental Check-up",
                            "Cleaning",
                            "Filling",
                            "Extraction",
                            "Root Canal",
                            "Teeth Whitening"
                        }
                );

        cmbStatus =
                new JComboBox<>(
                        new String[]{
                            "PENDING",
                            "COMPLETED",
                            "CANCELLED"
                        }
                );

        // ======================================================
        // DATE PICKER
        // ======================================================

        DatePickerSettings dateSettings =
                new DatePickerSettings();

        dateSettings.setFormatForDatesCommonEra(
                "yyyy-MM-dd"
        );

        datePicker =
                new DatePicker(
                        dateSettings
                );

        // ======================================================
        // TIME PICKER
        // ======================================================

        TimePickerSettings timeSettings =
                new TimePickerSettings();

        timeSettings.use24HourClockFormat();

        timePicker =
                new TimePicker(
                        timeSettings
                );

        timeSettings.setVetoPolicy(
                time -> {

                    LocalTime openingTime =
                            LocalTime.of(
                                    8,
                                    0
                            );

                    LocalTime closingTime =
                            LocalTime.of(
                                    18,
                                    0
                            );

                    return !time.isBefore(
                            openingTime
                    )
                            && !time.isAfter(
                                    closingTime
                            );
                }
        );

        txtNotes =
                new JTextArea(
                        2,
                        20
                );

        txtNotes.setLineWrap(
                true
        );

        txtNotes.setWrapStyleWord(
                true
        );

        JScrollPane notesScroll =
                new JScrollPane(
                        txtNotes
                );

        // ======================================================
        // ADD FIELDS
        // ======================================================

        fields.add(
                new JLabel(
                        "Appointment No"
                )
        );

        fields.add(
                txtAppointmentNo
        );

        fields.add(
                new JLabel(
                        "Patient"
                )
        );

        fields.add(
                cmbPatient
        );

        fields.add(
                new JLabel(
                        "Dentist"
                )
        );

        fields.add(
                cmbDentist
        );

        fields.add(
                new JLabel(
                        "Treatment"
                )
        );

        fields.add(
                cmbTreatment
        );

        fields.add(
                new JLabel(
                        "Appointment Date"
                )
        );

        fields.add(
                datePicker
        );

        fields.add(
                new JLabel(
                        "Appointment Time"
                )
        );

        fields.add(
                timePicker
        );

        fields.add(
                new JLabel(
                        "Status"
                )
        );

        fields.add(
                cmbStatus
        );

        fields.add(
                new JLabel(
                        "Notes"
                )
        );

        fields.add(
                notesScroll
        );

        fields.add(
                new JLabel()
        );

        fields.add(
                new JLabel()
        );

        fields.add(
                new JLabel()
        );

        fields.add(
                new JLabel()
        );

        // ======================================================
        // BUTTONS
        // ======================================================

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

        btnUpdate =
                createPrimaryButton(
                        "Update"
                );

        btnComplete =
                createSecondaryButton(
                        "Mark Completed"
                );

        btnCancel =
                createSecondaryButton(
                        "Cancel Appointment"
                );

        btnClear =
                createSecondaryButton(
                        "Clear"
                );

        buttons.add(
                btnUpdate
        );

        buttons.add(
                btnComplete
        );

        buttons.add(
                btnCancel
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

        btnUpdate.addActionListener(
                e -> updateAppointment()
        );

        btnComplete.addActionListener(
                e -> completeAppointment()
        );

        btnCancel.addActionListener(
                e -> cancelAppointment()
        );

        btnClear.addActionListener(
                e -> clearForm()
        );

        return container;
    }

    // ==========================================================
    // TABLE
    // ==========================================================

    private JPanel createAppointmentTable() {

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
                        "Appointments"
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
                "Search appointment, patient, dentist, treatment or status"
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
                            "Appointment No",
                            "Patient",
                            "Dentist",
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

        container.add(
                search,
                BorderLayout.NORTH
        );

        container.add(
                new JScrollPane(
                        tblAppointments
                ),
                BorderLayout.CENTER
        );

        btnSearch.addActionListener(
                e -> searchAppointments()
        );

        txtSearch.addActionListener(
                e -> searchAppointments()
        );

        btnRefresh.addActionListener(
                e -> {

                    txtSearch.setText(
                            ""
                    );

                    loadAppointments();
                }
        );

        tblAppointments
                .getSelectionModel()
                .addListSelectionListener(
                        e -> {

                            if (!e.getValueIsAdjusting()) {

                                loadSelectedAppointment();
                            }
                        }
                );

        return container;
    }

    // ==========================================================
    // LOAD PATIENTS
    // ==========================================================

    private void loadPatients() {

        cmbPatient.removeAllItems();

        List<Patient> patients =
                patientService
                        .getAllPatients();

        for (Patient patient : patients) {

            cmbPatient.addItem(
                    patient
            );
        }

        cmbPatient.setSelectedIndex(
                -1
        );
    }

    // ==========================================================
    // LOAD DENTISTS
    // ==========================================================

    private void loadDentists() {

        cmbDentist.removeAllItems();

        List<Dentist> dentists =
                dentistService
                        .getActiveDentists();

        for (Dentist dentist : dentists) {

            cmbDentist.addItem(
                    dentist
            );
        }

        cmbDentist.setSelectedIndex(
                -1
        );
    }

    // ==========================================================
    // UPDATE
    // ==========================================================

    private void updateAppointment() {

        try {

            String appointmentNo =
                    txtAppointmentNo
                            .getText()
                            .trim();

            if (appointmentNo.isEmpty()) {

                throw new IllegalArgumentException(
                        "Please select an appointment first."
                );
            }

            Patient selectedPatient =
                    (Patient) cmbPatient
                            .getSelectedItem();

            if (selectedPatient == null) {

                throw new IllegalArgumentException(
                        "Please select a patient."
                );
            }

            Dentist selectedDentist =
                    (Dentist) cmbDentist
                            .getSelectedItem();

            if (selectedDentist == null) {

                throw new IllegalArgumentException(
                        "Please select a dentist."
                );
            }

            Appointment appointment =
                    new Appointment(
                            appointmentNo,

                            selectedPatient
                                    .getPatientId(),

                            selectedDentist
                                    .getDentistId(),

                            cmbTreatment
                                    .getSelectedItem()
                                    .toString(),

                            getSelectedDate(),

                            getSelectedTime(),

                            cmbStatus
                                    .getSelectedItem()
                                    .toString(),

                            txtNotes
                                    .getText()
                                    .trim()
                    );

            boolean success =
                    appointmentService
                            .updateAppointment(
                                    appointment
                            );

            if (success) {

                JOptionPane.showMessageDialog(
                        this,
                        "Appointment updated successfully."
                );

                clearForm();

                loadAppointments();
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
    // COMPLETE
    // ==========================================================

    private void completeAppointment() {

        String appointmentNo =
                txtAppointmentNo
                        .getText()
                        .trim();

        if (appointmentNo.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select an appointment first."
            );

            return;
        }

        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        "Mark this appointment as completed?",
                        "Complete Appointment",
                        JOptionPane.YES_NO_OPTION
                );

        if (result
                != JOptionPane.YES_OPTION) {

            return;
        }

        boolean success =
                appointmentService
                        .updateAppointmentStatus(
                                appointmentNo,
                                "COMPLETED"
                        );

        if (success) {

            JOptionPane.showMessageDialog(
                    this,
                    "Appointment marked as completed."
            );

            clearForm();

            loadAppointments();
        }
    }

    // ==========================================================
    // CANCEL
    // ==========================================================

    private void cancelAppointment() {

        String appointmentNo =
                txtAppointmentNo
                        .getText()
                        .trim();

        if (appointmentNo.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select an appointment first."
            );

            return;
        }

        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to cancel this appointment?",
                        "Cancel Appointment",
                        JOptionPane.YES_NO_OPTION
                );

        if (result
                != JOptionPane.YES_OPTION) {

            return;
        }

        boolean success =
                appointmentService
                        .cancelAppointment(
                                appointmentNo
                        );

        if (success) {

            JOptionPane.showMessageDialog(
                    this,
                    "Appointment cancelled successfully."
            );

            clearForm();

            loadAppointments();
        }
    }

    // ==========================================================
    // LOAD APPOINTMENTS
    // ==========================================================

    private void loadAppointments() {

        List<Appointment> appointments =
                appointmentService
                        .getAllAppointments();

        populateTable(
                appointments
        );
    }

    // ==========================================================
    // SEARCH
    // ==========================================================

    private void searchAppointments() {

        String keyword =
                txtSearch
                        .getText()
                        .trim();

        List<Appointment> appointments =
                appointmentService
                        .searchAppointments(
                                keyword
                        );

        populateTable(
                appointments
        );
    }

    // ==========================================================
    // TABLE
    // ==========================================================

    private void populateTable(
            List<Appointment> appointments) {

        tableModel.setRowCount(
                0
        );

        for (Appointment appointment
                : appointments) {

            tableModel.addRow(
                    new Object[]{
                        appointment
                                .getAppointmentNo(),

                        getPatientNameById(
                                appointment
                                        .getPatientId()
                        ),

                        getDentistNameById(
                                appointment
                                        .getDentistId()
                        ),

                        appointment
                                .getTreatmentType(),

                        appointment
                                .getAppointmentDate(),

                        appointment
                                .getAppointmentTime(),

                        appointment
                                .getStatus()
                    }
            );
        }
    }

    // ==========================================================
    // SELECT APPOINTMENT
    // ==========================================================

    private void loadSelectedAppointment() {

        int row =
                tblAppointments
                        .getSelectedRow();

        if (row == -1) {

            return;
        }

        txtAppointmentNo.setText(
                safeValue(
                        tableModel.getValueAt(
                                row,
                                0
                        )
                )
        );

        selectPatientByName(
                safeValue(
                        tableModel.getValueAt(
                                row,
                                1
                        )
                )
        );

        selectDentistByName(
                safeValue(
                        tableModel.getValueAt(
                                row,
                                2
                        )
                )
        );

        cmbTreatment.setSelectedItem(
                safeValue(
                        tableModel.getValueAt(
                                row,
                                3
                        )
                )
        );

        setSelectedDate(
                safeValue(
                        tableModel.getValueAt(
                                row,
                                4
                        )
                )
        );

        setSelectedTime(
                safeValue(
                        tableModel.getValueAt(
                                row,
                                5
                        )
                )
        );

        cmbStatus.setSelectedItem(
                safeValue(
                        tableModel.getValueAt(
                                row,
                                6
                        )
                )
        );

        /*
         * Load notes directly from the Appointment object
         * because notes are not displayed in the table.
         */

        Appointment appointment =
                appointmentService
                        .findByAppointmentNo(
                                txtAppointmentNo
                                        .getText()
                                        .trim()
                        );

        if (appointment != null) {

            txtNotes.setText(
                    appointment
                            .getNotes()
            );
        }
    }

    // ==========================================================
    // DATE
    // ==========================================================

    private String getSelectedDate() {

        LocalDate date =
                datePicker
                        .getDate();

        if (date == null) {

            throw new IllegalArgumentException(
                    "Please select an appointment date."
            );
        }

        return date.toString();
    }

    private void setSelectedDate(
            String value) {

        if (value == null
                || value.trim().isEmpty()) {

            datePicker.clear();

            return;
        }

        datePicker.setDate(
                LocalDate.parse(
                        value
                )
        );
    }

    // ==========================================================
    // TIME
    // ==========================================================

    private String getSelectedTime() {

        LocalTime time =
                timePicker
                        .getTime();

        if (time == null) {

            throw new IllegalArgumentException(
                    "Please select an appointment time."
            );
        }

        return time
                .withSecond(
                        0
                )
                .withNano(
                        0
                )
                .toString();
    }

    private void setSelectedTime(
            String value) {

        if (value == null
                || value.trim().isEmpty()) {

            timePicker.clear();

            return;
        }

        if (value.length() >= 8) {

            value =
                    value.substring(
                            0,
                            5
                    );
        }

        timePicker.setTime(
                LocalTime.parse(
                        value
                )
        );
    }

    // ==========================================================
    // PATIENT NAME
    // ==========================================================

    private String getPatientNameById(
            int patientId) {

        try {

            Patient patient =
                    patientService
                            .getPatientById(
                                    patientId
                            );

            return patient.getName();

        } catch (IllegalArgumentException e) {

            return "Unknown Patient";
        }
    }

    // ==========================================================
    // DENTIST NAME
    // ==========================================================

    private String getDentistNameById(
            int dentistId) {

        try {

            Dentist dentist =
                    dentistService
                            .getDentistById(
                                    dentistId
                            );

            return dentist.getName();

        } catch (IllegalArgumentException e) {

            return "Unknown Dentist";
        }
    }

    // ==========================================================
    // SELECT PATIENT
    // ==========================================================

    private void selectPatientByName(
            String patientName) {

        for (int i = 0;
                i < cmbPatient.getItemCount();
                i++) {

            Patient patient =
                    cmbPatient
                            .getItemAt(
                                    i
                            );

            if (patient
                    .getName()
                    .equalsIgnoreCase(
                            patientName
                    )) {

                cmbPatient.setSelectedIndex(
                        i
                );

                return;
            }
        }
    }

    // ==========================================================
    // SELECT DENTIST
    // ==========================================================

    private void selectDentistByName(
            String dentistName) {

        for (int i = 0;
                i < cmbDentist.getItemCount();
                i++) {

            Dentist dentist =
                    cmbDentist
                            .getItemAt(
                                    i
                            );

            if (dentist
                    .getName()
                    .equalsIgnoreCase(
                            dentistName
                    )) {

                cmbDentist.setSelectedIndex(
                        i
                );

                return;
            }
        }
    }

    // ==========================================================
    // CLEAR
    // ==========================================================

    private void clearForm() {

        txtAppointmentNo.setText(
                ""
        );

        cmbPatient.setSelectedIndex(
                -1
        );

        cmbDentist.setSelectedIndex(
                -1
        );

        cmbTreatment.setSelectedIndex(
                0
        );

        cmbStatus.setSelectedItem(
                "PENDING"
        );

        datePicker.clear();

        timePicker.clear();

        txtNotes.setText(
                ""
        );

        tblAppointments.clearSelection();
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
    // BUTTON STYLE
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
                        150,
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
                        155,
                        34
                )
        );

        return button;
    }
}