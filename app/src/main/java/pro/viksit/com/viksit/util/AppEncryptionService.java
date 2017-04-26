package pro.viksit.com.viksit.util;


import android.util.Base64;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by Akshay on 26/04/2017.
 */

public class AppEncryptionService {

    private static final String ALGORITHM = "AES";
    private static final byte[] PRIVATE_KEY = "OXY IS IN ISTAR ".getBytes();

    public String encrypt(String valueToEnc) throws Exception {

        Key key = generateKey();
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);

        byte[] encValue = cipher.doFinal(valueToEnc.getBytes());
        byte[] encryptedBytes = Base64.encode(encValue, Base64.DEFAULT);
        String encryptedValue = new String(encryptedBytes);
        return encryptedValue;
    }

    public String decrypt(String encryptedValue) throws Exception {
        Key key = generateKey();
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);

        byte[] decodedBytes = Base64.decode(encryptedValue.getBytes(), Base64.DEFAULT);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
        String decryptedValue = new String(decryptedBytes);
        return decryptedValue;
    }

    private Key generateKey() throws Exception {
        Key key = new SecretKeySpec(PRIVATE_KEY, ALGORITHM);
        return key;
    }

}