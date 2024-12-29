package StudentAdmitManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class LoginPage extends JPanel {
    private HashMap<String, String> studentData;
    private HashMap<String, String> adminData;

    public LoginPage(JFrame frame, HashMap<String, String> adminData) {
        this.studentData = new HashMap<String, String>();//studentData;
        this.adminData = adminData;

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);

        // Set background color to the specified RGB values
        setBackground(new Color(249, 242, 238));

        // Header
        JLabel titleLabel = new JLabel("Student Admit Management System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
        titleLabel.setForeground(Color.BLACK); // Set text color for contrast
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titleLabel, gbc);

        // Login Options
        JRadioButton studentButton = new JRadioButton("Student");
        JRadioButton adminButton = new JRadioButton("Admin");
        ButtonGroup group = new ButtonGroup();
        group.add(studentButton);
        group.add(adminButton);
        studentButton.setFont(new Font("Arial", Font.PLAIN, 20));
        adminButton.setFont(new Font("Arial", Font.PLAIN, 20));
        studentButton.setForeground(Color.BLACK); // Set text color
        adminButton.setForeground(Color.BLACK); // Set text color
        studentButton.setOpaque(false); // Make background transparent
        adminButton.setOpaque(false); // Make background transparent
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(studentButton, gbc);
        gbc.gridx = 1;
        add(adminButton, gbc);

        // Username and Password
        JLabel userLabel = new JLabel("Name:");
        JTextField userField = new JTextField(20);
        JLabel passLabel = new JLabel("Password:");
        JPasswordField passField = new JPasswordField(20);
        userLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        passLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        userField.setFont(new Font("Arial", Font.PLAIN, 20));
        passField.setFont(new Font("Arial", Font.PLAIN, 20));
        userLabel.setForeground(Color.BLACK); // Set text color
        passLabel.setForeground(Color.BLACK); // Set text color
        passField.setText("");

        Dimension fieldSize = new Dimension(200, 30);
        userField.setPreferredSize(fieldSize);
        passField.setPreferredSize(fieldSize);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        add(userLabel, gbc);
        gbc.gridx = 1;
        add(userField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(passLabel, gbc);
        gbc.gridx = 1;
        add(passField, gbc);

        // Next Button
        JButton nextButton = new JButton("Next");
        nextButton.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(nextButton, gbc);

        // Action Listener for Next Button
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userField.getText();
                String password = new String(passField.getPassword());

                if (studentButton.isSelected()) {
                    if ("P@ssw0rd@#".equals(password)) {

                        if (Database.userExists(username)) {
                            // Retrieve the user data and navigate to ApplicationStatusPage
                             studentData = Database.getUserData(username);
                            JOptionPane.showMessageDialog(frame, "Welcome back, " + username + "!");
                            frame.setContentPane(new ApplicationStatusPage(frame, studentData, adminData));
                        }
                        else {
                            studentData.put(username, password);
                            JOptionPane.showMessageDialog(frame, "Welcome, Student " + username);
                            frame.setContentPane(new PersonalDetailsPage(frame, studentData, adminData));

                        }
                        frame.revalidate();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Invalid Student password", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else if (adminButton.isSelected()) {
                    if ("admin1".equals(username) && "adminpassword1".equals(password) ||
                            "admin2".equals(username) && "adminpassword2".equals(password)) {
                        JOptionPane.showMessageDialog(frame, "Welcome, Admin " + username);
                        frame.setContentPane(new PersonalDetailsPage(frame, studentData, adminData));
                        frame.revalidate();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Invalid Admin credentials", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Please select Student or Admin", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
