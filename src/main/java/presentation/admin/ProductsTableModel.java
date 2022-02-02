package presentation.admin;

import model.products.MenuItem;

import javax.swing.table.AbstractTableModel;
import java.lang.reflect.Field;
import java.util.Set;

/**
 * Clasa pentru crearea modelului abstract de tabel a unui JTable ineditabil de produse
 *
 * @author Raluca - Delia Bozdog
 * @since 25.05.2021
 */
public class ProductsTableModel extends AbstractTableModel {
    /**
     * Lista tuturor produselor
     */
    private Set<MenuItem> menuItemSet;
    /**
     * "Matricea" de String-uri ce retine datele despre un produs pe un rand, cu fiecare variabila instanta pe cate o coloana
     */
    private String[][] items;
    /**
     * Lista numelor coloanelor tabelului
     */
    private String[] columnNames;

    /**
     * Constructorul clasei ProductsTableModel
     * @param menuItems lista tuturor produselor
     */
    public ProductsTableModel(Set<MenuItem> menuItems)
    {
        Class type = new MenuItem().getClass();
        this.menuItemSet = menuItems;
        int cols = type.getDeclaredFields().length;
        int rows = menuItems.size();
        items = new String[rows][cols];
        columnNames = new String[cols];
        int i = 0;
        for(Field f : type.getDeclaredFields()){
            columnNames[i] = f.getName();
            i++;
        }
        int j = 0;
        for(MenuItem item: menuItemSet){
            i = 0;
            for(Field f : type.getDeclaredFields()){
                f.setAccessible(true);
                try {
                    items[j][i] = f.get(item).toString();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                i++;
            }
            j++;
        }
    }

    /**
     * Metoda getter pentru setul de produse
     * @return setul de produse
     */
    public Set<MenuItem> getMenuItemSet(){
        return this.menuItemSet;
    }

    /**
     * Metoda setter pentru setul de produse
     * @param menuItemSet noul set de produse
     */
    public void setMenuItemSet(Set<MenuItem> menuItemSet) {
        this.menuItemSet = menuItemSet;
    }

    /**
     * Metoda pentru aflarea numelui unei coloane in functie de indicele sau
     * @param column indicele coloanei
     * @return numele coloanei cu indicele column
     */
    @Override
    public String getColumnName(int column){
        return columnNames[column];
    }

    /**
     * Metoda pentru aflarea numarului total de coloane al tabelului
     * @return numarul total de coloane din tabel
     */
    @Override
    public int getColumnCount()
    {
        return columnNames.length;
    }

    /**
     * Metoda pentru aflarea numarului total de linii al tabelului
     * @return numarul de linii din tabel
     */
    @Override
    public int getRowCount()
    {
        return menuItemSet.size();
    }

    /**
     * Metoda pentru returnarea obiectului aflat in celula de la intersectia dintre o anumita linie si o anumita coloana
     * @param rowIndex indicele randului
     * @param columnIndex indicele coloanei
     * @return obiectul aflat in celula de la intersectia dintre linia rowIndex si coloana columnIndex
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        return items[rowIndex][columnIndex];
    }

    /**
     * Metoda pentru setarea unei noi valori in celula de la intersectiadintre o anumita linie si o anumita coloana
     * @param aValue valoarea cu care se va actualiza celula
     * @param rowIndex indicele randului
     * @param columnIndex indicele coloanei
     */
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex)
    {
    }

    /**
     * Metoda pentru stabilirea statutului de editabil/non-editabil al unei celule
     * @param rowIndex randul pe care se afla celula
     * @param columnIndex coloana pe care se afla celula
     * @return true daca celula este editabila; false in caz contrar
     */
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex)
    {
        return false;
    }
}
