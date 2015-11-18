# Enterprise Bowling (Oppgave brukt i TDD-kurs)
> Kodehåndverk - BEKK

# Oppgave 1: Legge inn spillere (en slags oppvarming)

**Tid:** 20 min

**Mål:** Komme inn i rytmen med red-green-refactor igjen samt lære å teste med exceptions.

**Krav:**
Systemet vi brukte tidligere lot oss starte spillet uten spillere. Det kræsjet hele systemet og vi måtte skru av og på
strømmen i hallen for å kunne fikse det. Klok av skade så skal det ikke være mulig å starte spillet uten minst én
spiller. Om noen prøver å gjøre det skal de få en feilmelding.
(Det holder å kaste en exception)
Rekkefølgen vi legger inn spillerne på er den rekkefølgen de spiller i.

**OBS:**
Det er viktig at dere ikke tester interne ting i klassen (som f.eks. at en liste inneholder spilleren). Da knytter
dere testen opp mot implementasjonen og gjør det vanskeligere å endre testen.

**Forslag til tester:**
* Spill uten spillere kaster en exception
  * `IllegalStateException` kan passe
  * Hint: `@Test`-annotasjonen kan ta parametere
* Spill med en spiller kaster ikke en exception
* Forsøk på å legge inn `null` på en spiller skal også gi en exception
  * `IllegalArgumentException` kan passe

**Tips:**
Forslag til datastrukturer i denne oppgaven er en `Map<Player, BowlingScorer>` for å holde rede på en spillers poeng.
(Her gjenbruker vi koden fra forrige del av kurset.) I tillegg må du holde rede på rekkefølgen på spillere. Dette kan
gjøres med en egen liste av typen `ArrayList<Player>`. Du kan endre på dette senere. Ved skjule implementasjonsdetaljer
står vi friere i ettertid til å endre koden uten å måtte fikse tester og skrive om andre ting.
Et annet bra tips er å lage en metode for å starte et spill med spillere i testklassen.


# Oppgave 2: Starte spillet

**Tid:** 20 min

**Mål:** Lære å bruke mocks/spies for å sjekke hva som har skjedd.

**Krav:**
Når spillet startes vil vi at det skal vises en fancy velkomstanimasjon på banen. Teamet i Latvia holder på med
animasjonen, men dere kan bare gå i gang med å sette det opp slik at det er klart når vi får den.

**Tips:**
Du kan lage en klasse som implementerer interfacet `Display`. I den klassen kan du ta vare på hva som har blitt kalt.
_Husk at spillet må ha spillere for å kunne startes._

**Forslag til tester**:
* Gitt at spillet startes, så skal det vises en velkomstanimasjon på skjermen
  * `Display.showWelcomeScreen()` er metoden som skal kalles
  * Du kan stole på at gutta i Latvia gjør jobben sin, så du trenger bare å sørge for at koden blir kalt.


# Oppgave 3: Spille Bowling

**Tid:** 30 min

**Mål:** Orkestrere en del funksjonalitet i flere komponenter. Samt terpe på bruk av mocks.

**Krav:**
Gitt at spillet er startet så skal hvert kast registreres. Det er noen avanserte sensorer som sender signaler til
systemet om hvor mange kjegler som ble truffet. Vi har følgende krav:

* Alle poeng etter et kast skal registreres på den aktive spilleren
  * En spiller er aktiv til han/hun har kastet sine kast eller fått en strike
* Strikes fører til at en (klein) strike-animasjon vises
* Etter endt runde (en spiller er ferdig med sine kast) skal neste spiller kalles opp (på skjermen)
  * Tips: Hold rede på rundene
  * Tips: Du kan endre på BowlingScorer for å få hjelp til å vite om en spiller er ferdig med runden sin

**Tips:**
Du kan lage en klasse som implementerer interfacet `Display`.
Husk at spillet må ha spillere for å kunne startes.

**Forslag til tester**:

* Gitt at spillet startes, så skal det vises en velkomstanimasjon på skjermen
  * `Display.showWelcomeScreen()` er metoden som skal kalles
  * Du kan stole på at gutta i Latvia gjør jobben sin, så du trenger bare å sørge for at koden blir kalt.


# Oppgave 4: Testing med tid

**Tid:** 30 min

**Mål:** Lære hvordan skrive tester der tid er en faktor

**Krav:**
Fredag er det disco-bowling! Kidsa glede seg sykt og vil ha disco-lys og høy musikk når de bowler.
Vi har allerede en gammel kassettspiller som sørger for høy musikk, men vi trenger å få kontrollert lysene.
Kravet er at hver fredag fra kl. 17:00 og fram til midnatt skal det være discolys.
De skal skrus på som de vanlige lysene - altså, når spillet startes.

**Tips:**
Det finnes ikke noe interface for klassene i Java/C# som du kan stubbe LocalDateTime. Kanskje du kan lage ditt eget?

**Forslag til tester**:

* Vanlige lys på en annen dag (f.eks. søndag kl. 12:30)
* Vanlige lys rett før kl. 17:00
* Discolys når kl. er 17:00
* Discolys når kl. er 23:59
* Vanlige lys lørdag 00:00

_TIPS: Her er det mulighet for å lage en hjelpemetode._


# Oppgave 5: Avslutte spillet

**Tid:** 20 min

**Mål**: Implementere litt mer forretningslogikk og annen bruk av mocks.

**Krav:**
Spillet kan avsluttes når som helst ved at spillerne går opp til skranken og ber om å avslutte.
Når spillet avsluttes må systemet sjekke om en eller flere spillere har fått en ny highscore. Om det er tilfelle må
systemet oppdatere highscore-listen.
Det er viktig at det ikke er mer enn 10 navn på listen.
Sist, men ikke minst, må vi skru av lysene. En fyr som heter Emil har sagt at det er viktig. (Husk, et tørk er nok!)

**Tips:**
* Du har listen med spillere og deres score samt mulighet til å hente en tilsvarende liste med eksisterende highscores.
Nå kommer endelig AlgDat-en til nytte!
* Det er en del nyttige metoder på interfacet til `HighScoreRepository`
* Du må nok være litt kreativ med hva du gjør i mocken for å kunne teste de forskjellige casene.
* Kravet med maks 10 navn er kanskje ikke viktig å teste her...?
  * Ingen fasit, men dere kan bestemme selv hvor logikken ligger

**Forslag til tester**:
* Ikke oppdater om det ikke er en ny highscore
* Oppdater om det er en ny highscore
* Evt. ikke plukke ut mer enn 10 navn
* Må håndtere tom liste
* Må håndtere ny highscore når det allerede er 10 navn på listen

# Oppgave 6: Integrasjonsteste en filbasert highscore tabell

**Tid:** ? min

**Mål**: Lære å skrive integrasjonstester

**Info:**
**Kommer snart**

**Krav:**
* Skal kunne skrive til disk
* Skal kunne lese fra disk
* Skal returnere en av typen `Map<Player, int>`

**Tips:**

**Løsningsforslag:**
