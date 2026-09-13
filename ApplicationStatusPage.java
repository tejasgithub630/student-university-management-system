package StudentAdmitManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class ApplicationStatusPage extends JPanel {
    private HashMap<String, String> studentData;
    private HashMap<String, String> adminData;

    public ApplicationStatusPage(JFrame frame, HashMap<String, String> studentData, HashMap<String, String> adminData) {
        this.studentData = studentData;
        this.adminData = adminData;

        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(240, 248, 255));

        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel headerLabel = new JLabel("Application Status", JLabel.CENTER);
        headerLabel.setFont(new Font("SansSerif", Font.BOLD, 36));
        headerLabel.setForeground(new Color(34, 34, 34));
        topPanel.add(headerLabel, BorderLayout.CENTER);
        add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(0, 2, 10, 10));
        centerPanel.setBackground(new Color(240, 248, 255));

        addDetail(centerPanel, "Name:", studentData.get("Name"));
        addDetail(centerPanel, "Contact Number:", studentData.get("ContactNumber"));
        addDetail(centerPanel, "Email:", studentData.get("EmailAddress"));
        addDetail(centerPanel, "Date of Birth (Day):", studentData.get("Day"));
        addDetail(centerPanel, "Date of Birth (Month):", studentData.get("Month"));
        addDetail(centerPanel, "Date of Birth (Year):", studentData.get("Year"));
        addDetail(centerPanel, "Gender:", studentData.get("Gender"));
        addDetail(centerPanel, "Photo:", studentData.get("photo"));
        addDetail(centerPanel, "10th Report Card:", studentData.get("10thReportCard"));
        addDetail(centerPanel, "12th Report Card:", studentData.get("12thReportCard"));
        addDetail(centerPanel, "Admission Order:", studentData.get("admission_order"));
        addDetail(centerPanel, "Transfer Certificate:", studentData.get("transfer_certificate"));
        addDetail(centerPanel, "Selected College:", studentData.get("SelectedCollege"));

        centerPanel.add(new JLabel("Selected Programs:"));
        String programs = studentData.get("SelectedPrograms");
        centerPanel.add(new JLabel(programs == null ? "" : programs));

        add(centerPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout(10, 10));
        bottomPanel.setBackground(new Color(240, 248, 255));

        JLabel confirmationLabel = new JLabel(
                "Your application has been successfully submitted.", JLabel.CENTER);
        confirmationLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        confirmationLabel.setForeground(new Color(34, 139, 34));
        bottomPanel.add(confirmationLabel, BorderLayout.CENTER);

        JButton closeButton = new JButton("Close");
        styleButton(closeButton);
        bottomPanel.add(closeButton, BorderLayout.SOUTH);
        add(bottomPanel, BorderLayout.SOUTH);

        // Save the completed application once, when this page is reached.
        Database.insertStudent(studentData);
    }

    private void addDetail(JPanel panel, String label, String value) {
        panel.add(new JLabel(label));
        panel.add(new JLabel(value == null ? "" : value));
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("SansSerif", Font.BOLD, 16));
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(65, 105, 225));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(70, 130, 180));
            }
        });

        button.addActionListener(e -> System.exit(0));
    }
}
