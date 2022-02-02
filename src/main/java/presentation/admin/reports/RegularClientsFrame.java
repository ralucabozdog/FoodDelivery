package presentation.admin.reports;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Clasa pentru crearea ferestrei de generare a unui raport al tuturor clientilor care au facut cel putin
 * x comenzi, fiecare dintre acestea in valoare de ce putin y ron
 *
 * @author Raluca - Delia Bozdog
 * @since 25.05.2021
 */
public class RegularClientsFrame extends JFrame {
    /**
     * JPanel-ul ferestrei
     */
    private JPanel panel;
    /**
     * Eticheta ce contine prima linie a mesajului de bun-venit
     */
    private JLabel welcomeMessage;
    /**
     * Eticheta ce contine a doua linie a mesajului de bun-venit
     */
    private JLabel welcomeMessageSecondLine;
    /**
     * Field pentru numarul de comenzi plasate peste care se ia in considerare un client
     */
    private JTextField numberOfTimes;
    /**
     * Field ce contine valoarea minima a oricarei comenzi a clientului
     */
    private JTextField minValue;
    /**
     * Field-ul pentru numele raportului
     */
    private JTextField reportName;
    /**
     * Buton de generare a raportului
     */
    private JButton generateButton;

    /**
     * Constructorul clasei RegularClientsFrame
     */
    public RegularClientsFrame() {
        setTitle("Generate report");
        setBounds(100, 100, 500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        panel = new JPanel();
        panel.setBackground(new Color(186, 200, 222));
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(panel);
        panel.setLayout(null);

        welcomeMessage = new JLabel("Clients who ordered more than x times");
        welcomeMessage.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeMessage.setForeground(new Color(188, 143, 143));
        welcomeMessage.setFont(new Font("Copper Black", Font.BOLD, 22));
        welcomeMessage.setBounds(0, 35, 480, 52);
        panel.add(welcomeMessage);

        welcomeMessageSecondLine = new JLabel("and the value of each order was > y");
        welcomeMessageSecondLine.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeMessageSecondLine.setForeground(new Color(188, 143, 143));
        welcomeMessageSecondLine.setFont(new Font("Copper Black", Font.BOLD, 22));
        welcomeMessageSecondLine.setBounds(0, 65, 480, 52);
        panel.add(welcomeMessageSecondLine);

        JLabel startHourLabel = new JLabel("x:");
        startHourLabel.setHorizontalAlignment(SwingConstants.LEFT);
        startHourLabel.setForeground(new Color(188, 143, 143));
        startHourLabel.setFont(new Font("Copper Black", Font.BOLD, 20));
        startHourLabel.setBounds(40, 160, 250, 30);
        panel.add(startHourLabel);

        numberOfTimes = new JTextField();
        numberOfTimes.setFont(new Font("Copper Black", Font.BOLD, 20));
        numberOfTimes.setForeground(new Color(188, 143, 143));
        numberOfTimes.setBounds(190, 160, 250, 30);
        panel.add(numberOfTimes);

        JLabel endHourLabel = new JLabel("y:");
        endHourLabel.setHorizontalAlignment(SwingConstants.LEFT);
        endHourLabel.setForeground(new Color(188, 143, 143));
        endHourLabel.setFont(new Font("Copper Black", Font.BOLD, 20));
        endHourLabel.setBounds(40, 220, 250, 30);
        panel.add(endHourLabel);

        minValue = new JTextField();
        minValue.setFont(new Font("Copper Black", Font.BOLD, 20));
        minValue.setForeground(new Color(188, 143, 143));
        minValue.setBounds(190, 220, 250, 30);
        panel.add(minValue);

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
     * Metoda getter pentru field-ul ce contine numarul de comenzi plasate peste care se ia in considerare un client
     * @return field-ul ce contine numarul de comenzi plasate peste care se ia in considerare un client
     */
    public JTextField getNumberOfTimes() {
        return numberOfTimes;
    }

    /**
     * Metoda getter pentru field-ul ce contine valoarea minima a oricarei comenzi a clientului
     * @return field-ul ce contine valoarea minima a oricarei comenzi a clientului
     */
    public JTextField getMinValue() {
        return minValue;
    }
    /**
     * Metoda getter pentru field-ul ce contine numele raportului
     * @return field-ul ce contine numele raportului
     */
    public JTextField getReportName() {
        return reportName;
    }

    /**
     * Metoda pentru adaugarea de listener butonului de generare a raportului
     * @param generate ActionListener-ul folosit
     */
    public void addGenerateListener(ActionListener generate){
        this.generateButton.addActionListener(generate);
    }
}
