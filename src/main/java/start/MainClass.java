package start;

import presentation.controller.Controller;
import presentation.general.LogInFrame;
/**
 * Clasa principala a programului, singura clasa runnable
 *
 * @author Raluca - Delia Bozdog
 * @since 25.05.2021
 */
public class MainClass {
    /**
     * Metoda main a programului
     * @param args argumentele liniei de comanda
     */
    public static void main(String[] args){
        LogInFrame logInFrame = new LogInFrame();
        Controller controller = new Controller(logInFrame);

        logInFrame.setVisible(true);
    }
}
