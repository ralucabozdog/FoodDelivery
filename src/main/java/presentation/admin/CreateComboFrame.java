package presentation.admin;

import model.products.CompositeProduct;
import model.products.MenuItem;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Clasa pentru definirea modelului general de fereastra de stergere dintr-un tabel din baza de date
 */
public class CreateComboFrame extends JFrame {
    /**
     * Tabelul cu obiecte extrase din baza de date
     */
    private JTable table;
    /**
     * Butonul prin actionarea caruia se creaza un produs compus
     */
    private JButton btnCombine;
    /**
     * Eticheta ce indica field-ul de introducere a numelui produslui compus
     */
    private JLabel labelComboTitle;
    /**
     * Field-ul de introducere a numelui produslui compus
     */
    private JTextField comboTitle;
    /**
     * Modelul de tabel al tabelului ce reda toate produsele
     */
    private ProductsTableModel productsTableModel;
    /**
     * Setul tuturor produselor
     */
    private Set<MenuItem> menuItemSet;

    /**
     * Constructorul clasei CreateComboFrame
     * @param menuItemSet setul de produse
     */
    public CreateComboFrame(Set<MenuItem> menuItemSet)
    {

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        labelComboTitle = new JLabel("Combo name: ");
        labelComboTitle.setHorizontalAlignment(SwingConstants.LEFT);
        labelComboTitle.setForeground(new Color(188, 143, 143));
        labelComboTitle.setFont(new Font("Copper Black", Font.BOLD, 25));
        labelComboTitle.setPreferredSize(new Dimension(180, 40));

        comboTitle = new JTextField();
        comboTitle.setForeground(new Color(188, 143, 143));
        comboTitle.setFont(new Font("Copper Black", Font.BOLD, 25));
        comboTitle.setPreferredSize(new Dimension(500, 40));

        panel.setPreferredSize(new Dimension(1500, 50));
        panel.setBackground(new Color(186, 200, 222));
        panel.add(labelComboTitle);
        panel.add(comboTitle);
        this.add(panel, BorderLayout.NORTH);

        setTitle("Create a composite product");

        this.menuItemSet = menuItemSet;
        productsTableModel = new ProductsTableModel(this.menuItemSet);
        table = new JTable(productsTableModel);
        table.getColumnModel().getColumn(0).setPreferredWidth(350);
        table.setBounds(30, 40, 200, 300);
        table.setRowHeight(30);
        table.setBackground(new Color(186, 200, 222));
        table.setFont(new Font("Copper Black", Font.PLAIN, 18));
        table.setSelectionBackground(new Color(188, 143, 143));
        table.setDefaultEditor(Object.class, null);

        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(188, 143, 143));
        header.setFont(new Font("Copper Black", Font.BOLD, 18));

        JScrollPane sp = new JScrollPane(table);
        this.add(sp, BorderLayout.CENTER);
        setSize(1500, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        btnCombine = new JButton("Combine");
        btnCombine.setForeground(new Color(255, 255, 255));
        btnCombine.setFont(new Font("Copper Black", Font.BOLD, 25));
        btnCombine.setBackground(new Color(188, 143, 143));
        btnCombine.setBounds(170, 480, 150, 50);
        JPanel p2 = new JPanel();
        p2.setPreferredSize(new Dimension(1500, 60));
        p2.setBackground(new Color(186, 200, 222));
        p2.add(btnCombine);
        this.add(p2, BorderLayout.SOUTH);
    }

    /**
     * Metoda de actualizare a tabelului
     * @param menuItems noua lista de produse
     */
    public void update(Set<MenuItem> menuItems){
        this.productsTableModel.setMenuItemSet(menuItems);
        this.menuItemSet = menuItems;

        this.table.setModel(new ProductsTableModel(menuItems));
        this.table.getColumnModel().getColumn(0).setPreferredWidth(350);
    }

    /**
     * Metoda ce creeaza un produs compus cu obiectelor selectate din tabel
     * @return produsul compus
     */
    public CompositeProduct combineSelectedRows(){
        List<MenuItem> list = new ArrayList<MenuItem>();
        int[] vector = table.getSelectedRows();
        for(int i = 0; i < vector.length; i++){
            MenuItem comboComponent = new MenuItem((String) table.getValueAt(vector[i], 0),   //title
                    Double.parseDouble((String) table.getValueAt(vector[i], 1)),              //rating
                    Double.parseDouble((String) table.getValueAt(vector[i], 2)),              //calories
                    Double.parseDouble((String) table.getValueAt(vector[i], 3)),              //protein
                    Double.parseDouble((String) table.getValueAt(vector[i], 4)),              //fat
                    Double.parseDouble((String) table.getValueAt(vector[i], 5)),              //soium
                    Double.parseDouble((String) table.getValueAt(vector[i], 6)));             //price
            list.add(comboComponent);
        }
        CompositeProduct compositeProduct = new CompositeProduct(comboTitle.getText(), list);

       return compositeProduct;
    }

    /**
     * Metoda getter a tabelului ferestrei
     * @return tabelul ferestrei
     */
    public JTable getTable() {
        return table;
    }

    /**
     * Metoda pentru adaugarea de listener butonului de creare a unui produs compus
     * @param combine ActionListener-ul folosit
     */
    public void addCombineListener(ActionListener combine){this.btnCombine.addActionListener(combine);}
}

