# Obligatorisk oppgave 2 i Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i Algoritmer og Datastrukturer. 
Oppgaven er levert av følgende studenter:
* Tiril Lundgaard Baklien, S360821, s360821@oslomet.no
* Eivind Skjolden, S362087, s362087@oslomet.no

# Arbeidsfordeling

I oppgaven har vi hatt følgende arbeidsfordeling:
* Eivind har hatt hovedansvar for oppgaver med oddetall. 
* Tiril har hatt hovedansvar for oppgaver med partall.
* Vi har samarbeidet med oppgave 2

# Oppgavebeskrivelse

I oppgave 1 så gikk vi frem ved å lage return verdier i metodene antall() og tom().
Konstruktøren DobbeltLenketListe(T[] a) lager en dobbeltlenket liste av en ferdig 
definert liste ved å sørge for å kun legge inn verdier som ikke er null og
registrerer antall noder som blir lagt til underveis. Nodene får sine neste- og
forrigepekere satt fortløpende.

I oppgave 2 metode leggInn(T verdi) sjekkes det at verdien ikke er null. Så sjekkes
det om listen er tom eller ikke. Hvis listen er tom legges noden inn som
hode og hale uten forrige og neste pekere. Dersom listen ikke er tom lagres halen
midlertidig og endres til den nye noden, den tidligere halen settes som den nye halen
sin forrige og gammel hale sin neste blir den nye halen. Nye halen får null som neste.
Antall og endringer øker. Boolean true verdi blir returnert dersom den nye noden ble
lagt til.

I oppgave 3 metode finnNode(int indeks) går vi utfra om noden er plassert i første
eller andre halvdel av listen og beveger oss derretter frem til noden fra hodet
eller halen. Når vi har kommet frem til riktig indeks returnerer vi noden.
Metoden hent(int indeks) sjekker først at indeks er innenfor listen deretter 
returnerer den noden sin verdi via metoden finnNode(int indeks).
Metoden oppdater(int indeks, T nyverdi) sjekker først at indeksen er innenfor listen
og at nyverdi ikke er null. Deretter lagrer vi den gamle verdien til noden og 
setter den nye verdien til noden. Til slutt returneres den gamle verdien og antall 
endringer har økt med en.
Metoden subliste(int fra, int til) tester at fra-til er et gyldig intervall. Starter
med å bevege seg frem til første noden i intervallet og legger til alle nodene fra
fra-indeks til til-indeks.

I oppgave 4 lags metoden indeksTil(T verdi). Den begynner med å sjekke om parameter-verdien
er null, og returnerer isåfall -1. Det lages så en hjelpevariabel "node", som settes til å
peke på hodet i første omgang. Deretter itereres det gjennom en for-løkke, og dersom hjelpenodens
verdi er lik parameterverdien, retureres i som er lik indeksposisjonen. Dersom verdiene ikke er like, settes
hjelpevariablen til å peke på neste node, og dersom løkken blir ferdig uten å finne verdien, returneres -1.
Metoden inneholder(T verdi) benytter seg av indeksTil(T verdi) for å sjekke om verdien finnes i en liste.
Dersom indeksTil ikke returnerer -1 vet vi at verdien finnes i listen, og metoden returnerer da true. Ellers
returnerer den false.

I oppgave 5 metode leggInn(int indeks, T verdi) sjekkes det at verdien ikke er null
og at indeks er gyldig. Så sjekkes det om listen er tom eller ikke. Hvis listen er
tom legges noden inn som hode og hale uten forrige og neste pekere. Dersom listen
ikke er tom og noden skal plasseres først lagres den som nytt hode og forrige 
hodet blir satt som den nye hodens neste. Om noden plasseres bakerst settes den som
den nye halen og den gamle halen sin neste blir den nye noden. Dersom noden plasseres
mellom to noder settes forrige og neste pekerne til den nye rekken ved å alltid
passe på å ha minst en peker som holder listen sammen. Antall og endringer økes.

I oppgave 6 lages de to fjern() metodene. fjern(T verdi) sjekker først om verdien som sendes inn er null, og
returnerer false dersom dette er tilfellet. Derettes opprettes det to hjelpevariabler; en tom node, og en teller som
starter med å peke på hodet. Deretter søkes de gjennom listen ved bruk av en while-løkke. Denne sammenligner
verdien til tellern med paramaterverdien. Dersom den finner verdien, settes den tomme noden til verdien av telleren, og
løkken stopper. Dersom verdien ikke er lik settes telleren til neste node, og løkken kjører igjen, helt til eventuelt
alle verdiene har blitt søkt igjennom. Dersom hjelpenoden fortsatt er tom etter gjennomkjøringen (altså at
verdien ikke finnes) returneres false. Deretter skal noden fjernes. Det sjekkes først om listen inneholder
flere enn 1 verdi. Dersom dette er flere enn 1 verdi er det 3 forskjellig tilfeller; at noden som skal fjernes er første
node, siste node eller en node midt i mellom. Hvis det kun er én node i listen, settes både hale og hode til null, slik
at listen nå er tom. Endringer og antall oppdateres, og metoden returnerer true dersom slettingen er vellykket.
Metoden fjern(int indeks) begynner med å sjekke om parameter-indeksen er gyldig ved bruk av indeksKontroll-metoden
som ligger i interfacet Liste. Det opprettes så en hjelpevariabel node, som skal ta vare på verdien av noden vi skal
fjerne. Her også er det tre mulige utfall; første node skal slettes, siste node skal slettes, eller en midt-verdi skal
slettes. Hvis første node skal slettes (indeks = 0), må det også tas hensyn til om dette er eneste noden i listen, slik
at listen nå er tom. Etter gjennomkjøringen endres antall og endringer, og verdien til noden som ble fjernet returneres. 

I oppgave 7 metode nulstill() brukte vi 1. måte. Den sjekker at listen ikke er tom.
Deretter fjerner den alle nodene og pekerne og reduserer antall helt til det er en igjen.
Etter det fjerner den siste noden og setter hode og hale verdiene til null. Antall
reduseres en siste gang og endringer økes.
På å fjerne 1 000 000 noder så bruker:
1. måte ~5 ms
2. måte ~9 ms
Derfor enklere å bare bruke fjern(indeks) metoden i en loop ettersom at det er
minimale forskjeller på tiden. (Tiden er gjennomsnittet av 100 forsøk)

I oppgave 8 er det iteratoren som skal kodes. T next() sjekker først om endringer og iteratorendringer er like, og at
det finnes flere neste-verdier i listen. Dersom dette er tilfellet, settes fjernOK til true, verdien til "denne"-noden
lagres, før "denne"-noden settes til neste verdi. Metoden returnerer verdien til noden. Metoden Iterator<T> iterator()
returnerer en ny DobbeltLenketListeIterator. Konstruktøren DobbeltLenketListeIterator(int indeks) bruker først 
finnNode(indeks) fra oppgave 3 til å finne noden i listen. "Denne"-variablen får verdien til den noden. FjernOK settes
til false, og iteratorendirnger settes lik endringer, likt som i den vanlige konstruktøren.
Metoden Iterator<T> iterator(int indeks) sjekker først om indeksverdien som sendes inn som parameter er gyldig ved bruk
av indeksKontroll-metoden fra interfacet Liste. Dersom indeksen er gyldig, opprettes en instans av
DobbeltLenketListeIterator med denne indeksen sin node som start-node. 