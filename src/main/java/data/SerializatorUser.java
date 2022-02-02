package data;

import model.users.User;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Clasa pentru serializarea si deserializarea utilizatorilor
 *
 * @author Raluca - Delia Bozdog
 * @since 25.05.2021
 */
public class SerializatorUser {
    /**
     * Fisierul de serializare si deserializare
     */
    private final String FILENAME = "user-serialization.txt";

    /**
     * Metoda pentru serializarea unui set de User
     * @param userSet setul de User ce se va serializa
     */
    public void serialize(Set<User> userSet) {
        try {
            FileOutputStream file = new FileOutputStream(FILENAME);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(userSet);
            out.close();
            file.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda pentru deserializarea unui set de User
     * @return set de User deserializat
     */
    @SuppressWarnings("unchecked")
    public Set<User> deserialize() {
        Set<User> userSet = new HashSet<User>();
        try {
            FileInputStream file = new FileInputStream(FILENAME);
            if (file.available() > 0) {
                ObjectInputStream in = new ObjectInputStream(file);
                userSet = (Set<User>) in.readObject();
                in.close();
                file.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return userSet;
    }


}