package com.sunrisedental.view.dentist;

import com.sunrisedental.model.Dentist;
import com.sunrisedental.service.DentistService;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DentistProfilePanel
        extends JPanel {

    private final int userId;

    private final DentistService dentistService;

    private Dentist currentDentist;

    private JTextField txtDentistId;
    private JTextField txtName;
    private JTextField txtSpecialization;
    private JTextField txtContactNumber;
    private JTextField txtEmail;
    private JTextField txtStatus;

    private JButton btnUpdate;
    private JButton btnReset;

    public DentistProfilePanel(
            int userId) {

        this.userId
                = userId;

        dentistService
                = new DentistService();

        initUI();

        loadProfile();
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
                createProfilePanel(),
                BorderLayout.CENTER
        );
    }

    // ==========================================================
    // HEADER
    // ==========================================================
    private JPanel createHeader() {

        JPanel panel
                = new JPanel(
                        new java.awt.GridLayout(
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
                        "Dentist Profile"
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
                        "View and update your personal information"
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
    // PROFILE PANEL
    // ==========================================================
    private JPanel createProfilePanel() {

        JPanel wrapper
                = new JPanel(
                        new BorderLayout()
                );

        wrapper.setOpaque(
                false
        );

        wrapper.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        0,
                        0,
                        0
                )
        );

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

        JLabel lblSectionTitle
                = new JLabel(
                        "Profile Information"
                );

        lblSectionTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        17
                )
        );

        container.add(
                lblSectionTitle,
                BorderLayout.NORTH
        );

        JPanel form
                = new JPanel(
                        new GridBagLayout()
                );

        form.setOpaque(
                false
        );

        txtDentistId
                = createReadOnlyField();

        txtName
                = createReadOnlyField();

        txtSpecialization
                = createReadOnlyField();

        txtContactNumber
                = new JTextField();

        txtEmail
                = new JTextField();

        txtStatus
                = createReadOnlyField();

        addFormRow(
                form,
                0,
                "Dentist ID",
                txtDentistId
        );

        addFormRow(
                form,
                1,
                "Name",
                txtName
        );

        addFormRow(
                form,
                2,
                "Specialization",
                txtSpecialization
        );

        addFormRow(
                form,
                3,
                "Contact Number",
                txtContactNumber
        );

        addFormRow(
                form,
                4,
                "Email",
                txtEmail
        );

        addFormRow(
                form,
                5,
                "Status",
                txtStatus
        );

        container.add(
                form,
                BorderLayout.CENTER
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

        btnUpdate
                = createPrimaryButton(
                        "Update Profile"
                );

        btnReset
                = createSecondaryButton(
                        "Reset"
                );

        buttons.add(
                btnUpdate
        );

        buttons.add(
                btnReset
        );

        container.add(
                buttons,
                BorderLayout.SOUTH
        );

        btnUpdate.addActionListener(
                e -> updateProfile()
        );

        btnReset.addActionListener(
                e -> loadProfile()
        );

        wrapper.add(
                container,
                BorderLayout.NORTH
        );

        return wrapper;
    }

    // ==========================================================
    // FORM ROW
    // ==========================================================
    private void addFormRow(
            JPanel panel,
            int row,
            String labelText,
            java.awt.Component component) {

        GridBagConstraints gbc
                = new GridBagConstraints();

        gbc.gridy
                = row;

        gbc.insets
                = new Insets(
                        8,
                        8,
                        8,
                        8
                );

        gbc.anchor
                = GridBagConstraints.WEST;

        gbc.gridx
                = 0;

        gbc.weightx
                = 0;

        gbc.fill
                = GridBagConstraints.NONE;

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

        gbc.gridx
                = 1;

        gbc.weightx
                = 1.0;

        gbc.fill
                = GridBagConstraints.HORIZONTAL;

        component.setPreferredSize(
                new Dimension(
                        350,
                        32
                )
        );

        panel.add(
                component,
                gbc
        );
    }

    // ==========================================================
    // READ-ONLY FIELD
    // ==========================================================
    private JTextField createReadOnlyField() {

        JTextField field
                = new JTextField();

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

        return field;
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

        button.setPreferredSize(
                new Dimension(
                        120,
                        34
                )
        );

        return button;
    }

    // ==========================================================
    // LOAD PROFILE
    // ==========================================================
    private void loadProfile() {

        try {

            currentDentist
                    = dentistService
                            .getDentistByUserId(
                                    userId
                            );

            txtDentistId.setText(
                    String.valueOf(
                            currentDentist
                                    .getDentistId()
                    )
            );

            txtName.setText(
                    currentDentist
                            .getName()
            );

            txtSpecialization.setText(
                    currentDentist
                            .getSpecialization()
            );

            txtContactNumber.setText(
                    currentDentist
                            .getContactNumber()
            );

            txtEmail.setText(
                    currentDentist
                            .getEmail()
            );

            txtStatus.setText(
                    currentDentist
                            .getStatus()
            );

        } catch (IllegalArgumentException e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Profile Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // ==========================================================
    // UPDATE PROFILE
    // ==========================================================
    private void updateProfile() {

        try {

            if (currentDentist == null) {

                throw new IllegalArgumentException(
                        "Dentist profile is not loaded."
                );
            }

            boolean success
                    = dentistService
                            .updateOwnProfile(
                                    currentDentist
                                            .getDentistId(),
                                    txtContactNumber
                                            .getText()
                                            .trim(),
                                    txtEmail
                                            .getText()
                                            .trim()
                            );

            if (success) {

                JOptionPane.showMessageDialog(
                        this,
                        "Profile updated successfully.",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );

                loadProfile();
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
}
