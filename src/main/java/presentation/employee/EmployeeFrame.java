package presentation.employee;

import business.DeliveryService;
import business.Order;
import model.products.MenuItem;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Observable;
import java.util.Observer;

/**
 * Clasa pentru crearea ferestrei unui angajat
 *
 * @author Raluca - Delia Bozdog
 * @since 25.05.2021
 */
public class EmployeeFrame extends JFrame implements Observer {
    /**
     * Panel-ul ferestrei
     */
    private JPanel contentPane;
    /**
     * Lista afisata in interfata
     */
    private JList list;
    /**
     * Modelul folosit pentru lista
     */
    private DefaultListModel dlm;
    /**
     * Momentul log-in-ului angajatului
     */
    private LocalDateTime logInMoment;

    /**
     * Constructorul clasei EmployeeFrame
     */
    public EmployeeFrame() {

            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setBounds(100, 100, 900, 600);
            setLocationRelativeTo(null);
            contentPane = new JPanel();
            contentPane.setBackground(new Color(186, 200, 222));
            contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
            setContentPane(contentPane);
            contentPane.setLayout(null);

            JLabel labelOrders = new JLabel("Orders");
            labelOrders.setHorizontalAlignment(SwingConstants.CENTER);
            labelOrders.setForeground(new Color(188, 143, 143));
            labelOrders.setFont(new Font("Copper Black", Font.BOLD, 29));
            labelOrders.setBounds(0, 11, 900, 52);
            contentPane.add(labelOrders);

            JPanel panel = new JPanel();
            panel.setBorder(new TitledBorder(null, "New orders", TitledBorder.LEADING, TitledBorder.TOP, null, null));
            panel.setBounds(30, 74, 825, 400);
            contentPane.add(panel);
            panel.setLayout(null);

            JScrollPane scrollPane = new JScrollPane();
            scrollPane.setBounds(6, 16, 815, 380);
            panel.add(scrollPane);

            list = new JList();
            list.setForeground(new Color(188, 143, 143));
            list.setFont(new Font("Copper Black", Font.PLAIN, 20));
            scrollPane.setViewportView(list);


            /*int i = 0;
            dlm = new DefaultListModel();

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

            for(Order order : deliveryService.getMap().keySet()){
                i++;
                String s = new String(i + ") ORDER ID: " + order.getOrderID() + "    CLIENT ID: " + order.getClientID() + "      ORDER DATE: " + dtf.format(order.getOrderDate()));
                dlm.add(dlm.size(), s);
                for(MenuItem menuItem : deliveryService.getMap().get(order)){
                    dlm.add(dlm.size(), "          " + menuItem.getTitle() + "\n");
                }
            }
            list.setModel(dlm);*/

            logInMoment = LocalDateTime.now();
        }

    /**
     * Metoda de update a ferestrei, suprascrisa din interfata Observer
     * @param o obiectul observabil (DeliveryService)
     * @param arg obiectul transmis
     */
    @Override
    public void update(Observable o, Object arg) {
        DeliveryService deliveryService = (DeliveryService) o;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        int i = 0;
        dlm = new DefaultListModel();
        for(Order order : deliveryService.getMap().keySet()){
            if(order.getOrderDate().compareTo(logInMoment) >= 0){
                i++;
                String s = new String(i + ") ORDER ID: " + order.getOrderID() + "    CLIENT ID: " + order.getClientID() + "      ORDER DATE: " + dtf.format(order.getOrderDate()));
                dlm.add(dlm.size(), s);
                for(MenuItem menuItem : deliveryService.getMap().get(order)){
                    dlm.add(dlm.size(), "          " + menuItem.getTitle() + "\n");
                }
            }
        }
        list.setModel(dlm);
        list.update(list.getGraphics());
    }
}
