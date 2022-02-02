package presentation.admin.reports;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Clasa pentru crearea ferestrei de generare a unui raport referitor la toate comenzile plasate
 * intr-un anumit interval de timp, indiferent de data; accesibila doar de catre admin
 *
 * @author Raluca - Delia Bozdog
 * @since 25.05.2021
 */
public class OrdersInIntervalFrame extends JFrame {
    /**
     * JPanel-ul ferestrei
     */
    private JPanel panel;
    /**
     * Eticheta ce contine mesajul de bun-venit
     */
    private JLabel welcomeMessage;
    /**
     * Field pentru numele raportului
     */
    private JTextField reportName;
    /**
     * Field pentru ora de final a intervalului
     */
    private JTextField endHour;
    /**
     * Field pentru ora de inceput a intervalului
     */
    private JTextField startHour;
    /**
     * Buton de generare a raportului
     */
    private JButton generateButton;

    /**
     * Constructorul clasei OrdersInIntervalFrame
     */
    public OrdersInIntervalFrame() {
        setTitle("Generate report");
        setBounds(100, 100, 500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        panel = new JPanel();
        panel.setBackground(new Color(186, 200, 222));
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(panel);
        panel.setLayout(null);

        welcomeMessage = new JLabel("Report of orders in specified time interval");
        welcomeMessage.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeMessage.setForeground(new Color(188, 143, 143));
        welcomeMessage.setFont(new Font("Copper Black", Font.BOLD, 22));
        welcomeMessage.setBounds(0, 50, 480, 52);
        panel.add(welcomeMessage);

        JLabel startHourLabel = new JLabel("Start hour:");
        startHourLabel.setHorizontalAlignment(SwingConstants.LEFT);
        startHourLabel.setForeground(new Color(188, 143, 143));
        startHourLabel.setFont(new Font("Copper Black", Font.BOLD, 20));
        startHourLabel.setBounds(40, 160, 250, 30);
        panel.add(startHourLabel);

        startHour = new JTextField();
        startHour.setFont(new Font("Copper Black", Font.BOLD, 20));
        startHour.setForeground(new Color(188, 143, 143));
        startHour.setBounds(190, 160, 250, 30);
        panel.add(startHour);

        JLabel endHourLabel = new JLabel("End hour:");
        endHourLabel.setHorizontalAlignment(SwingConstants.LEFT);
        endHourLabel.setForeground(new Color(188, 143, 143));
        endHourLabel.setFont(new Font("Copper Black", Font.BOLD, 20));
        endHourLabel.setBounds(40, 220, 250, 30);
        panel.add(endHourLabel);

        endHour = new JTextField();
        endHour.setFont(new Font("Copper Black", Font.BOLD, 20));
        endHour.setForeground(new Color(188, 143, 143));
        endHour.setBounds(190, 220, 250, 30);
        panel.add(endHour);

        JLabel reportNameLabel = new JLabel("Report name:");
        reportNameLabel.setHorizontalAlignment(SwingConstants.LEFT);
        reportNameLabel.setForeground(new Color(188, 143, 143));
        reportNameLabel.setFont(new Font("Copper Black", Font.BOLD, 20));
        reportNameLabel.setBounds(40, 280, 250, 30);
        panel.add(reportNameLabel);

        reportName = new JTextField();
        reportName.setFont(new Font("Copper Black", Font.BOLD, 20));
        reportName.setForeground(new Color(188, 143, 143));
        reportName.setBounds(190, 280, 250, 30);
        panel.add(reportName);

        generateButton = new JButton("Generate");
        generateButton.setForeground(new Color(255, 255, 255));
        generateButton.setFont(new Font("Copper Black", Font.BOLD, 25));
        generateButton.setBackground(new Color(188, 143, 143));
        generateButton.setBounds(150, 360, 200, 50);
        panel.add(generateButton);
    }

    /**
     * Metoda getter pentru field-ul ce contine numele raportului
     * @return field-ul ce contine numele raportului
     */
    public JTextField getReportName() {
        return reportName;
    }

    /**
     * Metoda getter pentru field-ul ce contine ora de final a intervalului
     * @return field-ul ce contine ora de final a intervalului
     */
    public JTextField getEndHour() {
        return endHour;
    }

    /**
     * Metoda getter pentru field-ul ce contine ora de inceput a intervalului
     * @return field-ul ce contine ora de inceput a intervalului
     */
    public JTextField getStartHour() {
        return startHour;
    }

    /**
     * Metoda pentru adaugarea de listener butonului de generare a raportului
     * @param generate ActionListener-ul folosit
     */
    public void addGenerateListener(ActionListener generate){
        this.generateButton.addActionListener(generate);
    }
}
