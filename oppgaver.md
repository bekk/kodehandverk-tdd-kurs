# Oppgave 1: Legge inn spillere (en slags oppvarming)

**Tid:** 15 min

**Mål:** Komme inn i rytmen med red-green-refactor igjen samt lære å teste med exceptions.

**Krav:**
Systemet vi brukte tidligere lot oss starte spillet uten spillere. Det kræsjet hele systemet og vi måtte skru av og på strømmen i hallen for å kunne fikse det. Klok av skade så skal det ikke være mulig å starte spillet uten minst én spiller. Om noen prøver å gjøre det skal de få en feilmelding.
(Det holder å kaste en exception)
Rekkefølgen vi legger inn spillerne på er den rekkefølgen de spiller i.

**Tips:**
Forslag til datastrukturer i denne oppgaven er en `Map<Player, BowlingScorer>` for å holde rede på en spillers poeng. (Her gjenbruker vi koden fra forrige del av kurset.) I tillegg må du holde rede på rekkefølgen på spillere. Dette kan gjøres med en egen liste av typen `ArrayList<Player>`. Du kan endre på dette senere. Ved skjule implementasjonsdetaljer står vi friere i ettertid til å endre koden uten å måtte fikse tester og skrive om andre ting.
Et annet bra tips er å lage en metode for å starte et spill med spillere i testklassen.

**Forslag til tester:**

* Spill uten spillere kaster en exception
  * `IllegalStateException` kan passe
  * Hint: `@Test`-annotasjonen kan ta parametere
* Spill med en spiller kaster ikke en exception
* Forsøk på å legge inn `null` på en spiller skal også gi en exception
  * `IllegalArgumentException` kan passe


# Oppgave 2: Starte spillet

**Tid:** 15 min

**Mål:** Lære å bruke mocks/spies for å sjekke hva som har skjedd.

**Krav:**
Når spillet startes vil vi at det skal vises en fancy velkomstanimasjon på banen. Tror gutta i Latvia holder på med animasjonen, men dere kan bare gå i gang med å sette det opp slik at det er klart når vi får den.

**Tips:**
Du kan lage en klasse som implementerer interfacet `Display`.
Husk at spillet må ha spillere for å kunne startes.

**Forslag til tester**:

* Gitt at spillet startes, så skal det vises en velkomstanimasjon på skjermen
  * `Display#showWelcomeScreen()` er metoden som skal kalles
  * Du kan stole på at gutta i Latvia gjør jobben sin, så du trenger bare å sørge for at koden blir kalt.

**Løsningsforslag:**

* Lag en mock som implementerer `Display`-interfacet - kall det gjerne `MockDisplay`. I metoden `showWelcomeScreen` setter du en public variabel kalt `hasShownWelcomeScreen` til `true`.
* I testen setter du inn en instans av `MockDisplay`
* Gjør en assert mot `MockDisplay#hasShownWelcomeScreen` og sjekk at den er true
  * Husk å gi gode feilmeldinger om du bruker assertTrue/False



# Oppgave 3: Spille Bowling

**Tid:** 30 min

**Mål:** Orkestrere en del funksjonalitet i flere komponenter. Samt terpe på bruk av mocks.

**Krav:**
Gitt at spillet er startet så skal hvert kast registreres. Det er noen avanserte sensorer som sender signaler til systemet om hvor mange kjegler som ble truffet. Vi har følgende krav:

* Alle poeng etter et kast skal registreres på den aktive spilleren
* Strikes fører til at en teit animasjon vises
* Etter endt runde (en spiller er ferdig med sine kast) skal neste spiller kalles opp

**Tips:**
Du kan lage en klasse som implementerer interfacet `Display`.
Husk at spillet må ha spillere for å kunne startes.

**Forslag til tester**:

* Gitt at spillet startes, så skal det vises en velkomstanimasjon på skjermen
  * `Display#showWelcomeScreen()` er metoden som skal kalles
  * Du kan stole på at gutta i Latvia gjør jobben sin, så du trenger bare å sørge for at koden blir kalt.

**Løsningsforslag:**

* Lag en mock som implementerer `Display`-interfacet - kall det gjerne `MockDisplay`. I metoden `showWelcomeScreen` setter du en public variabel kalt `hasShownWelcomeScreen` til `true`.
* I testen setter du inn en instans av `MockDisplay`
* Gjør en assert mot `MockDisplay#hasShownWelcomeScreen` og sjekk at den er true
  * Husk å gi gode feilmeldinger om du bruker assertTrue/False



# Oppgave X: Avslutte spillet

**Tid:** ? min

**Mål**: Mer forretningslogikk og bruk av mocks.

**Krav:**
Spillet kan avsluttes når som helst ved at spillerne går opp til skranken og ber om å avslutte.
Når spillet avsluttes må systemet sjekke om en eller flere spillere har fått en ny highscore. Om det er tilfelle må systemet oppdatere highscore-listen.

**Tips:**
* Det kan være lurt å lage en metode for å hente ut en spillers poengsum




# Oppgave X: Testing med tid

**Tid:** 20 min

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

**Løsningsforslag:**

* Lag et interface kalt `Clock` med en metode kalt `now()` som returnerer `LocalDateTime`
* Lag en implementasjon som heter `SystemClock` der `now()`  returnerer `LocalDateTime.now()`
* Legg til interfacet i `Game`-klassen og ta det inn i konstruktøren eller lag et setter
  Bruk `SystemClock` som standardimplementasjon
* Lag en test-implementasjon som heter `AdjustableClock` med en ekstra metode for å sette tiden `setFixedTime(LocalDateTime time)` og evt. `reset()` for å nullstille den.
`now()` returnerer da `fixedTime` on den er satt (!= null) og `new SystemClock().now()` om den ikke er satt.
  * Legg merke til at du må bruke `AdjustableClock` og ikke `Clock`-interfacet i testene slik at du kan bruke de nye metodene.
* I `Game`-klassen kan du da hente ut tiden med `clock.now()` framfor å bruke `LocalDateTime.now()`. Gratulerer, du har nå funnet en måte å kontrollere tiden på.
* Det finnes en annen måte å løse dette på ved å lage et interface som har en metode som heter noe sånt som `isDiscoTime()` og returnerer true/false. Da forsvinner konseptet med tid helt, men det er ikke alltid det passer eller er naturlig.

# Oppgave Y: Enkel stubbing


Det må integreres i det nye IT-systemet. Miljøbeviste som vi er ønsker vi at lysene skrus på og av etter behov.
