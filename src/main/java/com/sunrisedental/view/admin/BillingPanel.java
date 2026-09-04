package com.sunrisedental.view.admin;

import com.sunrisedental.model.Bill;
import com.sunrisedental.model.Dentist;
import com.sunrisedental.model.Patient;

import com.sunrisedental.service.BillingService;
import com.sunrisedental.service.DentistService;
import com.sunrisedental.service.PatientService;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import java.text.MessageFormat;

import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import javax.swing.table.DefaultTableModel;

public class BillingPanel
        extends JPanel {

    private final BillingService billingService;
    private final PatientService patientService;
    private final DentistService dentistService;

    private int selectedBillId =
            -1;

    private JTextField txtBillId;
    private JTextField txtBillNo;
    private JTextField txtAppointmentNo;
    private JTextField txtPatient;
    private JTextField txtDentist;
    private JTextField txtTreatment;

    private JTextField txtConsultationFee;
    private JTextField txtTreatmentFee;
    private JTextField txtDiscount;
    private JTextField txtTotalAmount;

    private JComboBox<String> cmbPaymentMethod;
    private JComboBox<String> cmbPaymentStatus;

    private JTextField txtSearch;

    private JButton btnUpdatePayment;
    private JButton btnPrint;
    private JButton btnClear;

    private JButton btnSearch;
    private JButton btnRefresh;

    private JTable tblBills;
    private DefaultTableModel tableModel;

    public BillingPanel() {

        billingService =
                new BillingService();

        patientService =
                new PatientService();

        dentistService =
                new DentistService();

        initUI();

        loadBills();
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
                        "Billing Management"
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
                        "View and manage clinic billing and payment information"
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
                createBillingDetailsPanel(),
                BorderLayout.NORTH
        );

        main.add(
                createBillingTablePanel(),
                BorderLayout.CENTER
        );

        return main;
    }

    // ==========================================================
    // BILL DETAILS
    // ==========================================================

    private JPanel createBillingDetailsPanel() {

        JPanel container =
                new JPanel(
                        new BorderLayout(
                                0,
                                18
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

        JLabel lblTitle =
                new JLabel(
                        "Bill Details"
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

        // ======================================================
        // FIELDS
        // ======================================================

        txtBillId =
                createReadOnlyField();

        txtBillNo =
                createReadOnlyField();

        txtAppointmentNo =
                createReadOnlyField();

        txtPatient =
                createReadOnlyField();

        txtDentist =
                createReadOnlyField();

        txtTreatment =
                createReadOnlyField();

        txtConsultationFee =
                createReadOnlyField();

        txtTreatmentFee =
                createReadOnlyField();

        txtDiscount =
                createReadOnlyField();

        txtTotalAmount =
                createReadOnlyField();

        cmbPaymentMethod =
                new JComboBox<>(
                        new String[]{
                            "CASH",
                            "CARD"
                        }
                );

        cmbPaymentStatus =
                new JComboBox<>(
                        new String[]{
                            "PENDING",
                            "PAID"
                        }
                );

        // ======================================================
        // FORM
        // ======================================================

        JPanel form =
                new JPanel(
                        new GridBagLayout()
                );

        form.setOpaque(
                false
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(
                        7,
                        8,
                        7,
                        8
                );

        gbc.anchor =
                GridBagConstraints.WEST;

        // ======================================================
        // ROW 1
        // ======================================================

        addFormField(
                form,
                gbc,
                0,
                0,
                "Bill ID",
                txtBillId
        );

        addFormField(
                form,
                gbc,
                0,
                2,
                "Bill No",
                txtBillNo
        );

        // ======================================================
        // ROW 2
        // ======================================================

        addFormField(
                form,
                gbc,
                1,
                0,
                "Appointment No",
                txtAppointmentNo
        );

        addFormField(
                form,
                gbc,
                1,
                2,
                "Patient",
                txtPatient
        );

        // ======================================================
        // ROW 3
        // ======================================================

        addFormField(
                form,
                gbc,
                2,
                0,
                "Dentist",
                txtDentist
        );

        addFormField(
                form,
                gbc,
                2,
                2,
                "Treatment",
                txtTreatment
        );

        // ======================================================
        // ROW 4
        // ======================================================

        addFormField(
                form,
                gbc,
                3,
                0,
                "Consultation Fee",
                txtConsultationFee
        );

        addFormField(
                form,
                gbc,
                3,
                2,
                "Treatment Fee",
                txtTreatmentFee
        );

        // ======================================================
        // ROW 5
        // ======================================================

        addFormField(
                form,
                gbc,
                4,
                0,
                "Discount",
                txtDiscount
        );

        addFormField(
                form,
                gbc,
                4,
                2,
                "Total Amount",
                txtTotalAmount
        );

        // ======================================================
        // ROW 6
        // ======================================================

        addFormField(
                form,
                gbc,
                5,
                0,
                "Payment Method",
                cmbPaymentMethod
        );

        addFormField(
                form,
                gbc,
                5,
                2,
                "Payment Status",
                cmbPaymentStatus
        );

        container.add(
                form,
                BorderLayout.CENTER
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

        btnUpdatePayment =
                createPrimaryButton(
                        "Update Payment"
                );

        btnPrint =
                createSecondaryButton(
                        "Print Receipt"
                );

        btnClear =
                createSecondaryButton(
                        "Clear"
                );

        buttons.add(
                btnUpdatePayment
        );

        buttons.add(
                btnPrint
        );

        buttons.add(
                btnClear
        );

        btnUpdatePayment.addActionListener(
                e -> updatePayment()
        );

        btnPrint.addActionListener(
                e -> printReceipt()
        );

        btnClear.addActionListener(
                e -> clearForm()
        );

        container.add(
                buttons,
                BorderLayout.SOUTH
        );

        return container;
    }

    // ==========================================================
    // FORM FIELD HELPER
    // ==========================================================

    private void addFormField(
            JPanel panel,
            GridBagConstraints gbc,
            int row,
            int column,
            String labelText,
            Component component) {

        JLabel label =
                new JLabel(
                        labelText
                );

        label.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        // ======================================================
        // LABEL
        // ======================================================

        gbc.gridx =
                column;

        gbc.gridy =
                row;

        gbc.weightx =
                0;

        gbc.fill =
                GridBagConstraints.NONE;

        gbc.anchor =
                GridBagConstraints.WEST;

        panel.add(
                label,
                gbc
        );

        // ======================================================
        // COMPONENT
        // ======================================================

        gbc.gridx =
                column + 1;

        gbc.gridy =
                row;

        gbc.weightx =
                1.0;

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        component.setPreferredSize(
                new Dimension(
                        260,
                        32
                )
        );

        panel.add(
                component,
                gbc
        );
    }

    // ==========================================================
    // BILL TABLE
    // ==========================================================

    private JPanel createBillingTablePanel() {

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

        // ======================================================
        // SEARCH HEADER
        // ======================================================

        JPanel search =
                new JPanel(
                        new BorderLayout()
                );

        search.setOpaque(
                false
        );

        JLabel lblTitle =
                new JLabel(
                        "Bills"
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
                "Search by bill number, appointment, treatment or payment status"
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

        // ======================================================
        // TABLE MODEL
        // ======================================================

        tableModel =
                new DefaultTableModel(
                        new Object[]{
                            "Bill ID",
                            "Bill No",
                            "Appointment No",
                            "Patient",
                            "Dentist",
                            "Treatment",
                            "Total",
                            "Payment Method",
                            "Payment Status"
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

        tblBills =
                new JTable(
                        tableModel
                );

        tblBills.setRowHeight(
                28
        );

        tblBills.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        tblBills
                .getTableHeader()
                .setFont(
                        new Font(
                                "Segoe UI",
                                Font.BOLD,
                                13
                        )
                );

        JScrollPane tableScroll =
                new JScrollPane(
                        tblBills
                );

        container.add(
                search,
                BorderLayout.NORTH
        );

        container.add(
                tableScroll,
                BorderLayout.CENTER
        );

        // ======================================================
        // LISTENERS
        // ======================================================

        btnSearch.addActionListener(
                e -> searchBills()
        );

        txtSearch.addActionListener(
                e -> searchBills()
        );

        btnRefresh.addActionListener(
                e -> {

                    txtSearch.setText(
                            ""
                    );

                    clearForm();

                    loadBills();
                }
        );

        tblBills
                .getSelectionModel()
                .addListSelectionListener(
                        e -> {

                            if (!e.getValueIsAdjusting()) {

                                loadSelectedBill();
                            }
                        }
                );

        return container;
    }

    // ==========================================================
    // LOAD BILLS
    // ==========================================================

    private void loadBills() {

        List<Bill> bills =
                billingService
                        .getAllBills();

        populateTable(
                bills
        );
    }

    // ==========================================================
    // SEARCH BILLS
    // ==========================================================

    private void searchBills() {

        String keyword =
                txtSearch
                        .getText()
                        .trim();

        List<Bill> bills =
                billingService
                        .searchBills(
                                keyword
                        );

        populateTable(
                bills
        );
    }

    // ==========================================================
    // POPULATE TABLE
    // ==========================================================

    private void populateTable(
            List<Bill> bills) {

        tableModel.setRowCount(
                0
        );

        for (Bill bill : bills) {

            tableModel.addRow(
                    new Object[]{
                        bill.getBillId(),

                        bill.getBillNo(),

                        bill.getAppointmentNo(),

                        getPatientName(
                                bill.getPatientId()
                        ),

                        getDentistName(
                                bill.getDentistId()
                        ),

                        bill.getTreatmentName(),

                        bill.getTotalAmount(),

                        bill.getPaymentMethod(),

                        bill.getPaymentStatus()
                    }
            );
        }
    }

    // ==========================================================
    // LOAD SELECTED BILL
    // ==========================================================

    private void loadSelectedBill() {

        int row =
                tblBills
                        .getSelectedRow();

        if (row == -1) {

            return;
        }

        selectedBillId =
                Integer.parseInt(
                        tableModel
                                .getValueAt(
                                        row,
                                        0
                                )
                                .toString()
                );

        try {

            Bill bill =
                    billingService
                            .getBillById(
                                    selectedBillId
                            );

            if (bill == null) {

                return;
            }

            txtBillId.setText(
                    String.valueOf(
                            bill.getBillId()
                    )
            );

            txtBillNo.setText(
                    safeValue(
                            bill.getBillNo()
                    )
            );

            txtAppointmentNo.setText(
                    safeValue(
                            bill.getAppointmentNo()
                    )
            );

            txtPatient.setText(
                    getPatientName(
                            bill.getPatientId()
                    )
            );

            txtDentist.setText(
                    getDentistName(
                            bill.getDentistId()
                    )
            );

            txtTreatment.setText(
                    safeValue(
                            bill.getTreatmentName()
                    )
            );

            txtConsultationFee.setText(
                    safeValue(
                            bill.getConsultationFee()
                    )
            );

            txtTreatmentFee.setText(
                    safeValue(
                            bill.getTreatmentFee()
                    )
            );

            txtDiscount.setText(
                    safeValue(
                            bill.getDiscount()
                    )
            );

            txtTotalAmount.setText(
                    safeValue(
                            bill.getTotalAmount()
                    )
            );

            cmbPaymentMethod.setSelectedItem(
                    bill.getPaymentMethod()
            );

            cmbPaymentStatus.setSelectedItem(
                    bill.getPaymentStatus()
            );

        } catch (IllegalArgumentException e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Billing Error",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }

    // ==========================================================
    // UPDATE PAYMENT
    // ==========================================================

    private void updatePayment() {

        try {

            if (selectedBillId <= 0) {

                throw new IllegalArgumentException(
                        "Please select a bill first."
                );
            }

            String paymentMethod =
                    cmbPaymentMethod
                            .getSelectedItem()
                            .toString();

            String paymentStatus =
                    cmbPaymentStatus
                            .getSelectedItem()
                            .toString();

            int result =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Update payment information for this bill?",
                            "Update Payment",
                            JOptionPane.YES_NO_OPTION
                    );

            if (result
                    != JOptionPane.YES_OPTION) {

                return;
            }

            boolean success =
                    billingService
                            .updatePaymentDetails(
                                    selectedBillId,
                                    paymentMethod,
                                    paymentStatus
                            );

            if (success) {

                JOptionPane.showMessageDialog(
                        this,
                        "Payment information updated successfully.",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );

                clearForm();

                loadBills();
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
    // PRINT RECEIPT
    // ==========================================================

    private void printReceipt() {

        if (selectedBillId <= 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a bill first."
            );

            return;
        }

        try {

            String billNo =
                    txtBillNo
                            .getText()
                            .trim();

            MessageFormat header =
                    new MessageFormat(
                            "Sunrise Dental Clinic - Bill "
                            + billNo
                    );

            MessageFormat footer =
                    new MessageFormat(
                            "Page {0}"
                    );

            boolean printed =
                    tblBills.print(
                            JTable.PrintMode.FIT_WIDTH,
                            header,
                            footer
                    );

            if (printed) {

                JOptionPane.showMessageDialog(
                        this,
                        "Print operation completed."
                );
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to print bill.",
                    "Print Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // ==========================================================
    // PATIENT NAME
    // ==========================================================

    private String getPatientName(
            int patientId) {

        try {

            if (patientId <= 0) {

                return "Unknown Patient";
            }

            Patient patient =
                    patientService
                            .getPatientById(
                                    patientId
                            );

            if (patient == null) {

                return "Unknown Patient";
            }

            return patient.getName();

        } catch (Exception e) {

            return "Unknown Patient";
        }
    }

    // ==========================================================
    // DENTIST NAME
    // ==========================================================

    private String getDentistName(
            int dentistId) {

        try {

            if (dentistId <= 0) {

                return "Unknown Dentist";
            }

            Dentist dentist =
                    dentistService
                            .getDentistById(
                                    dentistId
                            );

            if (dentist == null) {

                return "Unknown Dentist";
            }

            return dentist.getName();

        } catch (Exception e) {

            return "Unknown Dentist";
        }
    }

    // ==========================================================
    // CLEAR FORM
    // ==========================================================

    private void clearForm() {

        selectedBillId =
                -1;

        txtBillId.setText(
                ""
        );

        txtBillNo.setText(
                ""
        );

        txtAppointmentNo.setText(
                ""
        );

        txtPatient.setText(
                ""
        );

        txtDentist.setText(
                ""
        );

        txtTreatment.setText(
                ""
        );

        txtConsultationFee.setText(
                ""
        );

        txtTreatmentFee.setText(
                ""
        );

        txtDiscount.setText(
                ""
        );

        txtTotalAmount.setText(
                ""
        );

        cmbPaymentMethod.setSelectedItem(
                "CASH"
        );

        cmbPaymentStatus.setSelectedItem(
                "PENDING"
        );

        tblBills.clearSelection();
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
    // READ ONLY FIELD
    // ==========================================================

    private JTextField createReadOnlyField() {

        JTextField field =
                new JTextField();

        field.setEditable(
                false
        );

        field.setBackground(
                new Color(
                        245,
                        245,
                        245
                )
        );

        field.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        return field;
    }

    // ==========================================================
    // PRIMARY BUTTON
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

    // ==========================================================
    // SECONDARY BUTTON
    // ==========================================================

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