package presentation.admin;

import model.products.MenuItem;
import data.SerializatorMenuItem;
import presentation.general.ErrorPopUp;

import javax.swing.table.AbstractTableModel;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Clasa pentru crearea modelului abstract de tabel al JTable-ului din interfata grafica de editare produse
 *
 * @author Raluca - Delia Bozdog
 * @since 25.05.2021
 */
public class EditProductsTableModel extends AbstractTableModel {
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
     * Constructorul clasei EditProductsTableModel
     * @param menuItems lista tuturor produselor
     */
    public EditProductsTableModel(Set<MenuItem> menuItems) {
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
     * Metoda pentru aflarea numelui unei coloane in functie de indicele sau
     * @param column indicele coloanei
     * @return numele coloanei cu indicele column
     */
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    /**
     * Metoda pentru aflarea numarului total de coloane al tabelului
     * @return numarul total de coloane din tabel
     */
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    /**
     * Metoda pentru aflarea numarului total de linii al tabelului
     * @return numarul de linii din tabel
     */
    @Override
    public int getRowCount() {
        return menuItemSet.size();
    }

    /**
     * Metoda pentru returnarea obiectului aflat in celula de la intersectia dintre o anumita linie si o anumita coloana
     * @param rowIndex indicele randului
     * @param columnIndex indicele coloanei
     * @return obiectul aflat in celula de la intersectia dintre linia rowIndex si coloana columnIndex
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return items[rowIndex][columnIndex];
    }

    /**
     * Metoda pentru setarea unei noi valori in celula de la intersectiadintre o anumita linie si o anumita coloana
     * @param aValue valoarea cu care se va actualiza celula
     * @param rowIndex indicele randului
     * @param columnIndex indicele coloanei
     */
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

        MenuItem row = new MenuItem((String) getValueAt(rowIndex, 0),               //title
                Double.parseDouble((String) getValueAt(rowIndex, 1)),              //rating
                Double.parseDouble((String) getValueAt(rowIndex, 2)),              //calories
                Double.parseDouble((String) getValueAt(rowIndex, 3)),              //protein
                Double.parseDouble((String) getValueAt(rowIndex, 4)),              //fat
                Double.parseDouble((String) getValueAt(rowIndex, 5)),              //sodium
                Double.parseDouble((String) getValueAt(rowIndex, 6)));             //price
        MenuItem rowNew = new MenuItem((String) getValueAt(rowIndex, 0),               //title
                Double.parseDouble((String) getValueAt(rowIndex, 1)),              //rating
                Double.parseDouble((String) getValueAt(rowIndex, 2)),              //calories
                Double.parseDouble((String) getValueAt(rowIndex, 3)),              //protein
                Double.parseDouble((String) getValueAt(rowIndex, 4)),              //fat
                Double.parseDouble((String) getValueAt(rowIndex, 5)),              //sodium
                Double.parseDouble((String) getValueAt(rowIndex, 6)));             //price
        String aValueString = aValue.toString();
        String title = new String();
        double rating = 0;
        double calories = 0;
        double protein = 0;
        double fat = 0;
        double sodium = 0;
        double price = 0;
        if(aValueString.isEmpty()){
            new ErrorPopUp("Fields cannot be null!").setVisible(true);
            return;
        }
        if(columnIndex == 0){
            title = aValueString;
            rowNew.setTitle(title);
        }
        if(columnIndex == 1){
            try{
                rating = Double.parseDouble(aValueString);
                if(rating < 0 || rating > 5){
                    new ErrorPopUp("Rating has to be a real number between 0 and 5!").setVisible(true);
                    return;
                }
                rowNew.setRating(rating);
            }catch(NumberFormatException exception){
                new ErrorPopUp("Rating has to be a real number!").setVisible(true);
                return;
            }
        }
        if(columnIndex == 2){
            try{
                calories = Double.parseDouble(aValueString);
                if(calories < 0){
                    new ErrorPopUp("Calories has to be a positive real number!").setVisible(true);
                    return;
                }
                rowNew.setCalories(calories);
            }catch(NumberFormatException exception){
                new ErrorPopUp("Calories has to be a real number!").setVisible(true);
                return;
            }
        }
        if(columnIndex == 3){
            try{
                protein = Double.parseDouble(aValueString);
                if(protein < 0){
                    new ErrorPopUp("Protein has to be a positive real number!").setVisible(true);
                    return;
                }
                rowNew.setProtein(protein);
            }catch(NumberFormatException exception){
                new ErrorPopUp("Protein has to be a real number!").setVisible(true);
                return;
            }
        }
        if(columnIndex == 4){
            try{
                fat = Double.parseDouble(aValueString);
                if(fat < 0){
                    new ErrorPopUp("Fat has to be a positive real number!").setVisible(true);
                    return;
                }
                rowNew.setFat(fat);
            }catch(NumberFormatException exception){
                new ErrorPopUp("Fat has to be a real number!").setVisible(true);
                return;
            }
        }
        if(columnIndex == 5){
            try{
                sodium = Double.parseDouble(aValueString);
                if(sodium < 0){
                    new ErrorPopUp("Sodium has to be a positive real number!").setVisible(true);
                    return;
                }
                rowNew.setSodium(sodium);
            }catch(NumberFormatException exception){
                new ErrorPopUp("Sodium has to be a real number!").setVisible(true);
                return;
            }
        }
        if(columnIndex == 6){
            try{
                price = Double.parseDouble(aValueString);
                if(price < 0){
                    new ErrorPopUp("Price has to be a positive real number!").setVisible(true);
                    return;
                }
                rowNew.setPrice(price);
            }catch(NumberFormatException exception){
                new ErrorPopUp("Price has to be a real number!").setVisible(true);
                return;
            }
        }
        List<MenuItem> menuItemList = this.menuItemSet.stream().filter(x -> x.getTitle().equals(row.getTitle())).collect(Collectors.toList());
        this.menuItemSet.remove(menuItemList.get(0));
        items[rowIndex][columnIndex] = aValue.toString();
        this.menuItemSet.add(rowNew);
        new SerializatorMenuItem().serialize(this.menuItemSet);
    }

    /**
     * Metoda pentru stabilirea statutului de editabil/non-editabil al unei celule
     * @param rowIndex randul pe care se afla celula
     * @param columnIndex coloana pe care se afla celula
     * @return true daca celula este editabila; false in caz contrar
     */
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }
}