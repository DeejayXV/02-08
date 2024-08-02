package johnguevarra;

import java.util.HashMap;
import java.util.Map;

public class Catalogo {
    private Map<String, ElementoCatalogo> elementiCatalogo;

    public Catalogo() {
        this.elementiCatalogo = new HashMap<>();
    }

    public void aggiungiElemento(ElementoCatalogo elemento) {
        elementiCatalogo.put(elemento.getIsbn(), elemento);
    }

    public ElementoCatalogo cercaPerIsbn(String isbn) throws Exception {
        if (!elementiCatalogo.containsKey(isbn)) {
            throw new Exception("Elemento con ISBN " + isbn + " non trovato.");
        }
        return elementiCatalogo.get(isbn);
    }

    public void stampaCatalogo() {
        for (ElementoCatalogo elemento : elementiCatalogo.values()) {
            System.out.println(elemento);
        }
    }
}