package data;

import business.Order;
import model.products.MenuItem;

import java.io.*;
import java.util.*;

/**
 * Clasa pentru serializarea si deserializarea comenzilor
 *
 * @author Raluca - Delia Bozdog
 * @since 25.05.2021
 */
public class SerializatorOrder {
    /**
     * Fisierul de serializare si deserializare
     */
    private final String FILENAME = "order-serialization.ser";
    /**
     * Metoda pentru serializarea unui map de Order si lista de MenuItem
     * @param map map-ul de Order si lista de MenuItem ce se va serializa
     */
    public void serialize(Map<Order, List<MenuItem>> map) {
        try {
            FileOutputStream file = new FileOutputStream(FILENAME);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(map);
            out.close();
            file.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Metoda pentru deserializarea unui map de Order si lista de MenuItem
     * @return map-ul de Order si lista de MenuItem ce s-a deserializat
     */
    @SuppressWarnings("unchecked")
    public Map<Order, List<MenuItem>> deserialize() {
        Map<Order, List<MenuItem>> map = new HashMap<>();
        try {
            FileInputStream file = new FileInputStream(FILENAME);
            if (file.available() > 0) {
                ObjectInputStream in = new ObjectInputStream(file);
                map = (HashMap) in.readObject();
                in.close();
                file.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return map;
    }


}