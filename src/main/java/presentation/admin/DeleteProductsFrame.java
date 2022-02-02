package presentation.admin;

import model.products.MenuItem;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Clasa pentru crearea interfetei grafice de stergere produse
 *
 * @author Raluca - Delia Bozdog
 * @since 25.05.2021
 */
public class DeleteProductsFrame extends JFrame {
    /**
     * Tabelul in care sunt vizibile toate produsele si se pot sterge produsele selectate
     */
    private JTable table;

    /**
     * Modeulul de tabel al tabelului de produse
     */
    private ProductsTableModel productsTableModel;
    /**
     * Setul tuturor produselor
     */
    private Set<MenuItem> menuItemSet;
    /**
     * Butonul prin actionarea caruia se realizeaza stergeri
     */
    private JButton btnDelete;

    /**
     * Constructorul clasei DeleteProductsFrame
     * @param menuItemSet setul de MenuItem
     */
    public DeleteProductsFrame(Set<MenuItem> menuItemSet)
    {

        setTitle("Delete products");
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

        btnDelete = new JButton("Delete");
        btnDelete.setForeground(new Color(255, 255, 255));
        btnDelete.setFont(new Font("Copper Black", Font.BOLD, 25));
        btnDelete.setBackground(new Color(188, 143, 143));
        btnDelete.setBounds(170, 480, 150, 50);
        JPanel p2 = new JPanel();
        p2.setPreferredSize(new Dimension(1500, 60));
        p2.setBackground(new Color(186, 200, 222));
        p2.add(btnDelete);
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
     * Metoda ce returneaza lista obiectelor selectate din tabel; acestea sunt obiectele ce vor fi sterse din baza de date
     * @return lista obiectelor ce se vor sterge
     */
    public List<MenuItem> getSelectedRows(){
        List<MenuItem> list = new ArrayList<MenuItem>();
        int[] vector = table.getSelectedRows();
        for(int i = 0; i < vector.length; i++){
            MenuItem objectToDelete = new MenuItem((String) table.getValueAt(vector[i], 0),   //title
                    Double.parseDouble((String) table.getValueAt(vector[i], 1)),              //rating
                    Double.parseDouble((String) table.getValueAt(vector[i], 2)),              //calories
                    Double.parseDouble((String) table.getValueAt(vector[i], 3)),              //protein
                    Double.parseDouble((String) table.getValueAt(vector[i], 4)),              //fat
                    Double.parseDouble((String) table.getValueAt(vector[i], 5)),              //soium
                    Double.parseDouble((String) table.getValueAt(vector[i], 6)));             //price
            list.add(objectToDelete);
        }

        return list;
    }

    /**
     * Metoda pentru adaugarea de listener butonului de stergere de obiecte din baza de date
     * @param delete ActionListener-ul folosit
     */
    public void addDeleteListener(ActionListener delete){this.btnDelete.addActionListener(delete);}
}

