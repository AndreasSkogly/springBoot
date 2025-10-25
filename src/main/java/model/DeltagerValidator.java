package model;


public class DeltagerValidator {

    public boolean erGyldig(Deltager d) {
        if (d == null) return false;
        if (d.getFornavn() == null || d.getFornavn().length() < 2) return false;
        if (d.getEtternavn() == null || d.getEtternavn().length() < 2) return false;
        if (d.getMobil() == null || !d.getMobil().matches("\\d{8}")) return false;
        if (d.getPassord() == null || d.getPassord().length() < 6) return false;
        if (d.getKjonn() == null || (!d.getKjonn().equalsIgnoreCase("Mann") && !d.getKjonn().equalsIgnoreCase("Kvinne"))) return false;
        return true;
    }

    public boolean passordMatcher(String passord, String rep) {
        return passord != null && passord.equals(rep);
    }
}
