package model;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.HexFormat;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import org.springframework.stereotype.Service;

@Service
public class PassordService {

    private static final HexFormat HEX = HexFormat.of();

    public String genererTilfeldigSalt() {
        byte[] salt = new byte[16];
        try {
            SecureRandom.getInstance("SHA1PRNG").nextBytes(salt);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return HEX.formatHex(salt);
    }

    public String hashMedSalt(String passord, String salt) {
        if (passord == null || salt == null) throw new IllegalArgumentException();
        char[] passchar = passord.toCharArray();
        byte[] saltbytes = HEX.parseHex(salt);

        try {
            PBEKeySpec pks = new PBEKeySpec(passchar, saltbytes, 1000, 256);
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] keyhash = skf.generateSecret(pks).getEncoded();
            return HEX.formatHex(keyhash);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean erKorrektPassord(String passord, String salt, String hash) {
        if (passord == null || salt == null || hash == null) throw new IllegalArgumentException();
        return hash.equals(hashMedSalt(passord, salt));
    }
}
