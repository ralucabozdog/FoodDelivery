package presentation.admin;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Clasa pentru crearea ferestrei de importare a unor produse dintr-un fisier .csv
 *
 * @author Raluca - Delia Bozdog
 * @since 25.05.2021
 */
public class ImportProductsFrame extends JFrame {
    /**
     * JPanel-ul ferestrei
     */
    private JPanel panel;
    /**
     * Eticheta ce contine mesajul de bun-venit
     */
    private JLabel welcomeMessage;
    /**
     * Field-ul de introducere a numelui de fisier din care sa se faca importul
     */
    private JTextField fileName;
    /**
     * Buton de realizare a operatiei de import
     */
    private JButton importButton;

    /**
     * Constructorul clasei ImportProductsFrame
     */
    public ImportProductsFrame() {
        setTitle("Import products");
        setBounds(100, 100, 500, 440);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        panel = new JPanel();
        panel.setBackground(new Color(186, 200, 222));
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(panel);
        panel.setLayout(null);

        welcomeMessage = new JLabel("Import products from file");
        welcomeMessage.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeMessage.setForeground(new Color(188, 143, 143));
        welcomeMessage.setFont(new Font("Copper Black", Font.BOLD, 22));
        welcomeMessage.setBounds(0, 30, 480, 52);
        panel.add(welcomeMessage);

        JLabel imageLabel = new JLabel("");
        ImageIcon img = new ImageIcon(new ImageIcon("src\\main\\resources\\ImportProductsFrame.png").getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT));
        imageLabel.setIcon(img);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setBounds(0, 90, 500, 130);
        panel.add(imageLabel);

        JLabel fileNameLabel = new JLabel("File name:");
        fileNameLabel.setHorizontalAlignment(SwingConstants.LEFT);
        fileNameLabel.setForeground(new Color(188, 143, 143));
        fileNameLabel.setFont(new Font("Copper Black", Font.BOLD, 20));
        fileNameLabel.setBounds(40, 240, 250, 30);
        panel.add(fileNameLabel);

        fileName = new JTextField();
        fileName.setFont(new Font("Copper Black", Font.BOLD, 20));
        fileName.setForeground(new Color(188, 143, 143));
        fileName.setBounds(190, 240, 250, 30);
        panel.add(fileName);

        importButton = new JButton("Import");
        importButton.setForeground(new Color(255, 255, 255));
        importButton.setFont(new Font("Copper Black", Font.BOLD, 25));
        importButton.setBackground(new Color(188, 143, 143));
        importButton.setBounds(150, 310, 200, 50);
        panel.add(importButton);
    }

    /**
     * Metoda getter pentru field-ul ce contine numele fisierului .csv
     * @return field-ul ce contine numele fisierului .csv
     */
    public JTextField getFileName() {
        return fileName;
    }

    /**
     * Metoda pentru adaugarea de listener butonului de realizare a operatiei de import
     * @param importProducts ActionListener-ul folosit
     */
    public void addImportListener(ActionListener importProducts){
        this.importButton.addActionListener(importProducts);
    }
}
