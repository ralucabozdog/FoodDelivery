package presentation.general;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Clasa pentru crearea primei ferestre vizualizate de user la deschiderea aplicatiei, fereastra de log-in
 *
 * @author Raluca - Delia Bozdog
 * @since 25.05.2021
 */
public class LogInFrame extends JFrame {
    /**
     * JPanel-ul ferestrei
     */
    private JPanel panel;
    /**
     * Eticheta ce contine mesajul de bun-venit
     */
    private JLabel welcomeMessage;
    /**
     * JTextField-ul ce contine numele de utilizator
     */
    private JTextField username;
    /**
     * JPasswordField ce contine parola utilizatorului
     */
    private JPasswordField password;
    /**
     * Buton de log in
     */
    private JButton logInButton;
    /**
     * Buton de deschidere a ferestrei de creare a unui nou client
     */
    private JButton newClientButton;

    /**
     * Constructorul clasei LogInFrame
     */
    public LogInFrame() {
        setTitle("Food delivery");
        setBounds(100, 100, 500, 585);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setBackground(new Color(186, 200, 222));
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(panel);
        panel.setLayout(null);

        welcomeMessage = new JLabel("Welcome to our food delivery!");
        welcomeMessage.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeMessage.setForeground(new Color(188, 143, 143));
        welcomeMessage.setFont(new Font("Copper Black", Font.BOLD, 30));
        welcomeMessage.setBounds(0, 30, 480, 52);
        panel.add(welcomeMessage);

        JLabel imageLabel = new JLabel("");
        ImageIcon img = new ImageIcon(new ImageIcon("src\\main\\resources\\LogInFrame.png").getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT));
        imageLabel.setIcon(img);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setBounds(0, 90, 500, 130);
        panel.add(imageLabel);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setHorizontalAlignment(SwingConstants.LEFT);
        usernameLabel.setForeground(new Color(188, 143, 143));
        usernameLabel.setFont(new Font("Copper Black", Font.BOLD, 20));
        usernameLabel.setBounds(55, 260, 250, 30);
        panel.add(usernameLabel);

        username = new JTextField();
        username.setFont(new Font("Copper Black", Font.BOLD, 20));
        username.setForeground(new Color(188, 143, 143));
        username.setBounds(175, 260, 250, 30);
        panel.add(username);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setHorizontalAlignment(SwingConstants.LEFT);
        passwordLabel.setForeground(new Color(188, 143, 143));
        passwordLabel.setFont(new Font("Copper Black", Font.BOLD, 20));
        passwordLabel.setBounds(55, 320, 250, 30);
        panel.add(passwordLabel);

        password = new JPasswordField();
        password.setForeground(new Color(188, 143, 143));
        password.setFont(new Font("Copper Black", Font.BOLD, 20));
        password.setBounds(175, 320, 250, 30);
        panel.add(password);


        logInButton = new JButton("Log In");
        logInButton.setForeground(new Color(255, 255, 255));
        logInButton.setFont(new Font("Copper Black", Font.BOLD, 25));
        logInButton.setBackground(new Color(188, 143, 143));
        logInButton.setBounds(150, 400, 200, 50);
        panel.add(logInButton);

        newClientButton = new JButton("New client");
        newClientButton.setForeground(new Color(255, 255, 255));
        newClientButton.setFont(new Font("Copper Black", Font.BOLD, 15));
        newClientButton.setBackground(new Color(188, 143, 143));
        newClientButton.setBounds(330, 490, 120, 30);
        panel.add(newClientButton);
    }

    /**
     * Metoda getter a field-ului ce contine username-ul
     * @return field-ul ce contine username-ul
     */
    public JTextField getUsername() {
        return username;
    }

    /**
     * Metoda getter a field-ului ce contine parola user-ului
     * @return field-ul ce contine parola user-ului
     */
    public JPasswordField getPassword() {
        return password;
    }

    /**
     * Metoda pentru adaugarea de listener butonului de logare
     * @param logIn ActionListener-ul folosit
     */
    public void addLogInListener(ActionListener logIn){
        this.logInButton.addActionListener(logIn);
    }

    /**
     * Metoda pentru adaugarea de listener butonului de deschidere a ferestrei de adaugare a unui nou client
     * @param newClient ActionListener-ul folosit
     */
    public void addNewClientListener(ActionListener newClient){
        this.newClientButton.addActionListener(newClient);
    }
}
