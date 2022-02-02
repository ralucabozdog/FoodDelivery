package model.users;

import java.io.Serializable;
import java.util.Objects;

/**
 * Clasa pentru modelarea java a unui utilizator
 *
 * @author Raluca - Delia Bozdog
 * @since 25.05.2021
 */
public class User implements Serializable {
    /**
     * Id-ul utilizatorului
     */
    private long id;
    /**
     * Numele de utilizator
     */
    private String username;
    /**
     * Parola utilizatorului
     */
    private String password;
    /**
     * Tipul utilizatorului: 0 pentru admin, 1 pentru angajat, 2 pentru client
     */
    private int type;

    /**
     * Constructorul clasei
     * @param id id-ul userului
     * @param username numele utilizatorului
     * @param password parola utilizatorului
     * @param type tipul utilizatorului
     */
    public User(long id, String username, String password, int type){
        this.id = id;
        this.username = username;
        this.password = password;
        this.type = type;
    }

    /**
     * Metoda getter pentru id-ul utilizatorului
     * @return id-ul utilizatorului
     */
    public long getId() {
        return id;
    }

    /**
     * Metoda getter pentru numele utilizatorului
     * @return numele utilizatorului
     */
    public String getUsername() {
        return username;
    }

    /**
     * Metoda getter pentru parola utilizatorului
     * @return parola utilizatorului
     */
    public String getPassword() {
        return password;
    }

    /**
     * Metoda getter pentru tipul utilizatorului
     * @return tipul utilizatorului
     */
    public int getType() {
        return type;
    }

    /**
     * Metoda pentru manipularea cu ajutorul HashSet
     * @param o obiect cu care se face compararea obiectului curent
     * @return true daca obiectul curent este egal cu obiectul parametru; false altfel
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && username.equals(user.username) && password.equals(user.password);
    }

    /**
     * Metoda pentru manipularea cu ajutorul HashSet
     * @return un intreg pe baza caruia se face maparea valorilor in HashSet
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, username, password);
    }

    /**
     * Metoda auxiliara pentru afisarea unui client
     * @return un String ce contine toate caracteristicile unui client
     */
    public String toString(){
        return "ID: " + id + " USERNAME: " + username + " PASSWORD: " + password + " TYPE: " + type;
    }
}
