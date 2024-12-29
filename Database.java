package StudentAdmitManagementSystem;

import java.sql.*;
import java.util.HashMap;

public class Database {
    private static final String URL = "jdbc:sqlite:C:\\Users\\Admin\\Desktop\\Database\\data.db";

    // Connect to the database
    public static Connection connect() {
        try {
            return DriverManager.getConnection(URL);
        } catch (SQLException e) {
            System.err.println("Connection failed: " + e.getMessage());
            return null;
        }
    }

    // Create the student_admissions table
    public static void createTables() {
        String sql = """
            CREATE TABLE IF NOT EXISTS student_admissions (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT NOT NULL,
                contact TEXT NOT NULL,
                email TEXT NOT NULL,
                dob_day TEXT,
                dob_month TEXT,
                dob_year TEXT,
                gender TEXT,
                photo TEXT,
                report_card_10th TEXT,
                report_card_12th TEXT,
                admission_order TEXT,
                transfer_certificate TEXT,
                selected_college TEXT,
                selected_programs TEXT
            );
        """;

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tables created successfully.");
        } catch (SQLException e) {
            System.err.println("Table creation failed: " + e.getMessage());
        }
    }

    // Insert student data into student_admissions table
    public static void insertStudent(HashMap<String, String> studentData) {
        String sql = """
            INSERT INTO student_admissions(
                name, contact, email, dob_day, dob_month, dob_year, gender, photo, 
                report_card_10th, report_card_12th, admission_order, transfer_certificate, 
                selected_college, selected_programs
            ) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);
        """;

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, studentData.get("Name"));
            pstmt.setString(2, studentData.get("ContactNumber"));
            pstmt.setString(3, studentData.get("EmailAddress"));
            pstmt.setString(4, studentData.get("Day"));
            pstmt.setString(5, studentData.get("Month"));
            pstmt.setString(6, studentData.get("Year"));
            pstmt.setString(7, studentData.get("Gender"));
            pstmt.setString(8, studentData.get("photo"));
            pstmt.setString(9, studentData.get("10thReportCard"));
            pstmt.setString(10, studentData.get("12thReportCard"));
            pstmt.setString(11, studentData.get("admission_order"));
            pstmt.setString(12, studentData.get("transfer_certificate"));
            pstmt.setString(13, studentData.get("SelectedCollege"));
            pstmt.setString(14, studentData.get("SelectedPrograms"));

            pstmt.executeUpdate();
            System.out.println("Data inserted successfully.");
        } catch (SQLException e) {
            System.err.println("Insertion failed: " + e.getMessage());
        }
    }

    // Check if user exists in the database
    public static boolean userExists(String username) {
        String sql = "SELECT 1 FROM student_admissions WHERE name = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.err.println("User existence check failed: " + e.getMessage());
        }
        return false;
    }

    // Get user data from the database
    public static HashMap<String, String> getUserData(String username) {
        HashMap<String, String> userData = new HashMap<>();
        String sql = "SELECT * FROM student_admissions WHERE name = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                userData.put("Name", rs.getString("name"));
                userData.put("ContactNumber", rs.getString("contact"));
                userData.put("EmailAddress", rs.getString("email"));
                userData.put("Day", rs.getString("dob_day"));
                userData.put("Month", rs.getString("dob_month"));
                userData.put("Year", rs.getString("dob_year"));
                userData.put("Gender", rs.getString("gender"));
                userData.put("photo", rs.getString("photo"));
                userData.put("10thReportCard", rs.getString("report_card_10th"));
                userData.put("12thReportCard", rs.getString("report_card_12th"));
                userData.put("admission_order", rs.getString("admission_order"));
                userData.put("transfer_certificate", rs.getString("transfer_certificate"));
                userData.put("SelectedCollege", rs.getString("selected_college"));
                userData.put("SelectedPrograms", rs.getString("selected_programs"));
            }
        } catch (SQLException e) {
            System.err.println("User data retrieval failed: " + e.getMessage());
        }
        return userData;
    }

    // Verify inserted data
    public static void verifyInsertedData() {
        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery("SELECT * FROM student_admissions LIMIT 5"); // Check the first 5 records
            while (rs.next()) {
                System.out.println(rs.getInt("id") + ": " + rs.getString("name"));
            }
        } catch (SQLException e) {
            System.err.println("Error verifying data: " + e.getMessage());
        }
    }

    // Main method for CMD arguments
    public static void main(String[] args) {
        if (args.length != 14) { // Expect 14 arguments
            System.out.println("Usage: java Database <name> <contact> <email> <dob_day> <dob_month> <dob_year> <gender> <photo> <report_card_10th> <report_card_12th> <admission_order> <transfer_certificate> <selected_college> <selected_programs>");
            return;
        }

        createTables(); // Ensure the table exists

        // Prepare student data from arguments
        HashMap<String, String> studentData = new HashMap<>();
        studentData.put("Name", args[0]);
        studentData.put("ContactNumber", args[1]);
        studentData.put("EmailAddress", args[2]);
        studentData.put("Day", args[3]);
        studentData.put("Month", args[4]);
        studentData.put("Year", args[5]);
        studentData.put("Gender", args[6]);
        studentData.put("photo", args[7]);
        studentData.put("10thReportCard", args[8]);
        studentData.put("12thReportCard", args[9]);
        studentData.put("admission_order", args[10]);
        studentData.put("transfer_certificate", args[11]);
        studentData.put("SelectedCollege", args[12]);
        studentData.put("SelectedPrograms", args[13]);

        insertStudent(studentData); // Insert the provided data

        // Verify the inserted data
        verifyInsertedData();
    }
}
