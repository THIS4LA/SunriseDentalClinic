package com.sunrisedental.view.receptionist;

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

public class HelpPanel extends JPanel {

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
                        "Help Center"
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
                        "Find step-by-step guidance for using the system"
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

        scrollPane.getViewport()
                .setBackground(
                        new Color(
                                245,
                                247,
                                250
                        )
                );

        scrollPane.getVerticalScrollBar()
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
                "Search by topic, e.g. appointment, patient, bill"
        );

        container.add(
                lblSearch,
                BorderLayout.NORTH
        );

        container.add(
                txtSearch,
                BorderLayout.CENTER
        );

        txtSearch.getDocument()
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
    // HELP ITEM CARD
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
    // DISPLAY HELP ITEMS
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
                    javax.swing.Box.createVerticalStrut(
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
    // SEARCH / FILTER
    // ==========================================================

    private void filterHelpItems() {

        String keyword =
                txtSearch.getText()
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
    // HELP CONTENT
    // ==========================================================

    private void createHelpData() {

        helpItems.add(
                new HelpItem(
                        "How do I register a new patient?",
                        """
                        1. Select Patients from the left sidebar.
                        2. Enter the patient's name, address, contact number and email.
                        3. Check that the entered information is correct.
                        4. Click Save.
                        5. The new patient will appear in the patient table and will then be available when creating appointments.
                        """
                )
        );

        helpItems.add(
                new HelpItem(
                        "How do I create a new appointment?",
                        """
                        1. Select Appointments from the left sidebar.
                        2. Select the patient from the Patient dropdown.
                        3. Select the dentist.
                        4. Select the treatment type.
                        5. Choose the appointment date using the date picker.
                        6. Choose the appointment time using the time picker.
                        7. Enter any necessary notes.
                        8. Click Save Appointment.
                        9. If the dentist is already booked for that date and time, the system will ask you to choose another time.
                        """
                )
        );

        helpItems.add(
                new HelpItem(
                        "How do I search for an appointment?",
                        """
                        1. Open the Appointments section.
                        2. Enter the appointment number or another available search value in the search field.
                        3. Click Search.
                        4. Matching appointments will appear in the appointment table.
                        5. Select a row to view or update its details.
                        """
                )
        );

        helpItems.add(
                new HelpItem(
                        "How do I update an appointment?",
                        """
                        1. Open the Appointments section.
                        2. Search for the required appointment.
                        3. Select the appointment from the table.
                        4. Update the patient, dentist, treatment, date, time or notes as required.
                        5. Click Update.
                        6. The system will check for conflicting dentist bookings before saving the changes.
                        """
                )
        );

        helpItems.add(
                new HelpItem(
                        "How do I cancel an appointment?",
                        """
                        1. Open the Appointments section.
                        2. Search for and select the appointment.
                        3. Click Cancel Appointment.
                        4. Confirm the cancellation when prompted.
                        5. The appointment will remain in the database but its status will be changed to CANCELLED.
                        """
                )
        );

        helpItems.add(
                new HelpItem(
                        "How do I generate a patient bill?",
                        """
                        1. Select Billing from the left sidebar.
                        2. Enter the appointment number.
                        3. Click Find Appointment.
                        4. The system will load the patient, dentist and treatment information automatically.
                        5. The consultation fee and treatment fee will be loaded from the treatment record.
                        6. Enter a discount if applicable.
                        7. Click Calculate to calculate the total amount.
                        8. Select the payment method and payment status.
                        9. Click Save Bill.
                        """
                )
        );

        helpItems.add(
                new HelpItem(
                        "How do I print a receipt?",
                        """
                        1. Open the Billing section.
                        2. Find the required appointment and generate its bill.
                        3. Make sure the bill values and payment information are correct.
                        4. Click Print Receipt.
                        5. Select the required printer from the system print dialog.
                        6. Confirm the print operation.
                        """
                )
        );

        helpItems.add(
                new HelpItem(
                        "How do I view reports?",
                        """
                        1. Select Reports from the left sidebar.
                        2. Select the required report type.
                        3. Select the From Date and To Date using the date pickers.
                        4. Click Generate Report.
                        5. The summary cards and report table will display results for the selected period.
                        6. Click Print Report if a printed copy is required.
                        """
                )
        );

        helpItems.add(
                new HelpItem(
                        "How do I log out of the system?",
                        """
                        1. Click Logout from the left sidebar.
                        2. Confirm that you want to log out.
                        3. The receptionist dashboard will close.
                        4. The Login screen will be displayed again.
                        """
                )
        );
    }

    // ==========================================================
    // SIMPLE HELP ITEM MODEL
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