package no.oslomet.cs.algdat.Oblig2;


////////////////// class DobbeltLenketListe //////////////////////////////


import java.util.Comparator;
import java.util.Iterator;
import java.util.Objects;


public class DobbeltLenketListe<T> implements Liste<T> {

    /**
     * Node class
     *
     * @param <T>
     */
    private static final class Node<T> {
        private T verdi;                   // nodens verdi
        private Node<T> forrige, neste;    // pekere

        private Node(T verdi, Node<T> forrige, Node<T> neste) {
            this.verdi = verdi;
            this.forrige = forrige;
            this.neste = neste;
        }

        private Node(T verdi) {
            this(verdi, null, null);
        }
    }

    // instansvariabler
    private Node<T> hode;          // peker til den første i listen
    private Node<T> hale;          // peker til den siste i listen
    private int antall;            // antall noder i listen
    private int endringer;         // antall endringer i listen

    public DobbeltLenketListe() {
        throw new UnsupportedOperationException();
    }

    public DobbeltLenketListe(T[] a) {
        // Sjekker om tabellen er tom
        Objects.requireNonNull(a, "Tabellen a er null!");
        // Setter først hode og hale til null
        hode = null; hale = null;
        for (int i = 0; i < a.length; i++) {
            // Sjekker om a[i] har en verdi
            if (a[i] != null) {
                // Lager ny node objekt
                Node<T> nyNode = new Node<>(a[i]);
                // Dersom listen er tom legges noden som hodet
                if (hode == null) {
                    hode = nyNode;
                    hode.forrige = null;
                // Ellers legges noden i slutten av listen
                } else {
                    hale.neste = nyNode;
                    nyNode.forrige = hale;
                }
                // Hale settes til sist innsatt node
                hale = nyNode;
                hale.neste = null;
                // Antall noder i listen økes
                antall++;
            }
        }
    }

    public Liste<T> subliste(int fra, int til) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int antall() {
        return antall;
    }

    @Override
    public boolean tom() {
        return antall == 0;
    }

    @Override
    public boolean leggInn(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void leggInn(int indeks, T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean inneholder(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T hent(int indeks) {
        // Sjekker at indeksen er innenfor listen
        indeksKontroll(indeks, false);
        // Henter verdien til noden på "indeks"
        return (finnNode(indeks)).verdi;
    }

    @Override
    public int indeksTil(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T oppdater(int indeks, T nyverdi) {
        // Sjekker at indeksen er innenfor listen
        indeksKontroll(indeks, false);
        // Sjekker at verdien ikke er null
        Objects.requireNonNull(nyverdi, "Verdien kan ikke være null!");
        // Øker endringer
        endringer++;
        // Endrer verdien på noden
        return finnNode(indeks).verdi = nyverdi;
    }

    @Override
    public boolean fjern(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T fjern(int indeks) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void nullstill() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException();
    }

    public String omvendtString() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException();
    }

    public Iterator<T> iterator(int indeks) {
        throw new UnsupportedOperationException();
    }

    private class DobbeltLenketListeIterator implements Iterator<T> {
        private Node<T> denne;
        private boolean fjernOK;
        private int iteratorendringer;

        private DobbeltLenketListeIterator() {
            denne = hode;     // p starter på den første i listen
            fjernOK = false;  // blir sann når next() kalles
            iteratorendringer = endringer;  // teller endringer
        }

        private DobbeltLenketListeIterator(int indeks) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext() {
            return denne != null;
        }

        @Override
        public T next() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    } // class DobbeltLenketListeIterator

    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        throw new UnsupportedOperationException();
    }

    // Hjelpemetoden til opg 3
    private Node<T> finnNode(int indeks) {
        Node<T> denne;
        // Finner noden ved neste-pekere fra hode
        if (indeks < antall/2) {
            denne = hode;
            while (indeks > 0) {
                denne = denne.neste;
                indeks--;
            }
        // Finner noden ved forrige-pekere fra halen
        } else {
            denne = hale;
            while (indeks < antall) {
                denne = denne.forrige;
                indeks++;
            }
        }
        return denne;
    }

} // class DobbeltLenketListe


