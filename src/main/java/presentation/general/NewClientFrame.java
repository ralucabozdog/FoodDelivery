package presentation.general;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Clasa pentru crearea interfetei grafice de adaugare a unui nou client
 *
 * @author Raluca - Delia Bozdog
 * @since 25.05.2021
 */
public class NewClientFrame extends JFrame {
    /**
     * JPanel-ul ferestrei
     */
    private JPanel panel;
    /**
     * Eticheta mesajului de titlu al ferestrei
     */
    private JLabel addClientMessage;
    /**
     * Eticheta ce indica locul de introducere a username-ului clientului
     */
    private JLabel labelUsername;
    /**
     * JTextField de introducere a username-ului clientului
     */
    private JTextField username;
    /**
     * Eticheta ce indica locul de introducere a parolei clientului
     */
    private JLabel labelPassword;
    /**
     * JPasswordField-ul ce contine parola clientului
     */
    private JPasswordField password;
    /**
     * Eticheta ce indica locul de introducere a confirmarii parolei
     */
    private JLabel labelConfirmPassword;
    /**
     * JPasswordField-ul ce contine confirmarea parolei clientului
     */
    private JPasswordField confirmPassword;
    /**
     * Buton ce realizeaza crearea unui nou client
     */
    private JButton btnCreate;


    /**
     * Constructorul ferestrei NewClientFrame
     */
    public NewClientFrame() {
        setTitle("New client");
        setBounds(100, 100, 500, 530);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        panel = new JPanel();
        panel.setBackground(new Color(186, 200, 222));
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(panel);
        panel.setLayout(null);

        addClientMessage = new JLabel("Create client account");
        addClientMessage.setHorizontalAlignment(SwingConstants.CENTER);
        addClientMessage.setForeground(new Color(188, 143, 143));
        addClientMessage.setFont(new Font("Copper Black", Font.BOLD, 30));
        addClientMessage.setBounds(0, 30, 480, 52);
        panel.add(addClientMessage);

        JLabel lblNewLabel1 = new JLabel("");
        ImageIcon img = new ImageIcon(new ImageIcon("src\\main\\resources\\NewClientFrame.png").getImage().getScaledInstance(110, 110, Image.SCALE_DEFAULT));
        lblNewLabel1.setIcon(img);
        lblNewLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel1.setBounds(0, 90, 500, 120);
        panel.add(lblNewLabel1);

        labelUsername = new JLabel("Username: ");
        labelUsername.setHorizontalAlignment(SwingConstants.LEFT);
        labelUsername.setForeground(new Color(188, 143, 143));
        labelUsername.setFont(new Font("Copper Black", Font.BOLD, 20));
        labelUsername.setBounds(30, 220, 250, 30);
        panel.add(labelUsername);

        username = new JTextField();
        username.setForeground(new Color(188, 143, 143));
        username.setFont(new Font("Copper Black", Font.BOLD, 20));
        username.setColumns(10);
        username.setBounds(220, 220, 230, 30);
        panel.add(username);

        labelPassword = new JLabel("Password: ");
        labelPassword.setHorizontalAlignment(SwingConstants.LEFT);
        labelPassword.setForeground(new Color(188, 143, 143));
        labelPassword.setFont(new Font("Copper Black", Font.BOLD, 20));
        labelPassword.setBounds(30, 270, 250, 30);
        panel.add(labelPassword);

        password = new JPasswordField();
        password.setForeground(new Color(188, 143, 143));
        password.setFont(new Font("Copper Black", Font.BOLD, 20));
        password.setBounds(220, 270, 230, 30);
        panel.add(password);

        labelConfirmPassword = new JLabel("Confirm password: ");
        labelConfirmPassword.setHorizontalAlignment(SwingConstants.LEFT);
        labelConfirmPassword.setForeground(new Color(188, 143, 143));
        labelConfirmPassword.setFont(new Font("Copper Black", Font.BOLD, 20));
        labelConfirmPassword.setBounds(30, 320, 250, 30);
        panel.add(labelConfirmPassword);

        confirmPassword = new JPasswordField();
        confirmPassword.setForeground(new Color(188, 143, 143));
        confirmPassword.setFont(new Font("Copper Black", Font.BOLD, 20));
        confirmPassword.setBounds(220, 320, 230, 30);
        panel.add(confirmPassword);


        btnCreate = new JButton("Create");
        btnCreate.setForeground(new Color(255, 255, 255));
        btnCreate.setFont(new Font("Copper Black", Font.BOLD, 25));
        btnCreate.setBackground(new Color(188, 143, 143));
        btnCreate.setBounds(170, 400, 150, 50);
        panel.add(btnCreate);
    }

    /**
     * Metoda getter pentru numele de utilizator al clientului
     * @return numele de utilizator al clientului
     */
    public JTextField getUsername() {
        return username;
    }

    /**
     * Metoda getter pentru parola clientului
     * @return parola clientului
     */
    public JPasswordField getPassword() {
        return password;
    }

    /**
     * Metoda getter pentru confirmarea parolei clientului
     * @return confirmarea parolei clientului
     */
    public JPasswordField getConfirmPassword() {
        return confirmPassword;
    }

    /**
     * Metoda pentru adaugarea de listener butonului de creare a unui nou client
     * @param create ActionListener-ul folosit
     */
    public void addCreateListener(ActionListener create){
        this.btnCreate.addActionListener(create);
    }
}
