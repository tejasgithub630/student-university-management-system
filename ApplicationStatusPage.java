import java.util.logging.FileHandler;
import javaimport java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

private static final Logger logger = Logger.getLogger(ApplicationStatusPage.class.getName());

public ApplicationStatusPage(JFrame frame, HashMap<String, String> studentData, HashMap<String, String> adminData) {
    try {
        FileHandler fileHandler = new FileHandler("application_status.log", true);
        logger.addHandler(fileHandler);
        SimpleFormatter formatter = new SimpleFormatter();
        fileHandler.setFormatter(formatter);
    } catch (Exception e) {
        logger.severe("Error initializing log file: " + e.getMessage());
    }

    // ... rest of the code ...

    // Insert student data into the database
    try {
        Database.insertStudent(studentData);
        logger.info("Student data inserted into the database successfully.");
    } catch (Exception e) {
        logger.severe("Error inserting student data into the database: " + e.getMessage());
    }
}atch (Exception e) {
        logger.severe("Error inserting student data into the database: " + e.getMessage());
    }
}rivate static final Logger logger = Logger.getLogger(ApplicationStatusPage.class.getName());

public ApplicationStatusPage(JFrame frame, HashMap<Stringpackage StudentAdmitManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class ApplicationStatusPage extends JPanel {
    private HashMap<String, String> studentData;
    private HashMap<String, String> adminData;

    public ApplicationStatusPage(JFrame frame, HashMap<String, String> studentData, HashMap<String, String> adminData) {
        this.studentData = studentData;
        this.adminData = adminData;

        setLayout(new BorderLayout(10, 10)); // Border layout with gaps between components
        setBackground(new Color(240, 248, 255)); // Light blue background

        // Top Panel for Header
        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel headerLabel = new JLabel("Application Status", JLabel.CENTER);
        headerLabel.setFont(new Font("SansSerif", Font.BOLD, 36));
        headerLabel.setForeground(new Color(34, 34, 34)); // Dark text for readability
        topPanel.add(headerLabel, BorderLayout.CENTER);
        add(topPanel, BorderLayout.NORTH); // Add top panel to the north of the layout

        // Center Panel for displaying details
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(0, 2, 10, 10)); // Grid layout with 2 columns
        centerPanel.setBackground(new Color(240, 248, 255)); // Match the background color

        // Adding student details
        centerPanel.add(new JLabel("Name:"));
        centerPanel.add(new JLabel(studentData.get("Name")));

        centerPanel.add(new JLabel("Contact Number:"));
        centerPanel.add(new JLabel(studentData.get("ContactNumber")));

        centerPanel.add(new JLabel("Email:"));
        centerPanel.add(new JLabel(studentData.get("EmailAddress")));

        centerPanel.add(new JLabel("Date of Birth(DAY):"));
        centerPanel.add(new JLabel(studentData.get("Day")));

        centerPanel.add(new JLabel("Date of Birth(Month):"));
        centerPanel.add(new JLabel(studentData.get("Month")));

        centerPanel.add(new JLabel("Date of Birth(year):"));
        centerPanel.add(new JLabel(studentData.get("Year")));

        centerPanel.add(new JLabel("Gender:"));
        centerPanel.add(new JLabel(studentData.get("Gender")));

        centerPanel.add(new JLabel("Photo:"));
        centerPanel.add(new JLabel(studentData.get("Picture"))); // Assuming this is a path or URL

        centerPanel.add(new JLabel("10th Report Card:"));
        centerPanel.add(new JLabel(studentData.get("10thReportCard"))); // Assuming this is a path or URL

        centerPanel.add(new JLabel("12th Report Card:"));
        centerPanel.add(new JLabel(studentData.get("12thReportCard"))); // Assuming this is a path or URL

        centerPanel.add(new JLabel("Admission Order:"));
        centerPanel.add(new JLabel(studentData.get("AdmissionOrder"))); // Assuming this is a path or URL

        centerPanel.add(new JLabel("Transfer Certificate:"));
        centerPanel.add(new JLabel(studentData.get("TransferCertificate"))); // Assuming this is a path or URL

        centerPanel.add(new JLabel("Selected College:"));
        centerPanel.add(new JLabel(studentData.get("SelectedCollege")));

        centerPanel.add(new JLabel("Selected Programs:"));
        String[] selectedPrograms = studentData.get("SelectedPrograms").split(","); // Split by comma
        for (int i = 0; i < selectedPrograms.length; i++) {
            centerPanel.add(new JLabel(selectedPrograms[i].trim())); // Trim whitespace
            centerPanel.add(new JLabel("Program " + (i + 1) + ":"));
        }

        add(centerPanel, BorderLayout.CENTER);

        // Bottom Panel for confirmation and close button
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout(10, 10));
        bottomPanel.setBackground(new Color(240, 248, 255)); // Match the background color

        JLabel confirmationLabel = new JLabel("Your application has been successfully submitted.", JLabel.CENTER);
        confirmationLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        confirmationLabel.setForeground(new Color(34, 139, 34)); // Green confirmation message
        bottomPanel.add(confirmationLabel, BorderLayout.CENTER);

        // Close Button
        JButton closeButton = new JButton("Close");
        styleButton(closeButton, frame); // Apply button styles
        bottomPanel.add(closeButton, BorderLayout.SOUTH);

        add(bottomPanel, BorderLayout.SOUTH);

        // Insert student data into the database
        Database.insertStudent(studentData);
    }

    // Utility method to style buttons
    private void styleButton(JButton button, JFrame frame) {
        button.setFont(new Font("SansSerif", Font.BOLD, 16));
        button.setBackground(new Color(70, 130, 180)); // Blue background
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(65, 105, 225)); // Darker blue on hover
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(70, 130, 180)); // Reset to original blue
            }
        });

        // Action to exit the application when the Close button is clicked
        button.addActionListener(e -> System.exit(0)); // Exit the application
    }
}
