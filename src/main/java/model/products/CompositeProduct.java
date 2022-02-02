package model.products;

import java.util.List;

/**
 * Clasa pentru modelarea java a unui produs compus
 *
 * @author Raluca - Delia Bozdog
 * @since 25.05.2021
 */
public class CompositeProduct extends MenuItem{
    /**
     * Lista de produse caracteristica unui produs compus
     */
    private List<MenuItem> menuItems;

    /**
     * Constructorul clasei
     * @param title titlul produsului compus
     * @param menuItems lista prodselor ce intra in compozitia produsului compus
     */
    public CompositeProduct(String title, List<MenuItem> menuItems) {
        super(title);
        this.menuItems = menuItems;
        this.computeCharacteristics();
    }

    /**
     * Metoda getter a listei de produse a unui produs compus
     * @return lista de produse a unui produs compus
     */
    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    /**
     * Metoda pentru calcularea caracteristicilor unui produs compus
     */
    public void computeCharacteristics(){
        double totalRating = 0;
        double totalCalories = 0;
        double totalProtein = 0;
        double totalFat = 0;
        double totalSodium = 0;
        double totalPrice = 0;

        for(MenuItem baseProduct : this.menuItems){
            totalRating += baseProduct.getRating() / this.menuItems.size();
            totalCalories += baseProduct.getCalories();
            totalProtein += baseProduct.getProtein();
            totalFat += baseProduct.getFat();
            totalSodium += baseProduct.getSodium();
            totalPrice += baseProduct.getPrice();
        }

        this.setRating(totalRating);
        this.setCalories(totalCalories);
        this.setProtein(totalProtein);
        this.setFat(totalFat);
        this.setSodium(totalSodium);
        this.setPrice(totalPrice * 0.9);
    }

}
