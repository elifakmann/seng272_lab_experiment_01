import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class ProjectFormPanel extends JPanel {

    JTextField projectNameField, teamLeaderField, startDateField;
    JComboBox<String> teamSizeBox, projectTypeBox;
    JButton saveButton, clearButton;

    public ProjectFormPanel() {
        setBackground(new Color(255, 228, 236)); // soft pembe
        setOpaque(true);

        setLayout(new GridLayout(7, 2, 5, 5));

        add(new JLabel("Project Name:"));
        projectNameField = new JTextField();
        add(projectNameField);

        add(new JLabel("Team Leader:"));
        teamLeaderField = new JTextField();
        add(teamLeaderField);

        add(new JLabel("Team Size:"));
        String[] sizes = {"1-3", "4-6", "7-10", "10+"};
        teamSizeBox = new JComboBox<>(sizes);
        add(teamSizeBox);

        add(new JLabel("Project Type:"));
        String[] types = {"Web", "Mobile", "Desktop", "API"};
        projectTypeBox = new JComboBox<>(types);
        add(projectTypeBox);

        add(new JLabel("Start Date:"));
        startDateField = new JTextField();
        add(startDateField);

        saveButton = new JButton("Save");
        saveButton.setBackground(new Color(135, 1, 131));

        clearButton = new JButton("Clear");
        clearButton.setBackground(new Color(133, 19, 96));

        add(saveButton);
        add(clearButton);
        saveButton.setBackground(new Color(255, 182, 193));
        saveButton.setForeground(Color.WHITE);

        clearButton.setBackground(new Color(255, 105, 180));
        clearButton.setForeground(Color.WHITE);

        // 🔹 <3 SAVE <3
        saveButton.addActionListener(e -> {

            String name = projectNameField.getText();
            String leader = teamLeaderField.getText();
            String date = startDateField.getText();

            String size = (String) teamSizeBox.getSelectedItem();
            String type = (String) projectTypeBox.getSelectedItem();

            if (name.isEmpty() || leader.isEmpty() || date.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields!");
                return;
            }

            try (FileWriter writer = new FileWriter("projects.txt", true)) {

                writer.write("=== Project Entry ===\n");
                writer.write("Project Name  : " + name + "\n");
                writer.write("Team Leader   : " + leader + "\n");
                writer.write("Team Size     : " + size + "\n");
                writer.write("Project Type  : " + type + "\n");
                writer.write("Start Date    : " + date + "\n ");
                writer.write("Record Time   : " + LocalDateTime.now() + "\n");
                writer.write("====================\n\n");

                JOptionPane.showMessageDialog(this, "Saved successfully!");

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        // 🔹 ^^ CLEAR ^^
        clearButton.addActionListener(e -> {
            projectNameField.setText("");
            teamLeaderField.setText("");
            startDateField.setText("");

            teamSizeBox.setSelectedIndex(0);
            projectTypeBox.setSelectedIndex(0);
        });
    }
}