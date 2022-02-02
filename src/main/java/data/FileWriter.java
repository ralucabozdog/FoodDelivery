package data;

import business.DeliveryService;
import model.products.MenuItem;
import model.users.User;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Clasa pentru importarea de produse si utilizatori
 *
 * @author Raluca - Delia Bozdog
 * @since 25.05.2021
 */
public class FileWriter {
    /**
     * Metoda pentru importarea de produse dintr-un fisier .csv
     * @param fileName numele fisierului
     * @return set de MenuItem
     */
    public Set<MenuItem> importMenuItems(String fileName){
        Set<MenuItem> menuItemSet = new HashSet<>();
        try {
            Stream<String> stringStream = Files.lines(Paths.get("src\\main\\resources\\" + fileName));
            menuItemSet = stringStream
                    .skip(1)
                    .distinct()
                    .map(x -> x.split(","))
                    .map(x -> new MenuItem(x[0], Double.parseDouble(x[1]), Double.parseDouble(x[2]), Double.parseDouble(x[3]), Double.parseDouble(x[4]), Double.parseDouble(x[5]), Double.parseDouble(x[6])))
                    .collect(Collectors.toSet());
            stringStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return menuItemSet;
    }

    /**
     * Metoda pentru importarea de useri dintr-un fisier .csv
     * @param fileName numele fisierului
     * @return set de User
     */
    public Set<User> importUsers(String fileName){
        Set<User> menuItemSet = new HashSet<>();
        try {
            Stream<String> stringStream = Files.lines(Paths.get("src\\main\\resources\\" + fileName));
            menuItemSet = stringStream
                    .skip(1)
                    .distinct()
                    .map(x -> x.split(","))
                    .map(x -> new User(Long.parseLong(x[0]), x[1], x[2], Integer.parseInt(x[3])))
                    .collect(Collectors.toSet());
            stringStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return menuItemSet;
    }
}
