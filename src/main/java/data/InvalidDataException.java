package data;

/**
 * Clasa de exceptie folosita la semnalarea unei inconsistente in datele ce se doresc a fi introduse in baza de date
 *
 * @author Raluca - Delia Bozdog
 * @since 25.05.2021
 */
public class InvalidDataException extends Exception{
    /**
     * Mesajul clasei de exceptie, particularizat in functie de cazul in care este generata exceptia
     */
    private String message;

    /**
     * Constructorul clasei InvalidDataException
     * @param message mesajul de eroare ce va fi scris in pop-up de eroare in cazul generarii acestei exceptii
     */
    public InvalidDataException(String message){
        this.message = message;
    }

    /**
     * Metoda getter pentru mesajul exceptiei
     * @return mesajul obiectului InvalidDataException curent
     */
    public String getMessage(){
        return this.message;
    }
}
