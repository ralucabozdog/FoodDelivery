package presentation.controller;

import business.DeliveryService;
import business.IDeliveryService;
import business.Order;
import model.products.BaseProduct;
import model.products.MenuItem;
import model.users.User;
import data.FileWriter;
import data.SerializatorMenuItem;
import data.SerializatorUser;
import presentation.admin.*;
import presentation.admin.reports.OrdersInIntervalFrame;
import presentation.admin.reports.PopularProductsFrame;
import presentation.admin.reports.ProductsOfDayFrame;
import presentation.admin.reports.RegularClientsFrame;
import presentation.client.ClientFrame;
import presentation.client.FilterFrame;
import presentation.employee.EmployeeFrame;
import presentation.general.ErrorPopUp;
import presentation.general.LogInFrame;
import presentation.general.NewClientFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.Time;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * Clasa pentru realizarea conexiunii dintre Model si View
 *
 * @author Raluca - Delia Bozdog
 * @since 25.05.2021
 */
public class Controller {
    /**
     * Fereastra de logare
     */
    private LogInFrame logInFrame;

    /**
     * Fereastra unui admin
     */
    private AdministratorFrame administratorFrame;
    /**
     * Fereastra unui client
     */
    private ClientFrame clientFrame;
    /**
     * Fereastra unui angajat
     */
    private EmployeeFrame employeeFrame;

    /**
     * Fereastra de creare a unui nou cont de client
     */
    private NewClientFrame newClientFrame;

    /**
     * Fereastra de importare a unor produse
     */
    private ImportProductsFrame importProductsFrame;
    /**
     * Fereastra de adaugare a unui nou produs
     */
    private AddProductFrame addProductFrame;
    /**
     * Fereastara de stergere a produselor
     */
    private DeleteProductsFrame deleteProductsFrame;
    /**
     * Fereastra de editare a produselor
     */
    private EditProductsFrame editProductsFrame;
    /**
     * Fereastra de creare a unui produs compus
     */
    private CreateComboFrame createComboFrame;
    /**
     * Fereastra de alegere a tipului de raport ce se doreste a fi generat
     */
    private GenerateReportsFrame generateReportsFrame;

    /**
     * Fereastra de generare a unui raport al tuturor comenzilor plasate intr-un anumit interval orar,
     * indiferent de data
     */
    private OrdersInIntervalFrame ordersInIntervalFrame;
    /**
     * Fereastra de generare a unui raport al tuturor produselor care au fost comandate de mai mult de x ori
     */
    private PopularProductsFrame popularProductsFrame;
    /**
     * Fereastra de generare a unui raport al tuturor clientilor care au facut mai mult de x comenzi,
     * fiecare din acestea avand o valoare de cel putin y ron
     */
    private RegularClientsFrame regularClientsFrame;
    /**
     * Fereastra de generare a unui raport al tuturor produselor coamdate intr-o anumita zi
     */
    private ProductsOfDayFrame productsOfDayFrame;

    /**
     * Fereastra de filtrare a produselor
     */
    private FilterFrame filterFrame;

    /**
     * Clasa de management a aplicatiei
     */
    private DeliveryService deliveryService;

    /**
     * Constructorul clasei presentation.controller.Controller
     * @param logInFrame fereastra de logare
     */
    public Controller(LogInFrame logInFrame){
        this.logInFrame = logInFrame;
        this.logInFrame.addLogInListener(new OpeningFrameAfterLogIn());
        this.logInFrame.addNewClientListener(new OpeningNewClientFrame());
        this.deliveryService = new DeliveryService();
    }

    /**
     * Clasa pentru definirea de ActionListener pentru deschiderea ferestrei urmatoare dupa logare,
     * in functie de tipul de utilizator
     */
    class OpeningFrameAfterLogIn implements ActionListener {
        /**
         * Metoda pentru stabilirea actiunilor ce trebuie executate la intalnirea unui ActionEvent
         * @param e ActionEvent-ul declansator
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = logInFrame.getUsername().getText();
            String password = logInFrame.getPassword().getText();

            List<User> user = deliveryService.getUserSet().stream()
                    .filter(x -> x.getUsername().compareTo(username) == 0)
                    .filter(x -> x.getPassword().compareTo(password) == 0)
                    .collect(Collectors.toList());
            if(user.size() == 0)
                new ErrorPopUp("Invalid username or password").setVisible(true);
            else{
                if(user.get(0).getType() == 0){
                    administratorFrame = new AdministratorFrame();
                    administratorFrame.addImportProductsListener(new OpeningImportProductsFrame());
                    administratorFrame.addAddProductsListener(new OpeningAddProductFrame());
                    administratorFrame.addEditProductsListener(new OpeningEditProductsFrame());
                    administratorFrame.addDeleteProductsListener(new OpeningDeleteProductsFrame());
                    administratorFrame.addCreateComboListener(new OpeningCreateComboFrame());
                    administratorFrame.addGenerateReportsListener(new OpeningGenerateReportsFrame());
                    administratorFrame.setVisible(true);
                }
                else{
                    if(user.get(0).getType() == 1){
                        employeeFrame = new EmployeeFrame();
                        deliveryService.addObserver(employeeFrame);
                        employeeFrame.setVisible(true);
                    }
                    else{
                        clientFrame = new ClientFrame(deliveryService.getMenuItemSet(), user.get(0));
                        clientFrame.addFilterListener(new OpeningFilterProducts());
                        clientFrame.addOrderListener(new PerformingOrderProducts());
                        clientFrame.setVisible(true);
                    }
                }
            }
        }
    }

    /**
     * Clasa pentru definirea de ActionListener pentru deschiderea ferestrei de adaugare a unui client
     */
    class OpeningNewClientFrame implements ActionListener {
        /**
         * Metoda pentru stabilirea actiunilor ce trebuie executate la intalnirea unui ActionEvent
         * @param e ActionEvent-ul declansator
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            newClientFrame = new NewClientFrame();
            newClientFrame.addCreateListener(new PerformingCreateNewClient());
            newClientFrame.setVisible(true);
        }
    }

    /**
     * Clasa pentru definirea de ActionListener la actionarea butonului de adaugare a unui client
     */
    class PerformingCreateNewClient implements ActionListener{
        /**
         * Metoda pentru stabilirea actiunilor ce trebuie executate la intalnirea unui ActionEvent
         * @param e ActionEvent-ul declansator
         */
        @Override
        public void  actionPerformed(ActionEvent e){
            String username = newClientFrame.getUsername().getText();
            String password = newClientFrame.getPassword().getText();
            String confirmPassword = newClientFrame.getConfirmPassword().getText();

            if(password.compareTo(confirmPassword) != 0)
                new ErrorPopUp("Password does not match Confirm password!").setVisible(true);
            else{
                Set<User> userSet = deliveryService.getUserSet();
                long i = userSet.stream()
                                .filter(x -> x.getUsername().compareTo(username) == 0)
                                .count();
                if(i != 0)
                    new ErrorPopUp("A user with this name already exists").setVisible(true);
                else{
                    long totalUsers = userSet.stream()
                                                .count();
                    totalUsers++;
                    User client = new User(totalUsers, username, password, 2);
                    userSet.add(client);
                    new SerializatorUser().serialize(userSet);
                    newClientFrame.dispose();
                }
            }
        }
    }

    /**
     * Clasa pentru definirea de ActionListener pentru deschiderea ferestrei de importare de produse
     */
    class OpeningImportProductsFrame implements ActionListener {
        /**
         * Metoda pentru stabilirea actiunilor ce trebuie executate la intalnirea unui ActionEvent
         * @param e ActionEvent-ul declansator
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            importProductsFrame = new ImportProductsFrame();
            importProductsFrame.addImportListener(new PerformingImportProducts());
            importProductsFrame.setVisible(true);
        }
    }

    /**
     * Clasa pentru definirea de ActionListener la actionarea butonului de import produse
     */
    class PerformingImportProducts implements ActionListener {
        /**
         * Metoda pentru stabilirea actiunilor ce trebuie executate la intalnirea unui ActionEvent
         * @param e ActionEvent-ul declansator
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if(importProductsFrame.getFileName().getText().isEmpty())
                new ErrorPopUp("File name cannot be null!").setVisible(true);
            else{
                FileWriter fileWriter = new FileWriter();
                deliveryService.setMenuItemSet(fileWriter.importMenuItems(importProductsFrame.getFileName().getText()));
                importProductsFrame.dispose();
            }
        }
    }

    /**
     * Clasa pentru definirea de ActionListener pentru deschiderea ferestrei de adaugare a unui produs nou
     */
    class OpeningAddProductFrame implements ActionListener {
        /**
         * Metoda pentru stabilirea actiunilor ce trebuie executate la intalnirea unui ActionEvent
         * @param e ActionEvent-ul declansator
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            addProductFrame = new AddProductFrame();
            addProductFrame.addAddListener(new PerformingAddProduct());
            addProductFrame.setVisible(true);
        }
    }

    /**
     * Clasa pentru definirea de ActionListener la actionarea butonului de adaugare a unui produs nou
     */
    class PerformingAddProduct implements ActionListener {
        /**
         * Metoda pentru stabilirea actiunilor ce trebuie executate la intalnirea unui ActionEvent
         * @param e ActionEvent-ul declansator
         */
        @Override
        public void actionPerformed(ActionEvent e) {

            double rating;
            double calories;
            double protein;
            double fat;
            double sodium;
            double price;

            String title = addProductFrame.getTitleField().getText();
            if(title.isEmpty()){
                new ErrorPopUp("Title cannot be null!").setVisible(true);
                return;
            }
            try{
                rating = Double.parseDouble(addProductFrame.getRating().getText());
                if(rating < 0 || rating > 5){
                    new ErrorPopUp("Rating has to be a real number between 0 and 5!").setVisible(true);
                    return;
                }
            }catch(NumberFormatException exception){
                new ErrorPopUp("Rating has to be a real number!").setVisible(true);
                return;
            }

            try{
                calories = Double.parseDouble(addProductFrame.getCalories().getText());
                if(calories < 0){
                    new ErrorPopUp("Calories has to be a positive real number!").setVisible(true);
                    return;
                }
            }catch(NumberFormatException exception){
                new ErrorPopUp("Calories has to be a real number!").setVisible(true);
                return;
            }

            try{
                protein = Double.parseDouble(addProductFrame.getProtein().getText());
                if(protein < 0){
                    new ErrorPopUp("Protein has to be a positive real number!").setVisible(true);
                    return;
                }
            }catch(NumberFormatException exception){
                new ErrorPopUp("Protein has to be a real number!").setVisible(true);
                return;
            }

            try{
                fat = Double.parseDouble(addProductFrame.getFat().getText());
                if(fat < 0){
                    new ErrorPopUp("Fat has to be a positive real number!").setVisible(true);
                    return;
                }
            }catch(NumberFormatException exception){
                new ErrorPopUp("Fat has to be a real number!").setVisible(true);
                return;
            }

            try{
                sodium = Double.parseDouble(addProductFrame.getSodium().getText());
                if(sodium < 0){
                    new ErrorPopUp("Sodium has to be a positive real number!").setVisible(true);
                    return;
                }
            }catch(NumberFormatException exception){
                new ErrorPopUp("Sodium has to be a real number!").setVisible(true);
                return;
            }

            try{
                price = Double.parseDouble(addProductFrame.getPrice().getText());
                if(price < 0){
                    new ErrorPopUp("Price has to be a positive real number!").setVisible(true);
                    return;
                }
            }catch(NumberFormatException exception){
                new ErrorPopUp("Price has to be a real number!").setVisible(true);
                return;
            }

            BaseProduct baseProduct = new BaseProduct(title, rating, calories, protein, fat, sodium, price);
            deliveryService.getMenuItemSet().add(baseProduct);
            new SerializatorMenuItem().serialize(deliveryService.getMenuItemSet());
            addProductFrame.dispose();
        }
    }

    /**
     * Clasa pentru definirea de ActionListener pentru deschiderea ferestrei de stergere a unor produse
     */
    class OpeningDeleteProductsFrame implements ActionListener {
        /**
         * Metoda pentru stabilirea actiunilor ce trebuie executate la intalnirea unui ActionEvent
         * @param e ActionEvent-ul declansator
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            deleteProductsFrame = new DeleteProductsFrame(deliveryService.getMenuItemSet());
            deleteProductsFrame.addDeleteListener(new PerformingDeleteProducts());
            deleteProductsFrame.setVisible(true);
        }
    }

    /**
     * Clasa pentru definirea de ActionListener la actionarea butonului de stergere produse
     */
    class PerformingDeleteProducts implements ActionListener {
        /**
         * Metoda pentru stabilirea actiunilor ce trebuie executate la intalnirea unui ActionEvent
         * @param e ActionEvent-ul declansator
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            deliveryService.deleteProducts(deleteProductsFrame.getSelectedRows());
            new SerializatorMenuItem().serialize(deliveryService.getMenuItemSet());
            deleteProductsFrame.update(deliveryService.getMenuItemSet());
        }
    }

    /**
     * Clasa pentru definirea de ActionListener pentru deschiderea ferestrei de editare de produse
     */
    class OpeningEditProductsFrame implements ActionListener {
        /**
         * Metoda pentru stabilirea actiunilor ce trebuie executate la intalnirea unui ActionEvent
         * @param e ActionEvent-ul declansator
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            editProductsFrame = new EditProductsFrame(deliveryService.getMenuItemSet());
            editProductsFrame.setVisible(true);
        }
    }

    /**
     * Clasa pentru definirea de ActionListener pentru deschiderea ferestrei de creare a unui produs compus
     */
    class OpeningCreateComboFrame implements ActionListener {
        /**
         * Metoda pentru stabilirea actiunilor ce trebuie executate la intalnirea unui ActionEvent
         * @param e ActionEvent-ul declansator
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            createComboFrame = new CreateComboFrame(deliveryService.getMenuItemSet());
            createComboFrame.addCombineListener(new PerformingCreateCombo());
            createComboFrame.setVisible(true);
        }
    }

    /**
     * Clasa pentru definirea de ActionListener la actionarea butonului de creare a unui produs compus
     */
    class PerformingCreateCombo implements ActionListener {
        /**
         * Metoda pentru stabilirea actiunilor ce trebuie executate la intalnirea unui ActionEvent
         * @param e ActionEvent-ul declansator
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            deliveryService.getMenuItemSet().add(createComboFrame.combineSelectedRows());
            new SerializatorMenuItem().serialize(deliveryService.getMenuItemSet());
            createComboFrame.update(deliveryService.getMenuItemSet());
        }
    }

    /**
     * Clasa pentru definirea de ActionListener pentru deschiderea ferestrei de generare a unor rapoarte
     */
    class OpeningGenerateReportsFrame implements ActionListener {
        /**
         * Metoda pentru stabilirea actiunilor ce trebuie executate la intalnirea unui ActionEvent
         * @param e ActionEvent-ul declansator
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            generateReportsFrame = new GenerateReportsFrame();
            generateReportsFrame.addOrdersInIntervalListener(new OpeningOrdersInIntervalFrame());
            generateReportsFrame.addPopularProductsListener(new OpeningPopularProductsFrame());
            generateReportsFrame.addRegularClientsListener(new OpeningRegularClientsFrame());
            generateReportsFrame.addProductsOfDayListener(new OpeningProductsOfDayFrame());
            generateReportsFrame.setVisible(true);
        }
    }

    /**
     * Clasa pentru definirea de ActionListener pentru deschiderea ferestrei de generare a unui raport
     * al tuturor comenzilor plasate intr-un anumit interval de timp, indiferent de data
     */
    class OpeningOrdersInIntervalFrame implements ActionListener {
        /**
         * Metoda pentru stabilirea actiunilor ce trebuie executate la intalnirea unui ActionEvent
         * @param e ActionEvent-ul declansator
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            ordersInIntervalFrame = new OrdersInIntervalFrame();
            ordersInIntervalFrame.addGenerateListener(new GeneratingOrdersInInterval());
            ordersInIntervalFrame.setVisible(true);
        }
    }

    /**
     * Clasa pentru definirea de ActionListener la actionarea butonului de generare a unui raport
     * al tuturor comenzilor plasate intr-un anumit interval de timp, indiferent de data
     */
    class GeneratingOrdersInInterval implements ActionListener {
        /**
         * Metoda pentru stabilirea actiunilor ce trebuie executate la intalnirea unui ActionEvent
         * @param e ActionEvent-ul declansator
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if(ordersInIntervalFrame.getStartHour().getText().isEmpty()){
                new ErrorPopUp("Fill in start hour!").setVisible(true);
                return;
            }
            if(ordersInIntervalFrame.getEndHour().getText().isEmpty()){
                new ErrorPopUp("Fill in end hour!").setVisible(true);
                return;
            }
            if(ordersInIntervalFrame.getReportName().getText().isEmpty()){
                new ErrorPopUp("Fill in report name!").setVisible(true);
                return;
            }

            Time start = new Time(0, 0, 0);
            Time end = new Time(0, 0, 0);

            try{
                start = Time.valueOf(ordersInIntervalFrame.getStartHour().getText());
                end = Time.valueOf(ordersInIntervalFrame.getEndHour().getText());
            }catch (Exception exception){
                new ErrorPopUp("Start time and end time format is hh:mm:ss").setVisible(true);
                return;
            }

            deliveryService.generateOrdersInIntervalReport(start, end, ordersInIntervalFrame.getReportName().getText());
            ordersInIntervalFrame.dispose();
        }
    }

    /**
     * Clasa pentru definirea de ActionListener pentru deschiderea ferestrei de generare a unui raport
     * al tuturor produselor comandate de mai mult de x ori
     */
    class OpeningPopularProductsFrame implements ActionListener {
        /**
         * Metoda pentru stabilirea actiunilor ce trebuie executate la intalnirea unui ActionEvent
         * @param e ActionEvent-ul declansator
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            popularProductsFrame = new PopularProductsFrame();
            popularProductsFrame.addGenerateListener(new GeneratingPopularProducts());
            popularProductsFrame.setVisible(true);
        }
    }

    /**
     * Clasa pentru definirea de ActionListener la actionarea butonului de generare a unui raport
     * al tuturor produselor comandate de mai mult de x ori
     */
    class GeneratingPopularProducts implements ActionListener {
        /**
         * Metoda pentru stabilirea actiunilor ce trebuie executate la intalnirea unui ActionEvent
         * @param e ActionEvent-ul declansator
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if(popularProductsFrame.getReportName().getText().isEmpty()){
                new ErrorPopUp("Fill in report name!").setVisible(true);
                return;
            }
            if(popularProductsFrame.getNumberOfTimes().getText().isEmpty()){
                new ErrorPopUp("Fill in number of times!").setVisible(true);
                return;
            }

            int nbOfTimes = 0;

            try{
                nbOfTimes = Integer.parseInt(popularProductsFrame.getNumberOfTimes().getText());
            }catch (NumberFormatException exception){
                new ErrorPopUp("Number of times has to be an integer!").setVisible(true);
                return;
            }
            deliveryService.generatePopularProductsReport(nbOfTimes, popularProductsFrame.getReportName().getText());
            popularProductsFrame.dispose();
        }
    }

    /**
     * Clasa pentru definirea de ActionListener pentru deschiderea ferestrei de generare a unui raport
     * al tuturor clientilor care au facut mai mult de x comenzi, fiecare de cel putin y lei
     */
    class OpeningRegularClientsFrame implements ActionListener {
        /**
         * Metoda pentru stabilirea actiunilor ce trebuie executate la intalnirea unui ActionEvent
         * @param e ActionEvent-ul declansator
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            regularClientsFrame = new RegularClientsFrame();
            regularClientsFrame.addGenerateListener(new GeneratingRegularClients());
            regularClientsFrame.setVisible(true);
        }
    }

    /**
     * Clasa pentru definirea de ActionListener la actionarea butonului de generare a unui raport
     * al tuturor clientilor care au facut mai mult de x comenzi, fiecare de cel putin y lei
     */
    class GeneratingRegularClients implements ActionListener {
        /**
         * Metoda pentru stabilirea actiunilor ce trebuie executate la intalnirea unui ActionEvent
         * @param e ActionEvent-ul declansator
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if(regularClientsFrame.getNumberOfTimes().getText().isEmpty()){
                new ErrorPopUp("Fill in number of times!").setVisible(true);
                return;
            }
            if(regularClientsFrame.getMinValue().getText().isEmpty()){
                new ErrorPopUp("Fill in minimum value!").setVisible(true);
                return;
            }
            if(regularClientsFrame.getReportName().getText().isEmpty()){
                new ErrorPopUp("Fill in report name!").setVisible(true);
                return;
            }
            int nbOfTimes = 0;
            double minValue = 0;
            try{
                nbOfTimes = Integer.parseInt(regularClientsFrame.getNumberOfTimes().getText());
                try{
                    minValue = Double.parseDouble(regularClientsFrame.getMinValue().getText());
                }catch(NumberFormatException numberFormatException){
                    new ErrorPopUp("Minimum value has to be a real number!").setVisible(true);
                    return;
                }
            }catch (NumberFormatException exception){
                new ErrorPopUp("Number of times has to be a real number!").setVisible(true);
                return;
            }
            deliveryService.generateRegularClientsReport(nbOfTimes, minValue, regularClientsFrame.getReportName().getText());
            regularClientsFrame.dispose();
        }
    }

    /**
     * Clasa pentru definirea de ActionListener pentru deschiderea ferestrei de generare a unui raport
     * al tuturor produselor comandate intr-o anumita zi
     */
    class OpeningProductsOfDayFrame implements ActionListener {
        /**
         * Metoda pentru stabilirea actiunilor ce trebuie executate la intalnirea unui ActionEvent
         * @param e ActionEvent-ul declansator
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            productsOfDayFrame = new ProductsOfDayFrame();
            productsOfDayFrame.addGenerateListener(new GeneratingProductsOfDay());
            productsOfDayFrame.setVisible(true);
        }
    }
    /**
     * Clasa pentru definirea de ActionListener la actionarea butonului de generare a unui raport
     * al tuturor produselor comandate intr-o anumita zi
     */
    class GeneratingProductsOfDay implements ActionListener {
        /**
         * Metoda pentru stabilirea actiunilor ce trebuie executate la intalnirea unui ActionEvent
         * @param e ActionEvent-ul declansator
         */
        @Override
        public void actionPerformed(ActionEvent e) {

            if(productsOfDayFrame.getReportName().getText().isEmpty()){
                new ErrorPopUp("Fill in report name!").setVisible(true);
                return;
            }
            if(productsOfDayFrame.getDay().getText().isEmpty()){
                new ErrorPopUp("Fill in date!").setVisible(true);
                return;
            }
            Date date = new Date(0,0,0);
            try{
                date = Date.valueOf(productsOfDayFrame.getDay().getText());
            }catch (Exception exception) {
                new ErrorPopUp("Date format is yyyy-mm-dd").setVisible(true);
                return;
            }
            deliveryService.generateProductsOfDayReport(date, productsOfDayFrame.getReportName().getText());
            productsOfDayFrame.dispose();
        }
    }

    /**
     * Clasa pentru definirea de ActionListener pentru deschiderea ferestrei de filtrare a produselor
     */
    class OpeningFilterProducts implements ActionListener {
        /**
         * Metoda pentru stabilirea actiunilor ce trebuie executate la intalnirea unui ActionEvent
         * @param e ActionEvent-ul declansator
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            filterFrame = new FilterFrame();
            filterFrame.addFilterListener(new PerformingFilterProducts());
            filterFrame.setVisible(true);
        }
    }

    /**
     * Clasa pentru definirea de ActionListener la actionarea butonului de filtrare
     */
    class PerformingFilterProducts implements ActionListener {
        /**
         * Metoda pentru stabilirea actiunilor ce trebuie executate la intalnirea unui ActionEvent
         * @param e ActionEvent-ul declansator
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            String keyWord = filterFrame.getKeyWord().getText();
            String minRating = filterFrame.getRatingMin().getText();
            String maxRating = filterFrame.getRatingMax().getText();
            String minPrice = filterFrame.getPriceMin().getText();
            String maxPrice = filterFrame.getPriceMax().getText();
            String minCalories = filterFrame.getCaloriesMin().getText();
            String maxCalories = filterFrame.getCaloriesMax().getText();
            String minProtein = filterFrame.getProteinMin().getText();
            String maxProtein = filterFrame.getProteinMax().getText();
            String minFat = filterFrame.getFatMin().getText();
            String maxFat = filterFrame.getFatMax().getText();
            String minSodium = filterFrame.getSodiumMin().getText();
            String maxSodium = filterFrame.getSodiumMax().getText();

            Set<MenuItem> filter1 = new HashSet<>(clientFrame.getMenuItemSet());
            if(keyWord.isEmpty() == false)
                filter1 = IDeliveryService.filterByTitle(keyWord, clientFrame.getMenuItemSet());
            Set<MenuItem> filter2 = new HashSet<>(filter1);
            if(minRating.isEmpty() == false){
                try{
                    if (Double.parseDouble(minRating) >=0 )
                        filter2 = IDeliveryService.filterByMinRating(Double.parseDouble(minRating), filter1);
                    else
                        new ErrorPopUp("Min rating is a positive number").setVisible(true);
                }catch (NumberFormatException ex) {
                    new ErrorPopUp("Min rating is a real number").setVisible(true);
                }
            }
            Set<MenuItem> filter3 = new HashSet<>(filter2);
            if(maxRating.isEmpty() == false){
                try{
                    if (Double.parseDouble(maxRating) >=0 )
                        filter3 = IDeliveryService.filterByMaxRating(Double.parseDouble(maxRating), filter2);
                    else
                        new ErrorPopUp("Max rating is a positive number").setVisible(true);
                }catch (NumberFormatException ex) {
                    new ErrorPopUp("Max rating is a real number").setVisible(true);
                }
            }
            Set<MenuItem> filter4 = new HashSet<>(filter3);
            if(minPrice.isEmpty() == false){
                try{
                    if (Double.parseDouble(minPrice) >=0 )
                        filter4 = IDeliveryService.filterByMinPrice(Double.parseDouble(minPrice), filter3);
                    else
                        new ErrorPopUp("Min price is a positive number").setVisible(true);
                }catch (NumberFormatException ex) {
                    new ErrorPopUp("Min price is a real number").setVisible(true);
                }
            }
            Set<MenuItem> filter5 = new HashSet<>(filter4);
            if(maxPrice.isEmpty() == false){
                try{
                    if (Double.parseDouble(maxPrice) >=0 )
                        filter5 = IDeliveryService.filterByMaxPrice(Double.parseDouble(maxPrice), filter4);
                    else
                        new ErrorPopUp("Max price is a positive number").setVisible(true);
                }catch (NumberFormatException ex) {
                    new ErrorPopUp("Max price is a real number").setVisible(true);
                }
            }
            Set<MenuItem> filter6 = new HashSet<>(filter5);
            if(minCalories.isEmpty() == false){
                try{
                    if (Double.parseDouble(minCalories) >=0 )
                        filter6 = IDeliveryService.filterByMinCalories(Double.parseDouble(minCalories), filter5);
                    else
                        new ErrorPopUp("Min calories is a positive number").setVisible(true);
                }catch (NumberFormatException ex) {
                    new ErrorPopUp("Min calories is a real number").setVisible(true);
                }
            }
            Set<MenuItem> filter7 = new HashSet<>(filter6);
            if(maxCalories.isEmpty() == false){
                try{
                    if (Double.parseDouble(maxCalories) >=0 )
                        filter7 = IDeliveryService.filterByMaxCalories(Double.parseDouble(maxCalories), filter6);
                    else
                        new ErrorPopUp("Max calories is a positive number").setVisible(true);
                }catch (NumberFormatException ex) {
                    new ErrorPopUp("Max calories is a real number").setVisible(true);
                }
            }
            Set<MenuItem> filter8 = new HashSet<>(filter7);
            if(minProtein.isEmpty() == false){
                try{
                    if (Double.parseDouble(minProtein) >=0 )
                        filter8 = IDeliveryService.filterByMinProtein(Double.parseDouble(minProtein), filter7);
                    else
                        new ErrorPopUp("Min protein is a positive number").setVisible(true);
                }catch (NumberFormatException ex) {
                    new ErrorPopUp("Min protein is a real number").setVisible(true);
                }
            }
            Set<MenuItem> filter9 = new HashSet<>(filter8);
            if(maxProtein.isEmpty() == false){
                try{
                    if (Double.parseDouble(maxProtein) >=0 )
                        filter9 = IDeliveryService.filterByMaxProtein(Double.parseDouble(maxProtein), filter8);
                    else
                        new ErrorPopUp("Max protein is a positive number").setVisible(true);
                }catch (NumberFormatException ex) {
                    new ErrorPopUp("Max protein is a real number").setVisible(true);
                }
            }
            Set<MenuItem> filter10 = new HashSet<>(filter9);
            if(minFat.isEmpty() == false){
                try{
                    if (Double.parseDouble(minFat) >=0 )
                        filter10 = IDeliveryService.filterByMinFat(Double.parseDouble(minFat), filter9);
                    else
                        new ErrorPopUp("Min fat is a positive number").setVisible(true);
                }catch (NumberFormatException ex) {
                    new ErrorPopUp("Min fat is a real number").setVisible(true);
                }
            }
            Set<MenuItem> filter11 = new HashSet<>(filter10);
            if(maxFat.isEmpty() == false){
                try{
                    if (Double.parseDouble(maxFat) >=0 )
                        filter11 = IDeliveryService.filterByMaxFat(Double.parseDouble(maxFat), filter10);
                    else
                        new ErrorPopUp("Max fat is a positive number").setVisible(true);
                }catch (NumberFormatException ex) {
                    new ErrorPopUp("Max fat is a real number").setVisible(true);
                }
            }
            Set<MenuItem> filter12 = new HashSet<>(filter11);
            if(minSodium.isEmpty() == false){
                try{
                    if (Double.parseDouble(minSodium) >=0 )
                        filter12 = IDeliveryService.filterByMinSodium(Double.parseDouble(minSodium), filter10);
                    else
                        new ErrorPopUp("Min sodium is a positive number").setVisible(true);
                }catch (NumberFormatException ex) {
                    new ErrorPopUp("Min sodium is a real number").setVisible(true);
                }
            }
            Set<MenuItem> filter13 = new HashSet<>(filter12);
            if(maxSodium.isEmpty() == false){
                try{
                    if (Double.parseDouble(maxSodium) >=0 )
                        filter13 = IDeliveryService.filterByMaxSodium(Double.parseDouble(maxSodium), filter12);
                    else
                        new ErrorPopUp("Max sodium is a positive number").setVisible(true);
                }catch (NumberFormatException ex) {
                    new ErrorPopUp("Max sodium is a real number").setVisible(true);
                }
            }
            clientFrame.update(filter13);
            filterFrame.dispose();
        }
    }

    /**
     * Clasa pentru definirea de ActionListener la actionarea butonului de plasare a unei comenzi
     */
    class PerformingOrderProducts implements ActionListener {
        /**
         * Metoda pentru stabilirea actiunilor ce trebuie executate la intalnirea unui ActionEvent
         * @param e ActionEvent-ul declansator
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            int orderId = deliveryService.getMap().size() + 1;
            Order o = new Order(orderId, (int)clientFrame.getClient().getId());
            deliveryService.placeOrder(o, clientFrame.selectedMenuItemsRows(), clientFrame.selectedMenuItemsQuantities());
        }
    }
}
