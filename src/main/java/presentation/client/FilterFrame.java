package presentation.client;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Clasa pentru crearea interfetei grafice de filtrare a produselor
 *
 * @author Raluca - Delia Bozdog
 * @since 25.05.2021
 */
public class FilterFrame extends JFrame {
    /**
     * JPanel-ul ferestrei
     */
    private JPanel panel;
    /**
     * Eticheta ce indica locul de introducere a cuvintelor pentru filtrarea dupa nume
     */
    private JLabel labelKeyWord;
    /**
     * JTextField de introducere a cuvintelor pentru filtrarea dupa nume
     */
    private JTextField keyWord;
    /**
     * Eticheta ce indica locul de introducere a rating-ului minim
     */
    private JLabel labelRatingMin;
    /**
     * JTextField de introducere a rating-ului minim
     */
    private JTextField ratingMin;
    /**
     * Eticheta ce indica locul de introducere a rating-ului maxim
     */
    private JLabel labelRatingMax;
    /**
     * JTextField de introducere a rating-ului minim
     */
    private JTextField ratingMax;
    /**
     * Eticheta ce indica locul de introducere a pretului minim
     */
    private JLabel labelPriceMin;
    /**
     * JTextField de introducere a pretului minim
     */
    private JTextField priceMin;
    /**
     * Eticheta ce indica locul de introducere a pretului maxim
     */
    private JLabel labelPriceMax;
    /**
     * JTextField de introducere a pretului maxim
     */
    private JTextField priceMax;
    /**
     * Eticheta ce indica locul de introducere a numarului minim de calorii
     */
    private JLabel labelCaloriesMin;
    /**
     * JTextField de introducere a numarului minim de calorii
     */
    private JTextField caloriesMin;
    /**
     * Eticheta ce indica locul de introducere a numarului maxim de calorii
     */
    private JLabel labelCaloriesMax;
    /**
     * JTextField de introducere a numarului maxim de calorii
     */
    private JTextField caloriesMax;
    /**
     * Eticheta ce indica locul de introducere a numarului minim de proteine
     */
    private JLabel labelProteinMin;
    /**
     * JTextField de introducere a numarului minim de proteine
     */
    private JTextField proteinMin;
    /**
     * Eticheta ce indica locul de introducere a numarului maxim de proteine
     */
    private JLabel labelProteinMax;
    /**
     * JTextField de introducere a numarului maxim de proteine
     */
    private JTextField proteinMax;
    /**
     * Eticheta ce indica locul de introducere a numarului minim de grasimi
     */
    private JLabel labelFatMin;
    /**
     * Eticheta ce indica locul de introducere a numarului minim de grasimi
     */
    private JTextField fatMin;
    /**
     * Eticheta ce indica locul de introducere a numarului maxim de grasimi
     */
    private JLabel labelFatMax;
    /**
     * JTextField de introducere a numarului maxim de grasimi
     */
    private JTextField fatMax;
    /**
     * Eticheta ce indica locul de introducere a cantitatii minime de sodiu
     */
    private JLabel labelSodiumMin;
    /**
     * JTextField de introducere a cantitatii minime de sodiu
     */
    private JTextField sodiumMin;
    /**
     * Eticheta ce indica locul de introducere a cantitatii maxime de sodiu
     */
    private JLabel labelSodiumMax;
    /**
     * JTextField de introducere a cantitatii maxime de sodiu
     */
    private JTextField sodiumMax;
    /**
     * JButton pentru realizarea filtrarii
     */
    private JButton btnFilter;

    /**
     * Constructorul ferestrei FilterFrame
     */
    public FilterFrame() {
        setTitle("Filter");
        setBounds(100, 100, 590, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        panel = new JPanel();
        panel.setBackground(new Color(186, 200, 222));
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(panel);
        panel.setLayout(null);

        labelKeyWord = new JLabel("Key word: ");
        labelKeyWord.setHorizontalAlignment(SwingConstants.LEFT);
        labelKeyWord.setForeground(new Color(188, 143, 143));
        labelKeyWord.setFont(new Font("Copper Black", Font.BOLD, 20));
        labelKeyWord.setBounds(30, 30, 250, 30);
        panel.add(labelKeyWord);

        keyWord = new JTextField();
        keyWord.setForeground(new Color(188, 143, 143));
        keyWord.setFont(new Font("Copper Black", Font.BOLD, 20));
        keyWord.setColumns(10);
        keyWord.setBounds(170, 30, 360, 30);
        panel.add(keyWord);

        labelRatingMin = new JLabel("Rating min: ");
        labelRatingMin.setHorizontalAlignment(SwingConstants.LEFT);
        labelRatingMin.setForeground(new Color(188, 143, 143));
        labelRatingMin.setFont(new Font("Copper Black", Font.BOLD, 20));
        labelRatingMin.setBounds(30, 80, 250, 30);
        panel.add(labelRatingMin);

        ratingMin = new JTextField();
        ratingMin.setForeground(new Color(188, 143, 143));
        ratingMin.setFont(new Font("Copper Black", Font.BOLD, 20));
        ratingMin.setColumns(10);
        ratingMin.setBounds(170, 80, 140, 30);
        panel.add(ratingMin);

        labelRatingMax = new JLabel("max: ");
        labelRatingMax.setHorizontalAlignment(SwingConstants.LEFT);
        labelRatingMax.setForeground(new Color(188, 143, 143));
        labelRatingMax.setFont(new Font("Copper Black", Font.BOLD, 20));
        labelRatingMax.setBounds(330, 80, 100, 30);
        panel.add(labelRatingMax);

        ratingMax = new JTextField();
        ratingMax.setForeground(new Color(188, 143, 143));
        ratingMax.setFont(new Font("Copper Black", Font.BOLD, 20));
        ratingMax.setColumns(10);
        ratingMax.setBounds(390, 80, 140, 30);
        panel.add(ratingMax);

        labelPriceMin = new JLabel("Price min: ");
        labelPriceMin.setHorizontalAlignment(SwingConstants.LEFT);
        labelPriceMin.setForeground(new Color(188, 143, 143));
        labelPriceMin.setFont(new Font("Copper Black", Font.BOLD, 20));
        labelPriceMin.setBounds(30, 130, 250, 30);
        panel.add(labelPriceMin);

        priceMin = new JTextField();
        priceMin.setForeground(new Color(188, 143, 143));
        priceMin.setFont(new Font("Copper Black", Font.BOLD, 20));
        priceMin.setColumns(10);
        priceMin.setBounds(170, 130, 140, 30);
        panel.add(priceMin);

        labelPriceMax = new JLabel("max: ");
        labelPriceMax.setHorizontalAlignment(SwingConstants.LEFT);
        labelPriceMax.setForeground(new Color(188, 143, 143));
        labelPriceMax.setFont(new Font("Copper Black", Font.BOLD, 20));
        labelPriceMax.setBounds(330, 130, 250, 30);
        panel.add(labelPriceMax);

        priceMax = new JTextField();
        priceMax.setForeground(new Color(188, 143, 143));
        priceMax.setFont(new Font("Copper Black", Font.BOLD, 20));
        priceMax.setColumns(10);
        priceMax.setBounds(390, 130, 140, 30);
        panel.add(priceMax);

        labelCaloriesMin = new JLabel("Calories min: ");
        labelCaloriesMin.setHorizontalAlignment(SwingConstants.LEFT);
        labelCaloriesMin.setForeground(new Color(188, 143, 143));
        labelCaloriesMin.setFont(new Font("Copper Black", Font.BOLD, 20));
        labelCaloriesMin.setBounds(30, 180, 250, 30);
        panel.add(labelCaloriesMin);

        caloriesMin = new JTextField();
        caloriesMin.setForeground(new Color(188, 143, 143));
        caloriesMin.setFont(new Font("Copper Black", Font.BOLD, 20));
        caloriesMin.setColumns(10);
        caloriesMin.setBounds(170, 180, 140, 30);
        panel.add(caloriesMin);

        labelCaloriesMax = new JLabel("max: ");
        labelCaloriesMax.setHorizontalAlignment(SwingConstants.LEFT);
        labelCaloriesMax.setForeground(new Color(188, 143, 143));
        labelCaloriesMax.setFont(new Font("Copper Black", Font.BOLD, 20));
        labelCaloriesMax.setBounds(330, 180, 250, 30);
        panel.add(labelCaloriesMax);

        caloriesMax = new JTextField();
        caloriesMax.setForeground(new Color(188, 143, 143));
        caloriesMax.setFont(new Font("Copper Black", Font.BOLD, 20));
        caloriesMax.setColumns(10);
        caloriesMax.setBounds(390, 180, 140, 30);
        panel.add(caloriesMax);

        labelProteinMin = new JLabel("Protein min: ");
        labelProteinMin.setHorizontalAlignment(SwingConstants.LEFT);
        labelProteinMin.setForeground(new Color(188, 143, 143));
        labelProteinMin.setFont(new Font("Copper Black", Font.BOLD, 20));
        labelProteinMin.setBounds(30, 230, 250, 30);
        panel.add(labelProteinMin);

        proteinMin = new JTextField();
        proteinMin.setForeground(new Color(188, 143, 143));
        proteinMin.setFont(new Font("Copper Black", Font.BOLD, 20));
        proteinMin.setColumns(10);
        proteinMin.setBounds(170, 230, 140, 30);
        panel.add(proteinMin);

        labelProteinMax = new JLabel("max: ");
        labelProteinMax.setHorizontalAlignment(SwingConstants.LEFT);
        labelProteinMax.setForeground(new Color(188, 143, 143));
        labelProteinMax.setFont(new Font("Copper Black", Font.BOLD, 20));
        labelProteinMax.setBounds(330, 230, 250, 30);
        panel.add(labelProteinMax);

        proteinMax = new JTextField();
        proteinMax.setForeground(new Color(188, 143, 143));
        proteinMax.setFont(new Font("Copper Black", Font.BOLD, 20));
        proteinMax.setColumns(10);
        proteinMax.setBounds(390, 230, 140, 30);
        panel.add(proteinMax);

        labelFatMin = new JLabel("Fat min: ");
        labelFatMin.setHorizontalAlignment(SwingConstants.LEFT);
        labelFatMin.setForeground(new Color(188, 143, 143));
        labelFatMin.setFont(new Font("Copper Black", Font.BOLD, 20));
        labelFatMin.setBounds(30, 280, 250, 30);
        panel.add(labelFatMin);

        fatMin = new JTextField();
        fatMin.setForeground(new Color(188, 143, 143));
        fatMin.setFont(new Font("Copper Black", Font.BOLD, 20));
        fatMin.setColumns(10);
        fatMin.setBounds(170, 280, 140, 30);
        panel.add(fatMin);

        labelFatMax = new JLabel("max: ");
        labelFatMax.setHorizontalAlignment(SwingConstants.LEFT);
        labelFatMax.setForeground(new Color(188, 143, 143));
        labelFatMax.setFont(new Font("Copper Black", Font.BOLD, 20));
        labelFatMax.setBounds(330, 280, 250, 30);
        panel.add(labelFatMax);

        fatMax = new JTextField();
        fatMax.setForeground(new Color(188, 143, 143));
        fatMax.setFont(new Font("Copper Black", Font.BOLD, 20));
        fatMax.setColumns(10);
        fatMax.setBounds(390, 280, 140, 30);
        panel.add(fatMax);

        labelSodiumMin = new JLabel("Sodium min: ");
        labelSodiumMin.setHorizontalAlignment(SwingConstants.LEFT);
        labelSodiumMin.setForeground(new Color(188, 143, 143));
        labelSodiumMin.setFont(new Font("Copper Black", Font.BOLD, 20));
        labelSodiumMin.setBounds(30, 330, 250, 30);
        panel.add(labelSodiumMin);

        sodiumMin = new JTextField();
        sodiumMin.setForeground(new Color(188, 143, 143));
        sodiumMin.setFont(new Font("Copper Black", Font.BOLD, 20));
        sodiumMin.setColumns(10);
        sodiumMin.setBounds(170, 330, 140, 30);
        panel.add(sodiumMin);

        labelSodiumMax = new JLabel("max: ");
        labelSodiumMax.setHorizontalAlignment(SwingConstants.LEFT);
        labelSodiumMax.setForeground(new Color(188, 143, 143));
        labelSodiumMax.setFont(new Font("Copper Black", Font.BOLD, 20));
        labelSodiumMax.setBounds(330, 330, 250, 30);
        panel.add(labelSodiumMax);

        sodiumMax = new JTextField();
        sodiumMax.setForeground(new Color(188, 143, 143));
        sodiumMax.setFont(new Font("Copper Black", Font.BOLD, 20));
        sodiumMax.setColumns(10);
        sodiumMax.setBounds(390, 330, 140, 30);
        panel.add(sodiumMax);

        btnFilter = new JButton("Filter");
        btnFilter.setForeground(new Color(255, 255, 255));
        btnFilter.setFont(new Font("Copper Black", Font.BOLD, 25));
        btnFilter.setBackground(new Color(188, 143, 143));
        btnFilter.setBounds(215, 410, 150, 50);
        panel.add(btnFilter);
    }

    /**
     * Metoda getter pentru field-ul ce contine cuvantul dupa care se face filtrarea titlului
     * @return field-ul ce contine cuvantul dupa care se face filtrarea titlului
     */
    public JTextField getKeyWord() {
        return keyWord;
    }

    /**
     * Metoda getter pentru field-ul ce contine valoarea minima a rating-ului
     * @return field-ul ce contine valoarea minima a rating-ului
     */
    public JTextField getRatingMin() {
        return ratingMin;
    }

    /**
     * Metoda getter pentru field-ul ce contine valoarea maxima a rating-ului
     * @return field-ul ce contine valoarea maxima a rating-ului
     */
    public JTextField getRatingMax() {
        return ratingMax;
    }

    /**
     * Metoda getter pentru field-ul ce contine valoarea minima a pretului
     * @return field-ul ce contine valoarea minima a pretului
     */
    public JTextField getPriceMin() {
        return priceMin;
    }

    /**
     * Metoda getter pentru field-ul ce contine valoarea maxima a pretului
     * @return field-ul ce contine valoarea maxima a pretului
     */
    public JTextField getPriceMax() {
        return priceMax;
    }

    /**
     * Metoda getter pentru field-ul ce contine valoarea minima a caloriilor
     * @return field-ul ce contine valoarea minima a caloriilor
     */
    public JTextField getCaloriesMin() {
        return caloriesMin;
    }

    /**
     * Metoda getter pentru field-ul ce contine valoarea maxima a caloriilor
     * @return field-ul ce contine valoarea maxima a caloriilor
     */
    public JTextField getCaloriesMax() {
        return caloriesMax;
    }

    /**
     * Metoda getter pentru field-ul ce contine valoarea minima a proteinelor
     * @return field-ul ce contine valoarea minima a proteinelor
     */
    public JTextField getProteinMin() {
        return proteinMin;
    }

    /**
     * Metoda getter pentru field-ul ce contine valoarea maxima a proteinelor
     * @return field-ul ce contine valoarea maxima a proteinelor
     */
    public JTextField getProteinMax() {
        return proteinMax;
    }

    /**
     * Metoda getter pentru field-ul ce contine valoarea minima a grasimilor
     * @return field-ul ce contine valoarea minima a grasimilor
     */
    public JTextField getFatMin() {
        return fatMin;
    }

    /**
     * Metoda getter pentru field-ul ce contine valoarea maxima a grasimilor
     * @return field-ul ce contine valoarea maxima a grasimilor
     */
    public JTextField getFatMax() {
        return fatMax;
    }

    /**
     * Metoda getter pentru field-ul ce contine cantitatea minima de sodiu
     * @return field-ul ce contine cantitatea minima de sodiu
     */
    public JTextField getSodiumMin() {
        return sodiumMin;
    }

    /**
     * Metoda getter pentru field-ul ce contine cantitatea minima de sodiu
     * @return field-ul ce contine cantitatea minima de sodiu
     */
    public JTextField getSodiumMax() {
        return sodiumMax;
    }

    /**
     * Metoda pentru adaugarea de listener butonului de filtrare
     * @param filter ActionListener-ul folosit
     */
    public void addFilterListener(ActionListener filter){
        this.btnFilter.addActionListener(filter);
    }
}

