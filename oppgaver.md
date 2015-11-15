# Oppgave 1: Oppvarming

Mål: Komme inn i rytmen med red-green-refactor igjen

# Oppgave X: Testing med tid

**Tid:** 20 min

**Mål:** Lære hvordan skrive tester der tid er en faktor

**Krav:**
Fredag er det disco-bowling! Kidsa glede seg sykt og vil ha disco-lys og høy musikk når de bowler.
Vi har allerede en gammel kassettspiller som sørger for høy musikk, men vi trenger å få kontrollert lysene.
Kravet er at hver fredag fra kl. 17:00 og fram til midnatt skal det være discolys.
De skal skrus på som de vanlige lysene - altså, når spillet startes.

**Tips:**
Det finnes ikke noe interface for LocalDateTime

**Fasit:**

* Lag et interface kalt `Clock` med en metode kalt `now()` som returnerer `LocalDateTime`
* Lag en implementasjon som heter `SystemClock` der `now()`  returnerer `LocalDateTime.now()``
* Legg til interfacet i `Game`-klassen og ta det inn i konstruktøren eller lag et setter
  Bruk `SystemClock` som standardimplementasjon
* Lag en test-implementasjon som heter `AdjustableClock` med en ekstra metode for å sette tiden `setFixedTime(LocalDateTime time)` og evt. `reset()` for å nullstille den.
`now()` returnerer da `fixedTime` on den er satt (!= null) og `new SystemClock().now()` om den ikke er satt


# Oppgave Y: Enkel stubbing


Det må integreres i det nye IT-systemet. Miljøbeviste som vi er ønsker vi at lysene skrus på og av etter behov.
