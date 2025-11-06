package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DeltagerTester {

    private final DeltagerValidator validator = new DeltagerValidator();

   /* private Passord dummyPassord() {
        Passord p = new Passord();
        p.setSalt("1234ABCD1234ABCD1234ABCD1234ABCD");
        p.setHash("ABCDEABCDEABCDEABCDEABCDEABCDEABCDEABCDEABCDEABCDEABCDEABCDE");
        return p;
    }*/

    @Test
    void testGyldigDeltager() {
        Deltager d = new Deltager("12345678", "passord123",
                "Andreas", "Skogly", "Mann");
        assertTrue(validator.erGyldig(d), "Gyldig deltager skal gi true");
    }

    @Test
    void testForKortFornavn() {
        Deltager d = new Deltager("12345678", dummyPassord(),
                "A", "Skogly", "Mann");
        assertFalse(validator.erGyldig(d), "Fornavn med 1 tegn skal gi false");
    }

    @Test
    void testUgyldigMobil() {
        Deltager d = new Deltager("12AB5678", dummyPassord(),
                "Andreas", "Skogly", "Mann");
        assertFalse(validator.erGyldig(d), "Mobilnummer med bokstaver skal gi false");
    }

    @Test
    void testPassordMatcherIkke() {
        assertFalse(validator.passordMatcher("passord123", "passordet12323"),
                "Passord som ikke matcher skal gi false");
        assertTrue(validator.passordMatcher("passord", "passord"),
                "Like passord skal gi true");
    }

    @Test
    void testNullVerdier() {
        Deltager d = new Deltager(null, dummyPassord(),
                null, null, null);
        assertFalse(validator.erGyldig(d), "Null-verdier skal gi false");
    }
}
