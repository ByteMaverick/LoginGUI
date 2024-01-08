import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.*;

public class GUILOGiN extends App implements ActionListener {
    private static JLabel label;
    private static JTextField userText;
    private static JLabel passwordLabel;
    private static JPasswordField passwordText;
    private static JButton button;
    private static JLabel success;

    private  static JButton passwordreset;
    // variables for JDBC
    String url = "jdbc:mysql://localhost:3306/account_info";
    String user_database = "root";
    String password_database ="science237";

    public static void login(){
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        frame.setSize(700, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(null);

        label = new JLabel("User:");
        label.setBounds(10, 23, 80, 25);
        panel.add(label);

        userText = new JTextField(20);
        userText.setBounds(80, 20, 165, 25);
        panel.add(userText);

        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 60, 80, 25);
        panel.add(passwordLabel);

        passwordText = new JPasswordField(20);
        passwordText.setBounds(80, 60, 165, 25);
        passwordText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    button.doClick();
                }
            }
        });
        panel.add(passwordText);

        button = new JButton("Login");
        button.setBounds(10, 100, 150, 25);
        button.addActionListener(new GUILOGiN());
        panel.add(button);

        success = new JLabel();
        success.setBounds(100, 150, 300, 25);
        panel.add(success);

        passwordreset = new JButton(" Forgot Password");
        passwordreset.setBounds(180, 100, 150, 25);
        passwordreset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                App.user_name_check();
                frame.dispose();

            }
        });
        panel.add(passwordreset);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String user = userText.getText();
        String password = new String(passwordText.getPassword());
        String sql = "select * from accountinfo where username = ?  and password =?";
        try (
                Connection connection = DriverManager.getConnection(url,user_database,password_database);
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
                ){
                preparedStatement.setString(1,user);
                preparedStatement.setString(2,password);
                try (ResultSet resultSet = preparedStatement.executeQuery()){
                    if (resultSet.next()) {
                        String login = "Logged in";
                        success.setText(login);
                    }
                    else {
                        String login = "Invalid login";
                        success.setText(login);
                    }

                }
        } catch (SQLException ex){
            ex.printStackTrace();
        }

    }
    public static void main (String[]args){
        login();

    }



}
