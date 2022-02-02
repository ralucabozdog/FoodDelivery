package business;

import model.products.MenuItem;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Interfata ce defineste metodele implementate in DeliveryService, dar si metodele statice de filtrare a produselor
 *
 * @author Raluca - Delia Bozdog
 * @since 25.05.2021
 */
public interface IDeliveryService {
    /**
     * Metoda pentru plasarea unei comenzi
     * @param order comanda ce se doreste a fi plasata
     * @param menuItemList lista produselor ce se comanda
     * @param quantitiesList lista cantitatilor corespunzatoare fiecarui produs
     */
    void placeOrder(Order order, List<MenuItem> menuItemList, List<Integer> quantitiesList);

    /**
     * Metoda ce realizeaza scrierea unei facturi la realizarea unei comenzi
     * @param order comanda ce se doreste a fi plasata
     * @param menuItemList lista produselor ce se comanda
     * @param quantitiesList lista cantitatilor corespunzatoare fiecarui produs
     */
    void generateBill(Order order, List<MenuItem> menuItemList, List<Integer> quantitiesList);

    /**
     * Metoda ce realizeaza stergerea randurilor selectate din tabelul pentru delete products al unui admin
     * @param menuItemList lista produselor ce se doreste a fi sterse
     */
    void deleteProducts(List<MenuItem> menuItemList);

    /**
     * Metoda statica pentru filtrarea produselor disponibile in functie de titlu
     * @param keyWord secventa de caractere ce trebuie sa se regaseasca in cadrul ficarui titlu de produs
     * @param menuItems setul de produse asupra careia se aplica filtrul
     * @return setul de produse dupa filtrare
     */
    static Set<MenuItem> filterByTitle(String keyWord, Set<MenuItem> menuItems){
        Set<MenuItem> filteredMenuItems = menuItems.stream()
                                                    .filter(x -> x.getTitle()
                                                    .toLowerCase(Locale.ROOT)
                                                    .contains(keyWord.toLowerCase(Locale.ROOT)))
                                                    .collect(Collectors.toSet());
        return filteredMenuItems;
    }

    /**
     * Metoda statica pentru filtrarea produselor disponibile in functie de rating minim
     * @param minRating valoarea minima a rating-ului
     * @param menuItemSet setul de produse asupra careia se aplica filtrul
     * @return setul de produse dupa filtrare
     */
    static Set<MenuItem> filterByMinRating(Double minRating, Set<MenuItem> menuItemSet){
        Set<MenuItem> filteredMenuItems = menuItemSet.stream()
                                                     .filter(x -> x.getRating() >= minRating)
                                                     .collect(Collectors.toSet());
        return filteredMenuItems;
    }

    /**
     * Metoda statica pentru filtrarea produselor disponibile in functie de rating maxim
     * @param maxRating valoarea maxima a rating-ului
     * @param menuItemSet setul de produse asupra careia se aplica filtrul
     * @return setul de produse dupa filtrare
     */
    static Set<MenuItem> filterByMaxRating(Double maxRating, Set<MenuItem> menuItemSet){
        Set<MenuItem> filteredMenuItems = menuItemSet.stream()
                                                     .filter(x -> x.getRating()<= maxRating)
                                                     .collect(Collectors.toSet());
        return filteredMenuItems;
    }

    /**
     * Metoda statica pentru filtrarea produselor disponibile in functie de pret minim
     * @param minPrice valoarea minima a pretului
     * @param menuItemSet setul de produse asupra careia se aplica filtrul
     * @return setul de produse dupa filtrare
     */
    static Set<MenuItem> filterByMinPrice(Double minPrice, Set<MenuItem> menuItemSet){
        Set<MenuItem> filteredMenuItems = menuItemSet.stream()
                                                     .filter(x -> x.getPrice() >= minPrice)
                                                     .collect(Collectors.toSet());
        return filteredMenuItems;
    }

    /**
     * Metoda statica pentru filtrarea produselor disponibile in functie de pret maxim
     * @param maxPrice valoarea maxima a pretului
     * @param menuItemSet setul de produse asupra careia se aplica filtrul
     * @return setul de produse dupa filtrare
     */
    static Set<MenuItem> filterByMaxPrice(Double maxPrice, Set<MenuItem> menuItemSet){
        Set<MenuItem> filteredMenuItems = menuItemSet.stream()
                                                     .filter(x -> x.getPrice() <= maxPrice)
                                                     .collect(Collectors.toSet());
        return filteredMenuItems;
    }

    /**
     * Metoda statica pentru filtrarea produselor disponibile in functie de numarul minim de calorii
     * @param minCalories numarul minim de calorii
     * @param menuItemSet setul de produse asupra careia se aplica filtrul
     * @return setul de produse dupa filtrare
     */
    static Set<MenuItem> filterByMinCalories(Double minCalories, Set<MenuItem> menuItemSet){
        Set<MenuItem> filteredMenuItems = menuItemSet.stream()
                                                     .filter(x -> x.getCalories() >= minCalories)
                                                     .collect(Collectors.toSet());
        return filteredMenuItems;
    }

    /**
     * Metoda statica pentru filtrarea produselor disponibile in functie de numarul maxim de calorii
     * @param maxCalories numarul maxim de calorii
     * @param menuItemSet setul de produse asupra careia se aplica filtrul
     * @return setul de produse dupa filtrare
     */
    static Set<MenuItem> filterByMaxCalories(Double maxCalories, Set<MenuItem> menuItemSet){
        Set<MenuItem> filteredMenuItems = menuItemSet.stream()
                                                     .filter(x -> x.getCalories() <= maxCalories)
                                                     .collect(Collectors.toSet());
        return filteredMenuItems;
    }

    /**
     * Metoda statica pentru filtrarea produselor disponibile in functie de numarul minim de proteine
     * @param minProtein numarul minim de proteine
     * @param menuItemSet setul de produse asupra careia se aplica filtrul
     * @return setul de produse dupa filtrare
     */
    static Set<MenuItem> filterByMinProtein(Double minProtein, Set<MenuItem> menuItemSet){
        Set<MenuItem> filteredMenuItems = menuItemSet.stream()
                                                     .filter(x -> x.getProtein() >= minProtein)
                                                     .collect(Collectors.toSet());
        return filteredMenuItems;
    }

    /**
     * Metoda statica pentru filtrarea produselor disponibile in functie de numarul maxim de proteine
     * @param maxProtein numarul maxim de proteine
     * @param menuItemSet setul de produse asupra careia se aplica filtrul
     * @return setul de produse dupa filtrare
     */
    static Set<MenuItem> filterByMaxProtein(Double maxProtein, Set<MenuItem> menuItemSet){
        Set<MenuItem> filteredMenuItems = menuItemSet.stream()
                                                     .filter(x -> x.getProtein() <= maxProtein)
                                                     .collect(Collectors.toSet());
        return filteredMenuItems;
    }

    /**
     * Metoda statica pentru filtrarea produselor disponibile in functie de numarul minim de grasimi
     * @param minFat numarul minim de grasimi
     * @param menuItemSet setul de produse asupra careia se aplica filtrul
     * @return setul de produse dupa filtrare
     */
    static Set<MenuItem> filterByMinFat(Double minFat, Set<MenuItem> menuItemSet){
        Set<MenuItem> filteredMenuItems = menuItemSet.stream()
                                                     .filter(x -> x.getFat() >= minFat)
                                                     .collect(Collectors.toSet());
        return filteredMenuItems;
    }

    /**
     * Metoda statica pentru filtrarea produselor disponibile in functie de numarul maxim de grasimi
     * @param maxFat numarul maxim de grasimi
     * @param menuItemSet setul de produse asupra careia se aplica filtrul
     * @return setul de produse dupa filtrare
     */
    static Set<MenuItem> filterByMaxFat(Double maxFat, Set<MenuItem> menuItemSet){
        Set<MenuItem> filteredMenuItems = menuItemSet.stream()
                                                     .filter(x -> x.getFat() <= maxFat)
                                                     .collect(Collectors.toSet());
        return filteredMenuItems;
    }

    /**
     * Metoda statica pentru filtrarea produselor disponibile in functie de cantitatea minima de sodiu
     * @param minSodium cantitatea minima de sodiu
     * @param menuItemSet setul de produse asupra careia se aplica filtrul
     * @return setul de produse dupa filtrare
     */
    static Set<MenuItem> filterByMinSodium(Double minSodium, Set<MenuItem> menuItemSet){
        Set<MenuItem> filteredMenuItems = menuItemSet.stream()
                                                     .filter(x -> x.getSodium() >= minSodium)
                                                     .collect(Collectors.toSet());
        return filteredMenuItems;
    }

    /**
     * Metoda statica pentru filtrarea produselor disponibile in functie de cantitatea maxima de sodiu
     * @param maxSodium cantitatea maxima de sodiu
     * @param menuItemSet setul de produse asupra careia se aplica filtrul
     * @return setul de produse dupa filtrare
     */
    static Set<MenuItem> filterByMaxSodium(Double maxSodium, Set<MenuItem> menuItemSet){
        Set<MenuItem> filteredMenuItems = menuItemSet.stream()
                                                     .filter(x -> x.getSodium() <= maxSodium)
                                                     .collect(Collectors.toSet());
        return filteredMenuItems;
    }

    /**
     * Metoda pentru generarea unui raport al comenzilor plasate intr-un anumit interval orar, indiferent de ora
     * @param startTime momentul de inceput al intervalului de timp
     * @param endTime momentul de final al intervalului de timp
     * @param fileName numele fisierului pentru raport
     */
    void generateOrdersInIntervalReport(Time startTime, Time endTime, String fileName);

    /**
     * Metoda pentru generarea unui raport al produselor comandate de cel putin x ori
     * @param numberOfTimes numarul de ori dupa care se analizeaza comenzile
     * @param fileName numele fisierului pentru raport
     */
    void generatePopularProductsReport(int numberOfTimes, String fileName);

    /**
     * Metoda pentru generarea unui raport al clientilor care au comandat de cel putin x ori, fiecare dintre comenzi avand cel putin pretul y
     * @param numberOfTimes numerul de comenzi plasate ce trebuie depasit pentru a lua un client in considerare
     * @param minValue valoarea minima a fiecarei comenzi
     * @param fileName numele fisierului pentru raport
     */
    void generateRegularClientsReport(int numberOfTimes, double minValue, String fileName);

    /**
     * Metoda pentru generarea unui raport al produselor ce s-au comandat intr-o anumita zi
     * @param orderDate data comenzilor ce se iau in calcul
     * @param fileName numele fisierului pentru raport
     */
    void generateProductsOfDayReport(Date orderDate, String fileName);
}
