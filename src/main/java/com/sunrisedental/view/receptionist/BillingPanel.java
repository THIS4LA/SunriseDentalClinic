package com.sunrisedental.view.receptionist;

import com.sunrisedental.model.Appointment;
import com.sunrisedental.model.Bill;
import com.sunrisedental.model.Dentist;
import com.sunrisedental.model.Treatment;
import com.sunrisedental.service.BillingService;
import com.sunrisedental.model.Patient;
import com.sunrisedental.service.DentistService;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import java.math.BigDecimal;

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
import javax.swing.table.DefaultTableModel;

public class BillingPanel extends JPanel {

    private final BillingService billingService;
    private final DentistService dentistService;

    private Appointment currentAppointment;
    private Patient currentPatient;
    private Dentist currentDentist;
    private Treatment currentTreatment;

    private JTextField txtSearchAppointment;
    private JTextField txtBillNo;

    private JTextField txtPatient;
    private JTextField txtDentist;
    private JTextField txtTreatment;
    private JTextField txtAppointmentDate;

    private JTextField txtConsultationFee;
    private JTextField txtTreatmentFee;
    private JTextField txtDiscount;
    private JTextField txtTotal;

    private JComboBox<String> cmbPaymentMethod;
    private JComboBox<String> cmbPaymentStatus;

    private JButton btnFind;
    private JButton btnCalculate;
    private JButton btnSave;
    private JButton btnPrint;
    private JButton btnClear;

    private JTable tblBills;
    private DefaultTableModel tableModel;

    public BillingPanel() {

        billingService
                = new BillingService();

        dentistService
                = new DentistService();

        initUI();

        clearForm();

        loadBills();
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
                                1
                        )
                );

        panel.setOpaque(false);

        JLabel lblTitle
                = new JLabel(
                        "Billing Management"
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
                        "Generate and manage patient bills"
                );

        lblDescription.setForeground(
                new Color(
                        100,
                        100,
                        100
                )
        );

        panel.add(lblTitle);
        panel.add(lblDescription);

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
                createBillingSection(),
                BorderLayout.NORTH
        );

        main.add(
                createBillsTable(),
                BorderLayout.CENTER
        );

        return main;
    }

    private JPanel createBillingSection() {

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
                                20,
                                20,
                                20,
                                20
                        )
                )
        );

        // ==========================================================
        // SEARCH SECTION
        // ==========================================================
        JPanel searchPanel
                = new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                10,
                                0
                        )
                );

        searchPanel.setOpaque(false);

        JLabel lblAppointmentNo
                = new JLabel(
                        "Appointment No"
                );

        lblAppointmentNo.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        txtSearchAppointment
                = new JTextField();

        txtSearchAppointment.setPreferredSize(
                new Dimension(
                        220,
                        32
                )
        );

        btnFind
                = new JButton(
                        "Find Appointment"
                );

        btnFind.setPreferredSize(
                new Dimension(
                        150,
                        32
                )
        );

        searchPanel.add(
                lblAppointmentNo
        );

        searchPanel.add(
                txtSearchAppointment
        );

        searchPanel.add(
                btnFind
        );

        container.add(
                searchPanel,
                BorderLayout.NORTH
        );

        // ==========================================================
        // CREATE FIELDS
        // ==========================================================
        txtBillNo
                = createReadOnlyField();

        txtPatient
                = createReadOnlyField();

        txtDentist
                = createReadOnlyField();

        txtTreatment
                = createReadOnlyField();

        txtAppointmentDate
                = createReadOnlyField();

        txtConsultationFee
                = createReadOnlyField();

        txtTreatmentFee
                = createReadOnlyField();

        txtDiscount
                = new JTextField(
                        "0.00"
                );

        txtTotal
                = createReadOnlyField();

        cmbPaymentMethod
                = new JComboBox<>(
                        new String[]{
                            "Select Payment Method",
                            "CASH",
                            "CARD",
                            "BANK TRANSFER"
                        }
                );

        cmbPaymentStatus
                = new JComboBox<>(
                        new String[]{
                            "PAID",
                            "PENDING"
                        }
                );

        // ==========================================================
        // LEFT SIDE
        // ==========================================================
        JPanel pnlAppointmentDetails
                = new JPanel(
                        new java.awt.GridBagLayout()
                );

        pnlAppointmentDetails.setOpaque(
                false
        );

        pnlAppointmentDetails.setBorder(
                BorderFactory.createTitledBorder(
                        "Appointment / Patient Details"
                )
        );

        addFormRow(
                pnlAppointmentDetails,
                0,
                "Bill No",
                txtBillNo
        );

        addFormRow(
                pnlAppointmentDetails,
                1,
                "Patient",
                txtPatient
        );

        addFormRow(
                pnlAppointmentDetails,
                2,
                "Dentist",
                txtDentist
        );

        addFormRow(
                pnlAppointmentDetails,
                3,
                "Treatment",
                txtTreatment
        );

        addFormRow(
                pnlAppointmentDetails,
                4,
                "Appointment",
                txtAppointmentDate
        );

        // ==========================================================
        // RIGHT SIDE
        // ==========================================================
        JPanel pnlBillDetails
                = new JPanel(
                        new java.awt.GridBagLayout()
                );

        pnlBillDetails.setOpaque(
                false
        );

        pnlBillDetails.setBorder(
                BorderFactory.createTitledBorder(
                        "Bill Details"
                )
        );

        addFormRow(
                pnlBillDetails,
                0,
                "Consultation Fee",
                txtConsultationFee
        );

        addFormRow(
                pnlBillDetails,
                1,
                "Treatment Fee",
                txtTreatmentFee
        );

        addFormRow(
                pnlBillDetails,
                2,
                "Discount",
                txtDiscount
        );

        addFormRow(
                pnlBillDetails,
                3,
                "Total Amount",
                txtTotal
        );

        addFormRow(
                pnlBillDetails,
                4,
                "Payment Method",
                cmbPaymentMethod
        );

        addFormRow(
                pnlBillDetails,
                5,
                "Payment Status",
                cmbPaymentStatus
        );

        // ==========================================================
        // TWO COLUMN LAYOUT
        // ==========================================================
        JPanel pnlDetails
                = new JPanel(
                        new GridLayout(
                                1,
                                2,
                                25,
                                0
                        )
                );

        pnlDetails.setOpaque(
                false
        );

        pnlDetails.setBorder(
                BorderFactory.createEmptyBorder(
                        5,
                        0,
                        5,
                        0
                )
        );

        pnlDetails.add(
                pnlAppointmentDetails
        );

        pnlDetails.add(
                pnlBillDetails
        );

        container.add(
                pnlDetails,
                BorderLayout.CENTER
        );

        // ==========================================================
        // BUTTON SECTION
        // ==========================================================
        JPanel buttons
                = new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                10,
                                0
                        )
                );

        buttons.setOpaque(
                false
        );

        btnCalculate
                = new JButton(
                        "Calculate"
                );

        btnSave
                = new JButton(
                        "Save Bill"
                );

        btnPrint
                = new JButton(
                        "Print Receipt"
                );

        btnClear
                = new JButton(
                        "Clear"
                );

        Dimension buttonSize
                = new Dimension(
                        130,
                        35
                );

        btnCalculate.setPreferredSize(
                buttonSize
        );

        btnSave.setPreferredSize(
                buttonSize
        );

        btnPrint.setPreferredSize(
                buttonSize
        );

        btnClear.setPreferredSize(
                buttonSize
        );

        buttons.add(
                btnCalculate
        );

        buttons.add(
                btnSave
        );

        buttons.add(
                btnPrint
        );

        buttons.add(
                btnClear
        );

        container.add(
                buttons,
                BorderLayout.SOUTH
        );

        // ==========================================================
        // EVENTS
        // ==========================================================
        btnFind.addActionListener(
                e -> findAppointment()
        );

        btnCalculate.addActionListener(
                e -> calculateTotal()
        );

        btnSave.addActionListener(
                e -> saveBill()
        );

        btnPrint.addActionListener(
                e -> printReceipt()
        );

        btnClear.addActionListener(
                e -> clearForm()
        );

        return container;
    }

    private JTextField createReadOnlyField() {

        JTextField field
                = new JTextField();

        field.setEditable(false);

        field.setBackground(
                new Color(
                        245,
                        245,
                        245
                )
        );

        return field;
    }

    private JPanel createBillsTable() {

        JPanel panel
                = new JPanel(
                        new BorderLayout()
                );

        panel.setBackground(
                Color.WHITE
        );

        panel.setBorder(
                BorderFactory.createEmptyBorder(
                        10,
                        10,
                        10,
                        10
                )
        );

        JLabel title
                = new JLabel(
                        "Recent Bills"
                );

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        16
                )
        );

        panel.add(
                title,
                BorderLayout.NORTH
        );

        tableModel
                = new DefaultTableModel(
                        new Object[]{
                            "Bill No",
                            "Appointment",
                            "Total",
                            "Payment Method",
                            "Status",
                            "Created"
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

        tblBills
                = new JTable(
                        tableModel
                );

        tblBills.setRowHeight(
                28
        );

        panel.add(
                new JScrollPane(
                        tblBills
                ),
                BorderLayout.CENTER
        );

        return panel;
    }

    private void addFormRow(
            JPanel panel,
            int row,
            String labelText,
            java.awt.Component component) {

        java.awt.GridBagConstraints gbc
                = new java.awt.GridBagConstraints();

        gbc.gridy = row;

        gbc.insets
                = new java.awt.Insets(
                        7,
                        8,
                        7,
                        8
                );

        gbc.anchor
                = java.awt.GridBagConstraints.WEST;

        // Label
        gbc.gridx = 0;

        gbc.weightx = 0;

        gbc.fill
                = java.awt.GridBagConstraints.NONE;

        JLabel label
                = new JLabel(
                        labelText
                );

        label.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        panel.add(
                label,
                gbc
        );

        // Input
        gbc.gridx = 1;

        gbc.weightx = 1.0;

        gbc.fill
                = java.awt.GridBagConstraints.HORIZONTAL;

        component.setPreferredSize(
                new Dimension(
                        230,
                        32
                )
        );

        panel.add(
                component,
                gbc
        );
    }

    private void findAppointment() {

        try {

            currentAppointment
                    = billingService.findByAppointmentNo(
                            txtSearchAppointment
                                    .getText()
                                    .trim()
                    );

            currentPatient
                    = billingService.getPatient(
                            currentAppointment
                    );

            currentDentist
                    = billingService.getDentist(
                            currentAppointment
                    );

            currentTreatment
                    = billingService.getTreatment(
                            currentAppointment
                    );

            txtPatient.setText(
                    currentPatient.getName()
            );

            txtDentist.setText(
                    currentDentist.getName()
            );

            txtTreatment.setText(
                    currentAppointment
                            .getTreatmentType()
            );

            txtAppointmentDate.setText(
                    currentAppointment
                            .getAppointmentDate()
                    + " "
                    + currentAppointment
                            .getAppointmentTime()
            );

            txtConsultationFee.setText(
                    currentTreatment
                            .getConsultationFee()
                            .toPlainString()
            );

            txtTreatmentFee.setText(
                    currentTreatment
                            .getTreatmentFee()
                            .toPlainString()
            );

            txtBillNo.setText(
                    billingService
                            .generateBillNumber()
            );

            calculateTotal();

        } catch (IllegalArgumentException e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Billing",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }

    private void calculateTotal() {

        if (currentTreatment == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please find an appointment first."
            );

            return;
        }

        try {

            BigDecimal discount
                    = new BigDecimal(
                            txtDiscount
                                    .getText()
                                    .trim()
                    );

            BigDecimal total
                    = billingService
                            .calculateTotal(
                                    currentTreatment
                                            .getConsultationFee(),
                                    currentTreatment
                                            .getTreatmentFee(),
                                    discount
                            );

            txtTotal.setText(
                    total.toPlainString()
            );

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid discount."
            );

        } catch (IllegalArgumentException e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage()
            );
        }
    }

    private void saveBill() {

        if (currentAppointment == null
                || currentTreatment == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please find an appointment first."
            );

            return;
        }

        try {

            calculateTotal();

            BigDecimal discount
                    = new BigDecimal(
                            txtDiscount
                                    .getText()
                                    .trim()
                    );

            BigDecimal total
                    = new BigDecimal(
                            txtTotal
                                    .getText()
                                    .trim()
                    );

            Bill bill
                    = new Bill(
                            txtBillNo.getText(),
                            currentAppointment
                                    .getAppointmentNo(),
                            currentTreatment
                                    .getConsultationFee(),
                            currentTreatment
                                    .getTreatmentFee(),
                            discount,
                            total,
                            cmbPaymentMethod
                                    .getSelectedItem()
                                    .toString(),
                            cmbPaymentStatus
                                    .getSelectedItem()
                                    .toString()
                    );

            if (billingService
                    .saveBill(bill)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Bill saved successfully."
                );

                loadBills();
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Billing Error",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }

    private void loadBills() {

        List<Bill> bills
                = billingService
                        .getAllBills();

        tableModel.setRowCount(0);

        for (Bill bill : bills) {

            tableModel.addRow(
                    new Object[]{
                        bill.getBillNo(),
                        bill.getAppointmentNo(),
                        bill.getTotalAmount(),
                        bill.getPaymentMethod(),
                        bill.getPaymentStatus(),
                        bill.getCreatedAt()
                    }
            );
        }
    }

    private void printReceipt() {

        if (currentAppointment == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please load a bill first."
            );

            return;
        }

        String receipt
                = "SUNRISE DENTAL CLINIC\n"
                + "------------------------------\n"
                + "Bill No: "
                + txtBillNo.getText()
                + "\n"
                + "Appointment: "
                + currentAppointment
                        .getAppointmentNo()
                + "\n"
                + "Patient: "
                + txtPatient.getText()
                + "\n"
                + "Dentist: "
                + txtDentist.getText()
                + "\n"
                + "Treatment: "
                + txtTreatment.getText()
                + "\n"
                + "------------------------------\n"
                + "Consultation: Rs. "
                + txtConsultationFee.getText()
                + "\n"
                + "Treatment: Rs. "
                + txtTreatmentFee.getText()
                + "\n"
                + "Discount: Rs. "
                + txtDiscount.getText()
                + "\n"
                + "TOTAL: Rs. "
                + txtTotal.getText()
                + "\n"
                + "Payment: "
                + cmbPaymentMethod
                        .getSelectedItem()
                + "\n"
                + "------------------------------\n"
                + "Thank you.";

        JTextArea textArea
                = new JTextArea(
                        receipt
                );

        try {

            textArea.print();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to print receipt."
            );
        }
    }

    private String getDentistNameById(
            int dentistId) {

        try {

            Dentist dentist
                    = dentistService
                            .getDentistById(
                                    dentistId
                            );

            return dentist.getName();

        } catch (IllegalArgumentException e) {

            return "Unknown Dentist";
        }
    }

    private void clearForm() {

        currentAppointment = null;
        currentPatient = null;
        currentTreatment = null;
        currentDentist = null;

        txtSearchAppointment.setText("");

        txtBillNo.setText("");

        txtPatient.setText("");
        txtDentist.setText("");
        txtTreatment.setText("");
        txtAppointmentDate.setText("");

        txtConsultationFee.setText("");
        txtTreatmentFee.setText("");

        txtDiscount.setText(
                "0.00"
        );

        txtTotal.setText("");

        cmbPaymentMethod.setSelectedIndex(
                0
        );

        cmbPaymentStatus.setSelectedIndex(
                0
        );
    }
}
