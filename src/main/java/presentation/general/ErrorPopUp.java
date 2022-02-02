package presentation.general;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clasa pentru construirea casetei de dialog ce avertizeaza user-ul ca a introdus un input invalid
 *
 * @author Raluca - Delia Bozdog
 * @since 25.05.2021
 */
public class ErrorPopUp extends JDialog {

    /**
     * JPanel-ul ferestrei
     */
    private JPanel contentPanel;
    /**
     * Butonul de Retry
     */
    private JButton retryButton;
    /**
     * Eticheta ce afiseaza mesajul de eroare
     */
    private JLabel lblNewLabel;

    /**
     * Contructorul clasei presentation.general.ErrorPopUp
     * @param message mesajul ce se va afisa in fereastra pop-up
     */
    public ErrorPopUp(String message) {

        contentPanel = new JPanel();
        setBounds(100, 100, 500, 340);
        this.setLocationRelativeTo(null);
        contentPanel.setBackground(new Color(186, 200, 222));
        contentPanel.setLayout(null);
        setContentPane(contentPanel);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        retryButton = new JButton("Retry");
        retryButton.setForeground(new Color(255, 255, 255));
        retryButton.setFont(new Font("Arial Nova Light", Font.BOLD, 14));
        retryButton.setBackground(new Color(188, 143, 143));
        retryButton.setBounds(180, 220, 140, 48);
        contentPanel.add(retryButton);

        retryButton.addActionListener((ActionListener) new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        lblNewLabel = new JLabel(message);
        lblNewLabel.setForeground(new Color(188, 143, 143));
        lblNewLabel.setFont(new Font("Copper Black", Font.BOLD, 20));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(0, 10, 500, 66);
        contentPanel.add(lblNewLabel);

        JLabel lblNewLabel1 = new JLabel("");
        ImageIcon img = new ImageIcon(new ImageIcon("src\\main\\resources\\ErrorPopUp.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        lblNewLabel1.setIcon(img);
        lblNewLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel1.setBounds(0, 80, 500, 113);
        contentPanel.add(lblNewLabel1);
    }
}
