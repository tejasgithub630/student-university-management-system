package StudentAdmitManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CourseDetailsPage extends JPanel {
    private HashMap<String, String> studentData;
    private HashMap<String, String> adminData;

    public CourseDetailsPage(JFrame frame, HashMap<String, String> studentData, HashMap<String, String> adminData) {
        this.studentData = studentData;
        this.adminData = adminData;

        // Set background color
        setBackground(new Color(249, 242, 238));
        setLayout(new BorderLayout(20, 20));

        // Header
        JLabel headerLabel = new JLabel("Course Details", JLabel.CENTER);
        headerLabel.setFont(new Font("SansSerif", Font.BOLD, 36));
        headerLabel.setForeground(Color.BLACK); // Black title as requested
        add(headerLabel, BorderLayout.NORTH);

        // Center Panel for Colleges and Courses selection
        JPanel selectionPanel = new JPanel(new GridLayout(2, 1, 20, 20));
        selectionPanel.setBackground(new Color(249, 242, 238));
        selectionPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Colleges label and dropdown
        JLabel collegesLabel = new JLabel("Select College:");
        collegesLabel.setFont(new Font("SansSerif", Font.PLAIN, 24));
        collegesLabel.setForeground(Color.BLACK);
        selectionPanel.add(collegesLabel);

        JComboBox<String> collegesComboBox = new JComboBox<>(new String[]{
                "RV College of Engineering (RVCE)", "PES University (PESU)",
                "BMS College of Engineering (BMSCE)", "MS Ramaiah Institute of Technology (MSRIT)",
                "Dayananda Sagar College of Engineering (DSCE)", "Bangalore Institute of Technology (BIT)",
                "New Horizon College of Engineering (NHCE)", "Nitte Meenakshi Institute of Technology (NMIT)",
                "Sir M. Visvesvaraya Institute of Technology (MVIT)", "Christ University Faculty of Engineering",
                "Jain University School of Engineering and Technology", "CMR Institute of Technology (CMRIT)",
                "Acharya Institute of Technology", "RNS Institute of Technology (RNSIT)",
                "East Point College of Engineering and Technology", "BNM Institute of Technology (BNMIT)",
                "BMS Institute of Technology and Management", "Global Academy of Technology", "Reva University",
                "AMC Engineering College", "Sapthagiri College of Engineering", "Oxford College of Engineering",
                "Sri Venkateshwara College of Engineering (SVCE)", "Don Bosco Institute of Technology (DBIT)",
                "Alpha College of Engineering"
        });
        styleComboBox(collegesComboBox);
        selectionPanel.add(collegesComboBox);

        // Courses label
        JLabel coursesLabel = new JLabel("Select Your Program:");
        coursesLabel.setFont(new Font("SansSerif", Font.PLAIN, 24));
        coursesLabel.setForeground(Color.BLACK);
        selectionPanel.add(coursesLabel);

        // Programs selection with checkboxes
        JPanel coursesPanel = new JPanel(new GridLayout(15, 2));
        coursesPanel.setBackground(new Color(249, 242, 238));
        JScrollPane scrollPane = new JScrollPane(coursesPanel);
        scrollPane.setPreferredSize(new Dimension(400, 300));

        String[] courses = {
                "Computer Science and Engineering (CSE)", "Electronics and Communication Engineering (ECE)",
                "Electrical and Electronics Engineering (EEE)", "Mechanical Engineering (ME)", "Civil Engineering",
                "Information Technology (IT)", "Chemical Engineering", "Aerospace Engineering", "Automobile Engineering",
                "Biotechnology Engineering", "Artificial Intelligence and Machine Learning (AI & ML)", "Data Science and Engineering",
                "Cyber Security", "Internet of Things (IoT)", "Robotics and Automation", "Mechatronics Engineering",
                "Biomedical Engineering", "Environmental Engineering", "Industrial and Production Engineering",
                "Materials Science and Metallurgical Engineering", "Energy Engineering", "Renewable Energy Engineering",
                "Nanotechnology Engineering", "Mining Engineering", "Petroleum Engineering", "Marine Engineering",
                "Agricultural Engineering", "Software Engineering", "Big Data Analytics", "Embedded Systems Engineering"
        };

        List<JCheckBox> courseCheckBoxes = new ArrayList<>();
        for (String course : courses) {
            JCheckBox checkBox = new JCheckBox(course);
            checkBox.setBackground(new Color(249, 242, 238));
            checkBox.setFont(new Font("SansSerif", Font.PLAIN, 16));
            courseCheckBoxes.add(checkBox);
            coursesPanel.add(checkBox);
        }

        selectionPanel.add(scrollPane);
        add(selectionPanel, BorderLayout.CENTER);

        // Footer with buttons
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(249, 242, 238));

        JButton backButton = new JButton("Back");
        styleButton(backButton);
        backButton.addActionListener(e -> {
            frame.setContentPane(new DocumentUploadPage(frame, studentData, adminData));
            frame.revalidate();
        });

        JButton submitButton = new JButton("Submit");
        styleButton(submitButton);
        submitButton.addActionListener(e -> {
            String selectedCollege = (String) collegesComboBox.getSelectedItem();
            List<String> selectedCourses = new ArrayList<>();
            for (JCheckBox checkBox : courseCheckBoxes) {
                if (checkBox.isSelected()) {
                    selectedCourses.add(checkBox.getText());
                }
            }

            if (selectedCollege == null || selectedCollege.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please select a college.", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (selectedCourses.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please select at least one program.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                studentData.put("SelectedCollege", selectedCollege);
                studentData.put("SelectedPrograms", String.join(", ", selectedCourses));

                // Navigate to ApplicationStatusPage
                frame.setContentPane(new ApplicationStatusPage(frame, studentData, adminData));
                frame.revalidate();
            }
        });

        footerPanel.add(backButton);
        footerPanel.add(submitButton);
        add(footerPanel, BorderLayout.SOUTH);
    }

    // Utility method to style combo boxes
    private void styleComboBox(JComboBox<String> comboBox) {
        comboBox.setFont(new Font("SansSerif", Font.PLAIN, 16)); // Smaller font size
        comboBox.setPreferredSize(new Dimension(300, 25)); // Increased length and decreased width
        comboBox.setBackground(Color.WHITE);
        comboBox.setForeground(Color.BLACK);

        comboBox.setToolTipText("Click to select an option"); // Tooltip for interactivity
        comboBox.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    // Utility method to style buttons
    private void styleButton(JButton button) {
        button.setFont(new Font("SansSerif", Font.BOLD, 20));
        button.setBackground(new Color(94, 129, 172));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Add hover effect
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(129, 161, 193));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(94, 129, 172));
            }
        });
    }
}
