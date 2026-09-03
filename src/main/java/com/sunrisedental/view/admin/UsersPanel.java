package com.sunrisedental.view.admin;

import com.sunrisedental.model.User;
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
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import javax.swing.table.DefaultTableModel;

public class UsersPanel
        extends JPanel {

    private final int loggedInAdminUserId;

    private final UserService userService;

    private int selectedUserId
            = -1;

    private JTextField txtUserId;
    private JTextField txtUsername;
    private JTextField txtFullName;
    private JPasswordField txtPassword;

    private JComboBox<String> cmbRole;

    private JTextField txtSearch;

    private JButton btnSave;
    private JButton btnUpdate;
    private JButton btnResetPassword;
    private JButton btnClear;
    private JButton btnSearch;
    private JButton btnRefresh;

    private JTable tblUsers;
    private DefaultTableModel tableModel;

    public UsersPanel(
            int loggedInAdminUserId) {

        this.loggedInAdminUserId
                = loggedInAdminUserId;

        userService
                = new UserService();

        initUI();

        loadUsers();
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
                        "User Management"
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
                        "Create and manage system login accounts"
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
                createUserForm(),
                BorderLayout.NORTH
        );

        main.add(
                createUserTable(),
                BorderLayout.CENTER
        );

        return main;
    }

    // ==========================================================
    // USER FORM
    // ==========================================================
    private JPanel createUserForm() {

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
                        "User Details"
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

        txtUserId
                = new JTextField();

        txtUserId.setEditable(
                false
        );

        txtUserId.setBackground(
                new Color(
                        245,
                        245,
                        245
                )
        );

        txtUsername
                = new JTextField();

        txtFullName
                = new JTextField();

        txtPassword
                = new JPasswordField();

        cmbRole
                = new JComboBox<>(
                        new String[]{
                            "RECEPTIONIST",
                            "DENTIST",
                            "ADMIN"
                        }
                );

        fields.add(
                new JLabel(
                        "User ID"
                )
        );

        fields.add(
                txtUserId
        );

        fields.add(
                new JLabel(
                        "Username"
                )
        );

        fields.add(
                txtUsername
        );

        fields.add(
                new JLabel(
                        "Full Name"
                )
        );

        fields.add(
                txtFullName
        );

        fields.add(
                new JLabel(
                        "Role"
                )
        );

        fields.add(
                cmbRole
        );

        fields.add(
                new JLabel(
                        "Password"
                )
        );

        fields.add(
                txtPassword
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
                        "Create User"
                );

        btnUpdate
                = createSecondaryButton(
                        "Update"
                );

        btnResetPassword
                = createSecondaryButton(
                        "Reset Password"
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
                btnResetPassword
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
                e -> saveUser()
        );

        btnUpdate.addActionListener(
                e -> updateUser()
        );

        btnResetPassword.addActionListener(
                e -> resetPassword()
        );

        btnClear.addActionListener(
                e -> clearForm()
        );

        return container;
    }

    // ==========================================================
    // USER TABLE
    // ==========================================================
    private JPanel createUserTable() {

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
                        "System Users"
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
                            "User ID",
                            "Username",
                            "Full Name",
                            "Role"
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

        tblUsers
                = new JTable(
                        tableModel
                );

        tblUsers.setRowHeight(
                28
        );

        tblUsers.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        tblUsers
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
                        tblUsers
                ),
                BorderLayout.CENTER
        );

        btnSearch.addActionListener(
                e -> searchUsers()
        );

        txtSearch.addActionListener(
                e -> searchUsers()
        );

        btnRefresh.addActionListener(
                e -> {

                    txtSearch.setText(
                            ""
                    );

                    loadUsers();
                }
        );

        tblUsers
                .getSelectionModel()
                .addListSelectionListener(
                        e -> {

                            if (!e.getValueIsAdjusting()) {

                                loadSelectedUser();
                            }
                        }
                );

        return container;
    }

    // ==========================================================
    // SAVE
    // ==========================================================
    private void saveUser() {

        try {

            String password
                    = new String(
                            txtPassword.getPassword()
                    );

            boolean success
                    = userService
                            .addUser(
                                    txtUsername
                                            .getText()
                                            .trim(),
                                    password,
                                    txtFullName
                                            .getText()
                                            .trim(),
                                    cmbRole
                                            .getSelectedItem()
                                            .toString()
                            );

            if (success) {

                JOptionPane.showMessageDialog(
                        this,
                        "User account created successfully."
                );

                clearForm();

                loadUsers();
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
    private void updateUser() {

        try {

            if (selectedUserId <= 0) {

                throw new IllegalArgumentException(
                        "Please select a user first."
                );
            }

            String selectedRole
                    = cmbRole
                            .getSelectedItem()
                            .toString();

            /*
             * Prevent the logged-in admin from accidentally
             * removing their own ADMIN role.
             */
            if (selectedUserId
                    == loggedInAdminUserId
                    && !"ADMIN".equalsIgnoreCase(
                            selectedRole)) {

                throw new IllegalArgumentException(
                        "You cannot remove your own administrator role."
                );
            }

            boolean success
                    = userService
                            .updateUser(
                                    selectedUserId,
                                    txtFullName
                                            .getText()
                                            .trim(),
                                    selectedRole
                            );

            if (success) {

                JOptionPane.showMessageDialog(
                        this,
                        "User updated successfully."
                );

                clearForm();

                loadUsers();
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
    // RESET PASSWORD
    // ==========================================================
    private void resetPassword() {

        try {

            if (selectedUserId <= 0) {

                throw new IllegalArgumentException(
                        "Please select a user first."
                );
            }

            String password
                    = new String(
                            txtPassword.getPassword()
                    );

            int result
                    = JOptionPane.showConfirmDialog(
                            this,
                            "Reset password for this user?",
                            "Reset Password",
                            JOptionPane.YES_NO_OPTION
                    );

            if (result
                    != JOptionPane.YES_OPTION) {

                return;
            }

            boolean success
                    = userService
                            .resetPassword(
                                    selectedUserId,
                                    password
                            );

            if (success) {

                JOptionPane.showMessageDialog(
                        this,
                        "Password reset successfully."
                );

                txtPassword.setText(
                        ""
                );
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
    // LOAD USERS
    // ==========================================================
    private void loadUsers() {

        List<User> users
                = userService
                        .getAllUsers();

        populateTable(
                users
        );
    }

    // ==========================================================
    // SEARCH
    // ==========================================================
    private void searchUsers() {

        String keyword
                = txtSearch
                        .getText()
                        .trim();

        List<User> users
                = userService
                        .searchUsers(
                                keyword
                        );

        populateTable(
                users
        );
    }

    // ==========================================================
    // TABLE
    // ==========================================================
    private void populateTable(
            List<User> users) {

        tableModel.setRowCount(
                0
        );

        for (User user : users) {

            tableModel.addRow(
                    new Object[]{
                        user.getUserId(),
                        user.getUsername(),
                        user.getFullName(),
                        user.getRole()
                    }
            );
        }
    }

    // ==========================================================
    // SELECT USER
    // ==========================================================
    private void loadSelectedUser() {

        int row
                = tblUsers
                        .getSelectedRow();

        if (row == -1) {

            return;
        }

        selectedUserId
                = Integer.parseInt(
                        tableModel
                                .getValueAt(
                                        row,
                                        0
                                )
                                .toString()
                );

        txtUserId.setText(
                String.valueOf(
                        selectedUserId
                )
        );

        txtUsername.setText(
                tableModel
                        .getValueAt(
                                row,
                                1
                        )
                        .toString()
        );

        txtFullName.setText(
                tableModel
                        .getValueAt(
                                row,
                                2
                        )
                        .toString()
        );

        cmbRole.setSelectedItem(
                tableModel
                        .getValueAt(
                                row,
                                3
                        )
                        .toString()
        );

        /*
         * Username should not normally be changed once
         * the account has been created.
         */
        txtUsername.setEditable(
                false
        );

        txtPassword.setText(
                ""
        );
    }

    // ==========================================================
    // CLEAR
    // ==========================================================
    private void clearForm() {

        selectedUserId
                = -1;

        txtUserId.setText(
                ""
        );

        txtUsername.setText(
                ""
        );

        txtUsername.setEditable(
                true
        );

        txtFullName.setText(
                ""
        );

        txtPassword.setText(
                ""
        );

        cmbRole.setSelectedItem(
                "RECEPTIONIST"
        );

        tblUsers.clearSelection();

        txtUsername.requestFocus();
    }

    // ==========================================================
    // BUTTON STYLES
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
