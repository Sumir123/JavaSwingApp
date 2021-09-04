package app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import app.Database.StudentDao;

import javax.swing.JOptionPane;

public class login implements ActionListener, WindowListener, MouseListener {
    JFrame frame;

    JLabel loginLable, usernameLable, passwordLable, showLabel;
    JTextField usernameField;
    JPasswordField passwordField;
    JButton logiButton, signupbButton;

    public login() {
        frame = new JFrame("Login Form");
        frame.addWindowListener(this);

        loginLable = new JLabel("LOG IN");
        loginLable.setBounds(250, 20, 100, 30);

        usernameLable = new JLabel("Username:");
        usernameLable.setBounds(150, 100, 100, 30);

        usernameField = new JTextField();
        usernameField.setBounds(220, 100, 200, 30);

        passwordLable = new JLabel("Password:");
        passwordLable.setBounds(150, 140, 100, 30);

        passwordField = new JPasswordField();
        passwordField.setBounds(220, 140, 200, 30);
        passwordField.setEchoChar('*');

        showLabel = new JLabel("Show");
        showLabel.setBounds(430, 140, 200, 30);
        showLabel.addMouseListener(this);

        logiButton = new JButton("Log in");
        logiButton.setBounds(200, 200, 80, 30);
        logiButton.setActionCommand("login");
        logiButton.addActionListener(this);

        signupbButton = new JButton("Sign up");
        signupbButton.setBounds(300, 200, 80, 30);
        signupbButton.setActionCommand("signup");
        signupbButton.addActionListener(this);

        frame.add(usernameField);
        frame.add(usernameLable);
        frame.add(passwordLable);
        frame.add(passwordField);
        frame.add(showLabel);
        frame.add(loginLable);
        frame.add(logiButton);
        frame.add(signupbButton);

        frame.setSize(600, 500);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    public static void main(String[] args) {
        new login();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("signup")) {
            this.frame.dispose();
            new signup();
        }
        if (e.getActionCommand().equals("login")) {
            String username = usernameField.getText();
            String password = passwordField.getText();
            if (new StudentDao().checkValidUser(username,password)) {
                this.frame.dispose();
                new form();
            } else {
                JOptionPane.showMessageDialog(frame, "Incorrect username or password", "Swing Tester",
                        JOptionPane.ERROR_MESSAGE);
            }

        }

    }

    @Override
    public void windowOpened(WindowEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowClosing(WindowEvent e) {
        int option = JOptionPane.showOptionDialog(frame, "Are you sure to exit?", "Conformation",
                JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, JOptionPane.YES_OPTION);
        if (option == 0) {
            System.exit(0);
        } else {
            frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        passwordField.setEchoChar((char) 0);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        passwordField.setEchoChar(('*'));

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowClosed(WindowEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowIconified(WindowEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowActivated(WindowEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        // TODO Auto-generated method stub

    }

}
