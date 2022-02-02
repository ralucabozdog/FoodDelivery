package business;

import model.products.MenuItem;
import model.users.User;
import data.SerializatorMenuItem;
import data.SerializatorOrder;
import data.SerializatorUser;
import presentation.general.ErrorPopUp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Time;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Clasa folosita la realizarea anumitor operatii caracteristice pentru aplicatia de catering
 *
 * @author Raluca - Delia Bozdog
 * @since 25.05.2021
 */
public class DeliveryService extends Observable implements IDeliveryService{
    /**
     * Set-ul de MenuItem
     */
    private Set<MenuItem> menuItemSet;
    /**
     * Set-ul de useri
     */
    private Set<User> userSet;
    /**
     * Map al fiecarui order cu lista sa de menuItem
     */
    private Map<Order, List<MenuItem>> orderMap;

    /**
     * Constructorul clasei DeliveryService
     */
    public DeliveryService() {
        this.orderMap = new SerializatorOrder().deserialize();
        menuItemSet = new SerializatorMenuItem().deserialize();
        userSet = new SerializatorUser().deserialize();
        orderMap = new SerializatorOrder().deserialize();
    }

    /**
     * Metoda accesoare a set-ului de MenuItems
     * @return set-ul de MenuItems
     */
    public Set<MenuItem> getMenuItemSet() {
        return menuItemSet;
    }

    /**
     * Metoda mutatoare a set-ului de MenuItems
     * @param menuItemSet noua valoare a set-ușui de MenuItems
     */
    public void setMenuItemSet(Set<MenuItem> menuItemSet) {
        this.menuItemSet = menuItemSet;
    }

    /**
     * Metoda accesoare a map-ului de orders și lista de MenuItems corespunzatoare fiecarui order
     * @return map-ul de orders si lista de MenuItems corespunzatoare
     */
    public Map<Order, List<MenuItem>> getMap() {
        return orderMap;
    }

    /**
     * Metoda accesoare a set-ului de users
     * @return set-ul de users
     */
    public Set<User> getUserSet() {
        return userSet;
    }

    public void setUserSet(Set<User> userSet) {
        this.userSet = userSet;
    }

    /**
     * Metoda ce realizeaza plasarea unei comenzi
     * @invariant validData()
     * @precondition assert validData() : true
     * @postcondition assert billFile has been created
     * @param order comanda ce se doreste a fi plasata
     * @param menuItemList lista produselor ce se comanda
     * @param quantitiesList lista cantitatilor corespunzatoare fiecarui produs
     */
    @Override
    public void placeOrder(Order order, List<MenuItem> menuItemList, List<Integer> quantitiesList) {
        assert validData(menuItemList, quantitiesList) : true;
        generateBill(order, menuItemList, quantitiesList);
        List<MenuItem> finalMenuItemList = new ArrayList<>();
        for(int i = 0 ; i < quantitiesList.size(); i++){
            for(int j = 0; j < quantitiesList.get(i); j++){
                finalMenuItemList.add(menuItemList.get(i));
            }
        }
        this.orderMap.put(order, finalMenuItemList);
        setChanged();
        notifyObservers();
        new SerializatorOrder().serialize(orderMap);
        assert (new File("Order " + order.getOrderID() + ".txt").exists() && !(new File("Order " + order.getOrderID() + ".txt").isDirectory())) : true;
    }

    /**
     * Metoda ce realizeaza scrierea unei facturi la realizarea unei comenzi
     * @invariant validData()
     * @precondition assert validData() : true
     * @postcondition assert billFile has been created
     * @param order comanda ce se doreste a fi plasata
     * @param menuItemList lista produselor ce se comanda
     * @param quantitiesList lista cantitatilor corespunzatoare fiecarui produs
     */
    @Override
    public void generateBill(Order order, List<MenuItem> menuItemList, List<Integer> quantitiesList) {
        assert validData(menuItemList, quantitiesList) : true;
        String str = new String("Order " + (order.getOrderID()));
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        try {
            FileWriter fileWriter = new FileWriter(str + ".txt" , true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            int clientId = order.getClientID();
            User user = userSet.stream()
                                .filter(x -> x.getId() == clientId)
                                .collect(Collectors.toList())
                                .get(0);
            bufferedWriter.write(str + "\n\n" + user.getUsername() + ", thank you for your order!\n\nHere is a quick overview of your order from " + dtf.format(order.getOrderDate())+ "\n\n");

            Double total = 0.0;
            int i = 1;
            for(MenuItem product : menuItemList){
                total = total + product.getPrice() * quantitiesList.get(i - 1);
                bufferedWriter.write("Product " + i + ":    " + product.getTitle() + "        " + product.getPrice() + " RON      x " + quantitiesList.get(i - 1) + "\n");
                i++;
            }
            bufferedWriter.write("\nTOTAL:      " + total + " RON");
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        assert (new File("Order " + order.getOrderID() + ".txt").exists() && !(new File("Order " + order.getOrderID() + ".txt").isDirectory())) : true;
    }

    /**
     * Metoda ce realizeaza stergerea randurilor selectate din tabelul pentru delete products al unui admin
     * @param menuItemList lista produselor ce se doreste a fi sterse
     */
    @Override
    public void deleteProducts(List<MenuItem> menuItemList) {
        for(MenuItem m : menuItemList){
            this.menuItemSet.remove(m);
        }
    }

    /**
     * Metoda pentru generarea unui raport al comenzilor plasate intr-un anumit interval orar, indiferent de ora
     * @invariant validData()
     * @precondition assert validData() : true
     * @postcondition assert reportFile has been created
     * @param startTime momentul de inceput al intervalului de timp
     * @param endTime momentul de final al intervalului de timp
     * @param fileName numele fisierului pentru raport
     */
    @Override
    public void generateOrdersInIntervalReport(Time startTime, Time endTime, String fileName) {
        assert validData(startTime, endTime) : true;
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("Orders placed from " + startTime + " to " + endTime + " regardless of the day:\n\n");

            List<Order> orderList= this.orderMap.keySet()
                    .stream()
                    .filter(x -> x.getOrderDate().getHour() > startTime.getHours() || x.getOrderDate().getHour() == startTime.getHours() && x.getOrderDate().getMinute() > startTime.getMinutes() || x.getOrderDate().getHour() == startTime.getHours() && x.getOrderDate().getMinute() == startTime.getMinutes() && x.getOrderDate().getSecond() >= startTime.getSeconds())
                    .filter(x -> x.getOrderDate().getHour() < endTime.getHours() || x.getOrderDate().getHour() == endTime.getHours() && x.getOrderDate().getMinute() < endTime.getMinutes() || x.getOrderDate().getHour() == endTime.getHours() && x.getOrderDate().getMinute() == endTime.getMinutes() && x.getOrderDate().getSecond() <= endTime.getSeconds())
                    .collect(Collectors.toList());

            for(Order o : orderList){
                bufferedWriter.write("ORDER ID:  " + o.getOrderID() + "                 ORDER DATE: " + o.getOrderDate().getDayOfMonth() + "/" + o.getOrderDate().getMonthValue() + "/" + o.getOrderDate().getYear() + "        ORDER TIME: " + o.getOrderDate().getHour() + ":" + o.getOrderDate().getMinute() + ":" + o.getOrderDate().getSecond() + "\n    CLIENT ID:  " + o.getClientID() + "\n      PRODUCTS:\n");
                List<MenuItem> menuItemList = this.orderMap.get(o);
                int i = 1;
                double total = 0;
                for (MenuItem m : menuItemList){
                 bufferedWriter.write("             " + i + ") " + m.getTitle() + "   PRICE: " + m.getPrice() + "\n");
                 total = total + m.getPrice();
                 i++;
                }
                bufferedWriter.write("\n             TOTAL: " + total + "\n\n\n");
            }
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            bufferedWriter.write("REPORT GENERATED: " +  dtf.format(LocalDateTime.now()));
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        assert (new File(fileName).exists() && !(new File(fileName).isDirectory())) : true;
    }

    /**
     * Metoda pentru generarea unui raport al produselor comandate de cel putin x ori
     * @invariant validData()
     * @precondition assert validData() : true
     * @postcondition assert reportFile has been created
     * @param numberOfTimes numarul de ori dupa care se analizeaza comenzile
     * @param fileName numele fisierului pentru raport
     */
    @Override
    public void generatePopularProductsReport(int numberOfTimes, String fileName) {
        assert validData(numberOfTimes) : true;
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("Products ordered more than " + numberOfTimes + " times:\n\n");

            Map<MenuItem, Integer> productFrequency = new HashMap<>();
            for(Order o : this.orderMap.keySet()){
                List<MenuItem> menuItems = this.orderMap.get(o);
                for(MenuItem m : menuItems){
                    productFrequency.computeIfPresent(m, (key, val) -> val + 1);
                    productFrequency.putIfAbsent(m, 1);
                }
            }
            int i = 1;
            for(MenuItem m : productFrequency.keySet()){
                if(productFrequency.get(m) > numberOfTimes){
                    bufferedWriter.write(i + ") " + m.getTitle() + " x " + productFrequency.get(m) + "\n");
                    i++;
                }
            }
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            bufferedWriter.write("\n\nREPORT GENERATED: " +  dtf.format(LocalDateTime.now()));
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        assert (new File(fileName).exists() && !(new File(fileName).isDirectory())) : true;
    }

    /**
     * Metoda pentru generarea unui raport al clientilor care au comandat de cel putin x ori, fiecare dintre comenzi avand cel putin pretul y
     * @invariant validData()
     * @precondition assert validData() : true
     * @postcondition assert reportFile has been created
     * @param numberOfTimes numerul de comenzi plasate ce trebuie depasit pentru a lua un client in considerare
     * @param minValue valoarea minima a fiecarei comenzi
     * @param fileName numele fisierului pentru raport
     */
    @Override
    public void generateRegularClientsReport(int numberOfTimes, double minValue, String fileName) {
        assert validData(numberOfTimes, minValue) : true;
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("Clients who ordered more than " + numberOfTimes + " times, with a value of each order higher than " + minValue +" ron:\n\n");

            Map<Integer, Integer> clientIdNumberOfOrders = new HashMap<>();
            for(Order o : this.orderMap.keySet()){
                clientIdNumberOfOrders.computeIfPresent(o.getClientID(), (key, val) -> val + 1);
                clientIdNumberOfOrders.putIfAbsent(o.getClientID(), 1);
            }

            Set<Integer> clientPriceOfOrder = new HashSet<>();
            for(Order o : this.orderMap.keySet()){
                List<MenuItem> menuItems = this.orderMap.get(o);
                double total = 0;
                for(MenuItem m : menuItems){
                    total = total + m.getPrice();
                }
                if(total > minValue){
                    if(!clientPriceOfOrder.contains(o.getClientID())){
                        clientPriceOfOrder.add(o.getClientID());
                    }
                }
                else{
                    if(clientPriceOfOrder.contains(o.getClientID())){
                        clientPriceOfOrder.remove(o.getClientID());
                    }
                }
            }

            int i = 1;
            for(Integer integer : clientIdNumberOfOrders.keySet()){
                if(clientIdNumberOfOrders.get(integer) > numberOfTimes){
                    if(clientPriceOfOrder.contains(integer)){
                        bufferedWriter.write(i + ") Client ID: " + integer + "        Number of orders:" + clientIdNumberOfOrders.get(integer) + "\n");
                        i++;
                    }
                }
            }
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            bufferedWriter.write("\n\nREPORT GENERATED: " +  dtf.format(LocalDateTime.now()));
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        assert (new File(fileName).exists() && !(new File(fileName).isDirectory())) : true;
    }

    /**
     * Metoda pentru generarea unui raport al produselor ce s-au comandat intr-o anumita zi
     * @invariant validData()
     * @precondition assert validData() : true
     * @postcondition assert reportFile has been created
     * @param orderDate data comenzilor ce se iau in calcul
     * @param fileName numele fisierului pentru raport
     */
    @Override
    public void generateProductsOfDayReport(Date orderDate, String fileName) {
        assert validData(orderDate) : true;
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("Products ordered on " + orderDate + ":\n\n");

            List<Order> orderList = this.orderMap.keySet()
                    .stream()
                    .filter(x -> x.getOrderDate().getDayOfMonth() == orderDate.getDate() && x.getOrderDate().getMonthValue() == (orderDate.getMonth() + 1) && x.getOrderDate().getYear() == (orderDate.getYear() + 1900))
                    .collect(Collectors.toList());

            Map<MenuItem, Integer> productFrequency = new HashMap<>();
            for(Order o : orderList){
                List<MenuItem> menuItems = this.orderMap.get(o);
                for(MenuItem m : menuItems){
                    productFrequency.computeIfPresent(m, (key, val) -> val + 1);
                    productFrequency.putIfAbsent(m, 1);
                }
            }

            int i = 1;
            for(MenuItem m : productFrequency.keySet()){
                bufferedWriter.write(i + ") " + m.getTitle() + " x " + productFrequency.get(m) + "\n");
                i++;
            }
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            bufferedWriter.write("\n\nREPORT GENERATED: " +  dtf.format(LocalDateTime.now()));
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        assert (new File(fileName).exists() && !(new File(fileName).isDirectory())) : true;
    }

    /**
     * Invariant al clasei DeliveryService
     * @param startTime ora de inceput a intervalului orar ce se analizeaza
     * @param endTime ora de final a intervalului orar ce se analizeaza
     * @return true daca ora de inceput este inaintea orei de final; false altfel
     */
    public boolean validData(Time startTime, Time endTime){
        if(startTime.after(endTime)){
            new ErrorPopUp("Start time has to be before end time!").setVisible(true);
            return false;
        }
        return true;
    }

    /**
     * Invariant al clasei DeliveryService
     * @param orderDate data comenzilor ce se iau in calcul
     * @return true daca data furnizata este inainte de data curenta; false altfel
     */
    public boolean validData(Date orderDate){
        if(orderDate.after(Date.from(Instant.now()))){
            new ErrorPopUp("You can only analyze orders that have already been placed!").setVisible(true);
            return false;
        }
        return true;
    }

    /**
     * Invariant al clasei DeliveryService
     * @param numberOfTimes numarul de ori dupa care se analizeaza comenzile
     * @return false daca numberOfTimes e mai mic de 0 (caz irelevant); true altfel
     */
    public boolean validData(int numberOfTimes){
        if(numberOfTimes < 0){
            new ErrorPopUp("Not relevant number of times!").setVisible(true);
            return false;
        }
        return true;
    }

    /**
     * Invariant al clasei DeliveryService
     * @param numberOfTimes numerul de comenzi plasate ce trebuie depasit pentru a lua un client in considerare
     * @param minValue valoarea minima a fiecarei comenzi
     * @return true daca ambele valori sunt mai mari sau egale cu 0; false altfel
     */
    public boolean validData(int numberOfTimes, double minValue){
        if(numberOfTimes < 0){
            new ErrorPopUp("Not relevant number of times!").setVisible(true);
            return false;
        }
        if(minValue < 0){
            new ErrorPopUp("Not relevant minimum value!").setVisible(true);
            return false;
        }
        return true;
    }

    /**
     * Invariant al clasei DeliveryService
     * @param menuItemList lista produselor ce se comanda
     * @param quantitiesList lista cantitatilor corespunzatoare fiecarui produs
     * @return true daca lista de produse nu este goala si nu exista cantitati nule sau negative; false altfel
     */
    public boolean validData(List<MenuItem> menuItemList, List<Integer> quantitiesList){
        if(menuItemList.isEmpty()) {
            new ErrorPopUp("Cannot give an empty order!").setVisible(true);
            return false;
        }
        for(int i : quantitiesList){
            if(i <= 0){
                new ErrorPopUp("Quantity has to be a positive, non-zero integer!").setVisible(true);
                return false;
            }
        }
        return true;
    }
}
