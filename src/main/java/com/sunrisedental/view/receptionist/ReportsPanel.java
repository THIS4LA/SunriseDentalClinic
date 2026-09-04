package com.sunrisedental.view.receptionist;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import com.sunrisedental.model.ReportSummary;
import com.sunrisedental.service.ReportService;

import com.sunrisedental.util.PdfOpener;
import com.sunrisedental.utill.ReportPdfGenerator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import java.io.File;

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

public class ReportsPanel extends JPanel {

    // ==========================================================
    // SERVICE
    // ==========================================================
    private final ReportService reportService;

    // ==========================================================
    // FILTER COMPONENTS
    // ==========================================================
    private JComboBox<String> cmbReportType;

    private DatePicker datePickerFrom;
    private DatePicker datePickerTo;

    // ==========================================================
    // BUTTONS
    // ==========================================================
    private JButton btnGenerate;
    private JButton btnPrint;
    private JButton btnReset;

    // ==========================================================
    // SUMMARY LABELS
    // ==========================================================
    private JLabel lblTotalValue;
    private JLabel lblCompletedValue;
    private JLabel lblPendingValue;
    private JLabel lblRevenueValue;

    // ==========================================================
    // REPORT TABLE
    // ==========================================================
    private JTable tblReport;

    private DefaultTableModel tableModel;

    // ==========================================================
    // CONSTRUCTOR
    // ==========================================================
    public ReportsPanel() {

        reportService
                = new ReportService();

        initUI();

        setDefaultDates();

        /*
         * Load initial report information only.
         *
         * We do NOT generate/open a PDF here because otherwise
         * Chrome would open automatically whenever the user
         * visits the Reports page.
         */
        loadReportData();
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

        JPanel panel
                = new JPanel(
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

        JLabel lblTitle
                = new JLabel(
                        "Reports"
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
                        "View clinic performance, "
                        + "appointment and revenue reports"
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

        JPanel main
                = new JPanel(
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

        JPanel center
                = new JPanel(
                        new BorderLayout(
                                0,
                                15
                        )
                );

        center.setOpaque(
                false
        );

        center.add(
                createSummaryPanel(),
                BorderLayout.NORTH
        );

        center.add(
                createReportTable(),
                BorderLayout.CENTER
        );

        main.add(
                center,
                BorderLayout.CENTER
        );

        return main;
    }

    // ==========================================================
    // FILTER PANEL
    // ==========================================================
    private JPanel createFilterPanel() {

        JPanel container
                = new JPanel(
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
                                18,
                                20,
                                18,
                                20
                        )
                )
        );

        // ======================================================
        // TITLE
        // ======================================================
        JLabel lblTitle
                = new JLabel(
                        "Report Filters"
                );

        lblTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        16
                )
        );

        container.add(
                lblTitle,
                BorderLayout.NORTH
        );

        // ======================================================
        // FILTER FIELDS PANEL
        // ======================================================
        JPanel pnlFields
                = new JPanel(
                        new GridBagLayout()
                );

        pnlFields.setOpaque(
                false
        );

        pnlFields.setBorder(
                BorderFactory.createEmptyBorder(
                        10,
                        0,
                        10,
                        0
                )
        );

        // ======================================================
        // REPORT TYPE
        // ======================================================
        cmbReportType
                = new JComboBox<>(
                        new String[]{
                            "Appointment Summary",
                            "Revenue by Treatment",
                            "Dentist Workload",
                            "Payment Method Summary"
                        }
                );

        cmbReportType.setPreferredSize(
                new Dimension(
                        280,
                        34
                )
        );

        // ======================================================
        // FROM DATE
        // ======================================================
        DatePickerSettings fromSettings
                = new DatePickerSettings();

        fromSettings.setFormatForDatesCommonEra(
                "yyyy-MM-dd"
        );

        fromSettings.setFormatForDatesBeforeCommonEra(
                "uuuu-MM-dd"
        );

        datePickerFrom
                = new DatePicker(
                        fromSettings
                );

        datePickerFrom.setPreferredSize(
                new Dimension(
                        220,
                        34
                )
        );

        // ======================================================
        // TO DATE
        // ======================================================
        DatePickerSettings toSettings
                = new DatePickerSettings();

        toSettings.setFormatForDatesCommonEra(
                "yyyy-MM-dd"
        );

        toSettings.setFormatForDatesBeforeCommonEra(
                "uuuu-MM-dd"
        );

        datePickerTo
                = new DatePicker(
                        toSettings
                );

        datePickerTo.setPreferredSize(
                new Dimension(
                        220,
                        34
                )
        );

        // ======================================================
        // GRID BAG
        // ======================================================
        GridBagConstraints gbc
                = new GridBagConstraints();

        gbc.insets
                = new Insets(
                        8,
                        8,
                        8,
                        8
                );

        gbc.anchor
                = GridBagConstraints.WEST;

        // ======================================================
        // ROW 1 - REPORT TYPE
        // ======================================================
        gbc.gridy = 0;
        gbc.gridx = 0;

        JLabel lblReportType
                = new JLabel(
                        "Report Type"
                );

        lblReportType.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        pnlFields.add(
                lblReportType,
                gbc
        );

        gbc.gridx = 1;
        gbc.gridwidth = 3;
        gbc.weightx = 1.0;

        gbc.fill
                = GridBagConstraints.HORIZONTAL;

        pnlFields.add(
                cmbReportType,
                gbc
        );

        // ======================================================
        // ROW 2 - FROM DATE
        // ======================================================
        gbc.gridy = 1;

        gbc.gridwidth = 1;
        gbc.weightx = 0;

        gbc.fill
                = GridBagConstraints.NONE;

        gbc.gridx = 0;

        JLabel lblFromDate
                = new JLabel(
                        "From Date"
                );

        lblFromDate.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        pnlFields.add(
                lblFromDate,
                gbc
        );

        gbc.gridx = 1;
        gbc.weightx = 1.0;

        gbc.fill
                = GridBagConstraints.HORIZONTAL;

        pnlFields.add(
                datePickerFrom,
                gbc
        );

        // ======================================================
        // ROW 2 - TO DATE
        // ======================================================
        gbc.gridx = 2;
        gbc.weightx = 0;

        gbc.fill
                = GridBagConstraints.NONE;

        JLabel lblToDate
                = new JLabel(
                        "To Date"
                );

        lblToDate.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        pnlFields.add(
                lblToDate,
                gbc
        );

        gbc.gridx = 3;
        gbc.weightx = 1.0;

        gbc.fill
                = GridBagConstraints.HORIZONTAL;

        pnlFields.add(
                datePickerTo,
                gbc
        );

        container.add(
                pnlFields,
                BorderLayout.CENTER
        );

        // ======================================================
        // BUTTON PANEL
        // ======================================================
        JPanel pnlButtons
                = new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                10,
                                0
                        )
                );

        pnlButtons.setOpaque(
                false
        );

        btnGenerate
                = new JButton(
                        "Generate PDF"
                );

        btnPrint
                = new JButton(
                        "Print Report"
                );

        btnReset
                = new JButton(
                        "Reset"
                );

        Dimension buttonSize
                = new Dimension(
                        140,
                        34
                );

        btnGenerate.setPreferredSize(
                buttonSize
        );

        btnPrint.setPreferredSize(
                buttonSize
        );

        btnReset.setPreferredSize(
                new Dimension(
                        100,
                        34
                )
        );

        pnlButtons.add(
                btnGenerate
        );

        pnlButtons.add(
                btnPrint
        );

        pnlButtons.add(
                btnReset
        );

        container.add(
                pnlButtons,
                BorderLayout.SOUTH
        );

        // ======================================================
        // EVENTS
        // ======================================================
        btnGenerate.addActionListener(
                e -> generateReport()
        );

        btnPrint.addActionListener(
                e -> printReport()
        );

        btnReset.addActionListener(
                e -> resetReport()
        );

        /*
         * Changing the report type updates the table only.
         * It does not automatically create a PDF.
         */
        cmbReportType.addActionListener(
                e -> {

                    if (datePickerFrom != null
                            && datePickerTo != null) {

                        loadReportData();
                    }
                }
        );

        return container;
    }

    // ==========================================================
    // SUMMARY PANEL
    // ==========================================================
    private JPanel createSummaryPanel() {

        JPanel panel
                = new JPanel(
                        new GridLayout(
                                1,
                                4,
                                15,
                                0
                        )
                );

        panel.setOpaque(
                false
        );

        lblTotalValue
                = new JLabel(
                        "0"
                );

        lblCompletedValue
                = new JLabel(
                        "0"
                );

        lblPendingValue
                = new JLabel(
                        "0"
                );

        lblRevenueValue
                = new JLabel(
                        "Rs. 0.00"
                );

        panel.add(
                createSummaryCard(
                        "Appointments",
                        lblTotalValue
                )
        );

        panel.add(
                createSummaryCard(
                        "Completed",
                        lblCompletedValue
                )
        );

        panel.add(
                createSummaryCard(
                        "Pending",
                        lblPendingValue
                )
        );

        panel.add(
                createSummaryCard(
                        "Revenue",
                        lblRevenueValue
                )
        );

        return panel;
    }

    // ==========================================================
    // SUMMARY CARD
    // ==========================================================
    private JPanel createSummaryCard(
            String title,
            JLabel valueLabel) {

        JPanel panel
                = new JPanel(
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

        JLabel lblTitle
                = new JLabel(
                        title
                );

        lblTitle.setForeground(
                new Color(
                        90,
                        90,
                        90
                )
        );

        valueLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        22
                )
        );

        panel.add(
                lblTitle,
                BorderLayout.NORTH
        );

        panel.add(
                valueLabel,
                BorderLayout.CENTER
        );

        return panel;
    }

    // ==========================================================
    // REPORT TABLE
    // ==========================================================
    private JPanel createReportTable() {

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
                                12,
                                12,
                                12,
                                12
                        )
                )
        );

        JLabel title
                = new JLabel(
                        "Report Results"
                );

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        16
                )
        );

        tableModel
                = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(
                    int row,
                    int column) {

                return false;
            }
        };

        tblReport
                = new JTable(
                        tableModel
                );

        tblReport.setRowHeight(
                28
        );

        tblReport.setFillsViewportHeight(
                true
        );

        tblReport.getTableHeader()
                .setFont(
                        new Font(
                                "Segoe UI",
                                Font.BOLD,
                                13
                        )
                );

        JScrollPane scrollPane
                = new JScrollPane(
                        tblReport
                );

        panel.add(
                title,
                BorderLayout.NORTH
        );

        panel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        return panel;
    }

    // ==========================================================
    // LOAD REPORT DATA ONLY
    // ==========================================================
    private void loadReportData() {

        try {

            LocalDate fromDate
                    = datePickerFrom.getDate();

            LocalDate toDate
                    = datePickerTo.getDate();

            /*
             * getSummary() already performs the service-level
             * date validation.
             */
            updateSummary(
                    fromDate,
                    toDate
            );

            Object selectedItem
                    = cmbReportType
                            .getSelectedItem();

            if (selectedItem == null) {

                throw new IllegalArgumentException(
                        "Please select a report type."
                );
            }

            String reportType
                    = selectedItem.toString();

            switch (reportType) {

                case "Appointment Summary" ->

                    loadAppointmentReport(
                            fromDate,
                            toDate
                    );

                case "Revenue by Treatment" ->

                    loadRevenueReport(
                            fromDate,
                            toDate
                    );

                case "Dentist Workload" ->

                    loadDentistReport(
                            fromDate,
                            toDate
                    );

                case "Payment Method Summary" ->

                    loadPaymentReport(
                            fromDate,
                            toDate
                    );

                default ->

                    throw new IllegalArgumentException(
                            "Invalid report type."
                    );
            }

        } catch (IllegalArgumentException e) {

            clearReportTable();

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Report Error",
                    JOptionPane.WARNING_MESSAGE
            );

        } catch (Exception e) {

            clearReportTable();

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to load report data.\n"
                    + e.getMessage(),
                    "Report Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // ==========================================================
    // GENERATE PDF REPORT
    // ==========================================================
    private void generateReport() {

        try {

            LocalDate fromDate
                    = datePickerFrom.getDate();

            LocalDate toDate
                    = datePickerTo.getDate();

            Object selectedItem
                    = cmbReportType
                            .getSelectedItem();

            if (selectedItem == null) {

                throw new IllegalArgumentException(
                        "Please select a report type."
                );
            }

            String reportType
                    = selectedItem.toString();

            /*
             * Load the latest information before creating PDF.
             */
            updateSummary(
                    fromDate,
                    toDate
            );

            switch (reportType) {

                case "Appointment Summary" ->

                    loadAppointmentReport(
                            fromDate,
                            toDate
                    );

                case "Revenue by Treatment" ->

                    loadRevenueReport(
                            fromDate,
                            toDate
                    );

                case "Dentist Workload" ->

                    loadDentistReport(
                            fromDate,
                            toDate
                    );

                case "Payment Method Summary" ->

                    loadPaymentReport(
                            fromDate,
                            toDate
                    );

                default ->

                    throw new IllegalArgumentException(
                            "Invalid report type."
                    );
            }

            // ==================================================
            // EMPTY REPORT CHECK
            // ==================================================
            if (tblReport.getRowCount() == 0) {

                throw new IllegalArgumentException(
                        "There is no report data "
                        + "for the selected date range."
                );
            }

            // ==================================================
            // GENERATE PDF
            // ==================================================
            File pdfFile
                    = ReportPdfGenerator
                            .generateReport(
                                    reportType,
                                    fromDate,
                                    toDate,
                                    tblReport
                            );

            // ==================================================
            // OPEN PDF IN DEFAULT BROWSER
            // ==================================================
            PdfOpener.open(
                    pdfFile
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Report generated successfully.\n\n"
                    + "Saved to:\n"
                    + pdfFile.getAbsolutePath(),
                    "Report Generated",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (IllegalArgumentException e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Report Error",
                    JOptionPane.WARNING_MESSAGE
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to generate PDF report.\n"
                    + e.getMessage(),
                    "PDF Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // ==========================================================
    // UPDATE SUMMARY
    // ==========================================================
    private void updateSummary(
            LocalDate fromDate,
            LocalDate toDate) {

        ReportSummary summary
                = reportService.getSummary(
                        fromDate,
                        toDate
                );

        lblTotalValue.setText(
                String.valueOf(
                        summary.getTotalAppointments()
                )
        );

        lblCompletedValue.setText(
                String.valueOf(
                        summary.getCompletedAppointments()
                )
        );

        lblPendingValue.setText(
                String.valueOf(
                        summary.getPendingAppointments()
                )
        );

        lblRevenueValue.setText(
                "Rs. "
                + summary
                        .getTotalRevenue()
                        .toPlainString()
        );
    }

    // ==========================================================
    // APPOINTMENT REPORT
    // ==========================================================
    private void loadAppointmentReport(
            LocalDate fromDate,
            LocalDate toDate) {

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
                reportService
                        .getAppointmentReport(
                                fromDate,
                                toDate
                        )
        );
    }

    // ==========================================================
    // REVENUE REPORT
    // ==========================================================
    private void loadRevenueReport(
            LocalDate fromDate,
            LocalDate toDate) {

        tableModel.setColumnIdentifiers(
                new Object[]{
                    "Treatment",
                    "Bills",
                    "Revenue"
                }
        );

        populateTable(
                reportService
                        .getRevenueByTreatment(
                                fromDate,
                                toDate
                        )
        );
    }

    // ==========================================================
    // DENTIST WORKLOAD REPORT
    // ==========================================================
    private void loadDentistReport(
            LocalDate fromDate,
            LocalDate toDate) {

        tableModel.setColumnIdentifiers(
                new Object[]{
                    "Dentist",
                    "Total",
                    "Completed",
                    "Pending",
                    "Cancelled"
                }
        );

        populateTable(
                reportService
                        .getDentistWorkload(
                                fromDate,
                                toDate
                        )
        );
    }

    // ==========================================================
    // PAYMENT METHOD REPORT
    // ==========================================================
    private void loadPaymentReport(
            LocalDate fromDate,
            LocalDate toDate) {

        tableModel.setColumnIdentifiers(
                new Object[]{
                    "Payment Method",
                    "Payments",
                    "Amount"
                }
        );

        populateTable(
                reportService
                        .getPaymentMethodReport(
                                fromDate,
                                toDate
                        )
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

        if (rows == null) {

            return;
        }

        for (Object[] row : rows) {

            tableModel.addRow(
                    row
            );
        }
    }

    // ==========================================================
    // CLEAR REPORT TABLE
    // ==========================================================
    private void clearReportTable() {

        if (tableModel != null) {

            tableModel.setRowCount(
                    0
            );
        }
    }

    // ==========================================================
    // DEFAULT DATE RANGE
    // ==========================================================
    private void setDefaultDates() {

        LocalDate today
                = LocalDate.now();

        LocalDate firstDayOfMonth
                = today.withDayOfMonth(
                        1
                );

        datePickerFrom.setDate(
                firstDayOfMonth
        );

        datePickerTo.setDate(
                today
        );
    }

    // ==========================================================
    // RESET REPORT
    // ==========================================================
    private void resetReport() {

        cmbReportType.setSelectedIndex(
                0
        );

        setDefaultDates();

        /*
         * Reset only reloads the default report.
         * It does not create/open a PDF.
         */
        loadReportData();
    }

    // ==========================================================
    // PRINT REPORT
    // ==========================================================
    private void printReport() {

        if (tblReport.getRowCount() == 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "There is no report data to print.",
                    "Print Report",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        try {

            Object selectedItem
                    = cmbReportType
                            .getSelectedItem();

            if (selectedItem == null) {

                throw new IllegalArgumentException(
                        "Please select a report type."
                );
            }

            String reportType
                    = selectedItem.toString();

            LocalDate fromDate
                    = datePickerFrom.getDate();

            LocalDate toDate
                    = datePickerTo.getDate();

            String headerText
                    = "Sunrise Dental Clinic - "
                    + reportType
                    + " ("
                    + fromDate
                    + " to "
                    + toDate
                    + ")";

            boolean printed
                    = tblReport.print(
                            JTable.PrintMode.FIT_WIDTH,
                            new MessageFormat(
                                    headerText
                            ),
                            new MessageFormat(
                                    "Page {0}"
                            )
                    );

            if (printed) {

                JOptionPane.showMessageDialog(
                        this,
                        "Report sent to printer successfully.",
                        "Print Report",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }

        } catch (IllegalArgumentException e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Print Error",
                    JOptionPane.WARNING_MESSAGE
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to print report.\n"
                    + e.getMessage(),
                    "Print Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}