package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



// Har prøvd å lage en tester ut i fra at oppgaven vil at vi skal teste den med JUNIT. Har ikke fått det til å funke enda da.
class DeltagerTester {

    private final DeltagerValidator validator = new DeltagerValidator();

    @Test
    void testGyldigDeltager() {
        Deltager d = new Deltager("12345678", "hemmelig", "Andreas", "Skogly", "Mann");
        assertTrue(validator.erGyldig(d), "Gyldig deltager skal gi true");
    }

    @Test
    void testForKortFornavn() {
        Deltager d = new Deltager("12345678", "hemmelig", "A", "Skogly", "Mann");
        assertFalse(validator.erGyldig(d), "Fornavn med 1 tegn skal gi false");
    }

    @Test
    void testUgyldigMobilnummer() {
        Deltager d = new Deltager("12AB5678", "hemmelig", "Andreas", "Skogly", "Mann");
        assertFalse(validator.erGyldig(d), "Mobilnummer med bokstaver skal gi false");
    }

    @Test
    void testPassordMatcherIkke() {
        assertFalse(validator.passordMatcher("hemmelig", "feilpassord"), "Passord som ikke matcher skal gi false");
        assertTrue(validator.passordMatcher("hemmelig", "hemmelig"), "Like passord skal gi true");
    }

    @Test
    void testNullVerdier() {
        Deltager d = new Deltager(null, null, null, null, null);
        assertFalse(validator.erGyldig(d), "Null-verdier skal gi false");
    }
}
