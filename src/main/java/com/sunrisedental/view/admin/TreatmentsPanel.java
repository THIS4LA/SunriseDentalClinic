package com.sunrisedental.view.admin;

import com.sunrisedental.model.Treatment;
import com.sunrisedental.service.TreatmentService;

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
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import javax.swing.table.DefaultTableModel;

public class TreatmentsPanel
        extends JPanel {

    private final TreatmentService treatmentService;

    private int selectedTreatmentId
            = -1;

    private JTextField txtTreatmentId;
    private JTextField txtTreatmentName;
    private JTextField txtConsultationFee;
    private JTextField txtTreatmentFee;

    private JComboBox<String> cmbStatus;

    private JTextField txtSearch;

    private JButton btnSave;
    private JButton btnUpdate;
    private JButton btnActivate;
    private JButton btnDeactivate;
    private JButton btnClear;
    private JButton btnSearch;
    private JButton btnRefresh;

    private JTable tblTreatments;
    private DefaultTableModel tableModel;

    public TreatmentsPanel() {

        treatmentService
                = new TreatmentService();

        initUI();

        loadTreatments();
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
                        "Treatment Management"
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
                        "Manage treatment types and clinic fees"
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
                createTreatmentForm(),
                BorderLayout.NORTH
        );

        main.add(
                createTreatmentTable(),
                BorderLayout.CENTER
        );

        return main;
    }

    // ==========================================================
    // TREATMENT FORM
    // ==========================================================
    private JPanel createTreatmentForm() {

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
                                15,
                                15,
                                15,
                                15
                        )
                )
        );

        JLabel lblTitle
                = new JLabel(
                        "Treatment Details"
                );

        lblTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        17
                )
        );

        JPanel fields
                = new JPanel(
                        new GridLayout(
                                3,
                                4,
                                15,
                                10
                        )
                );

        fields.setOpaque(
                false
        );

        txtTreatmentId
                = new JTextField();

        txtTreatmentId.setEditable(
                false
        );

        txtTreatmentId.setBackground(
                new Color(
                        245,
                        245,
                        245
                )
        );

        txtTreatmentName
                = new JTextField();

        txtConsultationFee
                = new JTextField();

        txtTreatmentFee
                = new JTextField();

        cmbStatus
                = new JComboBox<>(
                        new String[]{
                            "ACTIVE",
                            "INACTIVE"
                        }
                );

        fields.add(
                new JLabel(
                        "Treatment ID"
                )
        );

        fields.add(
                txtTreatmentId
        );

        fields.add(
                new JLabel(
                        "Treatment Name"
                )
        );

        fields.add(
                txtTreatmentName
        );

        fields.add(
                new JLabel(
                        "Consultation Fee"
                )
        );

        fields.add(
                txtConsultationFee
        );

        fields.add(
                new JLabel(
                        "Treatment Fee"
                )
        );

        fields.add(
                txtTreatmentFee
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
                new JLabel()
        );

        fields.add(
                new JLabel()
        );

        // ======================================================
        // BUTTONS
        // ======================================================
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

        btnSave
                = createPrimaryButton(
                        "Add Treatment"
                );

        btnUpdate
                = createSecondaryButton(
                        "Update"
                );

        btnActivate
                = createSecondaryButton(
                        "Activate"
                );

        btnDeactivate
                = createSecondaryButton(
                        "Deactivate"
                );

        btnClear
                = createSecondaryButton(
                        "Clear"
                );

        buttons.add(
                btnSave
        );

        buttons.add(
                btnUpdate
        );

        buttons.add(
                btnActivate
        );

        buttons.add(
                btnDeactivate
        );

        buttons.add(
                btnClear
        );

        btnSave.addActionListener(
                e -> saveTreatment()
        );

        btnUpdate.addActionListener(
                e -> updateTreatment()
        );

        btnActivate.addActionListener(
                e -> activateTreatment()
        );

        btnDeactivate.addActionListener(
                e -> deactivateTreatment()
        );

        btnClear.addActionListener(
                e -> clearForm()
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
    // TABLE
    // ==========================================================
    private JPanel createTreatmentTable() {

        JPanel container
                = new JPanel(
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

        JPanel search
                = new JPanel(
                        new BorderLayout()
                );

        search.setOpaque(
                false
        );

        JLabel lblTitle
                = new JLabel(
                        "Treatments"
                );

        lblTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        17
                )
        );

        JPanel searchRight
                = new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                8,
                                0
                        )
                );

        searchRight.setOpaque(
                false
        );

        txtSearch
                = new JTextField();

        txtSearch.setPreferredSize(
                new Dimension(
                        220,
                        32
                )
        );

        txtSearch.setToolTipText(
                "Search by treatment ID, name or status"
        );

        btnSearch
                = createPrimaryButton(
                        "Search"
                );

        btnRefresh
                = createSecondaryButton(
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

        tableModel
                = new DefaultTableModel(
                        new Object[]{
                            "Treatment ID",
                            "Treatment Name",
                            "Consultation Fee",
                            "Treatment Fee",
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

        tblTreatments
                = new JTable(
                        tableModel
                );

        tblTreatments.setRowHeight(
                28
        );

        tblTreatments.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        tblTreatments
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
                        tblTreatments
                ),
                BorderLayout.CENTER
        );

        btnSearch.addActionListener(
                e -> searchTreatments()
        );

        txtSearch.addActionListener(
                e -> searchTreatments()
        );

        btnRefresh.addActionListener(
                e -> {

                    txtSearch.setText(
                            ""
                    );

                    loadTreatments();
                }
        );

        tblTreatments
                .getSelectionModel()
                .addListSelectionListener(
                        e -> {

                            if (!e.getValueIsAdjusting()) {

                                loadSelectedTreatment();
                            }
                        }
                );

        return container;
    }

    // ==========================================================
    // SAVE
    // ==========================================================
    private void saveTreatment() {

        try {

            BigDecimal consultationFee
                    = new BigDecimal(
                            txtConsultationFee
                                    .getText()
                                    .trim()
                    );

            BigDecimal treatmentFee
                    = new BigDecimal(
                            txtTreatmentFee
                                    .getText()
                                    .trim()
                    );

            Treatment treatment
                    = new Treatment(
                            0,
                            txtTreatmentName
                                    .getText()
                                    .trim(),
                            consultationFee,
                            treatmentFee,
                            cmbStatus
                                    .getSelectedItem()
                                    .toString()
                    );

            boolean success
                    = treatmentService
                            .addTreatment(
                                    treatment
                            );

            if (success) {

                JOptionPane.showMessageDialog(
                        this,
                        "Treatment added successfully."
                );

                clearForm();

                loadTreatments();
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter valid consultation and treatment fees.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );

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
    // UPDATE
    // ==========================================================
    private void updateTreatment() {

        try {

            if (selectedTreatmentId <= 0) {

                throw new IllegalArgumentException(
                        "Please select a treatment first."
                );
            }

            BigDecimal consultationFee
                    = new BigDecimal(
                            txtConsultationFee
                                    .getText()
                                    .trim()
                    );

            BigDecimal treatmentFee
                    = new BigDecimal(
                            txtTreatmentFee
                                    .getText()
                                    .trim()
                    );

            Treatment treatment
                    = new Treatment(
                            selectedTreatmentId,
                            txtTreatmentName
                                    .getText()
                                    .trim(),
                            consultationFee,
                            treatmentFee,
                            cmbStatus
                                    .getSelectedItem()
                                    .toString()
                    );

            boolean success
                    = treatmentService
                            .updateTreatment(
                                    treatment
                            );

            if (success) {

                JOptionPane.showMessageDialog(
                        this,
                        "Treatment updated successfully."
                );

                clearForm();

                loadTreatments();
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter valid consultation and treatment fees.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );

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
    // ACTIVATE
    // ==========================================================
    private void activateTreatment() {

        if (selectedTreatmentId <= 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a treatment first."
            );

            return;
        }

        boolean success
                = treatmentService
                        .activateTreatment(
                                selectedTreatmentId
                        );

        if (success) {

            JOptionPane.showMessageDialog(
                    this,
                    "Treatment activated successfully."
            );

            clearForm();

            loadTreatments();
        }
    }

    // ==========================================================
    // DEACTIVATE
    // ==========================================================
    private void deactivateTreatment() {

        if (selectedTreatmentId <= 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a treatment first."
            );

            return;
        }

        int result
                = JOptionPane.showConfirmDialog(
                        this,
                        "Deactivate this treatment?",
                        "Deactivate Treatment",
                        JOptionPane.YES_NO_OPTION
                );

        if (result
                != JOptionPane.YES_OPTION) {

            return;
        }

        boolean success
                = treatmentService
                        .deactivateTreatment(
                                selectedTreatmentId
                        );

        if (success) {

            JOptionPane.showMessageDialog(
                    this,
                    "Treatment deactivated successfully."
            );

            clearForm();

            loadTreatments();
        }
    }

    // ==========================================================
    // LOAD
    // ==========================================================
    private void loadTreatments() {

        List<Treatment> treatments
                = treatmentService
                        .getAllTreatments();

        populateTable(
                treatments
        );
    }

    // ==========================================================
    // SEARCH
    // ==========================================================
    private void searchTreatments() {

        List<Treatment> treatments
                = treatmentService
                        .searchTreatments(
                                txtSearch
                                        .getText()
                                        .trim()
                        );

        populateTable(
                treatments
        );
    }

    // ==========================================================
    // POPULATE TABLE
    // ==========================================================
    private void populateTable(
            List<Treatment> treatments) {

        tableModel.setRowCount(
                0
        );

        for (Treatment treatment : treatments) {

            tableModel.addRow(
                    new Object[]{
                        treatment.getTreatmentId(),
                        treatment.getTreatmentName(),
                        treatment.getConsultationFee(),
                        treatment.getTreatmentFee(),
                        treatment.getStatus()
                    }
            );
        }
    }

    // ==========================================================
    // SELECT
    // ==========================================================
    private void loadSelectedTreatment() {

        int row
                = tblTreatments
                        .getSelectedRow();

        if (row == -1) {

            return;
        }

        selectedTreatmentId
                = Integer.parseInt(
                        tableModel
                                .getValueAt(
                                        row,
                                        0
                                )
                                .toString()
                );

        txtTreatmentId.setText(
                String.valueOf(
                        selectedTreatmentId
                )
        );

        txtTreatmentName.setText(
                safeValue(
                        tableModel.getValueAt(
                                row,
                                1
                        )
                )
        );

        txtConsultationFee.setText(
                safeValue(
                        tableModel.getValueAt(
                                row,
                                2
                        )
                )
        );

        txtTreatmentFee.setText(
                safeValue(
                        tableModel.getValueAt(
                                row,
                                3
                        )
                )
        );

        cmbStatus.setSelectedItem(
                safeValue(
                        tableModel.getValueAt(
                                row,
                                4
                        )
                )
        );
    }

    // ==========================================================
    // CLEAR
    // ==========================================================
    private void clearForm() {

        selectedTreatmentId
                = -1;

        txtTreatmentId.setText(
                ""
        );

        txtTreatmentName.setText(
                ""
        );

        txtConsultationFee.setText(
                ""
        );

        txtTreatmentFee.setText(
                ""
        );

        cmbStatus.setSelectedItem(
                "ACTIVE"
        );

        tblTreatments.clearSelection();

        txtTreatmentName.requestFocus();
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
    // BUTTONS
    // ==========================================================
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

        button.setPreferredSize(
                new Dimension(
                        145,
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

        button.setPreferredSize(
                new Dimension(
                        145,
                        34
                )
        );

        return button;
    }
}
