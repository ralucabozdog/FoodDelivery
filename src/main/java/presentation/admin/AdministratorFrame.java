package presentation.admin;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Clasa pentru crearea ferestrei principale a unui admin
 *
 * @author Raluca - Delia Bozdog
 * @since 25.05.2021
 */
public class AdministratorFrame extends JFrame {
    /**
     * JPanel-ul ferestrei
     */
    private JPanel panel;
    /**
     * Eticheta cu mesajul de titlu al ferestrei pentru admin
     */
    private JLabel adminMessage;
    /**
     * Eticheta ce indica alegerea urmatoarei operatii pentru admin
     */
    private JLabel chooseOperation;
    /**
     * Buton prin actionarea caruia se deschide fereastra de import produse
     */
    private JButton importProducts;
    /**
     * Buton prin actionarea caruia se deschide fereastra de adaugare produs nou
     */
    private JButton addProducts;
    /**
     * Buton prin actionarea caruia se deschide fereastra de editare produse
     */
    private JButton editProducts;
    /**
     * Buton prin actionarea caruia se deschide fereastra de stergere produse
     */
    private JButton deleteProducts;
    /**
     * Buton prin actionarea caruia se deschide fereastra de creare a unui produs compus
     */
    private JButton createCombo;
    /**
     * Buton prin actionarea caruia se deschide fereastra de generare a unui raport
     */
    private JButton generateReports;

    /**
     * Constructorul clasei AdministratorFrame
     */
    public AdministratorFrame() {
        setTitle("Admin");
        setBounds(100, 100, 500, 840);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        panel = new JPanel();
        panel.setBackground(new Color(186, 200, 222));
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(panel);
        panel.setLayout(null);

        adminMessage = new JLabel("Administrator");
        adminMessage.setHorizontalAlignment(SwingConstants.CENTER);
        adminMessage.setForeground(new Color(188, 143, 143));
        adminMessage.setFont(new Font("Copper Black", Font.BOLD, 30));
        adminMessage.setBounds(0, 30, 480, 52);
        panel.add(adminMessage);

        JLabel lblNewLabel1 = new JLabel("");
        ImageIcon img = new ImageIcon(new ImageIcon("src\\main\\resources\\AdministratorFrame.png").getImage().getScaledInstance(110, 110, Image.SCALE_DEFAULT));
        lblNewLabel1.setIcon(img);
        lblNewLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel1.setBounds(0, 90, 500, 120);
        panel.add(lblNewLabel1);

        chooseOperation = new JLabel("What next?");
        chooseOperation.setHorizontalAlignment(SwingConstants.LEFT);
        chooseOperation.setForeground(new Color(188, 143, 143));
        chooseOperation.setFont(new Font("Copper Black", Font.BOLD, 22));
        chooseOperation.setBounds(67, 210, 250, 40);
        panel.add(chooseOperation);

        importProducts = new JButton("Import products");
        importProducts.setForeground(new Color(255, 255, 255));
        importProducts.setFont(new Font("Copper Black", Font.BOLD, 25));
        importProducts.setBackground(new Color(188, 143, 143));
        importProducts.setBounds(125, 280, 250, 50);
        panel.add(importProducts);

        addProducts = new JButton("Add product");
        addProducts.setForeground(new Color(255, 255, 255));
        addProducts.setFont(new Font("Copper Black", Font.BOLD, 25));
        addProducts.setBackground(new Color(188, 143, 143));
        addProducts.setBounds(125, 360, 250, 50);
        panel.add(addProducts);

        editProducts = new JButton("Edit product");
        editProducts.setForeground(new Color(255, 255, 255));
        editProducts.setFont(new Font("Copper Black", Font.BOLD, 25));
        editProducts.setBackground(new Color(188, 143, 143));
        editProducts.setBounds(125, 440, 250, 50);
        panel.add(editProducts);

        deleteProducts = new JButton("Delete product");
        deleteProducts.setForeground(new Color(255, 255, 255));
        deleteProducts.setFont(new Font("Copper Black", Font.BOLD, 25));
        deleteProducts.setBackground(new Color(188, 143, 143));
        deleteProducts.setBounds(125, 520, 250, 50);
        panel.add(deleteProducts);

        createCombo = new JButton("Create combo");
        createCombo.setForeground(new Color(255, 255, 255));
        createCombo.setFont(new Font("Copper Black", Font.BOLD, 25));
        createCombo.setBackground(new Color(188, 143, 143));
        createCombo.setBounds(125, 600, 250, 50);
        panel.add(createCombo);

        generateReports = new JButton("Generate reports");
        generateReports.setForeground(new Color(255, 255, 255));
        generateReports.setFont(new Font("Copper Black", Font.BOLD, 25));
        generateReports.setBackground(new Color(188, 143, 143));
        generateReports.setBounds(125, 680, 250, 50);
        panel.add(generateReports);
    }

    /**
     * Metoda pentru adaugarea de listener butonului de deschidere a ferestrei de import produse
     * @param importProducts ActionListener-ul folosit
     */
    public void addImportProductsListener(ActionListener importProducts){
        this.importProducts.addActionListener(importProducts);
    }

    /**
     * Metoda pentru adaugarea de listener butonului de deschidere a ferestrei de adaugare produs
     * @param add ActionListener-ul folosit
     */
    public void addAddProductsListener(ActionListener add){
        this.addProducts.addActionListener(add);
    }

    /**
     * Metoda pentru adaugarea de listener butonului de deschidere a ferestrei de editare produse
     * @param edit ActionListener-ul folosit
     */
    public void addEditProductsListener(ActionListener edit){
        this.editProducts.addActionListener(edit);
    }

    /**
     * Metoda pentru adaugarea de listener butonului de deschidere a ferestrei de stergere produse
     * @param delete ActionListener-ul folosit
     */
    public void addDeleteProductsListener(ActionListener delete){
        this.deleteProducts.addActionListener(delete);
    }

    /**
     * Metoda pentru adaugarea de listener butonului de deschidere a ferestrei de creare a unui produs compus
     * @param create ActionListener-ul folosit
     */
    public void addCreateComboListener(ActionListener create){
        this.createCombo.addActionListener(create);
    }

    /**
     * Metoda pentru adaugarea de listener butonului de deschidere a ferestrei de generare raport
     * @param generate ActionListener-ul folosit
     */
    public void addGenerateReportsListener(ActionListener generate){
        this.generateReports.addActionListener(generate);
    }
}
