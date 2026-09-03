package com.sunrisedental.view.admin;

import com.sunrisedental.model.Dentist;
import com.sunrisedental.model.User;

import com.sunrisedental.service.DentistService;
import com.sunrisedental.service.UserService;

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
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import javax.swing.table.DefaultTableModel;

public class DentistsPanel
        extends JPanel {

    private final DentistService dentistService;
    private final UserService userService;

    private int selectedDentistId
            = -1;

    private JTextField txtDentistId;
    private JComboBox<User> cmbUser;

    private JTextField txtDentistName;
    private JTextField txtSpecialization;
    private JTextField txtContactNumber;
    private JTextField txtEmail;

    private JComboBox<String> cmbStatus;

    private JTextField txtSearch;

    private JButton btnSave;
    private JButton btnUpdate;
    private JButton btnActivate;
    private JButton btnDeactivate;
    private JButton btnClear;
    private JButton btnSearch;
    private JButton btnRefresh;

    private JTable tblDentists;
    private DefaultTableModel tableModel;

    public DentistsPanel() {

        dentistService
                = new DentistService();

        userService
                = new UserService();

        initUI();

        loadAvailableDentistUsers();

        loadDentists();
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
                        "Dentist Management"
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
                        "Create and manage dentist profiles and linked user accounts"
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
                createDentistForm(),
                BorderLayout.NORTH
        );

        main.add(
                createDentistTable(),
                BorderLayout.CENTER
        );

        return main;
    }

    // ==========================================================
    // FORM
    // ==========================================================
    private JPanel createDentistForm() {

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
                        "Dentist Details"
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
                                4,
                                4,
                                15,
                                10
                        )
                );

        fields.setOpaque(
                false
        );

        txtDentistId
                = new JTextField();

        txtDentistId.setEditable(
                false
        );

        txtDentistId.setBackground(
                new Color(
                        245,
                        245,
                        245
                )
        );

        cmbUser
                = new JComboBox<>();

        txtDentistName
                = new JTextField();

        txtDentistName.setEditable(
                false
        );

        txtDentistName.setBackground(
                new Color(
                        245,
                        245,
                        245
                )
        );

        cmbUser.addActionListener(
                e -> {

                    User selectedUser
                    = (User) cmbUser
                            .getSelectedItem();

                    if (selectedUser != null) {

                        txtDentistName.setText(
                                selectedUser
                                        .getFullName()
                        );
                    }
                }
        );

        txtSpecialization
                = new JTextField();

        txtContactNumber
                = new JTextField();

        txtEmail
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
                        "Dentist ID"
                )
        );

        fields.add(
                txtDentistId
        );

        fields.add(
                new JLabel(
                        "User Account"
                )
        );

        fields.add(
                cmbUser
        );

        fields.add(
                new JLabel(
                        "Dentist Name"
                )
        );

        fields.add(
                txtDentistName
        );

        fields.add(
                new JLabel(
                        "Specialization"
                )
        );

        fields.add(
                txtSpecialization
        );

        fields.add(
                new JLabel(
                        "Contact Number"
                )
        );

        fields.add(
                txtContactNumber
        );

        fields.add(
                new JLabel(
                        "Email"
                )
        );

        fields.add(
                txtEmail
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
                        "Create Dentist"
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

        btnSave.addActionListener(
                e -> saveDentist()
        );

        btnUpdate.addActionListener(
                e -> updateDentist()
        );

        btnActivate.addActionListener(
                e -> activateDentist()
        );

        btnDeactivate.addActionListener(
                e -> deactivateDentist()
        );

        btnClear.addActionListener(
                e -> clearForm()
        );

        return container;
    }

    // ==========================================================
    // TABLE
    // ==========================================================
    private JPanel createDentistTable() {

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
                        "Dentists"
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
                        200,
                        32
                )
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
                            "Dentist ID",
                            "User ID",
                            "Name",
                            "Specialization",
                            "Contact",
                            "Email",
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

        tblDentists
                = new JTable(
                        tableModel
                );

        tblDentists.setRowHeight(
                28
        );

        tblDentists.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        tblDentists
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
                        tblDentists
                ),
                BorderLayout.CENTER
        );

        btnSearch.addActionListener(
                e -> searchDentists()
        );

        txtSearch.addActionListener(
                e -> searchDentists()
        );

        btnRefresh.addActionListener(
                e -> {

                    txtSearch.setText(
                            ""
                    );

                    loadDentists();
                }
        );

        tblDentists
                .getSelectionModel()
                .addListSelectionListener(
                        e -> {

                            if (!e.getValueIsAdjusting()) {

                                loadSelectedDentist();
                            }
                        }
                );

        return container;
    }

    // ==========================================================
    // SAVE
    // ==========================================================
    private void saveDentist() {

        try {

            User selectedUser
                    = (User) cmbUser
                            .getSelectedItem();

            if (selectedUser == null) {

                throw new IllegalArgumentException(
                        "Please select a dentist user account."
                );
            }

            Dentist dentist
                    = new Dentist();

            dentist.setUserId(
                    selectedUser
                            .getUserId()
            );

            dentist.setName(
                    txtDentistName
                            .getText()
                            .trim()
            );

            dentist.setSpecialization(
                    txtSpecialization
                            .getText()
                            .trim()
            );

            dentist.setContactNumber(
                    txtContactNumber
                            .getText()
                            .trim()
            );

            dentist.setEmail(
                    txtEmail
                            .getText()
                            .trim()
            );

            dentist.setStatus(
                    cmbStatus
                            .getSelectedItem()
                            .toString()
            );

            boolean success
                    = dentistService
                            .addDentist(
                                    dentist
                            );

            if (success) {

                JOptionPane.showMessageDialog(
                        this,
                        "Dentist created successfully."
                );

                clearForm();

                loadAvailableDentistUsers();

                loadDentists();
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
    // UPDATE
    // ==========================================================
    private void updateDentist() {

        try {

            if (selectedDentistId <= 0) {

                throw new IllegalArgumentException(
                        "Please select a dentist first."
                );
            }

            Dentist currentDentist
                    = dentistService
                            .getDentistById(
                                    selectedDentistId
                            );

            currentDentist.setName(
                    txtDentistName
                            .getText()
                            .trim()
            );

            currentDentist.setSpecialization(
                    txtSpecialization
                            .getText()
                            .trim()
            );

            currentDentist.setContactNumber(
                    txtContactNumber
                            .getText()
                            .trim()
            );

            currentDentist.setEmail(
                    txtEmail
                            .getText()
                            .trim()
            );

            currentDentist.setStatus(
                    cmbStatus
                            .getSelectedItem()
                            .toString()
            );

            boolean success
                    = dentistService
                            .updateDentist(
                                    currentDentist
                            );

            if (success) {

                JOptionPane.showMessageDialog(
                        this,
                        "Dentist updated successfully."
                );

                clearForm();

                loadDentists();
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
    // ACTIVATE
    // ==========================================================
    private void activateDentist() {

        try {

            if (selectedDentistId <= 0) {

                throw new IllegalArgumentException(
                        "Please select a dentist first."
                );
            }

            boolean success
                    = dentistService
                            .activateDentist(
                                    selectedDentistId
                            );

            if (success) {

                JOptionPane.showMessageDialog(
                        this,
                        "Dentist activated successfully."
                );

                clearForm();

                loadDentists();
            }

        } catch (IllegalArgumentException e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage()
            );
        }
    }

    // ==========================================================
    // DEACTIVATE
    // ==========================================================
    private void deactivateDentist() {

        try {

            if (selectedDentistId <= 0) {

                throw new IllegalArgumentException(
                        "Please select a dentist first."
                );
            }

            int result
                    = JOptionPane.showConfirmDialog(
                            this,
                            "Deactivate this dentist?",
                            "Deactivate Dentist",
                            JOptionPane.YES_NO_OPTION
                    );

            if (result
                    != JOptionPane.YES_OPTION) {

                return;
            }

            boolean success
                    = dentistService
                            .deactivateDentist(
                                    selectedDentistId
                            );

            if (success) {

                JOptionPane.showMessageDialog(
                        this,
                        "Dentist deactivated successfully."
                );

                clearForm();

                loadDentists();
            }

        } catch (IllegalArgumentException e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage()
            );
        }
    }

    // ==========================================================
    // LOAD USER ACCOUNTS
    // ==========================================================
    private void loadAvailableDentistUsers() {

        cmbUser.removeAllItems();

        List<User> users
                = userService
                        .getAvailableDentistUsers();

        for (User user : users) {

            cmbUser.addItem(
                    user
            );
        }

        cmbUser.setSelectedIndex(
                -1
        );
    }

    // ==========================================================
    // LOAD DENTISTS
    // ==========================================================
    private void loadDentists() {

        List<Dentist> dentists
                = dentistService
                        .getAllDentists();

        populateTable(
                dentists
        );
    }

    // ==========================================================
    // SEARCH
    // ==========================================================
    private void searchDentists() {

        String keyword
                = txtSearch
                        .getText()
                        .trim();

        List<Dentist> dentists
                = dentistService
                        .searchDentists(
                                keyword
                        );

        populateTable(
                dentists
        );
    }

    // ==========================================================
    // TABLE
    // ==========================================================
    private void populateTable(
            List<Dentist> dentists) {

        tableModel.setRowCount(
                0
        );

        for (Dentist dentist : dentists) {

            tableModel.addRow(
                    new Object[]{
                        dentist.getDentistId(),
                        dentist.getUserId(),
                        dentist.getName(),
                        dentist.getSpecialization(),
                        dentist.getContactNumber(),
                        dentist.getEmail(),
                        dentist.getStatus()
                    }
            );
        }
    }

    // ==========================================================
    // SELECT DENTIST
    // ==========================================================
    private void loadSelectedDentist() {

        int row
                = tblDentists
                        .getSelectedRow();

        if (row == -1) {

            return;
        }

        selectedDentistId
                = Integer.parseInt(
                        tableModel
                                .getValueAt(
                                        row,
                                        0
                                )
                                .toString()
                );

        txtDentistId.setText(
                String.valueOf(
                        selectedDentistId
                )
        );

        txtDentistName.setText(
                safeValue(
                        tableModel.getValueAt(
                                row,
                                2
                        )
                )
        );

        txtSpecialization.setText(
                safeValue(
                        tableModel.getValueAt(
                                row,
                                3
                        )
                )
        );

        txtContactNumber.setText(
                safeValue(
                        tableModel.getValueAt(
                                row,
                                4
                        )
                )
        );

        txtEmail.setText(
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

        cmbUser.setEnabled(
                false
        );
    }

    // ==========================================================
    // CLEAR
    // ==========================================================
    private void clearForm() {

        selectedDentistId
                = -1;

        txtDentistId.setText(
                ""
        );

        txtDentistName.setText(
                ""
        );

        txtSpecialization.setText(
                ""
        );

        txtContactNumber.setText(
                ""
        );

        txtEmail.setText(
                ""
        );

        cmbStatus.setSelectedItem(
                "ACTIVE"
        );

        cmbUser.setEnabled(
                true
        );

        tblDentists.clearSelection();
    }

    private String safeValue(
            Object value) {

        return value == null
                ? ""
                : value.toString();
    }

    // ==========================================================
    // BUTTON STYLING
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

        button.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        button.setPreferredSize(
                new Dimension(
                        140,
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
                        140,
                        34
                )
        );

        return button;
    }
}
