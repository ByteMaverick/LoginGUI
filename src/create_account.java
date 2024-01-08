import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class create_account implements ActionListener {
    private static JLabel label_title;
    private static JLabel label_username;
    private static JLabel label_subtitle;
    private static JLabel Question1;
    private static JTextField answer1;
    private static JLabel Question2;
    private static JTextField answer2;
    private static JLabel Question3;
    private static JTextField answer3;
    private static JButton button;

    public static void account_creation() {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 600);  // Adjusted window size
        frame.add(panel);
        panel.setLayout(null);

        label_title = new JLabel("Hi, Welcome!");
        label_title.setBounds(150, 20, 200, 30);  // Adjusted bounds
        panel.add(label_title);

        label_username = new JLabel("Username:");
        label_username.setBounds(10, 60, 80, 25);  // Adjusted bounds
        panel.add(label_username);

        JTextField text_username = new JTextField();
        text_username.setBounds(100, 60, 300, 25);  // Adjusted bounds
        panel.add(text_username);

        JLabel label_password = new JLabel("Password:");
        label_password.setBounds(10, 90, 80, 25);  // Adjusted bounds
        panel.add(label_password);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(100, 90, 300, 25);
        panel.add(passwordField);

        JLabel label_retype_password = new JLabel("Retype Password:");
        panel.add(label_retype_password);

        JPasswordField retype_passwordField = new JPasswordField();
        retype_passwordField.setBounds(130, 120, 270, 25);
        panel.add(retype_passwordField);

        JLabel security_questions = new JLabel("If you forget your password,you can recover it by answering 3 questions ");
        security_questions.setBounds(10, 150, 480, 40);
        panel.add(security_questions);

        label_subtitle = new JLabel("Security Questions");
        label_subtitle.setBounds(150, 190, 150, 30);
        panel.add(label_subtitle);

        Question1 = new JLabel("Question: What is your Birth City");
        Question1.setBounds(10, 220, 300, 20);
        panel.add(Question1);

        answer1 = new JTextField(20);
        answer1.setBounds(10, 240, 300, 25);
        panel.add(answer1);

        Question2 = new JLabel("Question: What is your nickname");
        Question2.setBounds(10, 270, 300, 20);
        panel.add(Question2);

        answer2 = new JTextField(20);
        answer2.setBounds(10, 290, 300, 25);
        panel.add(answer2);

        Question3 = new JLabel("Question: What is your BirthYear");
        Question3.setBounds(10, 320, 300, 20);
        panel.add(Question3);

        answer3 = new JTextField(20);
        answer3.setBounds(10, 340, 300, 25);
        answer3.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    button.doClick();
                }
            }
        });
        panel.add(answer3);

        button = new JButton("Create Account");
        button.setBounds(150,370,150,25);
        button.addActionListener(new create_account());
        panel.add(button);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
