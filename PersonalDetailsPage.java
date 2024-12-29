package StudentAdmitManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.io.File;  // <-- Add this import
import java.util.HashMap;
import java.util.stream.IntStream;

public class PersonalDetailsPage extends JPanel {
    private HashMap<String, String> studentData;
    private HashMap<String, String> adminData;
    private JLabel pictureDisplayLabel;

    public PersonalDetailsPage(JFrame frame, HashMap<String, String> studentData, HashMap<String, String> adminData) {
        this.studentData = studentData;
        this.adminData = adminData;

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);

        // Set background color
        setBackground(new Color(249, 242, 238));

        // Header
        JLabel headerLabel = new JLabel("Personal Details");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 30));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(headerLabel, gbc);

        // Name
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField(20);
        nameField.setText(studentData.getOrDefault("Name", ""));
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        nameField.setFont(new Font("Arial", Font.PLAIN, 20));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(nameLabel, gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(nameField, gbc);

        // Contact Number
        JLabel contactLabel = new JLabel("Contact Number:");
        JTextField contactField = new JTextField(20);
        contactField.setText(studentData.getOrDefault("ContactNumber", ""));
        contactField.setFont(new Font("Arial", Font.PLAIN, 20));
        contactField.setDocument(new JTextFieldLimit(10, true)); // Limits to 10 digits and allows only numbers
        contactLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        add(contactLabel, gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(contactField, gbc);

        // Email Address
        JLabel emailLabel = new JLabel("Email Address:");
        JTextField emailField = new JTextField(20);
        emailField.setText(studentData.getOrDefault("EmailAddress", ""));
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        emailField.setFont(new Font("Arial", Font.PLAIN, 20));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        add(emailLabel, gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(emailField, gbc);

        // Gender
        JLabel genderLabel = new JLabel("Gender:");
        JRadioButton maleButton = new JRadioButton("Male");
        JRadioButton femaleButton = new JRadioButton("Female");
        String gender = studentData.getOrDefault("Gender", "");
        if (gender.equals("Male")) maleButton.setSelected(true);
        else if (gender.equals("Female")) femaleButton.setSelected(true);
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleButton);
        genderGroup.add(femaleButton);
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        genderPanel.setBackground(new Color(249, 242, 238));
        genderPanel.add(maleButton);
        genderPanel.add(femaleButton);
        genderLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        maleButton.setFont(new Font("Arial", Font.PLAIN, 20));
        femaleButton.setFont(new Font("Arial", Font.PLAIN, 20));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.EAST;
        add(genderLabel, gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(genderPanel, gbc);

        // Date of Birth
        JLabel dobLabel = new JLabel("Date of Birth:");
        dobLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        // Day Dropdown (1 to 31)
        JComboBox<Integer> dayDropdown = new JComboBox<>(
                IntStream.rangeClosed(1, 31).boxed().toArray(Integer[]::new)
        );
        dayDropdown.setFont(new Font("Arial", Font.PLAIN, 20));
        if (studentData.containsKey("Day")) {
            dayDropdown.setSelectedItem(Integer.parseInt(studentData.get("Day")));
        }

        // Month Dropdown (1 to 12)
        JComboBox<Integer> monthDropdown = new JComboBox<>(
                IntStream.rangeClosed(1, 12).boxed().toArray(Integer[]::new)
        );
        monthDropdown.setFont(new Font("Arial", Font.PLAIN, 20));
        if (studentData.containsKey("Month")) {
            monthDropdown.setSelectedItem(Integer.parseInt(studentData.get("Month")));
        }

        // Year Dropdown (1900 to Current Year)
        JComboBox<Integer> yearDropdown = new JComboBox<>(
                IntStream.rangeClosed(1900, java.util.Calendar.getInstance().get(java.util.Calendar.YEAR))
                        .boxed().toArray(Integer[]::new)
        );
        yearDropdown.setFont(new Font("Arial", Font.PLAIN, 20));
        if (studentData.containsKey("Year")) {
            yearDropdown.setSelectedItem(Integer.parseInt(studentData.get("Year")));
        }

        JPanel dobPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        dobPanel.setBackground(new Color(249, 242, 238));
        dobPanel.add(dayDropdown);
        dobPanel.add(monthDropdown);
        dobPanel.add(yearDropdown);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.EAST;
        add(dobLabel, gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(dobPanel, gbc);

        // Photo Upload
        JLabel photoLabel = new JLabel("Upload Photo:");
        photoLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        JButton photoUploadButton = new JButton("Upload Photo");
        pictureDisplayLabel = new JLabel();
        pictureDisplayLabel.setPreferredSize(new Dimension(150, 150));
        pictureDisplayLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        String picturePath = studentData.get("Picture");
        if (picturePath != null) {
            setPicture(picturePath);
        }

        photoUploadButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                studentData.put("Picture", selectedFile.getAbsolutePath());
                setPicture(selectedFile.getAbsolutePath());
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.EAST;
        add(photoLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(photoUploadButton, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridheight = 6;
        gbc.anchor = GridBagConstraints.CENTER;
        add(pictureDisplayLabel, gbc);

        // Next Button
        JButton nextButton = new JButton("Next");
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        add(nextButton, gbc);

        // Action Listener for Next Button
        nextButton.addActionListener(e -> {
            // Validate fields
            if (nameField.getText().isEmpty() || contactField.getText().isEmpty() || emailField.getText().isEmpty()
                    || (!maleButton.isSelected() && !femaleButton.isSelected())
                    || dayDropdown.getSelectedItem() == null || monthDropdown.getSelectedItem() == null || yearDropdown.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(frame, "Please fill all fields before proceeding!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                studentData.put("Name", nameField.getText());
                studentData.put("ContactNumber", contactField.getText());
                studentData.put("EmailAddress", emailField.getText());
                studentData.put("Gender", maleButton.isSelected() ? "Male" : "Female");
                studentData.put("Day", String.valueOf(dayDropdown.getSelectedItem()));
                studentData.put("Month", String.valueOf(monthDropdown.getSelectedItem()));
                studentData.put("Year", String.valueOf(yearDropdown.getSelectedItem()));

                frame.setContentPane(new DocumentUploadPage(frame, studentData, adminData));
                frame.revalidate();
            }
        });
    }

    private void setPicture(String path) {
        ImageIcon icon = new ImageIcon(path);
        Image image = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        pictureDisplayLabel.setIcon(new ImageIcon(image));
    }

    // Helper class to limit JTextField input length and allow only numbers
    class JTextFieldLimit extends javax.swing.text.PlainDocument {
        private int limit;
        private boolean numbersOnly;

        JTextFieldLimit(int limit, boolean numbersOnly) {
            super();
            this.limit = limit;
            this.numbersOnly = numbersOnly;
        }

        public void insertString(int offset, String str, javax.swing.text.AttributeSet attr) throws javax.swing.text.BadLocationException {
            if (str == null) return;
            if ((getLength() + str.length()) <= limit) {
                if (!numbersOnly || str.matches("\\d+")) {
                    super.insertString(offset, str, attr);
                }
            }
        }
    }
}
