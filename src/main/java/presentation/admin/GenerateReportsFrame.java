package presentation.admin;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Clasa pentru crearea interfetei grafice de selectare a unui tip de raport care sa fie generat
 *
 * @author Raluca - Delia Bozdog
 * @since 25.05.2021
 */
public class GenerateReportsFrame extends JFrame {
    /**
     * JPanel-ul ferestrei
     */
    private JPanel panel;
    /**
     * Eticheta cu mesajul de titlu al ferestrei de generare rapoarte
     */
    private JLabel productsMessage;
    /**
     * Eticheta ce indica alegerea tipului de raport
     */
    private JLabel chooseReport;
    /**
     * Buton prin actionarea caruia se deschide fereastra de generare a unui raport in functie de
     * intervalul de timp in care s-a plasat comanda
     */
    private JButton timeInterval;
    /**
     * Buton prin actionarea caruia se deschide fereastra de generare a unui raport in functie de
     * numarul minim de ori de care a fost comandat un produs
     */
    private JButton popularProducts;
    /**
     * Buton prin actionarea caruia se deschide fereastra de generare a unui raport in functie de
     * numarul de comenzi plasate de catre un client si valoarea minima a fiecarei comenzi
     */
    private JButton regularClients;
    /**
     * Buton prin actionarea caruia se deschide fereastra de generare a unui raport in functie de
     * data la care s-a plasat o comanda
     */
    private JButton productsOfDay;

    /**
     * Constructorul ferestrei pentru selectarea raportului de generat
     */
    public GenerateReportsFrame() {
        setTitle("Generate Reports");
        setBounds(100, 100, 500, 680);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        panel = new JPanel();
        panel.setBackground(new Color(186, 200, 222));
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(panel);
        panel.setLayout(null);

        productsMessage = new JLabel("Generate a report");
        productsMessage.setHorizontalAlignment(SwingConstants.CENTER);
        productsMessage.setForeground(new Color(188, 143, 143));
        productsMessage.setFont(new Font("Copper Black", Font.BOLD, 30));
        productsMessage.setBounds(0, 30, 500, 52);
        panel.add(productsMessage);

        JLabel lblNewLabel1 = new JLabel("");
        ImageIcon img = new ImageIcon(new ImageIcon("src\\main\\resources\\GenerateReportsFrame.png").getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT));
        lblNewLabel1.setIcon(img);
        lblNewLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel1.setBounds(0, 90, 500, 130);
        panel.add(lblNewLabel1);

        chooseReport = new JLabel("Choose a type of report:");
        chooseReport.setHorizontalAlignment(SwingConstants.LEFT);
        chooseReport.setForeground(new Color(188, 143, 143));
        chooseReport.setFont(new Font("Copper Black", Font.BOLD, 22));
        chooseReport.setBounds(67, 220, 250, 40);
        panel.add(chooseReport);

        timeInterval = new JButton("Orders in time interval");
        timeInterval.setForeground(new Color(255, 255, 255));
        timeInterval.setFont(new Font("Copper Black", Font.BOLD, 20));
        timeInterval.setBackground(new Color(188, 143, 143));
        timeInterval.setBounds(125, 280, 250, 50);
        panel.add(timeInterval);

        popularProducts = new JButton("Popular products");
        popularProducts.setForeground(new Color(255, 255, 255));
        popularProducts.setFont(new Font("Copper Black", Font.BOLD, 20));
        popularProducts.setBackground(new Color(188, 143, 143));
        popularProducts.setBounds(125, 360, 250, 50);
        panel.add(popularProducts);

        regularClients = new JButton("Regular clients");
        regularClients.setForeground(new Color(255, 255, 255));
        regularClients.setFont(new Font("Copper Black", Font.BOLD, 20));
        regularClients.setBackground(new Color(188, 143, 143));
        regularClients.setBounds(125, 440, 250, 50);
        panel.add(regularClients);

        productsOfDay = new JButton("Products of a day");
        productsOfDay.setForeground(new Color(255, 255, 255));
        productsOfDay.setFont(new Font("Copper Black", Font.BOLD, 20));
        productsOfDay.setBackground(new Color(188, 143, 143));
        productsOfDay.setBounds(125, 520, 250, 50);
        panel.add(productsOfDay);
    }

    /**
     * Metoda pentru adaugarea de listener butonului de deschidere a ferestrei de generare a unui raport in functie de
     * intervalul de timp in care s-a plasat comanda
     * @param generate ActionListener-ul folosit
     */
    public void addOrdersInIntervalListener(ActionListener generate){
        this.timeInterval.addActionListener(generate);
    }

    /**
     * Metoda pentru adaugarea de listener butonului de deschidere a ferestrei de generare a unui raport in functie de
     * numarul minim de ori de care a fost comandat un produs
     * @param generate ActionListener-ul folosit
     */
    public void addPopularProductsListener(ActionListener generate){
        this.popularProducts.addActionListener(generate);
    }

    /**
     * Metoda pentru adaugarea de listener butonului de deschidere a ferestrei de generare a unui raport in functie de
     * numarul de comenzi plasate de catre un client si valoarea minima a fiecarei comenzi
     * @param generate ActionListener-ul folosit
     */
    public void addRegularClientsListener(ActionListener generate){
        this.regularClients.addActionListener(generate);
    }

    /**
     * Metoda pentru adaugarea de listener butonului de deschidere a ferestrei de generare a unui raport in functie de
     * data la care s-a plasat o comanda
     * @param generate ActionListener-ul folosit
     */
    public void addProductsOfDayListener(ActionListener generate){
        this.productsOfDay.addActionListener(generate);
    }
}
