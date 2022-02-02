package presentation.admin.reports;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Clasa pentru crearea ferestrei de generare a unui raport al produsleor care au fost comandate de cel putin x ori;
 * accesibila doar de catre admin
 *
 * @author Raluca - Delia Bozdog
 * @since 25.05.2021
 */
public class PopularProductsFrame extends JFrame {
    /**
     * JPanel-ul ferestrei
     */
    private JPanel panel;
    /**
     * Eticheta ce contine mesajul de bun-venit
     */
    private JLabel welcomeMessage;
    /**
     * Field-ul pentru numele raportului
     */
    private JTextField reportName;
    /**
     * Field pentru numarul de ori peste care se ia in considerare un produs
     */
    private JTextField numberOfTimes;
    /**
     * Buton de generare a raportului
     */
    private JButton generateButton;

    /**
     * Constructorul clasei PopularProductsFrame
     */
    public PopularProductsFrame() {
        setTitle("Generate report");
        setBounds(100, 100, 500, 440);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        panel = new JPanel();
        panel.setBackground(new Color(186, 200, 222));
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(panel);
        panel.setLayout(null);

        welcomeMessage = new JLabel("Report of products ordered more than x times");
        welcomeMessage.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeMessage.setForeground(new Color(188, 143, 143));
        welcomeMessage.setFont(new Font("Copper Black", Font.BOLD, 22));
        welcomeMessage.setBounds(0, 50, 480, 52);
        panel.add(welcomeMessage);

        JLabel numberOfTimesLabel = new JLabel("x:");
        numberOfTimesLabel.setHorizontalAlignment(SwingConstants.LEFT);
        numberOfTimesLabel.setForeground(new Color(188, 143, 143));
        numberOfTimesLabel.setFont(new Font("Copper Black", Font.BOLD, 20));
        numberOfTimesLabel.setBounds(40, 160, 250, 30);
        panel.add(numberOfTimesLabel);

        numberOfTimes = new JTextField();
        numberOfTimes.setFont(new Font("Copper Black", Font.BOLD, 20));
        numberOfTimes.setForeground(new Color(188, 143, 143));
        numberOfTimes.setBounds(190, 160, 250, 30);
        panel.add(numberOfTimes);

        JLabel reportNameLabel = new JLabel("Report name:");
        reportNameLabel.setHorizontalAlignment(SwingConstants.LEFT);
        reportNameLabel.setForeground(new Color(188, 143, 143));
        reportNameLabel.setFont(new Font("Copper Black", Font.BOLD, 20));
        reportNameLabel.setBounds(40, 220, 250, 30);
        panel.add(reportNameLabel);

        reportName = new JTextField();
        reportName.setFont(new Font("Copper Black", Font.BOLD, 20));
        reportName.setForeground(new Color(188, 143, 143));
        reportName.setBounds(190, 220, 250, 30);
        panel.add(reportName);

        generateButton = new JButton("Generate");
        generateButton.setForeground(new Color(255, 255, 255));
        generateButton.setFont(new Font("Copper Black", Font.BOLD, 25));
        generateButton.setBackground(new Color(188, 143, 143));
        generateButton.setBounds(150, 300, 200, 50);
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
     * Metoda getter pentru field-ul ce contine numarul de ori peste care se ia in considerare un produs
     * @return field-ul ce contine numarul de ori peste care se ia in considerare un produs
     */
    public JTextField getNumberOfTimes() {
        return numberOfTimes;
    }

    /**
     * Metoda pentru adaugarea de listener butonului de generare a raportului
     * @param generate ActionListener-ul folosit
     */
    public void addGenerateListener(ActionListener generate){
        this.generateButton.addActionListener(generate);
    }
}
