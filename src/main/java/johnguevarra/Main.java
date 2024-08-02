package johnguevarra;

public class Main {
    public static void main(String[] args) {
        Catalogo catalogo = new Catalogo();

        Libro libro1 = new Libro("978-3-16-148410-0", "Il Signore degli Anelli", 1954, 1216, "J.R.R. Tolkien", "Fantasy");
        Rivista rivista1 = new Rivista("978-1-23-456789-7", "National Geographic", 2023, 120, Rivista.Periodicita.MENSILE);

        catalogo.aggiungiElemento(libro1);
        catalogo.aggiungiElemento(rivista1);

        catalogo.stampaCatalogo();

        try {
            ElementoCatalogo elementoCercato = catalogo.cercaPerIsbn("978-3-16-148410-0");
            System.out.println("Elemento trovato: " + elementoCercato);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        try {
            ElementoCatalogo elementoCercato2 = catalogo.cercaPerIsbn("978-1-23-456789-7"); 
            System.out.println("Elemento trovato: " + elementoCercato2);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}