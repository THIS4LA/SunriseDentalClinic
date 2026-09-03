package com.sunrisedental.view.dentist;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import com.sunrisedental.service.DentistReportService;
import com.sunrisedental.service.DentistService;

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
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import javax.swing.table.DefaultTableModel;

public class DentistReportsPanel
        extends JPanel {

    private final int userId;

    private int dentistId;

    private final DentistService dentistService;
    private final DentistReportService reportService;

    private JComboBox<String> cmbReportType;

    private DatePicker fromDatePicker;
    private DatePicker toDatePicker;

    private JButton btnGenerate;
    private JButton btnPrint;
    private JButton btnReset;

    private JLabel lblSummary;

    private JTable tblReport;
    private DefaultTableModel tableModel;

    public DentistReportsPanel(
            int userId) {

        this.userId =
                userId;

        dentistService =
                new DentistService();

        reportService =
                new DentistReportService();

        initUI();

        loadDentist();

        setDefaultDates();
    }

    // ==========================================================
    // UI
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
                        "Reports"
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
                        "Generate reports based on your appointments and treatments"
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
                createReportPanel(),
                BorderLayout.CENTER
        );

        return main;
    }

    // ==========================================================
    // FILTER PANEL
    // ==========================================================

    private JPanel createFilterPanel() {

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
                                20,
                                20,
                                20,
                                20
                        )
                )
        );

        JLabel lblTitle =
                new JLabel(
                        "Report Filters"
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

        JPanel pnlFields =
                new JPanel(
                        new GridLayout(
                                2,
                                3,
                                15,
                                8
                        )
                );

        pnlFields.setOpaque(false);

        JLabel lblReportType =
                new JLabel(
                        "Report Type"
                );

        JLabel lblFrom =
                new JLabel(
                        "From Date"
                );

        JLabel lblTo =
                new JLabel(
                        "To Date"
                );

        cmbReportType =
                new JComboBox<>(
                        new String[]{
                            "Appointment Summary",
                            "Treatment Summary",
                            "Status Summary",
                            "Patient Summary"
                        }
                );

        DatePickerSettings fromSettings =
                new DatePickerSettings();

        fromSettings
                .setFormatForDatesCommonEra(
                        "yyyy-MM-dd"
                );

        fromDatePicker =
                new DatePicker(
                        fromSettings
                );

        DatePickerSettings toSettings =
                new DatePickerSettings();

        toSettings
                .setFormatForDatesCommonEra(
                        "yyyy-MM-dd"
                );

        toDatePicker =
                new DatePicker(
                        toSettings
                );

        pnlFields.add(
                lblReportType
        );

        pnlFields.add(
                lblFrom
        );

        pnlFields.add(
                lblTo
        );

        pnlFields.add(
                cmbReportType
        );

        pnlFields.add(
                fromDatePicker
        );

        pnlFields.add(
                toDatePicker
        );

        container.add(
                pnlFields,
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

        btnGenerate =
                createPrimaryButton(
                        "Generate Report"
                );

        btnPrint =
                createSecondaryButton(
                        "Print Report"
                );

        btnReset =
                createSecondaryButton(
                        "Reset"
                );

        buttons.add(
                btnGenerate
        );

        buttons.add(
                btnPrint
        );

        buttons.add(
                btnReset
        );

        container.add(
                buttons,
                BorderLayout.SOUTH
        );

        btnGenerate.addActionListener(
                e -> generateReport()
        );

        btnPrint.addActionListener(
                e -> printReport()
        );

        btnReset.addActionListener(
                e -> resetReport()
        );

        return container;
    }

    // ==========================================================
    // REPORT TABLE
    // ==========================================================

    private JPanel createReportPanel() {

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

        JPanel header =
                new JPanel(
                        new BorderLayout()
                );

        header.setOpaque(false);

        JLabel lblTitle =
                new JLabel(
                        "Report Results"
                );

        lblTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        17
                )
        );

        lblSummary =
                new JLabel(
                        "No report generated"
                );

        lblSummary.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        header.add(
                lblTitle,
                BorderLayout.WEST
        );

        header.add(
                lblSummary,
                BorderLayout.EAST
        );

        tableModel =
                new DefaultTableModel();

        tblReport =
                new JTable(
                        tableModel
                );

        tblReport.setRowHeight(
                28
        );

        tblReport
                .getTableHeader()
                .setFont(
                        new Font(
                                "Segoe UI",
                                Font.BOLD,
                                13
                        )
                );

        panel.add(
                header,
                BorderLayout.NORTH
        );

        panel.add(
                new JScrollPane(
                        tblReport
                ),
                BorderLayout.CENTER
        );

        return panel;
    }

    // ==========================================================
    // BUTTONS
    // ==========================================================

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

        button.setFocusPainted(false);

        button.setPreferredSize(
                new Dimension(
                        130,
                        34
                )
        );

        return button;
    }

    // ==========================================================
    // LOAD DENTIST
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
    // DEFAULT DATES
    // ==========================================================

    private void setDefaultDates() {

        LocalDate today =
                LocalDate.now();

        fromDatePicker.setDate(
                today.withDayOfMonth(1)
        );

        toDatePicker.setDate(
                today
        );
    }

    // ==========================================================
    // GENERATE REPORT
    // ==========================================================

    private void generateReport() {

        try {

            if (dentistId <= 0) {

                throw new IllegalArgumentException(
                        "Invalid dentist."
                );
            }

            LocalDate from =
                    fromDatePicker.getDate();

            LocalDate to =
                    toDatePicker.getDate();

            if (from == null
                    || to == null) {

                throw new IllegalArgumentException(
                        "Please select both dates."
                );
            }

            String reportType =
                    cmbReportType
                            .getSelectedItem()
                            .toString();

            switch (reportType) {

                case "Appointment Summary":
                    generateAppointmentSummary(
                            from.toString(),
                            to.toString()
                    );
                    break;

                case "Treatment Summary":
                    generateTreatmentSummary(
                            from.toString(),
                            to.toString()
                    );
                    break;

                case "Status Summary":
                    generateStatusSummary(
                            from.toString(),
                            to.toString()
                    );
                    break;

                case "Patient Summary":
                    generatePatientSummary(
                            from.toString(),
                            to.toString()
                    );
                    break;
            }

        } catch (IllegalArgumentException e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Report Error",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }

    // ==========================================================
    // APPOINTMENT SUMMARY
    // ==========================================================

    private void generateAppointmentSummary(
            String fromDate,
            String toDate) {

        List<Object[]> rows =
                reportService
                        .getAppointmentSummary(
                                dentistId,
                                fromDate,
                                toDate
                        );

        tableModel.setColumnIdentifiers(
                new Object[]{
                    "Appointment No",
                    "Patient",
                    "Treatment",
                    "Date",
                    "Time",
                    "Status"
                }
        );

        populateTable(
                rows
        );

        lblSummary.setText(
                "Total appointments: "
                + rows.size()
        );
    }

    // ==========================================================
    // TREATMENT SUMMARY
    // ==========================================================

    private void generateTreatmentSummary(
            String fromDate,
            String toDate) {

        List<Object[]> rows =
                reportService
                        .getTreatmentSummary(
                                dentistId,
                                fromDate,
                                toDate
                        );

        tableModel.setColumnIdentifiers(
                new Object[]{
                    "Treatment",
                    "Completed Count"
                }
        );

        populateTable(
                rows
        );

        lblSummary.setText(
                "Treatment types: "
                + rows.size()
        );
    }

    // ==========================================================
    // STATUS SUMMARY
    // ==========================================================

    private void generateStatusSummary(
            String fromDate,
            String toDate) {

        List<Object[]> rows =
                reportService
                        .getStatusSummary(
                                dentistId,
                                fromDate,
                                toDate
                        );

        tableModel.setColumnIdentifiers(
                new Object[]{
                    "Status",
                    "Appointment Count"
                }
        );

        populateTable(
                rows
        );

        lblSummary.setText(
                "Status categories: "
                + rows.size()
        );
    }

    // ==========================================================
    // PATIENT SUMMARY
    // ==========================================================

    private void generatePatientSummary(
            String fromDate,
            String toDate) {

        int count =
                reportService
                        .getPatientCount(
                                dentistId,
                                fromDate,
                                toDate
                        );

        tableModel.setColumnIdentifiers(
                new Object[]{
                    "Description",
                    "Total"
                }
        );

        tableModel.setRowCount(
                0
        );

        tableModel.addRow(
                new Object[]{
                    "Distinct Patients",
                    count
                }
        );

        lblSummary.setText(
                "Patients treated: "
                + count
        );
    }

    // ==========================================================
    // POPULATE TABLE
    // ==========================================================

    private void populateTable(
            List<Object[]> rows) {

        tableModel.setRowCount(
                0
        );

        for (Object[] row : rows) {

            tableModel.addRow(
                    row
            );
        }
    }

    // ==========================================================
    // PRINT
    // ==========================================================

    private void printReport() {

        if (tableModel.getRowCount() == 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please generate a report first."
            );

            return;
        }

        try {

            String reportTitle =
                    cmbReportType
                            .getSelectedItem()
                            .toString();

            tblReport.print(
                    JTable.PrintMode.FIT_WIDTH,
                    new java.text.MessageFormat(
                            "Sunrise Dental Clinic - "
                            + reportTitle
                    ),
                    new java.text.MessageFormat(
                            "Page {0}"
                    )
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to print report.",
                    "Print Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // ==========================================================
    // RESET
    // ==========================================================

    private void resetReport() {

        cmbReportType.setSelectedIndex(
                0
        );

        setDefaultDates();

        tableModel.setRowCount(
                0
        );

        tableModel.setColumnCount(
                0
        );

        lblSummary.setText(
                "No report generated"
        );
    }
}