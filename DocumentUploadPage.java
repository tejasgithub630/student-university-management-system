package StudentAdmitManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.HashMap;

public class DocumentUploadPage extends JPanel {
    private HashMap<String, String> studentData;
    private HashMap<String, String> adminData;
    private JLabel previewLabel;

    public DocumentUploadPage(JFrame frame, HashMap<String, String> studentData, HashMap<String, String> adminData) {
        this.studentData = studentData;
        this.adminData = adminData;

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);

        // Set background color
        setBackground(new Color(249, 242, 238));

        // Header
        JLabel headerLabel = new JLabel("Documents Upload");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 30));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        add(headerLabel, gbc);

        // Add document upload sections
        addDocumentUploadSection(gbc, "10th Report Card:", "10thReportCard", 1);
        addDocumentUploadSection(gbc, "12th Report Card:", "12thReportCard", 2);
        addDocumentUploadSection(gbc, "Admission Order:", "AdmissionOrder", 3);
        addDocumentUploadSection(gbc, "Transfer Certificate:", "TransferCertificate", 4);

        // Preview Section
        previewLabel = new JLabel();
        previewLabel.setPreferredSize(new Dimension(200, 200));
        previewLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        previewLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center align the preview
        previewLabel.setText("No Preview"); // Default empty message
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridheight = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        add(previewLabel, gbc);

        // Back Button
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(backButton, gbc);

        // Next Button
        JButton nextButton = new JButton("Next");
        nextButton.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(nextButton, gbc);

        // Action Listener for Back Button
        backButton.addActionListener(e -> {
            // Navigate back to the Personal Details page
            frame.setContentPane(new PersonalDetailsPage(frame, studentData, adminData));
            frame.revalidate();
        });

        // Action Listener for Next Button
        nextButton.addActionListener(e -> {
            // Validate if all documents are uploaded
            if (areAllDocumentsUploaded()) {
                // Display success message
                JOptionPane.showMessageDialog(frame, "All documents uploaded successfully!");
                // Navigate to the Course Details page
                frame.setContentPane(new CourseDetailsPage(frame, studentData, adminData));
                frame.revalidate();
            } else {
                // Display error message if some documents are missing
                JOptionPane.showMessageDialog(frame, "Please upload all required documents.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Update the UI with previously uploaded data and images
        updateUploadedFiles();
    }

    private void addDocumentUploadSection(GridBagConstraints gbc, String labelText, String documentType, int gridy) {
        JLabel label = new JLabel(labelText);
        JButton uploadButton = new JButton("Upload");

        // Set styles
        label.setFont(new Font("Arial", Font.PLAIN, 20));
        uploadButton.setFont(new Font("Arial", Font.PLAIN, 18));

        // Add label to layout
        gbc.gridx = 0;
        gbc.gridy = gridy;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(label, gbc);

        // Add upload button to layout
        gbc.gridx = 1;
        gbc.gridy = gridy;
        gbc.anchor = GridBagConstraints.WEST;
        add(uploadButton, gbc);

        // Action Listener for the Upload Button
        uploadButton.addActionListener(e -> handleFileUpload(documentType));
    }

    private void handleFileUpload(String documentType) {
        JFileChooser fileChooser = new JFileChooser();
        // Set file filter to allow only images or documents
        fileChooser.setFileFilter(new FileNameExtensionFilter("Documents and Images", "jpg", "jpeg", "png", "pdf"));

        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            studentData.put(documentType, selectedFile.getAbsolutePath());

            // If the uploaded file is an image, display it in the preview section
            if (isImageFile(selectedFile)) {
                ImageIcon icon = new ImageIcon(selectedFile.getAbsolutePath());
                Image img = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                previewLabel.setIcon(new ImageIcon(img));
                previewLabel.setText(""); // Remove "No Preview" text
            } else {
                previewLabel.setIcon(null); // Clear preview if not an image
                previewLabel.setText("Preview not available");
            }

            JOptionPane.showMessageDialog(null, "File " + selectedFile.getName() + " uploaded successfully!");
        }
    }

    private boolean isImageFile(File file) {
        String[] imageExtensions = {"jpg", "jpeg", "png"};
        String fileName = file.getName().toLowerCase();
        for (String extension : imageExtensions) {
            if (fileName.endsWith(extension)) {
                return true;
            }
        }
        return false;
    }

    private boolean areAllDocumentsUploaded() {
        // Check if all required documents are uploaded
        return studentData.containsKey("10thReportCard") && studentData.containsKey("12thReportCard")
                && studentData.containsKey("AdmissionOrder") && studentData.containsKey("TransferCertificate");
    }

    private void updateUploadedFiles() {
        for (String documentType : studentData.keySet()) {
            String filePath = studentData.get(documentType);
            if (filePath != null) {
                File file = new File(filePath);

                // Update preview if it's an image
                if (isImageFile(file)) {
                    ImageIcon icon = new ImageIcon(filePath);
                    Image img = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                    previewLabel.setIcon(new ImageIcon(img));
                    previewLabel.setText(""); // Remove "No Preview" text
                } else {
                    previewLabel.setIcon(null);
                    previewLabel.setText("Preview not available");
                }
                // Break after setting the preview for the latest document
                break;
            }
        }
    }
}
