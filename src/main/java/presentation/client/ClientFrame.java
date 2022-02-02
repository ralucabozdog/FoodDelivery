package presentation.client;

import model.products.MenuItem;
import model.users.User;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Set;
import java.util.List;

/**
 * Clasa pentru crearea ferestrei principale a unui client
 *
 * @author Raluca - Delia Bozdog
 * @since 25.05.2021
 */
public class ClientFrame extends JFrame {
    /**
     * JPanel al partii de filtrare
     */
    private JPanel panelFilter;
    /**
     * JPanel al etichetei cu masejul de alegere a produselor
     */
    private JPanel panelChooseProduct;
    /**
     * JScrollPane al tabelului de produse
     */
    private JScrollPane scrollTableProduct;
    /**
     * Panel al zonei de alegere produs din fereastra
     */
    private JPanel panelProduct;
    /**
     * Lista tuturor produselor din baza de date
     */
    private Set<MenuItem> menuItemSet;
    /**
     * Modelul de tabel pentru tabelul de produse
     */
    private OrderProductsTableModel model;
    /**
     * JPanel al butonului de introducere a comenzii
     */
    private JPanel panelOrder;
    /**
     * Tabelul de produse
     */
    private JTable tableProduct;
    /**
     * Eticheta ce indica faptul ca se pot selecta produsele dorite
     */
    private JLabel labelChooseProduct;
    /**
     * Butonul prin actionarea caruia se inregisreaza noile comenzi in baza de date
     */
    private JButton buttonOrder;
    /**
     * Butonul prin actionarea caruia se deschide fereastra de filtrare
     */
    private JButton buttonFilter;
    /**
     * Panel-ul de sus din fereastra
     */
    private JPanel panelUp;

    /**
     * Clientul care s-a logat in aplicatie
     */
    private User client;

    /**
     * Constructorul clasei ClientFrame
     * @param menuItems setul de MenuItem
     * @param client clientul care este logat
     */
    public ClientFrame(Set<MenuItem> menuItems, User client){

        this.client = client;

        setTitle("Our menu");
        setBackground(new Color(186, 200, 222));
        setSize(1500, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        this.panelProduct = new JPanel(new BorderLayout());
        this.menuItemSet = menuItems;
        this.model = new OrderProductsTableModel(menuItems);
        tableProduct = new JTable(model);
        tableProduct.getColumnModel().getColumn(0).setPreferredWidth(350);
        tableProduct.setBounds(30, 40, 200, 300);
        tableProduct.setRowHeight(30);
        tableProduct.setBackground(new Color(186, 200, 222));
        tableProduct.setFont(new Font("Copper Black", Font.PLAIN, 18));
        tableProduct.setSelectionBackground(new Color(188, 143, 143));
        tableProduct.setDefaultEditor(Object.class, null);

        JTextField auxiliary = new JTextField();
        auxiliary.setFont(new Font("Copper Black", Font.PLAIN, 18));
        auxiliary.setForeground(new Color(188, 143, 143));

        TableColumn col = tableProduct.getColumnModel().getColumn(tableProduct.getColumnCount() - 1);
        col.setCellEditor(new DefaultCellEditor(auxiliary));

        JTableHeader header1 = tableProduct.getTableHeader();
        header1.setBackground(new Color(188, 143, 143));
        header1.setFont(new Font("Copper Black", Font.BOLD, 18));

        scrollTableProduct = new JScrollPane(tableProduct);
        this.panelProduct.add(scrollTableProduct, BorderLayout.CENTER);
        this.add(panelProduct, BorderLayout.CENTER);

        labelChooseProduct = new JLabel("Order your favourite food!");
        labelChooseProduct.setHorizontalAlignment(0);
        labelChooseProduct.setForeground(new Color(188, 143, 143));
        labelChooseProduct.setFont(new Font("Copper Black", Font.BOLD, 30));
        labelChooseProduct.setPreferredSize(new Dimension(700, 50));

        panelChooseProduct = new JPanel(new BorderLayout());
        panelChooseProduct.setPreferredSize(new Dimension(1500, 60));
        panelChooseProduct.setBackground(new Color(186, 200, 222));
        panelChooseProduct.add(labelChooseProduct, BorderLayout.CENTER);

        buttonFilter = new JButton("Filter");
        buttonFilter.setForeground(new Color(255, 255, 255));
        buttonFilter.setFont(new Font("Copper Black", Font.BOLD, 15));
        buttonFilter.setBackground(new Color(188, 143, 143));
        buttonFilter.setPreferredSize(new Dimension(120, 15));

        this.panelFilter = new JPanel(new BorderLayout());
        this.panelFilter.add(buttonFilter, BorderLayout.EAST);
        this.panelFilter.setPreferredSize(new Dimension(1500, 25));
        this.panelFilter.setBorder(BorderFactory.createEmptyBorder(0,0,0,50));
        this.panelFilter.setBackground(new Color(186, 200, 222));

        this.panelUp = new JPanel();
        this.panelUp.setPreferredSize(new Dimension(1500, 110));
        this.panelUp.setBackground(new Color(186, 200, 222));
        this.panelUp.add(panelChooseProduct, BorderLayout.NORTH);
        this.panelUp.add(panelFilter, BorderLayout.SOUTH);
        this.add(panelUp, BorderLayout.NORTH);

        buttonOrder = new JButton("Order");
        buttonOrder.setForeground(new Color(255, 255, 255));
        buttonOrder.setFont(new Font("Copper Black", Font.BOLD, 25));
        buttonOrder.setBackground(new Color(188, 143, 143));
        buttonOrder.setPreferredSize(new Dimension(250, 50));

        this.panelOrder = new JPanel();
        this.panelOrder.add(buttonOrder);
        this.panelOrder.setPreferredSize(new Dimension(1500, 60));
        this.panelOrder.setBackground(new Color(186, 200, 222));
        this.add(panelOrder, BorderLayout.SOUTH);
    }

    /**
     * Metoda getter pentru setul de MenuItem
     * @return setul de MenuItem
     */
    public Set<MenuItem> getMenuItemSet() {
        return menuItemSet;
    }

    /**
     * Metoda setter pentru setul de MenuItem
     * @param menuItemSet noul set de MenuItem
     */
    public void setMenuItemSet(Set<MenuItem> menuItemSet){
        this.menuItemSet = menuItemSet;
        this.tableProduct.update(this.tableProduct.getGraphics());
    }

    /**
     * Metoda de actualizare a ferestrei
     * @param menuItems noua lista de MenuItem
     */
    public void update(Set<MenuItem> menuItems){
        this.model.setMenuItemSet(menuItems);
        this.menuItemSet = menuItems;

        this.tableProduct.setModel(new OrderProductsTableModel(menuItems));
        this.tableProduct.getColumnModel().getColumn(0).setPreferredWidth(350);
        JTextField auxiliary = new JTextField();
        auxiliary.setFont(new Font("Copper Black", Font.PLAIN, 18));
        auxiliary.setForeground(new Color(188, 143, 143));

        TableColumn col = tableProduct.getColumnModel().getColumn(tableProduct.getColumnCount() - 1);
        col.setCellEditor(new DefaultCellEditor(auxiliary));
    }

    /**
     * Metoda ce returneaza randurile selectate din tabelul ce contine toate produsele; se vor returna produsele comandate
     * @return lista produselor selectate din tabelul ce contine toate produsele
     */
    public List<MenuItem> selectedMenuItemsRows() {
        List<MenuItem> list = new ArrayList<MenuItem>();

        int[] vector = tableProduct.getSelectedRows();
        for(int i = 0; i < vector.length; i++){
            MenuItem menuItem = new MenuItem((String) tableProduct.getValueAt(vector[i], 0),   //title
                    Double.parseDouble((String) tableProduct.getValueAt(vector[i], 1)),              //rating
                    Double.parseDouble((String) tableProduct.getValueAt(vector[i], 2)),              //calories
                    Double.parseDouble((String) tableProduct.getValueAt(vector[i], 3)),              //protein
                    Double.parseDouble((String) tableProduct.getValueAt(vector[i], 4)),              //fat
                    Double.parseDouble((String) tableProduct.getValueAt(vector[i], 5)),              //soium
                    Double.parseDouble((String) tableProduct.getValueAt(vector[i], 6)));             //price
                list.add(menuItem);
        }
        return list;
    }

    /***
     * Metoda ce returneaza lista cantitatilor pentru fiecare produs din lista de produse
     * @return lista de intregi reprezentand cantitatile pentru produsele comandate
     */
    public List<Integer> selectedMenuItemsQuantities(){
        List<Integer> list = new ArrayList<Integer>();

        int[] vector = tableProduct.getSelectedRows();
        for(int i = 0; i < vector.length; i++)
                list.add(Integer.parseInt((String) tableProduct.getValueAt(vector[i], 7)));
        return list;
    }

    /**
     * Metoda getter pentru id-ul clientului curent
     * @return id-ul clientului
     */
    public User getClient() {
        return client;
    }

    /**
     * Metoda pentru adaugarea de listener butonului de deschidere a operatiei de filtrare
     * @param filter ActionListener-ul folosit
     */
    public void addFilterListener(ActionListener filter){
        this.buttonFilter.addActionListener(filter);
    }

    /**
     * Metoda pentru adaugarea de listener butonului de plasare a comenzii
     * @param order ActionListener-ul folosit
     */
    public void addOrderListener(ActionListener order){
        this.buttonOrder.addActionListener(order);
    }

}

