package com.sunrisedental.view.admin;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import com.sunrisedental.service.AdminReportService;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import java.math.BigDecimal;

import java.text.MessageFormat;

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

public class ReportsPanel
        extends JPanel {

    private final AdminReportService reportService;

    private JComboBox<String> cmbReportType;

    private DatePicker fromDatePicker;
    private DatePicker toDatePicker;

    private JButton btnGenerate;
    private JButton btnPrint;
    private JButton btnReset;

    private JLabel lblResultSummary;
    private JLabel lblRevenue;

    private JTable tblReport;
    private DefaultTableModel tableModel;

    public ReportsPanel() {

        reportService =
                new AdminReportService();

        initUI();

        setDefaultDates();
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
                        "Admin Reports"
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
                        "Generate clinic-wide operational and financial reports"
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

        JPanel top =
                new JPanel(
                        new BorderLayout(
                                0,
                                15
                        )
                );

        top.setOpaque(
                false
        );

        top.add(
                createFilterPanel(),
                BorderLayout.NORTH
        );

        top.add(
                createSummaryPanel(),
                BorderLayout.CENTER
        );

        main.add(
                top,
                BorderLayout.NORTH
        );

        main.add(
                createReportTablePanel(),
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
                                15,
                                15,
                                15,
                                15
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

        JPanel fields =
                new JPanel(
                        new GridLayout(
                                2,
                                3,
                                15,
                                8
                        )
                );

        fields.setOpaque(
                false
        );

        cmbReportType =
                new JComboBox<>(
                        new String[]{
                            "Appointment Summary",
                            "Revenue by Treatment",
                            "Dentist Workload",
                            "Payment Method Summary"
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

        fields.add(
                new JLabel(
                        "Report Type"
                )
        );

        fields.add(
                new JLabel(
                        "From Date"
                )
        );

        fields.add(
                new JLabel(
                        "To Date"
                )
        );

        fields.add(
                cmbReportType
        );

        fields.add(
                fromDatePicker
        );

        fields.add(
                toDatePicker
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

        btnGenerate.addActionListener(
                e -> generateReport()
        );

        btnPrint.addActionListener(
                e -> printReport()
        );

        btnReset.addActionListener(
                e -> resetReport()
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

        return container;
    }

    // ==========================================================
    // SUMMARY PANEL
    // ==========================================================

    private JPanel createSummaryPanel() {

        JPanel panel =
                new JPanel(
                        new GridLayout(
                                1,
                                2,
                                15,
                                0
                        )
                );

        panel.setOpaque(
                false
        );

        lblResultSummary =
                new JLabel(
                        "No report generated"
                );

        lblRevenue =
                new JLabel(
                        "Paid Revenue: 0.00"
                );

        panel.add(
                createSummaryCard(
                        "Report Result",
                        lblResultSummary
                )
        );

        panel.add(
                createSummaryCard(
                        "Revenue",
                        lblRevenue
                )
        );

        return panel;
    }

    private JPanel createSummaryCard(
            String title,
            JLabel value) {

        JPanel panel =
                new JPanel(
                        new BorderLayout(
                                0,
                                8
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
                        title
                );

        lblTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        value.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        18
                )
        );

        panel.add(
                lblTitle,
                BorderLayout.NORTH
        );

        panel.add(
                value,
                BorderLayout.CENTER
        );

        return panel;
    }

    // ==========================================================
    // REPORT TABLE
    // ==========================================================

    private JPanel createReportTablePanel() {

        JPanel container =
                new JPanel(
                        new BorderLayout(
                                0,
                                10
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
                        "Report Results"
                );

        lblTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        17
                )
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

        container.add(
                lblTitle,
                BorderLayout.NORTH
        );

        container.add(
                new JScrollPane(
                        tblReport
                ),
                BorderLayout.CENTER
        );

        return container;
    }

    // ==========================================================
    // GENERATE REPORT
    // ==========================================================

    private void generateReport() {

        try {

            LocalDate from =
                    fromDatePicker
                            .getDate();

            LocalDate to =
                    toDatePicker
                            .getDate();

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

            String fromDate =
                    from.toString();

            String toDate =
                    to.toString();

            switch (reportType) {

                case "Appointment Summary":

                    generateAppointmentSummary(
                            fromDate,
                            toDate
                    );

                    break;

                case "Revenue by Treatment":

                    generateRevenueByTreatment(
                            fromDate,
                            toDate
                    );

                    break;

                case "Dentist Workload":

                    generateDentistWorkload(
                            fromDate,
                            toDate
                    );

                    break;

                case "Payment Method Summary":

                    generatePaymentSummary(
                            fromDate,
                            toDate
                    );

                    break;
            }

            BigDecimal revenue =
                    reportService
                            .getTotalRevenue(
                                    fromDate,
                                    toDate
                            );

            lblRevenue.setText(
                    "Paid Revenue: "
                    + revenue
            );

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
                                fromDate,
                                toDate
                        );

        tableModel.setColumnIdentifiers(
                new Object[]{
                    "Appointment No",
                    "Patient",
                    "Dentist",
                    "Treatment",
                    "Date",
                    "Time",
                    "Status"
                }
        );

        populateTable(
                rows
        );

        lblResultSummary.setText(
                "Appointments: "
                + rows.size()
        );
    }

    // ==========================================================
    // REVENUE BY TREATMENT
    // ==========================================================

    private void generateRevenueByTreatment(
            String fromDate,
            String toDate) {

        List<Object[]> rows =
                reportService
                        .getRevenueByTreatment(
                                fromDate,
                                toDate
                        );

        tableModel.setColumnIdentifiers(
                new Object[]{
                    "Treatment",
                    "Paid Bills",
                    "Revenue"
                }
        );

        populateTable(
                rows
        );

        lblResultSummary.setText(
                "Treatment types: "
                + rows.size()
        );
    }

    // ==========================================================
    // DENTIST WORKLOAD
    // ==========================================================

    private void generateDentistWorkload(
            String fromDate,
            String toDate) {

        List<Object[]> rows =
                reportService
                        .getDentistWorkload(
                                fromDate,
                                toDate
                        );

        tableModel.setColumnIdentifiers(
                new Object[]{
                    "Dentist ID",
                    "Dentist",
                    "Appointments",
                    "Completed",
                    "Pending",
                    "Cancelled"
                }
        );

        populateTable(
                rows
        );

        lblResultSummary.setText(
                "Dentists: "
                + rows.size()
        );
    }

    // ==========================================================
    // PAYMENT METHOD SUMMARY
    // ==========================================================

    private void generatePaymentSummary(
            String fromDate,
            String toDate) {

        List<Object[]> rows =
                reportService
                        .getPaymentMethodSummary(
                                fromDate,
                                toDate
                        );

        tableModel.setColumnIdentifiers(
                new Object[]{
                    "Payment Method",
                    "Paid Bills",
                    "Total Amount"
                }
        );

        populateTable(
                rows
        );

        lblResultSummary.setText(
                "Payment methods: "
                + rows.size()
        );
    }

    // ==========================================================
    // POPULATE
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
    // DEFAULT DATES
    // ==========================================================

    private void setDefaultDates() {

        LocalDate today =
                LocalDate.now();

        fromDatePicker.setDate(
                today.withDayOfMonth(
                        1
                )
        );

        toDatePicker.setDate(
                today
        );
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

        lblResultSummary.setText(
                "No report generated"
        );

        lblRevenue.setText(
                "Paid Revenue: 0.00"
        );
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

            String reportType =
                    cmbReportType
                            .getSelectedItem()
                            .toString();

            MessageFormat header =
                    new MessageFormat(
                            "Sunrise Dental Clinic - "
                            + reportType
                    );

            MessageFormat footer =
                    new MessageFormat(
                            "Page {0}"
                    );

            tblReport.print(
                    JTable.PrintMode.FIT_WIDTH,
                    header,
                    footer
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
    // BUTTONS
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

        button.setPreferredSize(
                new Dimension(
                        140,
                        34
                )
        );

        return button;
    }
}