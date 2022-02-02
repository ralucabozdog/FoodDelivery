package presentation.admin;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Clasa pentru crearea interfetei grafice de adaugare a unui nou produs in baza de date
 *
 * @author Raluca - Delia Bozdog
 * @since 25.05.2021
 */
public class AddProductFrame extends JFrame {
    /**
     * JPanel-ul ferestrei
     */
    private JPanel panel;
    /**
     * Eticheta mesajului de titlu al ferestrei
     */
    private JLabel addProductMessage;
    /**
     * Eticheta ce indica locul de introducere a titlului produsului
     */
    private JLabel labelTitle;
    /**
     * JTextField de introducere a titlului produsului
     */
    private JTextField titleField;
    /**
     * Eticheta ce indica locul de introducere a caloriilor produsului
     */
    private JLabel labelCalories;
    /**
     * JTextField de introducere a caloriilor produsului
     */
    private JTextField calories;
    /**
     * Eticheta ce indica locul de introducere a proteinelor produsului
     */
    private JLabel labelProtein;
    /**
     * JTextField de introducere a proteinelor produsului
     */
    private JTextField protein;
    /**
     * Eticheta ce indica locul de introducere a grasimilor produsului
     */
    private JLabel labelFat;
    /**
     * JTextField de introducere a grasimilor produsului
     */
    private JTextField fat;
    /**
     * Eticheta ce indica locul de introducere a cantitatii de sodiu a produsului
     */
    private JLabel labelSodium;
    /**
     * JTextField de introducere a cantitatii de sodiu a produsului
     */
    private JTextField sodium;
    /**
     * Eticheta ce indica locul de introducere a pretului produsului
     */
    private JLabel labelPrice;
    /**
     * JTextField de introducere a pretului produsului
     */
    private JTextField price;
    /**
     * Eticheta ce indica locul de introducere a rating-ului produsului
     */
    private JLabel labelRating;
    /**
     * JTextField de introducere a rating-ului produsului
     */
    private JTextField rating;
    /**
     * Buton pentru adaugarea in baza de date a produsului cu datele introduse
     */
    private JButton btnAdd;


    /**
     * Constructorul ferestrei pentru introducerea datelor de creare a unui nou produs in baza de date
     */
    public AddProductFrame() {
        setTitle("New product");
        setBounds(100, 100, 500, 710);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        panel = new JPanel();
        panel.setBackground(new Color(186, 200, 222));
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(panel);
        panel.setLayout(null);

        addProductMessage = new JLabel("Add new product");
        addProductMessage.setHorizontalAlignment(SwingConstants.CENTER);
        addProductMessage.setForeground(new Color(188, 143, 143));
        addProductMessage.setFont(new Font("Copper Black", Font.BOLD, 30));
        addProductMessage.setBounds(0, 30, 480, 52);
        panel.add(addProductMessage);

        JLabel imageLabel = new JLabel("");
        ImageIcon img = new ImageIcon(new ImageIcon("src\\main\\resources\\AddProductFrame.png").getImage().getScaledInstance(110, 110, Image.SCALE_DEFAULT));
        imageLabel.setIcon(img);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setBounds(0, 90, 500, 120);
        panel.add(imageLabel);

        labelTitle = new JLabel("Title: ");
        labelTitle.setHorizontalAlignment(SwingConstants.LEFT);
        labelTitle.setForeground(new Color(188, 143, 143));
        labelTitle.setFont(new Font("Copper Black", Font.BOLD, 20));
        labelTitle.setBounds(60, 220, 250, 30);
        panel.add(labelTitle);

        titleField = new JTextField();
        titleField.setForeground(new Color(188, 143, 143));
        titleField.setFont(new Font("Copper Black", Font.BOLD, 20));
        titleField.setColumns(10);
        titleField.setBounds(170, 220, 250, 30);
        panel.add(titleField);

        labelRating = new JLabel("Rating: ");
        labelRating.setHorizontalAlignment(SwingConstants.LEFT);
        labelRating.setForeground(new Color(188, 143, 143));
        labelRating.setFont(new Font("Copper Black", Font.BOLD, 20));
        labelRating.setBounds(60, 270, 250, 30);
        panel.add(labelRating);

        rating = new JTextField();
        rating.setForeground(new Color(188, 143, 143));
        rating.setFont(new Font("Copper Black", Font.BOLD, 20));
        rating.setColumns(10);
        rating.setBounds(170, 270, 250, 30);
        panel.add(rating);

        labelCalories = new JLabel("Calories: ");
        labelCalories.setHorizontalAlignment(SwingConstants.LEFT);
        labelCalories.setForeground(new Color(188, 143, 143));
        labelCalories.setFont(new Font("Copper Black", Font.BOLD, 20));
        labelCalories.setBounds(60, 320, 250, 30);
        panel.add(labelCalories);

        calories = new JTextField();
        calories.setForeground(new Color(188, 143, 143));
        calories.setFont(new Font("Copper Black", Font.BOLD, 20));
        calories.setColumns(10);
        calories.setBounds(170, 320, 250, 30);
        panel.add(calories);

        labelProtein = new JLabel("Protein: ");
        labelProtein.setHorizontalAlignment(SwingConstants.LEFT);
        labelProtein.setForeground(new Color(188, 143, 143));
        labelProtein.setFont(new Font("Copper Black", Font.BOLD, 20));
        labelProtein.setBounds(60, 370, 250, 30);
        panel.add(labelProtein);

        protein = new JTextField();
        protein.setForeground(new Color(188, 143, 143));
        protein.setFont(new Font("Copper Black", Font.BOLD, 20));
        protein.setColumns(10);
        protein.setBounds(170, 370, 250, 30);
        panel.add(protein);

        labelFat = new JLabel("Fat: ");
        labelFat.setHorizontalAlignment(SwingConstants.LEFT);
        labelFat.setForeground(new Color(188, 143, 143));
        labelFat.setFont(new Font("Copper Black", Font.BOLD, 20));
        labelFat.setBounds(60, 420, 250, 30);
        panel.add(labelFat);

        fat = new JTextField();
        fat.setForeground(new Color(188, 143, 143));
        fat.setFont(new Font("Copper Black", Font.BOLD, 20));
        fat.setColumns(10);
        fat.setBounds(170, 420, 250, 30);
        panel.add(fat);

        labelSodium = new JLabel("Sodium: ");
        labelSodium.setHorizontalAlignment(SwingConstants.LEFT);
        labelSodium.setForeground(new Color(188, 143, 143));
        labelSodium.setFont(new Font("Copper Black", Font.BOLD, 20));
        labelSodium.setBounds(60, 470, 250, 30);
        panel.add(labelSodium);

        sodium = new JTextField();
        sodium.setForeground(new Color(188, 143, 143));
        sodium.setFont(new Font("Copper Black", Font.BOLD, 20));
        sodium.setColumns(10);
        sodium.setBounds(170, 470, 250, 30);
        panel.add(sodium);

        labelPrice = new JLabel("Price: ");
        labelPrice.setHorizontalAlignment(SwingConstants.LEFT);
        labelPrice.setForeground(new Color(188, 143, 143));
        labelPrice.setFont(new Font("Copper Black", Font.BOLD, 20));
        labelPrice.setBounds(60, 520, 250, 30);
        panel.add(labelPrice);

        price = new JTextField();
        price.setForeground(new Color(188, 143, 143));
        price.setFont(new Font("Copper Black", Font.BOLD, 20));
        price.setColumns(10);
        price.setBounds(170, 520, 250, 30);
        panel.add(price);

        btnAdd = new JButton("Add");
        btnAdd.setForeground(new Color(255, 255, 255));
        btnAdd.setFont(new Font("Copper Black", Font.BOLD, 25));
        btnAdd.setBackground(new Color(188, 143, 143));
        btnAdd.setBounds(170, 580, 150, 50);
        panel.add(btnAdd);
    }

    /**
     * Metoda getter pentru campul in care se introduce titlul produsului
     * @return JTextField-ul in care se introduce titlul produsului
     */
    public JTextField getTitleField() {
        return titleField;
    }

    /**
     * Metoda getter pentru campul in care se introduce numarul caloriilor produsului
     * @return JTextField-ul in care se introduce numarul caloriilor produsului
     */
    public JTextField getCalories() {
        return calories;
    }

    /**
     * Metoda getter pentru campul in care se introduce numarul proteinelor produsului
     * @return JTextField-ul in care se introduce numarul proteinelor produsului
     */
    public JTextField getProtein() {
        return protein;
    }

    /**
     * Metoda getter pentru campul in care se introduce numarul grasimilor produsului
     * @return JTextField-ul in care se introduce numarul grasimilor produsului
     */
    public JTextField getFat() {
        return fat;
    }

    /**
     * Metoda getter pentru campul in care se introduce cantitatea de sodiu a produsului
     * @return JTextField-ul in care se introduce cantitatea de sodiu a produsului
     */
    public JTextField getSodium(){
        return sodium;
    }

    /**
     * Metoda getter pentru campul in care se introduce pretul produsului
     * @return JTextField-ul in care se introduce pretul produsului
     */
    public JTextField getPrice() {
        return price;
    }

    /**
     * Metoda getter pentru campul in care se introduce rating-ul produsului
     * @return JTextField-ul in care se introduce rating-ul produsului
     */
    public JTextField getRating() {
        return rating;
    }

    /**
     * Metoda pentru adaugarea de listener butonului de adaugare a unui produs
     * @param add ActionListener-ul folosit
     */
    public void addAddListener(ActionListener add){
        this.btnAdd.addActionListener(add);
    }
}
