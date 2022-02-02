package data;

import model.products.MenuItem;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Clasa pentru serializarea si deserializarea produselor din meniu
 *
 * @author Raluca - Delia Bozdog
 * @since 25.05.2021
 */
public class SerializatorMenuItem {
    /**
     * Fisierul de serializare si deserializare
     */
    private final String FILENAME = "menu-serialization.txt";

    /**
     * Metoda pentru serializarea unui set de MenuItem
     * @param items setul de MenuItem ce se va serializa
     */
    public void serialize(Set<MenuItem> items) {
        try {
            FileOutputStream file = new FileOutputStream(FILENAME);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(items);
            out.close();
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda pentru deserializarea unui set de MenuItem
     * @return set de MenuItem deserializat
     */
    @SuppressWarnings("unchecked")
    public Set<MenuItem> deserialize() {
        Set<MenuItem> menuItemSet = new HashSet<MenuItem>();
        try {
            FileInputStream file = new FileInputStream(FILENAME);
            if (file.available() > 0) {
                ObjectInputStream in = new ObjectInputStream(file);
                menuItemSet = (Set<MenuItem>) in.readObject();
                in.close();
                file.close();
            }
        } catch (Exception e ) {
            e.printStackTrace();
        }
        return menuItemSet;
    }

}