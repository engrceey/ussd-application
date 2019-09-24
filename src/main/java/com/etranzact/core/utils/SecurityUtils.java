package com.etranzact.core.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.nio.charset.StandardCharsets;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.KeySpec;

import static com.etranzact.core.constants.AppConstant.SecurityConstants.*;

/**
 *  * Created by johnadeshola on 9/21/19.
 */
@Slf4j
public class SecurityUtils {

    private static String encryption(String text, String secretKey) {
        String encryptedData = "";
        try {

            KeySpec keySpec = new PBEKeySpec(secretKey.toCharArray(), SALT, ITERATION_COUNT);
            AlgorithmParameterSpec paramSpec = new PBEParameterSpec(SALT, ITERATION_COUNT);

            SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);

            Cipher ecipher = Cipher.getInstance(key.getAlgorithm());
            ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);

            byte[] enc = ecipher.doFinal(text.getBytes(UTF_8));

            encryptedData = new String(Base64.encodeBase64(enc), StandardCharsets.UTF_8);
            // escapes for url
            encryptedData = encryptedData.replace('+', '-').replace('/', '_').replace("%", "%25").replace("\n", "%0A");
        } catch (Exception e) {
            log.error("error occurred:: {}", e.getMessage());
        }
        return encryptedData;
    }

    private static String decryption(String textS, String secretKey) {
        try {

            String input = textS.replace("%0A", "\n").replace("%25", "%").replace('_', '/').replace('-', '+');

            byte[] dec = Base64.decodeBase64(input.getBytes(StandardCharsets.UTF_8));

            KeySpec keySpec = new PBEKeySpec(secretKey.toCharArray(), SALT, ITERATION_COUNT);
            AlgorithmParameterSpec paramSpec = new PBEParameterSpec(SALT, ITERATION_COUNT);

            SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);

            Cipher dcipher = Cipher.getInstance(key.getAlgorithm());
            dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);

            byte[] decoded = dcipher.doFinal(dec);

            textS = new String(decoded, StandardCharsets.UTF_8);

        } catch (Exception e) {
            log.error("error occurred:: {}", e.getMessage());
        }

        return textS;

    }

    public static String encryptStringData(String data) {
        return encryption(data, DEFUALT_KEY);
    }

    public static String decryptStringData(String data) {
        return decryption(data, DEFUALT_KEY);
    }

    public static String twoXEncryptStringData(String data) {
        return encryptStringData(encryptStringData(data));
    }

    public static String twoXDecryptStringData(String data) {
        return decryptStringData(decryptStringData(data));
    }

    public static void main(String[] args) {
        System.out.println(twoXEncryptStringData("YAWN447rub404@@"));
    }

}
