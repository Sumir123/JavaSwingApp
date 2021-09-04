package app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.StatementEvent;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import java.sql.Statement;
import app.Database.DbConnection;
import app.Database.StudentDao;

public class form implements ActionListener, KeyListener {
    JFrame frame;
    JLabel TitleLable, PhoneLable, passwordLable, FirstNameLabel, LastNameLabel, AddressLabel, hobbiesLabel,
            genderLabel, coursLabel, remarksLabel;
    JTextField firstNameField, lastNameField, addressField, phoneField;
    JCheckBox readingCheckBox, writingCheckBox, swimmingCheckBox, musicCheckBox;
    JRadioButton maleRadioButton, femaleRadioButton;
    JComboBox courseComboBox;
    JTextArea RemarksArea;
    ButtonGroup bgGender;
    JButton saveButton, deletebButton, updatebButton;
    JTable studentListTable;
    DefaultTableModel model;

    String[] columns = { "STUDENTID", "FIRST NAME ", "LAST NAME", "GENDER", "COURSE", "HOBBIES", "PHONE", "ADDRESS",
            "REMARKS" };
    JScrollPane scrollPane;

    public form() {
        frame = new JFrame("form");

        model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        studentListTable = new JTable();
        studentListTable.setModel(model);
        scrollPane = new JScrollPane(studentListTable);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(500, 80, 800, 400);

        // label
        TitleLable = new JLabel("Student Detail");
        FirstNameLabel = new JLabel("First Name:");
        LastNameLabel = new JLabel("Last Name:");
        AddressLabel = new JLabel(" Address:");
        PhoneLable = new JLabel("Phone:");
        hobbiesLabel = new JLabel("Hobbies:");
        genderLabel = new JLabel("Gender:");
        coursLabel = new JLabel("Course:");
        remarksLabel = new JLabel("Remarks:");

        // label Bonds
        TitleLable.setBounds(220, 50, 100, 30);
        FirstNameLabel.setBounds(100, 100, 100, 30);
        LastNameLabel.setBounds(100, 140, 100, 30);
        AddressLabel.setBounds(100, 180, 100, 30);
        PhoneLable.setBounds(100, 220, 100, 30);
        hobbiesLabel.setBounds(100, 250, 100, 30);
        genderLabel.setBounds(100, 280, 100, 30);
        coursLabel.setBounds(100, 320, 100, 30);
        remarksLabel.setBounds(100, 370, 200, 30);

        // field
        firstNameField = new JTextField();
        lastNameField = new JTextField();
        addressField = new JTextField();
        phoneField = new JTextField();
        RemarksArea = new JTextArea(10, 10);
        // field Bonds
        firstNameField.setBounds(170, 100, 200, 30);
        lastNameField.setBounds(170, 140, 200, 30);
        addressField.setBounds(170, 180, 200, 30);
        phoneField.setBounds(170, 220, 200, 30);
        RemarksArea.setBounds(170, 370, 200, 30);
        phoneField.addKeyListener(this);

        // checkbox
        readingCheckBox = new JCheckBox("Reading");
        readingCheckBox.setBounds(170, 250, 80, 30);
        writingCheckBox = new JCheckBox("Writing");
        writingCheckBox.setBounds(250, 250, 80, 30);
        swimmingCheckBox = new JCheckBox("Swiming");
        swimmingCheckBox.setBounds(330, 250, 80, 30);
        musicCheckBox = new JCheckBox("Music");
        musicCheckBox.setBounds(420, 250, 80, 30);
        // button
        saveButton = new JButton("Save");
        saveButton.setBounds(200, 450, 120, 30);
        saveButton.setActionCommand("save");
        saveButton.addActionListener(this);

        updatebButton = new JButton("update");
        updatebButton.setBounds(1050, 520, 100, 30);
        updatebButton.setActionCommand("update");
        updatebButton.addActionListener(this);

        deletebButton = new JButton("delete");
        deletebButton.setBounds(1200, 520, 100, 30);
        deletebButton.setActionCommand("delete");
        deletebButton.addActionListener(this);

        frame.add(updatebButton);
        frame.add(deletebButton);
        // radiobutton
        maleRadioButton = new JRadioButton("Male");
        maleRadioButton.setBounds(170, 280, 80, 30);
        maleRadioButton.setSelected(true);
        femaleRadioButton = new JRadioButton("Female");
        femaleRadioButton.setBounds(250, 280, 80, 30);
        bgGender = new ButtonGroup();
        bgGender.add(maleRadioButton);
        bgGender.add(femaleRadioButton);

        String course[] = { "BCA", "BBA", "CSIT", "BIT", "BIM" };

        courseComboBox = new JComboBox<>(course);
        courseComboBox.setBounds(200, 320, 120, 30);
        frame.add(courseComboBox);

        // add label
        frame.add(TitleLable);
        frame.add(FirstNameLabel);
        frame.add(LastNameLabel);
        frame.add(AddressLabel);
        frame.add(PhoneLable);
        frame.add(hobbiesLabel);
        frame.add(genderLabel);
        frame.add(remarksLabel);
        frame.add(saveButton);
        frame.add(coursLabel);
        frame.add(RemarksArea);
        // add checkbox
        frame.add(readingCheckBox);
        frame.add(writingCheckBox);
        frame.add(swimmingCheckBox);
        frame.add(musicCheckBox);
        // radio
        frame.add(maleRadioButton);
        frame.add(femaleRadioButton);

        // add field
        frame.add(firstNameField);
        frame.add(lastNameField);
        frame.add(addressField);
        frame.add(phoneField);
        frame.add(scrollPane);

        frame.setSize(1500, 1000);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        getstudentlist();

    }

    private void getstudentlist() {
        if (model.getRowCount() > 0) {
            model.setRowCount(0);
        }
        try {
            Connection connection = new DbConnection().getDbConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT STUDENTID,FIRSTNAME,LASTNAME,GENDER,COURSE,HOBBIES,PHONE,REMARKS,ADDRESS FROM STUDENTS");
            while (rs.next()) {
                model.addRow(new Object[] { rs.getInt("STUDENTID"), rs.getString("FIRSTNAME"), rs.getString("LASTNAME"),
                        rs.getString("GENDER"), rs.getString("COURSE"), rs.getString("HOBBIES"), rs.getString("PHONE"),
                        rs.getString("ADDRESS"), rs.getString("REMARKS") });
            }
            stmt.close();
            rs.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new form();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("save")) {

            String fName = firstNameField.getText();
            String lName = lastNameField.getText();
            String address = addressField.getText();
            String phone = phoneField.getText();
            String gender = maleRadioButton.isSelected() ? "male" : "female";
            String course = (String) courseComboBox.getSelectedItem();
            String remarks = RemarksArea.getText();
            List<String> hobbyList = new ArrayList<>();
            if (readingCheckBox.isSelected()) {
                hobbyList.add("reading");
            }
            if (writingCheckBox.isSelected()) {
                hobbyList.add("writing");
            }
            if (swimmingCheckBox.isSelected()) {
                hobbyList.add("swimming");
            }
            if (musicCheckBox.isSelected()) {
                hobbyList.add("music");
            }
            StringBuilder sb = new StringBuilder();
            for (String hobby : hobbyList) {
                sb.append(hobby);
                sb.append(",");
            }
            String hooby = sb.toString();

            frame.repaint();
            if (new StudentDao().addStudents(fName, lName, phone, address, gender, hooby, course, remarks)) {
                JOptionPane.showMessageDialog(frame, "Student Successfully Added", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                refreshFields();

            } else {
                JOptionPane.showMessageDialog(frame, "failed to add student", "Failed", JOptionPane.ERROR_MESSAGE);
            }

        } else if (e.getActionCommand().equals("delete")) {
            if (studentListTable.getSelectedRowCount() < 1) {
                JOptionPane.showMessageDialog(frame, "Please select a student to delete", "Failed",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                int studentId = (int) studentListTable.getValueAt(studentListTable.getSelectedRow(), 0);
                if (new StudentDao().deleteStudent(studentId)) {
                    JOptionPane.showMessageDialog(frame, "Student successfully deleted", "Succcess",
                            JOptionPane.INFORMATION_MESSAGE);
                    getstudentlist();
                } else {
                    JOptionPane.showMessageDialog(frame, "Failed to delete student", "Failed",
                            JOptionPane.ERROR_MESSAGE);
                }
            }

        } else if (e.getActionCommand().equals("update")) {
            saveButton.setActionCommand("UpdateStudent");
            int selectedRowCount = studentListTable.getSelectedRowCount();
            int selectedRow = studentListTable.getSelectedRow();

            if (selectedRowCount < 1) {
                JOptionPane.showMessageDialog(frame, "Please select a student to Update", "Failed",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                readingCheckBox.setSelected(false);
                writingCheckBox.setSelected(false);
                swimmingCheckBox.setSelected(false);
                musicCheckBox.setSelected(false);
                firstNameField.setText((String) studentListTable.getValueAt(selectedRow, 1));
                lastNameField.setText((String) studentListTable.getValueAt(selectedRow, 2));
                addressField.setText((String) studentListTable.getValueAt(selectedRow, 7));
                phoneField.setText((String) studentListTable.getValueAt(selectedRow, 6));
                RemarksArea.setText((String) studentListTable.getValueAt(selectedRow, 8));
                String gender = (String) studentListTable.getValueAt(selectedRow, 3);
                if (gender.equals("male")) {
                    maleRadioButton.setSelected(true);
                } else {
                    femaleRadioButton.setSelected(true);
                }
                String course = (String) studentListTable.getValueAt(selectedRow, 4);
                courseComboBox.setSelectedItem(course);

                String hobies = (String) studentListTable.getValueAt(selectedRow, 5);
                String[] hobbyArray = hobies.split(",");
                for (String hobby : hobbyArray) {
                    if (hobby.equals("reading")) {
                        readingCheckBox.setSelected(true);
                    } else if (hobby.equals("writing")) {
                        writingCheckBox.setSelected(true);
                    } else if (hobby.equals("swimming")) {
                        swimmingCheckBox.setSelected(true);
                    } else if (hobby.equals("music")) {
                        musicCheckBox.setSelected(true);
                    }

                }

            }
        } else if (e.getActionCommand().equals("UpdateStudent")) {

            String fName = firstNameField.getText();
            String lName = lastNameField.getText();
            String address = addressField.getText();
            String phone = phoneField.getText();
            String gender = maleRadioButton.isSelected() ? "male" : "female";
            String course = (String) courseComboBox.getSelectedItem();
            String remarks = RemarksArea.getText();
            List<String> hobbyList = new ArrayList<>();
            if (readingCheckBox.isSelected()) {
                hobbyList.add("reading");
            }
            if (writingCheckBox.isSelected()) {
                hobbyList.add("writing");
            }
            if (swimmingCheckBox.isSelected()) {
                hobbyList.add("swimming");
            }
            if (musicCheckBox.isSelected()) {
                hobbyList.add("music");
            }
            StringBuilder sb = new StringBuilder();
            for (String hobby : hobbyList) {
                sb.append(hobby);
                sb.append(",");
            }
            String hooby = sb.toString();

            frame.repaint();
            if (new StudentDao().updateStudents((int) studentListTable.getValueAt(studentListTable.getSelectedRow(), 0),
                    fName, lName, phone, address, gender, hooby, course, remarks)) {
                JOptionPane.showMessageDialog(frame, "Student Successfully Updated", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                refreshFields();
                getstudentlist();
            } else {
                JOptionPane.showMessageDialog(frame, "failed to Update student", "Failed", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void refreshFields() {
        firstNameField.setText("");
        lastNameField.setText("");
        addressField.setText("");
        phoneField.setText("");
        RemarksArea.setText("");
        courseComboBox.setSelectedIndex(0);
        readingCheckBox.setSelected(false);
        writingCheckBox.setSelected(false);
        swimmingCheckBox.setSelected(false);
        bgGender.clearSelection();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        char character = e.getKeyChar();
        if (phoneField.getText().length() < 10) {
            if (!Character.isDigit(character)) {
                e.consume();
            }
        } else {
            e.consume();
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }
}
