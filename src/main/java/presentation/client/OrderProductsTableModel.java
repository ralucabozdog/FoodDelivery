package presentation.client;

import model.products.MenuItem;
import presentation.general.ErrorPopUp;

import javax.swing.table.AbstractTableModel;
import java.util.Set;

/**
 * Clasa pentru crearea modelului abstract de tabel al JTable-ului de produse din interfata grafica pentru client
 *
 * @author Raluca - Delia Bozdog
 * @since 25.05.2021
 */
public class OrderProductsTableModel extends AbstractTableModel {
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
     * Constructorul clasei OrderProductsTableModel
     * @param menuItems lista tuturor produselor
     */
    public OrderProductsTableModel(Set<MenuItem> menuItems)
    {
        this.menuItemSet = menuItems;

        int cols = 8;
        int rows = menuItems.size();

        items = new String[rows][cols];
        columnNames = new String[cols];

        columnNames[0] = new String("title");
        columnNames[1] = new String("rating");
        columnNames[2] = new String("calories");
        columnNames[3] = new String("protein");
        columnNames[4] = new String("fat");
        columnNames[5] = new String("sodium");
        columnNames[6] = new String("price");
        columnNames[7] = new String("quantity");

        int j = 0;
        for (MenuItem item : this.menuItemSet) {
            items[j][0] = item.getTitle();
            items[j][1] = Double.toString(item.getRating());
            items[j][2] = Double.toString(item.getCalories());
            items[j][3] = Double.toString(item.getProtein());
            items[j][4] = Double.toString(item.getFat());
            items[j][5] = Double.toString(item.getSodium());
            items[j][6] = Double.toString(item.getPrice());
            items[j][7] = Integer.toString(0);
            j++;
        }
    }

    /**
     * Metoda getter a setului de produse
     * @return setul de produse
     */
    public Set<MenuItem> getMenuItemSet(){
        return this.menuItemSet;
    }

    /**
     * Metoda setter a setului de produse
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
        if(7 == columnIndex) {
            try{
                int val = Integer.parseInt((String)aValue);
                if(val <= 0)
                    new ErrorPopUp("Quantity has to be a positive, non-zero integer").setVisible(true);
                else
                    items[rowIndex][columnIndex] = Integer.toString(val);
            }catch(NumberFormatException e){
                new ErrorPopUp("Quantity has to be an integer").setVisible(true);
            }
        }
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
        if(columnIndex == (columnNames.length - 1))
            return true;
        return false;
    }
}
