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

opg 4

I oppgave 5 metode leggInn(int indeks, T verdi) sjekkes det at verdien ikke er null
og at indeks er gyldig. Så sjekkes det om listen er tom eller ikke. Hvis listen er
tom legges noden inn som hode og hale uten forrige og neste pekere. Dersom listen
ikke er tom og noden skal plasseres først lagres den som nytt hode og forrige 
hodet blir satt som den nye hodens neste. Om noden plasseres bakerst settes den som
den nye halen og den gamle halen sin neste blir den nye noden. Dersom noden plasseres
mellom to noder settes forrige og neste pekerne til den nye rekken ved å alltid
passe på å ha minst en peker som holder listen sammen. Antall og endringer økes.