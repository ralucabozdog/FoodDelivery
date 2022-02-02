package model.products;

/**
 * Clasa pentru modelarea java a unui produs de baza
 *
 * @author Raluca - Delia Bozdog
 * @since 25.05.2021
 */
public class BaseProduct extends MenuItem {
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
    public BaseProduct(String title, double rating, double calories, double protein, double fat, double sodium, double price) {
        super(title, rating, calories, protein, fat, sodium, price);
    }
}
