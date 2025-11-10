package model;

public class DeltagerValidator {

    public boolean erGyldig(Deltager d) {
        if (d == null) return false;
        if (d.getFornavn() == null || d.getFornavn().length() < 2) return false;
        if (d.getEtternavn() == null || d.getEtternavn().length() < 2) return false;
        if (d.getMobil() == null || !d.getMobil().matches("\\d{8}")) return false;

        if (d.getKjonn() == null ||
                !(d.getKjonn().equalsIgnoreCase("Mann") ||
                        d.getKjonn().equalsIgnoreCase("Kvinne"))) return false;

        return true;
    }


    public boolean passordMatcher(String passord1, String passord2) {
        if (passord1 == null || passord2 == null) return false;
        return passord1.equals(passord2);
    }
}
