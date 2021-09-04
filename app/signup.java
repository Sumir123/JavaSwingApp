package app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import app.Database.StudentDao;

public class signup implements ActionListener {
    JFrame frame;
    JLabel signupLable, usernameLable, passwordLable, showLabel;
    JTextField usernameField;
    JPasswordField passwordField;
    JButton signupButton, signupbButton;

    public signup() {
        frame = new JFrame("sign up");

        // label
        signupLable = new JLabel("SIGN UP");
        usernameLable = new JLabel("Username:");
        passwordLable = new JLabel("Password:");

        // label Bonds
        signupLable.setBounds(220, 50, 100, 30);
        usernameLable.setBounds(100, 180, 100, 30);
        passwordLable.setBounds(100, 220, 100, 30);

        // field
        usernameField = new JTextField();
        passwordField = new JPasswordField();

        // field Bonds
        usernameField.setBounds(170, 180, 200, 30);
        passwordField.setBounds(170, 220, 200, 30);

        signupButton = new JButton("SIGN UP");
        signupButton.setBounds(200, 300, 120, 30);
        signupButton.setActionCommand("signup");
        signupButton.addActionListener(this);

        // add label
        frame.add(signupLable);
        frame.add(usernameLable);
        frame.add(passwordLable);
        frame.add(signupButton);
        // add field
        frame.add(usernameField);
        frame.add(passwordField);

        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    public static void main(String[] args) {
        new signup();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = usernameField.getText();
        String password = passwordField.getText();
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Some field Empty", "Incomplete Details", JOptionPane.ERROR_MESSAGE);
        } else {
            if (new StudentDao().addUser(username, password)) {
                new login();
                JOptionPane.showMessageDialog(frame, "User Successfully registered", "Success",
                        JOptionPane.INFORMATION_MESSAGE);

            } else {
                JOptionPane.showMessageDialog(frame, "User registeration failed", "ERROR", JOptionPane.ERROR_MESSAGE);

            }
        }
    }

}
