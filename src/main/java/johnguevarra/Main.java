package johnguevarra;

import johnguevarra.enums.Periodicita;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Catalogo catalogo = new Catalogo();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("Scegli un'operazione:");
            System.out.println("1. Aggiungi un elemento");
            System.out.println("2. Rimuovi un elemento");
            System.out.println("3. Cerca per ISBN");
            System.out.println("4. Cerca per anno di pubblicazione");
            System.out.println("5. Cerca per autore");
            System.out.println("6. Salva su disco");
            System.out.println("7. Carica da disco");
            System.out.println("8. Esci");

            int scelta = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (scelta) {
                    case 1:
                        aggiungiElemento();
                        break;
                    case 2:
                        rimuoviElemento();
                        break;
                    case 3:
                        cercaPerIsbn();
                        break;
                    case 4:
                        cercaPerAnnoPubblicazione();
                        break;
                    case 5:
                        cercaPerAutore();
                        break;
                    case 6:
                        salvaSuDisco();
                        break;
                    case 7:
                        caricaDaDisco();
                        break;
                    case 8:
                        running = false;
                        break;
                    default:
                        System.out.println("Scelta non valida.");
                }
            } catch (Exception e) {
                System.err.println("Errore: " + e.getMessage());
            }
        }
    }

    private static void aggiungiElemento() {
        System.out.println("Scegli il tipo di elemento:");
        System.out.println("1. Libro");
        System.out.println("2. Rivista");
        int scelta = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Inserisci ISBN:");
        String isbn = scanner.nextLine();
        System.out.println("Inserisci titolo:");
        String titolo = scanner.nextLine();
        System.out.println("Inserisci anno di pubblicazione:");
        int annoPubblicazione = scanner.nextInt();
        System.out.println("Inserisci numero di pagine:");
        int numeroPagine = scanner.nextInt();
        scanner.nextLine();

        if (scelta == 1) {
            System.out.println("Inserisci autore:");
            String autore = scanner.nextLine();
            System.out.println("Inserisci genere:");
            String genere = scanner.nextLine();
            catalogo.aggiungiElemento(new Libro(isbn, titolo, annoPubblicazione, numeroPagine, autore, genere));
        } else if (scelta == 2) {
            System.out.println("Inserisci periodicità (SETTIMANALE, MENSILE, SEMESTRALE):");
            String periodicitaStr = scanner.nextLine();
            Periodicita periodicita = Periodicita.valueOf(periodicitaStr.toUpperCase());
            catalogo.aggiungiElemento(new Rivista(isbn, titolo, annoPubblicazione, numeroPagine, periodicita));
        } else {
            System.out.println("Tipo di elemento non valido.");
        }
    }

    private static void rimuoviElemento() throws Exception {
        System.out.println("Inserisci ISBN dell'elemento da rimuovere:");
        String isbn = scanner.nextLine();
        catalogo.rimuoviElemento(isbn);
        System.out.println("Elemento rimosso con successo.");
    }

    private static void cercaPerIsbn() throws Exception {
        System.out.println("Inserisci ISBN:");
        String isbn = scanner.nextLine();
        ElementoCatalogo elemento = catalogo.cercaPerIsbn(isbn);
        System.out.println("Elemento trovato: " + elemento);
    }

    private static void cercaPerAnnoPubblicazione() {
        System.out.println("Inserisci anno di pubblicazione:");
        int anno = scanner.nextInt();
        scanner.nextLine();

        List<ElementoCatalogo> risultati = catalogo.cercaPerAnnoPubblicazione(anno);
        if (risultati.isEmpty()) {
            System.out.println("Nessun elemento trovato per l'anno " + anno);
        } else {
            risultati.forEach(System.out::println);
        }
    }

    private static void cercaPerAutore() {
        System.out.println("Inserisci autore:");
        String autore = scanner.nextLine();

        List<ElementoCatalogo> risultati = catalogo.cercaPerAutore(autore);
        if (risultati.isEmpty()) {
            System.out.println("Nessun elemento trovato per l'autore " + autore);
        } else {
            risultati.forEach(System.out::println);
        }
    }

    private static void salvaSuDisco() {
        System.out.println("Inserisci il nome del file:");
        String nomeFile = scanner.nextLine();

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomeFile))) {
            oos.writeObject(catalogo);
            System.out.println("Archivio salvato su disco.");
        } catch (IOException e) {
            System.err.println("Errore durante il salvataggio: " + e.getMessage());
        }
    }

    private static void caricaDaDisco() {
        System.out.println("Inserisci il nome del file:");
        String nomeFile = scanner.nextLine();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomeFile))) {
            catalogo = (Catalogo) ois.readObject();
            System.out.println("Archivio caricato dal disco.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Errore durante il caricamento: " + e.getMessage());
        }
    }
}
