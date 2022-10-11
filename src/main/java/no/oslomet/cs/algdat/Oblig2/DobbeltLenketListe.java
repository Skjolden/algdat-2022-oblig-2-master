package no.oslomet.cs.algdat.Oblig2;


////////////////// class DobbeltLenketListe //////////////////////////////


import java.util.*;


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
        /*throw new UnsupportedOperationException();*/
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
        fratilKontroll(antall, fra, til);
        DobbeltLenketListe<T> nySubliste = new DobbeltLenketListe<>();
        int tilStart = fra;
        Node<T> denne = hode;
        // Flytter frem til første node som skal med
        while (tilStart > 0) {
            denne = denne.neste;
            tilStart--;
        }
        // Legger inn noder fra-til
        for (int i = fra; i < til; i++) {
            nySubliste.leggInn(denne.verdi);
            denne = denne.neste;
            nySubliste.antall++; // Usikker
        }
        return nySubliste;
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
        Objects.requireNonNull(verdi, "Null-verdier er ikke tillatt");

        Node<T> nyNode = new Node<>(verdi);
        //Hvis listen er tom
        if(antall==0){
            hode = nyNode;
            hale = nyNode;
            hode.neste = null;
            hode.forrige = null;
            hale.neste = null;
            hale.forrige = null;
        }
        //Hvis listen ikke er tom
        else{
            //Mellomlagrer forrige hale
            Node lagre = hale;
            //Legger ny node bakerst
            hale = nyNode;
            hale.neste = null;
            hale.forrige = lagre;
            //Gammel hale får ny peker til ny hale
            lagre.neste = hale;
        }

        antall++;
        endringer++;

        return true;
    }

    @Override
    public void leggInn(int indeks, T verdi) {
        // Sjekker at det er en gyldig verdi
        Objects.requireNonNull(verdi, "Det kan ikke være en null-verdi!");
        // Sjekker at indeks er gyldig
        indeksKontroll(indeks, true);
        // Nytt node-objekt
        Node<T> nyNode = new Node<>(verdi);
        // Dersom noden er den første verdien i en tom liste:
        if (hode == null) {
            hode = nyNode;
            hode.neste = hale;
            hale.forrige = hode;
            hode.forrige = null;
            hale.neste = null;
        // Dersom noden plasseres først i listen
        } else if (indeks == 0) {
            hode.forrige = nyNode;
            nyNode.neste = hode;
            hode = nyNode;
            hode.forrige = null;
        // Dersom noden plasseres bakerst i listen
        } else if (indeks == antall) {
            hale.neste = nyNode;
            nyNode.forrige = hale;
            hale = nyNode;
            hale.neste = null;
        // Dersom noden plasseres mellom 2 noder
        } else {
            // Plassen til den gamle noden som den nye noden får
            Node<T> gammelIndeks = finnNode(indeks);
            gammelIndeks.forrige.neste = nyNode;
            nyNode.neste = gammelIndeks;
            nyNode.forrige = gammelIndeks.forrige;
            gammelIndeks.forrige = nyNode;
        }
        antall++;
        endringer++;
    }

    @Override
    public boolean inneholder(T verdi) {
        //Hvis verdien finnes:
        if(indeksTil(verdi)!= -1){
            return true;
        }

        //Hvis verdien ikke finnes:
        return false;
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
        //hvis verdien er null
        if(verdi == null){
            return -1;
        }

        //Hjelpevariabel
        Node <T> node = hode;

        //søker etter verdi
        for(int i = 0; i < antall; i++){
            //Hvis nodens verdi er lik parameter-verdien, returner i (=indeksen)
            if(node.verdi.equals(verdi)){
                return i;
            }
            node = node.neste;
        }

        //Hvis verdien ikke er funnet:
        return -1;
    }

    @Override
    public T oppdater(int indeks, T nyverdi) {
        // Sjekker at indeksen er innenfor listen
        indeksKontroll(indeks, false);
        // Sjekker at verdien ikke er null
        Objects.requireNonNull(nyverdi, "Verdien kan ikke være null!");
        T gammelVerdi = finnNode(indeks).verdi;
        // Endrer verdien på noden
        finnNode(indeks).verdi = nyverdi;
        // Øker endringer
        endringer++;
        return gammelVerdi;
    }

    @Override
    public boolean fjern(T verdi) {
        if(verdi==null){
            return false;
        }

        //Hvis liste ikke er tom??
        Node<T> node = null;   //Hjelpevariabel
        Node<T> teller = hode;   //Teller, starter fra hodet

        //Så lenge telleren ikke peker null
        while(teller != null){
            //hvis tellerens verdi er lik parameterverdien
            if(teller.verdi.equals(verdi)){
                node = teller;      //Noden som skal fjernes
                break;      //Verdi funnet, stopper løkken
            }
            teller = teller.neste;    //Sjekker neste verdi i neste gjennomgang
        }

        //Hvis node-verdien ikke er funnet
        if(node == null){
            return false;
        }

        //

        //Hvis det er mer enn én node i liste:
        if(antall != 1){
            //Hvis noden som skal fjenes er første verdien
            if(node == hode){
                hode = hode.neste;
                hode.forrige = null;
            }
            //Hvis noden som skal fjernes er siste verdien
            else if(node == hale){
                hale = hale.forrige;
                hale.neste = null;
            }
            else{
                Node<T> p = node.forrige;   //Noden foran noden som skal fjernes
                Node<T> r = node.neste;     //Noden etter noden som skal fjernes
                p.neste = node.neste;
                r.forrige = node.forrige;
            }
        }
        //Hvis det kun er én node:
        else{
            hode = null;
            hale = null;
        }

        endringer++;
        antall--;

        return true;
    }

    @Override
    public T fjern(int indeks) {
        //Sjekker om indeks er større enn null, og mindre enn antall
        indeksKontroll(indeks, false);

        T node;   //Hjelpevariabel

        //Hvis første verdi skal fjernes
        if (indeks == 0) {
            node = hode.verdi;  //Tar vare på verdien
            if(antall > 1){     //hvis flere enn 1 verdi i listen
                hode = hode.neste;
                hode.forrige = null;
            }else{      //Hvis bare 1 verdi i listen
                hode = null;
                hale = null;
            }

        }
        //Hvis siste verdi skal fjernes
        else if (indeks == antall - 1) {
            node = hale.verdi;  //Tar vare på verdien
            hale = hale.forrige;
            hale.neste = null;
        }
        //hvis en midt-verdi skal fjernes
        else {
            Node<T> q = finnNode(indeks);     //noden som skal fjernes
            node = q.verdi; //Tar vare på verdien
            Node<T> p = q.forrige;        //Noden før
            Node<T> r = q.neste;     //Noden etter

            //Korrigerer pekere
            p.neste = q.neste;
            r.forrige = q.forrige;
        }

        antall--;
        endringer++;

        return node;
    }

    @Override
    public void nullstill() {
        Node<T> denne = hode;
        // Sjekker at listen ikke allerede er tom
        if (antall > 0) {
            // Tømmer listen for noder så lenge det er mer enn en node
            while (antall > 1) {
                denne.forrige = null;
                denne = denne.neste;
                denne.forrige.neste = null;
                denne.forrige.verdi = null;
                antall--;
            }
            // Tømmer siste noden og resetter hode og hale verdiene
            denne.forrige = null;
            denne.neste = null;
            denne.verdi = null;
            hode = null;
            hale = null;
            antall--;
            endringer++;
        } else {
            throw new IllegalArgumentException("Listen er allerede tom!");
        }
    }

    @Override
    public String toString() {
        StringBuilder ut = new StringBuilder("[");
        Node<T> denne = hode;
        // Sjekker at noden har verdi
        if (denne != null) {
            // Legger til alle verdiene
            while(denne != hale) {
                ut.append(denne.verdi);
                // Legger til komma, kun om det er flere noder
                if (denne.neste != null) {
                    ut.append(", ");
                }
                denne = denne.neste;
            }
            // Legger til siste verdi uten komma etter
            ut.append(denne.verdi);
        }
        ut.append("]");
        return ut.toString();
    }

    public String omvendtString() {
        StringBuilder utskrift = new StringBuilder();
        utskrift.append("[");

        //Hvis listen ikke er tom
        if(!tom()){
            //Legger til verdien til hale
            utskrift.append(hale.verdi);

            //Hjelpevariabel
            Node<T> node = hale;

            //Legger til de neste nodene (så lenge antall er større enn 1)
            for(int i=1; i < antall; i++){
                node = node.forrige;      //Neste node
                utskrift.append(", ").append(node.verdi);
            }
        }

        utskrift.append("]");
        return utskrift.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new DobbeltLenketListeIterator();
    }

    public Iterator<T> iterator(int indeks) {
        indeksKontroll(indeks, false);
        return new DobbeltLenketListeIterator(indeks);
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
            Node <T> node = finnNode(indeks);       //Bruker hjelpemetoden til oppgave 3
            denne = node;
            fjernOK = false;  // blir sann når next() kalles
            iteratorendringer = endringer;  // teller endringer
        }

        @Override
        public boolean hasNext() {
            return denne != null;
        }

        @Override
        public T next() {
            if(iteratorendringer != endringer){
                throw new ConcurrentModificationException();
            }
            if(hasNext()!= true){
                throw new NoSuchElementException();
            }

            fjernOK = true;
            T verdi = denne.verdi;      //verdien
            denne = denne.neste;     //Flytter til neste node
            return verdi;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    } // class DobbeltLenketListeIterator

    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        throw new UnsupportedOperationException();
    }

    // Hjelpemetoden til oppg 3
    private Node<T> finnNode(int indeks) {
        Node<T> denne;

        // Finner noden ved neste-pekere fra hode
        if (indeks < antall/2 | antall < 2) {
            denne = hode;
            while (indeks > 0) {
                denne = denne.neste;
                indeks--;
            }
        // Finner noden ved forrige-pekere fra halen
        } else {
            denne = hale;
            while (indeks < antall-1) {
                denne = denne.forrige;
                indeks++;
            }
        }
        return denne;
    }

    // fratilKontroll() fra kompendiet
    private static void fratilKontroll(int antall, int fra, int til)
    {
        if (fra < 0)                               // fra er negativ
            throw new IndexOutOfBoundsException
                    ("fra(" + fra + ") er negativ!");

        if (til > antall)                          // til er utenfor listen
            throw new IndexOutOfBoundsException
                    ("til(" + til + ") > antall(" + antall + ")");

        if (fra > til)                             // fra er større enn til
            throw new IllegalArgumentException
                    ("fra(" + fra + ") > til(" + til + ") - illegalt intervall!");
    }

} // class DobbeltLenketListe


