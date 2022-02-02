package business;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Clasa pentru modelarea java a unei comenzi
 *
 * @author Raluca - Delia Bozdog
 * @since 25.05.2021
 */
public class Order implements Serializable {
    /**
     * Id-ul comenzii curente
     */
    private int orderID;
    /**
     * Id-ul clientului ce a plasat comanda curenta
     */
    private int clientID;
    /**
     * Data si ora la care s-a plasat comanda
     */
    private LocalDateTime orderDate;

    /**
     * Contsructorul clasei
     * @param orderID id-ul comenzii
     * @param clientID id-ul clientului care plaseaza comanda
     */
    public Order(int orderID, int clientID) {
        this.orderID = orderID;
        this.clientID = clientID;
        this.orderDate = LocalDateTime.now();
    }

    /**
     * Metoda getter pentru id-ul comenzii
     * @return id-ul comenzii care a fost plasata
     */
    public int getOrderID() {
        return orderID;
    }

    /**
     * Metoda getter pentru id-ul clientului
     * @return id-ul clientului care a plasat comanda curenta
     */
    public int getClientID() {
        return clientID;
    }

    /**
     * Metoda getter pentru ora la care a fost plasata comanda curenta
     * @return ora la care a fost plasata comanda curenta
     */
    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    /**
     * Metoda pentru manipularea cu ajutorul HashSet
     * @return un intreg pe baza caruia se face maparea valorilor in HashSet
     */
    @Override
    public int hashCode() {
        return Objects.hash(orderID);
    }

    /**
     * Metoda pentru manipularea cu ajutorul HashSet
     * @param obj obiect cu care se face compararea obiectului curent
     * @return true daca obiectul curent este egal cu obiectul parametru (au acelasi ID); false altfel
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Order ord = (Order) obj;
        return orderID == ord.getOrderID();
    }
}
