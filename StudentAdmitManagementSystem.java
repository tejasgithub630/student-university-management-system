package StudentAdmitManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class StudentAdmitManagementSystem {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Student Admit Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximize the window
        frame.setLayout(new BorderLayout());

        // Create the user data
        HashMap<String, String> studentData = new HashMap<>();
        studentData.put("student1", "password1");
        studentData.put("student2", "password2");
        // Add as many students as needed

        HashMap<String, String> adminData = new HashMap<>();
        adminData.put("admin1", "adminpassword1");
        adminData.put("admin2", "adminpassword2");

        // Start with the login page
        frame.add(new LoginPage(frame, adminData), BorderLayout.CENTER);

        frame.setVisible(true);
    }
}
