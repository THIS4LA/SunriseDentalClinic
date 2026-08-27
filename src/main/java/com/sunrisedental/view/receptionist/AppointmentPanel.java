package com.sunrisedental.view.receptionist;

import com.sunrisedental.model.Appointment;
import com.sunrisedental.service.AppointmentService;

import com.sunrisedental.model.Patient;
import com.sunrisedental.service.PatientService;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.TimePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.components.TimePickerSettings;

import java.time.LocalDate;
import java.time.LocalTime;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
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

public class AppointmentPanel extends JPanel {

    private final AppointmentService appointmentService;
    private final PatientService patientService;

    private JTextField txtAppointmentNo;
    //private JTextField txtPatient;
    private JComboBox<Patient> cmbPatient;
    private DatePicker datePicker;
    private TimePicker timePicker;
    private JTextField txtSearch;

    private JComboBox<String> cmbDentist;
    private JComboBox<String> cmbTreatment;

    private JTextArea txtNotes;

    private JButton btnSave;
    private JButton btnUpdate;
    private JButton btnCancel;
    private JButton btnClear;
    private JButton btnSearch;

    private JTable tblAppointments;
    private DefaultTableModel tableModel;

    public AppointmentPanel() {

        appointmentService
                = new AppointmentService();

        patientService
                = new PatientService();

        initUI();

        loadPatients();

        generateAppointmentNumber();

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

        createHeader();
        createMainContent();
    }

    private void createHeader() {

        JPanel pnlHeader
                = new JPanel(
                        new BorderLayout()
                );

        pnlHeader.setOpaque(false);

        JLabel lblTitle
                = new JLabel(
                        "Appointment Management"
                );

        lblTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        24
                )
        );

        JLabel lblDescription
                = new JLabel(
                        "Create, search and manage patient appointments"
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

        JPanel pnlTitle
                = new JPanel(
                        new GridLayout(
                                2,
                                1,
                                0,
                                3
                        )
                );

        pnlTitle.setOpaque(false);

        pnlTitle.add(
                lblTitle
        );

        pnlTitle.add(
                lblDescription
        );

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

        JPanel pnlMain
                = new JPanel(
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

        JPanel pnlFormContainer
                = new JPanel(
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

        JLabel lblFormTitle
                = new JLabel(
                        "Appointment Details"
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

        JPanel pnlFields
                = new JPanel(
                        new GridLayout(
                                4,
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

        JLabel lblAppointmentNo
                = new JLabel(
                        "Appointment No"
                );

        txtAppointmentNo
                = new JTextField();

        txtAppointmentNo.setEditable(false);

        JLabel lblPatient
                = new JLabel(
                        "Patient"
                );

        cmbPatient
                = new JComboBox<>();

        JLabel lblDentist
                = new JLabel(
                        "Dentist"
                );

        cmbDentist
                = new JComboBox<>(
                        new String[]{
                            "Select Dentist",
                            "Dr. Silva",
                            "Dr. Perera",
                            "Dr. Fernando"
                        }
                );

        JLabel lblTreatment
                = new JLabel(
                        "Treatment"
                );

        cmbTreatment
                = new JComboBox<>(
                        new String[]{
                            "Select Treatment",
                            "Dental Check-up",
                            "Cleaning",
                            "Filling",
                            "Extraction",
                            "Root Canal",
                            "Teeth Whitening"
                        }
                );

        JLabel lblDate
                = new JLabel("Appointment Date");

        DatePickerSettings dateSettings
                = new DatePickerSettings();

        dateSettings.setFormatForDatesCommonEra(
                "yyyy-MM-dd"
        );

        datePicker
                = new DatePicker(
                        dateSettings
                );

        dateSettings.setDateRangeLimits(
                LocalDate.now(),
                LocalDate.now().plusYears(2)
        );

        JLabel lblTime
                = new JLabel("Appointment Time");

        TimePickerSettings timeSettings
                = new TimePickerSettings();

        timeSettings.use24HourClockFormat();

        timePicker
                = new TimePicker(timeSettings);

        // Set allowed appointment time range
        timeSettings.setVetoPolicy(
                time -> {

                    LocalTime openingTime
                    = LocalTime.of(8, 0);

                    LocalTime closingTime
                    = LocalTime.of(18, 0);

                    return !time.isBefore(openingTime)
                    && !time.isAfter(closingTime);
                }
        );

        JLabel lblNotes
                = new JLabel(
                        "Notes"
                );

        txtNotes
                = new JTextArea(
                        2,
                        20
                );

        txtNotes.setLineWrap(true);
        txtNotes.setWrapStyleWord(true);

        JScrollPane notesScroll
                = new JScrollPane(
                        txtNotes
                );

        pnlFields.add(
                lblAppointmentNo
        );

        pnlFields.add(
                txtAppointmentNo
        );

        pnlFields.add(
                lblPatient
        );

        pnlFields.add(
                cmbPatient
        );

        pnlFields.add(
                lblDentist
        );

        pnlFields.add(
                cmbDentist
        );

        pnlFields.add(
                lblTreatment
        );

        pnlFields.add(
                cmbTreatment
        );

        pnlFields.add(
                lblDate
        );

        pnlFields.add(
                datePicker
        );

        pnlFields.add(
                lblTime
        );

        pnlFields.add(
                timePicker
        );

        pnlFields.add(
                lblNotes
        );

        pnlFields.add(
                notesScroll
        );

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

        JPanel pnlButtons
                = new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                10,
                                0
                        )
                );

        pnlButtons.setOpaque(false);

        btnSave
                = createPrimaryButton(
                        "Save Appointment"
                );

        btnUpdate
                = createSecondaryButton(
                        "Update"
                );

        btnCancel
                = createSecondaryButton(
                        "Cancel Appointment"
                );

        btnClear
                = createSecondaryButton(
                        "Clear"
                );

        pnlButtons.add(
                btnSave
        );

        pnlButtons.add(
                btnUpdate
        );

        pnlButtons.add(
                btnCancel
        );

        pnlButtons.add(
                btnClear
        );

        pnlFormContainer.add(
                pnlButtons,
                BorderLayout.SOUTH
        );

        btnSave.addActionListener(
                e -> saveAppointment()
        );

        btnUpdate.addActionListener(
                e -> updateAppointment()
        );

        btnCancel.addActionListener(
                e -> cancelAppointment()
        );

        btnClear.addActionListener(
                e -> clearForm()
        );

        return pnlFormContainer;
    }

    private JPanel createTablePanel() {

        JPanel pnlTableContainer
                = new JPanel(
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

        JPanel pnlSearch
                = new JPanel(
                        new BorderLayout(
                                10,
                                0
                        )
                );

        pnlSearch.setOpaque(false);

        JLabel lblListTitle
                = new JLabel(
                        "Appointments"
                );

        lblListTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        17
                )
        );

        JPanel pnlSearchRight
                = new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                8,
                                0
                        )
                );

        pnlSearchRight.setOpaque(false);

        txtSearch
                = new JTextField();

        txtSearch.setPreferredSize(
                new Dimension(
                        220,
                        32
                )
        );

        btnSearch
                = createPrimaryButton(
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

        tableModel
                = new DefaultTableModel(
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

        tblAppointments
                = new JTable(
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

        JScrollPane tableScroll
                = new JScrollPane(
                        tblAppointments
                );

        pnlTableContainer.add(
                tableScroll,
                BorderLayout.CENTER
        );

        btnSearch.addActionListener(
                e -> searchAppointments()
        );

        txtSearch.addActionListener(
                e -> searchAppointments()
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

        return pnlTableContainer;
    }

    private JButton createPrimaryButton(
            String text) {

        JButton button
                = new JButton(
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

        JButton button
                = new JButton(
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
                        150,
                        34
                )
        );

        return button;
    }

    private void loadPatients() {

        cmbPatient.removeAllItems();

        List<Patient> patients
                = patientService.getAllPatients();

        for (Patient patient : patients) {

            cmbPatient.addItem(
                    patient
            );
        }

        cmbPatient.setSelectedIndex(-1);
    }

    private String getSelectedDate() {

        LocalDate selectedDate
                = datePicker.getDate();

        if (selectedDate == null) {

            throw new IllegalArgumentException(
                    "Please select an appointment date."
            );
        }

        return selectedDate.toString();
    }

    private String getSelectedTime() {

        LocalTime selectedTime
                = timePicker.getTime();

        if (selectedTime == null) {

            throw new IllegalArgumentException(
                    "Please select an appointment time."
            );
        }

        return selectedTime
                .withSecond(0)
                .withNano(0)
                .toString();
    }

    private void generateAppointmentNumber() {

        String appointmentNo
                = appointmentService
                        .generateAppointmentNumber();

        txtAppointmentNo.setText(
                appointmentNo
        );
    }

    private void saveAppointment() {

        try {

            Patient selectedPatient
                    = (Patient) cmbPatient.getSelectedItem();

            if (selectedPatient == null) {

                throw new IllegalArgumentException(
                        "Please select a patient."
                );
            }

            Appointment appointment
                    = new Appointment(
                            txtAppointmentNo.getText().trim(),
                            selectedPatient.getPatientId(),
                            cmbDentist.getSelectedItem().toString(),
                            cmbTreatment.getSelectedItem().toString(),
                            getSelectedDate(),
                            getSelectedTime(),
                            "PENDING",
                            txtNotes.getText().trim()
                    );

            boolean success
                    = appointmentService
                            .addAppointment(
                                    appointment
                            );

            if (success) {

                JOptionPane.showMessageDialog(
                        this,
                        "Appointment registered successfully.",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
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

    private void updateAppointment() {

        Patient selectedPatient
                = (Patient) cmbPatient.getSelectedItem();

        if (selectedPatient == null) {

            throw new IllegalArgumentException(
                    "Please select a patient."
            );
        }

        try {

            Appointment appointment
                    = new Appointment(
                            txtAppointmentNo
                                    .getText()
                                    .trim(),
                            selectedPatient
                                    .getPatientId(),
                            cmbDentist
                                    .getSelectedItem()
                                    .toString(),
                            cmbTreatment
                                    .getSelectedItem()
                                    .toString(),
                            getSelectedDate(),
                            getSelectedTime(),
                            "PENDING",
                            txtNotes
                                    .getText()
                                    .trim()
                    );

            boolean success
                    = appointmentService
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

    private void cancelAppointment() {

        String appointmentNo
                = txtAppointmentNo
                        .getText()
                        .trim();

        if (appointmentNo.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select an appointment first."
            );

            return;
        }

        int result
                = JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to cancel this appointment?",
                        "Cancel Appointment",
                        JOptionPane.YES_NO_OPTION
                );

        if (result
                != JOptionPane.YES_OPTION) {

            return;
        }

        boolean success
                = appointmentService
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

    private void searchAppointments() {

        String keyword
                = txtSearch
                        .getText()
                        .trim();

        List<Appointment> appointments
                = appointmentService
                        .searchAppointments(
                                keyword
                        );

        populateTable(
                appointments
        );
    }

    private void loadAppointments() {

        List<Appointment> appointments
                = appointmentService
                        .getAllAppointments();

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
                        appointment.getDentistName(),
                        appointment.getTreatmentType(),
                        appointment.getAppointmentDate(),
                        appointment.getAppointmentTime(),
                        appointment.getStatus()
                    }
            );
        }
    }

    private void setSelectedDate(String dateString) {

        if (dateString == null
                || dateString.trim().isEmpty()) {

            datePicker.clear();
            return;
        }

        LocalDate date
                = LocalDate.parse(dateString);

        datePicker.setDate(date);
    }

    private void setSelectedTime(String timeString) {

        if (timeString == null
                || timeString.trim().isEmpty()) {

            timePicker.clear();
            return;
        }

        if (timeString.length() >= 8) {

            timeString
                    = timeString.substring(
                            0,
                            5
                    );
        }

        LocalTime time
                = LocalTime.parse(timeString);

        timePicker.setTime(time);
    }

    private void loadSelectedAppointment() {

        int row
                = tblAppointments
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

        cmbPatient.setSelectedItem(
                safeValue(
                        tableModel.getValueAt(
                                row,
                                1
                        )
                )
        );

        cmbDentist.setSelectedItem(
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

    }

    private String safeValue(
            Object value) {

        return value == null
                ? ""
                : value.toString();
    }

    private void clearForm() {

        cmbPatient.setSelectedIndex(
                0
        );
        datePicker.clear();
        timePicker.clear();
        txtNotes.setText("");

        cmbDentist.setSelectedIndex(
                0
        );

        cmbTreatment.setSelectedIndex(
                0
        );

        tblAppointments.clearSelection();

        generateAppointmentNumber();

        cmbPatient.requestFocus();
    }

}
