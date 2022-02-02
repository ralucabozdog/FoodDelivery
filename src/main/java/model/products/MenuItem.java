package model.products;

import java.io.Serializable;
import java.util.Objects;

/**
 * Clasa pentru modelarea java a unei intrari in meniu
 *
 * @author Raluca - Delia Bozdog
 * @since 25.05.2021
 */
public class MenuItem implements Serializable, Comparable {
    /**
     * Titlul produsului
     */
    private String title;
    /**
     * Rating-ul produsului
     */
    private double rating;
    /**
     * Numarul de calorii al produsului
     */
    private double calories;
    /**
     * Numarul de proteine al produsului
     */
    private double protein;
    /**
     * Numarul de grasimi al produsului
     */
    private double fat;
    /**
     * Cantitatea de sodiu a produsului
     */
    private double sodium;
    /**
     * Pretul produsului
     */
    private double price;

    /**
     * Constructorul clasei
     * @param title titlul produsului
     * @param rating rating-ul (intre 0 si 5)
     * @param calories numar de calorii
     * @param protein numar de proteine
     * @param fat numar de grasimi
     * @param sodium cantitate de sodiu
     * @param price pret
     */
    public MenuItem(String title, double rating, double calories, double protein, double fat, double sodium, double price) {
        this.title = title;
        this.rating = rating;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.sodium = sodium;
        this.price = price;
    }

    /**
     * Constructorul fara parametri al clasei
     */
    public MenuItem(){

    }

    /**
     * Metoda pentru manipularea cu ajutorul HashSet
     * @param o obiect cu care se face compararea obiectului curent
     * @return true daca obiectul curent este egal cu obiectul parametru (au acelasi nume); false altfel
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuItem menuItem = (MenuItem) o;
        return title.equals(menuItem.title);
    }

    /**
     * Metoda pentru manipularea cu ajutorul HashSet
     * @return un intreg pe baza caruia se face maparea valorilor in HashSet
     */
    @Override
    public int hashCode() {
        return Objects.hash(title);
    }

    /**
     * Metoda auxiliara pentru afisarea unui produs
     * @return un String ce contine toate caracteristicile unui produs
     */
    public String toString(){
        return "TITLE: " + title + " RATING: " + Double.toString(rating) + " CALORIES: " + Double.toString(calories) + " PROTEIN: " + Double.toString(protein) + " FAT: " + Double.toString(fat) + " SODIUM: " + Double.toString(sodium) + " PRICE: " + Double.toString(price);
    }

    /**
     * Constructorul cu un singur parametru al clasei
     * @param title titlul produsului ce se construieste
     */
    public MenuItem(String title){
        this.title = title;
    }

    /**
     * Metoda getter pentru titlul unui produs
     * @return titlul produsului
     */
    public String getTitle() {
        return title;
    }

    /**
     * Metoda setter pentru titlul unui produs
     * @param title nou titlu al produsului
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Metoda getter pentru rating-ul unui produs
     * @return rating-ul produsului
     */
    public double getRating() {
        return rating;
    }

    /**
     * Metoda setter pentru rating-ul unui produs
     * @param rating noul rating al produsului
     */
    public void setRating(double rating) {
        this.rating = rating;
    }

    /**
     * Metoda getter pentru caloriile unui produs
     * @return caloriile produsului
     */
    public double getCalories() {
        return calories;
    }

    /**
     * Metoda setter pentru caloriile unui produs
     * @param calories noul numar de calorii al produsului
     */
    public void setCalories(double calories) {
        this.calories = calories;
    }

    /**
     * Metoda getter pentru proteinele unui produs
     * @return proteinele produsului
     */
    public double getProtein() {
        return protein;
    }

    /**
     * Metoda setter pentru proteinele unui produs
     * @param protein noul numar de proteine al produsului
     */
    public void setProtein(double protein) {
        this.protein = protein;
    }

    /**
     * Metoda getter pentru grasimile unui produs
     * @return grasimile produsului
     */
    public double getFat() {
        return fat;
    }

    /**
     * Metoda setter pentru grasimile unui produs
     * @param fat noul numar de grasimi ale produsului
     */
    public void setFat(double fat) {
        this.fat = fat;
    }

    /**
     * Metoda getter pentru cantitatea de sodiu ale unui produs
     * @return cantitatea de sodiu a produsului
     */
    public double getSodium() {
        return sodium;
    }

    /**
     * Metoda setter pentru cantitatea de sodiu ale unui produs
     * @param sodium noa cantitate de sodiu a produsului
     */
    public void setSodium(double sodium) {
        this.sodium = sodium;
    }

    /**
     * Metoda getter pentru pretul unui produs
     * @return pretul produsului
     */
    public double getPrice() {
        return price;
    }

    /**
     * Metoda setter pentru pretul unui produs
     * @param price noul pret al produsului
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Metoda pentru compararea a doua produse
     * @param o obiectul cu care se face compararea
     * @return -1 daca obiectul curent precede obiectul o; 1 daca il succede; 0 daca sunt egale
     */
    @Override
    public int compareTo(Object o) {
        if(this.title.compareTo(((MenuItem) o).title) < 0)
            return -1;
        if(this.title.compareTo(((MenuItem) o).title) > 0)
            return 1;
        if(this.rating < ((MenuItem) o).rating)
            return 1;
        if(this.rating > ((MenuItem) o).rating)
            return -1;
        if(this.price < ((MenuItem) o).price)
            return -1;
        if(this.price > ((MenuItem) o).price)
            return 1;
        if(this.calories < ((MenuItem) o).calories)
            return -1;
        if(this.calories > ((MenuItem) o).calories)
            return 1;
        if(this.protein < ((MenuItem) o).protein)
            return -1;
        if(this.protein > ((MenuItem) o).protein)
            return 1;
        if(this.fat < ((MenuItem) o).fat)
            return -1;
        if(this.fat > ((MenuItem) o).fat)
            return 1;
        if(this.sodium < ((MenuItem) o).sodium)
            return -1;
        if(this.sodium > ((MenuItem) o).sodium)
            return 1;
        return 0;
    }
}
