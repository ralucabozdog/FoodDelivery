package presentation.admin;

import model.products.MenuItem;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.Set;

/**
 * Clasa pentru crearea interfetei grafice de editare produse
 *
 * @author Raluca - Delia Bozdog
 * @since 25.05.2021
 */
public class EditProductsFrame extends JFrame {
    /**
     * Tabelul in care sunt vizibile toate produsele si se pot face editari asupra acestora
     */
    private JTable table;
    /**
     * Lista tuturor produselor
     */
    private Set<MenuItem> menuItems;

    /**
     * Constructorul ferestrei pentru introducerea datelor de editare a produselor
     * @param menuItemSet lista tuturor produselor
     */
    public EditProductsFrame(Set<MenuItem> menuItemSet){
        setTitle("Edit products");
        this.menuItems = menuItemSet;
        table = new JTable(new EditProductsTableModel(this.menuItems));
        table.getColumnModel().getColumn(0).setPreferredWidth(350);
        table.setBounds(30, 40, 200, 300);
        table.setRowHeight(30);
        table.setBackground(new Color(186, 200, 222));
        table.setFont(new Font("Copper Black", Font.PLAIN, 18));
        table.setSelectionBackground(new Color(188, 143, 143));

        JTextField auxiliary = new JTextField();
        auxiliary.setFont(new Font("Copper Black", Font.PLAIN, 18));
        auxiliary.setForeground(new Color(188, 143, 143));
        for (int i = 0; i < table.getColumnCount(); i++) {
            TableColumn col = table.getColumnModel().getColumn(i);
            col.setCellEditor(new DefaultCellEditor(auxiliary));
        }

        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(188, 143, 143));
        header.setFont(new Font("Copper Black", Font.BOLD, 18));

        JScrollPane sp = new JScrollPane(table);
        add(sp);
        setSize(1500, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
