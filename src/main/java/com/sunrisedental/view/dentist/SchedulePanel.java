package com.sunrisedental.view.dentist;

import com.sunrisedental.model.Appointment;
import com.sunrisedental.model.Patient;

import com.sunrisedental.service.AppointmentService;
import com.sunrisedental.service.DentistService;
import com.sunrisedental.service.PatientService;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class SchedulePanel
        extends JPanel {

    private final int userId;

    private int dentistId;

    private final AppointmentService appointmentService;
    private final DentistService dentistService;
    private final PatientService patientService;

    private DatePicker datePicker;

    private JButton btnToday;
    private JButton btnRefresh;

    private JTable tblSchedule;
    private DefaultTableModel tableModel;

    public SchedulePanel(
            int userId) {

        this.userId = userId;

        appointmentService
                = new AppointmentService();

        dentistService
                = new DentistService();

        patientService
                = new PatientService();

        initUI();

        loadDentist();

        datePicker.setDate(
                LocalDate.now()
        );

        loadSchedule();
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

        JPanel panel
                = new JPanel(
                        new GridLayout(
                                2,
                                1,
                                0,
                                3
                        )
                );

        panel.setOpaque(false);

        JLabel lblTitle
                = new JLabel(
                        "My Schedule"
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
                        "View your appointments by date"
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

        JPanel main
                = new JPanel(
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

        JLabel lblTitle
                = new JLabel(
                        "Schedule Date"
                );

        lblTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        16
                )
        );

        JPanel pnlControls
                = new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                8,
                                0
                        )
                );

        pnlControls.setOpaque(false);

        DatePickerSettings dateSettings
                = new DatePickerSettings();

        dateSettings.setFormatForDatesCommonEra(
                "yyyy-MM-dd"
        );

        datePicker
                = new DatePicker(
                        dateSettings
                );

        datePicker.setPreferredSize(
                new Dimension(
                        180,
                        32
                )
        );

        btnToday
                = new JButton(
                        "Today"
                );

        btnRefresh
                = new JButton(
                        "Refresh"
                );

        btnToday.setPreferredSize(
                new Dimension(
                        100,
                        32
                )
        );

        btnRefresh.setPreferredSize(
                new Dimension(
                        100,
                        32
                )
        );

        pnlControls.add(
                datePicker
        );

        pnlControls.add(
                btnToday
        );

        pnlControls.add(
                btnRefresh
        );

        panel.add(
                lblTitle,
                BorderLayout.WEST
        );

        panel.add(
                pnlControls,
                BorderLayout.EAST
        );

        datePicker.addDateChangeListener(
                e -> loadSchedule()
        );

        btnToday.addActionListener(
                e -> {

                    datePicker.setDate(
                            LocalDate.now()
                    );

                    loadSchedule();
                }
        );

        btnRefresh.addActionListener(
                e -> loadSchedule()
        );

        return panel;
    }

    private JPanel createTablePanel() {

        JPanel panel
                = new JPanel(
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

        JLabel lblTitle
                = new JLabel(
                        "Daily Schedule"
                );

        lblTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        17
                )
        );

        tableModel
                = new DefaultTableModel(
                        new Object[]{
                            "Time",
                            "Appointment No",
                            "Patient",
                            "Treatment",
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

        tblSchedule
                = new JTable(
                        tableModel
                );

        tblSchedule.setRowHeight(
                28
        );

        tblSchedule
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
                        tblSchedule
                ),
                BorderLayout.CENTER
        );

        return panel;
    }

    private void loadDentist() {

        dentistId
                = dentistService
                        .getDentistIdByUserId(
                                userId
                        );
    }

    private void loadSchedule() {

        if (dentistId <= 0) {
            return;
        }

        LocalDate selectedDate
                = datePicker.getDate();

        if (selectedDate == null) {
            return;
        }

        List<Appointment> appointments
                = appointmentService.getScheduleForDentist(
                                dentistId,
                                selectedDate.toString()
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

            Patient patient
                    = patientService
                            .getPatientById(
                                    appointment
                                            .getPatientId()
                            );

            String patientName
                    = patient != null
                            ? patient.getName()
                            : "Unknown Patient";

            tableModel.addRow(
                    new Object[]{
                        appointment.getAppointmentTime(),
                        appointment.getAppointmentNo(),
                        patientName,
                        appointment.getTreatmentType(),
                        appointment.getStatus()
                    }
            );
        }
    }
}
