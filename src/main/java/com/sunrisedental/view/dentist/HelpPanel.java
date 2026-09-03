package com.sunrisedental.view.dentist;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class HelpPanel
        extends JPanel {

    private JTextField txtSearch;

    private JPanel pnlHelpItems;

    private final List<HelpItem> helpItems;

    public HelpPanel() {

        helpItems =
                new ArrayList<>();

        createHelpData();

        initUI();

        displayHelpItems(
                helpItems
        );
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
                        "Dentist Help Center"
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
                        "Find step-by-step guidance for using the dentist dashboard"
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
                createSearchPanel(),
                BorderLayout.NORTH
        );

        pnlHelpItems =
                new JPanel();

        pnlHelpItems.setLayout(
                new BoxLayout(
                        pnlHelpItems,
                        BoxLayout.Y_AXIS
                )
        );

        pnlHelpItems.setBackground(
                new Color(
                        245,
                        247,
                        250
                )
        );

        JScrollPane scrollPane =
                new JScrollPane(
                        pnlHelpItems
                );

        scrollPane.setBorder(
                null
        );

        scrollPane
                .getViewport()
                .setBackground(
                        new Color(
                                245,
                                247,
                                250
                        )
                );

        scrollPane
                .getVerticalScrollBar()
                .setUnitIncrement(
                        16
                );

        main.add(
                scrollPane,
                BorderLayout.CENTER
        );

        return main;
    }

    // ==========================================================
    // SEARCH PANEL
    // ==========================================================

    private JPanel createSearchPanel() {

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

        JLabel lblSearch =
                new JLabel(
                        "Search Help"
                );

        lblSearch.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        16
                )
        );

        txtSearch =
                new JTextField();

        txtSearch.setPreferredSize(
                new Dimension(
                        0,
                        36
                )
        );

        txtSearch.setToolTipText(
                "Search by topic, e.g. appointment, patient, treatment, report"
        );

        container.add(
                lblSearch,
                BorderLayout.NORTH
        );

        container.add(
                txtSearch,
                BorderLayout.CENTER
        );

        txtSearch
                .getDocument()
                .addDocumentListener(
                        new DocumentListener() {

                            @Override
                            public void insertUpdate(
                                    DocumentEvent e) {

                                filterHelpItems();
                            }

                            @Override
                            public void removeUpdate(
                                    DocumentEvent e) {

                                filterHelpItems();
                            }

                            @Override
                            public void changedUpdate(
                                    DocumentEvent e) {

                                filterHelpItems();
                            }
                        }
                );

        return container;
    }

    // ==========================================================
    // HELP CARD
    // ==========================================================

    private JPanel createHelpItemPanel(
            HelpItem item) {

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
                                18,
                                20,
                                18,
                                20
                        )
                )
        );

        panel.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        220
                )
        );

        JLabel lblQuestion =
                new JLabel(
                        item.getTitle()
                );

        lblQuestion.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        16
                )
        );

        JTextArea txtDescription =
                new JTextArea(
                        item.getDescription()
                );

        txtDescription.setEditable(
                false
        );

        txtDescription.setOpaque(
                false
        );

        txtDescription.setLineWrap(
                true
        );

        txtDescription.setWrapStyleWord(
                true
        );

        txtDescription.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        txtDescription.setForeground(
                new Color(
                        70,
                        70,
                        70
                )
        );

        txtDescription.setBorder(
                null
        );

        panel.add(
                lblQuestion,
                BorderLayout.NORTH
        );

        panel.add(
                txtDescription,
                BorderLayout.CENTER
        );

        return panel;
    }

    // ==========================================================
    // DISPLAY ITEMS
    // ==========================================================

    private void displayHelpItems(
            List<HelpItem> items) {

        pnlHelpItems.removeAll();

        for (HelpItem item : items) {

            JPanel panel =
                    createHelpItemPanel(
                            item
                    );

            pnlHelpItems.add(
                    panel
            );

            pnlHelpItems.add(
                    javax.swing.Box
                            .createVerticalStrut(
                                    12
                            )
            );
        }

        if (items.isEmpty()) {

            JLabel lblNoResults =
                    new JLabel(
                            "No help topics found."
                    );

            lblNoResults.setFont(
                    new Font(
                            "Segoe UI",
                            Font.PLAIN,
                            14
                    )
            );

            lblNoResults.setForeground(
                    new Color(
                            100,
                            100,
                            100
                    )
            );

            pnlHelpItems.add(
                    lblNoResults
            );
        }

        pnlHelpItems.revalidate();

        pnlHelpItems.repaint();
    }

    // ==========================================================
    // SEARCH
    // ==========================================================

    private void filterHelpItems() {

        String keyword =
                txtSearch
                        .getText()
                        .trim()
                        .toLowerCase();

        if (keyword.isEmpty()) {

            displayHelpItems(
                    helpItems
            );

            return;
        }

        List<HelpItem> filtered =
                new ArrayList<>();

        for (HelpItem item : helpItems) {

            boolean matchesTitle =
                    item.getTitle()
                            .toLowerCase()
                            .contains(
                                    keyword
                            );

            boolean matchesDescription =
                    item.getDescription()
                            .toLowerCase()
                            .contains(
                                    keyword
                            );

            if (matchesTitle
                    || matchesDescription) {

                filtered.add(
                        item
                );
            }
        }

        displayHelpItems(
                filtered
        );
    }

    // ==========================================================
    // DENTIST HELP CONTENT
    // ==========================================================

    private void createHelpData() {

        helpItems.add(
                new HelpItem(
                        "How do I view my appointments?",
                        """
                        1. Select My Appointments from the left sidebar.
                        2. The system will display appointments assigned to your dentist account only.
                        3. Use the search field to search by appointment number, patient, treatment or status.
                        4. Click Refresh to return to the full appointment list.
                        """
                )
        );

        helpItems.add(
                new HelpItem(
                        "How do I view my daily schedule?",
                        """
                        1. Select My Schedule from the left sidebar.
                        2. Select the required date using the date picker.
                        3. The system will display your appointments for the selected date.
                        4. Appointments are displayed in time order.
                        5. Cancelled appointments are not included in the active schedule.
                        """
                )
        );

        helpItems.add(
                new HelpItem(
                        "How do I view my patients?",
                        """
                        1. Select Patients from the left sidebar.
                        2. The system will display patients who have appointments assigned to you.
                        3. Use the search field to search by patient ID, name, contact number or email.
                        4. The dentist patient page is intended mainly for viewing patient information.
                        """
                )
        );

        helpItems.add(
                new HelpItem(
                        "How do I create a treatment record?",
                        """
                        1. Select Treatment Records from the left sidebar.
                        2. Select the required appointment.
                        3. The patient's information will be loaded automatically.
                        4. Enter the diagnosis.
                        5. Enter the treatment performed.
                        6. Add clinical notes and recommendations where necessary.
                        7. Select Follow-up Required if another visit is required.
                        8. Select the follow-up date when applicable.
                        9. Click Save Record.
                        """
                )
        );

        helpItems.add(
                new HelpItem(
                        "How do I update a treatment record?",
                        """
                        1. Open Treatment Records.
                        2. Select the required treatment record from the table.
                        3. The record details will be loaded into the form.
                        4. Update the diagnosis, treatment performed, clinical notes, recommendation or follow-up information.
                        5. Click Update Record.
                        6. Only treatment records associated with your dentist account can be updated.
                        """
                )
        );

        helpItems.add(
                new HelpItem(
                        "How do I manage follow-up information?",
                        """
                        1. Open the required treatment record.
                        2. Select Follow-up Required.
                        3. Choose the required follow-up date.
                        4. Enter any follow-up recommendation or instructions.
                        5. Save or update the treatment record.
                        6. If no follow-up is required, leave the option unselected.
                        """
                )
        );

        helpItems.add(
                new HelpItem(
                        "How do I generate a dentist report?",
                        """
                        1. Select Reports from the left sidebar.
                        2. Choose the required report type.
                        3. Select the From Date and To Date.
                        4. Click Generate Report.
                        5. The report will contain information associated with your dentist account only.
                        6. Available reports may include appointment, treatment, status and patient summaries.
                        7. Click Print Report if a printed copy is required.
                        """
                )
        );

        helpItems.add(
                new HelpItem(
                        "How do I update my profile?",
                        """
                        1. Select Profile from the left sidebar.
                        2. Your dentist information will be loaded automatically.
                        3. Dentist ID, name, specialization and status are read-only.
                        4. Update your contact number or email address.
                        5. Click Update Profile.
                        6. Click Reset if you want to reload the saved information.
                        """
                )
        );

        helpItems.add(
                new HelpItem(
                        "What do the dashboard cards show?",
                        """
                        Today's Appointments shows the number of active appointments assigned to you for today.

                        Pending shows the number of today's appointments that currently have PENDING status.

                        Completed shows the number of today's appointments that have been completed.

                        These values are calculated using your dentist account only.
                        """
                )
        );

        helpItems.add(
                new HelpItem(
                        "How do the dashboard quick actions work?",
                        """
                        My Appointments opens your assigned appointment list.

                        View Patients opens the list of patients associated with your appointments.

                        Treatment Records opens the clinical treatment record section.

                        My Schedule opens your daily appointment schedule.
                        """
                )
        );

        helpItems.add(
                new HelpItem(
                        "Why can I not see another dentist's information?",
                        """
                        The system uses role-based and dentist-specific access.

                        When you log in, your user account is linked to your dentist record.

                        Appointment, patient, treatment record, schedule and report queries use your dentist ID so that you only see information associated with your account.
                        """
                )
        );

        helpItems.add(
                new HelpItem(
                        "How do I log out?",
                        """
                        1. Click Logout from the left sidebar.
                        2. Confirm the logout request.
                        3. The dentist dashboard will close.
                        4. The Login screen will be displayed again.
                        """
                )
        );
    }

    // ==========================================================
    // HELP ITEM MODEL
    // ==========================================================

    private static class HelpItem {

        private final String title;
        private final String description;

        public HelpItem(
                String title,
                String description) {

            this.title =
                    title;

            this.description =
                    description;
        }

        public String getTitle() {

            return title;
        }

        public String getDescription() {

            return description;
        }
    }
}