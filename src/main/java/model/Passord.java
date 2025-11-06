package model;

import jakarta.persistence.Embeddable;

@Embeddable
public class Passord {

    private String hash;
    private String salt;

    public Passord() {}

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
