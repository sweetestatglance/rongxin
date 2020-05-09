//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.utils;

import java.io.IOException;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;
import net.iharder.Base64;

public class SignProvider {
    private SignProvider() {
    }

    public static boolean verify(byte[] pubKeyText, String plainText, byte[] signText) {
        X509EncodedKeySpec bobPubKeySpec = null;

        try {
            bobPubKeySpec = new X509EncodedKeySpec(Base64.decode(pubKeyText));
        } catch (IOException var9) {
            var9.printStackTrace();
        }

        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey pubKey = keyFactory.generatePublic(bobPubKeySpec);
            byte[] signed = Base64.decode(signText);
            Signature signatureChecker = Signature.getInstance("MD5withRSA");
            signatureChecker.initVerify(pubKey);
            signatureChecker.update(plainText.getBytes());
            return signatureChecker.verify(signed);
        } catch (Throwable var8) {
            System.out.println("校验签名失败");
            var8.printStackTrace();
            return false;
        }
    }
}
