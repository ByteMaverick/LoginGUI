import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class App implements ActionListener {
    private static JLabel label;
    private static JLabel Question1;
    private static JPasswordField answer1;
    private static JButton button;
    private static JLabel success;
    private static JLabel Question2;
    private static JPasswordField answer2;
    private static  JLabel Question3;
    private static JPasswordField answer3;
    boolean if_correct_next = false;
    // JDBC details
    static String url = "jdbc:mysql://localhost:3306/account_info";
    static String user_database = "root";
    static String password_database ="science237";

    public static void passwordSet() {


        JFrame frame = new JFrame();
        JPanel panel = new JPanel(new FlowLayout());
        frame.setSize(300, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        label = new JLabel("Security Questions");
        label.setBounds(50, 20, 150, 30);
        panel.add(label);

        Question1 = new JLabel("Question: What is your Birth City");
        Question1.setBounds(10,50,70,70);
        panel.add(Question1);


        answer1 = new JPasswordField(20);
        answer1.setBounds(10,70,70,80);

        panel.add(answer1);

        Question2 = new JLabel("Question: What is your nickname");
        Question2.setBounds(10,70,70,70);
        panel.add(Question2);


        answer2 = new JPasswordField(20);
        answer2.setBounds(10,70,70,80);
        panel.add(answer2);

        Question3 = new JLabel("Question: What is your BirthYear");
        Question3.setBounds(10,90,70,70);
        panel.add(Question3);


        answer3 = new JPasswordField(20);
        answer3.setBounds(10,90,70,80);
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


        button = new JButton("Submit");
        button.setBounds(10,100,70,50);
        button.addActionListener(new App());
        panel.add(button);

        success = new JLabel();
        success.setBounds(100, 150, 300, 25);
        panel.add(success);

        frame.setVisible(true);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        String city = "riyadh";
        String answer1Text = answer1.getText();
        String nickname = "addu";
        String answer2Text = answer2.getText();
        String birthyear ="2005";
        String answer3Text = answer3.getText();
        if(answer1Text.toLowerCase().equals(city) && answer2Text.toLowerCase().equals(nickname) && answer3Text.toLowerCase().equals(birthyear)){
            if_correct_next = true;
            success.setText(" USER NAME = imohammed \n PASSWORD = IamIronMan" );
        }
        else{
            success.setText("Wrong Answer" );
        }
    }
    public static void user_name_check() {
        JFrame frame = new JFrame();
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel label = new JLabel("Username:");
        label.setBounds(20, 20, 80, 25);
        panel.add(label);

        JTextField username = new JTextField();
        username.setBounds(120, 20, 150, 25);

        JButton button = new JButton("Submit");
        button.setBounds(280, 20, 80, 25);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String enteredName = username.getText();
                String sql = "select username from accountinfo;";
                ArrayList <String> usernamelist = new ArrayList<>();
                try {
                    Connection conection = DriverManager.getConnection(url,user_database,password_database);
                    PreparedStatement preparedStatement = conection.prepareStatement(sql);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    while(resultSet.next()){
                        String usernames = resultSet.getString("username");
                        usernamelist.add(usernames);

                    }
                    for( String element: usernamelist){
                        if(enteredName.toLowerCase().equals(element)){
                            passwordSet();
                            frame.dispose();
                        }
                        else{
                            success.setText("Invalid Username");
                        }

                    }


                } catch (Exception ex){
                    ex.printStackTrace();
                }

            }
        });

        username.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    button.doClick();
                }
            }
        });

        panel.add(username);
        panel.add(button);

        success = new JLabel();
        success.setBounds(20, 80, 300, 25);
        panel.add(success);

        frame.add(panel);
        frame.setVisible(true);
    }
    public static void main(String[]args){
        passwordSet();

    }







}


