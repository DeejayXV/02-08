package johnguevarra;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Catalogo implements Serializable {
    private Map<String, ElementoCatalogo> elementiCatalogo;

    public Catalogo() {
        this.elementiCatalogo = new HashMap<>();
    }

    public void aggiungiElemento(ElementoCatalogo elemento) {
        elementiCatalogo.put(elemento.getIsbn(), elemento);
    }

    public ElementoCatalogo rimuoviElemento(String isbn) throws Exception {
        if (!elementiCatalogo.containsKey(isbn)) {
            throw new Exception("Elemento con ISBN " + isbn + " non trovato.");
        }
        return elementiCatalogo.remove(isbn);
    }

    public ElementoCatalogo cercaPerIsbn(String isbn) throws Exception {
        if (!elementiCatalogo.containsKey(isbn)) {
            throw new Exception("Elemento con ISBN " + isbn + " non trovato.");
        }
        return elementiCatalogo.get(isbn);
    }

    public List<ElementoCatalogo> cercaPerAnnoPubblicazione(int anno) {
        return elementiCatalogo.values().stream()
                .filter(e -> e.getAnnoPubblicazione() == anno)
                .collect(Collectors.toList());
    }

    public List<ElementoCatalogo> cercaPerAutore(String autore) {
        return elementiCatalogo.values().stream()
                .filter(e -> e instanceof Libro)
                .map(e -> (Libro) e)
                .filter(libro -> libro.getAutore().equalsIgnoreCase(autore))
                .collect(Collectors.toList());
    }

    public void stampaCatalogo() {
        for (ElementoCatalogo elemento : elementiCatalogo.values()) {
            System.out.println(elemento);
        }
    }
}
